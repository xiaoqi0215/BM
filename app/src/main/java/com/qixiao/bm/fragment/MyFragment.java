package com.qixiao.bm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiao.bm.BMApplication;
import com.qixiao.bm.R;
import com.qixiao.bm.activity.MyDetailInfo;
import com.qixiao.bm.base.BaseFragment;
import com.qixiao.bm.widget.BMDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

public class MyFragment extends BaseFragment {


    @BindView(R.id.iv_mine_user_icon)
    ImageView ivMineUserIcon;
    @BindView(R.id.layout_mine_user_icon)
    RelativeLayout layoutMineUserIcon;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.layout_my_list_name_iv)
    ImageView layoutMyListNameIv;
    @BindView(R.id.layout_my_list_name)
    RelativeLayout layoutMyListName;
    @BindView(R.id.layout_my_list_pwd_iv)
    ImageView layoutMyListPwdIv;
    @BindView(R.id.layout_my_list_pwd)
    RelativeLayout layoutMyListPwd;
    @BindView(R.id.layout_my_list_up_iv)
    ImageView layoutMyListUpIv;
    @BindView(R.id.layout_my_list_up)
    RelativeLayout layoutMyListUp;
    @BindView(R.id.btn_mine_exit)
    Button btnMineExit;
    Unbinder unbinder;

    public  static  final String TYPE="type";
    public  static  final String TYPE_NAME="name";
    public  static  final String TYPE_PWD="pwd";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView(View rootView) {
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }


    @OnClick({R.id.iv_mine_user_icon, R.id.layout_my_list_name, R.id.layout_my_list_pwd, R.id.layout_my_list_up, R.id.btn_mine_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_user_icon:
                break;
            case R.id.layout_my_list_name:
                Intent intent1 = new Intent(getActivity(),MyDetailInfo.class);
                intent1.putExtra(TYPE,TYPE_NAME);
                toActivity(intent1);
                break;
            case R.id.layout_my_list_pwd:
                Intent intent2 = new Intent(getActivity(),MyDetailInfo.class);
                intent2.putExtra(TYPE,TYPE_PWD);
                toActivity(intent2);
                break;
            case R.id.layout_my_list_up:
                break;
            case R.id.btn_mine_exit:
                final BMDialog dialog = new BMDialog(mContext);
                dialog.show();
                dialog.setDialogMessage("是否退出登陆？");
                dialog.setOnConfirmClickListener(new BMDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        dialog.dismiss();
                        JPushInterface.deleteAlias(mContext,1);
                        BMApplication.exit();
                    }
                });
                dialog.setOnCancelClickListener(new BMDialog.OnCancelClickListener() {
                    @Override
                    public void onCancelClick() {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }
}
