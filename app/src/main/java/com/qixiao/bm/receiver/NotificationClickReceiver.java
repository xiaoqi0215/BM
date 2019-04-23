package com.qixiao.bm.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qixiao.bm.activity.MessageActivity;
import com.qixiao.bm.activity.SendSmsActivity;

public class NotificationClickReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //用这个方法实现点击notification后的事件  不知为何不能自动清掉已点击的notification  故自己手动清就ok了
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            int notificationId = intent.getIntExtra("notificationId", -1);
            notificationManager.cancel(notificationId);
        }

        Intent openMessage = new Intent(context, SendSmsActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(openMessage);
        //        EventBus.getDefault().post(new MessageEvent(context.getString(R.string.to_message), "notification"));


        //取消所有通知栏消息（先写在这里，需要用到时再用）
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }
}