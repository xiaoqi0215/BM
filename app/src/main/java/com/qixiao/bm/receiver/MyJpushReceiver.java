package com.qixiao.bm.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.qixiao.bm.R;
import com.qixiao.bm.Utils.DateUtil;
import com.qixiao.bm.activity.MessageActivity;
import com.qixiao.bm.bean.JpushBean;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyJpushReceiver extends BroadcastReceiver {

    private static final String TAG = "JIGUANG-Example";

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        try {
            Bundle bundle = intent.getExtras();
            Log.d(TAG, "[MyJpushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Log.d(TAG, "[MyJpushReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                Log.d(TAG, "[MyJpushReceiver] 接收到推送下来的自定义消息: " + message);
                processCustomMessage(context, message);

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Log.d(TAG, "[MyJpushReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                Log.d(TAG, "[MyJpushReceiver] 接收到推送下来的通知的ID: " + notifactionId);

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Log.d(TAG, "[MyJpushReceiver] 用户点击打开了通知");

                //打开自定义的Activity
                Intent clickIntent = new Intent(context, MessageActivity.class);
                clickIntent.putExtras(bundle);
                clickIntent.putExtra("MessageFrom", "通知栏");
                clickIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(clickIntent);

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Log.d(TAG, "[MyJpushReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Log.w(TAG, "[MyJpushReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Log.d(TAG, "[MyJpushReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception ignored) {

        }

    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.get(key));
            }
        }
        return sb.toString();
    }

    //send msg to TestActivity

    private void processCustomMessage(Context context, String message) {
        //        showNotification(context, bundle);
        try {
            //解析成功，说明是生日管家的推送消息
            JpushBean jpushBean = new Gson().fromJson(message, JpushBean.class);

            EventBus.getDefault().post(jpushBean);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

    }


    /**
     * 自定义notification样式
     */

    private void showNotification(Context context, Bundle bundle) {

        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String messageType = "新消息来了";

        String channelID = "110";
        String channelName = "消息通知";
        int notificationId = (int) System.currentTimeMillis() / 10000;

        Intent intent = new Intent(mContext, MessageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, channelID);
        NotificationManager notifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.tv_noti_time, DateUtil.format3(System.currentTimeMillis()));
        remoteViews.setTextViewText(R.id.tv_noti_content, message == null ? "" : message);
        remoteViews.setTextViewText(R.id.tv_noti_type, messageType);

        builder.setAutoCancel(true)
                .setSmallIcon(R.mipmap.birthday_tab_add)
                .setDefaults(Notification.DEFAULT_ALL)
                //不设置的话，小米系统会放到不重要通知
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCustomBigContentView(remoteViews)
                .setContentTitle(messageType)
                .setContentText(message == null ? "" : message)
                .setContentIntent(pendingIntent);

        if (notifyManager != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
                notifyManager.createNotificationChannel(channel);
                builder.setChannelId(channelID);
            }
            //通过builder.build()方法生成Notification对象,并发送通知，相同id只会显示一条
            notifyManager.notify(notificationId, builder.build());
        }
    }
}
