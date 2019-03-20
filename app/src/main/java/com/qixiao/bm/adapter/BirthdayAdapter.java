package com.qixiao.bm.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.qixiao.bm.BirthdayListBean;
import com.qixiao.bm.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.ViewHolder> {


    Context context;
    List<BirthdayListBean> data;

    public BirthdayAdapter(Context context, List<BirthdayListBean> data) {
        this.context = context;
        this.data = data;
    }

    public  void  setData(List<BirthdayListBean> data){
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item_birthday_list,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RequestOptions options = RequestOptions
                .circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(R.mipmap.ic_birthday_friend_icon).apply(options).into(viewHolder.mIconIv);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_birthday_friend_item_icon)
        ImageView mIconIv;
        @BindView(R.id.iv_birthday_friend_item_calendar)
        ImageView mCalendarIv;
        @BindView(R.id.tv_birthday_friend_item_name)
        TextView mNameTv;
        @BindView(R.id.tv_birthday_friend_item_date)
        TextView mDateTv;
        @BindView(R.id.tv_birthday_friend_item_age)
        TextView mAgeTv;
        @BindView(R.id.tv_birthday_friend_item_birthday)
        TextView mBirthdayTv;

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
