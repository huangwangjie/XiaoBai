package com.example.administrator.taobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.administrator.taobao.BitmapCache;
import com.example.administrator.taobao.R;
import com.example.administrator.taobao.pojo.Product;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Jason on 2015-10-24.
 */
public class ProductAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> list;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.product_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = list.get(position);

        holder.name.setText(product.getName());
        holder.createDate.setText(product.getCreateDate());
        holder.price.setText(product.getPrice().toString());

        RequestQueue mQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener =
                ImageLoader.getImageListener(holder.image,
                        R.drawable.empty_photo, R.mipmap.ic_launcher);
        imageLoader.get(product.getImage(), listener);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.image)
        ImageView image;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.createDate)
        TextView createDate;
        @InjectView(R.id.price)
        TextView price;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
