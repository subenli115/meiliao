package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.widget.RelativeLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.receiver.NetUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.utils.WebViewManager;
import com.ziran.meiliao.widget.NestedWebView;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * 专辑详情课程简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class AlbumDetailFragment extends BaseFragment {

    @Bind(R.id.webView)
    NestedWebView webView;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;
    //webView的容器
    @Bind(R.id.webviewLinearLayout)
    RelativeLayout webviewLinearLayout;
    protected String url;
    private AlbumDetailActivity albumDetailActivity;

    private boolean isInitView;
    private boolean isLoadUrl;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_jyg_album_detail;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        if (getActivity() instanceof AlbumDetailActivity) {
            albumDetailActivity = (AlbumDetailActivity) getActivity();
        }
        if (!isInitView) {
            loadedTip.setImg_tip_logo(R.mipmap.ic_empty_message);
            loadedTip.setTips("加载失败,请稍后重试");
            WebViewManager.init(webView,loadedTip);
            isInitView = true;
        }
        mRxManager.on(AppConstant.RXTag.NETWORK_CHANGE_STATE, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case NetUtil.NETWORK_MOBILE:
                    case NetUtil.NETWORK_WIFI:
                        if (WebViewManager.isLoadError()){
                            webView.reload();
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        WebViewManager.onDestroy(webviewLinearLayout, webView);
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
        WebViewManager.onPause(webView);
    }

    @Override
    public void onResume() {
        super.onResume();
        WebViewManager.onResume(webView);
    }

    //懒加载,当第一次显示该页面时才开始加载webView
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible && !isLoadUrl) {
            if (albumDetailActivity != null && albumDetailActivity.getAlbumBean() != null) {
                String detail = albumDetailActivity.getAlbumBean().getDetail();
                if (EmptyUtils.isEmpty(detail)) {
                    isLoadUrl = false;
                    return;
                }
                webView.loadUrl(detail);
                isLoadUrl = true;
            }
        }
    }
}
