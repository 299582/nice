package com.example.pictureview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyPictureView extends View {

    private Paint paint;

    private Bitmap bitmap_wnm;
    private int pw;
    private int ph;
    private int width;
    private int height;
    private boolean ismove;
    private int anInt;
    private int anInt1;
    private int downx;
    private int downy;


    public MyPictureView(Context context) {
        this(context,null);
    }

    public MyPictureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyPictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint = new Paint();
        bitmap_wnm = BitmapFactory.decodeResource(getResources(), R.drawable.wnm);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        pw = (bitmap_wnm.getWidth()) / 2;
        ph = (bitmap_wnm.getHeight()) / 2;

        height = this.getMeasuredHeight()/2;
        width = this.getMeasuredWidth()/2;

        anInt = width - pw;
        anInt1 = height - ph;

        //setMeasuredDimension(bitmap_wnm.getWidth(),bitmap_wnm.getHeight());

    }

    //430 610
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("ppppp","pw"+pw+"      ph"+ph);
        Log.d("ppppp","width"+width+"      height"+height);

        canvas.drawBitmap(bitmap_wnm,anInt,anInt1,paint);
        //canvas.translate(pw,ph);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downx = (int) event.getX();
                downy = (int) event.getY();
                if(downx<pw+width && downx>anInt){
                    if(downy<ph+height && downy>anInt1){
                        ismove = true;
                    }

                }
                Log.d("pppp","按下的x轴"+downx+"   按下的y轴"+downy);
                Log.d("pppp","控件最大x轴大小"+(pw+width)+"   控件最小x轴大小"+anInt);
                Log.d("pppp","控件最大y轴大小"+(ph+height)+"   控件最小y轴大小"+anInt1);
                break;
            case MotionEvent.ACTION_MOVE:
                if(ismove){
                    anInt = (int) event.getX();
                    anInt1 = (int) event.getY();

                    postInvalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
                ismove = false;
                break;
        }

        return true;
    }
}
