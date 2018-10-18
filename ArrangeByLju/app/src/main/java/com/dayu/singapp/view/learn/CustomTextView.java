package com.dayu.singapp.view.learn;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.dayu.singapp.R;
import com.dayu.singapp.util.DeviceUtil;
import com.dayu.singapp.util.LogUtil;
import com.dayu.singapp.util.ResourceUtil;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Ljy on 2018/2/7.
 */

public class CustomTextView extends View {
    private String mText;
    private int mTextColor;
    private int mTextSize;
    private Paint mPaint;
    private Rect mBound;

    public CustomTextView(Context context) {
        super(context);
        LogUtil.d("custom_text", "constructor one");
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        LogUtil.d("custom_text", "constructor two");
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.d("custom_text", "constructor three");
        //获得自定义属性样式  attribute 属性  dimension  尺寸
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTextView, 0, 0);
        //int attributeCount = array.getIndexCount();  获得属性数目
        mText = array.getString(R.styleable.CustomTextView_custom_text);
        mTextColor = array.getColor(R.styleable.CustomTextView_custom_text_color, 0xFFFFFFFF);
        mTextSize = array.getDimensionPixelSize(R.styleable.CustomTextView_custom_text_size, 0);
        //记得要回收
        array.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);

        mBound = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = getRandomText();
                postInvalidate();
            }
        });
    }

    private String getRandomText(){
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4){
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }

        StringBuffer sb = new StringBuffer();
        for (Integer ii :  set){
            sb.append("" + ii);
        }

        return sb.toString();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.d("custom_text", "onDraw");
        //绘制Rect
        mPaint.setColor(ResourceUtil.getColor(R.color.yellow));
        //left top right bottom
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        //绘制文字
        mPaint.setColor(mTextColor);
        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtil.d("custom_text", "onMeasure" + "width" + getWidth() + "height" +getHeight());
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //MeasureSpec.EXACTLY 一般是设置了明确的值或者MATCH_PARENT
        if (widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else {
            //AT_MOST 表示布局限制在一个最大值内 一般表示WRAP_CONTENT
            //UNSPECIFIED 表示子布局想要多打就多大  一般很少使用
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            width = getPaddingLeft() + getPaddingRight() + mBound.width();
        }

        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            height = getPaddingTop() + getPaddingBottom() + mBound.height();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.d("custom_text", "onLayout");
    }

}
