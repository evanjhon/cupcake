package com.evan.cupcake.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.evan.cupcake.R;
import com.evan.cupcake.util.AppManager;
import com.evan.cupcake.util.StatusBarBlackWordUtil;
import com.evan.cupcake.util.StatusBarUtil;
import com.evan.cupcake.util.UtilHelpers;

/**
 * Created by Evan on 2018/12/19.
 * des 基类
 */

public class BaseActivity extends AppCompatActivity {
    protected Activity context;

    private View view;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.addActivity(this);
        context = this;


    }

    /**
     * 白底黑字的状态栏
     */
    protected void StatusBarWhiteColor() {
        if (Build.VERSION.SDK_INT > 22) {
            View view_staus = findViewById(R.id.view_staus);
            view_staus.setVisibility(View.VISIBLE);
            StatusBarUtil.setStutaViewHeight(this, view_staus);
            StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.white));
            StatusBarBlackWordUtil.StatusBarLightMode(this);
        } else {
            StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.title_bg));
            StatusBarBlackWordUtil.StatusBarLightMode(this);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                UtilHelpers.hideKeyboard(ev, view, BaseActivity.this);//调用方法判断是否需要隐藏键盘
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
