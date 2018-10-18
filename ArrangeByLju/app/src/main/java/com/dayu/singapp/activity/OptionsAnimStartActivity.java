package com.dayu.singapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dayu.singapp.R;

/**
 * Created by Ljy on 2018/7/20.
 */

public class OptionsAnimStartActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * enter：用于决定第一次打开当前Activity时的动画
         * exit : 用于决定退出当前Activity时的动画
         * reenter: 用于决定如果当前Activity已经打开过，并且再次打开该Activity时的动画
         * shared elements:用于决定在两个Activity之间切换时，指定两个Activity中对应的View的过渡效果
         */
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);//告诉Window页面切换需要使用动画
//        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);//这里引用用android.R
        //退出时使用
//        getWindow().setExitTransition(explode);
        //第一次进入时使用
//        getWindow().setEnterTransition(explode);
        //再次进入时使用
//        getWindow().setReenterTransition(explode);

        /**
         * 相当于在Style 直接使用主题
         * <item name="android:windowExitTransition">@transition/explode</item>
           <item name="android:windowEnterAnimation">@transition/explode</item>
            <item name="android:windowReenterTransition">@transition/explode</item>
         */
        setContentView(R.layout.activity_option_anim);
    }


}
