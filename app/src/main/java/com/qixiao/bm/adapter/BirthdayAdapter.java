package com.qixiao.bm.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.qixiao.bm.BirthdayListBean;
import com.qixiao.bm.R;
import com.qixiao.bm.Utils.CalendarUtil;
import com.qixiao.bm.activity.AddFriendActivity;
import com.qixiao.bm.base.BaseActivity;
import com.qixiao.bm.bean.db.DBFriendBean;
import com.qixiao.bm.fragment.BirthdayFragment;
import com.qixiao.bm.widget.BMDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.ViewHolder> {


    Context context;
    List<DBFriendBean> data;
    OnItenLClickListener clickListener;
    int currentDay;
    int currentMonth;
    int currentYear;

    public void setClickListener(OnItenLClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLongClickListener(OnItenLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    OnItenLongClickListener longClickListener;

    public BirthdayAdapter(Context context, List<DBFriendBean> data) {
        this.context = context;
        this.data = data;
        Calendar calendar = Calendar.getInstance();

        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH)+1;
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

    }

    public  void  setData(List<DBFriendBean> data){
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        DBFriendBean bean =data.get(i);
        String name =bean.getName()!=null?bean.getName():" ";
        int age = bean.getAge();

        int month = bean.getMonth();
        int day = bean.getDay();
        int year = bean.getYear();

        Log.e("tag","item:"+year+"-"+month+"-"+day);
        CalendarUtil.lunar_t currentBir = new CalendarUtil.lunar_t();
        if (bean.getSolar()==2)
            currentBir= CalendarUtil.toSolar(new CalendarUtil.lunar_t(currentYear,month,day));
        else {
            currentBir.setYear(currentYear);
            currentBir.setMonth(month);
            currentBir.setDay(day);
        }

        int between =CalendarUtil.dayToDay(currentYear,currentMonth,currentDay,currentYear,currentBir.getMonth(),currentBir.getDay());
      if (between<0){
          if (bean.getSolar()==2){
              CalendarUtil.lunar_t  lunar_t=CalendarUtil.toSolar(new CalendarUtil.lunar_t(currentYear+1,month,day));
              between = CalendarUtil.dayToDay(currentYear,currentMonth,currentDay,currentYear+1,lunar_t.getMonth(),lunar_t.getDay());
          }else {
              between = CalendarUtil.dayToDay(currentYear,currentMonth,currentDay,currentYear+1,month,day);
          }

      }

        viewHolder.mNameTv.setText(name);

      viewHolder.mDateTv.setText(between+"天");
        viewHolder.mAgeTv.setText("天后过生日");
        viewHolder.mBirthdayTv.setText(month+"月"+day);
        RequestOptions options = RequestOptions
                .circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        if (bean.getSolar()==1){
            Glide.with(context).load(R.mipmap.solar).apply(options).into(viewHolder.mCalendarIv);
        }else {
            Glide.with(context).load(R.mipmap.lunar).apply(options).into(viewHolder.mCalendarIv);
        }

        Glide.with(context).load(R.mipmap.ic_birthday_friend_icon).apply(options).into(viewHolder.mIconIv);


        viewHolder.mLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickListener.onItemLongClick(i);
             return true;
            }
        });
        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.layout_rv_item)
        RelativeLayout mLayout;
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
    public interface OnItenLongClickListener{
        void onItemLongClick(int position);
    }
    public interface OnItenLClickListener{
        void onItemClick(int posotion);
    }
}
