package com.qixiao.bm.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qixiao.bm.R;
import com.qixiao.bm.base.BaseActivity;
import com.qixiao.bm.widget.MyActionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.tab_regidter)
    MyActionBar tabRegidter;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_register_account)
    TextView tvRegisterAccount;
    @BindView(R.id.tv_register_pwd)
    TextView tvRegisterPwd;
    @BindView(R.id.et_register_account)
    EditText etRegisterAccount;
    @BindView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R.id.et_register_repwd)
    EditText etRegisterRepwd;
    @BindView(R.id.tv_register_repwd)
    TextView tvRegisterRepwd;
    @BindView(R.id.view5)
    View view5;

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {
        tabRegidter.showLeft();
        tabRegidter.hihRight();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_register, R.id.et_register_account, R.id.et_register_pwd, R.id.et_register_repwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                break;
            case R.id.et_register_account:
                break;
            case R.id.et_register_pwd:
                break;
            case R.id.et_register_repwd:
                break;
        }
    }
}
