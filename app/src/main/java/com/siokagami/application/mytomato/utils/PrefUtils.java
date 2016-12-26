package com.siokagami.application.mytomato.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.siokagami.application.mytomato.base.TomatoApplication;
import com.siokagami.application.mytomato.base.TomatoConstants;


public class PrefUtils {
    public static String TAG = PrefUtils.class.getName();

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;
    public static SharedPreferences userRelatesharedPreferences;
    public static SharedPreferences.Editor userRelatesharedPreferencesEditor;

//    static {
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(TomatoApplication.instance);
//        sharedPreferencesEditor = sharedPreferences.edit();
//
//        //和用户相关的缓存,退出时会统一清理掉
//        userRelatesharedPreferences = TomatoApplication.instance.getSharedPreferences("tomato_pref", Context.MODE_PRIVATE);
//        userRelatesharedPreferencesEditor = userRelatesharedPreferences.edit();
//    }


    public static long getMyTomatoWorkTime(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getLong(TomatoConstants.MY_TOMATO_COUNT_INFO.TOMATO_WORK, 10000L);
    }

    public static void setMyTomatoWorkTime(Context context, long workTime) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(TomatoConstants.MY_TOMATO_COUNT_INFO.TOMATO_WORK, workTime);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long getMyTomatoRestTime(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getLong(TomatoConstants.MY_TOMATO_COUNT_INFO.TOMATO_REST, 5000L);
    }

    public static void setMyTomatoRestTime(Context context, long workTime) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(TomatoConstants.MY_TOMATO_COUNT_INFO.TOMATO_REST, workTime);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}