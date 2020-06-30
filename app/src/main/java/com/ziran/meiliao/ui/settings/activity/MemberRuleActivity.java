package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.FintessDetailBean;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/6/7.
 */

public class MemberRuleActivity extends CommonHttpActivity<CommonPresenter, CommonModel> {
    private String url;
    private FintessDetailBean.DataBean serializableExtra;
    private Boolean isShow;
    @Bind(R.id.tab_layout)
    NormalTitleBar normalTitleBar;

    @Bind(R.id.webView)
    WebView webView;
    private ProgressBar mProgressBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rule_web;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        super.initView();
        Intent intent = getIntent();
        if(intent!=null){
            url = intent.getStringExtra("url");
            isShow = intent.getBooleanExtra("isShow",false);
            serializableExtra = (FintessDetailBean.DataBean) intent.getSerializableExtra("JsCourseBean");
        }
        if(isShow){
            normalTitleBar.setVisibility(View.GONE);
        }
        init();


    }

    public static void startAction(Context context, String url, FintessDetailBean.DataBean bean){
        Intent intent = new Intent();
        intent.putExtra("url",url);
        intent.putExtra("JsCourseBean",bean);
        intent.putExtra("isShow",true);
        intent.setClass(context,MemberRuleActivity.class);
        context.startActivity(intent);
    }

    public static void startAction(Context context, String url){
        Intent intent = new Intent();
        intent.putExtra("url",url);
        intent.setClass(context,MemberRuleActivity.class);
        context.startActivity(intent);
    }
    SharePopupWindow mSharePopupWindow;
    private void share() {
        if(serializableExtra!=null){
            SharePopupWindow.showPopup(getBaseContext(), mSharePopupWindow, serializableExtra.getShareTitle(), serializableExtra.getShareDesc(), serializableExtra
                    .getShareUrl(),serializableExtra.getSharePic());
        }
    }

    private void init(){

        // 顶部显示的进度条
        mProgressBar = new ProgressBar(getBaseContext(), null, android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.MATCH_PARENT, 7, 0, 2));
        Drawable drawable = getBaseContext().getResources().getDrawable(com.ziran.meiliao.common.R.drawable.layer_web_progress_bar);
        mProgressBar.setProgressDrawable(drawable);
        webView. addView(mProgressBar);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setDomStorageEnabled(true);//对H5支持
        webView.setWebChromeClient(mWebChromeClientBase);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http://") || url.startsWith("https://")){
                    webView.loadUrl(url);
                    return true;
                }else if(url.contains("wpyx://back")){
                        onBackPressed();
                    return true;
                }else if(url.contains("wpyx://share")){
                    share();
                    return true;
                }else if(url.contains("wpyx://start")){

                    return true;
                }else {
                    return false;
                }
            }
        });
        webView.loadUrl(url);
    }
    private WebChromeClientBase mWebChromeClientBase = new WebChromeClientBase();
    private class WebChromeClientBase extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            if (mWebListener != null) {
//                mWebListener.onProgressChanged(newProgress);
//            }
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                if (mProgressBar.getVisibility() == View.GONE)
                    mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }
    }
}