package com.siokagami.application.mytomato.presenter.inf;

import android.content.Context;

import com.siokagami.application.mytomato.bean.UserLoginQuery;

/**
 * Created by siokagami on 16/12/28.
 */

public interface UserLoginPresenterInf {
    void doUserLogin(Context context, UserLoginQuery query);
}
