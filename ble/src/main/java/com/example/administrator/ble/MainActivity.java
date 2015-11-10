package com.example.administrator.ble;

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
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter bluetoothAdapter;
    private TextView tvResult;
    private static final int requestEnable_bt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.tvResult);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            String name = bluetoothAdapter.getName().toString();
            String address = bluetoothAdapter.getAddress().toString();
            tvResult.setText(name);
            tvResult.append("\n" + address);
            //bluetoothAdapter.enable();
            if (!bluetoothAdapter.isEnabled()) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, requestEnable_bt);
            } else {
                Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
                for (BluetoothDevice d : devices) {
                    tvResult.append("\n" + d.getName() + "," + d.getAddress());
                }


            }
        } else {
            Toast.makeText(getApplicationContext(), "不支持蓝牙", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestEnable_bt) {
            if (resultCode == RESULT_OK) {
                //开启成功
            } else {
                //开启失败
            }
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                bluetoothAdapter.startDiscovery();
                break;
        }
        return true;
    }

    private Set<BluetoothDevice> devices = new HashSet<>();
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice device=intent.getParcelableExtra(
                    BluetoothDevice.EXTRA_DEVICE
            );
            devices.add(device);
            tvResult.setText("");
            for(BluetoothDevice bd:devices){
                tvResult.append(bd.getName()+"\n");
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver,intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }
}
