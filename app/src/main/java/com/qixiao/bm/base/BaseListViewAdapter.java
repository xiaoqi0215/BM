package com.qixiao.bm.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListViewAdapter<T> extends ArrayAdapter<T> {

    protected Context mContext;
    protected List<T> mDataList;

    public BaseListViewAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
        this.mContext = context;
//        setData(objects);
    }

    /**
     * @param objects
     */
    public void setData(List<T> objects) {
        this.mDataList = objects;
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        if (this.mDataList == null) {
            mDataList = new ArrayList<T>();
        }
        mDataList.add(item);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return this.mDataList;
    }

    public void addData(List<T> obj) {
        if (this.mDataList == null) {
            mDataList = new ArrayList<T>();
        }

        this.mDataList.addAll(obj);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDataList != null) {
            return mDataList.size();
        }
        return 0;
    }

    public void removeItem(int position) {
        if (mDataList != null) {
            mDataList.remove(position);
            notifyDataSetChanged();
        }
    }

    @Override
    public T getItem(int position) {
        if (mDataList != null) {
            return mDataList.get(position);
        }
        return null;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

}
