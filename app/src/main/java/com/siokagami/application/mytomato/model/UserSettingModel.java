package com.siokagami.application.mytomato.model;

import android.content.Context;

import com.siokagami.application.mytomato.bean.MainPageResponse;
import com.siokagami.application.mytomato.bean.UserProfileQuery;
import com.siokagami.application.mytomato.model.inf.UserSettingModelInf;
import com.siokagami.application.mytomato.service.MyTomatoAPI;
import com.siokagami.application.mytomato.utils.PrefUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by siokagami on 16/12/28.
 */

public class UserSettingModel implements UserSettingModelInf {
    private UserSettingListener userSettingListener;

    public UserSettingModel(UserSettingListener userSettingListener) {
        this.userSettingListener = userSettingListener;
    }

    @Override
    public void setUserName(Context context,String name) {
        UserProfileQuery query = new UserProfileQuery();
        query.nickname = name;
        query.token = PrefUtils.getUserAccessToken(context);
        Observable<Void> setUserName = MyTomatoAPI.myTomatoService.changUserProfile(query);
        setUserName
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        userSettingListener.onSuccess();

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        userSettingListener.onFailure(throwable);
                    }
                });

    }

    @Override
    public void setUserSex(Context context,int sex) {
        UserProfileQuery query = new UserProfileQuery();
        query.sex = sex;
        query.token = PrefUtils.getUserAccessToken(context);
        Observable<Void> setUserName = MyTomatoAPI.myTomatoService.changUserProfile(query);
        setUserName
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        userSettingListener.onSuccess();

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        userSettingListener.onFailure(throwable);
                    }
                });

    }

    public interface UserSettingListener
    {
        void onSuccess();
        void onFailure(Throwable throwable);
    }
}
