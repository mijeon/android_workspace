package com.example.app0215;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Socket socket; //java SE 의 바로 그 소켓
    EditText t_ip, t_input;
    TextView t_view;

    /*자바에서는 메인쓰레드를 대기상태에 두거나 루프로 놓아도 에러사항까지는
     * 아니고, 프로그램이 동작이 멈추는 수준이지만, 안드로이드, 아이폰의 앱분야에서는
     * 메인쓰레드를 이용하여 네트워크작업, 대기, 루프등에 넣는것 자체가 심각한 에러
     * 로 간주됨.... */
    Thread thread;
    ChatThread chatThread; //채팅용 쓰레드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        t_ip=findViewById(R.id.t_ip);
        t_input=findViewById(R.id.t_input);
        t_view=findViewById(R.id.t_view);
        Button bt_connect=findViewById(R.id.bt_connect);
        Button bt_send=findViewById(R.id.bt_send);

        bt_connect.setOnClickListener((v)->{
            connect();
        });

        bt_send.setOnClickListener((v)->{
            //입력한 메시지를 서버에 전송
            String msg = t_input.getText().toString();
            chatThread.send(msg);
        });
    }
    public void connect(){
        String ip = t_ip.getText().toString();
        int port=8000;

        thread = new Thread(){
            @Override
            public void run() {
                try {
                    socket = new Socket(ip, port); //접속시도
                    chatThread = new ChatThread(socket, MainActivity.this );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();

    }
}