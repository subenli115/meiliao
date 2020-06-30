package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonWebActivity;
import com.ziran.meiliao.ui.bean.ResBean;
import com.ziran.meiliao.utils.CheckUtil;

import java.util.HashMap;

/**
 * author admin
 * create  2017/3/27 17
 * <p>
 * updateAuthor     $admin
 * updateDate   2017/3/27 17
 * updateDes    ${TODO}
 */

public class PayWebActivity extends CommonWebActivity {

    private ResBean.DataBean mDataBean;
    private String mReferer;
    private boolean first=true;

    public static void startAction(Context context, String url) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, PayWebActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.SPKey.EXTRAS_URL, url);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    @Override
    public void initView() {
        super.initView();
        ivCollect.setVisibility(View.GONE);
        ivShare.setVisibility(View.GONE);
        setMyTitle("支付");
        webView.setWebViewClient(webViewClient);
        if(getIntent()!=null){
            String url = getIntent().getStringExtra("url");
            mReferer = getIntent().getStringExtra("referer");
            Log.e("mReferer",""+mReferer);
            HashMap<String, String> map = new HashMap<>(1);
            map.put("Referer", mReferer);
            webView.loadUrl(url,map);
        }
    }

    // 设置微信 H5 支付调用 loadDataWithBaseURL 的标记位，避免循环调用，
    // 再次进入微信 H5 支付流程时记得重置此标记位状态
    boolean firstVisitWXH5PayUrl = true;

    WebViewClient webViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("PayWebActivity",""+url);
                first=false;
            if (url.startsWith("weixin://")) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } catch (Exception e) {
                    // 防止手机没有安装处理某个 scheme 开头的 url 的 APP 导致 crash
                    return true;
                }
            } else if (url.startsWith("alipays://") || url.startsWith("alipay")) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } catch (Exception e) {
                    // 防止手机没有安装处理某个 scheme 开头的 url 的 APP 导致 crash
                    // 启动支付宝 App 失败，会自行跳转支付宝网页支付
                    return true;
                }
            }


            // 处理微信 H5 支付跳转时验证请求头 referer 失效
            // 验证不通过会出现“商家参数格式有误，请联系商家解决”
            Log.e("Referer",""+url);
            if (url.contains("wx.tenpay.com")) {
                // 申请微信 H5 支付时填写的域名
                // 比如经常用来测试网络连通性的 http://www.baidu.com
                String referer =mReferer;

                // 兼容 Android 4.4.3 和 4.4.4 两个系统版本设置 referer 无效的问题
                if (("4.4.3".equals(android.os.Build.VERSION.RELEASE))
                        || ("4.4.4".equals(android.os.Build.VERSION.RELEASE))) {
                    if (firstVisitWXH5PayUrl) {
                        view.loadDataWithBaseURL(referer, "<script>window.location.href=\"" + url + "\";</script>",
                                "text/html", "utf-8", null);
                        // 修改标记位状态，避免循环调用
                        // 再次进入微信H5支付流程时记得重置状态 firstVisitWXH5PayUrl = true
                        firstVisitWXH5PayUrl = false;
                    }
                    // 返回 false 由系统 WebView 自己处理该 url
                    return false;
                } else {
                    // HashMap 指定容量初始化，避免不必要的内存消耗
                    HashMap<String, String> map = new HashMap<>(1);
                    map.put("Referer", referer);
                    Log.e("Referer",""+referer);
                    view.loadUrl(url, map);
                    return true;
                }
            }
            return false;
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult",""+resultCode);
    }

    @Override
    protected void initBundle(Bundle extras) {
        if (EmptyUtils.isNotEmpty(MeiliaoConfig.getResBean()) && EmptyUtils.isNotEmpty(MeiliaoConfig.getResBean().getData())) {
            mDataBean = MeiliaoConfig.getResBean().getData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!first){
            finish();
        }

    }
}
