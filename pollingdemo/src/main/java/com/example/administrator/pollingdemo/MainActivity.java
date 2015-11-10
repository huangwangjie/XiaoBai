package com.example.administrator.pollingdemo;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @author Jason
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PollingUtils.startPollingService(this, 20, PillingService.class, PillingService.ACTION);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PollingUtils.stopPollingService(this,PillingService.class,PillingService.ACTION);
    }

}
