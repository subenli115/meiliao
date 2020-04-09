package com.ziran.meiliao.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.SettingUtil;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.receiver.NetUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.WebViewManager;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;


/**
 * author 吴祖清
 * create  2017/3/8
 * des     常用的WebView基类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public abstract class CommonWebActivity extends ShareActivity implements LoadingTip.onReloadListener {
    /**
     * 父容器
     */
    @Bind(R.id.rootView)
    protected View rootView;

    /**
     * 加载状态view
     */
    @Bind(R.id.loadedTip)
    protected LoadingTip loadedTip;

    /**
     * webview
     */
    @Bind(R.id.webView)
    protected android.webkit.WebView webView;
    /**
     * 加载进度条
     */
    @Bind(R.id.progressBar1)
    protected ProgressBar pg1;

    /**
     * 加载的网络路径
     */
    protected String url;
    /**
     * 标题
     */
    @Bind(R.id.tv_headview_title)
    protected TextView tvHeadviewTitle;

    /**
     * 收藏按钮
     */
    @Bind(R.id.iv_headview_collect)
    protected ImageView ivCollect;

    /**
     * 分享按钮
     */
    @Bind(R.id.iv_headview_share)
    protected ImageView ivShare;


    protected SharePopupWindow mSharePopupWindow;
    /**
     * 标记是否加载网页
     */
    protected boolean isLoadPage;

    @Override
    public void initPresenter() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }


    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                url = extras.getString(AppConstant.SPKey.EXTRAS_URL);
                initBundle(extras);
            }
        }
        if (EmptyUtils.isEmpty(url)) {
            showShortToast("当前服务器繁忙，请稍后重试");
            finish();
        }
        //初始化webView
        initWebView();
        loadedTip.setOnReloadListener(this);
        //订阅网络状态恢复正常的事件
        mRxManager.on(AppConstant.RXTag.NETWORK_CHANGE_STATE, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case NetUtil.NETWORK_MOBILE:
                    case NetUtil.NETWORK_WIFI:
                        if (!isLoadPage) {
                            loadUrl();
                        }
                        break;
                }
            }
        });
    }

    /**
     * 初始化webView
     */
    private void initWebView() {
        if (webView != null) {
            WebViewManager.init(webView, null, pg1);
            if (NetWorkUtils.isNetConnected(this)) {
                loadUrl();
                isLoadPage = true;
            } else {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.noWifi);
            }
        }
        mRxManager.on(AppConstant.RXTag.ON_WEB_PAY, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                webView.loadUrl("javascript: payRs('"+integer+"')");
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 当url里面包含article字段的时候，则跳转到TwoStairsActivity页面，否则还是继续显示网页
                // 返回值：true 不会显示网页资源，需要等待你的处理，false 就认为系统没有做处理，会显示网页资源
                if (!TextUtils.isEmpty(url) && url.contains("page/static/activity/signStatus")) {

                }
                return false;
            }
        });
    }

    protected void loadUrl() {
        webView.loadUrl(url);
    }

    /**
     * 设置收藏按钮和分享按钮的显示和隐藏
     *
     * @param showCollect true显示收藏按钮 false 隐藏收藏按钮
     * @param showShare   true显示分享按钮 false 隐藏分享按钮
     */
    public void setShareViewGone(boolean showCollect, boolean showShare) {
        ivCollect.setVisibility(showCollect ? View.VISIBLE : View.GONE);
        ivShare.setVisibility(showShare ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置标题内容
     *
     * @param title 标题内容
     */
    public void setMyTitle(String title) {
        if (EmptyUtils.isNotEmpty(title)) {
            tvHeadviewTitle.setText(title);
        }
    }

    /**
     * 获取从上个界面传过来的参数
     *
     * @param extras Bundle传参对象
     */
    protected void initBundle(Bundle extras) {
    }

    /**
     * 分享成功回调
     */
    @Override
    public void onResult(SHARE_MEDIA share_media) {
        super.onResult(share_media);
        setShow(false);
    }

    /**
     * 取消分享回调
     */
    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        super.onCancel(share_media);
        setShow(false);
    }

    /**
     * 设置是否显示分享控件
     *
     * @param show true 显示 FALSE 隐藏
     */
    protected void setShow(boolean show) {
    }


    @Override
    protected void onPause() {
        super.onPause();
        WebViewManager.onPause(webView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebViewManager.onResume(webView);
    }


    @Override
    protected void onDestroy() {
        WebViewManager.onDestroy((ViewGroup) rootView, webView);
        super.onDestroy();
    }

    /**
     * 改变收藏的图标
     *
     * @param ivCollect 收藏图标的view
     * @param isCollect 是否收藏
     */
    public void setCollect(ImageView ivCollect, boolean isCollect) {
        if (isCollect) {
            ivCollect.setImageResource(R.mipmap.ic_jyg_player_collect_green);
        } else {
            ivCollect.setImageResource(R.mipmap.ic_jyg_player_collect);
        }
    }

    /**
     * 点击监听事件
     *
     * @param view 点击的控件
     */
    @OnClick({R.id.iv_headview_back, R.id.iv_headview_share, R.id.iv_headview_collect})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_headview_back: //返回按钮
                finish();
                break;
            case R.id.iv_headview_share:
                if (!CheckUtil.check(this, view)) //如果有网络则显示分享控件
                    return;
                share();
                break;
            case R.id.iv_headview_collect:
                if (!CheckUtil.check(this, view))//如果有网络则进行收藏请求
                    return;
                doCollcet();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //如果分享控件隐藏并且webview没有页面缓存则正常退出
        if ( WebViewManager.onBackPressed(webView)) {
            super.onBackPressed();
        }
    }

    /**
     * 进行收藏请求
     */
    protected void doCollcet() {
    }

    /**
     * 进行分享控件显示
     */
    protected void share() {
    }

    /**
     * 开始分享请求
     */
    @Override
    public void onStart(SHARE_MEDIA share_media) {
//        startProgressDialog("加载中");
    }

    /**
     * 点击跳转到设置界面
     */
    @Override
    public void reload() {
        SettingUtil.startSettings(this);
    }


}
