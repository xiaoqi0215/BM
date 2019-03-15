package com.qixiao.bm.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qixiao.bm.R;
import com.qixiao.bm.adapter.RVDynamicAdapter;
import com.qixiao.bm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class DynamicFragment extends BaseFragment {

    @BindView(R.id.rv_dynamic)
    RecyclerView rvDynamic;
    RVDynamicAdapter mAdapter;
    List<Object> mData;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void createPresenter() {


    }

    @Override
    protected void initView(View rootView) {
    mAdapter = new RVDynamicAdapter(getActivity());
    mData = new ArrayList<>();
    rvDynamic.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i=0;i<3;i++){
            Object object = new Object();
            mData.add(object);
        }
        //rvDynamic.addItemDecoration(new RVDynamicAdapter.SpaceItemDecoration(20));
    mAdapter.setData(mData);
    rvDynamic.setAdapter(mAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }



}
