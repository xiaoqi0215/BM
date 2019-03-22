package com.qixiao.bm;

import android.content.SharedPreferences;

import com.qixiao.bm.Utils.SharedPreferencesUtils;
import com.qixiao.bm.base.BaseApplication;

public class BMApplication extends BaseApplication {
    private   SharedPreferencesUtils msp;
    private static  BMApplication instance = null;

    public BMApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLogin();
    }

    private void initLogin() {
        msp = new SharedPreferencesUtils(this);

    }
}
