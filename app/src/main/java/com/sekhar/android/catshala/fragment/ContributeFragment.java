package com.sekhar.android.catshala.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.sekhar.android.catshala.R;
import com.sekhar.android.catshala.activity.MainActivity;

/**
 * Created by sekhar on 17-09-2016.
 */
public class ContributeFragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.contribute, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.contribute_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contribute_submit:
                String question = ((EditText)getActivity().findViewById(R.id.contribute_question)).getText().toString();
                submitQuestion(question);
                break;
        }
    }

    private void submitQuestion(String questionBody) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"subhendu.sekhar@gmail.com; praveen.jharbade@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "CAT Question");
        i.putExtra(Intent.EXTRA_TEXT   , questionBody);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
