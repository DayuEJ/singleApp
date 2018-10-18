package com.dayu.singapp.view;

/**
 * Created by Ljy on 2018/2/5.
 */

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.dayu.singapp.R;
import com.dayu.singapp.util.DeviceUtil;
//import com.hawk.screen.camera.flashlight.commons.utils.C1920a;
//import com.hawk.screen.camera.flashlight.module.p071a.p072a.C1935d;
//import com.hawk.screen.camera.flashlight.module.p073b.C1946a;
//import com.hawk.screen.camera.flashlight.module.p073b.C1947c;
//import com.hawk.screen.camera.flashlight.module.p073b.C1948d;
//import com.hawk.screen.camera.flashlight.module.p073b.C1949e;
//import com.hawk.screen.camera.flashlight.module.p073b.C1950f;
//import com.hawk.screen.camera.flashlight.module.p073b.C1952g;
//import com.hawk.screen.camera.flashlight.module.p073b.C1953h;
//import com.hawk.screen.camera.flashlight.p068d.C1931g;

//listener C1935d
public class CenterImageView extends AppCompatImageView  {
    private static final String[] mScaleInfo = new String[]{"0", "1", "2", "3", "4", "SOS"};//f6489b
    private int mScaleSize;
    private float degree;//f6491c
//    private C1952g f6492d;
    private Paint mPaint;
    private int mSelectIndex;
    private int textSize;//f6495g;
    private int f6496h;
    private LightScaleChange mCallBack;

    public interface LightScaleChange{
        void scaleChange(int scale);
    }

    public void setScaleChangeListener(LightScaleChange listener){
        this.mCallBack = listener;
    }

//    class C20212 extends C1979a {
//        final CenterImageView f6487a;
//
//        C20212(CenterImageView centerImageView) {
//            this.f6487a = centerImageView;
//        }
//
//        public void onAnimationEnd(Animator animator) {
//            this.f6487a.mSelectIndex = 0;
//            this.f6487a.invalidate();
//        }
//    }

    class C20223 implements Runnable {
        final /* synthetic */ CenterImageView f6488a;

        C20223(CenterImageView centerImageView) {
            this.f6488a = centerImageView;
        }

        public void run() {
//            Snackbar.m1000a(this.f6488a, (int) R.string.flash_is_opend, -1).m991a();
//            if (this.f6488a.f6492d != null) {
////                this.f6488a.f6492d.m10541b();
//                this.f6488a.f6492d = null;
//            }
        }
    }

    public CenterImageView(Context context) {
        super(context);
        this.mScaleSize = mScaleInfo.length;
        this.degree = 0.0f;
        this.mSelectIndex = 0;
        this.f6496h = -1;
    }

    public CenterImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CenterImageView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.mScaleSize = mScaleInfo.length;
        this.degree = 0.0f;
        this.mSelectIndex = 0;
        this.f6496h = -1;
        init();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void init() {
        textSize = DeviceUtil.dp2Px(16);
        degree = (float) (360 / this.mScaleSize);
        mPaint = new Paint(1);
        mPaint.setTextSize((float) textSize);
        mPaint.setTextAlign(Align.CENTER);
    }

    public void setModul(final int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rotation", new float[]{getRotation(), ((float) i) * this.degree});
//        ofFloat.addListener(new C1979a(this) {
//            CenterImageView f6486b;
//
//            public void onAnimationEnd(Animator animator) {
////                this.f6486b.m10797b(this.f6486b.mScaleSize - i);
//                this.f6486b.mSelectIndex = this.f6486b.mScaleSize - i;
//                this.f6486b.invalidate();
//            }
//        });

        ofFloat.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mSelectIndex = mScaleSize - i;
                updateScale(mSelectIndex);
                invalidate();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
//        C1931g.m10470a().m10471a(45, getContext());//fixme virbate
        ofFloat.setDuration(300);
        ofFloat.start();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        for (int i = 0; i < this.mScaleSize; i++) {
            if (i == this.mScaleSize - 1) {
                this.mPaint.setColor(getResources().getColor(R.color.red));
            } else {
                this.mPaint.setColor(-1);
            }
            if (i == this.mSelectIndex) {
                this.mPaint.setTextSize((float) textSize);
            } else {
                this.mPaint.setTextSize((float) textSize);
            }
            canvas.drawText(mScaleInfo[i], (float) (width / 2), (float) (height / 6), this.mPaint);
            canvas.rotate(this.degree, (float) (width / 2), (float) (height / 2));
        }
        canvas.translate(((float) width) * 0.5f, ((float) height) * 0.5f);
    }

//    private void m10797b(int i) {
//        if (this.f6492d != null) {
//            this.f6492d.m10541b();
//            this.f6492d = null;
//        }
//        this.f6492d = new C1952g();
//        this.f6492d.m10539a((C1935d) this);
//        switch (i) {
//            case 0:
//                m10798b(this.f6492d);
//                return;
//            case 1:
//                m10799c(this.f6492d);
//                return;
//            case 2:
//                m10800d(this.f6492d);
//                return;
//            case 3:
//                m10801e(this.f6492d);
//                return;
//            case 4:
//                m10802f(this.f6492d);
//                return;
//            case 5:
//                m10793a(this.f6492d);
//                return;
//            default:
//                return;
//        }
//    }

//    public void m10806b() {
//        if (this.f6492d != null) {
//            this.f6492d.m10541b();
//            this.f6492d = null;
//        }
//    }
//
//    private void m10793a(C1952g c1952g) {
//        c1952g.m10540a(new C1953h());
//        c1952g.m10538a();
//    }
//
//    private void m10798b(C1952g c1952g) {
//        c1952g.m10540a(new C1946a());
//        c1952g.m10538a();
//    }
//
//    private void m10799c(C1952g c1952g) {
//        c1952g.m10540a(new C1947c());
//        c1952g.m10538a();
//    }
//
//    private void m10800d(C1952g c1952g) {
//        c1952g.m10540a(new C1948d());
//        c1952g.m10538a();
//    }
//
//    private void m10801e(C1952g c1952g) {
//        c1952g.m10540a(new C1949e());
//        c1952g.m10538a();
//    }
//
//    private void m10802f(C1952g c1952g) {
//        c1952g.m10540a(new C1950f());
//        c1952g.m10538a();
//    }

    public void m10807c() {
        float rotation = getRotation();
        if (rotation > this.degree) {
            rotation -= 360.0f;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rotation", new float[]{rotation, 0.0f});
//        ofFloat.addListener(new C20212(this));

        ofFloat.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mSelectIndex = 0;
                updateScale(mSelectIndex);
                invalidate();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

//        C1931g.m10470a().m10471a(45, getContext());//// FIXME: 2018/2/5  virbate
//        m10797b(0);
        ofFloat.setDuration(300);
        ofFloat.start();
    }

    public void setModulFromDegree(float f) {
        float f2 = f % 360.0f;
        if (f2 < 0.0f) {
            f2 += 360.0f;
        }
        for (int i = 0; i < this.mScaleSize; i++) {
            if (i != 0) {
                float f3 = (this.degree * ((float) i)) + (this.degree / 2.0f);
                if (f2 >= (this.degree * ((float) (i - 1))) + (this.degree / 2.0f) && f2 <= f3) {
                    setModul(i);
                    return;
                }
            } else if (f2 >= 360.0f - (this.degree / 2.0f) || f2 <= this.degree / 2.0f) {
                m10807c();
                return;
            }
        }
    }

    public void setRotationDegree(float f) {
        setRotation(f);
        if (((double) (f % this.degree)) < ((double) this.degree) * 0.2d || ((double) (f % this.degree)) > ((double) this.degree) * 0.8d) {
            this.mSelectIndex = Math.round(((float) this.mScaleSize) - (f / this.degree)) % 6;
            if (this.f6496h != this.mSelectIndex) {
//                C1931g.m10470a().m10471a(45, getContext());//// FIXME: 2018/2/5 virbate
                this.f6496h = this.mSelectIndex;
            }
            invalidate();
            return;
        }
        this.f6496h = -1;
        this.mSelectIndex = -1;
        invalidate();
    }

    public int getSelectIndex() {
        return this.mSelectIndex;
    }

    public void mo1339a(int i) {
        post(new C20223(this));
    }

    public void mo1340a(boolean z) {
    }

    public void updateScale(int scale){
        if (mCallBack != null){
            mCallBack.scaleChange(scale);
        }
    }
}