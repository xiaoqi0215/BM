package com.qixiao.bm.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Dimension;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.qixiao.bm.R;
import com.qixiao.bm.Utils.ImageUtils;
import com.qixiao.bm.base.BaseApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public   class NineLayout extends ViewGroup {
    
    float mSpace;
    private static  final float DEFAULR_SPPACE = 1f;
    protected List<String> mUrlList = new ArrayList<>();
    protected Context mContext;
    private  boolean isFirst = true;
    private int mTotalWidth;
    private int mSingleWidth;
    private int mColumns;
    private int mRows;
    private boolean isShowAll= false;

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        Log.e("TAG","NineLayout:onScreenStateChanged");
        if (screenState == View.SCREEN_STATE_OFF){
            dataHasChange();
        }

    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction,  Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.e("TAG","NineLayout:onFocusChanged");
    }

    @Override
    public void addOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
        super.addOnAttachStateChangeListener(listener);
        Log.e("TAG","NineLayout:addOnAttachStateChangeListener");
    }

    public void setData(List<String> urlList){
        if (getListSize(urlList) == 0) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);
        mUrlList.clear();
        mUrlList.addAll(urlList);
        if (!isFirst) {
            dataHasChange();
        }

    }

    public NineLayout(Context context) {
        this(context,null);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("TAG","NineLayout:NineLayout");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e("TAG","NineLayout:NineLayout");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.e("TAG","NineLayout:NineLayout");
    }

    public NineLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public NineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setSaveEnabled(true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NineLayout);
        mSpace = typedArray.getDimension(R.styleable.NineLayout_space,DEFAULR_SPPACE);
        typedArray.recycle();
        init();
        Log.e("TAG","NineLayout:NineLayout");
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable= super.onSaveInstanceState();
        SaveState saveState = new SaveState(parcelable);
        saveState.mUrlList = mUrlList;
        return saveState;
    }


    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SaveState saveState = (SaveState) state;
        super.onRestoreInstanceState(saveState.getSuperState());
        init();
        saveState.mUrlList = mUrlList;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("TAG","NineLayout:onMeasure");
    }



    private void init() {
//        mUrlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552910607380&di=76470883fe27db2e9afd81fa2e3a574c&imgtype=0&src=http%3A%2F%2Fp3.ssl.cdn.btime.com%2Ft01ba4779de5a261eda.jpg%3Fsize%3D1200x2134");
//        mUrlList.add("http://wx2.sinaimg.cn/mw690/ac38503ely1fesz8m0ov6j20qo140dix.jpg");
      //  mUrlList.add("http://dingyue.nosdn.127.net/zJ46w4xAYR=eUEsduYj4NvtRwYWTP4alP9gAoRKNoLrym1538308080579compressflag.jpg");
//        mUrlList.add("http://b-ssl.duitang.com/uploads/item/201510/04/20151004160737_5YESH.thumb.700_0.jpeg");
//        mUrlList.add("http://img2.imgtn.bdimg.com/it/u=1591348172,3241312526&fm=214&gp=0.jpg");
//        mUrlList.add("http://b-ssl.duitang.com/uploads/item/201707/26/20170726115711_vLY4y.thumb.700_0.jpeg");
//        mUrlList.add("http://hbimg.b0.upaiyun.com/d5ee0f809d71803a7a9dfc917856d97ee7ee7e9848ae9-XSNXvz_fw658");
//        mUrlList.add("http://img.yzcdn.cn/upload_files/2018/04/10/FibwDGQHJJoy-9smh7od4UApEB8G.jpg%21280x280.jpg");
//        mUrlList.add("http://hbimg.b0.upaiyun.com/d5ee0f809d71803a7a9dfc917856d97ee7ee7e9848ae9-XSNXvz_fw658");

        if (getListSize(mUrlList) == 0) {
            setVisibility(GONE);       
        } else {
            setVisibility(VISIBLE);
        }
    }

    private int getListSize(List<String> mUrlList) {
        if(mUrlList==null || mUrlList.size()==0){
            return  0;
        }
        return  mUrlList.size();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    mTotalWidth = r - l;
    mSingleWidth = (int)((mTotalWidth-mSpace*2)/3);
    if (isFirst){
        dataHasChange();
        isFirst = false;
    }
        Log.e("TAG","NineLayout:onLayout");
    }

    public void  dataHasChange(){
        post(new TimerTask() {

            @Override

            public void run() {

                refresh();

            }

        });

    }

    private void refresh() {
        super.removeAllViews();
        String url = mUrlList.get(0);
        int size =getListSize(mUrlList);
        if(size==1){
            ImageView imageView = createImageView(0,url);
            LayoutParams params = getLayoutParams();
            params.height =mSingleWidth;
            imageView.layout(0, 0, mSingleWidth, mSingleWidth);
            boolean isDefault =displayOneImage(imageView, url, mTotalWidth);
            if (isDefault){

            }
            addView(imageView);
            return;
        }
        manyLayout(size);
        manyParam();

        for(int i=0;i<size ;i++){
            String imageUrl = mUrlList.get(i);
            if (isShowAll){

            }
            ImageView imageView = createImageView(i,imageUrl);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            layoutImageView(imageView,i,imageUrl,false);
        }

    }

    private void manyParam() {
            int singleHeight = mSingleWidth;
            LayoutParams params = getLayoutParams();
            params.height =(int)(singleHeight * mRows + mSpace*(mRows-1));
            setLayoutParams(params);
    }

    private void manyLayout(int size) {
        if(size ==2 || size ==4){
            mColumns = 2;
            mRows = size / 2;
            return;
        }
        int temp =size/3;
        int temp2 = size%3;
        if(temp2 == 0){
            mRows = temp;
        }else {
            mRows = temp+1;
        }
        mColumns =3;
        Log.e("TAG ROW",+mRows+" " +mColumns);
    }

    protected void setOneImageLayoutParams(ImageView imageView, int width, int height) {
        imageView.setLayoutParams(new LayoutParams(width,height));
        imageView.layout(0,0,width,height);
        LayoutParams params = getLayoutParams();
        params.height = height;
        setLayoutParams(params);
    }

    protected  boolean displayOneImage(final ImageView imageView, String url, int mTotalWidth){
        Log.e("TAG","MyNineLayout:displayOneImage");

        Glide.with(mContext)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                          @Override
                          public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                              int width = resource.getWidth();
                              int height = resource.getHeight();
                              setOneImageLayoutParams(imageView,width,height);
                              imageView.setImageBitmap( resource);
                          }
                      }
                );
        return false;
    }


    private void layoutImageView(ImageView imageView, int childPos, String url, boolean isDefault) {



        int[] pos= findPosition(childPos);
        int singleWidth = (int)((mTotalWidth-mSpace*2)/3);
        int singlHeight = singleWidth;
        int left = (int )(singleWidth +mSpace) * pos[1];
        int top = (int)(singlHeight+mSpace) * pos[0];
        int right = (int)left+singleWidth;
        int bottom =(int)top+singlHeight;

        imageView.layout(left,top,right,bottom);


        addView(imageView);
        displayImage(imageView,mUrlList.get(childPos));
    }

    protected  void displayImage(ImageView imageView, String s){
        Log.e("TAG","MyNineLayout:displayImage");
        Glide.with(mContext)
                .load(s)
                .into(imageView);
    }

    private  int[] findPosition(int childNum){
        int[] position = new int[2];
        for(int i = 0;i<mRows;i++){
            for (int j=0;j<mColumns;j++){
                if (i*mColumns+j==childNum){
                    position[0]=i;
                    position[1]=j;
                    break;
                }
            }
        }
        return position;
    }


    private  ImageView  createImageView(final int i , final String imageUrl){
        ImageView imageView = new ImageView(mContext);
       // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageOnClick(i,imageUrl,mUrlList);
            }
        });
        return  imageView;
    }
    void imageOnClick(int i,String imageUrl,List<String> UrlList){

    }

    static class SaveState extends BaseSavedState{

        protected List<String> mUrlList ;
        public SaveState(Parcel source) {
            super(source);
            source.readStringList(mUrlList);

        }

        public void setmUrlList(List<String> mUrlList) {
            this.mUrlList = mUrlList;
        }

        public SaveState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeStringList(mUrlList);
        }
        public static final Parcelable.Creator<SaveState> CREATOR = new Creator<SaveState>() {

            @Override
            public SaveState createFromParcel(Parcel source) {
                return new SaveState(source);
            }

            @Override
            public SaveState[] newArray(int size) {
                return new SaveState[0];
            }
        };
     }

    }



