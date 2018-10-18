package com.dayu.singapp.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ljy on 2018/6/29.
 */

public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);

    private int i = 0;

    //使用CAS算法实现线程安全计数器
    private void safeCount(){
        for (;;){
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, i++);
            if (suc){
                break;
            }
        }
    }

    //非线程安全计数
    private void count(){
        i++;
    }
}
