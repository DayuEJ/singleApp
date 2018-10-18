package com.dayu.singapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dayu.singapp.R;
import com.dayu.singapp.util.LogUtil;
import com.dayu.singapp.view.CenterControlButton;
import com.dayu.singapp.view.CenterImageView;

/**
 * Created by Ljy on 2018/2/5.
 */

public class CenterControlActivity extends BaseActivity implements View.OnClickListener, CenterImageView.LightScaleChange{
    private CenterControlButton mControlButton;
    private CenterImageView mControlImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_control);
        mControlButton = (CenterControlButton) findViewById(R.id.control_layout);

        mControlImageView = (CenterImageView) findViewById(R.id.center_wheel);
        findViewById(R.id.light_btn_open).setOnClickListener(this);
        findViewById(R.id.light_btn_close).setOnClickListener(this);
        mControlImageView.setScaleChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.light_btn_open:
                mControlButton.setVisibility(View.VISIBLE);
                mControlButton.startOpenLightAnim();
                findViewById(R.id.light_btn_open).setVisibility(View.GONE);
                break;

            case R.id.light_btn_close:
                findViewById(R.id.light_btn_open).setVisibility(View.VISIBLE);
                mControlButton.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void scaleChange(int scale) {
        LogUtil.d("center_control", "scale" +  scale);
    }
}
