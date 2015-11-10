package com.example.administrator.sensordemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imgShake;
    private ShakeListener shakeListener;

    private int[]imgs={R.drawable.brick,R.drawable.calendar,
    R.drawable.eoemarket,R.drawable.terminater,R.drawable.whitesociety};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgShake= (ImageView) findViewById(R.id.imgShake);
        shakeListener=new ShakeListener(this);
        shakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
               // Log.v("MainActivity","摇一摇");
                Random random=new Random();
                int i=random.nextInt(5);
                imgShake.setImageResource(imgs[i]);
            }
        });



    }

    @Override
    protected void onStop() {
        super.onStop();
        if(shakeListener!=null){
            shakeListener.stop();
        }
    }

    public void compass(View view){
        startActivity(new Intent(getApplicationContext(),CompassActivity.class));
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
