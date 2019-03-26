package com.qixiao.bm.network;

import com.qixiao.bm.base.BaseRxManager;
import com.qixiao.bm.base.BaseView;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxSchedule {


    public static <T> ObservableTransformer<T,T> applyProgressBar(final BaseView view,

                                                                  final BaseRxManager baseRxManager){
        view.showProgress(null);
        return  new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

                return upstream
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                baseRxManager.addDisposable(disposable);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                view.dismissProgress();
                            }
                        });
            }
        };
    }

}
