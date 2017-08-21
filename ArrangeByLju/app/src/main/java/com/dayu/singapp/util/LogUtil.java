package com.dayu.singapp.util;

import android.util.Log;

import com.dayu.singapp.BuildConfig;

public class LogUtil {

    private static boolean LogEnabled = BuildConfig.DEBUG ? true : false;

    public static void d(String TAG, String msg) {
        if (LogEnabled)
            Log.d(TAG, msg);
    }

    public static void e(String TAG, String msg) {
        if (LogEnabled)
            Log.e(TAG, msg);
    }

    public static void i(String TAG, String msg) {
        if (LogEnabled)
            Log.i(TAG, msg);
    }

    public static void v(String TAG, String msg) {
        if (LogEnabled)
            Log.v(TAG, msg);
    }

}
