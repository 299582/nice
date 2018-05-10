package com.example.mac.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap bitmap;
    private Canvas canvas;

    private Bitmap bitmap_white;
    private int startx;
    private int starty;
    private int newx;
    private int newy;
    private Paint paint;
    private Button but_wide;
    private FileOutputStream fileOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView_canvas);
        but_wide = findViewById(R.id.button_wide);
        //创建原图
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        //创建白纸
        bitmap_white = Bitmap.createBitmap(this.bitmap.getWidth(), this.bitmap.getHeight(), this.bitmap.getConfig());
        //创建画板
        canvas = new Canvas(bitmap_white);
        //canvas.drawColor(R.color.colorAccent,);
        //创建画笔
        paint = new Paint();
        paint.setColor(Color.BLACK);
        //paint.setAntiAlias(true);
        //在画板上作画
        canvas.drawBitmap(bitmap,new Matrix(), paint);
       // canvas.drawColor(getResources().getColor(R.color.colorAccent));
        //在imageview上添加手指指示器(滑动监听);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    //当手指在手机屏幕上按下是触发
                    case MotionEvent.ACTION_DOWN:
                        //得到按下时的起点位置x的坐标和y的坐标
                        startx = (int)event.getX();
                        starty = (int) event.getY();
                        Log.d("zzzz","startx : "+startx);
                        break;
                        //当手指在手机屏幕上滑动时触发（一直在触发）
                    case MotionEvent.ACTION_MOVE:
                        //得到手指滑动时的x和y的坐标
                        newx = (int)event.getX();
                        newy = (int) event.getY();
                        //调用画板的画线的功能五个参数（开始的坐标和移动时的坐标，和一只画笔）；
                        canvas.drawLine(startx,starty,newx,newy,paint);
                        //将上面创建的白纸设置给控件ImageView　
                        imageView.setImageBitmap(bitmap_white);
                        //再将移动时的坐标赋给起点坐标
                        startx = newx;
                        starty = newy;
                        break;

                }

                return true;//默认为false:不消耗次事件，向上传递；
                //改为true：消耗此事件；
            }
        });


    }

    //点击红色按钮画笔画出的线变成红色
    public void red(View view){
        //将画笔设置成红色
        paint.setColor(Color.RED);
    }

    //点击绿色按钮画笔画出的线变成绿色

    public void green(View view){
        //将画笔设置成绿色
        paint.setColor(Color.GREEN);
    }

    //点击刷子按钮画笔画出的线变粗

    public void wide(View view){
        String s = but_wide.getText().toString();
        if(s.equals("刷子")){
            //设置画笔的粗细
            paint.setStrokeWidth(20);
            //设置画笔是否支持抗锯齿
            paint.setAntiAlias(true);
            //使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
            paint.setDither(true);
            but_wide.setText("铅笔");
        }else{
            but_wide.setText("刷子");
            //设置画笔的粗细
            paint.setStrokeWidth(5);
        }

    }

    //点击保存按钮加图片保存在sd卡下

    public void save(View view){
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".jpg");

        try {
            fileOutputStream = new FileOutputStream(file);
            Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
           image.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        try {
//            //fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Toast.makeText(MainActivity.this,"保存已经至"+Environment.getExternalStorageDirectory()+"下", Toast.LENGTH_SHORT).show();

    }

}
