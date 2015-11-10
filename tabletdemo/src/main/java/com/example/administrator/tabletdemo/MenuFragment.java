package com.example.administrator.tabletdemo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment
        implements AdapterView.OnItemClickListener {
    private ListView menuList;
    private ArrayAdapter<String> adapter;
    private String[] menuItem={"Sound","Display"};
    private boolean isTwoPage;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        adapter=new ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1,menuItem);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu,container,false);
        menuList= (ListView) view.findViewById(R.id.menu_list);
        menuList.setAdapter(adapter);
        menuList.setOnItemClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.details_layout)!=null){
            isTwoPage=true;
        }else {
            isTwoPage=false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(isTwoPage){
            Fragment fragment=null;
            if(position==0){
                fragment=new SoundFragment();
            }else if(position==1){
                fragment=new DisplayFragment();
            }
            getFragmentManager().beginTransaction().replace(R.id.details_layout,fragment).commit();
        }else {
            Intent intent=null;
            if(position==0){
                intent=new Intent(getActivity(),SoundActivity.class);
            }else if(position==1){
                intent=new Intent(getActivity(),DisplayActivity.class);
            }
            startActivity(intent);
        }

    }
}
