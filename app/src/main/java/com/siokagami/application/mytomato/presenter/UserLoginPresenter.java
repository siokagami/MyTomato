package com.siokagami.application.mytomato.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.siokagami.application.mytomato.bean.BaseResponse;
import com.siokagami.application.mytomato.bean.UserLoginQuery;
import com.siokagami.application.mytomato.bean.UserLoginResponse;
import com.siokagami.application.mytomato.model.UserLoginModel;
import com.siokagami.application.mytomato.model.inf.UserLoginModelInf;
import com.siokagami.application.mytomato.presenter.inf.UserLoginPresenterInf;
import com.siokagami.application.mytomato.utils.IntentUtil;
import com.siokagami.application.mytomato.utils.PrefUtils;
import com.siokagami.application.mytomato.view.inf.LoginActivityInf;

/**
 * Created by siokagami on 16/12/28.
 */

public class UserLoginPresenter implements UserLoginModel.UserLoginListener, UserLoginPresenterInf {
    private Context context;
    private UserLoginModelInf userLoginModelInf;
    private LoginActivityInf loginActivityInf;

    public UserLoginPresenter(LoginActivityInf loginActivityInf) {
        this.loginActivityInf = loginActivityInf;
        this.userLoginModelInf = new UserLoginModel(UserLoginPresenter.this);
    }

    @Override
    public void onSuccess(UserLoginResponse userLoginResponse) {
        PrefUtils.setUserAccessToken(context, userLoginResponse.getToken());
        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
        context.startActivity(IntentUtil.showMainPage(context));
        PrefUtils.setUserName(context,userLoginResponse.getUser().getNickname());
        PrefUtils.setUserSex(context,userLoginResponse.getUser().getSex());
        loginActivityInf.loginSuccess();
    }

    @Override
    public void onFailure(Throwable throwable) {
        Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doUserLogin(Context context, UserLoginQuery query) {
        this.context = context;
        userLoginModelInf.doUserLogin(query);

    }
}
