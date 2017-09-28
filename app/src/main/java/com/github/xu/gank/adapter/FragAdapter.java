package com.github.xu.gank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xukankan on 2017/9/25.
 */

public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public FragAdapter(FragmentManager fm, List<Fragment> fragList) {
        super(fm);
        fragmentList = fragList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
