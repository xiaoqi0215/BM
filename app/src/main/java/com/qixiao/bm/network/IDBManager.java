package com.qixiao.bm.network;

import android.content.ContentValues;

import com.qixiao.bm.bean.db.DBFriendBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface IDBManager {

        Observable<List<DBFriendBean>> queryFriendAll();
        Observable<Boolean> insertOne(String tableName, ContentValues values);
        Observable<DBFriendBean> isHasFriend(String name);
        Observable<Boolean> deleteFriend(String name);
        Observable<Boolean> updataFriend(ContentValues values,String name);
}
