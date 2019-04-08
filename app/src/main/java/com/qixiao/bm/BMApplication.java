package com.qixiao.bm;

import android.content.SharedPreferences;
import android.util.Log;

import com.qixiao.bm.Utils.SharedPreferencesUtils;
import com.qixiao.bm.base.BaseApplication;

import cn.jpush.android.api.JPushInterface;

public class BMApplication extends BaseApplication {
    private   SharedPreferencesUtils msp;
    private static  BMApplication instance = null;

    public BMApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
        instance = this;
        initLogin();
    }

    private void initLogin() {
        msp = new SharedPreferencesUtils(this);
    }
}
