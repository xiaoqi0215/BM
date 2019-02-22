package com.qixiao.bm;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.qixiao.bm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BirthdayFragment extends BaseFragment {

    PopupWindow popupWindow;
    View mpopupWindowView;


    @BindView(R.id.iv_birthday_tab_add)
    ImageView mTabAdd;

    ListView listView;

    @BindView(R.id.rv_birthday_friend)
    RecyclerView mFriendRv;

    BirthdayAdapter mAdapter;

    List<BirthdayListBean> data;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_birthday;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView(View rootView) {
        data = new ArrayList<>();
        for(int i = 0 ;i<5;i++){
            BirthdayListBean bean  = new BirthdayListBean();
            data.add(bean);
        }
        mAdapter = new BirthdayAdapter(getActivity(),data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mFriendRv.setLayoutManager(linearLayoutManager);
        mFriendRv.setAdapter(mAdapter);
        mFriendRv.addItemDecoration(new DividerItemDecoration(mContext,1));
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_birthday_tab_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_birthday_tab_add:

                showPopWindow();
        }
    }

    void showPopWindow () {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        mpopupWindowView = LayoutInflater.from(mContext).inflate(R.layout.menu_main_tab, null);
        listView = mpopupWindowView.findViewById(R.id.lv_birthday_tab_menu);
        mpopupWindowView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        popupWindow = new PopupWindow(mpopupWindowView, 3 * width / 11, ViewGroup.LayoutParams.WRAP_CONTENT); //设置popupWindow 的大小
        List<String> menuData = new ArrayList<>();
        menuData.add("添加好友");
        menuData.add("批量删除");
        listView.setAdapter(new ArrayAdapter<String>(mContext,
                R.layout.list_item_menu_main, R.id.tv_list_item_birthday, menuData));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    showToast(position+"");
                } else if (position == 1) {
                    showToast(position+"");
                }
            }
        });
        int[] location = new int[2];
        mTabAdd.getLocationOnScreen(location);

        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());//最好加上这一句，因为他可以取消显示这个弹出菜单，不加的话，弹出菜单很难消失


        int viewH = (int) mTabAdd.getHeight();
        popupWindow.showAtLocation(mpopupWindowView, Gravity.NO_GRAVITY
                , location[0], location[1]+viewH+20);

//        popupWindow.showAtLocation(mpopupWindowView, Gravity.NO_GRAVITY, location[0]+mpopupWindowView.getWidth(), location[1]);
        //显示在右边

        //popupWindow显示在左边
//        popupWindow.showAtLocation(mpopupWindowView, Gravity.NO_GRAVITY
//                , location[0] - popupWindow.getWidth(), location[1]);

    }

    @Override
    public void onClick(View v) {

    }
}
