package com.example.zk0514;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class TitleBar extends RelativeLayout {


    private Context mContext;
    private Button mTop_Left,mTop_Right;
    private TextView mTop_Tv;
    private String s;
    private int news;


    public TitleBar(Context context) {
        this(context,null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_bar, this, true);
        mTop_Tv=findViewById(R.id.top_title_tv);
        mTop_Left=findViewById(R.id.top_left_iv);
        mTop_Right=findViewById(R.id.top_right_iv);

    }




        public void setTitleClickListener(OnClickListener onClickListener) {
            if (onClickListener != null) {
                mTop_Left.setOnClickListener(onClickListener);
                mTop_Right.setOnClickListener(onClickListener);
            }
        }

        public Button getTitleBarLeftBtn() {
            return mTop_Left;
        }

        public Button getTitleBarRightBtn() {
            return mTop_Right;
        }

        public TextView getTitleBarTitle() {
            return mTop_Tv;
        }







}
