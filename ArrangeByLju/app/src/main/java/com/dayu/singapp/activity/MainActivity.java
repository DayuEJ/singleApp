package com.dayu.singapp.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.dayu.singapp.R;
import com.dayu.singapp.util.DeviceUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private final String TAG = "MainActivity";
    private final int REQUEST_SHOW_ACCESSIBILITY_SETTINGS = 1586;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindAction();
    }

    private void bindAction(){
        findViewById(R.id.tv_action_heart).setOnClickListener(this);
        findViewById(R.id.tv_layout_param).setOnClickListener(this);
        findViewById(R.id.tv_layout_present).setOnClickListener(this);
        findViewById(R.id.tv_install_list).setOnClickListener(this);
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

            case R.id.tv_layout_present:
                Intent intent_access = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
                startActivity(intent_access);
                break;

            case R.id.tv_install_list:
//                Intent intent_install = new Intent(MainActivity.this, InstallPackageNameActivity.class);
//                startActivity(intent_install);
                Intent intent_parcelable = new Intent(MainActivity.this, ParcelableActivity.class);
                startActivity(intent_parcelable);

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
