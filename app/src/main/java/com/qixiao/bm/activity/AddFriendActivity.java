package com.qixiao.bm.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qixiao.bm.R;
import com.qixiao.bm.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddFriendActivity extends BaseActivity {


    @BindView(R.id.iv_add_friend_icon)
    ImageView ivAddFriendIcon;
    @BindView(R.id.et_addfriend_name)
    EditText etAddfriendName;
    @BindView(R.id.rb_add_friend_female)
    RadioButton rbAddFriendFemale;
    @BindView(R.id.rb_add_friend_male)
    RadioButton rbAddFriendMale;
    @BindView(R.id.rg_add_friend_sex)
    RadioGroup rgAddFriendSex;
    @BindView(R.id.tv_addfriend_birthday)
    TextView tvAddfriendBirthday;
    @BindView(R.id.tv_addfriend_way)
    TextView tvAddfriendWay;
    @BindView(R.id.et_addfriend_tel)
    EditText etAddfriendTel;
    @BindView(R.id.iv_addfriend_tel)
    ImageView ivAddfriendTel;
    @BindView(R.id.et_addfriend_word)
    EditText etAddfriendWord;
    @BindView(R.id.btn_add_friend_add)
    Button btnAddFriendAdd;

    @Override
    protected int getContentView() {
        return R.layout.fragment_add_friend;
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

    @OnClick({R.id.iv_add_friend_icon,R.id.btn_add_friend_add,R.id.tv_addfriend_birthday,
            R.id.tv_addfriend_way,R.id.iv_addfriend_tel})
    public void onViewClick(View view) {
            switch(view.getId()){
                case R.id.iv_add_friend_icon:
                    break;
                case  R.id.btn_add_friend_add:
                    break;
                case R.id.tv_addfriend_birthday:
                    break;
                case R.id.tv_addfriend_way:
                    break;
                case  R.id.iv_addfriend_tel:
                    break;
            }

    }



}
