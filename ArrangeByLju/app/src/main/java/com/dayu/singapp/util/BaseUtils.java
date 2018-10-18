package com.dayu.singapp.util;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by Ljy on 2018/10/18.
 */

public class BaseUtils {
    public static boolean isMainProcess(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.pid == android.os.Process.myPid()) {
                    return true;
                }
            }
        }
        return false;
    }
}
