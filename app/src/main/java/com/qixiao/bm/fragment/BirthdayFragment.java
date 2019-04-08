package com.qixiao.bm.fragment;


import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qixiao.bm.BMApplication;
import com.qixiao.bm.BirthdayListBean;
import com.qixiao.bm.R;
import com.qixiao.bm.activity.AddFriendActivity;
import com.qixiao.bm.adapter.BirthdayAdapter;
import com.qixiao.bm.base.BaseFragment;
import com.qixiao.bm.widget.BMDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

public class BirthdayFragment extends BaseFragment {

    @BindView(R.id.rv_birthday_friend)
    RecyclerView mFriendRv;

    BirthdayAdapter mAdapter;

    List<BirthdayListBean> data;
    public  final static int  EDIT_FRIEND=100;

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

        mAdapter.setLongClickListener(new BirthdayAdapter.OnItenLongClickListener() {
            @Override
            public void onItemLongClick() {
                final BMDialog dialog = new BMDialog(mContext);
                dialog.show();
                dialog.setDialogMessage("是删除此好友信息？");
                dialog.setOnConfirmClickListener(new BMDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        dialog.dismiss();
                    }
                });
                dialog.setOnCancelClickListener(new BMDialog.OnCancelClickListener() {
                    @Override
                    public void onCancelClick() {
                        dialog.dismiss();
                    }
                });
            }
        });
        mAdapter.setClickListener(new BirthdayAdapter.OnItenLClickListener() {
            @Override
            public void onItemClick() {
                Intent intent = new Intent(mContext,AddFriendActivity.class);
                toActivity(intent,EDIT_FRIEND);
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
