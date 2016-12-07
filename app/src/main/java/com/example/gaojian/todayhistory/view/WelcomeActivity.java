package com.example.gaojian.todayhistory.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.example.gaojian.todayhistory.R;

import baseactivity.BaseActivity;

public class WelcomeActivity extends BaseActivity {
    int i = 3;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (i == 0) {
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                mHandler.removeCallbacksAndMessages(null);
                finish();
            } else {
                i--;
                mHandler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

    @Override
    public int bindLayout() {
        initState();
        return R.layout.activity_main;
    }
    /**
     * 沉浸式状态栏
     */
    private void initState() {
        //Build.VERSION.SDK_INT进行版本判断
        //Build.VERSION_CODES.KITKAT      4.4版
       /* Google从Android kitkat(Android 4.4)开始,给我们开发者提供了一套能透明的系统ui样式给状态栏和导航栏，
        这样的话就不用向以前那样每天面对着黑乎乎的上下两条黑栏了，
        还可以调成跟Activity一样的样式，形成一个完整的主题,和IOS7.0以上系统一样了。*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            /*//透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);*/
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void requestSuccess(String json, int tag) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
