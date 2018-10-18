package com.dayu.singapp.util;

import android.content.Context;
import java.util.HashMap;

/**
 * Created by Ljy on 2018/6/27.
 */

public class TimeMonitorManager {
    private static TimeMonitorManager mTimeMonitorManager = null;
    private static Context mContext;
    private HashMap<Integer, TimeMonitor> timeMonitorList = new HashMap<>();
    public synchronized static TimeMonitorManager getInstance(){
        if (mTimeMonitorManager == null){
            mTimeMonitorManager = new TimeMonitorManager();
        }
        return mTimeMonitorManager;
    }

    public TimeMonitorManager(){
    }

    //初始化某一个打点器
    public void resetTimeMonitor(int id){
        if (timeMonitorList.get(id) != null){
            timeMonitorList.remove(id);
        }
        getTimeMonitor(id);
    }

    public TimeMonitor getTimeMonitor(int id){
        TimeMonitor monitor = timeMonitorList.get(id);
        if (monitor == null){
            monitor = new TimeMonitor(id);
            timeMonitorList.put(id, monitor);
        }
        return monitor;
    }
}
