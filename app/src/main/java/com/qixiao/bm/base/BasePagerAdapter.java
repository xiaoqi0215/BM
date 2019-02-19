package com.qixiao.bm.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by linfeizheng on 2017/4/18.
 */

public abstract class BasePagerAdapter<T> extends PagerAdapter {

    protected Context mContext;
    protected List<T> list;

    public BasePagerAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null || list.size() == 0) {
            return 0;
        } else if (list.size() == 1) {
            return 1;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public int getOriginalCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public abstract Object instantiateItem(ViewGroup container, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}