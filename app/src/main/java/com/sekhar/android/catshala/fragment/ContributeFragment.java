package com.sekhar.android.catshala.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.sekhar.android.catshala.R;
import com.sekhar.android.catshala.activity.MainActivity;

/**
 * Created by sekhar on 17-09-2016.
 */
public class ContributeFragment extends Fragment implements View.OnClickListener {

    private WebView articleContent;
    private EditText textContent;
    String webViewContent = "";

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
        articleContent = (WebView) getActivity().findViewById(R.id.contribute_web_view);

        textContent = (EditText)getActivity().findViewById(R.id.contribute_question);

        textContent.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                prepDynamicWebContent(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });
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

    private void prepDynamicWebContent(String content) {
        /*String content = "If $ax^2+bx+c=0$ with $a≠0$, then: $$x={-b±√{b^2-4ac}}/{2a}$$ " +
                "<br/> \\[∫_\\Δd\\bo ω=∫_{∂\\Δ}\\bo ω\\] " +
                "<br/> $(\\table \\cos θ, - \\sin θ; \\sin θ, \\cos θ)$ gives a rotation by $θ$" +
                "<br/> $$f'(x)=\\lim↙{h→0}{f(x+h)-f(x)}/h$$" +
                "<br/> $${1+√5}/2=1+1/{1+1/{1+⋯}}$$" +
                "<br/> \\(U=⋃↙αU_α⇒0→\\fr F(U)→∏↙α\\fr F(U_α)→↖{-}∏↙{α,β}\\fr F(U_α∩U_β)\\) is exact";*/

        loadWebView(content);
    }

    private void loadWebView(String content) {

        WebSettings mWebSettings = articleContent.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        webViewContent = "<html><head>"
                + "<link rel='stylesheet' href='file:///android_asset/mathscribe/jqmath-0.4.3.css'>"
                + "<script src = 'file:///android_asset/mathscribe/jquery-1.4.3.min.js'></script>"
                + "<script src = 'file:///android_asset/mathscribe/jqmath-etc-0.4.5.min.js'></script>"
                + "</head><body>"
                + "<div>" +
                content +
                "</div>"
                + "<script> var di = document.getElementsByTagName(\"div\")[0]; M.parseMath(di);</script>"
                + "</body></html>";
        articleContent.loadDataWithBaseURL("", webViewContent,"text/html", "UTF-8", "");
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
