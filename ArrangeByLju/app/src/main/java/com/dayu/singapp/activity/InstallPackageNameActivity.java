package com.dayu.singapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.dayu.singapp.R;

/**
 * Created by Ljy on 2017/8/21.
 */

public class InstallPackageNameActivity extends BaseActivity {
    private ListView mDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_package_name);
        initView();
    }

    private void initView(){
        mDataList = (ListView) findViewById(R.id.lv_data);
    }

    private class MyadApter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
