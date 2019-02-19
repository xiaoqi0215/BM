package com.qixiao.bm.base;

public interface BaseView {

    void showProgress(String msg);

    void dismissProgress();

    void setStatus(int status);

    void showToast(String msg);

    void tologin(String msg);
}
