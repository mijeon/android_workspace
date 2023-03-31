package com.example.goolglemapapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;  //구글맵 전용 프레그먼트

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //googleMap 객체를 이용해 아이콘, 그림, 메세지...
        //위도, 경도 설정
        MarkerOptions options=new MarkerOptions();
        options.position(new LatLng(37.55555, 126.888889));  //(위도, 경도)
        options.title("여기가 맛집");
        googleMap.addMarker(options);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //구글맵을 보여주기 위해서는 그냥 Fragement가 아닌 MapFragment를 사용함
        mapFragment=new SupportMapFragment();

        //액티비티가 프레그먼트를 제어하려면, 프레그먼트 매니저를 사용해야 함
        //매니저의 주역활 - 페이지에 대한 트랜잭션 처리
        FragmentManager fragmentManager=getSupportFragmentManager();

        //트랜잭션 시작
        mapFragment.getMapAsync(this);
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.Fragment_container, mapFragment);
        transaction.commit();


    }
}