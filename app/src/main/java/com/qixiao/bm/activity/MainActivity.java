package com.qixiao.bm.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qixiao.bm.base.BasePresenter;
import com.qixiao.bm.contract.MainActivityContract;
import com.qixiao.bm.fragment.BirthdayFragment;
import com.qixiao.bm.fragment.DynamicFragment;
import com.qixiao.bm.fragment.MyFragment;
import com.qixiao.bm.R;
import com.qixiao.bm.base.BaseActivity;
import com.qixiao.bm.widget.MyActionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


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
    List<Fragment> fragments ;
    String[] title = {"好友生日","动态","个人中心"};
    ListView listView;

    int mFragmentindex = 0;


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {
        actionBar.hihLeft();
        mTabAdd =actionBar.getRighttv();
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
        transaction.add(R.id.main_fragmentlayout,birthdayFragment).commit();

       rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch (checkedId){
                   case R.id.main_tab_my:
                       Toast.makeText(MainActivity.this,"my",Toast.LENGTH_SHORT).show();
                       rbMy.setChecked(true);
                       addFragment(2);
                       break;
                   case R.id.main_tab_dynamic:
                       Toast.makeText(MainActivity.this,"a",Toast.LENGTH_SHORT).show();
                       rbDynamic.setChecked(true);
                       addFragment(1);
                       break;
                   case R.id.main_tab_birthday:
                       Toast.makeText(MainActivity.this,"s",Toast.LENGTH_SHORT).show();
                       rbBirthday.setChecked(true);
                       addFragment(0);
                       break;
               }
           }
       });
        rbBirthday.setChecked(true);
    }

    void  addFragment(int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        if (mFragmentindex == index){
            return;
        }
        transaction.hide(fragments.get(mFragmentindex));
        if (!(fragments.get(index).isAdded())){
            actionBar.setTitleText(title[index]);
            transaction.add(R.id.main_fragmentlayout,fragments.get(index)).show(fragments.get(index));
        }else {
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
        switch (v.getId()){
            case R.id.iv_base_activity_actionbar_right:
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
                    toActivity(AddFriendActivity.class);
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

    }


}
