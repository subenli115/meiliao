package com.ziran.meiliao.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.PayResult;
import com.ziran.meiliao.ui.bean.PayWXBean;
import com.ziran.meiliao.ui.bean.PayWxH5Bean;
import com.ziran.meiliao.ui.bean.PayZFBAppBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.settings.activity.PayWebActivity;
import com.ziran.meiliao.wxapi.WXPayEntryActivity;

import java.util.Map;

import static com.ziran.meiliao.constant.ApiKey.ACCOUNT_ORDER;
import static com.ziran.meiliao.constant.ApiKey.ACCOUNT_WX_WXCHATPAY;
import static com.ziran.meiliao.constant.ApiKey.ACCOUNT_WX_WXPAY;
import static com.ziran.meiliao.constant.ApiKey.ACCOUNT_ZFB_APPALIPAY;

public class PayUtils {

    private final IWXAPI msgApi;
    private final int mId;
    private final double mPrice;
    private String url="http://api.ziran518.com/alipay.html";
    private Context mContext;
    public static String orderId;

    public PayUtils(Context context,double price,int id) {
        mContext = context;
        mId=id;
        mPrice=price;
        msgApi = WXAPIFactory.createWXAPI(mContext, null);
        msgApi.registerApp(MyAPP.wcAppId);
    }


    public void WxPay() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("goodsId", mId + "");
        defMap.put("userId", MyAPP.getUserId());
        defMap.put("number", "1");
        defMap.put("price", mPrice+"");
        defMap.put("accessToken", MyAPP.getAccessToken());
        OkHttpClientManager.postAsyncAddHead(ACCOUNT_WX_WXCHATPAY, defMap, "", new
                NewRequestCallBack<PayWXBean>(PayWXBean.class) {

                    @Override
                    public void onSuccess(PayWXBean bean) {
                        PayReq request = new PayReq();
                         orderId = bean.getData().getOrderId();
                        PayWXBean.DataBean data = bean.getData();
                        request.appId = MyAPP.getWcAppId();
                        request.partnerId = data.getMch_id();
                        request.prepayId= data.getPrepay_id();
                        request.packageValue = "Sign=WXPay";
                        request.nonceStr= data.getNonce_str();
                        request.timeStamp= data.getTimeStamp();
                        request.sign= data.getSign();
                        msgApi.sendReq(request);

                    }

                    @Override
                    public void onError(String msg, int code) {
                    }
                });
    }
    public void ZfbH5Pay() {
        Map<String, String> defMap = MapUtils.getDefMap(false);
        defMap.put("goodsId",mId+"");
        defMap.put("userId", MyAPP.getUserId());
        defMap.put("number","1");
        defMap.put("price",mPrice+"");
        defMap.put("token",MyAPP.getAccessToken());
        StringBuilder params = new StringBuilder();
        params.append(url);
        if (null != defMap && !defMap.isEmpty()) {
            if (!url.contains("?") && !url.endsWith("?"))
                params.append("?");
            String logInfo;
            String value;
            boolean isFirst = params.toString().endsWith("?");
            for (String name : defMap.keySet()) {
                value = defMap.get(name);
                value = value == null ? "" : value;
                if (isFirst) {
                    logInfo = name + "=" + value;
                    isFirst = false;
                } else {
                    logInfo = "&" + name + "=" + value;
                }
                params.append(logInfo);
            }
        }
        Log.e("ssssssssss",""+params.toString());
        goToPay(params.toString(),"");
    }

    public void WxH5Pay() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("goodsId", mId + "");
        defMap.put("userId", MyAPP.getUserId());
        defMap.put("number", "1");
        defMap.put("price", mPrice+"");
        defMap.put("token", MyAPP.getAccessToken());
        OkHttpClientManager.postAsyncAddHead(ACCOUNT_WX_WXPAY, defMap, "", new
                NewRequestCallBack<PayWxH5Bean>(PayWxH5Bean.class) {

                    @Override
                    public void onSuccess(PayWxH5Bean bean) {
                        goToPay(bean.getData().getMweb_url(), bean.getData().getReferer());
                    }

                    @Override
                    public void onError(String msg, int code) {
                    }
                });
    }


    public void goToPay(String url, String referer) {
        Intent intent = new Intent();
        intent.putExtra("url", url);
        intent.putExtra("referer", referer);
        intent.setClass(mContext, PayWebActivity.class);
        mContext.startActivity(intent);
    }
    public void ZfbPay(){
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("goodsId", mId + "");
        defMap.put("userId", MyAPP.getUserId());
        defMap.put("number", "1");
        defMap.put("price", mPrice+"");
        defMap.put("token", MyAPP.getAccessToken());
        OkHttpClientManager.postAsyncAddHead(ACCOUNT_ZFB_APPALIPAY, defMap, "", new
                NewRequestCallBack<PayZFBAppBean>(PayZFBAppBean.class) {

                    @Override
                    public void onSuccess(PayZFBAppBean bean) {
                         orderId = bean.getData().getOrderId();
                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                                PayTask alipay = new PayTask((Activity) mContext);
                                Map <String,String> result = alipay.payV2(bean.getData().getBody(),true);
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };
                        // 必须异步调用
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }

                    @Override
                    public void onError(String msg, int code) {
                    }
                });
    }






    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Intent intent = new Intent();
                        intent.putExtra("resultStatus","9000");
                        intent.setClass(mContext, WXPayEntryActivity.class);
                        mContext.startActivity(intent);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    }
                    break;
                }
            }
        };
    };

}
