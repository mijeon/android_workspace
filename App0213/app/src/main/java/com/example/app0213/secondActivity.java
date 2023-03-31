package com.example.app0213;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {
    EditText t_input;
    LinearLayout box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button bt_regist=findViewById(R.id.bt_regist);
        t_input=findViewById(R.id.t_input);
        box=findViewById(R.id.box);  //동적으로 뷰를 붙일 뷰그룹

        //버튼과 리스너 연결
        bt_regist.setOnClickListener((v)->{
            regist();
        });
    }

    public void regist(){
        //입력한 제목을 반영하여 갤러리 복합형 구조의 뷰를 동적으로 등록하자
        
        //모든 뷰는 해당 컨트롤러에 소속관계가 있어야 하므로 생성 시
        //생성자의 인수로 어느 액티비티 소속인지를 지정해야 함
        //GalleryItem wrapper=new GalleryItem(this, t_input.getText().toString());
        GalleryItem2 wrapper=new GalleryItem2(this, t_input.getText().toString());
        box.addView(wrapper.layout);
    }
}