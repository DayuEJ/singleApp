package com.dayu.singapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dayu.singapp.R;
import com.dayu.singapp.view.InCallHeartAnimLayout;

/**
 * Created by Ljy on 2017/7/18.
 */

public class InCallHeartAnimActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_heart);
        init();
    }

    private void init(){
        findViewById(InCallHeartAnimLayout.class, R.id.layout_heart_anim).startAnim();
    }
}
