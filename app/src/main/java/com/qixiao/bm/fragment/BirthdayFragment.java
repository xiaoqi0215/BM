package com.qixiao.bm.fragment;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qixiao.bm.BirthdayListBean;
import com.qixiao.bm.R;
import com.qixiao.bm.adapter.BirthdayAdapter;
import com.qixiao.bm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BirthdayFragment extends BaseFragment {

    @BindView(R.id.rv_birthday_friend)
    RecyclerView mFriendRv;

    BirthdayAdapter mAdapter;

    List<BirthdayListBean> data;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_birthday;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView(View rootView) {


        data = new ArrayList<>();
        for(int i = 0 ;i<5;i++){
            BirthdayListBean bean  = new BirthdayListBean();
            data.add(bean);
        }
        mAdapter = new BirthdayAdapter(getActivity(),data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mFriendRv.setLayoutManager(linearLayoutManager);
        mFriendRv.addItemDecoration(new BirthdayAdapter.SpaceItemDecoration(15));
        mFriendRv.setAdapter(mAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }

//    @OnClick({R.id.iv_birthday_tab_add})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.iv_birthday_tab_add:
//
//                showPopWindow();
//        }
//    }



}
