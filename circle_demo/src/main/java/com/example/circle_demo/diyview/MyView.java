package com.example.circle_demo.diyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    private Paint paint;

    //创建对象时调用此方法
    public MyView(Context context) {
        this(context,null);
    }

    //创建布局时调用此方法
    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    //创建布局且在布局里调用其样式调用此方法
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        init();
    }

    private void init() {
        //创建画笔
        paint = new Paint();
    }

    //设置控件宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置控件的宽高
        setMeasuredDimension(500,500);

    }


    //布局(不常用)
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    //绘图
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画板颜色
        canvas.drawColor(Color.GREEN);
        //设置画笔颜色
        paint.setColor(Color.RED);
        //设置抗锯齿
        paint.setAntiAlias(true);
        //开始画圆
        canvas.drawCircle(100,100,80,paint);
    }
}
