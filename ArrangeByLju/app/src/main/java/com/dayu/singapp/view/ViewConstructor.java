package com.dayu.singapp.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dayu.singapp.util.LogUtil;

/**
 * Created by Ljy on 2017/7/20.
 */

public class ViewConstructor extends View {
    public ViewConstructor(Context context) {
        this(context, null);
        LogUtil.d("ViewConstructor", "one");
    }

    public ViewConstructor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        LogUtil.d("ViewConstructor", "two");
    }

    public ViewConstructor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.d("ViewConstructor", "three");
    }

    public ViewConstructor(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LogUtil.d("ViewConstructor", "four");
    }
}
