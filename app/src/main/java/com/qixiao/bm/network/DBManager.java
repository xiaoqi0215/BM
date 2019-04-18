package com.qixiao.bm.network;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import com.qixiao.bm.BMApplication;
import com.qixiao.bm.BMContants;
import com.qixiao.bm.Utils.CalendarUtil;
import com.qixiao.bm.Utils.RemindUtils;
import com.qixiao.bm.Utils.SQLiteUtils;
import com.qixiao.bm.Utils.SharedPreferencesUtils;
import com.qixiao.bm.base.BaseRxManager;
import com.qixiao.bm.base.BaseView;
import com.qixiao.bm.bean.db.DBFriendBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;

public class DBManager implements IDBManager {


    private static DBManager manager;

    private DBManager() {
    }

    private static SharedPreferencesUtils sharedPreferencesUtils;
    public  static IDBManager getInstance(){
        if (manager == null){
            synchronized (RetrofitManager.class){
                if (manager==null){
                    manager = new DBManager();
                    sharedPreferencesUtils = new SharedPreferencesUtils(BMApplication.getContext());
                }
            }
        }
        return manager;
    }

    private void setCanlenarEvent(String name, final int year, final int month, final int day, int hour, int mintue, int way) {


        long accountId =sharedPreferencesUtils.getLong("calanderAccount");
        if (accountId==0) {
            return;
        }
        String wayString = name+ "的生日到了";
        int time= 0;
        if (way== BMContants.REMIND_ONE_INT) {
            time = 86400*1;
            wayString =name + "的生日还有一天就要到了";
        }else if (way==BMContants.REMIND_THREE_INT){
            time = 86400*3;
            wayString = name + "的生日还有三天就要到了";
        }else if (way==BMContants.REMIND_SEVEN_INT){
            time = 86400*7;
            wayString = name+ "的生日还有七天就要到了";
        }
        String title =name+"的生日";
        //开始时间
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR,year);
        mCalendar.set(Calendar.MONTH,month-1);
        mCalendar.set(Calendar.DAY_OF_MONTH,day);
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
        mCalendar.set(Calendar.MINUTE,mintue);
        long start =  mCalendar.getTimeInMillis();
        //截止时间，如果需要设置一周或者一个月可以改截止日期即可
        mCalendar.set(Calendar.HOUR_OF_DAY, 20);
        mCalendar.set(Calendar.MINUTE,10);
        long end = mCalendar.getTimeInMillis();

        Log.e("CanlenarEvent",year+"  "+(month-1)+" "+day);
        RemindUtils.insertCalendarEvent(BMApplication.getContext(),accountId,title,null, start,end);

        RemindUtils.addCalendarEventRemind(BMApplication.getContext(), title, wayString, start, end, 1, new RemindUtils.onCalendarRemindListener() {
            @Override
            public void onFailed(Status error_code) {

            }

            @Override
            public void onSuccess() {
                Log.e("CanlenarEvent",year+"-"+month+"-"+day+"事件提醒添加成功");
            }
        });
    }

    @Override
    public Observable<List<DBFriendBean>> queryFriendAll() {
        return Observable.create(new
        ObservableOnSubscribe<List<DBFriendBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<DBFriendBean>> emitter) throws Exception {
                Cursor cursor = SQLiteUtils.query(BMContants.DBFRIEND,null,null,null,null,null);
                List<DBFriendBean> dbData= new ArrayList<>();
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        DBFriendBean dbFriendBean =new DBFriendBean();
                        String friendName = cursor.getString(cursor.getColumnIndex("name"));
                        int friendAge = cursor.getInt(cursor.getColumnIndex("age"));
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String friendTel = cursor.getString(cursor.getColumnIndex("tel"));
                        int friendWay = cursor.getInt(cursor.getColumnIndex("way"));
                        int friendYear = cursor.getInt(cursor.getColumnIndex("year"));
                        int userId = cursor.getInt(cursor.getColumnIndex("userId"));
                        String friendIcon = cursor.getString(cursor.getColumnIndex("icon"));
                        String word = cursor.getString(cursor.getColumnIndex("word"));
                        int friendMonth = cursor.getInt(cursor.getColumnIndex("month"));
                        int friendDay = cursor.getInt(cursor.getColumnIndex("day"));
                        int solar = cursor.getInt(cursor.getColumnIndex("solar"));
                        int hour = cursor.getInt(cursor.getColumnIndex("hour"));
                        int sex = cursor.getInt(cursor.getColumnIndex("sex"));
                        int mintue = cursor.getInt(cursor.getColumnIndex("mintue"));

                        dbFriendBean.setSolar(solar);
                        dbFriendBean.setAge(friendAge);
                        dbFriendBean.setId(id);
                        dbFriendBean.setUserId(userId);
                        dbFriendBean.setName(friendName);
                        dbFriendBean.setYear(friendYear);
                        dbFriendBean.setTel(friendTel);
                        dbFriendBean.setWay(friendWay);
                        dbFriendBean.setIcon(friendIcon);
                        dbFriendBean.setDay(friendDay);
                        dbFriendBean.setMonth(friendMonth);
                        dbFriendBean.setSex(sex);
                        dbFriendBean.setHour(hour);
                        dbFriendBean.setMintue(mintue);
                        dbFriendBean.setWord(word);


                        dbData.add(dbFriendBean);
                    }
                }
                emitter.onNext(dbData);
                emitter.onComplete();
                for (int i = 0 ;i < dbData.size();i++){
                    DBFriendBean temp = dbData.get(i);
                    int year = temp.getYear();
                    int month = temp.getMonth();
                    int day = temp.getDay();
                    int hour = temp.getHour();
                    int mintue = temp.getMintue();
                    int way = temp.getWay();
                    if (temp.getSolar()==2){
                        for (int j = 2018;j< 2022;j++){
                            CalendarUtil.lunar_t calendar =CalendarUtil.toSolar(new CalendarUtil.lunar_t(j,month,day));
                            setCanlenarEvent(temp.getName(),calendar.getYear(),calendar.getMonth(),calendar.getDay(),hour,mintue,way);
                        }
                    }else {
                        for (int j = 2018;j< 2022;j++){
                            setCanlenarEvent(temp.getName(),j,month,day,hour,mintue,way);
                        }
                    }
                }

            }
        }).subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<Boolean> insertOne(final String tableName, final ContentValues values) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
              SQLiteUtils.insert(values,tableName);
              emitter.onNext(true);
              emitter.onComplete();
              String name =values.getAsString(BMContants.DB_NAME);
              int year = values.getAsInteger(BMContants.DB_YEAR);
              int month = values.getAsInteger(BMContants.DB_MONTH);
              int day = values.getAsInteger(BMContants.DB_DAY);
              int hour = values.getAsInteger(BMContants.DB_HOUR);
              int mintue = values.getAsInteger(BMContants.DB_MIN);
              int way = values.getAsInteger(BMContants.DB_WAY);
              int solor= values.getAsInteger(BMContants.DB_SOLAR);
                    if (solor==2){
                        for (int j = 2017;j< 2022;j++){
                            CalendarUtil.lunar_t calendar =CalendarUtil.toSolar(new CalendarUtil.lunar_t(j,month,day));
                            setCanlenarEvent(name,calendar.getYear(),calendar.getMonth(),calendar.getDay(),hour,mintue,way);
                        }
                    }else {
                        for (int j = 2017;j< 2022;j++){
                            setCanlenarEvent(name,j,month,day,hour,mintue,way);
                        }
                    }
            }
        });
    }

    @Override
    public Observable<DBFriendBean> isHasFriend(final String name) {
        return Observable.create(new ObservableOnSubscribe<DBFriendBean>() {
            @Override
            public void subscribe(ObservableEmitter<DBFriendBean> emitter) throws Exception {
                String sql = "select * from friend where name="+"'"+name+"'";

                Cursor cursor = SQLiteUtils.query(sql);
//                Cursor cursor = SQLiteUtils.query(BMContants.DBFRIEND,new String[]{"name"},"name=?",new String[]{name},null,null);
//                Log.e("TAG",name);

                DBFriendBean bean = new DBFriendBean();

                Log.e("TAG",cursor.getCount()+"vv");
                if (cursor.getCount()>=1){
                    cursor.moveToNext();
                    String friendName = cursor.getString(cursor.getColumnIndex("name"));
                    int friendAge = cursor.getInt(cursor.getColumnIndex("age"));
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String friendTel = cursor.getString(cursor.getColumnIndex("tel"));
                    int friendWay = cursor.getInt(cursor.getColumnIndex("way"));
                    int friendYear = cursor.getInt(cursor.getColumnIndex("year"));
                    int userId = cursor.getInt(cursor.getColumnIndex("userId"));
                    String friendIcon = cursor.getString(cursor.getColumnIndex("icon"));
                    String word = cursor.getString(cursor.getColumnIndex("word"));
                    int friendMonth = cursor.getInt(cursor.getColumnIndex("month"));
                    int friendDay = cursor.getInt(cursor.getColumnIndex("day"));
                    int solar = cursor.getInt(cursor.getColumnIndex("solar"));
                    bean.setSolar(solar);
                    bean.setAge(friendAge);
                    bean.setId(id);
                    bean.setUserId(userId);
                    bean.setName(friendName);
                    bean.setYear(friendYear);
                    bean.setTel(friendTel);
                    bean.setWay(friendWay);
                    bean.setIcon(friendIcon);
                    bean.setDay(friendDay);
                    bean.setMonth(friendMonth);
                    emitter.onNext(bean);
                    emitter.onComplete();
                }
            }
        })
        .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> deleteFriend(final String name) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                SQLiteUtils.delete("friend","name=?",new String[]{name});
                emitter.onNext(true);
                RemindUtils.deleteCalendarEventRemind(BMApplication.getContext(),name+"的生日",null,null);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> updataFriend(final ContentValues values, final String name) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                SQLiteUtils.update("friend",values,"name=?",new String[]{name});
                emitter.onNext(true);
                emitter.onComplete();
                RemindUtils.deleteCalendarEventRemind(BMApplication.getContext(),name+"的生日",null,null);
                String name =values.getAsString(BMContants.DB_NAME);
                int year = values.getAsInteger(BMContants.DB_YEAR);
                int month = values.getAsInteger(BMContants.DB_MONTH);
                int day = values.getAsInteger(BMContants.DB_DAY);
                int hour = values.getAsInteger(BMContants.DB_HOUR);
                int mintue = values.getAsInteger(BMContants.DB_MIN);
                int way = values.getAsInteger(BMContants.DB_WAY);
                int solor= values.getAsInteger(BMContants.DB_SOLAR);
                if (solor==2){
                    for (int j = 2017;j< 2022;j++){
                        CalendarUtil.lunar_t calendar =CalendarUtil.toSolar(new CalendarUtil.lunar_t(j,month,day));
                        setCanlenarEvent(name,calendar.getYear(),calendar.getMonth(),calendar.getDay(),hour,mintue,way);
                    }
                }else {
                    for (int j = 2017;j< 2022;j++){
                        setCanlenarEvent(name,j,month,day,hour,mintue,way);
                    }
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
