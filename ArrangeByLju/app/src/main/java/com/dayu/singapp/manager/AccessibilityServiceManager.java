package com.dayu.singapp.manager;

import android.content.Context;
import android.provider.Settings;

import com.dayu.singapp.common.ApplicationEx;
import com.dayu.singapp.service.RushPresentService;

/**
 * Created by Ljy on 2017/10/17.
 */

public class AccessibilityServiceManager {
    private static AccessibilityServiceManager sInstance = null;
    public static final int REQUEST_SHOW_ACCESSIBILITY_SETTINGS = 1586;

    private static AccessibilityServiceManager getInstance(){
        if (sInstance == null){
            synchronized (AccessibilityServiceManager.class){
                sInstance = new AccessibilityServiceManager();
            }
        }
        return sInstance;
    }

    private AccessibilityServiceManager(){

    }

    public boolean isEnable(){
        Context context = ApplicationEx.getInstance();
        return isEnable(context);
    }

    //判断权限是否授权成功
    private boolean isEnable(Context context){
        boolean enable = false;
        try {
            int accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);

            if (accessibilityEnabled == 1) {
                String settingValue = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
                if (settingValue != null && settingValue.contains(RushPresentService.class.getSimpleName()) && settingValue.contains(context.getPackageName())) {
                    enable = true;
                }
            }
        } catch (Settings.SettingNotFoundException e) {
        }finally {
            return enable;
        }
    }
}
