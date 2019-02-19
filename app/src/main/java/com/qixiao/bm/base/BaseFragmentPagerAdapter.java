package com.qixiao.bm.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by linfeizheng on 2017/8/18.
 */

public class BaseFragmentPagerAdapter extends FragmentStatePagerAdapter {

    protected List<Fragment> fragments;
    private Fragment mCurrentFragment;

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments != null) {
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    public void setData(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }


    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

}
