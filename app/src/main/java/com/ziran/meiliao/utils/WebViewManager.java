package com.ziran.meiliao.utils;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ziran.meiliao.common.commonwidget.LoadingTip;

/**
 * author 吴祖清
 * create  2017/4/8 13
 * des    webView管理工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */
public class WebViewManager {
    //是否是加载错误的状态
    private static boolean loadError;

    /**
     *  初始化对象
     * @param webView webView对象
     */
    public static void init(WebView webView) {
        init(webView, null);
    }

    /**
     *   初始化对象
     * @param webView       webView对象
     * @param loadingTip    显示加载状态的控件
     */
    public static void init(WebView webView, final LoadingTip loadingTip) {
        init(webView, loadingTip, null);
    }

    /**
     *   初始化对象
     * @param webView       webView对象
     * @param loadingTip    显示加载状态的控件
     * @param progressBar   加载的进度条
     */
    public static void init(WebView webView, final LoadingTip loadingTip, final ProgressBar progressBar) {
        if (webView != null) {
            webView.setWebViewClient(new WebViewClient() {
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    //handler.cancel(); // Android默认的处理方式
                    handler.proceed();  // 接受所有网站的证书
                    //handleMessage(Message msg); // 进行其他处理
                }
                //开始页面加载
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    loadError = false;
                    if (loadingTip != null) {
                        loadingTip.setLoadingTip(LoadingTip.LoadStatus.loading);
                    }
                    super.onPageStarted(view, url, favicon);
                }
                //页面加载完成
                @Override
                public void onPageFinished(WebView view, String url) {
                    if (!loadError) {//当网页加载成功的时候判断是否加载成功
                        if (loadingTip != null) {
                            loadingTip.setLoadingTip(LoadingTip.LoadStatus.finish);
                            view.setVisibility(View.VISIBLE);
                        }
                    } else { //加载失败的话，初始化页面加载失败的图，然后替换正在加载的视图页面
                        if (loadingTip != null) {
                            loadingTip.setLoadingTip(LoadingTip.LoadStatus.error);
                            view.setVisibility(View.GONE);
                        }
                    }
                }
                //设置在本应用加载网页
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
                //加载错误
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    super.onReceivedError(view, errorCode, description, failingUrl);
                    loadError = true;
                }
            });
           /**
            * 设置铬处理。这是一个实现webchromeclient
            * 在处理JavaScript对话框、图标、标题、和进步。
            * 这将取代当前处理程序。
            */
            webView.setWebChromeClient(new WebChromeClient() {
                /**
                 * 当WebView加载之后，返回 HTML 页面的标题 Title
                 * @param view
                 * @param title
                 */
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    //判断标题 title 中是否包含有“error”字段，如果包含“error”字段，则设置加载失败，显示加载失败的视图
                    if (!TextUtils.isEmpty(title) && title.toLowerCase().contains("error")) {
                        loadError = true;
                    }
                }

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (progressBar == null) return;
                    if (newProgress == 100) progressBar.setVisibility(View.GONE);/*加载完网页进度条消失*/
                    else {
                        progressBar.setVisibility(View.VISIBLE);/*开始加载网页时显示进度条*/
                        progressBar.setProgress(newProgress);/*设置进度值*/
                    }
                }
            });
            //设置开启与html交互
            webView.getSettings().setJavaScriptEnabled(true);
            //设置编码方式
            webView.getSettings().setDefaultTextEncodingName("utf-8");
            //设置 Javascript交互接口
        }
    }

    //暂停
    public static void onPause(WebView webView) {
        if (webView != null)
            webView.onPause();
    }

    //恢复
    public static void onResume(WebView webView) {
        if (webView != null)
            webView.onResume();
    }

    //销毁
    public static void onDestroy(ViewGroup viewGroup, WebView webView) {
        if (webView != null && viewGroup != null) {
            viewGroup.removeView(webView);
            webView.removeAllViews();
            webView.destroy();
        }
    }

    //销毁
    public static boolean onBackPressed(WebView webView) {
        if (webView != null && webView.canGoBack()) { //当webview不是处于第一页面时，返回上一个页面
            webView.goBack();
            return false;
        }
        return true;
    }
    //是否加载错误
    public static boolean isLoadError() {
        return loadError;
    }
}
