package com.siokagami.application.mytomato.service;

import com.siokagami.application.mytomato.bean.User;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by siokagami on 16/9/27.
 */
public interface MyTomatoService
{
    @GET("book/{id}")
    Observable<User> userProfile(@Path("id") String id);

}
