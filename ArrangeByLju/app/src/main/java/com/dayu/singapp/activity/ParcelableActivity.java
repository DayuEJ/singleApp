package com.dayu.singapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dayu.singapp.R;
import com.dayu.singapp.bean.Book;

/**
 * Created by Ljy on 2017/10/26.
 */

public class ParcelableActivity extends BaseActivity {
    private Book mBook;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable);
        mBook = new Book(555, "Ljy", 100);
        findViewById(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParcelableActivity.this, ParcelableActivity1.class);
                intent.putExtra(ParcelableActivity1.DATA_MODE, mBook);
                startActivity(intent);
            }
        });
    }
}
