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
        return preferences.getLong(TomatoConstants.MY_TOMATO_COUNT_INFO.TOMATO_WORK, 1500000L);
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
        return preferences.getLong(TomatoConstants.MY_TOMATO_COUNT_INFO.TOMATO_REST, 900000L);
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

    public static String getUserAccessToken(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getString(TomatoConstants.ACCESS_TOKEN, "");
    }

    public static void setUserAccessToken(Context context, String accessToken) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(TomatoConstants.ACCESS_TOKEN, accessToken);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getUserName(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getString(TomatoConstants.USER_NAME, "");
    }

    public static void setUserName(Context context, String userName) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(TomatoConstants.USER_NAME, userName);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int getUserSex(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getInt(TomatoConstants.SEX,0);
    }

    public static void setUserSex(Context context, int sex) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(TomatoConstants.SEX, sex);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserWorkTag(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getString(TomatoConstants.WORK_TAG, "工作");
    }

    public static void setUserWorkTag(Context context, String tag) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(TomatoConstants.WORK_TAG, tag);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}