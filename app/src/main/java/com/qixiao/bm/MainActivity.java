package com.qixiao.bm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qixiao.bm.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity {
    @BindView(R.id.main_mytab)
    RadioGroup rb;
    @BindView(R.id.main_tab_my)
    RadioButton rbMy;
    @BindView(R.id.main_tab_dynamic)
    RadioButton rbDynamic;
    @BindView(R.id.main_tab_birthday)
    RadioButton rbBirthday;

    List<Fragment> fragments ;
    

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
        FragmentTransaction transaction;
        
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
            transaction.add(R.id.main_fragmentlayout,fragments.get(index)).show(fragments.get(index));
        }else {
            transaction.show(fragments.get(index));
        }
        mFragmentindex = index;
        transaction.commit();
    }


    @Override
    protected void initData() {

    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void setStatus(int status) {

    }

    @Override
    public void tologin(String msg) {

    }
}
