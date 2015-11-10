package com.android.lbs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 位置服务(Location-Based Services,LBS)又称定位服务或基于位置的服务,融合了GPS定位、移动通信、导航等多种技术,提供了与空间位置相关的综合应用服务
 * <p/>
 * Android定位系统主要有2个来源:GPS定位和Network定位(基于cell和WiFi热点定位)
 * <p/>
 * 基于位置的服务发展迅速，已涉及到商务、医疗、工作和生活的各个方面，为用户提供定位、追踪和敏感区域警告等一系列服务
 */
public class MainActivity extends AppCompatActivity implements LocationListener {
    private TextView tvLbs;

    private LocationManager lm;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLbs = (TextView) findViewById(R.id.tvLbs);
        // 获得位置服务管理类
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        // api 23: 检查权限
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //Location对象中，包含了可以确定位置的信息，如经度、纬度和速度等
        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if (location != null) {
            display(location);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        /**
         * requestLocationUpdates()，可以根据位置的距离变化和时间间隔设定产生
         * 位置改变事件的条件，
         * 这样可以避免因微小的距离变化而产生大量的位置改变事件
         * 第1个参数是定位的方法，GPS定位或网络定位
         * 第2个参数是产生位置改变事件的时间间隔，单位为毫秒
         * 第3个参数是距离条件，单位是米
         * 第4个参数是回调函数，在满足条件后的位置改变事件的处理函数
         */
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                1000, 0, this);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 0, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // 移除位置更新的监听事件
        lm.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        // 位置发生改变
        display(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        /**
         * 提供定位功能的硬件的状态改变时被调用，
         * 如从不可获取位置信息状态到可以获取位置信息的状态，反之亦然
         */
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    // 显示位置信息的方法
    private void display(Location location) {
        float ac = location.getAccuracy();
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        double alt = location.getAltitude();
        float bear = location.getBearing();
        float speed = location.getSpeed();
        long time = location.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sdate = sdf.format(new Date(time));

        // long t = loc.getElapsedRealtimeNanos();
        String pro = location.getProvider();
        // loc.distanceTo(dest)

        tvLbs.setText("");
        tvLbs.append(String.format("精度=%f\n", ac));
        tvLbs.append(String.format("纬度=%f\n", lat));
        tvLbs.append(String.format("经度=%f\n", lon));
        tvLbs.append(String.format("海拔=%f\n", alt));
        tvLbs.append(String.format("方向=%f\n", bear));
        tvLbs.append(String.format("速度=%f\n", speed));
        tvLbs.append(String.format("时间=%s\n", sdate));
        tvLbs.append(String.format("提供=%s\n", pro));
    }

    public void onClick(View view) {
        startActivity(new Intent(this, ProximityAlertActivity.class));
    }

}
