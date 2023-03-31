package com.example.app0213;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String TAG=this.getClass().getName();
    EditText t_input;
    List nationList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //xml을 반드시 써야하는것은 아니지만, 스윙처럼 직접 자바코드만으로 디자인을 할 경우 효율성이 떨어짐
        //따라서 xml 사용빈도가 높음
        setContentView(R.layout.activity_main);
        //xml로부터 view 객체들이 태어나는 과정을 imflation이라 함
        //setContentView 메서드 호출 이후부터는 id만 알면 인스턴스를 접근할 수 있음
        //이때 id를 통해 접근하는 메서드가 fineViewById (getElementById 와 흡사함)
        Button bt_regist=(Button) findViewById(R.id.bt_regist);
        t_input=(EditText) findViewById(R.id.t_input);

        //xml 문서에 있는 listView를 제어하기 위해 id를 이용하여 레퍼런스 얻기
        ListView listView=(ListView) this.findViewById(R.id.listView);
        
        //List, GridView 일명 어댑터를 이용한다 하여 어댑터뷰라 함
        //주로 목록을 처리하는데 압도적으로 많이 사용됨
        //Swing에서의 JTable이 TableModel을 이용해 데이터를 연동하는 것과 동일
        //JTable - ListView, GridView 등의 어댑터뷰
        //TableModel - Adapter
        nationList=new ArrayList();
        nationList.add("한국");
        nationList.add("베트남");
        nationList.add("태국");
        nationList.add("대만");
        nationList.add("터키");
        
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nationList);
        listView.setAdapter(adapter);  //연결대상 어댑터

        //버튼과 리스너 연결
        bt_regist.setOnClickListener((v)->{
            Log.d(TAG, "눌렀어");
            regist();
        });
    }
    public void regist(){
        //입력창에 입략한 값을 리스트에 추가
        String value=t_input.getText().toString();
        nationList.add(value);

        //어댑터 새로고침
        adapter.notifyDataSetChanged();
    }
}