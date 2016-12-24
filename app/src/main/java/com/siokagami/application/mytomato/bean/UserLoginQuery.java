package com.siokagami.application.mytomato.bean;

/**
 * Created by siokagami on 16/12/24.
 */

public class UserLoginQuery {
    String phone;
    String password;

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

    public UserLoginQuery(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
