package com.example.fragmentbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class PagerActivit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        //페이지뷰와 어댑터 연결
        ViewPager2 viewPager2=findViewById(R.id.viewPager2);
        MyPageAdapter adapter=new MyPageAdapter(this);
        viewPager2.setAdapter(adapter);
    }
}