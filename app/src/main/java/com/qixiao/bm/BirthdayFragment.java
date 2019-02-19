package com.qixiao.bm;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
        mFriendRv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setStatus(int status) {

    }

    @Override
    public void tologin(String msg) {

    }
}
