package com.sekhar.android.catshala.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sekhar.android.catshala.QuestionViewGenerator;
import com.sekhar.android.catshala.R;

/**
 * Created by sekhar on 17-09-2016.
 */
public class ExamFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.exam, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        new QuestionViewGenerator(getActivity()).createView();
    }
}
