package com.sekhar.android.catshala.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.sekhar.android.catshala.QuestionViewGenerator;
import com.sekhar.android.catshala.R;

import java.io.IOException;

/**
 * Created by sekhar on 27-09-2016.
 */
public class MathExprFragment extends Fragment {
    private WebView articleContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.math_expressions, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        articleContent = (WebView) getActivity().findViewById(R.id.formula_page);
        /*articleContent.getSettings().setJavaScriptEnabled(true);
        articleContent.getSettings().setBuiltInZoomControls(true);
        articleContent.loadUrl("file:///android_asset/test.html");*/

        WebSettings mWebSettings = articleContent.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        String path="file:///android_asset/";
        String js = "<html><head>"
                + "<link rel='stylesheet' href='file:///android_asset/mathscribe/jqmath-0.4.3.css'>"
                + "<script src = 'file:///android_asset/mathscribe/jquery-1.4.3.min.js'></script>"
                + "<script src = 'file:///android_asset/mathscribe/jqmath-etc-0.4.5.min.js'></script>"
                + "</head><body>"
                + "<div>If $ax^2+bx+c=0$ with $a≠0$, then: $$x={-b±√{b^2-4ac}}/{2a}$$ " +
                "<br/> \\[∫_\\Δd\\bo ω=∫_{∂\\Δ}\\bo ω\\] " +
                "<br/> $(\\table \\cos θ, - \\sin θ; \\sin θ, \\cos θ)$ gives a rotation by $θ$" +
                "<br/> $$f'(x)=\\lim↙{h→0}{f(x+h)-f(x)}/h$$" +
                "<br/> $${1+√5}/2=1+1/{1+1/{1+⋯}}$$" +
                "<br/> \\(U=⋃↙αU_α⇒0→\\fr F(U)→∏↙α\\fr F(U_α)→↖{-}∏↙{α,β}\\fr F(U_α∩U_β)\\) is exact" +
                "</div>"
                + "<script> var di = document.getElementsByTagName(\"div\")[0]; M.parseMath(di);</script>"
                + "</body></html>";
        articleContent.loadDataWithBaseURL("",js,"text/html", "UTF-8", "");
    }
}
