package com.siokagami.application.mytomato.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.bean.UserLoginQuery;
import com.siokagami.application.mytomato.bean.UserRegisterQuery;
import com.siokagami.application.mytomato.service.MyTomatoAPI;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends Fragment {
    private ImageView ivMainWorkStart;

    public MainPageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        ivMainWorkStart = (ImageView) view.findViewById(R.id.iv_main_work_start);
        ivMainWorkStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testApi();
            }
        });
        return view;
    }
    private void testApi()
    {
        Observable<Void> postUserLogin = MyTomatoAPI.myTomatoService.userRegister(new UserRegisterQuery("13000000000","123456","niconico"));
        postUserLogin.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                               @Override
                               public void call(Void aVoid) {
                                   Toast.makeText(getContext(), "喵帕斯~~~", Toast.LENGTH_SHORT).show();
                               }

                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
    }

}
