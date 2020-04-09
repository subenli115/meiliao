package com.ziran.meiliao.javascript;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.HorizontalHistoryActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.HorizontalLiveActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.SearchActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.VerticalLiveActivity;
import com.ziran.meiliao.utils.PayUtil;
import com.ziran.meiliao.widget.pupop.SimplePayPopupWindow;

/**
 * 安卓端与后台web端的交互
 * Created by Administrator on 2017/3/4.
 */

public class JavaScriptObject {

    @JavascriptInterface
    public void toast(String text) {
        ToastUitl.showShort(text);
    }

    @JavascriptInterface
    public void showDialogForLoading(String tips) {
        LoadingDialog.showDialogForLoading(AppManager.getAppManager().currentActivity(), tips, false);
    }

    @JavascriptInterface
    public void cancelDialogForLoading() {
        LoadingDialog.cancelDialogForLoading();
    }

    @JavascriptInterface
    public String getToken() {
        return MyAPP.getAccessToken();
    }

    //废弃方法
    @JavascriptInterface
    public void openCourse(int courseId, int liveStreaming) {
        if (liveStreaming == 1) {
            HorizontalLiveActivity.startAction(courseId);
        } else {
            HorizontalHistoryActivity.startAction(courseId);
        }
    }

    //更新方法  1.10.5
    @JavascriptInterface
    public void openCourse(int courseId, int liveStreaming, int states) {
        if (states == 0) {
            if (liveStreaming == 1) {
                HorizontalLiveActivity.startAction(courseId);
            } else {
                HorizontalHistoryActivity.startAction(courseId);
            }
        } else {
            VerticalLiveActivity.startAction(AppManager.getAppManager().currentActivity(), String.valueOf(courseId), liveStreaming);
        }
    }

    @JavascriptInterface
    public void openAlbum(int albumId) {
        Activity activity = AppManager.getAppManager().currentActivity();
        AlbumDetailActivity.startAction(activity, String.valueOf(albumId));
//        activity.finish();
    }

    @JavascriptInterface
    public void closeWebView() {
        AppManager.getAppManager().finishActivity(SearchActivity.class);
    }

    @JavascriptInterface
    public void closeView() {
        RxManagerUtil.post(AppConstant.RXTag.CLOSE_VIEW, true);
    }

    @JavascriptInterface
    public void tel(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppManager.getAppManager().currentActivity().startActivity(intent);
    }

//    @JavascriptInterface
//    public void referer(String phone) {
////        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
////        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        AppManager.getAppManager().currentActivity().startActivity(intent);
//    }

    //pay(type,token,id)

    //pay( String payId, String title, String type,float price, String usedCouponId,String payType)
    @JavascriptInterface
    public void pay(String payId, String title, String type, float price, String usedCouponId, String payTpye) {

        PayUtil payUtil = new PayUtil(AppManager.getAppManager().currentActivity());
        payUtil.setType(type);
        payUtil.setPayId(payId);
        payUtil.setPrice(price);
        payUtil.setTitle(title);
        if (!"0".equals(usedCouponId)) {
            payUtil.setCouponId(usedCouponId);
        }
        payUtil.setOnPayCallBack(new PayUtil.OnPayCallBack() {
            @Override
            public void onPaySuccess(int platform) {
                RxManagerUtil.post(AppConstant.RXTag.ON_WEB_PAY, 1);

            }

            @Override
            public void onPayFailed() {
                RxManagerUtil.post(AppConstant.RXTag.ON_WEB_PAY, -1);
            }
        });
        if ("wechat".equals(payTpye)) {
            payUtil.weChatPay();
        } else if ("alipay".equals(payTpye)) {
            payUtil.alipayPay();
        }
    }

    @JavascriptInterface
    public void simplePay(String payId, String title, String type, float price, String usedCouponId ) {
        SimplePayPopupWindow simplePayPopupWindow   =new SimplePayPopupWindow(AppManager.getAppManager().currentActivity());
        simplePayPopupWindow.setPamras(price,title,payId,type);
        simplePayPopupWindow.setOnPayCallBack(new PayUtil.OnPayCallBack() {
            @Override
            public void onPaySuccess(int platform) {
                RxManagerUtil.post(AppConstant.RXTag.ON_WEB_PAY, 1);

            }

            @Override
            public void onPayFailed() {
                RxManagerUtil.post(AppConstant.RXTag.ON_WEB_PAY, -1);
            }
        });
        simplePayPopupWindow.show();
    }
}
