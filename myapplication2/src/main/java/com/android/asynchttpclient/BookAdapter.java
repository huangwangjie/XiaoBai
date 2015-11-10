package com.android.asynchttpclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Song on 2015/10/12.
 */
public class BookAdapter extends BaseAdapter {
    private List<Book> books;
    private Context context;
    private LayoutInflater inflater;

    public BookAdapter(Context context, List<Book> books) {
        this.books = books;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.book_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book=books.get(position);
        holder.isbn.setText(book.getIsbn());
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());

        // 毕加索加载图片
        // with(上下文).load(图片路径URL).into(指定的ImageView)
        Picasso.with(context).load(book.getImage()).into(holder.image);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.image)
        ImageView image;
        @InjectView(R.id.title)
        TextView title;
        @InjectView(R.id.isbn)
        TextView isbn;
        @InjectView(R.id.author)
        TextView author;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
