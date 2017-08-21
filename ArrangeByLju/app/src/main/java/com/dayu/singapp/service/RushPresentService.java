package com.dayu.singapp.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.dayu.singapp.util.LogUtil;

/**
 * Created by Ljy on 2017/8/9.
 */

public class RushPresentService extends AccessibilityService {
    private final String TAG = "rush_present";
    private final String MONITOR_PACKAGENAME = "com.netease.onmyoji";//阴阳师的包名
    @Override
    protected void onServiceConnected() {
        //服务授权成功
        LogUtil.d(TAG, "onServiceConnected");
        AccessibilityServiceInfo serviceInfo = new AccessibilityServiceInfo();
        serviceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        serviceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        serviceInfo.notificationTimeout = 100;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            serviceInfo.flags = AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS;
        }
        setServiceInfo(serviceInfo);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //接受事件 接受的事件是经过过滤的 过滤是在配置工作时设定的
        AccessibilityNodeInfo accessibilityNodeInfo = event.getSource();
        if (accessibilityNodeInfo.getPackageName().equals(MONITOR_PACKAGENAME))
        accessibilityNodeInfo.findAccessibilityNodeInfosByText("");
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        return super.onKeyEvent(event);
        //接收按键事件
    }

    @Override
    public void onInterrupt() {
        //服务中断 （授权关闭 or Services kill）
    }
}
