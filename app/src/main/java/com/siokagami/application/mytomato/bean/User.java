package com.siokagami.application.mytomato.bean;

/**
 * Created by siokagami on 16/9/27.
 */
public class User
{
    public String uid;
    public String userName;
    public String count = "0";

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
