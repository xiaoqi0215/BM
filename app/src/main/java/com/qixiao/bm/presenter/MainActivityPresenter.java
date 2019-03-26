package com.qixiao.bm.presenter;

import android.content.Context;

import com.qixiao.bm.base.BasePresenter;
import com.qixiao.bm.base.BasePresenterImpl;
import com.qixiao.bm.contract.MainActivityContract;

public class MainActivityPresenter extends BasePresenterImpl<MainActivityContract.View> implements BasePresenter {
    public MainActivityPresenter(MainActivityContract.View mView, Context context) {
        super(mView, context);
    }
}
