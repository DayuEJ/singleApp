package com.dayu.singapp.util;


import com.airbnb.lottie.BuildConfig;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Ljy on 2018/6/27.
 */

public class TimeMonitor {
    public final String TAG = "TimeMonitor";
    private int monitorId = -1;

    private HashMap<String, Long> mTimeTag = new HashMap<>();
    private long mStartTime = 0;

    public TimeMonitor(int id){
        if (BuildConfig.DEBUG){
            LogUtil.d(TAG, "init TimeMonitor id" + id);
        }
        monitorId = id;
    }

    public int getMonitorId(){
        return monitorId;
    }

    //每次重新启动都要把前面的数据清除，避免统计到错误的数据
    public void startMoniter(){
        if (mTimeTag.size() > 0){
            mTimeTag.clear();
        }
        mStartTime = System.currentTimeMillis();
    }

    //打一次点
    public void recordingTimeTag(String tag){
        //检查是否保存相同的tag
        if (mTimeTag.get(tag) != null){
            mTimeTag.remove(tag);
        }

        long time = System.currentTimeMillis() - mStartTime;
        mTimeTag.put(tag, time);
    }

    public void end(String tag, boolean writelog){
        recordingTimeTag(tag);
        end(writelog);
    }

    public void end(boolean isWriteLog){
        if (isWriteLog){
            //写入文件到本地
        }
        testShowData();
    }

    public void testShowData(){
        if (mTimeTag.size() < 0){
            return;
        }

        Iterator iterator = mTimeTag.keySet().iterator();
        while (iterator != null && iterator.hasNext()){
            String tag = (String) iterator.next();
            LogUtil.d(TAG, tag + ":" + mTimeTag.get(tag));
        }
    }

    public HashMap<String, Long> getTimeTags(){
        return mTimeTag;
    }
}
