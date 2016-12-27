package com.siokagami.application.mytomato.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by siokagami on 16/9/27.
 */
public class MyTomatoAPI {
    private static String BASE_URL = "http://112.74.43.190:3000";
    private static Retrofit mRetrofit  = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static MyTomatoService  myTomatoService  = mRetrofit.create(MyTomatoService.class);

}
