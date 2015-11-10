package com.example.administrator.phonewifi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvPhone;
    private TelephonyManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String deviceID = manager.getDeviceId();
        String networkOperator = manager.getNetworkOperator();
        String networkOperatorName = manager.getNetworkOperatorName();
        String cellLocation = manager.getCellLocation() != null ? manager.getCellLocation().toString() : "未知位置";
        String simCountryIso = manager.getSimCountryIso();
        String simSerialNumber = manager.getSimSerialNumber();
        String deviceSV =
                manager.getDeviceSoftwareVersion() != null ? manager.getDeviceSoftwareVersion() : "未知";
        String netWorkTypeName = getNetWorkTypeName(manager.getNetworkType());
        tvPhone.setText("");
        tvPhone.append("设备ID：" + deviceID + "\n");
        tvPhone.append("设备系统平台的版本：" + deviceSV + "\n");
        tvPhone.append("网络运营商代号：" + networkOperator + "\n");
        tvPhone.append("网络运营商名称：" + networkOperatorName + "\n");
        tvPhone.append("设备所在位置：" + cellLocation + "\n");
        tvPhone.append("SIM卡的国别：" + simCountryIso + "\n");
        tvPhone.append("SIM卡的序列号：" + simSerialNumber + "\n");
        tvPhone.append("设备的网络类型：" + netWorkTypeName);
    }

    private String getNetWorkTypeName(int networkType) {
        switch (networkType) {
            case NETWORK_TYPE_GPRS:
                return "GPRS";
            case NETWORK_TYPE_EDGE:
                return "EDGE";
            case NETWORK_TYPE_UMTS:
                return "UMTS";
            case NETWORK_TYPE_HSDPA:
                return "HSDPA";
            case NETWORK_TYPE_HSUPA:
                return "HSUPA";
            case NETWORK_TYPE_HSPA:
                return "HSPA";
            case NETWORK_TYPE_CDMA:
                return "CDMA";
            case NETWORK_TYPE_EVDO_0:
                return "CDMA - EvDo rev. 0";
            case NETWORK_TYPE_EVDO_A:
                return "CDMA - EvDo rev. A";
            case NETWORK_TYPE_EVDO_B:
                return "CDMA - EvDo rev. B";
            case NETWORK_TYPE_1xRTT:
                return "CDMA - 1xRTT";
            case NETWORK_TYPE_LTE:
                return "LTE";
            case NETWORK_TYPE_EHRPD:
                return "CDMA - eHRPD";
            case NETWORK_TYPE_IDEN:
                return "iDEN";
            case NETWORK_TYPE_HSPAP:
                return "HSPA+";
            case NETWORK_TYPE_GSM:
                return "GSM";
            case NETWORK_TYPE_TD_SCDMA:
                return "TD_SCDMA";
            case NETWORK_TYPE_IWLAN:
                return "IWLAN";
            default:
                return "UNKNOWN";
        }

    }

    /**
     * Network type is unknown
     */
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    /**
     * Current network is GPRS
     */
    public static final int NETWORK_TYPE_GPRS = 1;
    /**
     * Current network is EDGE
     */
    public static final int NETWORK_TYPE_EDGE = 2;
    /**
     * Current network is UMTS
     */
    public static final int NETWORK_TYPE_UMTS = 3;
    /**
     * Current network is CDMA: Either IS95A or IS95B
     */
    public static final int NETWORK_TYPE_CDMA = 4;
    /**
     * Current network is EVDO revision 0
     */
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    /**
     * Current network is EVDO revision A
     */
    public static final int NETWORK_TYPE_EVDO_A = 6;
    /**
     * Current network is 1xRTT
     */
    public static final int NETWORK_TYPE_1xRTT = 7;
    /**
     * Current network is HSDPA
     */
    public static final int NETWORK_TYPE_HSDPA = 8;
    /**
     * Current network is HSUPA
     */
    public static final int NETWORK_TYPE_HSUPA = 9;
    /**
     * Current network is HSPA
     */
    public static final int NETWORK_TYPE_HSPA = 10;
    /**
     * Current network is iDen
     */
    public static final int NETWORK_TYPE_IDEN = 11;
    /**
     * Current network is EVDO revision B
     */
    public static final int NETWORK_TYPE_EVDO_B = 12;
    /**
     * Current network is LTE
     */
    public static final int NETWORK_TYPE_LTE = 13;
    /**
     * Current network is eHRPD
     */
    public static final int NETWORK_TYPE_EHRPD = 14;
    /**
     * Current network is HSPA+
     */
    public static final int NETWORK_TYPE_HSPAP = 15;
    /**
     * Current network is GSM {@hide}
     */
    public static final int NETWORK_TYPE_GSM = 16;
    /**
     * Current network is TD_SCDMA {@hide}
     */
    public static final int NETWORK_TYPE_TD_SCDMA = 17;
    /**
     * Current network is IWLAN {@hide}
     */
    public static final int NETWORK_TYPE_IWLAN = 18;

    public void onClick(View view) {
        startActivity(new Intent(this, WifiActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
