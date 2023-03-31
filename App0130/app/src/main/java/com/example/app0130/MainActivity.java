package com.example.app0130;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스윙을 포함한 모든 GUI 어플리케이션은 화면에 UI 컴포넌트를 올려놓기 전에 반드시 배치방법을 결정해야 함
        //안드로이드에서 지원하는 배치방법은 다음과 같음
        /*
            LinearLayout : 선형레이아웃(수직, 수평의 방향성이 있는 레이아웃 FlowLayout과 흡사)
                                레이아웃 + 컨테이너를 합쳐놓음
            GridLayout
            RelativeLayout
            TableLayout
            ConstraintLayout
        */

        //리니어 생성
        //div class="container"
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //버튼 생성
        for(int i=0;i<=50;i++) {
            Button bt = new Button(this);
            bt.setText("버튼"+i);
            layout.addView(bt);  //레이아웃에 버튼 추가
        }
        setContentView(layout);  //화면 반영
    }
}