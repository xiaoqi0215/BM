package com.qixiao.bm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.qixiao.bm.R;
import com.qixiao.bm.base.BaseActivity;
import com.qixiao.bm.fragment.MyFragment;
import com.qixiao.bm.widget.MyActionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDetailInfo extends BaseActivity {
    @BindView(R.id.tab_my_detail_info)
    MyActionBar tabMyDetailInfo;
    @BindView(R.id.et_my_info_details_name)
    EditText etMyInfoDetailsName;
    @BindView(R.id.layout_my_info_details_name)
    LinearLayout layoutMyInfoDetailsName;
    @BindView(R.id.et_my_info_details_old_pwd)
    EditText etMyInfoDetailsOldPwd;
    @BindView(R.id.et_my_info_details_new_pwd)
    EditText etMyInfoDetailsNewPwd;
    @BindView(R.id.et_my_info_details_renew_pwd)
    EditText etMyInfoDetailsRenewPwd;
    @BindView(R.id.layout_my_info_details_pwd)
    LinearLayout layoutMyInfoDetailsPwd;
    @BindView(R.id.btn_my_info_details_save)
    Button btnMyInfoDetailsSave;
    String type;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_detail_info;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {
        tabMyDetailInfo.hihLeft();
        tabMyDetailInfo.hihRight();
        Intent  intent = getIntent();
        type = intent.getStringExtra(MyFragment.TYPE);
        if(type.equals(MyFragment.TYPE_NAME)){
            layoutMyInfoDetailsName.setVisibility(View.VISIBLE);
            tabMyDetailInfo.setTitleText("修改姓名");
        }else if(type.equals(MyFragment.TYPE_PWD)){
            layoutMyInfoDetailsPwd.setVisibility(View.VISIBLE);
            tabMyDetailInfo.setTitleText("修改密码");
        }


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

    @OnClick(R.id.btn_my_info_details_save)
    public void onViewClicked() {
    }
}
