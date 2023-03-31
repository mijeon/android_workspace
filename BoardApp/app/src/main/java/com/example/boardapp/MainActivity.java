package com.example.boardapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    EditText t_title;
    EditText t_writer;
    EditText t_content;
    Button bt_regist, bt_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t_title=findViewById(R.id.t_title);
        t_writer=findViewById(R.id.t_writer);
        t_content=findViewById(R.id.t_content);
        bt_regist=findViewById(R.id.bt_regist);
        bt_list=findViewById(R.id.bt_list);

        bt_regist.setOnClickListener((v)->{
            Thread thread=new Thread(){
                @Override
                public void run() {
                    request();  //웹서버에 요청하기
                }
            };
            thread.start();  //요청 시작
        });
    }

    public void request(){
        //자바언어에서 웹서버와의 요청 및 응답정보를 받기 위한 전용 객체는 바로
        //HttpURLConnection 객체가 있으며, URLConnection의 자식임
        //요청용
        DataOutputStream dos=null;

        //서버의 응답받기용
        BufferedReader buffr=null;
        InputStreamReader is=null;

        try {
            URL url=new URL("http://172.30.1.47:7777/rest/notice/regist");
            URLConnection uCon=url.openConnection();
            HttpURLConnection httpCon=(HttpURLConnection)uCon;
            httpCon.setRequestMethod("POST");

            //파라미터 만들기
            String postData="title=hi&writer=zino&content=himne";


            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);

            //POST 전송을 위한 form-data setting -> application/x-www-form-urlencoding
            httpCon.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            //출력스트림을 이용해 데이터 전송예정
            dos=new DataOutputStream(httpCon.getOutputStream());
            dos.writeBytes(postData);

            //서버의 응답정보 받기
            is=new InputStreamReader(httpCon.getInputStream());
            buffr=new BufferedReader(is);

            String msg=null;
            while(true) {
                msg=buffr.readLine();
                if(msg==null) break; {
                    System.out.println(msg);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(buffr!=null) {
                try {
                    buffr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(dos!=null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}