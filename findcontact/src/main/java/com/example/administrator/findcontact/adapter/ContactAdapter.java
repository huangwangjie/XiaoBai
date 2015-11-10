package com.example.administrator.findcontact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.findcontact.R;
import com.example.administrator.findcontact.pojo.Contact;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Jason on 2015-11-7.
 */
public class ContactAdapter extends BaseAdapter {
    private List<Contact> contacts;
    private LayoutInflater inflater;

    public ContactAdapter(Context context,List<Contact> contacts) {
        this.contacts = contacts;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contact_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact contact = contacts.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvMobile.setText(contact.getMobile());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tvName)
        TextView tvName;
        @InjectView(R.id.tvMobile)
        TextView tvMobile;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
