package com.example.photoapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.appsearch.SetSchemaRequest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName();

    ActivityResultLauncher launcher;  //권한요청 객체

    File selectedFile;  //서버에 전송할 사진 및 미리보기할 사진
    PhotoView photoView;

    UploadManager uploadManager=new UploadManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_internal = findViewById(R.id.bt_internal);
        Button bt_external = findViewById(R.id.bt_external);
        Button bt_regist = findViewById(R.id.bt_regist);
        photoView = findViewById(R.id.photoView);

        bt_internal.setOnClickListener((v) -> {
            openInternal();
        });

        bt_external.setOnClickListener((v) -> {
            openExternal();
        });

        bt_regist.setOnClickListener((v) -> {
            Thread thread=new Thread(){
                @Override
                public void run() {
                    upload();
                }
            };
            thread.start();
        });

        /*
            안드로이드의 새로운 권한 정책으로 인해, 앱 시작과 동시에 사용자로부터 권한에 대한 확인 및 수락을 받도록 함
            단 마시멜로 이전 스마트폰의 경우 허락 불필요
            안드로이드의 새로운 정책을 구현하기 위해서는 사용자에게 권한을 요청하고 수락을 받아야 하는데 이때 사용되는 객체가
            바로 ActivityResultLauncher라 하며 이 객체의 인스턴스를 생성하기 위한 메서드는 registerForActivityResult() 라 함

            매개변수1) 어떤 퍼미션을 요청했는지에 대한 정보 -> ActivityResultContract
            매개변수2) 사용자가 해당 요청에 대해 어떤 반응을 보였는지에 대한 정보를 처리할 콜백메서드
        */
        launcher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                //READ ~~~ true
                //WRITE ~~~ false
                Log.d(TAG, "요청에 대한 사용자의 반응 결과는 " + result);  //콜백메서드

                Iterator<String> it = result.keySet().iterator();  //map에 들어있는 key 값만을 일렬로 늘어서게 함 (순서가 없기 때문에)

                while (it.hasNext()) {  //key의 수만큼 반복문 수행
                    String permission_name = it.next();  //권한명 반환받음

                    //key(권한명)를 이용해 map의 실제 데이터(수락여부 논리값)에 접근
                    boolean granted = result.get(permission_name);
                    if (granted == false) {  //해당 권한에 대해 수락을 안한 경우
                        //일단 거부를 1번 이상하면, 수락할 의도가 없으므로 아래의 메세지를 무조건 수행하면 안됨. 즉, 한번만 나와야 함
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission_name)) {
                            Toast.makeText(MainActivity.this, "권한을 수락해야 이용이 가능합니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            //수락을 2회 이상 거절한 경우
                            Toast.makeText(MainActivity.this, "정상적인 앱 이용을 위해서는 앱설정에서 권한을 수락해주세요.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            }
        });

        if (checkVersion()) {
            //최신스마트폰이므로, 파일에 대한 접근보다는 사용자로부터 허락을 받아야 함
            if (checkGranted()) {

            } else {
                //권한 요청 시도 (사용자에게는 권한 수락에 대한 팝업이 보이게 됨)
                launcher.launch(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                });
            }
        } else {
            //구버전 스마트폰이므로 허락을 받지 않고 파일접근 가능
        }
    }

    //파일 업로드하기
    public void upload(){
        Product product=new Product();
        product.setCategory_idx(1);
        product.setProduct_name("지노");
        product.setBrand("zino");
        product.setPrice(100000000);
        product.setDiscount(0);
        product.setDetail("월급");
        try {
            uploadManager.regist(product, selectedFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkVersion() {
        //마시멜로 폰보터 새로운 권한정책을 적용해야 하므로, 현재 사용자의 폰이 어떤 버전인지 파악
        Log.d(TAG, "SDK_INT : " + Build.VERSION.SDK_INT);
        Log.d(TAG, "VERSION_CODES 마시멜로는 : " + Build.VERSION_CODES.M);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        } else {
            return false;
        }
    }

    //사용자로부터 권한 수락을 요청하는 메서드
    public boolean checkGranted() {
        //이미 해당 유저가 권한을 수락했는지 여부 확인
        int read_permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int write_permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //읽기 권한을 수락했다면
        boolean result1 = read_permission == PackageManager.PERMISSION_GRANTED;

        //쓰기 권한을 수락했다면
        boolean result2 = write_permission == PackageManager.PERMISSION_GRANTED;

        return result1 && result2;
    }

    /*
        안드로이드의 저장소(stoage)는 크게 2가지 유형으로 나뉨
        1) 내부저장소 - 외부저장소 중, 해당 앱만이 전용으로 사용하는 저장소를 가리켜 내부저장소라 함
                            해당 앱이 폰에서 설치 삭제되면, 저장소도 함께 삭제되어 짐

        2) 외부저장소 -
    */
    public void openInternal() {
        //내부저장소 root (경로반환)
        File file = new File(this.getFilesDir(), "");
        Log.d(TAG, file.getAbsolutePath());
    }

    public void openExternal() {
        File storage = Environment.getExternalStorageDirectory();
        Log.d(TAG, "외부저장소 경로 " + storage.getAbsolutePath());

        //외부저장소의 하위 디렉토리 및 모든 파일의 목록을 조회해보자
        File[] files = storage.listFiles();
        Log.d(TAG, "하위디렉토리 및 파일 수는 " + files.length);
        for (File file : files) {
            Log.d(TAG, file.getName());
        }

        //안드로이드의 카메라 앱이 사용중인 DCIM 디렉토리 안의 Camera 디렉토리 안의 첫번째 이미지 접근해보기
        File dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File[] dcimSub = dcim.listFiles();  //DCIM 디렉토리의 하위 디렉토리 반환

        Log.d(TAG, "DCIM 디렉토리의 하위 디렉토리 수는 " + dcimSub.length);

        for (File sub : dcimSub) {
            Log.d(TAG, "DCIM 디렉토리의 하위 디렉토리명은 " + sub.getName());

            if (sub.getName().equals("Camera")) {
                //이 안에 들어있는 사진중 0번째 사진을 강제 선택하여 미리보기 해줌
                File[] pics = sub.listFiles();  //camera 디렉토리의 모든 파일을 배열로 반환
                Log.d(TAG, "사진 수는 "+pics.length);
                selectedFile = pics[0];
                photoView.createBitmap();
                photoView.invalidate();  //onDraw() 호출  다시 그려라
                //preview();
            }


        }


    }
}