package com.qixiao.bm.network;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;
import com.qixiao.bm.base.BaseResponse;
import com.qixiao.bm.base.BaseRxManager;
import com.qixiao.bm.base.BaseView;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class BaseO1<T> implements Observer<Response<T>> {
    private BaseView mView;
    private BaseRxManager rxManager;

    private static final String ACK_CODE = "ACK";
    private static final String NACK_CODE = "NACK";
    private static final int UNAUTHORIZED_CODE = 401;
    private static final int INTERNAL_SERVER_CODE = 500;
    private static final int REFRESHUNAUTHORIZED_CODE = 402;

    public BaseO1(BaseView mView, BaseRxManager rxManager) {
        this.mView = mView;
        this.rxManager = rxManager;
    }

    @Override
    public final void onSubscribe(Disposable d) {
        rxManager.addDisposable(d);
    }

    @Override
    public final void onNext(Response<T> response) {
        if (response.isSuccessful()) {
            if (response.body() == null) {
                Logger.d("接口调用失败，response.body() 为 null");
                onBizFailure("服务器异常，请稍候再试");
                onFinally();
                return;
            }

            Log.e("TAG","isSuccessful");
            onBizSuccess(response.body());
        }
        onFinally();

    }

    @Override
    public final void onError(Throwable e) {
        Logger.e("onError,Retrofit:" + e.getMessage());
        if (e instanceof HttpException) {
            //   HTTP错误
            int code = ((HttpException) e).response().code();
            onBizFailure("服务器异常，请稍后再试");
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onBizFailure("当前无网络连接");
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onBizFailure("连接超时,请稍后再试");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onBizFailure("解析服务器响应数据失败，请稍后再试");
        } else {
            onBizFailure("服务器开小差了...");
        }
        onFinally();
    }

    @Override
    public final void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param result 服务器返回的数据
     */
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
