package com.dayu.singapp.receiver;

/**
 * Created by lwp on 2016/4/2.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class HomeWatcherReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "HomeReceiver";
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";
    private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            // android.intent.action.CLOSE_SYSTEM_DIALOGS
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);

            if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                //press
//                EventBus.getDefault().post(new OnHomeKeyPressed());
                //StatisticsUtil.logAction(NewUserActionType.ACTION_HOME_KEY_PRESSED);
            } else if (SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason) ||
                    SYSTEM_DIALOG_REASON_ASSIST.equals(reason)) {
                //long press
//                EventBus.getDefault().post(new OnHomeKeyPressed().setLongPress(true));
                //StatisticsUtil.logAction(NewUserActionType.ACTION_HOME_KEY_PRESSED);
            } else if (SYSTEM_DIALOG_REASON_LOCK.equals(reason)) {
                //lock screen
            }
        }
    }

    public static IntentFilter getHomeWatcherFilter() {
        return new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
    }
}
