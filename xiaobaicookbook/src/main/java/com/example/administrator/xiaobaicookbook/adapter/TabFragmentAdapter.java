package com.example.administrator.xiaobaicookbook.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.administrator.xiaobaicookbook.fragment.NothingFragment;
import com.example.administrator.xiaobaicookbook.fragment.WantToEatFragment;

/**
 * Created by Jason on 2015-11-10.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs= new String[2];;

    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NothingFragment();
            case 1:
                return new WantToEatFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
