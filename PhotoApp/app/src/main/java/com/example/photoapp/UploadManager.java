package com.example.photoapp;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UploadManager {
    //Http 통신을 위한 객체
    HttpURLConnection con;
    String host="http://172.30.1.47:7777/admin/rest/product";
    String hypen="--";
    String boundary="**********";  //하이픈으로 감쌀 데이터의 경계기준 문자열
    String line="\r\n";  //커서 맨앞+줄바꿈
    File file;  //유저가 전송을 위해 선택한 파일

    //text뿐만 아니라 바이너리 파일도 함께 Http방식으로 전송해야 하므로 multipart/form-data로 전송해야 함
    public void regist(Product product, File file) throws MalformedURLException, IOException {
        this.file=file;

        URL url=new URL(host);
        con=(HttpURLConnection)url.openConnection();

        //웹 전송을 위한 머리와 몸을 구성함
        //머리(header) 구성하기
        con.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8;boundary="+boundary);
        con.setRequestMethod("POST");
        con.setDoOutput(true);  //서버에 보낼 경우
        con.setDoInput(true);  //서버에서 가져올 경우
        con.setUseCaches(false);
        con.setConnectTimeout(2500);

        //body 구성하기 (스트림으로 처리)
        DataOutputStream ds=new DataOutputStream(con.getOutputStream());

        //텍스트 파라미터의 시작을 알리는 구분자선언
        ds.writeBytes(hypen+boundary+line);  //시작할 경우
        //바디를 구성하는 요소들간에는 줄바꿈으로 구분함
        ds.writeBytes("Content-Disposition:form-data;name=\"category.category_idx\""+line);
        ds.writeBytes("Content-type:text/plaint;charset=utf-8"+line);
        ds.writeBytes(line);  //값 지정 직후에는 라인으로 또 구분함
        ds.writeBytes(product.getCategory_idx()+line);

        //텍스트 파라미터의 시작을 알리는 구분자선언
        ds.writeBytes(hypen+boundary+line);  //시작할 경우
        //바디를 구성하는 요소들간에는 줄바꿈으로 구분함
        ds.writeBytes("Content-Disposition:form-data;name=\"product_name\""+line);
        ds.writeBytes("Content-type:text/plaint;charset=utf-8"+line);
        ds.writeBytes(line);  //값 지정 직후에는 라인으로 또 구분함
        ds.writeBytes(product.getProduct_name()+line);

        //텍스트 파라미터의 시작을 알리는 구분자선언
        ds.writeBytes(hypen+boundary+line);  //시작할 경우
        //바디를 구성하는 요소들간에는 줄바꿈으로 구분함
        ds.writeBytes("Content-Disposition:form-data;name=\"brand\""+line);
        ds.writeBytes("Content-type:text/plaint;charset=utf-8"+line);
        ds.writeBytes(line);  //값 지정 직후에는 라인으로 또 구분함
        ds.writeBytes(product.getBrand()+line);

        //텍스트 파라미터의 시작을 알리는 구분자선언
        ds.writeBytes(hypen+boundary+line);  //시작할 경우
        //바디를 구성하는 요소들간에는 줄바꿈으로 구분함
        ds.writeBytes("Content-Disposition:form-data;name=\"price\""+line);
        ds.writeBytes("Content-type:text/plaint;charset=utf-8"+line);
        ds.writeBytes(line);  //값 지정 직후에는 라인으로 또 구분함
        ds.writeBytes(product.getPrice()+line);

        //텍스트 파라미터의 시작을 알리는 구분자선언
        ds.writeBytes(hypen+boundary+line);  //시작할 경우
        //바디를 구성하는 요소들간에는 줄바꿈으로 구분함
        ds.writeBytes("Content-Disposition:form-data;name=\"discount\""+line);
        ds.writeBytes("Content-type:text/plaint;charset=utf-8"+line);
        ds.writeBytes(line);  //값 지정 직후에는 라인으로 또 구분함
        ds.writeBytes(product.getDiscount()+line);

        //텍스트 파라미터의 시작을 알리는 구분자선언
        ds.writeBytes(hypen+boundary+line);  //시작할 경우
        //바디를 구성하는 요소들간에는 줄바꿈으로 구분함
        ds.writeBytes("Content-Disposition:form-data;name=\"detail\""+line);
        ds.writeBytes("Content-type:text/plaint;charset=utf-8"+line);
        ds.writeBytes(line);  //값 지정 직후에는 라인으로 또 구분함
        ds.writeBytes(product.getDetail()+line);

        //파일 파라미터 처리
        ds.writeBytes(hypen+boundary+line);  //시작할 경우
        ds.writeBytes("Content-Disposition:form-data;name=\"photo\";filename=\""+file.getName()+"\""+line);
        ds.writeBytes("Content-Type:image/jpg"+line);  //파일의 종류, 형식
        ds.writeBytes(line);  //값 지정 직후에는 라인으로 또 구분함

        //파일쪼개서 전송 (하나씩 읽어드림)
        FileInputStream fis=new FileInputStream(file);
        byte[] buff=new byte[1024];

        int data=-1;
        while(true) {
            data=fis.read(buff);
            if(data==-1)break;  //끝
            ds.write(buff);
        }

        //전송
        ds.writeBytes(line);
        ds.writeBytes(hypen+boundary+hypen+line);  //끝맺을 경우
        ds.flush();  //출력스트림에 버퍼처리된 출력스트림의 경우 flush()가 사용됨
        fis.close();
        ds.close();

        //웹서버로부터 받은 http 상태코드로 성공여부를 따져보자
        int status=con.getResponseCode();  //http code
        if(status==HttpURLConnection.HTTP_OK) {
            System.out.println("성공");
        }else {
            System.out.println("실패");
        }
    }

}
