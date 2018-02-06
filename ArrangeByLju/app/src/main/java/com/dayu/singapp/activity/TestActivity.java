package com.dayu.singapp.activity;

import android.os.Bundle;
import android.view.View;

import com.dayu.singapp.R;
import com.dayu.singapp.view.circle_menu.CircleMenuLayout;

/**
 * Created by Ljy on 2018/2/5.
 */

public class TestActivity extends BaseActivity{
    private CircleMenuLayout mCircleMenuLayout;

    private String[] mItemTexts = new String[] { "0 ", "1", "2",
            "3", "4", "SOS" };

//    private int[] mItemImgs = new int[] { R.mipmap.home_mbank_1_normal,
//            R.mipmap.home_mbank_2_normal, R.mipmap.home_mbank_3_normal,
//            R.mipmap.home_mbank_4_normal, R.mipmap.home_mbank_5_normal,
//            R.mipmap.home_mbank_6_normal };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemTexts);

        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {


            }

            @Override
            public void itemCenterClick(View view) {


            }
        });

    }
}
