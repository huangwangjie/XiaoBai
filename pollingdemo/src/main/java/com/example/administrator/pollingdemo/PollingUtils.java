package com.example.administrator.pollingdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by Jason on 2015-10-15.
 */
public class PollingUtils {
    public static void startPollingService(Context context,int seconds,
    Class<?> cls,String action){
        //获得AlarmManager系统服务（定时管理器）
        AlarmManager manager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //包装要执行
        Intent intent=new Intent(context,cls);
        intent.setAction(action);
        PendingIntent pi=PendingIntent.getService(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        long triggerAtTime= SystemClock.elapsedRealtime();
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME,triggerAtTime,seconds*1000,pi);

    }

    public static void stopPollingService(Context context,
                                           Class<?> cls,String action){
        AlarmManager manager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //包装要执行
        Intent intent=new Intent(context,cls);
        intent.setAction(action);
        PendingIntent pi=PendingIntent.getService(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        manager.cancel(pi);

    }
}
