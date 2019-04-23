package com.qixiao.bm.activity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.qixiao.bm.BMContants;
import com.qixiao.bm.R;
import com.qixiao.bm.base.BaseActivity;
import com.qixiao.bm.bean.db.DBFriendBean;
import com.qixiao.bm.network.DBManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class SendSmsActivity extends BaseActivity {
    @BindView(R.id.et_send_tel)
    EditText etSendTel;
    @BindView(R.id.et_send_word)
    EditText etSendWord;
    @BindView(R.id.button)
    Button button;

    @Override
    protected int getContentView() {
        return R.layout.activity_sendsms;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {
        DBManager.getInstance().querySmsFriend(msp.getInt(BMContants.USER_ID), "qiqi")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DBFriendBean>() {
                    @Override
                    public void accept(DBFriendBean dbFriendBean) throws Exception {
                        String tel = dbFriendBean.getTel();
                        String word = dbFriendBean.getWord();
                        if (!TextUtils.isEmpty(tel)) {
                            etSendTel.setText(tel);
                        }
                        if (!TextUtils.isEmpty(word)) {
                            etSendWord.setText(word);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }


    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.button:
                SmsManager smsManager = SmsManager.getDefault();
                String content = etSendWord.getText().toString();
                String tel = etSendTel.getText().toString();
                if (!TextUtils.isEmpty(content)&&!TextUtils.isEmpty(tel)){
                    ArrayList<String> list = smsManager.divideMessage(content);
                    for (int i = 0; i < list.size(); i++) {
                        smsManager.sendTextMessage("10086", null, list.get(i), null, null);
                    }
                }
                finish();
                showToast("发送成功");
        }
    }
}
