package com.qixiao.bm.contract;

import android.content.Context;

import com.qixiao.bm.activity.AddFriendActivity;
import com.qixiao.bm.activity.MainActivity;
import com.qixiao.bm.base.BasePresenter;
import com.qixiao.bm.base.BasePresenterImpl;
import com.qixiao.bm.bean.reponse.Test;
import com.qixiao.bm.network.BaseO1;
import com.qixiao.bm.network.BaseObserver;
import com.qixiao.bm.network.RetrofitManager;
import com.qixiao.bm.network.RxSchedule;

import retrofit2.Response;

public class AddFriendPresenter extends BasePresenterImpl<AddFriendContract.View> implements AddFriendContract.Presenter {
    public AddFriendPresenter(AddFriendActivity mView, Context context) {
        super(mView, context);
    }

    @Override
    public void test() {
        RetrofitManager.getService().test()
                .compose(RxSchedule.<Response<Test>>applyProgressBar(mView,mRxManager))
                .subscribe(new BaseO1<Test>(mView,mRxManager) {
                    @Override
                    public void onBizSuccess(Test result) {
                        mView.testScuess(result.getName());
                    }
                });

    }
}
