package com.qixiao.bm.activity;

import android.Manifest;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qixiao.bm.R;
import com.qixiao.bm.Utils.SQLiteUtils;
import com.qixiao.bm.base.BaseActivity;
import com.qixiao.bm.bean.request.LoginBean;



import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

import static com.qixiao.bm.BMContants.TOKEN;
import static com.qixiao.bm.BMContants.USER_ICON;
import static com.qixiao.bm.BMContants.USER_ID;
import static com.qixiao.bm.BMContants.USER_NAME;
import static com.qixiao.bm.BMContants.USER_TEL;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.et_login_account)
    EditText etLoginAccount;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick({R.id.btn_login, R.id.tv_login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //极光别名
                JPushInterface.setAlias(mContext,  1, "y");


                LoginBean bean = new LoginBean();
                bean.setIcon("https://b-ssl.duitang.com/uploads/item/201803/08/20180308222614_mGSZH.thumb.700_0.jpeg");
                bean.setId(7);
                bean.setTel("1324567");
                loginSuccess(bean);
                break;
            case R.id.tv_login_register:

                toActivity(RegisterActivity.class);
                break;
        }
    }





    public void loginSuccess(LoginBean bean) {

        if (bean == null) {
            return;
        }
        msp.setInt(USER_ID, bean.getId());
        msp.setString(TOKEN, bean.getToken());
        msp.setString(USER_NAME, bean.getName());
        msp.setString(USER_TEL, bean.getTel());
        msp.setString(USER_ICON, bean.getIcon());

        toActivity(MainActivity.class);
        finish();
    }
}
