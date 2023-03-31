package com.example.app0213;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GalleryItem extends RelativeLayout {
    public GalleryItem(Context context, String title) {  //alt+insert
        super(context);

        ImageView imageView=new ImageView(context);

        //비트맵 얻기
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(), R.drawable.hearts);
        imageView.setImageBitmap(bitmap);

        //제목, 작성자, 작성일을 포함할 그룹뷰
        LinearLayout linearLayout=new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView t_title=new TextView(context);  //제목
        TextView t_writer=new TextView(context);  //작성자
        TextView t_regdate=new TextView(context);  //작성일

        t_title.setText(title);
        t_writer.setText("zino");
        t_regdate.setText("2023-02-13");

        //조립
        linearLayout.addView(t_title);
        linearLayout.addView(t_writer);
        linearLayout.addView(t_regdate);

        this.addView(imageView);
        this.addView(linearLayout);


    }
}
