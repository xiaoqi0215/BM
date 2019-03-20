package com.qixiao.bm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qixiao.bm.R;
import com.qixiao.bm.adapter.RVDynamicAdapter;
import com.qixiao.bm.base.BaseFragment;
import com.qixiao.bm.widget.NineLayout;

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
    public void onStart() {
        super.onStart();
        Log.e("TAG","DynamicFragment:onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
     //   mAdapter.notifyDataSetChanged();
        Log.e("TAG","DynamicFragment:onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG","DynamicFragment:onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("TAG","DynamicFragment:onStop");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("TAG","DynamicFragment:onViewCreated");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.e("TAG","DynamicFragment:onSaveInstanceState");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e("TAG","DynamicFragment:onViewStateRestored");
    }

    @Override
    protected void initView(View rootView) {
    mAdapter = new RVDynamicAdapter(getActivity());
    mData = new ArrayList<>();
    rvDynamic.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i=0;i<5;i++){
            Object object = new Object();
            mData.add(object);
        }
        //rvDynamic.addItemDecoration(new RVDynamicAdapter.SpaceItemDecoration(20));
    rvDynamic.setAdapter(mAdapter);
        mAdapter.setData(mData);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }



}
