package com.edu.boardclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_board=findViewById(R.id.bt_board);
        Button bt_chat=findViewById(R.id.bt_chat);

        bt_board.setOnClickListener((v)->{
            //게시판을 담당하는 액티비티 띄우기
            //화면을 담당하는 액티비티는 안드로이드에서 주요 컴포넌트중 하나임
            //또한 안드로이드 컴포넌트는 시스템에 의해 관리되므로, 개발자가 직접 new 할 수 없음
            Intent intent=new Intent(MainActivity.this, BoardActivity.class);  //(현재 액티비티, 어떤 액티비티)
            startActivity(intent);
        });
    }
}