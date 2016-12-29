package com.siokagami.application.mytomato.service;

import com.siokagami.application.mytomato.bean.BaseResponse;
import com.siokagami.application.mytomato.bean.MainPageResponse;
import com.siokagami.application.mytomato.bean.UpdateStatQuery;
import com.siokagami.application.mytomato.bean.UserLoginQuery;
import com.siokagami.application.mytomato.bean.UserLoginResponse;
import com.siokagami.application.mytomato.bean.UserProfileQuery;
import com.siokagami.application.mytomato.bean.UserRegisterQuery;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by siokagami on 16/9/27.
 */
public interface MyTomatoService
{
    @POST("/user/login")
    Observable<UserLoginResponse> userLogin(@Body UserLoginQuery query);
    @POST("/user/register")
    Observable<Void> userRegister(@Body UserRegisterQuery query);
    @POST("/stat")
    Observable<Void> updateStat(@Body UpdateStatQuery query);
    @GET("/stat/info")
    Observable<MainPageResponse> getMainPageData(@Query("token") String token);
    @PATCH("/user/profile")
    Observable<Void> changUserProfile(@Body UserProfileQuery query);


}
