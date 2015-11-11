package com.example.administrator.xiaobaicookbook.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.xiaobaicookbook.R;

/**
 * Created by Jason on 2015-11-11.
 */
public class MutipleChoiceAdapter extends BaseAdapter {
    // 填充数据的list
    private String[] list;
    // 用来控制CheckBox的选中状况
    private SparseBooleanArray isSelected;
    // 用来导入布局
    private LayoutInflater inflater;

    public MutipleChoiceAdapter(String[] list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        isSelected = new SparseBooleanArray();
        // 初始化数据
        initData();
    }

    // 初始化isSelected的数据
    private void initData() {
        for (int i = 0; i < list.length; i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 获得ViewHolder对象
            holder = new ViewHolder();
            // 导入布局并赋值给convertview
            convertView = inflater.inflate(R.layout.custom_mutiplechoice_view_list_item, null);
            holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
            holder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
            // 为view设置标签
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }
        // 设置list中TextView的显示
        holder.tv.setText(list[position]);
        // 根据isSelected来设置checkbox的选中状况
        holder.cb.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public SparseBooleanArray getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(SparseBooleanArray isSelected) {
        this.isSelected = isSelected;
    }

    public static class ViewHolder {
        TextView tv;
        public CheckBox cb;
    }
}
