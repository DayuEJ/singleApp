package com.dayu.singapp.view;

/**
 * Created by Ljy on 2018/2/5.
 */

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dayu.singapp.R;

public class CenterControlButton extends RelativeLayout{
    private CenterImageView mCenterImage;//f6472a
    private ImageView mIvWeelBack;
    private ImageView mIvLightClose;
    private int mLastX;
    private int mLastY;
    private int mStartX;
    private int mStartY;
    private int mWidth;
    private int mHeight;
    private float mCurDegree;//f6481j;
    private boolean f6482k;
    private C1990a f6483l;
    private FrameLayout mCenterLayout;

    public interface C1990a {
        void mo1335a();
    }

    public CenterControlButton(Context context) {
        super(context);
    }

    public CenterControlButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CenterControlButton(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    public void setCenterButtonClick(C1990a c1990a) {
        this.f6483l = c1990a;
    }

    private void init() {
        mCenterImage = (CenterImageView) findViewById(R.id.center_wheel);
        mIvWeelBack = (ImageView) findViewById(R.id.wheel_deis_back);
        mIvLightClose = (ImageView) findViewById(R.id.light_btn_close);
        mCenterLayout = (FrameLayout) findViewById(R.id.wheel_deis_layout);
        mCurDegree = 0.0f;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mWidth = getMeasuredWidth() / 2;
        this.mHeight = getMeasuredHeight() / 2;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        mStartX = (int) motionEvent.getX();
        mStartY = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                boolean z = false;
                this.mLastX = (int) motionEvent.getX();
                this.mLastY = (int) motionEvent.getY();
                this.mCurDegree = (float) ((int) this.mCenterImage.getRotation());
                if (!isInRange(mStartX, mStartY)) {
                    z = true;
                }
                this.f6482k = z;
                break;
            case MotionEvent.ACTION_UP:
                this.mLastX = 0;
                this.mLastY = 0;
                if (!this.f6482k) {
                    if (this.f6483l != null) {
                        this.f6483l.mo1335a();
                        break;
                    }
                }
                this.mCenterImage.setModulFromDegree(mCurDegree);
                break;

            case MotionEvent.ACTION_MOVE:
                int i2;
                if (mStartY <= this.mHeight || mStartX <= this.mWidth) {
                    i2 = 0;
                } else {
                    i2 = this.mLastX - mStartX;
                    i = mStartY - this.mLastY;
                }
                if (mStartY < this.mHeight && mStartX > this.mWidth) {
                    i2 = mStartX - this.mLastX;
                    i = mStartY - this.mLastY;
                }
                if (mStartY < this.mHeight && mStartX < this.mWidth) {
                    i2 = mStartX - this.mLastX;
                    i = this.mLastY - this.mStartY;
                }
                if (mStartY > mHeight && mStartX < this.mWidth) {
                    i2 = this.mLastX - mStartX;
                    i = this.mLastY - this.mStartY;
                }
                if (i2 * i2 > i * i) {
                    setRotationDegree(((float) i2) * 0.3f);
                } else {
                    setRotationDegree(((float) i) * 0.3f);
                }
                this.mLastX = mStartX;
                this.mLastY = mStartY;
                break;
        }
        return true;
    }

    private boolean isInRange(int x, int y) {
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        getDrawingRect(rect2);
        this.mIvLightClose.getDrawingRect(rect);
        //left top  right bottom
        rect.set((rect2.right - rect.right) / 2, (rect2.bottom - rect.bottom) / 2, ((rect2.right - rect.right) / 2) + rect.right, ((rect2.bottom - rect.bottom) / 2) + rect.bottom);
        return rect.contains(x, y);
    }

    public void setRotationDegree(float f) {
        if (f6482k || Math.abs(f) >= 2.0f) {
            f6482k = true;
            mCurDegree += f;
            mCurDegree %= 360.0f;
            if (mCurDegree < 0.0f) {
                mCurDegree += 360.0f;
            }
            this.mCenterImage.setRotationDegree(mCurDegree);
            return;
        }
        this.f6482k = false;
    }

    public void startOpenLightAnim() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mCenterLayout, "scaleX", new float[]{0.0f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mCenterLayout, "scaleY", new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setInterpolator(new OvershootInterpolator());
        animatorSet.setDuration(500);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mCenterImage.updateScale(getSelectIndex());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

//    public void m10789b() {
//        this.f6472a.m10806b();
//    }

    public int getSelectIndex() {
        return this.mCenterImage.getSelectIndex();
    }

    public void m10790c() {
        this.mCenterImage.setModulFromDegree(this.mCurDegree);
    }
}