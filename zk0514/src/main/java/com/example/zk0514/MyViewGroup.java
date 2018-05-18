package com.example.zk0514;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {

    private int width;
    private int height;
    private View v;

    private boolean istype = true;

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //测量子View的宽高，只有ViewFroup中有这个方法，直接继承自View是没有这个方法的
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        width = this.getMeasuredWidth();
        height = this.getMeasuredHeight();

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        //拿到子控件的个数
        int childCount = getChildCount();
        //定义一个临时高度
        int left=0;
        int top=0;
        //循环遍历出每一个View
        for(int a=0;a<childCount;a++){
            v = getChildAt(a);
            //给每一个view设置自己的位置  左  上  右  下

            if(left+v.getMeasuredWidth()>width){
                left = left-v.getMeasuredWidth();
                v.layout(left,top,left+v.getMeasuredWidth(),top+v.getMeasuredHeight());
                top += v.getMeasuredHeight();
                istype = false;
            }else if(left<=0){
                v.layout(left,top,left+v.getMeasuredWidth(),top+v.getMeasuredHeight());
                left+=v.getMeasuredWidth();
                top+=v.getMeasuredHeight();
                istype = true;
            }else{
                if(istype){
                    v.layout(left,top,left+v.getMeasuredWidth(),top+v.getMeasuredHeight());
                    left+=v.getMeasuredWidth();
                    top+=v.getMeasuredHeight();
                }else{
                    left = left-v.getMeasuredWidth();
                    v.layout(left,top,left+v.getMeasuredWidth(),top+v.getMeasuredHeight());
                    top += v.getMeasuredHeight();
                }
            }

        }

        //Log.d("ttt","startHeight"+startHeight+"       startWidth"+startWidth+"   i4"+i4+"    i5"+i5);
        //Log.d("hhh","width:"+width+"    height"+height);
    }
}