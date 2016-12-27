package com.siokagami.application.mytomato.presenter;

import android.content.Context;

import com.siokagami.application.mytomato.bean.MainPageResponse;
import com.siokagami.application.mytomato.model.MainPageModel;
import com.siokagami.application.mytomato.model.inf.MainPageModelInf;
import com.siokagami.application.mytomato.presenter.inf.MainPagePresenterInf;
import com.siokagami.application.mytomato.view.MainPageFragment;
import com.siokagami.application.mytomato.view.inf.MainPageFragmentInf;

/**
 * Created by siokagami on 16/12/26.
 */

public class MainPagePresenter implements MainPageModel.MainPageOnListener,MainPagePresenterInf {

    private MainPageFragmentInf mainPageFragmentInf ;
    private MainPageModelInf mainPageModelInf;

    public MainPagePresenter(MainPageFragmentInf mainPageFragmentInf) {
        this.mainPageFragmentInf = mainPageFragmentInf;
        this.mainPageModelInf = new MainPageModel(this);
    }

    @Override
    public void onSuccess(MainPageResponse response) {
        mainPageFragmentInf.setView(response);

    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    public void getMainPageData(Context context) {
        mainPageModelInf.getMainPageData(context);

    }
}
