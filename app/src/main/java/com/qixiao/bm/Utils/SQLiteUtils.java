package com.qixiao.bm.Utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.qixiao.bm.BMApplication;

import java.util.List;

public class SQLiteUtils {

    public static SQLiteDatabase db;


    public static void createDB() {
        if (db!=null){
            return ;
        }else
        db= new SQLiteDBHelper(BMApplication.getContext(),"bm.db",null,1).getReadableDatabase();
    }

    public static void insert(ContentValues cValue, String tableName){
            if (cValue !=null){
                db.insert(tableName,null,cValue);

            }
        }

    public static void delete(String tableName,String col,String[] valus) {
        db.delete(tableName,col,valus);
    }

    public static void update(String tableName,ContentValues values,String whereClause, String[] whereArgs){

        db.update(tableName,values,whereClause,whereArgs);
    }
    public static Cursor  query(String tableName, String[] columns, String selection,
                                String[] selectionArgs, String groupBy,String orderBy){

        Cursor cursor =db.query(tableName, columns, selection, selectionArgs, groupBy,null, orderBy);
        return cursor;
    }

    public static Cursor  query(String sql){

        Cursor cursor =db.rawQuery(sql,null);
        return cursor;
    }
    public static Cursor  query(String sql,String[] strings){

        Cursor cursor =db.rawQuery(sql,strings);
        return cursor;
    }


    public static void close(){

        //执行SQL
        db.close();
    }
    public static void drop(){
        SQLiteDBHelper.deleteDatabase(BMApplication.getContext());
    }

}
