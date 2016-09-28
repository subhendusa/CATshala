package com.sekhar.android.catshala.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sekhar.android.catshala.R;
import com.sekhar.android.catshala.activity.MainActivity;

/**
 * Created by sekhar on 17-09-2016.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_page, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.home_contribute).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_contribute:
                ((MainActivity)getActivity()).switchFragment(new ContributeFragment());
                break;
        }
    }
}
