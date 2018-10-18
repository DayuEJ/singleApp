package com.dayu.singapp.activity;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dayu.singapp.R;
import com.dayu.singapp.activeandroid.Log;
import com.dayu.singapp.calculator.Calculator;
import com.dayu.singapp.util.DeviceUtil;
import com.dayu.singapp.util.LogUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private final String TAG = "TestActivity";
    private final int REQUEST_SHOW_ACCESSIBILITY_SETTINGS = 1586;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindAction();
    }

    private void bindAction(){
        bindClicks(new int[]{
                R.id.tv_action_heart,
                R.id.tv_layout_param,
                R.id.tv_install_list,
                R.id.tv_cubic_bezier,
                R.id.tv_card_slide,
                R.id.tv_card_calendar,
                R.id.tv_card_lottie,
                R.id.tv_card_calculator,
                R.id.tv_card_recycle,
                R.id.opengl,
                R.id.tv_card_canvas
        }, this);
    }

    private void openFirstOpengL(){
        Intent intent = new Intent(MainActivity.this, FirstOpenGLProjectActivity.class);
        startActivity(intent);
    }

    private void testLayoutParams(){
        //// TODO: 2017/8/9  test layoutParams
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

            case R.id.opengl:
//                openFirstOpengL();
                v.setTransitionName("test");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(v, "test"));
                startActivity(new Intent(this, OptionsAnimStartActivity.class), options.toBundle());
                break;

            case R.id.tv_install_list:
                Intent intent_bezier = new Intent(MainActivity.this, BezierQuadShowActivity.class);
                startActivity(intent_bezier);
                break;

            case R.id.tv_cubic_bezier:
                Intent intent_cubic = new Intent(MainActivity.this, BezierCubicShowActivity.class);
                startActivity(intent_cubic);
                break;

            case R.id.tv_card_slide:
                startActivity(new Intent(MainActivity.this, CardSlideActivity.class));
                break;

            case R.id.tv_card_calendar:
                startActivity(new Intent(MainActivity.this, CalendarShowActivity.class));
                break;

            case R.id.tv_card_lottie:
                startActivity(new Intent(MainActivity.this, LottieShowActivity.class));
                break;

            case R.id.tv_card_calculator:
                startActivity(new Intent(MainActivity.this, Calculator.class));
                break;

            case R.id.tv_card_recycle:
                startActivity(new Intent(MainActivity.this, CenterControlActivity.class));
                break;

            case R.id.tv_card_canvas:
                startActivity(new Intent(MainActivity.this, CanvasTestActivity.class));
                break;

            default:
                break;
        }
    }
    //// FIXME: 2017/8/21  为什么要一定要采用 startActivityForResult的启动方式
//    public void showAccessibilitySettings(Activity activity) {
//        Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
//        try {
//            activity.startActivityForResult(intent, REQUEST_SHOW_ACCESSIBILITY_SETTINGS);
//        } catch (ActivityNotFoundException e) {
//            if (BuildConfig.DEBUG) LogUtil.d(TAG, "show setting fail");
//        }
//    }
}
