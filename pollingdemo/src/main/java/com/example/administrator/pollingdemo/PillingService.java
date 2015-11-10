package com.example.administrator.pollingdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PillingService extends Service {
    private static final String TAG = "MainActivity";
    public static final String ACTION = "android.intent.action.PillingService";

    public PillingService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new PollingThread().start();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    int count = 0;

    private class PollingThread extends Thread {
        @Override
        public void run() {
            Log.v(TAG, "访问数据！");
            String url = "http://192.168.43.162:8087/androidcloud/PushSmServlet";
            RequestQueue requestQueue = Volley.newRequestQueue(PillingService.this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        int state = jsonObject.getInt("state");
                        String content = jsonObject.getString("content");
                        String date = jsonObject.getString("date");
                        String number = jsonObject.getString("number");
                        showNotification(state, content, date, number, count++);
                    } catch (JSONException e) {
                        Log.v(TAG, "数据出错！");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            requestQueue.add(jsonObjectRequest);
        }
    }

    private void showNotification(int state, String content, String date, String number, int count) {
        //Log.v(TAG,String.format("%d %s %s %s",count,content,date,number));
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder = new NotificationCompat.Builder(this).
                setSmallIcon(android.R.drawable.stat_notify_missed_call).
                setContentTitle(count + " " + number).
                setContentText(content + " " + date).
                setContentIntent(pi);
        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND;
        manager.notify(0, notification);
        //多条通知
        //manager.notify(count,notification);

    }
}
