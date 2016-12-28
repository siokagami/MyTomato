package com.siokagami.application.mytomato.model;

import com.siokagami.application.mytomato.bean.BaseResponse;
import com.siokagami.application.mytomato.bean.UserLoginQuery;
import com.siokagami.application.mytomato.model.inf.UserLoginModelInf;
import com.siokagami.application.mytomato.service.MyTomatoAPI;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by siokagami on 16/12/24.
 */

public class UserLoginModel implements UserLoginModelInf {
    private UserLoginListener loginListener;

    public UserLoginModel(UserLoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void doUserLogin(UserLoginQuery userLoginQuery) {
        Observable<BaseResponse> doUserLogin = MyTomatoAPI.myTomatoService.userLogin(userLoginQuery);
        doUserLogin
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseResponse>() {
                    @Override
                    public void call(BaseResponse baseResponse) {
                        loginListener.onSuccess(baseResponse);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        loginListener.onFailure(throwable);

                    }
                });


    }
    public interface UserLoginListener
    {
        void onSuccess(BaseResponse baseResponse);
        void onFailure(Throwable throwable);
    }
}
