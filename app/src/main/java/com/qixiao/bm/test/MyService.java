package com.qixiao.bm.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private final static String UPDATE_UI="android.xiaoqi.ui";

    public MyService() {
    }

    // 周期性更新 widget 的周期
    private static final int UPDATE_TIME = 1000;

    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG","MyService:onCreate");
        // 每经过指定时间，发送一次广播
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Intent updateIntent = new Intent(UPDATE_UI);
                sendBroadcast(updateIntent);
            }
        };
        mTimer.schedule(mTimerTask, 1000, UPDATE_TIME);
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimerTask.cancel();
        mTimer.cancel();
    }
}
