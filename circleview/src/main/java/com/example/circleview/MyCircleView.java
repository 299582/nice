package com.example.circleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyCircleView extends View {

    private Paint paint;
    private int cw;
    private int ch;
    private int radius = 50;
    private int downx;
    private int downy;
    private boolean isdown;

    public MyCircleView(Context context) {
        this(context,null);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview();
    }

    private void initview() {
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        cw = this.getWidth() / 2;
        ch = this.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cw,ch,radius,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downx = (int) event.getX();
                downy = (int)event.getY();
                isdown = isdown(downx, downy);

                break;
            case MotionEvent.ACTION_MOVE:

                if(isdown){
                    cw = (int)event.getX();
                    ch = (int)event.getY();
                    postInvalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private boolean isdown(int downx, int downy) {
        double sqrt = Math.sqrt((downx - cw) * (downx - cw) + (downy - ch) * (downy - ch));
        if(sqrt<radius){
            return true;
        }
        return false;
    }
}
