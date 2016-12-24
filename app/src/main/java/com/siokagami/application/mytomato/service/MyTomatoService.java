package com.siokagami.application.mytomato.service;

import com.siokagami.application.mytomato.bean.UpdateStatQuery;
import com.siokagami.application.mytomato.bean.UserLoginQuery;
import com.siokagami.application.mytomato.bean.UserRegisterQuery;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by siokagami on 16/9/27.
 */
public interface MyTomatoService
{
    @POST("/user/login")
    Observable<Void> userLogin(@Body UserLoginQuery query);
    @POST("/user/register")
    Observable<Void> userRegister(@Body UserRegisterQuery query);
    @POST("stat")
    Observable<Void> updateStat(@Body UpdateStatQuery query);

}
