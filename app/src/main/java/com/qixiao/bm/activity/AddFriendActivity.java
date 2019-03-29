package com.qixiao.bm.activity;

import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qixiao.bm.R;
import com.qixiao.bm.Utils.CalendarUtil;
import com.qixiao.bm.Utils.RemindUtils;
import com.qixiao.bm.Utils.ImageUtils;
import com.qixiao.bm.base.BaseActivity;
import com.qixiao.bm.contract.AddFriendContract;
import com.qixiao.bm.contract.AddFriendPresenter;
import com.qixiao.bm.widget.MyActionBar;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class AddFriendActivity extends BaseActivity<AddFriendPresenter>implements AddFriendContract.View {

    @BindView(R.id.iv_add_friend_icon)
    ImageView ivAddFriendIcon;
    @BindView(R.id.et_addfriend_name)
    EditText etAddfriendName;
    @BindView(R.id.rb_add_friend_female)
    RadioButton rbAddFriendFemale;
    @BindView(R.id.rb_add_friend_male)
    RadioButton rbAddFriendMale;
    @BindView(R.id.rg_add_friend_sex)
    RadioGroup rgAddFriendSex;
    @BindView(R.id.tv_addfriend_birthday)
    TextView tvAddfriendBirthday;
    @BindView(R.id.tv_addfriend_way)
    TextView tvAddfriendWay;
    @BindView(R.id.et_addfriend_tel)
    EditText etAddfriendTel;
    @BindView(R.id.iv_addfriend_tel)
    ImageView ivAddfriendTel;
    @BindView(R.id.et_addfriend_word)
    EditText etAddfriendWord;
    @BindView(R.id.btn_add_friend_add)
    Button btnAddFriendAdd;
    @BindView(R.id.tab_add_friend)
    MyActionBar tabAddFriend;
    @BindView(R.id.tv_addfriend_word)
    TextView tvAddfriendWord;


    @Override
    protected int getContentView() {
        return R.layout.fragment_add_friend;
    }

    @Override
    protected void createPresenter() {
            mPresenter = new AddFriendPresenter(this,this);
    }

    @Override
    protected void initView() {
        ImageUtils.loadIntCircleImg(this, R.mipmap.mine_user_icon_default, ivAddFriendIcon);
        tabAddFriend.hihRight();
    }


    @Override
    public void onClick(View v) {



    }

    @OnClick({R.id.iv_add_friend_icon, R.id.btn_add_friend_add, R.id.tv_addfriend_birthday,
            R.id.tv_addfriend_way, R.id.iv_addfriend_tel})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_friend_icon:
                String y = etAddfriendName.getText().toString();
                String m = etAddfriendTel.getText().toString();
                String d = etAddfriendWord.getText().toString();
                CalendarUtil.lunar_t lunar = new CalendarUtil.lunar_t();
                lunar.setYear(Integer.parseInt(y));
                lunar.setMonth(Integer.parseInt(m));
                lunar.setDay(Integer.parseInt(d));
                lunar = CalendarUtil.toSolar(lunar);
                showToast(lunar.getYear()+"-"+lunar.getMonth()+"-"+lunar.getDay());

                break;
            case R.id.btn_add_friend_add:
               // mPresenter.test();
                long  id= RemindUtils.checkCalendarAccounts(this);
                if (id == -1){
                    id = RemindUtils.addCalendarAccount(this);
                }
                Calendar calendar = Calendar.getInstance();
                //开始时间
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.HOUR_OF_DAY, 16);
                mCalendar.set(Calendar.MINUTE,56);
                long start = mCalendar.getTime().getTime();
                //截止时间，如果需要设置一周或者一个月可以改截止日期即可
                mCalendar.set(Calendar.HOUR_OF_DAY, 17);
                mCalendar.set(Calendar.MINUTE,10);
                long end = mCalendar.getTime().getTime();
                RemindUtils.insertCalendarEvent(this,id,"事件","描述", start,end);
                RemindUtils.addCalendarEventRemind(this, "bba", "dd", start, end, 2, new RemindUtils.onCalendarRemindListener() {
                    @Override
                    public void onFailed(Status error_code) {

                    }

                    @Override
                    public void onSuccess() {

                    }
                });
                break;
            case R.id.tv_addfriend_birthday:
                break;
            case R.id.tv_addfriend_way:
                break;
            case R.id.iv_addfriend_tel:
                break;
        }

    }


    @Override
    public void testScuess(String name) {
        showToast(name);
    }
}
