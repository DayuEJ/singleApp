package com.dayu.singapp.manager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ljy on 2018/10/18.
 */

public class ServiceManager {
    public static final int GLOBAL_WORK_SERVICE = 1;
    public static final int KERNEL_SERVICE = 2;
    public static final int REMOTE_SERVICE = 3;
    public static final int FOREGROUND_SERVICE = 4;

    private static SparseArray<String> sServiceInfo = new SparseArray<String>(){
        {

        }
    };

    public static void startServiceIfNeed(Context context){
        for (int i = 0; i < sServiceInfo.size(); i++){
            startServiceIfNeed(context, sServiceInfo.keyAt(i), null);
        }
    }

    //return true when service alive
    public static boolean startServiceIfNeed(Context context, int type, Map<String, String> params){
        return startServiceIfNeed(context, type, params, true);
    }

    public static boolean startServiceIfNeed(Context context, int type, Map<String, String> params, boolean autoStart) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = manager.getRunningServices(Integer.MAX_VALUE);
        boolean serviceAlive = false;
        String serviceName = sServiceInfo.get(type, "");

        for (ActivityManager.RunningServiceInfo info : serviceList) {
            if (info.process.equals(context.getPackageName()) && info.service.getClassName().equals(serviceName)) {
                serviceAlive = true;
                break;
            }
        }

        if (!serviceAlive && autoStart) {
            Intent intent = null;
            switch (type) {
//                case GLOBAL_WORK_SERVICE:
//                    intent = new Intent(context, LionmobiService.class);
//                    break;
//                case KERNEL_SERVICE:
//                    intent = new Intent(context, KernelService.class);
//                    break;
//                case FOREGROUND_SERVICE:
//                    intent = new Intent(context, ForegroundToolBarService.class);
//                    break;
//                case REMOTE_SERVICE:
//                    intent = new Intent(context, RemoteService.class);
                default:
                    break;
            }

            if (intent != null) {
                //set params to intent
                if (params != null) {
                    Set<Map.Entry<String, String>> set = params.entrySet();
                    Iterator<Map.Entry<String, String>> iterator = set.iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> info = iterator.next();
                        intent.putExtra(info.getKey(), info.getValue());
                    }
                }
                context.startService(intent);
            }
        }

        return serviceAlive;
    }
}
