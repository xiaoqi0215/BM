package com.qixiao.bm.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qixiao.bm.R;
import com.qixiao.bm.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

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
                JPushInterface.setAlias(mContext,  1, "y");
                toActivity(MainActivity.class);
                break;
            case R.id.tv_login_register:
                toActivity(RegisterActivity.class);
                break;
        }
    }
}
