package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebSettings;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonWebActivity;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.CourseShareBean;
import com.ziran.meiliao.ui.priavteclasses.util.TrailerCollectConfig;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

import java.util.HashMap;
import java.util.Map;

import static com.ziran.meiliao.constant.ApiKey.ACTIVITY_GETSHAREDATA;

/**
 * Created by Administrator on 2017/3/2.
 */

public class GongZuoFangActivity extends CommonWebActivity  {

   private ActisData dataBean;

    public static void startAction(Context context, ActisData dataBean) {
        startAction(context, dataBean, null);
    }

    public static void startAction(Context context, ActisData dataBean, String title) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, GongZuoFangActivity.class);
            Bundle bundle = new Bundle();
            String url = EmptyUtils.isEmpty(title) ? dataBean.getUrl() : dataBean.getSignup();
            TrailerCollectConfig.addConfig(url,dataBean);
            bundle.putString(AppConstant.SPKey.EXTRAS_URL,url );
            if (EmptyUtils.isEmpty(dataBean.getShareUrl())){
                dataBean.setShareUrl(url);
            }
            if (EmptyUtils.isNotEmpty(title)) {
                bundle.putString(AppConstant.ExtraKey.VIDEO_TITLE, title);
            }
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void initView() {
        tvHeadviewTitle.setText(R.string.act_detail);
        super.initView();
        Map<String, String> defMap = MapUtils.getDefMap(true);
        if(dataBean.getActivityId()!=null&&!dataBean.getActivityId().equals("")){
            defMap.put("activityId",dataBean.getActivityId()+"");
            OkHttpClientManager.postAsync(ACTIVITY_GETSHAREDATA, defMap, new NewRequestCallBack<CourseShareBean>(CourseShareBean.class) {
                @Override
                protected void onSuccess(CourseShareBean result) {
                    if (EmptyUtils.isNotEmpty(result.getData())) {
                        if (EmptyUtils.isNotEmpty(result.getData().getShareUrl())) {
                            CourseShareBean.DataBean data = result.getData();
                            dataBean.setSharePic(data.getSharePic());
                            dataBean.setShareTitle(data.getShareTitle());
                            dataBean.setShareUrl(data.getShareUrl());
                            dataBean.setShareDescript(data.getShareDescript());
                            return;
                        }
                    }
                }
                @Override
                public void onError(String msg, int code) {
                }
            });
        }
    }

    @Override
    protected void loadUrl() {
        //允许混合模式（http与https）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        String rr = "https://www.psytap.com";
        if (("4.4.3".equals(android.os.Build.VERSION.RELEASE))
                || ("4.4.4".equals(android.os.Build.VERSION.RELEASE))) {
            //兼容这两个版本设置referer无效的问题
            webView.loadDataWithBaseURL(rr,
                    "<script>window.location.href=\"" + url + "\";</script>",
                    "text/html", "utf-8", null);
        } else {
            Map<String, String> extraHeaders = new HashMap<>();
            extraHeaders.put("Referer",rr);

            webView.loadUrl(url, extraHeaders);
        }



    }


    @Override
    protected void initBundle(Bundle extras) {
        dataBean = TrailerCollectConfig.getConfig(url);
        String title = extras.getString(AppConstant.ExtraKey.VIDEO_TITLE);
        if (EmptyUtils.isNotEmpty(title)) {
            tvHeadviewTitle.setText(title);
//            ivCollect.setVisibility(View.GONE);
//            ivShare.setVisibility(View.GONE);
        } else {
            if (dataBean != null) {
                setCollect(ivCollect, dataBean.isIsCollect());
            }
        }

    }

    @Override
    protected void setShow(boolean show) {
        if (mSharePopupWindow !=null){
            mSharePopupWindow.dismiss();
        }
    }

   private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            ToastUitl.showShort(msg.obj.toString());
            dataBean.setIsCollect(!dataBean.isIsCollect());
            setCollect(ivCollect, dataBean.isIsCollect());
            mRxManager.post(AppConstant.UPDATE_COLLECT_ACTIVITY, dataBean);
            return true;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!dataBean.isIsCollect()) {
            RxManagerUtil.post(AppConstant.UPDATE_ENROL_ACTIVITY, dataBean);
        }
    }

    @Override
    protected void doCollcet() {

        OkHttpClientManager.postAsync(ApiKey.COLLECT_ACT, MapUtils.getCollect(dataBean.isIsCollect(), "activityIds", String.valueOf
                (dataBean.getId())), new NewRequestCallBack<Result>(Result.class) {

            @Override
            public void onSuccess(Result result) {
                handler.sendMessage(handler.obtainMessage(1, result.getResultMsg()));
            }
        });
    }

    @Override
    protected void share() {
        SharePopupWindow.showPopup(this,mSharePopupWindow,dataBean);
    }

}
