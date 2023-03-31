package com.example.app0209;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //이 액티비티가 사용할 디자인 레이아웃 지정
        setContentView(R.layout.profile_layout);

        //모든 xml에 명시된 뷰들이 메모리에 생성된 이후 이므로 js처럼 id를 통해 레퍼런스를 얻어옴
        //참고로 xml로부터 자바의 객체들이 메모리에 올라오는 현상을 가리켜 안드로이드에서는
        //inflation 이라고 함
        TextView introduce=(TextView)findViewById(R.id.introduce);
        String data="Where does it come from?\n" +
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.";
        introduce.setText(data);
    }
}