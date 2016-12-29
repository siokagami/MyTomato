package com.siokagami.application.mytomato.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.bean.MainPageResponse;
import com.siokagami.application.mytomato.bean.UpdateStatQuery;
import com.siokagami.application.mytomato.databinding.FragmentMainPageBinding;
import com.siokagami.application.mytomato.presenter.MainPagePresenter;
import com.siokagami.application.mytomato.presenter.inf.MainPagePresenterInf;
import com.siokagami.application.mytomato.service.MyTomatoAPI;
import com.siokagami.application.mytomato.utils.PrefUtils;
import com.siokagami.application.mytomato.utils.StringUtils;
import com.siokagami.application.mytomato.view.inf.MainPageFragmentInf;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends Fragment implements MainPageFragmentInf {
    private TomatoNavigationMenu tomatoNavigationMenu;
    private ImageView ivMainWorkStart;
    public FragmentMainPageBinding binding;
    MainPageResponse response = new MainPageResponse();
    private MainPagePresenterInf pagePresenterInf = new MainPagePresenter(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false);
        binding.setMainPage(response);
        initView(view);
        initUserData();
        return binding.getRoot();
    }

    private void initView(View view) {
        tomatoNavigationMenu = (TomatoNavigationMenu) getActivity();
        ivMainWorkStart = (ImageView) binding.getRoot().findViewById(R.id.iv_main_work_start);
        ivMainWorkStart.setVisibility(View.GONE);
    }

    private void initUserData() {
        pagePresenterInf.getMainPageData(getContext());
    }

    private void testApi() {
        Observable<Void> postTomatoWork = MyTomatoAPI.myTomatoService.updateStat(new UpdateStatQuery("摸鱼", 1, PrefUtils.getUserAccessToken(getContext())));
        postTomatoWork.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                               @Override
                               public void call(Void aVoid) {
                                   Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                               }

                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });



    }

    @Override
    public void setView(MainPageResponse response) {
        if (StringUtils.isEmpty(response.getCommon())) {
            this.response.setCommon("暂无工作");
        }else {
            this.response.setCommon(response.getCommon());
        }
        this.response.setCount(response.getCount());
        this.response.setRanking(response.getRanking());
        this.response.setLatest(response.getLatest());

    }
}
