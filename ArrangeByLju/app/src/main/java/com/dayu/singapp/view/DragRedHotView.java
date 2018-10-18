package com.dayu.singapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.dayu.singapp.util.LogUtil;

/**
 * Created by Ljy on 2018/8/1.
 */

public class DragRedHotView extends FrameLayout {
    private PointF mStartPoint, mCurPoint;
    private int mRadius = 40;
    private Paint mPaint;
    private Path mPath;
    private boolean mTouch;//手指按下画圆 拖动时不画

    public DragRedHotView(@NonNull Context context) {
        super(context, null);
        //在代码中直接New
        LogUtil.d("ljy", "creator one");
        initView();
    }

    public DragRedHotView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        //xml 直接调用
        LogUtil.d("ljy", "creator two");
        initView();
    }

    public DragRedHotView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        //mStartPoint 表示起始圆心的位置 mCurPoint是当前手指的位置（移动的圆心位置）
        mStartPoint = new PointF(100, 100);
        mCurPoint = new PointF();

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        LogUtil.d("ljy", "initView()");
    }

    private void calculatePath() {
        float x = mCurPoint.x;
        float y = mCurPoint.y;
        float startX = mStartPoint.x;
        float startY = mStartPoint.y;
        float dx = x - startX;
        float dy = y - startY;
        double a = Math.atan(dy / dx);
        float offsetX = (float) (mRadius * Math.sin(a));
        float offsetY = (float) (mRadius * Math.cos(a));

        // 根据角度算出四边形的四个点
        float x1 = startX - offsetX;
        float y1 = startY + offsetY;

        float x2 = x - offsetX;
        float y2 = y + offsetY;

        float x3 = x + offsetX;
        float y3 = y - offsetY;

        float x4 = startX + offsetX;
        float y4 = startY - offsetY;

        float anchorX = (startX + x) / 2;
        float anchorY = (startY + y) / 2;

        mPath.reset();
        mPath.moveTo(x1, y1);
        mPath.quadTo(anchorX, anchorY, x2, y2);
        mPath.lineTo(x3, y3);
        mPath.quadTo(anchorX, anchorY, x4, y4);
        mPath.lineTo(x1, y1);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        LogUtil.d("ljy", "dispatchDraw");
        canvas.saveLayer(new RectF(0 , 0, getWidth(), getHeight()),mPaint,Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(mStartPoint.x, mStartPoint.y, mRadius, mPaint);//圆心 半径 画笔
        if (mTouch){
            calculatePath();
            canvas.drawCircle(mCurPoint.x, mCurPoint.y, mRadius, mPaint);
            canvas.drawPath(mPath, mPaint);
        }
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mTouch = true;
                break;
            case MotionEvent.ACTION_UP:
                mTouch = false;
                break;
        }

        mCurPoint.set(event.getX(), event.getY());
        postInvalidate();
        return true;//return 表示消息到此为止 不会再往父控件传递
    }

}
