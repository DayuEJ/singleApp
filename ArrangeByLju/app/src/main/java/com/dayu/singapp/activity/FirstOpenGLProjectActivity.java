package com.dayu.singapp.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.dayu.singapp.R;
import com.dayu.singapp.util.FirstOpenGLRenderer;

/**
 * Created by Ljy on 2018/4/12.
 */

public class FirstOpenGLProjectActivity extends BaseActivity {
    private GLSurfaceView mGlSurfaceView;
    private boolean mIsRendererSet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlSurfaceView = new GLSurfaceView(this);
        if (checkOpenGlVaild()){
            mGlSurfaceView.setEGLContextClientVersion(2);
            mGlSurfaceView.setRenderer(new FirstOpenGLRenderer(this));
            //mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);//按请求渲染 默认情况下GLSurfaceView会以显示设备的刷新频率来刷新
            mIsRendererSet = true;
        }else {
            //当前设备不支持OpenGl2.0
            Toast.makeText(this, "This device dose not support OpenGl Es 2.0", Toast.LENGTH_LONG).show();
            return;
        }
        setContentView(mGlSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsRendererSet){
            mGlSurfaceView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mIsRendererSet){
            mGlSurfaceView.onPause();
        }
    }

    //检查设备是否支持opengl 2.0
    private boolean checkOpenGlVaild(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = activityManager.getDeviceConfigurationInfo();
        return info.reqGlEsVersion >= 0x20000;
    }
}
