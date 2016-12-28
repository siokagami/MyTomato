package com.siokagami.application.mytomato.presenter;

import android.content.Context;
import android.widget.Toast;

import com.siokagami.application.mytomato.model.UserSettingModel;
import com.siokagami.application.mytomato.model.inf.UserSettingModelInf;
import com.siokagami.application.mytomato.presenter.inf.UserSettingPresenterInf;
import com.siokagami.application.mytomato.view.SettingInf;

/**
 * Created by siokagami on 16/12/28.
 */

public class UserSettingPresenter implements UserSettingModel.UserSettingListener, UserSettingPresenterInf {
    private UserSettingModelInf userSettingModelInf;
    private SettingInf settingInf;

    public UserSettingPresenter(SettingInf settingInf) {
        this.userSettingModelInf = new UserSettingModel(UserSettingPresenter.this);
        this.settingInf = settingInf;
    }

    @Override
    public void onSuccess() {
        settingInf.showSuccess();
    }

    @Override
    public void onFailure(Throwable throwable) {
        settingInf.showFailure();
    }

    @Override
    public void setUserName(Context context,String name) {
        userSettingModelInf.setUserName(context,name);

    }

    @Override
    public void setUserSex(Context context,int sex) {
        userSettingModelInf.setUserSex(context,sex);

    }
}
