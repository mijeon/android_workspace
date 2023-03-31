package com.example.fragmentbasic;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*액티비티가 화면전체를 관리한다면, 프레그먼트는 액티비티의 화면 일부를 관리하는 목적으로 지원되는 객체임*/
public class HomeFragment extends Fragment {
   private String TAG=this.getClass().getName();

   //프레그먼트 생명주기
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach() 호출 : 액티비티와 연결될 때 호출");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() 호출 : 프레그먼트가 초기화될 때 호출");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() 호출 : 프레그먼트가 초기화된 후 뷰를 구성할 때 호출");
        //프레그먼트가 사용하는 xml 레이아웃 파일 인플레이션 시키기
        //false의 의미 : 인플레이션 후 생성된 뷰를 어디에도 부착하지 않음
        View view=inflater.inflate(R.layout.home_fragment, container, false);
        
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() 호출 : 프레그먼트와 연결된 엑티비티가 onStart() 되어 프레그먼트가 보여지기 직전에 호출");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() 호출 : 프레그먼트와 연결된 엑티비티가 onResume() 되어 프레그먼트가 완전히 보여질 때 호출, 이때부터 사용자는 UI를 제어할 수 있음");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() 호출 : 프레그먼트와 연결된 액티비티가 onPause() 되었을 때 호출, 이때부터 사용자는 UI 사용불가함");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() 호출 : 프레그먼트와 연결된 액티비티가 onStop() 되어 화면에서 완전히 가려졌을 때 호출, 이때부터 프레그먼트는 기능 중지됨");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView() 호출 : 프레그먼트와 관련된 뷰 리소스 해제 시 호출");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() 호출 : 프레그먼트 종료 직전 호출, 이때부터 개발자는 프레그먼트와 관련된 상태를 마지막으로 정리할 수 있음");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach() 호출 : 프레그먼트가 액티비티와 연결을 끊기 직전 호출");
    }
}
