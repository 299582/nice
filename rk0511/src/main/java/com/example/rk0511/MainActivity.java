package com.example.rk0511;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private int screen_W;        //屏幕宽度
    private int screen_H;        //屏幕高度
    private Display display;
    private Paint paint;        //定义画笔
    private float cx = 50;      //圆点默认X坐标
    private float cy = 50;      //圆点默认Y坐标
    private int radius = 100;   //设置圆的大小
    private Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = this.getWindowManager().getDefaultDisplay();
        //设置全屏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //获取屏幕宽和高
        screen_W = display.getWidth();
        screen_H = display.getHeight();


        //再想想，我们是通过什么方法将这个布局填充到 Activity 上的呢？
        //没错是 setContentView
        setContentView(new BallView(this));



    }

    class BallView extends View{

        public BallView(Context context) {
            super(context);
            paint = new Paint();
            //设置消除锯齿
            paint.setAntiAlias(true);
            //设置画笔颜色
            paint.setColor(Color.RED);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //将屏幕设置为白色
            canvas.drawColor(Color.WHITE);
            //修正圆点坐标
            revise();
            //绘制小圆作为小球
            canvas.drawCircle(cx, cy, radius, paint);

        }

        //修正圆点坐标
        private void revise(){
            if(cx <= radius){
                cx = radius;
            }else if(cx >= (screen_W-radius)){
                cx = screen_W-radius;
            }
            if(cy <= radius){
                cy = radius;
            }else if(cy >= (screen_H-radius)){
                cy = screen_H-radius;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // 按下
                    cx = (int) event.getX();
                    cy = (int) event.getY();
                    // 通知重绘
                    postInvalidate();   //该方法会调用onDraw方法，重新绘图
                    break;
                case MotionEvent.ACTION_MOVE:
                    // 移动
                    cx = (int) event.getX();
                    cy = (int) event.getY();
                    // 通知重绘
                    postInvalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    // 抬起
                    cx = (int) event.getX();
                    cy = (int) event.getY();
                    // 通知重绘
                    postInvalidate();
                    break;
            }

            /*
             * 备注1：此处一定要将return super.onTouchEvent(event)修改为return true，原因是：
             * 1）父类的onTouchEvent(event)方法可能没有做任何处理，但是返回了false。
             * 2)一旦返回false，在该方法中再也不会收到MotionEvent.ACTION_MOVE及MotionEvent.ACTION_UP事件。
             */
            //return super.onTouchEvent(event);
            return true;
        }
    }
}
