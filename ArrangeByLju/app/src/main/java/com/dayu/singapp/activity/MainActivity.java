package com.dayu.singapp.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.dayu.singapp.R;
import com.dayu.singapp.util.DeviceUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindAction();
    }

    private void bindAction(){
        // test layoutParams
        findViewById(R.id.tv_action_heart).setOnClickListener(this);
        findViewById(R.id.tv_layout_param).setOnClickListener(this);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) findViewById(R.id.tv_layout_param).getLayoutParams();
        params.setMargins(params.leftMargin, DeviceUtil.dp2Px(100), params.rightMargin, params.bottomMargin);
        findViewById(R.id.tv_layout_param).setLayoutParams(params);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById(R.id.tv_action_heart).getLayoutParams();
        layoutParams.setMargins(params.leftMargin, DeviceUtil.dp2Px(0), params.rightMargin, params.bottomMargin);
        findViewById(R.id.tv_action_heart).setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_action_heart:
                Intent intent = new Intent(this, InCallHeartAnimActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_layout_param:
                doBackPressed();
                break;
            default:
                break;
        }
    }
}
