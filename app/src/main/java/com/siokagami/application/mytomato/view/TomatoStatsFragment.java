package com.siokagami.application.mytomato.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.utils.PrefUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TomatoStatsFragment extends Fragment {

    private WebView wvTomatoStats;
    private String url = "http://112.74.43.190:3000/stat.html?token=";

    public TomatoStatsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tomato_stats_fragment, container, false);

        initView(view);
        return view;
    }

    private void initView(View view)
    {
        wvTomatoStats = (WebView) view.findViewById(R.id.wv_tomato_stats);
        wvTomatoStats.getSettings().setJavaScriptEnabled(true);
        wvTomatoStats.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        try {
            wvTomatoStats.loadUrl(url+ URLEncoder.encode(PrefUtils.getUserAccessToken(getContext()), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
