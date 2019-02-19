package com.qixiao.bm.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;



import java.util.ArrayList;
import java.util.List;

/**
 * @author qingmanyu
 * @desc
 * @created 2018/7/5 17:55
 */
public class BaseApplication extends Application {
    public static List<Activity> activities = new ArrayList<>();

    /**
     * application上下文
     **/
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }



    /**
     * 添加Activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 移除Activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }


    /**
     * 结束指定类名的Activity以外的Activity
     */
    public static void finishOtherActivity(Class<?> c) {
        if (activities != null) {
            for (Activity activity : activities) {
                if (activity.getClass() != c) {
                    activity.finish();
                }
            }
        }

    }

    /**
     * 退出应用
     */
    public static void exit() {

        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }

    }

    /**
     * 退出应用
     */
    public static void exitWithKill() {

        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        // // 杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    public static String getVersion() {
        try {
            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext()
                    .getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
