package com.example.fragmentbasic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*액티비티가 화면전체를 관리한다면, 프레그먼트는 액티비티의 화면 일부를 관리하는 목적으로 지원되는 객체임*/
public class BoardFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //프레그먼트가 사용하는 xml 레이아웃 파일 인플레이션 시키기
        //false의 의미 : 인플레이션 후 생성된 뷰를 어디에도 부착하지 않음
        View view=inflater.inflate(R.layout.board_fragment, container, false);
        
        return view;
    }
}
