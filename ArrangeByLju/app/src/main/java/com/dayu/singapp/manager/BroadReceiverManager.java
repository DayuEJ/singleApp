package com.dayu.singapp.manager;

import com.dayu.singapp.common.ApplicationEx;
import com.dayu.singapp.receiver.HomeWatcherReceiver;

/**
 * Created by Ljy on 2018/10/18.
 */

public class BroadReceiverManager implements ApplicationEx.AppListener {
    private static BroadReceiverManager sInstance = null;
    private HomeWatcherReceiver mHomeWatcher;

    public static BroadReceiverManager getInstance(){
        if (sInstance == null){
            synchronized (BroadReceiverManager.class){
                if (sInstance == null){
                    sInstance = new BroadReceiverManager();
                }
            }
        }
        return sInstance;
    }

    private BroadReceiverManager(){
        ApplicationEx.getInstance().addListener(this);
        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver(){
        mHomeWatcher = new HomeWatcherReceiver();
        ApplicationEx.getInstance().registerReceiver(mHomeWatcher, mHomeWatcher.getHomeWatcherFilter());
    }


    @Override
    public void onAppClose() {
        ApplicationEx.getInstance().unregisterReceiver(mHomeWatcher);
    }
}
