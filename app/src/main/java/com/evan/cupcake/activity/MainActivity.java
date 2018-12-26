package com.evan.cupcake.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.evan.cupcake.fragment.HomeFragment;
import com.evan.cupcake.fragment.MyFragment;
import com.evan.cupcake.R;
import com.evan.cupcake.fragment.RefundFragment;
import com.evan.cupcake.fragment.UpdateFragment;
import com.evan.cupcake.base.BaseActivity;
import com.evan.cupcake.util.AppManager;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FrameLayout framelayoutMain;
    private RadioGroup mainRadiogroup;
    private RadioButton rbMainHome;
    private RadioButton rbMainDynamic;
    private RadioButton rbMainMessage;
    private RadioButton rbMainMy;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private HomeFragment homeFragment;//首页
    private RefundFragment refundFragment;//还款
    private UpdateFragment updateFragment;//升级
    private MyFragment myFragment;//我的

    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarWhiteColor();
        initView();
        initData();
    }


    private void initView() {
        framelayoutMain = (FrameLayout) findViewById(R.id.framelayout_main);
        mainRadiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        rbMainHome = (RadioButton) findViewById(R.id.rb_main_home);
        rbMainDynamic = (RadioButton) findViewById(R.id.rb_main_dynamic);
        rbMainMessage = (RadioButton) findViewById(R.id.rb_main_message);
        rbMainMy = (RadioButton) findViewById(R.id.rb_main_my);
        rbMainHome.setOnClickListener(this);
        rbMainDynamic.setOnClickListener(this);
        rbMainMessage.setOnClickListener(this);
        rbMainMy.setOnClickListener(this);
    }

    private void initData() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        homeFragment = new HomeFragment();


        ft.add(R.id.framelayout_main, homeFragment);
        ft.commit();
    }


    /**
     * 隐藏没点击的fragment
     */
    private void goFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (refundFragment != null) {
            ft.hide(refundFragment);
        }
        if (updateFragment != null) {
            ft.hide(updateFragment);
        }
        if (myFragment != null) {
            ft.hide(myFragment);
        }
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            //首页
            case R.id.rb_main_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.framelayout_main, homeFragment);
                }
                goFragment();
                ft.show(homeFragment);
                break;
            //动态
            case R.id.rb_main_dynamic:
                if (refundFragment == null) {
                    refundFragment = new RefundFragment();
                    ft.add(R.id.framelayout_main, refundFragment);
                }
                goFragment();
                ft.show(refundFragment);
                break;
            //消息
            case R.id.rb_main_message:
                if (updateFragment == null) {
                    updateFragment = new UpdateFragment();
                    ft.add(R.id.framelayout_main, updateFragment);
                }
                goFragment();
                ft.show(updateFragment);
                break;
            //我的
            case R.id.rb_main_my:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    ft.add(R.id.framelayout_main, myFragment);
                }
//                if (modelFragment == null) {
//                    modelFragment = new ModelFragment();
//                    ft.add(R.id.framelayout_main, modelFragment);
//                }
                goFragment();
                ft.show(myFragment);
                break;

        }
        ft.commit();
    }

    /**
     * 双击退出程序
     */
    private int time = 1;

    private void getTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                    time = 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!homeFragment.isVisible()) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                goFragment();
                ft.show(homeFragment);
                ft.commit();
                rbMainHome.setChecked(true);

                return false;
            }
            if (time == 1) {
                Toast.makeText(MainActivity.this, "再次点击退出程序", Toast.LENGTH_LONG).show();
                time += 1;
                getTime();
                return false;
            } else {
                AppManager.finishAllActivity();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
