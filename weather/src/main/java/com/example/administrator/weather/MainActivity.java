package com.example.administrator.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.weather.adapter.WeatherAdapter;
import com.example.administrator.weather.pojo.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvCityName;
    private ListView listView;
    private WeatherAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCityName = (TextView) findViewById(R.id.tvCityName);
        listView = (ListView) findViewById(R.id.listView);
        parseJson();
    }

    private void parseJson() {
        try {
            InputStream in = getResources().getAssets().open("future.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String json = "";
            String line = null;
            while ((line = br.readLine()) != null) {
                json += line;
            }
            String title = "";
            List<Weather> listFuture = null;

            JSONObject jo = new JSONObject(json);
            JSONArray ja = jo.getJSONArray("weather");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject joWeather = ja.getJSONObject(i);
                String cityName = joWeather.getString("city_name");
                String lastUpdate = joWeather.getString("last_update");
                title = cityName + "  " + lastUpdate;

                JSONArray jaFuture = joWeather.getJSONArray("future");
                if (jaFuture.length() > 0) {
                    listFuture = new ArrayList<>();
                    for (int j = 0; j < jaFuture.length(); j++) {
                        JSONObject joFuture = jaFuture.getJSONObject(j);
                        Weather weather = new Weather();
                        weather.setDay(joFuture.getString("day"));
                        weather.setHigh(joFuture.getString("high"));
                        weather.setLow(joFuture.getString("low"));
                        weather.setCop(joFuture.getString("cop"));
                        weather.setWind(joFuture.getString("wind"));
                        weather.setText(joFuture.getString("text"));
                        listFuture.add(weather);
                    }
                }
            }
            tvCityName.setText(title);
            adapter = new WeatherAdapter(this, listFuture);
            Log.v("MainActivity",listFuture.toString());
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
