package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.EncodeUtil;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.NewMediaAndTextBean;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.ui.bean.SubscribeCommentResultBean;
import com.ziran.meiliao.ui.priavteclasses.activity.ZLFreeZLActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.SubscribeProfitAdapter;
import com.ziran.meiliao.ui.priavteclasses.contract.NoBuyZhuanLanContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.NoBuyZhuanLanPresenter;
import com.ziran.meiliao.ui.priavteclasses.util.BuyZhuanLanUtil;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanSubscribeProfitUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.PayResultPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 专栏未订阅Fragment
 */

public class NoBuyZLFragment extends CommonRefreshFragment<NoBuyZhuanLanPresenter, CommonModel> implements
        NoBuyZhuanLanContract.View {

    @Bind(R.id.ntb)
    NormalTitleBar mNtb;
    @Bind(R.id.tv_zhuanlan_buy)
    FilterTextView mTvZhuanlanBuy;
    @Bind(R.id.ll_bottom_container)
    LinearLayout mLlBottomContainer;
    String specColumnId;
    @Bind(R.id.webView)
    WebView webView;
    private Map<String, String> stringMap;
    private String audioId = "1";
    private Map<String, String> apiKeyMap;
    private String type;
    private ZhuanLanSubscribeProfitUtil mZhuanLanSubscribeProfitUtil;
    SharePopupWindow mSharePopupWindow;
    private String htmlLink;
    private int back;

    @Override
    protected int getLayoutResource() {
        if(back==1){
            return R.layout.fragment_no_buy_zl_nobottom;
        }else {
            return R.layout.fragment_no_buy_zl;
        }
    }
    public void share() {
        if (data == null) return;
        SharePopupWindow.showPopup(getContext(), mSharePopupWindow, data);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected void initBundle(Bundle extras) {
        try {
            int anInt = extras.getInt(AppConstant.ExtraKey.FROM_TYPE);
             back = extras.getInt("back");
            type = anInt+"";
            htmlLink = extras.getString("htmlLink");
            specColumnId = extras.getString(AppConstant.ExtraKey.SUBSCRIPTION_ID);
        } catch (Exception e) {
            specColumnId = "";
            audioId="";
            e.printStackTrace();
        } finally {
            stringMap = MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID, specColumnId);
            stringMap.put("courseId", specColumnId);
            apiKeyMap = MapUtils.getOnlyCan(AppConstant.RXTag.AUDIO_ID, "");
            apiKeyMap.put("audioId", specColumnId);
        }
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        //添加下滑线
        return new SubscribeProfitAdapter(getContext());
    }

    @Override
    protected void loadData() {
    }

    View headView;
    @Override
    protected void initOther() {
        stringMap.put("page", String.valueOf(page));
        apiKeyMap.put("page", String.valueOf(page));
        getDataType();
        setRecyclerEnabled(false);
        if(htmlLink!=null&&htmlLink.length()>0){
            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient());
            WebSettings ws = webView.getSettings();
            ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            ws.setSupportZoom(true);
            ws.setBuiltInZoomControls(true);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBlockNetworkImage(false);
            webView.getSettings().setDomStorageEnabled(true);//对H5支持
            webView.getSettings().setAllowFileAccess(true);
            webView.setVisibility(View.VISIBLE);
            webView.loadUrl(htmlLink);
        }
        mNtb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webView.canGoBack()){
                    webView.goBack();
                }else{
                    finish();
                }
            }
        });
        mZhuanLanSubscribeProfitUtil = new ZhuanLanSubscribeProfitUtil(getContext());
        mZhuanLanSubscribeProfitUtil.init();
        mZhuanLanSubscribeProfitUtil.setVisit();
        headView = LayoutInflater.from(getContext()).inflate(R.layout.headerview_zhuanlan_new, null);
        iRecyclerView.setHeadView(headView);
        mNtb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            share();
            }
        });
        mNtb.setBackGroundColor(R.color.transparent);
        mPresenter.getData(ApiKey.SUBSCRIPTION_NATIVEINDEXV3, stringMap);

        mRxManager.on(ApiKey.SPEC_COLUMN_BUY, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                finish();
            }
        });
        mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
            @Override
            public void call(String balance) {
                data.setUserCoin(balance);
            }
        });
    }

    public void getDataType() {
            if (type.equals("1") ) {
                mPresenter.getCommentList(ApiKey.GET_COURSE_COMMENT, stringMap);
                //视频
            } else {
                //音频
                mPresenter.getCommentList(ApiKey.AUDIO_GET_COMMENT, apiKeyMap);
            }
    }


    @OnClick({R.id.tv_free_listen, R.id.tv_zhuanlan_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_free_listen:
                startActivity(ZLFreeZLActivity.class, getBundle(specColumnId));
                break;
            case R.id.tv_zhuanlan_buy:
                buy();
                break;

            case R.id.id_right_img1:
                break;
            case R.id.id_right_img2:
                break;
        }
    }

    private NewMediaAndTextBean.DataBean data;

    @Override
    public void showData(NewMediaAndTextBean.DataBean result) {
        data = result;
        mRxManager.post(AppConstant.RXTag.SUBSCRIBE_UPDATE, HandlerUtil.obj(10, result));
        mRxManager.on(AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                //执行上传评论请求
                switch (msg.what) {
                    case AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG_POST_COMMENT:
                        apiKeyMap.put("content", EncodeUtil.encodeUTF(msg.obj.toString()));
                        if(type.equals("1")){
                            mPresenter.postComment(ApiKey.POST_COURSE_COMMENT, apiKeyMap);
                        }else {
                            mPresenter.postComment(ApiKey.AUDIO_COMMENT, apiKeyMap);
                        }
                        break;
                    case AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG_POST_PRAISE_COMMENT:
                        SubscribeCommentListBean.DataBean dataBean = (SubscribeCommentListBean.DataBean) msg.obj;
                        apiKeyMap.put("commentId", String.valueOf(dataBean.getId()));
                        apiKeyMap.put("praise", dataBean.isPraise() ? "false" : "true");
                        dataBean.setPraise(!dataBean.isPraise());
                        if(type.equals("1")){
                            mPresenter.postPriaseComment(ApiKey.COURSE_PRAISE_COMMENT, apiKeyMap);
                        }else {
                            mPresenter.postLike(ApiKey.AUDIO_PRAISE_COMMENT, apiKeyMap);

                        }

                        break;
                }
            }
        });
        ImageLoaderUtils.displayTager(getContext(), (ImageView) headView.findViewById(R.id.iv_headview_sjk_zhuanlan_ban), result.getPic(), R.mipmap.ic_loading_square_big);
        ViewUtil.setText(headView, R.id.tv_headview_sjk_zhuanlan_teacher_profile, result.getAuthor().getTeaIntro());
        ViewUtil.setText(headView, R.id.tv_headview_sjk_zhuanlan_profile, result.getDetail());
        if (result.isIsBuy()) {
            mLlBottomContainer.setVisibility(View.GONE);
        }
        ViewUtil.setText(mTvZhuanlanBuy, HtmlUtil.format("订阅（¥%d）", result.getNeedCoin()/10));
    }


    @Override
    public void showBuySpecColumnResult(Result result) {
        boolean isBuy = "购买成功".equals(result.getResultMsg());
        PayResultPopupWindow payResultPopupWindow = new PayResultPopupWindow(getContext());
        payResultPopupWindow.setResult(isBuy, result.getResultMsg());
        PopupWindowUtil.show(getActivity(), payResultPopupWindow);
        //跳转 新的页面
        mRxManager.post(AppConstant.RXTag.ZL_BUY_SUCESS, true);
        refresh(isBuy,htmlLink);
    }


    @Override
    public void showLikeResult(Result bean) {

    }

    @Override
    public void showCertificate(StudyFinishBean.DataBean bean) {

    }

    @Override
    public void commentResult(SubscribeCommentResultBean.DataBean bean) {
        page = 1;
        apiKeyMap.put("page", String.valueOf(page));
        mAdapter.getPageBean().setRefresh(true);
        if(type.equals("1")){
            mPresenter.getCommentList(ApiKey.GET_COURSE_COMMENT, apiKeyMap);
        }else{
            mPresenter.getCommentList(ApiKey.AUDIO_GET_COMMENT, apiKeyMap);
        }
    }

    @Override
    public void showCommentList(List<SubscribeCommentListBean.DataBean> result) {
        List list = new ArrayList();
        if (EmptyUtils.isNotEmpty(result)) {
            if (result.size() > 3) {
                list.addAll(result.subList(0, 3));
            } else {
                list.addAll(result);
            }
        }
        if (EmptyUtils.isEmpty(list)) {
            list.add(new SubscribeCommentListBean.DataBean());
        }
        updateData(list);
    }

    private BuyZhuanLanUtil mBuyZhuanLanUtil;
    public void buy() {
        if (mBuyZhuanLanUtil == null) mBuyZhuanLanUtil = new BuyZhuanLanUtil();
        mBuyZhuanLanUtil.buy(getContext(), mPresenter, data, specColumnId);

    }
    public void refresh(boolean isBuy, String htmlLink) {
        finish();
    }


}
