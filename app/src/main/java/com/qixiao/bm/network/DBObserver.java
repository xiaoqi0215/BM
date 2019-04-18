package com.qixiao.bm.network;


import android.text.TextUtils;

import com.qixiao.bm.base.BaseResponse;
import com.qixiao.bm.base.BaseRxManager;
import com.qixiao.bm.base.BaseView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class DBObserver<T> implements Observer<T> {
    private BaseView mView;
    private BaseRxManager rxManager;

    public DBObserver(BaseView mView, BaseRxManager rxManager) {
        this.mView = mView;
        this.rxManager = rxManager;
    }

    @Override
    public void onSubscribe(Disposable d) {
        rxManager.addDisposable(d);
    }

    @Override
    public void onNext(T t) {
        onBizSuccess(t);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public abstract void onBizSuccess(T result);

    /**
     * 服务器返回数据，但响应码不为200
     */
    public void onBizFailure(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            mView.showToast(msg);
        }
    }

    public void onFinally() {
        mView.dismissProgress();
    }
}
