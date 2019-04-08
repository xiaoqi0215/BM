package com.qixiao.bm.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    private LinearLayout mLlInput;
    private TextView mTvTitle;
    private EditText mEtContent;

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

    public void setInputType(int type) {
        mEtContent.setInputType(type);
    }

    public void setInputHint(String hint) {
        mEtContent.setHint(hint);
    }

    public void setInputLength(int length) {
        //手动设置maxLength
        InputFilter[] filters = {new InputFilter.LengthFilter(length)};
        mEtContent.setFilters(filters);
    }

    public void setDialogMessage(String message) {
        mTvMessage.setText(message);
    }

    public void setDialogTitle(String title) {
        mTvTitle.setText(title);
    }

    public String getInput() {
        return mEtContent.getText().toString();
    }

    public void showInput() {
        mLlInput.setVisibility(View.VISIBLE);
        mTvMessage.setVisibility(View.GONE);
    }

    private void initView() {
        mBtnConfirm = ((TextView) findViewById(R.id.btn_dialog_confirm));
        mBtnCancel = (TextView) findViewById(R.id.btn_dialog_cancel);
        mTvMessage = (TextView) findViewById(R.id.tv_dialog_notice_message);
        mLlInput = (LinearLayout) findViewById(R.id.ll_input);
        mTvTitle = (TextView) findViewById(R.id.tv_dialog_title);
        mEtContent = (EditText) findViewById(R.id.et_dialog_content);
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
