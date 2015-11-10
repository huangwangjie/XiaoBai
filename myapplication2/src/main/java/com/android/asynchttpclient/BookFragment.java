package com.android.asynchttpclient;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;


public class BookFragment extends Fragment {
    @InjectView(R.id.listView)
    ListView listView;

    private List<Book> data;
    private BookAdapter adapter;

    public BookFragment() {
        // Required empty public constructor
    }

    public void loadData(List<Book> data) {
        this.data = data;
        adapter = new BookAdapter(getActivity(), this.data);
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(int position) {
        Book book = data.get(position);
        Toast.makeText(getActivity(), book.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


}
