package com.siokagami.application.mytomato.bean;

/**
 * Created by siokagami on 16/12/30.
 */

public class FindPasswordQuery {
    public String phone;
    public String code;
    public String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
