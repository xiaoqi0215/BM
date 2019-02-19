package com.qixiao.bm.test;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.qixiao.bm.MainActivity;
import com.qixiao.bm.R;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static  Set set = new HashSet();
    private final static String UPDATE_UI="android.xiaoqi.ui";

    static int index;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        if (UPDATE_UI.equals(action)){
            Log.e("TAG",UPDATE_UI);
            updateAllAppWidgets(context,AppWidgetManager.getInstance(context),set);
        }else if (intent.hasCategory(Intent.CATEGORY_ALTERNATIVE)){
            Log.e("TAG","CATEGORY_ALTERNATIVE");
            index = 0;
            updateAllAppWidgets(context,AppWidgetManager.getInstance(context),set);
        }
    }

    private void updateAllAppWidgets(Context context, AppWidgetManager appWidgetManager, Set set) {
        int appId;
        Iterator it = set.iterator();

        index++;
        while (it.hasNext()){
            appId = ((Integer)it.next()).intValue();
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            views.setTextViewText(R.id.tv, index+"");
            views.setOnClickPendingIntent(R.id.bt_reset,getOpenPendingIntent(context));
            views.setOnClickPendingIntent(R.id.bt_open,getResetPendingIntent(context));
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appId, views);
        }
    }

    private PendingIntent getResetPendingIntent(Context context) {
    Intent intent = new Intent(context,NewAppWidget.class);
    intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
    return pendingIntent;
    }

    private PendingIntent getOpenPendingIntent(Context context) {
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        return  pendingIntent;
    }


    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            set.add(Integer.valueOf(appWidgetId));
        }
    }

    @Override
    public void onEnabled(Context context) {
      Intent intent = new Intent(context,MyService.class);
        Log.e("TAG","onEnabled");
      context.startService(intent);
      super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        Intent intent = new Intent(context,MyService.class);
        context.stopService(intent);
        super.onDisabled(context);
    }



}

