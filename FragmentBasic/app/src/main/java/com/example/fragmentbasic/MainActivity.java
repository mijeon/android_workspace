package com.example.fragmentbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    Fragment[] fragments=new Fragment[3];  //공간이 만들어짐
    public static final int HOMEPAGE=0;
    public static final int BOARDPAGE=1;
    public static final int BLOGPAGE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FrameLayout frameLayout=findViewById(R.id.fragment_container);

        Button bt_home=findViewById(R.id.bt_home);
        Button bt_board=findViewById(R.id.bt_board);
        Button bt_blog=findViewById(R.id.bt_blog);

        bt_home.setOnClickListener((v)->{
            showPage(HOMEPAGE);
        });
        bt_board.setOnClickListener((v)->{
            showPage(BOARDPAGE);
        });
        bt_blog.setOnClickListener((v)->{
            showPage(BLOGPAGE);
        });

        //3개의 페이지 생성하기
        fragments[0]=new HomeFragment();
        fragments[1]=new BoardFragment();
        fragments[2]=new BlogFragment();


    }
    //페이지 전환하기
    public void showPage(int pageNum){
        //프레그먼트를 제어하기 위해서는 프레그먼트 매니저를 이용해야 함
        FragmentManager fragmentManager=this.getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragments[pageNum]);
        transaction.commit();
    }

}