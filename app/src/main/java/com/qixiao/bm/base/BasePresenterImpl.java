package com.qixiao.bm.base;


import android.content.Context;

public class BasePresenterImpl<V extends BaseView> implements BasePresenter {

    protected V mView;
    protected Context context;
    protected BaseRxManager mRxManager;

    public BasePresenterImpl(V mView, Context context) {
        this.mView = mView;
        mRxManager = new BaseRxManager();
        this.context = context;
    }

    @Override
    public void detachView() {
        mRxManager.clear();
    }

}
