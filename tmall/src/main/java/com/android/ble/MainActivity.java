package com.android.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

/**
 * Android 4.3(API Level 18) 引入低功耗蓝牙 BLE = Bluetooth Low Energy
 * 蓝牙应用范围:距离传感器 / 心率监视器 / 健身设备
 * 传输距离: 15 Metre
 * <p/>
 * 蓝牙操作:
 * 1.授权
 * 2.定义并实例化蓝牙适配器
 * 3.获得本机的名称 & 地址
 * 4.蓝牙是否开启
 * 5.搜索附近蓝牙
 */
public class MainActivity extends AppCompatActivity {
    // 蓝牙适配器
    private BluetoothAdapter bluetoothAdapter;
    private TextView tvResult;
    private static final int REQUEST_ENABLE_BT = 77;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);

        // 实例化蓝牙适配器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            // 获得本机的名称 & 地址
            String name = bluetoothAdapter.getName();
            String address = bluetoothAdapter.getAddress();

            tvResult.setText(name);
            tvResult.append("\n" + address);

            // 直接启动 (若蓝牙关闭则抛异常)
            // bluetoothAdapter.enable();

            // 4.蓝牙是否开启
            if (!bluetoothAdapter.isEnabled()) {
                // 未开启时询问是否开启蓝牙
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_ENABLE_BT);
            } else {
                // 扫描已匹配的蓝牙设备
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                if (pairedDevices.size() > 0) {
                    for (BluetoothDevice device : pairedDevices) {
                        tvResult.append("\n\n" + device.getName() + "," +
                                device.getAddress());
                    }
                }
            }
        } else {
            // Device does not support Bluetooth
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                // 开启成功
            } else {
                // 开启失败
            }
        }
    }

    // 5.搜索附近蓝牙 (权限: BLUETOOTH_ADMIN)---------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                if (bluetoothAdapter != null) {
                    // 5.1 开始搜索
                    bluetoothAdapter.startDiscovery();
                }
                break;
        }
        return true;
    }

    // 5.3 定义存储发现蓝牙设备的集合
    private Set<BluetoothDevice> devices = new HashSet<>();

    // 5.2 定义广播用于接收蓝牙搜索后的数据
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 搜索发现的蓝牙设备
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                BluetoothDevice device = intent.getParcelableExtra(
                        BluetoothDevice.EXTRA_DEVICE);
                devices.add(device);

                // 显示搜索到的蓝牙设备
                tvResult.setText("");
                for (BluetoothDevice bd : devices) {
                    tvResult.append(bd.getName() + "\n");
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        // 5.4 注册广播接收器
        IntentFilter filter = new IntentFilter();
        // 发现了蓝牙设备
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 5.5 注销广播接收器
        unregisterReceiver(receiver);
    }
}

