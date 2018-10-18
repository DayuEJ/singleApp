package com.dayu.singapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dayu.singapp.R;
import com.dayu.singapp.util.LogUtil;
import com.dayu.singapp.util.ResourceUtil;

/**
 * Created by Ljy on 2018/7/27.
 */

public class CanvasTestView extends View {
    public CanvasTestView(Context context) {
        super(context);
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ResourceUtil.getColor(R.color.patch_err));
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        paint.setTextSize(120);
        paint.setTextAlign(Paint.Align.LEFT);
        LogUtil.d("ljy", "aecent" + fontMetricsInt.ascent);
        paint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(10, 10, 200, 200);
        canvas.drawRect(rectF, paint);
//        canvas.translate(100, 100);
//        canvas.drawRect(rectF, paint);


//        canvas.drawText("HelloWorld", 100, 500, paint);
    }
}
