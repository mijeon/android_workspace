package com.example.noticeclient;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RegistActivity extends AppCompatActivity {
    EditText t_title, t_writer, t_content;
    private String TAG=this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        Button bt_regist=findViewById(R.id.bt_regist);
        Button bt_list=findViewById(R.id.bt_list);

        t_title=findViewById(R.id.t_title);
        t_writer=findViewById(R.id.t_writer);
        t_content=findViewById(R.id.t_content);

        bt_regist.setOnClickListener((v)->{
            regist();
        });

        bt_list.setOnClickListener((v)->{
            finish();
        });
    }

    public void regist(){
        Thread thread=new Thread(){
            @Override
            public void run() {
                //HttpRequest
                BufferedWriter buffw=null;
                OutputStreamWriter os=null;
                DataOutputStream dos=null;

                BufferedReader buffr=null;
                InputStreamReader is=null;
                //DataInputStream dis=null;

                try {
                    URL url = new URL("http://172.30.1.47:7777/rest/notice/regist");
                    URLConnection uCon = url.openConnection();
                    HttpURLConnection httpCon = (HttpURLConnection) uCon;
                    httpCon.setRequestMethod("POST");
                    httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpCon.setDoOutput(true);

                    //보낼 데이터
                    String title=t_title.getText().toString();
                    String writer=t_writer.getText().toString();
                    String content=t_content.getText().toString();

                    String postData="title="+title+"&writer="+writer+"&content="+content;
                    os=new OutputStreamWriter(httpCon.getOutputStream(), "UTF-8");
                    buffw=new BufferedWriter(os);
                    //dos=new DataOutputStream(httpCon.getOutputStream());
                    //dos.writeBytes(postData);

                    buffw.write(postData+"\n");
                    buffw.flush();

                    //데이터 요청 이후에 입력 스트림을 만들어야 함
                    is=new InputStreamReader(httpCon.getInputStream(), "UTF-8");
                    buffr=new BufferedReader(is);

                    StringBuilder sb=new StringBuilder();
                    while (true){
                        String result=buffr.readLine();
                        sb.append(result);
                        if(result==null){
                            break;
                        }
                    }
                    Log.d(TAG,sb.toString());

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }finally {
                    if(is!=null){
                        try {
                            is.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(buffr!=null){
                        try {
                            buffr.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(os!=null){
                        try {
                            os.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(buffw!=null){
                        try {
                            buffw.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };
        thread.start();
    }
}