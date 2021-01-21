package com.goodeggs.android.ui.wesitesview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.goodeggs.android.utils.Constants;
import com.goodeggs.android.R;

public class WebUrlSitesFragment extends Fragment {
    private WebView mFaq_webview;
    private ProgressBar mFaq_progressbar;

    SwipeRefreshLayout swipeRefreshLayout;
    RelativeLayout relativeLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.weburl_fragment, null);
        initializeComponents(root);

        mFaq_webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("android_asset")) {
                    mFaq_progressbar.setVisibility(View.GONE);
                    return false;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mFaq_progressbar.setVisibility(View.GONE);
            }
        });

        return root;
    }

    private void initializeComponents(View root) {
        mFaq_webview = root.findViewById(R.id.faq_webview);
        mFaq_progressbar = root.findViewById(R.id.faq_progressbar);
        mFaq_webview.getSettings().setJavaScriptEnabled(true);
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);
        relativeLayout = root.findViewById(R.id.relativeLayout);
        getUrls();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
//            if (Utility.isNetworkAvailable(getActivity())) {
            //  getCompletePreferenceList();
            getUrls();
            /*} else {
                Snackbar snackBar = Snackbar.make(relativeLayout, Constants.PLEASE_CHECK_INTERNET, Snackbar.LENGTH_SHORT);

            }*/
        });

    }

    public void getUrls() {
        Bundle arguments = getArguments();
        if (arguments.getString("FAQ").equals("Contact Us")) {
            mFaq_webview.loadUrl(Constants.CONTACT_US);
        } else if (arguments.getString("FAQ").equals("About us")) {
            mFaq_webview.loadUrl(Constants.ABOUT_US);
        }
    }

}

