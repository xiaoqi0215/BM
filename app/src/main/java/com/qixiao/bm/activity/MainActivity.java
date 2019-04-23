package com.qixiao.bm.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qixiao.bm.BMApplication;
import com.qixiao.bm.BMContants;
import com.qixiao.bm.R;
import com.qixiao.bm.Utils.CalendarUtil;
import com.qixiao.bm.Utils.RemindUtils;
import com.qixiao.bm.Utils.SQLiteUtils;
import com.qixiao.bm.base.BaseActivity;
import com.qixiao.bm.bean.db.DBFriendBean;
import com.qixiao.bm.contract.MainActivityContract;
import com.qixiao.bm.fragment.BirthdayFragment;
import com.qixiao.bm.fragment.DynamicFragment;
import com.qixiao.bm.fragment.MyFragment;
import com.qixiao.bm.widget.BMDialog;
import com.qixiao.bm.widget.MyActionBar;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity<MainActivityContract.Presenter> implements MainActivityContract.View {

    @BindView(R.id.main_mytab)
    RadioGroup rb;
    @BindView(R.id.main_tab_my)
    RadioButton rbMy;
    @BindView(R.id.main_tab_dynamic)
    RadioButton rbDynamic;
    @BindView(R.id.main_tab_birthday)
    RadioButton rbBirthday;

    @BindView(R.id.action_bar_myroot)
    MyActionBar actionBar;
    ImageView mTabAdd;
    PopupWindow popupWindow;
    View mpopupWindowView;
    List<Fragment> fragments;
    String[] title = {"好友生日", "动态", "个人中心"};
    ListView listView;

    int mFragmentindex = 0;
    @BindView(R.id.main_fragmentlayout)
    FrameLayout mainFragmentlayout;
    @BindView(R.id.layout_mian_default)
    LinearLayout layoutMianDefault;

    List<DBFriendBean> friendData;


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.equals("login")){
            setDefault();
        }else{
            SQLiteUtils.createDB();
            initContentView();
        }

    }

    private void setDefault() {
        actionBar.hihLeft();
        actionBar.hihRight();
     //   layoutMianDefault.setVisibility(View.VISIBLE);
        actionBar.setTitleText("生日管家");
        final BMDialog dialog = new BMDialog(this);
        dialog.show();
        dialog.setDialogMessage("是否同步账号信息？");
        dialog.setOnConfirmClickListener(new BMDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                //调用接口同步信息

                //缓存数据
                SQLiteUtils.createDB();
                downFromInternet();
                initContentView();
                dialog.dismiss();
            }
        });
        dialog.setOnCancelClickListener(new BMDialog.OnCancelClickListener() {
            @Override
            public void onCancelClick() {
                dialog.dismiss();
                BMApplication.exit();

            }
        });
    }
    private void downFromInternet() {
        friendData = new ArrayList<>();
        ContentValues cv = new ContentValues();
        cv.put("name", "qiqi");
        cv.put("solar", 1);
        cv.put("age", 12);
        cv.put("way", 1);
        cv.put("year", 2019);
        cv.put("month", 4);
        cv.put("day", 13);
        cv.put("userId", 1);
        cv.put("tel", "152490155");
      //  SQLiteUtils.insert(cv, "friend");

            DBFriendBean bean = new DBFriendBean();
            bean.setName("qiqi");
            bean.setYear(2019);
            bean.setMonth(4);
            bean.setDay(12);
            bean.setWay(1);
            friendData.add(bean);

    }



    private void initContentView() {
        if (msp.getLong("calanderAccount")!=0){
            long  calanderAccount = RemindUtils.addCalendarAccount(BMApplication.getContext());
            msp.setLong("calanderAccount",calanderAccount);
        }

        layoutMianDefault.setVisibility(View.GONE);
        mainFragmentlayout.setVisibility(View.VISIBLE);

        mTabAdd = actionBar.getRighttv();
        FragmentTransaction transaction;
        mTabAdd.setOnClickListener(this);
        fragments = new ArrayList<>();
        MyFragment myFragment = new MyFragment();
        DynamicFragment dynamicFragment = new DynamicFragment();
        BirthdayFragment birthdayFragment = new BirthdayFragment();
        fragments.add(birthdayFragment);
        fragments.add(dynamicFragment);
        fragments.add(myFragment);

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_fragmentlayout, birthdayFragment).commit();

        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_tab_my:
                        Toast.makeText(MainActivity.this, "my", Toast.LENGTH_SHORT).show();
                        rbMy.setChecked(true);
                        addFragment(2);
                        actionBar.hihRight();
                        break;
                    case R.id.main_tab_dynamic:
                        Toast.makeText(MainActivity.this, "a", Toast.LENGTH_SHORT).show();
                        rbDynamic.setChecked(true);
                        addFragment(1);
                        actionBar.hihRight();
                        break;
                    case R.id.main_tab_birthday:
                        Toast.makeText(MainActivity.this, "s", Toast.LENGTH_SHORT).show();
                        rbBirthday.setChecked(true);
                        addFragment(0);
                        actionBar.showRight();
                        break;
                }
            }
        });
        rbBirthday.setChecked(true);
    }



    void addFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mFragmentindex == index) {
            return;
        }
        transaction.hide(fragments.get(mFragmentindex));
        if (!(fragments.get(index).isAdded())) {
            actionBar.setTitleText(title[index]);
            transaction.add(R.id.main_fragmentlayout, fragments.get(index)).show(fragments.get(index));
        } else {
            actionBar.setTitleText(title[index]);
            transaction.show(fragments.get(index));
        }
        mFragmentindex = index;
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_base_activity_actionbar_right:
                showPopWindow();
        }

    }

    void showPopWindow() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        mpopupWindowView = LayoutInflater.from(mContext).inflate(R.layout.menu_main_tab, null);
        listView = mpopupWindowView.findViewById(R.id.lv_birthday_tab_menu);
        mpopupWindowView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        popupWindow = new PopupWindow(mpopupWindowView, 3 * width / 11, ViewGroup.LayoutParams.WRAP_CONTENT); //设置popupWindow 的大小
        List<String> menuData = new ArrayList<>();
        menuData.add("添加好友");
        listView.setAdapter(new ArrayAdapter<String>(mContext,
                R.layout.list_item_menu_main, R.id.tv_list_item_birthday, menuData));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                //    showToast(position + "");
                    //Intent intent = new Intent(MainActivity.this,AddFriendActivity.class);
                    toActivity(SendSmsActivity.class);
                } else if (position == 1) {
                 //   showToast(position + "");
                }
            }
        });
        int[] location = new int[2];
        mTabAdd.getLocationOnScreen(location);

        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());//最好加上这一句，因为他可以取消显示这个弹出菜单，不加的话，弹出菜单很难消失


        int viewH = (int) mTabAdd.getHeight();
        popupWindow.showAtLocation(mpopupWindowView, Gravity.NO_GRAVITY
                , location[0], location[1] + viewH + 20);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
