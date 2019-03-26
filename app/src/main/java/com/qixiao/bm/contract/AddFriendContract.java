package com.qixiao.bm.contract;

import com.qixiao.bm.base.BasePresenter;
import com.qixiao.bm.base.BaseView;
import com.qixiao.bm.bean.reponse.Test;

public class AddFriendContract {
    public interface  View extends BaseView{
        public void testScuess(String name);
    }

    public interface Presenter extends BasePresenter{
        public void  test();
    }

}
