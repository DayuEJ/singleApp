package com.dayu.singapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Ljy on 2017/7/17.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //unnecessary setContentView();
    }

    final public void bindClick(int resId, View.OnClickListener listener){
        findViewById(resId).setOnClickListener(listener);
    }

    final public void bindClicks(int[] resList, View.OnClickListener listener){
        for (int resId : resList){
            bindClick(resId, listener);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(Class<T> type, int res){
        View target  = findViewById(res);
        if (target != null){
            return (T) target;
        }
        return null;
    }

    public void doBackPressed(){
        onBackPressed();
    }
}
