package com.example.photoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//사진을 미리볼 수 있는 그냥 뷰
public class PhotoView extends View {
    Bitmap bitmap;  //사진, 이미지...

    MainActivity mainActivity;

    //이 뷰를 xml에서 사용하려면 생성자의 매개변수에는 반드시 xml의 태그속성을 받을 수 있는 매개변수인 AttributeSet이 존재해야 함
    public PhotoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mainActivity=(MainActivity) context;  //사진이 액티비티에 있으므로
    }

    //사진 넘겨받기
    public void createBitmap(){
        FileInputStream fis=null;
        try {
            //메인 엑티비티가 보유한 자료형은 file형이므로, 여기서 bitmap 형으로 바꿔서 전송
            fis=new FileInputStream(mainActivity.selectedFile);
            bitmap= BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(bitmap!=null) {
            canvas.drawBitmap(bitmap, 0, 0, null);  //그림 준비
        }
    }
}
