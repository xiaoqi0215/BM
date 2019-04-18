package com.qixiao.bm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qixiao.bm.BMContants;
import com.qixiao.bm.R;
import com.qixiao.bm.Utils.SQLiteUtils;
import com.qixiao.bm.activity.AddFriendActivity;
import com.qixiao.bm.adapter.BirthdayAdapter;
import com.qixiao.bm.base.BaseFragment;
import com.qixiao.bm.bean.db.DBFriendBean;
import com.qixiao.bm.network.DBManager;
import com.qixiao.bm.widget.BMDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.app.Activity.RESULT_OK;

public class BirthdayFragment extends BaseFragment {
    private static final int RESULT_CODE_TOADDFRIEND = 1001;
    @BindView(R.id.rv_birthday_friend)
    RecyclerView mFriendRv;
    BirthdayAdapter mAdapter;


    List<DBFriendBean> dbData;
    public final static int EDIT_FRIEND = 100;
    @BindView(R.id.floatingActionButton2)
    FloatingActionButton fab;
    Unbinder unbinder;

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddFriendActivity.class);
                toActivity(intent, RESULT_CODE_TOADDFRIEND);
            }
        });
    }

    private void queryAllFriend() {
        DBManager.getInstance().queryFriendAll()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showProgress();
                    }
                })
                .subscribe(new Observer<List<DBFriendBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final List<DBFriendBean> dbFriendBeans) {
                        dbData.addAll(dbFriendBeans);
                        mAdapter = new BirthdayAdapter(getActivity(), dbData);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        mFriendRv.setLayoutManager(linearLayoutManager);
                        mFriendRv.addItemDecoration(new BirthdayAdapter.SpaceItemDecoration(15));
                        mFriendRv.setAdapter(mAdapter);

                        mAdapter.setLongClickListener(new BirthdayAdapter.OnItenLongClickListener() {
                            @Override
                            public void onItemLongClick(final int position) {
                                final BMDialog dialog = new BMDialog(mContext);
                                final String name = dbData.get(position).getName();
                                dialog.show();
                                dialog.setDialogMessage("是删除此好友信息？");
                                dialog.setOnConfirmClickListener(new BMDialog.OnConfirmClickListener() {
                                    @Override
                                    public void onConfirmClick() {
                                        DBManager.getInstance().deleteFriend(name)
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .doOnSubscribe(new Consumer<Disposable>() {
                                                    @Override
                                                    public void accept(Disposable disposable) throws Exception {
                                                        showProgress();
                                                    }
                                                })
                                             .subscribe(new Consumer<Boolean>() {
                                            @Override
                                            public void accept(Boolean dbFriendBean) throws Exception {
                                                dbData.remove(position);
                                                mAdapter.setData(dbData);
                                                dismissProgress();
                                                dialog.dismiss();
                                            }
                                        });

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
                            public void onItemClick(int position) {
                                DBFriendBean bean = dbData.get(position);
                                String friendName = bean.getName();
                                int friendAge = bean.getAge();
                                int id = bean.getId();
                                String friendTel = bean.getTel();
                                int friendWay =bean.getWay();
                                int friendYear = bean.getYear();
                                int userId = bean.getUserId();
                                String friendIcon = bean.getIcon();
                                String word = bean.getWord();
                                int friendMonth = bean.getMonth();
                                int friendDay = bean.getDay();
                                int solar = bean.getSolar();
                                int hour = bean.getHour();
                                int mintue = bean.getMintue();
                                int sex = bean.getSex();

                                Log.e("TAG","点击：name:"+friendName+"sex:"+sex+"hour:"+
                                        hour+"min:"+mintue+"solao:"+solar);
                                Intent intent = new Intent(mContext, AddFriendActivity.class);
                                intent.putExtra(BMContants.DB_NAME,friendName);
                                intent.putExtra(BMContants.DB_TEL,friendTel);
                                intent.putExtra(BMContants.DB_WAY,friendWay);
                                intent.putExtra(BMContants.DB_YEAR,friendYear);
                                intent.putExtra(BMContants.DB_ICON,friendIcon);
                                intent.putExtra(BMContants.DB_WORD,word);
                                intent.putExtra(BMContants.DB_MONTH,friendMonth);
                                intent.putExtra(BMContants.DB_SOLAR,solar);
                                intent.putExtra(BMContants.DB_HOUR,hour);
                                intent.putExtra(BMContants.DB_MIN,mintue);
                                intent.putExtra(BMContants.DB_DAY,friendDay);
                                intent.putExtra("type","edit");
                                intent.putExtra(BMContants.DB_SEX,sex);
                                dbData.remove(position);
                                toActivity(intent, EDIT_FRIEND);
                            }
                        });
                        dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_CODE_TOADDFRIEND&&resultCode==RESULT_OK){
                String name = data.getStringExtra(BMContants.DB_NAME);
                DBManager.getInstance().isHasFriend(name)
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                showProgress();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<DBFriendBean>() {
                            @Override
                            public void accept(DBFriendBean dbFriendBean) throws Exception {
                                dbData.add(dbFriendBean);
                                mAdapter.setData(dbData);
                                dismissProgress();
                            }
                        });
        }else if (requestCode==EDIT_FRIEND&&resultCode==RESULT_OK){
            String name = data.getStringExtra(BMContants.DB_NAME);
            DBManager.getInstance().isHasFriend(name)
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            showProgress();
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<DBFriendBean>() {
                        @Override
                        public void accept(DBFriendBean dbFriendBean) throws Exception {
                            dbData.add(dbFriendBean);
                            mAdapter.setData(dbData);
                            dismissProgress();
                        }
                    });
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
