package com.dayu.singapp.view.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Ljy on 2017/11/3.
 * 二阶贝塞尔曲线
 */

public class BezierQuadView extends View {
    //定义起始点和结束点
    private int mStartPointX;
    private int mStartPointY;
    private int mEndPointX;
    private int mEndPointY;

    //定义控制点
    private int mConPointX;
    private int mConPointY;

    private Path mPath;
    private Paint mPaint;

    private Paint mLinePaint;
    private Paint mTextPaint;

    public BezierQuadView(Context context) {
        super(context);
        init(context);
    }

    public BezierQuadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BezierQuadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        //获取屏幕的宽高
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        //设置各点的位置
        mStartPointX = screenWidth / 4;
        mStartPointY = screenHeight / 2;
        mEndPointX = screenWidth * 3 / 4;
        mEndPointY = screenHeight / 2;
        mConPointX = screenWidth / 2;
        mConPointY = screenHeight / 2 - 400;

        //路径,画笔设置
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);

        //辅助线画笔
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.GRAY);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(3);

        //写字画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(20);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        //Bezier quadTo() rQuadTo()代表的是绝对坐标和相对坐标
        mPath.moveTo(mStartPointX, mStartPointY);
        mPath.quadTo(mConPointX, mConPointY, mEndPointX, mEndPointY);
        canvas.drawPath(mPath, mPaint);

        //辅助线
        canvas.drawLine(mStartPointX, mStartPointY, mConPointX, mConPointY, mLinePaint);
        canvas.drawLine(mConPointX, mConPointY, mEndPointX, mEndPointY, mLinePaint);

        //文字
        canvas.drawPoint(mStartPointX, mStartPointY, mPaint);
        canvas.drawText("起始点", mStartPointX, mStartPointY + 30, mTextPaint);
        canvas.drawPoint(mEndPointX, mEndPointY, mPaint);
        canvas.drawText("结束点", mEndPointX, mEndPointY + 30, mTextPaint);
        canvas.drawPoint(mConPointX, mConPointY, mPaint);
        canvas.drawText("控制点", mConPointX, mConPointY - 30, mTextPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mConPointX= (int) event.getX();
                mConPointY=(int)event.getY();
                invalidate();// 这里会回调onDraw
                break;
        }
        return true;
    }
}
