package com.qixiao.bm.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qixiao.bm.R;


public class CustomProgressDialog extends Dialog {
    private View mDialogView;
    private boolean cancelTouchOutside;
    private boolean cancelble;

    public CustomProgressDialog(Builder builder) {
        super(builder.context);
        mDialogView = builder.mDialogView;
        cancelTouchOutside = builder.cancelTouchOutside;
        cancelble = builder.cancelable;
    }

    private CustomProgressDialog(Builder builder, int themeResId) {
        super(builder.context, themeResId);
        mDialogView = builder.mDialogView;
        cancelTouchOutside = builder.cancelTouchOutside;
        cancelble = builder.cancelable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mDialogView);
        setCanceledOnTouchOutside(cancelTouchOutside);
        setCanceledOnTouchOutside(cancelble);
    }


    public static final class Builder {
        Context context;
        private int resStyle = -1;
        private View mDialogView;
        private boolean cancelTouchOutside;
        private boolean cancelable = true;

        public Builder(Context context) {
            this.context = context;
            mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        }

        /**
         * 设置主题
         *
         * @param resStyle style id
         * @return CustomProgressDialog.Builder
         */
        public Builder setTheme(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder setMessage(String message) {
            TextView tvMessage = (TextView) mDialogView.findViewById(R.id.tv_loadingmsg);
            if (tvMessage != null && message != null) {
                tvMessage.setText(message);
            }
            return this;
        }

        /**
         * 设置点击dialog外部是否取消dialog
         *
         * @param val 点击外部是否取消dialog
         * @return
         */
        public Builder cancelTouchOutside(boolean val) {
            cancelTouchOutside = val;
            return this;
        }

        /**
         * 是否可以取消
         *
         * @param val
         * @return
         */
        public Builder cancelable(boolean val) {
            cancelable = val;
            return this;
        }

        public CustomProgressDialog build() {
            if (resStyle != -1) {
                return new CustomProgressDialog(this, resStyle);
            } else {
                return new CustomProgressDialog(this);
            }
        }
    }
}
