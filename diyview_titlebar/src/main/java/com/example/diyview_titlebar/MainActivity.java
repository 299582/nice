package com.example.diyview_titlebar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyTextBar textBar;
    private ImageView t1;
    private TextView textView;
    private RelativeLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBar = findViewById(R.id.scrollView);
        t1 = findViewById(R.id.iv_detail);
        layout = findViewById(R.id.layout_title);
        textView = findViewById(R.id.tv_titlebar);


        textBar.setOnScrollViewListener(new MyTextBar.setScrollListener() {
            @Override
            public void setScrollChangeListener(MyTextBar myTextBar, int l, int t, int oldl, int oldt,float alpha) {
                int height = t1.getLayoutParams().height;
                Log.d("hhh","高"+height+"   t"+t);
                if(t>height){
                    textView.setVisibility(View.VISIBLE);
                    textView.setAlpha(1-alpha);
                    //layout.setBackgroundColor(Color.YELLOW);
                }else if(t<height && t>0){

                    textView.setVisibility(View.VISIBLE);
                    textView.setAlpha(1-alpha);
                }else if(t == 0){
                    textView.setVisibility(View.GONE);
                }

            }
        });

    }
}
