package com.siokagami.application.mytomato.bean;

/**
 * Created by siokagami on 16/12/24.
 */

public class UserRegisterQuery {
    public String phone;
    public String password;
    public String nickname;


    public UserRegisterQuery(String phone, String password, String nickname) {
        this.phone = phone;
        this.password = password;
        this.nickname = nickname;
    }

    public UserRegisterQuery() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
