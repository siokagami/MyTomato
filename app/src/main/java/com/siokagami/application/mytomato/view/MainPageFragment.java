package com.siokagami.application.mytomato.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.databinding.FragmentMainPageBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends Fragment {


    public MainPageFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        return view;
    }

}
