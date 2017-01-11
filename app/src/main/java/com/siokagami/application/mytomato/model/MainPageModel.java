package com.siokagami.application.mytomato.model;

import android.content.Context;

import com.siokagami.application.mytomato.bean.MainPageResponse;
import com.siokagami.application.mytomato.bean.UpdateStatQuery;
import com.siokagami.application.mytomato.model.inf.MainPageModelInf;
import com.siokagami.application.mytomato.service.MyTomatoAPI;
import com.siokagami.application.mytomato.utils.PrefUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by siokagami on 16/12/26.
 */

public class MainPageModel implements MainPageModelInf {
    private MainPageOnListener mainPageOnListener;

    public MainPageModel(MainPageOnListener mainPageOnListener) {
        this.mainPageOnListener = mainPageOnListener;
    }

    @Override
    public void getMainPageData(Context context) {
        Observable<MainPageResponse> getMainPageResponse = MyTomatoAPI.myTomatoService.getMainPageData(PrefUtils.getUserAccessToken(context));
        getMainPageResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MainPageResponse>() {
                    @Override
                    public void call(MainPageResponse mainPageResponse) {
                        mainPageOnListener.onSuccess(mainPageResponse);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mainPageOnListener.onFailure(throwable);
                    }
                })
        ;
    }
    public interface MainPageOnListener
    {
        void onSuccess(MainPageResponse response);
        void onFailure(Throwable e);
    }


}

