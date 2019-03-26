package com.qixiao.bm.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.TextView;

import com.qixiao.bm.BMApplication;

public class SharedPreferencesUtils {
    SharedPreferences sp;
    SharedPreferences.Editor editor ;
    public SharedPreferencesUtils(Context context){
        sp = context.getSharedPreferences("bm", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }
    public void setString(String key,String  value){
        if(TextUtils.isEmpty(value)){
            return;
        }
        editor.putString(key,value);
        editor.commit();
    }
    public void setInt(String key,int  value){
        editor = sp.edit();
        editor.putInt(key,value);
        editor.commit();
    }
    public void setBoolean(String key,boolean  value){
        editor = sp.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public boolean getBoolean(String key,boolean defult){
       return sp.getBoolean(key,defult);
    }
    public String getString(String key){
        return sp.getString(key,"");
    }
    public int getInt(String key){
        return sp.getInt(key,0);
    }

}
