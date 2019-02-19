package com.qixiao.bm.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Cai, Jiajun
 * @classame BaseViewPageAdapter
 * @description 简单版Viewpager+tablayout适配器
 * @date 2018/11/26
 */
public class BaseViewPageAdapter extends FragmentPagerAdapter {
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private Fragment currentFragment;
    private int currentPosition = -1;

    public int getCurrentPosition() {
        return currentPosition;
    }


    public Fragment getCurrentFragment() {
        return currentFragment;
    }


    public BaseViewPageAdapter(FragmentManager fm, List<String> titleList, List<Fragment> fragmentList) {
        super(fm);
        this.titleList = titleList;
        this.fragmentList = fragmentList;
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
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentFragment = (Fragment) object;
        currentPosition = position;
        super.setPrimaryItem(container, position, object);
    }

}
