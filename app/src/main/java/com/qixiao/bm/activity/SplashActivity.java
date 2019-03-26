package com.qixiao.bm.activity;

import android.view.View;

import com.qixiao.bm.R;
import com.qixiao.bm.base.BaseActivity;

import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {

//        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                    toActivity(MainActivity.class);
//                    finish();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        pool.execute(runnable);

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
        pool.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    toActivity(LoginActivity.class);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },0,TimeUnit.SECONDS);
    }

    @Override
    public void onClick(View v) {

    }
}
