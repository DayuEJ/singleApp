package com.dayu.singapp.common;

import android.app.Application;
import android.content.Context;

import com.dayu.singapp.manager.BroadReceiverManager;
import com.dayu.singapp.manager.ServiceManager;
import com.dayu.singapp.util.BaseUtils;

import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * Created by zhangjinwei on 2017/5/10.
 */

public class ApplicationEx extends Application {
    private static final String LOG_TAG = "ApplicationEx";
    private static ApplicationEx sInstance;
    private ConcurrentLinkedQueue<AppListener> mListenerQ = new ConcurrentLinkedQueue<>();

    public static ApplicationEx getInstance() {
        return sInstance;
    }

    public interface AppListener {
        void onAppClose();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        if (BaseUtils.isMainProcess(getInstance())){
            startInitWork();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // TODO: 2017/5/10
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        destroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // TODO: 2017/5/10
    }


    private void startInitWork() {
        initSync();
        initAsync();
    }

    private void initSync() {
        BroadReceiverManager.getInstance();
    }

    // Async work, controlled by each task.
    private void initAsync() {
        ServiceManager.startServiceIfNeed(getInstance());
    }

    private void destroy() {
        for (AppListener listener : mListenerQ) {
            listener.onAppClose();
        }
        mListenerQ.clear();
    }

    public void addListener(AppListener listener) {
        if (!mListenerQ.contains(listener)) {
            mListenerQ.add(listener);
        }
    }
}