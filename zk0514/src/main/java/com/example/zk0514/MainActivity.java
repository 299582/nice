package com.example.zk0514;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTop_Left,mTop_Right;
    private TextView mTop_Tv;
    private String s;
    private int news;
    private MyViewGroup myViewGroup;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_title_bar);

        //初始化控件 id
        initId();

        //监听控件
        Listener();
    }

    /**
     *  初始化控件id
     **/
    private void initId() {
        mTop_Tv=findViewById(R.id.top_title_tv);
        mTop_Left=findViewById(R.id.top_left_iv);
        mTop_Right=findViewById(R.id.top_right_iv);
        myViewGroup = findViewById(R.id.myviewgroup);

    }

    private void Listener() {

        mTop_Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = mTop_Tv.getText().toString();
                int i = Integer.parseInt(s);
                news = i + 1;
                mTop_Tv.setText(news + "");
                textView = new TextView(MainActivity.this);
                textView.setText(news+"条数据");
                myViewGroup.addView(textView);
            }
        });

        mTop_Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = mTop_Tv.getText().toString();
                int i = Integer.parseInt(s);
                if (i > 0) {
                    news = i - 1;
                    mTop_Tv.setText(news + "");

                        myViewGroup.removeViewAt(news);
                        //myViewGroup.onViewRemoved(textView);

                } else {
                    Toast.makeText(MainActivity.this, "数据已经为0了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
