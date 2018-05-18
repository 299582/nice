package com.example.diyview_titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyTextBar extends ScrollView {
    public MyTextBar(Context context) {
        this(context,null);
    }

    public MyTextBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        // alpha = 滑出去的高度/(screenHeight/3);
        float heightPixels = getContext().getResources().getDisplayMetrics().heightPixels;
        float scrollY = getScrollY();//该值 大于0
        float alpha = 1-scrollY/(heightPixels/3);// 0~1 透明度是1~0
//这里选择的screenHeight的1/3 是alpha改变的速率 （根据你的需要你可以自己定义）
        if(scrollListener != null){
            scrollListener.setScrollChangeListener(this,l, t, oldl, oldt,alpha);
        }
    }

    //1定义接口
    public interface setScrollListener{
        void setScrollChangeListener(MyTextBar myTextBar,int l, int t, int oldl, int oldt,float alpha);
    }
    //2定义变量
    private setScrollListener scrollListener;

    //3定义公共方法让外界调用

    public void setOnScrollViewListener(setScrollListener scrollListener){

        this.scrollListener = scrollListener;

    }

}
