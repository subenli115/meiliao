package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.base.CommonHttpFragment;

import butterknife.Bind;

public class CatalogVideoFragment extends CommonHttpFragment {
    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected int getLayoutResource() {
        return R.layout.ac_video_web;
    }


    @Override
    protected void initView() {
        super.initView();
        String html = (String)getArguments().get("html");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setDomStorageEnabled(true);//对H5支持
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);// 不使用缓存
        webView.getSettings().setUserAgentString(System.getProperty("http.agent"));
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http://") || url.startsWith("https://"))
                    webView.loadUrl(url);
                return true;
            }
        });

        webView.setWebViewClient(new MyWebViewClient());
        webView.loadData(html, "text/html;charset=utf-8","utf-8");
    }


    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void imgReset() {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }
}
