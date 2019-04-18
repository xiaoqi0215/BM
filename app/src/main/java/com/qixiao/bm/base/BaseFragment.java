package com.qixiao.bm.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.qixiao.bm.R;
import com.qixiao.bm.widget.CustomProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:
 *
 * @author qingmanyu
 * @time 2018/7/17 14:16
 */
public abstract class BaseFragment<P extends BasePresenterImpl> extends Fragment implements BaseView, View.OnClickListener {
    protected Context mContext;
    protected P mPresenter;
    protected View mRootView;
    public static String TAG;

    private Unbinder unbinder;
    private CustomProgressDialog mProgressDialog;

    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    protected boolean isFirst = true;


    protected abstract int getLayoutId();

    protected abstract void createPresenter();

    protected abstract void initView(View rootView);

    protected abstract void initData();

    @Override
    public void tologin() {

    }

    /**
     * 每次都进行懒加载
     * isFirst可以控制仅第一次加载
     */
    protected void lazyLoad() {
    }

    protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mContext = getActivity();
        initFragment(savedInstanceState);
        unbinder = ButterKnife.bind(this, mRootView);
        TAG = this.getClass().getSimpleName();
        createPresenter();
        initView(mRootView);
        isInit = true;
        /**初始化的时候去加载数据**/
        isCanLoadData();
        initData();
        return mRootView;
    }

    /**
     * fragment被设置为不可见时调用
     */
    protected void stopLoad() {
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        dismissProgress();
        super.onDestroyView();
        unbinder.unbind();
        isInit = false;
        isLoad = false;
        isFirst = true;
    }


    protected void refreshData() {
    }

    public void showProgress() {
        showProgress("");
    }

    /**
     * 显示ProgressDialog
     */
    @Override
    public void showProgress(String msg) {
        if (mContext == null) {
            return;
        }

        if (mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog.Builder(mContext)
                    .setTheme(R.style.ProgressDialogStyle)
                    .setMessage(msg)
                    .cancelTouchOutside(false)
                    .build();
        }
        Log.d("========", mProgressDialog.isShowing() + "");
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }


    /**
     * 取消ProgressDialog
     */
    @Override
    public void dismissProgress() {
    mProgressDialog.dismiss();
    }


    public void showToast(int msgResid) {
        showToast(getString(msgResid));
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
    }


    /**
     * 跳转
     *
     * @param activity
     */
    protected void toActivity(Class<? extends BaseActivity> activity) {
        startActivity(new Intent(mContext, activity));
    }

    /**
     * 跳转
     *
     * @param intent
     */
    protected void toActivity(Intent intent) {
        startActivity(intent);
    }

    /**
     * 跳转
     *
     * @param intent
     * @param requestCode
     */
    protected void toActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    public void setOnlyFirstLoadData(boolean onlyFirstLoadData) {
        isFirst = !onlyFirstLoadData;
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }
        if (getUserVisibleHint()) {
            if (isFirst) {
                lazyLoad();
                isLoad = true;
            }
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }


}
