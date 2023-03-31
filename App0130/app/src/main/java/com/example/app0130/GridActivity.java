package com.example.app0130;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //GridLayout 사용해보기
        GridLayout layout=new GridLayout(this);
        layout.setColumnCount(3);  //호수
        layout.setRowCount(5);  //층수

        for(int i=1;i<=15;i++){
            Button bt=new Button(this);
            layout.addView(bt);  //그리드 레이아웃에 버튼 부착
        }
        //화면에 레이아웃 부착
        setContentView(layout);
    }
}