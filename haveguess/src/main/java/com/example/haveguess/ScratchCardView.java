package com.example.haveguess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class ScratchCardView extends View {
    private Paint mTransparentPaint;//透明的画笔
    private Path mFingerPath;//手指的路径
    private Bitmap mBgBitmap;//第一张图的 bitmap
    private Bitmap mFgBitmap;//第二章图的 bitmap
    private Canvas mCanvas1;//新建的画布，用于操作 mFgBitmap
    private static int mScreenWidth;
    private static int mScreenHeight;

    public ScratchCardView(Context context) {
        super(context);
        init();
    }

    public ScratchCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScratchCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ScratchCardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        mTransparentPaint = new Paint();//设置透明画笔
        mTransparentPaint.setAntiAlias(true);
        mTransparentPaint.setAlpha(0);//设置成透明
        mTransparentPaint.setStyle(Paint.Style.STROKE);
        mTransparentPaint.setStrokeJoin(Paint.Join.ROUND);//让笔触和连接处更加圆滑
        mTransparentPaint.setStrokeCap(Paint.Cap.ROUND);
        mTransparentPaint.setStrokeWidth(50);
        mTransparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        mFingerPath = new Path();//记录手指滑过的路径

        Bitmap bmp = ((BitmapDrawable)getResources().getDrawable(R.drawable.mv)).getBitmap();
        mBgBitmap = Bitmap.createScaledBitmap(bmp, mScreenWidth, 1080, true);
        mFgBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(), mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);//第二章图
        mCanvas1 = new Canvas(mFgBitmap);//mCanvas1 画布进行的一些列操作都会作用到 mFgBitmap
        mCanvas1.drawColor(Color.GRAY);//例如这一步，绘制灰色，会使 mFgBitmap 变成一张全灰色的 bitmap

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBgBitmap, 0,0, null);
        canvas.drawBitmap(mFgBitmap, 0,0, null);

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFingerPath.reset();//因为之前的效果已经作用在 fgBitmap 上了，所以需要重置 path ，避免对下一步造成影响
                mFingerPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mFingerPath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        mCanvas1.drawPath(mFingerPath, mTransparentPaint);//使透明的效果作用到 fgBitmap 上。
        invalidate();//执行重绘
        return true;
    }
}

