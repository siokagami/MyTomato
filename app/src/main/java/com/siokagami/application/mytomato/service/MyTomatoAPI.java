package com.siokagami.application.mytomato.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by siokagami on 16/9/27.
 */
public class MyTomatoAPI {
    private static String BASE_URL = "";
    private static Retrofit mRetrofit  = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
