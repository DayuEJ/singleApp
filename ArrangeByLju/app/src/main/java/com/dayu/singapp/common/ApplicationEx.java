package com.dayu.singapp.common;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * Created by zhangjinwei on 2017/5/10.
 */

public class ApplicationEx extends Application {
    private static final String LOG_TAG = "ApplicationEx";
    private static ApplicationEx sInstance;

    //public static LiteOrm liteOrm;

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
        init();
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
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
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // TODO: 2017/5/10
    }

    private void init() {
    }

    private void startInitWork() {
        initSync();
        initAsync();
    }

    private void initSync() {
    }

    // Async work, controlled by each task.
    private void initAsync() {
        asyncDownloadGif();
    }

    private void asyncDownloadGif() {
        long delay = 0;
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
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
    }
}