package com.siokagami.application.mytomato.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siokagami.application.mytomato.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TomatoWorkFragment extends Fragment {


    public TomatoWorkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tomato_work, container, false);
    }

}
