package com.example.app0209;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //xml 읽기
        setContentView(R.layout.grid_view);

        GridView gridView=(GridView)this.findViewById(R.id.gridView);
        ArrayList<String> list=new ArrayList<String>();
        list.add("포카칩");
        list.add("꼬북칩");
        list.add("포테이토칩");
        list.add("오징어집");

        ArrayAdapter<String> adapter=null;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);

        gridView.setAdapter(adapter);
    }
}