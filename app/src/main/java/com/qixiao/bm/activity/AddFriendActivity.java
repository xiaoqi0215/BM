package com.qixiao.bm.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.qixiao.bm.BMApplication;
import com.qixiao.bm.BMContants;
import com.qixiao.bm.R;
import com.qixiao.bm.Utils.CalendarUtil;
import com.qixiao.bm.Utils.ImageUtils;
import com.qixiao.bm.Utils.RemindUtils;
import com.qixiao.bm.Utils.SQLiteUtils;
import com.qixiao.bm.base.BaseActivity;
import com.qixiao.bm.contract.AddFriendContract;
import com.qixiao.bm.contract.AddFriendPresenter;
import com.qixiao.bm.network.DBManager;
import com.qixiao.bm.widget.BMDialog;
import com.qixiao.bm.widget.MyActionBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.IllegalFormatCodePointException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class AddFriendActivity extends BaseActivity<AddFriendPresenter> implements AddFriendContract.View {

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
    @BindView(R.id.rb_add_friend_solar)
    RadioButton rbAddFriendSolar;
    @BindView(R.id.rb_add_friend_lunar)
    RadioButton rbAddFriendLunar;
    @BindView(R.id.rg_add_friend_calardar)
    RadioGroup rgAddFriendCalardar;
    @BindView(R.id.tv_addfriend_time)
    TextView tvAddfriendTime;
    TimePickerView pvTime1;

    int mYear;
    int mMonth;
    int mDay;
    int mHour;
    int mMin;
    int mWay;
    String mName;
    int mAge;
    int mId;
    String mTel;
    String mIcon ;
    String mWord ;
    String type;
    int mSex;
    int mSolar ;
    String[] wayList= new String[]{"请选择提醒方式",BMContants.REMIND_ON_STEING,null,BMContants.REMIND_THREE_STEING,null,null,null,BMContants.REMIND_SEVEN_STEING};
    int[] solorList = new int[]{0,R.id.rb_add_friend_solar,R.id.rb_add_friend_lunar};
    int[] sexList = new int[]{0,R.id.rb_add_friend_male,R.id.rb_add_friend_female};

    @Override
    protected int getContentView() {
        return R.layout.fragment_add_friend;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new AddFriendPresenter(this, this);
    }

    @Override
    protected void initView() {
        initData();

        ImageUtils.loadIntCircleImg(this, R.mipmap.mine_user_icon_default, ivAddFriendIcon);
        tabAddFriend.hihRight();

    }

    private void initData() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if (TextUtils.equals(type,"edit")){
            mName=intent.getStringExtra(BMContants.DB_NAME);
            mTel=intent.getStringExtra(BMContants.DB_TEL);
            mWay=intent.getIntExtra(BMContants.DB_WAY,0);
            mYear=intent.getIntExtra(BMContants.DB_YEAR,0);
            mIcon= intent.getStringExtra(BMContants.DB_ICON);
            mSex = intent.getIntExtra(BMContants.DB_SEX,0);
            mWord= intent.getStringExtra(BMContants.DB_WORD);
            mMonth=intent.getIntExtra(BMContants.DB_MONTH,0);
            mSolar=intent.getIntExtra(BMContants.DB_SOLAR,0);
            mHour= intent.getIntExtra(BMContants.DB_HOUR,0);
            mMin=intent.getIntExtra(BMContants.DB_MIN,-1);
            mDay=intent.getIntExtra(BMContants.DB_DAY,0);
            etAddfriendName.setText(mName);
            etAddfriendTel.setText(TextUtils.isEmpty(mTel)?" ":mTel);
            etAddfriendWord.setText(TextUtils.isEmpty(mWord)?" ":mWord);
            tvAddfriendWay.setText(wayList[mWay]);
            tvAddfriendBirthday.setText(mYear+"-"+mMonth+"-"+mDay);
            tvAddfriendTime.setText(mHour+":"+mMin);
            rgAddFriendSex.check(sexList[mSex]);
            rgAddFriendCalardar.check(solorList[mSolar]);
        }
        return;
    }


    @Override
    public void onClick(View v) {


    }

    @OnClick({R.id.iv_add_friend_icon, R.id.btn_add_friend_add, R.id.tv_addfriend_birthday,
            R.id.tv_addfriend_way, R.id.iv_addfriend_tel,R.id.tv_addfriend_time})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_friend_icon:

                break;
            case R.id.btn_add_friend_add:
                checkImput();
                ContentValues values = new ContentValues();
                if (checkImput()){
                    int sex = 0;
                    switch (rgAddFriendSex.getCheckedRadioButtonId()){
                        case R.id.rb_add_friend_male:
                            sex = BMContants.SEX_MAN;
                            break;
                        case R.id.rb_add_friend_female:
                            sex = BMContants.SEX_FEMAN;
                            break;
                    }
                    int calendar = 0;
                    switch (rgAddFriendCalardar.getCheckedRadioButtonId()){
                        case R.id.rb_add_friend_solar:
                            calendar = BMContants.DB_SOLAR_SOLAR;
                            break;
                        case R.id.rb_add_friend_lunar:
                            calendar = BMContants.DB_SOLAR_LUNAR;
                            break;
                    }
                    if (tvAddfriendWay.getText().toString().equals(wayList[0])){
                        mWay= 0;
                    }else if (tvAddfriendWay.getText().toString().equals(wayList[1])){
                        mWay= 1;
                    }else if (tvAddfriendWay.getText().toString().equals(wayList[2])){
                        mWay= 3;
                    } else if (tvAddfriendWay.getText().toString().equals(wayList[3])){
                        mWay= 7;
                    }
                    values.put(BMContants.DB_NAME,etAddfriendName.getText().toString());
                    values.put(BMContants.DB_USERID,msp.getInt(BMContants.USER_ID));
                    values.put(BMContants.DB_TEL,etAddfriendTel.getText().toString()!=null);
                    values.put(BMContants.DB_SEX,sex);
                    values.put(BMContants.DB_SOLAR,calendar);
                    values.put(BMContants.DB_YEAR,mYear);
                    values.put(BMContants.DB_MONTH,mMonth);
                    values.put(BMContants.DB_DAY,mDay);
                    values.put(BMContants.DB_HOUR,mHour);
                    values.put(BMContants.DB_MIN,mMin);


                    Log.e("添加：TAG",mYear+"-"+mMonth+"-"+mDay+"-"+mHour+":"+mMin);
                    values.put(BMContants.DB_WAY,mWay);
                    if (!etAddfriendTel.getText().toString().isEmpty()){
                        values.put(BMContants.DB_TEL,etAddfriendTel.getText().toString());
                    }
                    if (!etAddfriendWord.getText().toString().isEmpty()){
                        values.put(BMContants.DB_WORD,etAddfriendWord.getText().toString());
                    }
                    if (TextUtils.equals(type,"edit")){
                        DBManager.getInstance().updataFriend(values,etAddfriendName.getText().toString())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<Boolean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Boolean aBoolean) {
                                        Intent intent = new Intent();
                                        intent.putExtra(BMContants.DB_NAME,etAddfriendName.getText().toString());
                                        setResult(RESULT_OK,intent);
                                        finish();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });

                    }else {
                        DBManager.getInstance().insertOne("friend",values)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<Boolean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Boolean aBoolean) {
                                        Intent intent = new Intent();
                                        intent.putExtra(BMContants.DB_NAME,etAddfriendName.getText().toString());
                                        setResult(RESULT_OK,intent);
                                        finish();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }



                break;
            case R.id.tv_addfriend_time:
                initTimePicker2();
                break;
            case R.id.tv_addfriend_birthday:
                initTimePicker1();
                break;
            case R.id.tv_addfriend_way:
                final BMDialog dialog = new BMDialog(this);

                dialog.show();
                dialog.showRemind();

                dialog.setOnConfirmClickListener(new BMDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        final String wayTemp = dialog.getRemidWay();
                        dialog.dismiss();
                        dialog.setOnConfirmClickListener(new BMDialog.OnConfirmClickListener() {
                            @Override
                            public void onConfirmClick() {
                                switch (rgAddFriendCalardar.getCheckedRadioButtonId()){
                                    case R.id.rb_dialog_one:
                                        mWay = 1;
                                        break;
                                    case R.id.rb_dialog_three:
                                        mWay = 3;
                                        break;
                                    case R.id.rb_dialog_seven:
                                        mWay = 7;
                                        break;
                                }
                            }
                        });
                        tvAddfriendWay.setText(wayTemp);
                    }
                });
               dialog.setOnCancelClickListener(new BMDialog.OnCancelClickListener() {
                   @Override
                   public void onCancelClick() {
                       dialog.dismiss();
                   }
               });

                break;
            case R.id.iv_addfriend_tel:
                break;
        }

    }

    private boolean checkImput() {
        if (etAddfriendTel.getText().toString()==null){
            showToast("请输入姓名");
            return false;
        }
        if (rgAddFriendSex.getCheckedRadioButtonId()==-1){
            showToast("请选择性别");
            return false;
        }
        if (rgAddFriendCalardar.getCheckedRadioButtonId()==-1){
            showToast("请选择生日类别");
            return false;
        }
        if (tvAddfriendBirthday.equals("请选择生日")){
            showToast("请选择生日");
            return false;
        }
        if (tvAddfriendWay.equals("请选择提醒方式")){
            showToast("请选择提醒方式");
            return  false;
        }
        if (tvAddfriendTime.equals("请选择提醒的时间段")){
            showToast("请选择提醒的时间段");
            return false;
        }
        return true;

    }


    @Override
    public void testScuess(String name) {
        showToast(name);
    }


    private void initTimePicker1() {//选择出生年月日


        Date curDate = new Date(System.currentTimeMillis());//获取当前时间

        SimpleDateFormat formatter_year = new SimpleDateFormat("yyyy ");

        String year_str = formatter_year.format(curDate);

        int year_int = (int) Double.parseDouble(year_str);



        SimpleDateFormat formatter_mouth = new SimpleDateFormat("MM ");

        String mouth_str = formatter_mouth.format(curDate);

        int mouth_int = (int) Double.parseDouble(mouth_str);


        SimpleDateFormat formatter_day = new SimpleDateFormat("dd ");

        String day_str = formatter_day.format(curDate);

        final int day_int = (int) Double.parseDouble(day_str);



        Calendar selectedDate = Calendar.getInstance();//系统当前时间

        Calendar startDate = Calendar.getInstance();

        startDate.set(1900, 0, 1);

        Calendar endDate = Calendar.getInstance();

        endDate.set(year_int, mouth_int - 1, day_int);



        //时间选择器

        pvTime1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {

            @Override

            public void onTimeSelect(Date date, View v) {//选中事件回调

                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null

                /*btn_Time.setText(getTime(date));*/

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);


                 mYear = calendar.get(Calendar.YEAR);
                 mMonth = calendar.get(Calendar.MONDAY)+1;
                mDay = calendar.get(Calendar.DATE);

                tvAddfriendBirthday.setText(getTime(date));

            }

        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示

                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒

                .isCenterLabel(false)

                .setDividerColor(Color.RED)

                .setTextColorCenter(Color.RED)//设置选中项的颜色

                .setTextColorOut(Color.BLACK)//设置没有被选中项的颜色

                .setContentSize(21)

                .setDate(selectedDate)

                .setLineSpacingMultiplier(1.2f)

                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]

                .setRangDate(startDate, endDate)

//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色

                .setDecorView(null)

                .build();
        pvTime1.show();

    }

    private void initTimePicker2(){
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {

            @Override

            public void onTimeSelect(Date date,View v) {//选中事件回调


                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                mHour = calendar.get(Calendar.HOUR);
                mMin =calendar.get(Calendar.MINUTE);
                tvAddfriendTime.setText(getHourAndMin(date));

            }

        }) .setType(new boolean[]{false, false, false, true, true, false}) //年月日时分秒 的显示与否，不设置则默认全部显示

                .setLabel(" ", " ", " ", "时", "分", "")//默认设置为年月日时分秒

                .isCenterLabel(false)

                .setDividerColor(Color.RED)

                .setTextColorCenter(Color.RED)//设置选中项的颜色

                .setTextColorOut(Color.BLACK)//设置没有被选中项的颜色

                .setContentSize(21)

                .setLineSpacingMultiplier(1.2f)

                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]


                .setDecorView(null)

                .build();


        pvTime.show();

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(date);

    }
    private String getHourAndMin(Date date) {//可根据需要自行截取数据显示

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        return format.format(date);

    }


}
