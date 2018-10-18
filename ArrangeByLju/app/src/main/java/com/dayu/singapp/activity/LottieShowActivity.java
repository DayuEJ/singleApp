package com.dayu.singapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.dayu.singapp.R;
import com.dayu.singapp.activeandroid.Log;
import com.dayu.singapp.util.LogUtil;

/**
 * Created by Ljy on 2018/1/31.
 */

public class LottieShowActivity extends BaseActivity {
    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_show);
        /**note
        * 不同的线程访问同一个ThreadLocal的get() ThreadLocal内部会从各自线程中取出一个数组，然后在从
         * 数组中根据当前ThreadLocal的索引去查找对应的value.这就是为什么通过ThreadLocal可以在不同线程中维护同一套数据
         * 副本，并且彼此互不干扰。
        * */
        testThreadLocal();
    }

    private void testThreadLocal(){
        mBooleanThreadLocal.set(true);
        LogUtil.d("thread_local",  "main value = " + mBooleanThreadLocal.get());
        new Thread("Thread#1"){
            @Override
            public void run() {
                mBooleanThreadLocal.set(false);
                LogUtil.d("thread_local",  "Thread#1 value = " + mBooleanThreadLocal.get());
            }
        }.start();

        new Thread("Thread#2"){
            @Override
            public void run() {
                LogUtil.d("thread_local",  "Thread#2 value = " + mBooleanThreadLocal.get());
            }
        }.start();
    }
}
