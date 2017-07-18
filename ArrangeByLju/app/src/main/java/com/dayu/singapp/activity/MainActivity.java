package com.dayu.singapp.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.dayu.singapp.R;
public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindAction();
    }

    private void bindAction(){
        findViewById(R.id.tv_action_heart).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_action_heart:
                Intent intent = new Intent(this, InCallHeartAnimActivity.class);
                startActivity(intent);
                break;
        }
    }
}
