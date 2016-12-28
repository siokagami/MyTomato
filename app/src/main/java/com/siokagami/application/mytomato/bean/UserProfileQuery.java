package com.siokagami.application.mytomato.bean;


/**
 * Created by siokagami on 16/12/28.
 */

public class UserProfileQuery extends BaseQuery {



    public String nickname;
    public  int sex;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
