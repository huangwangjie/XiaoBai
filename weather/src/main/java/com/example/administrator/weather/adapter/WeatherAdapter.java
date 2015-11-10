package com.example.administrator.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.weather.R;
import com.example.administrator.weather.pojo.Weather;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Jason on 2015-11-7.
 */
public class WeatherAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Weather> weathers;

    public WeatherAdapter(Context context, List<Weather> weathers) {
        this.context = context;
        this.weathers=weathers;
        inflater= LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return weathers.size();
    }

    @Override
    public Object getItem(int position) {
        return weathers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_weather, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Weather weather = weathers.get(position);
        holder.tvCityName.setText(weather.getCityName());
        holder.tvLastUpdate.setText(weather.getLastUpdate());
        holder.tvText.setText(weather.getText());
        holder.tvHigh.setText(weather.getHigh());
        holder.tvLow.setText(weather.getLow());
        holder.tvCop.setText(weather.getCop());
        holder.tvWind.setText(weather.getWind());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tvCityName)
        TextView tvCityName;
        @InjectView(R.id.tvLastUpdate)
        TextView tvLastUpdate;
        @InjectView(R.id.tvText)
        TextView tvText;
        @InjectView(R.id.tvHigh)
        TextView tvHigh;
        @InjectView(R.id.tvLow)
        TextView tvLow;
        @InjectView(R.id.tvCop)
        TextView tvCop;
        @InjectView(R.id.tvWind)
        TextView tvWind;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
