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

public class TomatoStatsFragment extends Fragment {

    private WebView wvTomatoStats;
    private String url = "http://10.0.0.4:8080/echarts.html";

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
        wvTomatoStats.loadUrl(url);
    }

}
