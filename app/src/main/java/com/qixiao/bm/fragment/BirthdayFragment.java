package com.qixiao.bm.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.qixiao.bm.BMApplication;
import com.qixiao.bm.BMContants;
import com.qixiao.bm.BirthdayListBean;
import com.qixiao.bm.R;
import com.qixiao.bm.Utils.SQLiteUtils;
import com.qixiao.bm.activity.AddFriendActivity;
import com.qixiao.bm.adapter.BirthdayAdapter;
import com.qixiao.bm.base.BaseFragment;
import com.qixiao.bm.bean.db.DBFriendBean;
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
    List<DBFriendBean> dbData;
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
        dbData = new ArrayList<>();
        queryAllFriend();

        mAdapter = new BirthdayAdapter(getActivity(),dbData);
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

    private void queryAllFriend() {

        Cursor cursor = SQLiteUtils.query(BMContants.DBFRIEND,null,null,null,null,null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                DBFriendBean dbFriendBean =new DBFriendBean();
                String friendName = cursor.getString(cursor.getColumnIndex("name"));
                int friendAge = cursor.getInt(cursor.getColumnIndex("age"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String friendTel = cursor.getString(cursor.getColumnIndex("tel"));
                int friendWay = cursor.getInt(cursor.getColumnIndex("way"));
                int friendYear = cursor.getInt(cursor.getColumnIndex("year"));
                int userId = cursor.getInt(cursor.getColumnIndex("userId"));
                String friendIcon = cursor.getString(cursor.getColumnIndex("icon"));
                String word = cursor.getString(cursor.getColumnIndex("word"));
                int friendMonth = cursor.getInt(cursor.getColumnIndex("month"));
                int friendDay = cursor.getInt(cursor.getColumnIndex("day"));
                dbFriendBean.setAge(friendAge);
                dbFriendBean.setId(id);
                dbFriendBean.setUserId(userId);
                dbFriendBean.setName(friendName);
                dbFriendBean.setYear(friendYear);
                dbFriendBean.setTel(friendTel);
                dbFriendBean.setWay(friendWay);
                dbFriendBean.setIcon(friendIcon);
                dbFriendBean.setDay(friendDay);
                dbFriendBean.setMonth(friendMonth);
                dbData.add(dbFriendBean);
                Log.e("TAG", "姓名："+friendName+" "+"年龄："+friendAge+" "+"电话："+friendTel+"way"+friendWay+"year"+friendYear);
            }
        }
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





}
