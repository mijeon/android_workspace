package com.example.app0209;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView에 의해 xml이 읽혀지고 해석되므로 xml에 명시된 뷰를 얻기 위해서는
        // setContentView() 메서드 이후에 접근해야 함
        setContentView(R.layout.list_view);

        // R은 R.java라는 상수를 모아놓은 클래스임
        // R.java는 프로젝트 구성 디렉토리중 res 디렉토리를 반영한 클래스임
        // 즉 개발자가 res 디렉토리에 xml, 이미지나 음원 등등 리소스를 등록하면
        //개발 환경 자체에서 실시간으로 R.java에 상수로 등록함
        ListView listView=(ListView) this.findViewById(R.id.listView);  //document.getElementById() 흡사

        //ListView에 데이터를 채우기 위해 ArrayList를 사용함
        // ListView, GridView는 일명 어댑터뷰라 불리며 MVC로 분리되어 있DMA
        ArrayList<String> list=new ArrayList<String>();
        list.add("해영");
        list.add("미전");
        list.add("나연");
        list.add("가자미");

        //어댑터 생성
        //어댑터의 생성자 매개변수 중 두번째 매개변수에는 아이템을 담게될 뷰가 올 수 있음
        //특히 이 뷰는 xml 레이아웃 파일로 처리되어야 하며 이 파일은 개발자가 직접 생성해도 되고,
        //이미 안드로이드 자체에서 지원하는 xml 파일을 이용할 수도 있는데 어디까지나 개발자의 선택임
        //참고로 현재 프로젝트의 res와 연계된 자바 클래스는 R.java이고 안드로이드 시스템 자체에서 지원하는
        //리소스와 연계된 자바 클래스는 그냥 R.java(프로젝트 소속)가 아닌 android.R.java(시스템 소속), R.java>android.R.java
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);

        //어댑터 반영하기
        listView.setAdapter(adapter);
    }
}