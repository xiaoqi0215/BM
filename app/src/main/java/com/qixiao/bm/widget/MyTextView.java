package com.qixiao.bm.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qixiao.bm.R;

public class MyTextView extends LinearLayout {
    public  static final int MAX_Line = 6;
    public  static final String MORE = "more";
    public  static final String TOGGLR = "toggle";
    TextView tvContent;
    TextView more;
    private Context mContent;
    private String mContentString;



    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

//    public void setmContentString(String mContentString) {
//        this.mContentString = mContentString;
//    }

    public void setmContentString(String ContentString) {
        this.mContentString = ContentString;
        if (tvContent.getWidth() == 0){
            return;
        }
        tvContent.setMaxLines(Integer.MAX_VALUE);
        tvContent.setText(mContentString);
        tvContent.setVisibility(VISIBLE);
//        tvContent.post(new Runnable() {
//                @Override
//                public void run() {
//                    int countLine = tvContent.getLineCount();
//                    Log.e("tag","sss"+ countLine +" "+tvContent.getLineCount());
//                   // Log.e("tag",tvContent.getText().toString());
//                    if(countLine > MAX_Line){
//
//                        if (more.getTag().equals(MORE)){
//                            more.setText("显示全文");
//                            more.setTag(TOGGLR);
//                            tvContent.setMaxLines(MAX_Line);
//                        }else if (more.getTag().equals(TOGGLR)){
//                            more.setText("收起");
//                            more.setTag(MORE);
//                            tvContent.setMaxLines(Integer.MAX_VALUE);
//                        }
//                        more.setVisibility(VISIBLE);
//                    }
//
//                }
//            });

        int countLine = tvContent.getLineCount();
       // Log.e("tag","sss"+ countLine +" "+tvContent.getLineCount());
        // Log.e("tag",tvContent.getText().toString());
        if(countLine > MAX_Line){

            if (more.getTag().equals(MORE)){
                more.setText("显示全文");
                more.setTag(TOGGLR);
                tvContent.setMaxLines(MAX_Line);
            }else if (more.getTag().equals(TOGGLR)){
                more.setText("收起");
                more.setTag(MORE);
                tvContent.setMaxLines(Integer.MAX_VALUE);
            }
            more.setVisibility(VISIBLE);
            invalidate();
        }

    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        mContent= context;
        View view = LayoutInflater.from(mContent).inflate(R.layout.widge_dynamic_tv,this);
        tvContent = view.findViewById(R.id.tv_dynamic_widge_content);
        more = view.findViewById(R.id.tv_dynamic_more);
        tvContent.setMaxLines(Integer.MAX_VALUE);
        more.setTag(MORE);
        more.setVisibility(VISIBLE);
        more.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changeStatus();
            }
        });
       // setmContentString(mContentString);

        tvContent.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (tvContent.getWidth() == 0) {
                            return;
                        }
                        tvContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                          setmContentString(mContentString);

                    }
                }
        );

    }

    private void changeStatus() {
        more.setVisibility(VISIBLE);
        if (more.getTag().equals(MORE)){
            more.setText("显示全文");
            more.setTag(TOGGLR);
            tvContent.setMaxLines(MAX_Line);
        }else if (more.getTag().equals(TOGGLR)){
            more.setText("收起");
            more.setTag(MORE);
            tvContent.setMaxLines(Integer.MAX_VALUE);
        }
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
