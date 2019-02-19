package com.qixiao.bm.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseRxManager {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void addDisposable(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            compositeDisposable.add(disposable);
        }
    }

    public void clear() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}
