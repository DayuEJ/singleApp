package com.dayu.singapp.view.learn;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dayu.singapp.R;

/**
 * Created by Ljy on 2018/2/24.
 * ImageView + TextView
 */

public class CustomImageView extends View {
    private String mText;
    private int mTextSize;
    private int mTextColor;
    private Rect mRect;
    private Rect mTextBound;
    private Paint mPaint;
    private Bitmap mImage;


    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, 0, 0);
        mText = array.getString(R.styleable.CustomImageView_titleText);
        mTextColor = array.getColor(R.styleable.CustomImageView_titleTextColor,  0xFFFFFFFF);
        mTextSize = array.getDimensionPixelSize(R.styleable.CustomImageView_titleTextSize, 0);
        mImage = BitmapFactory.decodeResource(getResources(), array.getResourceId(R.styleable.CustomImageView_image, 0));
        array.recycle();
        mRect = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //confirm width
        if (widthMode == MeasureSpec.EXACTLY){
            //MeasureSpec.EXACTLY 表示MATCH_PARENT
            width = widthSize;
        }else {
            //MeasureSpec.AT_MOST 表示为WRAP_CONTENT
            //UNSPECIFIED 表示子布局想要多大就多大 一般很少使用
            //由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
            int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width();
            int desire = Math.max(desireByImg, desireByTitle);
            width = Math.min(desire, widthSize);
        }

        //confirm height
        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else {
            int heightByImg = getPaddingTop() + getPaddingBottom() + mImage.getHeight();
            int heightByText = getPaddingBottom() +getPaddingTop() + mTextBound.height();
            int heightByTemp = Math.max(heightByImg, heightByText);
            height = Math.min(heightByTemp, heightSize);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(4);//设置线宽
        mPaint.setStyle(Paint.Style.STROKE);
    }


//    @Override
//    protected void onDraw(Canvas canvas)
//    {
//        // super.onDraw(canvas);
//        /**
//         * 边框
//         */
//        mPaint.setStrokeWidth(4);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.CYAN);
//        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
//
//        rect.left = getPaddingLeft();
//        rect.right = mWidth - getPaddingRight();
//        rect.top = getPaddingTop();
//        rect.bottom = mHeight - getPaddingBottom();
//
//        mPaint.setColor(mTextColor);
//        mPaint.setStyle(Style.FILL);
//        /**
//         * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
//         */
//        if (mTextBound.width() > mWidth)
//        {
//            TextPaint paint = new TextPaint(mPaint);
//            String msg = TextUtils.ellipsize(mTitle, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
//                    TextUtils.TruncateAt.END).toString();
//            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
//
//        } else
//        {
//            //正常情况，将字体居中
//            canvas.drawText(mTitle, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
//        }
//
//        //取消使用掉的快
//        rect.bottom -= mTextBound.height();
//
//        if (mImageScale == IMAGE_SCALE_FITXY)
//        {
//            canvas.drawBitmap(mImage, null, rect, mPaint);
//        } else
//        {
//            //计算居中的矩形范围
//            rect.left = mWidth / 2 - mImage.getWidth() / 2;
//            rect.right = mWidth / 2 + mImage.getWidth() / 2;
//            rect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;
//            rect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;
//
//            canvas.drawBitmap(mImage, null, rect, mPaint);
//        }
//
//    }
}
