package com.dayu.singapp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dayu.singapp.R;

@SuppressWarnings("unused")// call flash heart anim
public class InCallHeartAnimLayout extends RelativeLayout {
    private final String TAG = "InCallHeartAnimLayout";
    private int mCurrentWidth;
    private int mCurrentHeight;

    private int mSpeedTime = 2000;                                     // 值越小表示动画速度越快
    private int mIntervalTime = 500;                                   // 动画间隔时间
    private int mRepeatCount = ValueAnimator.INFINITE;                 // 动画出现的次数
    private Interpolator mUserInterpolator = new LinearInterpolator(); // 动画插值器

    private int mImageId = R.mipmap.ic_anim_rose;
    private ValueAnimator mValueAnimator;

    public InCallHeartAnimLayout(Context context) {
        super(context);
    }

    public InCallHeartAnimLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InCallHeartAnimLayout(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    @SuppressWarnings("unused")
    @TargetApi(21)
    public InCallHeartAnimLayout(Context context, AttributeSet attributeSet, int defStyleAttr,
                                 int defStyleRes) {
        super(context, attributeSet, defStyleAttr, defStyleRes);
    }

    public InCallHeartAnimLayout setSpeedTime(int speedTime) {
        this.mSpeedTime = speedTime;
        return this;
    }

    public InCallHeartAnimLayout setAnimInterval(int time) {
        mIntervalTime = time;
        return this;
    }

    public InCallHeartAnimLayout setAnimRepeatCount(int count) {
        mRepeatCount = count;
        return this;
    }

    public InCallHeartAnimLayout setUserInterpolator(Interpolator interpolator) {
        mUserInterpolator = interpolator;
        return this;
    }

    public InCallHeartAnimLayout setAnimView(@DrawableRes int imageId) {
        mImageId = imageId;
        return this;
    }

    public void startAnim() {
        release();
        //Create anim child View
        mValueAnimator = ValueAnimator.ofInt(0, 1, 0).setDuration(mIntervalTime);
        mValueAnimator.setRepeatCount(mRepeatCount);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if ((Integer) valueAnimator.getAnimatedValue() == 1) {
                    // 动态生成一个ImageView，添加到该InCallHeartAnimLayout中
                    View imageView = new ImageView(getContext());
                    imageView.setBackgroundResource(mImageId);
                    // calculateTransferParam  calculate anim child view random path
                    startValueAnim(imageView, calculateTransferParam());
                }
            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
            }
        });
        mValueAnimator.start();
    }

    public void stopAnim() {
        release();
    }

    @Override
    protected void onSizeChanged(int currentWidth, int currentHeight,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(currentWidth, currentHeight, oldWidth, oldHeight);
        this.mCurrentWidth = currentWidth;
        this.mCurrentHeight = currentHeight;
    }

    // random position arithmetic
    private TransferParam calculateTransferParam() {
        float width;
        float height;
        float random;
        float endTranslateY;
        float endTranslateX;
        if (Math.random() <= 0.10000000149011612d) {
            width = ((float) this.mCurrentWidth) / 2.0f;
            height = ((float) this.mCurrentHeight) / 2.0f;
            random = (float) Math.random();
            if (random <= 0.3f) {
                endTranslateY = calculateA(calculateB(-height, height), height);
                endTranslateX = 0.0f;
            } else if (random < 0.7f) {
                endTranslateX = calculateA(calculateB(-width, width), width);
                endTranslateY = 0.0f;
            } else {
                endTranslateX = calculateA(calculateB(-width, width), width);
                endTranslateY = calculateA(calculateB(-height, height), height);
                if (Math.abs(endTranslateX) < width && Math.abs(endTranslateY) < height) {
                    if (Math.random() <= 0.5d) {
                        endTranslateX = calculateA(endTranslateX, width);
                    } else {
                        endTranslateY = calculateA(endTranslateY, height);
                    }
                }
            }
            return new TransferParam(this, 0.0f, 0.0f, endTranslateX, endTranslateY, 0.1f, 0.1f);
        }
        width = ((float) this.mCurrentWidth) / 2.0f;
        height = ((float) this.mCurrentHeight) / 2.0f;
        random = (float) (Math.random() * 50.0f);
        endTranslateX = calculateB((-width) - random, width + random);
        endTranslateY = calculateB((-height) - random, random + height);
        if (Math.abs(endTranslateX) < width && Math.abs(endTranslateY) < height) {
            if (Math.random() <= 0.5d) {
                endTranslateX = calculateA(endTranslateX, width);
            } else {
                endTranslateY = calculateA(endTranslateY, height);
            }
        }
        return new TransferParam(this, 0.0f, 0.0f, endTranslateX, endTranslateY, 0.0f, calculateB(10.0f, 10.0f) / 10.0f);
    }

    private float calculateA(float f1, float f2) {
        if (Math.abs(f1) >= f2) {
            return f1;
        }
        if (f1 < 0.0f) {
            return f1 - f2;
        }
        return f1 + f2;
    }

    private float calculateB(float f1, float f2) {
        return ((float) (Math.random() * ((double) ((f2 - f1) + 10.0f)))) + f1;
    }

    private void remove(final View view) {
        post(new Runnable() {
            public void run() {
                removeView(view);
            }
        });
    }

    private void release(){
        if (mValueAnimator != null && mValueAnimator.isRunning()){
            mValueAnimator.end();
            mValueAnimator.cancel();
        }
    }

    // 自定义属性动画
    private void startValueAnim(final View view, final TransferParam transferParam) {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(view, layoutParams);

        view.setTranslationX(transferParam.startTranslateX);
        view.setTranslationY(transferParam.startTranslateY);
        view.setScaleX(transferParam.scaleStart);
        view.setScaleY(transferParam.scaleStart);

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(mSpeedTime);
        //valueAnimator.ofObject() 系统没有设置默认的估值器
        valueAnimator.setObjectValues(new DeltaParam(this, transferParam.startTranslateX,
                transferParam.startTranslateY, transferParam.scaleStart));
        valueAnimator.setInterpolator(mUserInterpolator);
        //根据当前属性改变百分比计算当前属性值 估值器
        //根据当前时间流逝的百分比  计算当前属性变化的百分比 插值器
        valueAnimator.setEvaluator(new TypeEvaluator<DeltaParam>() {
            @Override
            public DeltaParam evaluate(float fraction, DeltaParam startValue, DeltaParam endValue) {
                //fraction 估值小数  动画属性变化的百分比
                InCallHeartAnimLayout myLayout = (InCallHeartAnimLayout) view.getParent();
                DeltaParam deltaParam = new DeltaParam(myLayout);
                //重新定义插值器  fraction 是从0 - 1 匀速变化的  new LinearInterpolator();
                float interFraction = new AccelerateInterpolator().getInterpolation(fraction);

                deltaParam.deltaX = transferParam.startTranslateX +
                        ((transferParam.endTranslateX - transferParam.startTranslateX) * interFraction);

                deltaParam.deltaY = transferParam.startTranslateY +
                        ((transferParam.endTranslateY - transferParam.startTranslateY) * interFraction);

                deltaParam.deltaScale = transferParam.scaleStart  +
                        ((transferParam.scaleEnd - transferParam.scaleStart) * interFraction);

                return deltaParam;
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                view.setVisibility(View.VISIBLE);
            }

            public void onAnimationEnd(Animator animator) {
                remove(view);
            }

            public void onAnimationCancel(Animator animator) {
                remove(view);
            }
        });

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                DeltaParam deltaParam = (DeltaParam) valueAnimator.getAnimatedValue();
                view.setTranslationX(deltaParam.deltaX);
                view.setTranslationY(deltaParam.deltaY);
                view.setScaleX(deltaParam.deltaScale);
                view.setScaleY(deltaParam.deltaScale);
                invalidate();
            }
        });
        valueAnimator.start();
    }

    // 封装了参数坐标变换的差值
    private class DeltaParam {
        float deltaX;
        float deltaY;
        float deltaScale;
        final InCallHeartAnimLayout mInCallHeartAnimLayout;

        DeltaParam(InCallHeartAnimLayout inCallHeartAnimLayout, float deltaX,
                   float deltaY, float deltaScale) {
            this.mInCallHeartAnimLayout = inCallHeartAnimLayout;
            this.deltaX = deltaX;
            this.deltaY = deltaY;
            this.deltaScale = deltaScale;
        }

        DeltaParam(InCallHeartAnimLayout inCallHeartAnimLayout) {
            this.mInCallHeartAnimLayout = inCallHeartAnimLayout;
        }
    }

    // 封装了一些与图形变换的参数
    private class TransferParam {
        float startTranslateX = 0.0f;
        float startTranslateY = 0.0f;
        float endTranslateX;
        float endTranslateY;
        float scaleStart;
        float scaleEnd;
        final InCallHeartAnimLayout mInCallHeartAnimLayout;

        TransferParam(InCallHeartAnimLayout inCallHeartAnimLayout,
                      float startTranslateX,
                      float startTranslateY,
                      float endTranslateX,
                      float endTranslateY,
                      float scaleStart,
                      float scaleEnd) {
            this.mInCallHeartAnimLayout = inCallHeartAnimLayout;
            this.startTranslateX = startTranslateX;
            this.startTranslateY = startTranslateY;
            this.endTranslateX = endTranslateX;
            this.endTranslateY = endTranslateY;
            this.scaleStart = scaleStart;
            this.scaleEnd = scaleEnd;
        }

        public String toString() {
            return "HeartParams{endTranslateX=" + this.endTranslateX + ", mEndTranslateY=" +
                    this.endTranslateY + ", mScaleStart=" + this.scaleStart + ", mScaleEnd="
                    + this.scaleEnd + '}';
        }
    }
}
