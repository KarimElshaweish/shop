package com.example.root.shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by root on 1/19/18.
 */

public class UserSessionManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _Context;
    int PRIVATE_MODE=0;
      static final String PREER_NAME="AndroidExamplePref";
     static final String IS_USER_LOGIN="isUserLogIn";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PASSWORD="password";
    public UserSessionManager(Context context){
        this._Context=context;
        pref=_Context.getSharedPreferences(PREER_NAME,PRIVATE_MODE);
        editor=pref.edit();
    }
    public void CreatUserLoginSession(String Email,String Password){
        editor.putBoolean(IS_USER_LOGIN,true);
        editor.putString(KEY_EMAIL,Email);
        editor.putString(KEY_PASSWORD,Password);
        editor.commit();
    }
    public boolean checkLogin(){
        if(!this.IsUserLogiedIn()){
            Intent intent =new Intent(_Context,loginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _Context.startActivity(intent);
            return true;
        }
        return false;
    }
    public boolean IsUserLogiedIn(){
        return pref.getBoolean(IS_USER_LOGIN,false);
    }
    public HashMap<String,String>getUserDatails(){
        HashMap<String,String>user=new HashMap<>();
        user.put(KEY_EMAIL,pref.getString(KEY_EMAIL,null));
        user.put(KEY_PASSWORD,pref.getString(KEY_PASSWORD,null));
        return user;
    }
    public void LogigOut(){
        editor.clear();
        editor.commit();
        Intent intent=new Intent(_Context,loginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _Context.startActivity(intent);
    }
}
