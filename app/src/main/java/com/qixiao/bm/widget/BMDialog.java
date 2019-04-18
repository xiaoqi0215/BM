package com.qixiao.bm.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qixiao.bm.R;
import com.qixiao.bm.Utils.ClickOnUtil;

public class BMDialog extends Dialog {
    public BMDialog(Context context) {
        super(context,R.style.CommonDialog);
    }


    private OnConfirmClickListener mOnConfirmClickListener;
    private OnCancelClickListener mOnCancelClickListener;

    private TextView mBtnConfirm;
    private TextView mBtnCancel;
    private TextView mTvMessage;
    private RadioGroup mRgRemind;

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.mOnConfirmClickListener = onConfirmClickListener;
    }

    public void setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
        this.mOnCancelClickListener = onCancelClickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bm_dialog);
        //弹出动画
        getWindow().setWindowAnimations(R.style.DialogScaleAnim);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
            mBtnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnConfirmClickListener != null && !ClickOnUtil.isDoubleClickQuickly()) {
                        mOnConfirmClickListener.onConfirmClick();
                    }
                }
            });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCancelClickListener != null && !ClickOnUtil.isDoubleClickQuickly()) {
                    mOnCancelClickListener.onCancelClick();
                }
            }

        });
    }

    public void setDialogMessage(String message) {
        mTvMessage.setText(message);
    }


    public String getRemidWay() {
        switch (mRgRemind.getCheckedRadioButtonId()){
            case R.id.rb_dialog_one:
                return "提前一天";
            case R.id.rb_dialog_three:
                return "提前三天";
            case R.id.rb_dialog_seven:
                return "提前七天";
        }
        return " ";

    }

    public void showRemind() {
        mRgRemind.setVisibility(View.VISIBLE);
        mTvMessage.setVisibility(View.GONE);
    }

    private void initView() {
        mBtnConfirm = ((TextView) findViewById(R.id.btn_dialog_confirm));
        mBtnCancel = (TextView) findViewById(R.id.btn_dialog_cancel);
        mTvMessage = (TextView) findViewById(R.id.tv_dialog_notice_message);
        mRgRemind = findViewById(R.id.rg_dialog_remind);
    }

    public void setConfirmText(String text) {
        mBtnConfirm.setText(text);
    }


    public interface OnConfirmClickListener {
        void onConfirmClick();
    }

    public interface OnCancelClickListener {
        void onCancelClick();
    }
}
