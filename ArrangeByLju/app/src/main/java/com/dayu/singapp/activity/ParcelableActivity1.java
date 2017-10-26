package com.dayu.singapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dayu.singapp.R;
import com.dayu.singapp.bean.Book;

/**
 * Created by Ljy on 2017/10/26.
 * 测试序列化ParceLable 接口
 */

public class ParcelableActivity1 extends BaseActivity {
    public static final String DATA_MODE = "DATA_MODE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable1);
        Book book = getIntent().getParcelableExtra(DATA_MODE);
        int id = book.id;
        int price = book.price;
        String name = book.bookName;
        findViewById(TextView.class, R.id.tv_content).setText(id + price + name);
    }
}
