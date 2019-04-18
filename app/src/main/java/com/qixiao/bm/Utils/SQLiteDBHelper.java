package com.qixiao.bm.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDBHelper extends SQLiteOpenHelper {
    private static String name;
    public SQLiteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_table="create table user(id integer primary key autoincrement,name VARCHAR(20),icon VARCHAR(300),tel VARCHAR(12),pwd VARCHAR(10))";

        String dynamic_table="create table dynamic(id integer primary key autoincrement,userId integer,contents VARCHAR(1500),picture varchar(1500),cteateTime DATA)";
        String friend_table="create table friend(id integer primary key autoincrement, name varchar(20),sex integer, hour integer,mintue integer,  solar integer,age integer,tel varchar(12) ,word  varchar(100),  icon  varchar(300),year  integer, month  integer,day  integer,way  integer,  userId  integer)"  ;

        //执行SQL语句
        db.execSQL(user_table);
        db.execSQL(friend_table);
        db.execSQL(dynamic_table);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static boolean deleteDatabase(Context context) {
        return context.deleteDatabase(name);
    }
}
