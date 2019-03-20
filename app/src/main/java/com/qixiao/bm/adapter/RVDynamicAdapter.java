package com.qixiao.bm.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.qixiao.bm.R;
import com.qixiao.bm.Utils.ImageUtils;
import com.qixiao.bm.widget.MyTextView;
import com.qixiao.bm.widget.NineLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RVDynamicAdapter extends RecyclerView.Adapter<RVDynamicAdapter.ViewHolder> {
    Context context;

    public void setData(List<Object> mdata) {
        this.mdata = mdata;
        notifyDataSetChanged();
    }

    List<Object> mdata;

    public RVDynamicAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_dynamic,null,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ImageUtils.loadCircleImg(context,"http://i1.073img.com/170802/17598513_160725_3_lit.jpg",
                viewHolder.icon,R.mipmap.mine_user_icon_default);
        ArrayList<String> mUrlList = new ArrayList<>();
        for (int j = 0;j<= i+2;j++){
            String url = "http://dingyue.nosdn.127.net/zJ46w4xAYR=eUEsduYj4NvtRwYWTP4alP9gAoRKNoLrym1538308080579compressflag.jpg";
            mUrlList.add(url);
        }
        viewHolder.nineLayout.setData(mUrlList);
        Log.e("TAG","adapter:onBindViewHolder");
       // viewHolder.nineLayout.invalidate();
        viewHolder.contexts.setmContentString("歌曲歌词\n" +
                "有多久没见突然忘了时间\n" +
                "最近还好吗  你还是那样\n有多久没见\n" +
                "突然忘了时间\n" +
                "最近还好吗\n" +
                "你还是那样\n" +
                "一点都没变\n" +
                "我过得很好\n" +
                "在分开之后");
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.gv_dynamic_list_item_picture)
        NineLayout nineLayout;
        @BindView(R.id.iv_dynamic_list_item_icon)
        ImageView icon;
        @BindView(R.id.tv_dynamic_list_item_cotents)
        MyTextView contexts;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = mSpace;


        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }

}
