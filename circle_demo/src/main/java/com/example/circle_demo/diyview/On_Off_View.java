package com.example.circle_demo.diyview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.circle_demo.R;

public class On_Off_View extends View implements View.OnClickListener,View.OnTouchListener{

    private Bitmap slide_button;
    private Bitmap switch_background;
    private Paint paint;
    private Canvas canvas_but;
    private boolean isType = false;
    private boolean isopen = false;
    private int width_back;
    private int width_but;
    private int ramge = 0;
    int first;
    int last;
    private int x;
    private int newx;
    private int max;
    private int downx;


    public On_Off_View(Context context) {
        this(context,null);
    }

    public On_Off_View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public On_Off_View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        initView();
    }

    private void initView() {
        //将两张图片转化成BitMap类型
        slide_button = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
        switch_background = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        //创建画笔
        paint = new Paint();
        width_back = switch_background.getWidth();
        width_but = slide_button.getWidth();
        max = width_back-width_but;
        setOnClickListener(this);
        setOnTouchListener(this);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置控件宽高(设置为背景图的宽高)
        setMeasuredDimension(switch_background.getWidth(),switch_background.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(switch_background,0,0,paint);
        canvas.drawBitmap(slide_button,ramge,0,paint);

    }

    @Override
    public void onClick(View v) {

            //判断用户滑到开还是关，开的x坐标为0，关就然图片移到右边
            if(isopen){
                ramge = max;

            }else{
                ramge = 0;
            }

            isopen = !isopen;


        invalidate();


    }






    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                first = last = downx = (int) event.getX();
               // isType = false;
                break;
            case MotionEvent.ACTION_MOVE:
                newx = (int) event.getX();
                int setoff = newx - last;
                ramge = setoff+ramge;
                last = newx;

                break;
            case MotionEvent.ACTION_UP:
                int abs = ramge + width_but/2;
                if(abs <= width_back/2){
                    ramge = 0;
                }else if(abs > width_back/2){
                    ramge = max;
                }

                if(Math.abs(first - event.getX())>3){
                    if(abs <= width_back/2){
                        ramge = 0;
                    }else if(abs > width_back/2){
                        ramge = max;
                    }
                    invalidate();
                    return true;
                }

                break;
        }

        againplay();
        return false;
    }
    private void againplay() {
        if(ramge<0){
            ramge = 0;
        }else if(ramge>max){
            ramge = max;
        }
        invalidate();
    }
}
