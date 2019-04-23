package com.qixiao.bm.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.drm.DrmStore;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiao.bm.R;

public class MyActionBar extends RelativeLayout {
    private  RelativeLayout mlinearLayout;
    private ImageView mIvLfet;
    private ImageView mIvRight;
    private TextView mTvTitle;
    private String mTitle;


    public MyActionBar(Context context) {
        this(context,null);
    }

    public MyActionBar(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }



    public MyActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyActionBar);
        mTitle = typedArray.getString(R.styleable.MyActionBar_title);
        mTvTitle.setText(mTitle);
    }

    private void init() {
        View layout = inflate(getContext(),R.layout.actionbar_baseactivity,this);
        mIvLfet = layout.findViewById(R.id.iv_base_activity_actionbar_left);
        mIvRight = layout.findViewById(R.id.iv_base_activity_actionbar_right);
        mTvTitle = layout.findViewById(R.id.tv_base_activity_actionbar_title);
        mlinearLayout = layout.findViewById(R.id.rl_base_activity_actionbar_layout);


    }//设置状态栏的高度
    public void setStatusBarHeight(int statusBarHeight) {
         ViewGroup.LayoutParams params = mlinearLayout.getLayoutParams();
         params.height = statusBarHeight;
         mlinearLayout.setLayoutParams(params);
     }

    public void hihRight() {
        mIvRight.setVisibility(View.GONE);
    }
    public void showRight() {
        mIvRight.setVisibility(View.VISIBLE);
    }
    public ImageView getRighttv() {
        return mIvRight;
    }

    public void setRightIv(int id){
         mIvRight.setImageResource(id);
    }

    public void hihLeft() {
        mIvLfet.setVisibility(View.GONE);
    }
    public void showLeft() {
        mIvLfet.setVisibility(View.VISIBLE);
    }

    public void setTitleText(String titleText) {
        mTvTitle.setText(titleText);
    }
}
