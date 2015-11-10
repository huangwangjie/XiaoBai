package com.example.administrator.phonewifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class WifiActivity extends AppCompatActivity {
    private WifiManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        manager= (WifiManager) getSystemService(WIFI_SERVICE);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.openWifi:
            manager.setWifiEnabled(true);
                Toast.makeText(this,"当前网卡状态："+manager.getWifiState(),
                        Toast.LENGTH_LONG).show();
            break;
            case R.id.closeWifi:
                manager.setWifiEnabled(false);
                Toast.makeText(this,"当前网卡状态："+manager.getWifiState(),
                        Toast.LENGTH_LONG).show();
            break;
            case R.id.checkWifi:
                TextView txt= (TextView) findViewById(R.id.txt);
                txt.setText("");
                List<ScanResult> scanResults=manager.getScanResults();
                for(ScanResult s:scanResults){
                    txt.append(s.SSID+"\n");
                    //www.github.com可下载点击链接wifi。里面有很多源代码
                }
            break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wifi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
