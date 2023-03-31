package com.example.mapandboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_map=findViewById(R.id.bt_map);
        Button bt_blog=findViewById(R.id.bt_blog);
        Button bt_board=findViewById(R.id.bt_board);
        Button bt_shop=findViewById(R.id.bt_shop);

        bt_map.setOnClickListener((v)->{
            openMap();
        });

        bt_blog.setOnClickListener((v)->{

        });

        bt_board.setOnClickListener((v)->{

        });

        bt_shop.setOnClickListener((v)->{

        });
    }

    public void openMap(){
        //액티비티는 시스템이 관리하므로 액티비티를 화면에서 교체하려면, 개발자는 시스템에 부탁을 해야하는데
        //이때 어떤 의도를 갖는지에 대한 정보객체인 Intent를 이용해야 함 (의도 + 정보보관)
        Intent intent=new Intent(this, MymapActivity.class);  //(어디에서, 어디로)
        startActivity(intent);  //다른 액티비티 호출
    }
}