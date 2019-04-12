package com.qixiao.bm.base;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qixiao.bm.R;
import com.qixiao.bm.Utils.SharedPreferencesUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author qingmanyu
 * @desc
 * @created 2018/7/5 17:00
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView, View.OnClickListener {

    protected T mPresenter;
    public static String TAG;
    protected Context mContext;
    private Unbinder unbinder;
    protected SharedPreferencesUtils msp;

    protected abstract int getContentView();

    protected abstract void createPresenter();

    protected abstract void initView();

    @Override
    public void tologin() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        mContext = this;
        initActivity(savedInstanceState);
        msp = new SharedPreferencesUtils(this);
        BaseApplication.addActivity(this);
        TAG = this.getClass().getSimpleName();
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        createPresenter();

        initView();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        dismissProgress();
        super.onDestroy();
        unbinder.unbind();
        BaseApplication.removeActivity(this);
    }

    public void showProgress() {
        showProgress("");
    }

    /**
     * 显示ProgressDialog
     */
    @Override
    public void showProgress(String msg) {

    }

    /**
     * 取消ProgressDialog
     */
    @Override
    public void dismissProgress() {

    }

    public void showToast(int msgResid) {
        showToast(getString(msgResid));
    }

    @Override
    public void showToast(String message){
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
    }


    protected void toActivity(Class c ){
        Intent intent = new Intent(mContext,c);
        toActivity(intent);
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



    /**
     * 初次安装直接点击“打开”，之后出现activity混乱现象，每次都重新启动
     *
     * @param savedInstanceState
     */
    public void initActivity(Bundle savedInstanceState) {
    }


    public void hideSoftKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    protected void showSoftKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }
}
