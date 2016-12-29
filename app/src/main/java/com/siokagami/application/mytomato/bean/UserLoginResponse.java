package com.siokagami.application.mytomato.bean;

/**
 * Created by siokagami on 16/12/29.
 */

public class UserLoginResponse extends BaseResponse {

    private UserProfileResponse user;

    public UserProfileResponse getUser() {
        return user;
    }

    public void setUser(UserProfileResponse user) {
        this.user = user;
    }
}
