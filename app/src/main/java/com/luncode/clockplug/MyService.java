package com.luncode.clockplug;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private Timer timer;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                updateview();
               handler.postDelayed(this,1000);
            }
        };
        runnable.run();
    }

    public void updateview(){
        Date date = new Date();
        String temp = format.format(date);
        RemoteViews views = new RemoteViews(getPackageName(),R.layout.clock_widget);
        views.setTextViewText(R.id.clock_text,temp);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        ComponentName componentName = new ComponentName(getApplicationContext(),ClockWidget.class);
        appWidgetManager.updateAppWidget(componentName,views);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
