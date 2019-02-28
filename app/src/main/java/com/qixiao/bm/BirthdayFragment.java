package com.qixiao.bm;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.qixiao.bm.base.BaseFragment;
import com.qixiao.bm.widget.MyActionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
        mFriendRv.addItemDecoration(new DividerItemDecoration(mContext,1));
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
