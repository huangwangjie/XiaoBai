package com.example.administrator.taobao;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.taobao.adapter.ProductAdapter;
import com.example.administrator.taobao.pojo.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProductFragment extends Fragment {
    @InjectView(R.id.listView)
    ListView listView;
    private ProductAdapter adapter;
    private ArrayList<Product> data;


    public ProductFragment() {
        // Required empty public constructor
    }

    public void loadData(ArrayList<Product> list) {
        data = list;
        adapter = new ProductAdapter(getActivity(), data);
        listView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


}
