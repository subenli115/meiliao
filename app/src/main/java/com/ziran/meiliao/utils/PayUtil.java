package com.ziran.meiliao.utils;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.beecolud.BillUtils;
import com.beecolud.DialogUtils;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.AppStartUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.StringDataBean;

import java.util.HashMap;
import java.util.Map;

import cn.beecloud.BCPay;
import cn.beecloud.async.BCCallback;
import cn.beecloud.async.BCResult;
import cn.beecloud.entity.BCPayResult;


/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     第三方支付的工具类(目前微信,支付宝)
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class PayUtil {

    private String mBillNum=BillUtils.genBillNum();
    //支付额外参数
    private String type;
    //支付额外参数
    private String detailId;
    //支付额外参数
    private String id;
    //支付的标题
    private String title;
    //支付的价格
    private int price;
    //上下文对象
    private Context mContext;
    //当前的第三方支付
    private int curr_pay;
    //加载中对话框
    private ProgressDialog loadingDialog;
    //支付请求的额外参数
    private Map<String, String> mapOptional = new HashMap<>();
    //支付回调的消息
    private String toastMsg;
    //支付完成监听
    private OnPayFinishListener onPayFinishListener;
    //支付点击监听
    private OnPayCallBack payClickListener;
    //是否支付成功
    private boolean isPaySuccess;
    //优惠劵的id
    private String usedCouponId;


    //微信支付请求
    public static final int PAY_REQUSE_WECHAT = 1;
    //支付宝支付请求
    public static final int PAY_REQUSE_ALIPAY = 2;

    public PayUtil(Context context) {
        mContext = context;
        loadingDialog = DialogUtils.getPayProgressDialog(context);
        checkPaySecreId();
    }

    public String getmBillNum() {
        return mBillNum;
    }

    public void setmBillNum(String mBillNum) {
        this.mBillNum = mBillNum;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayId() {
        return id;
    }

    public void setPrice(int price) {
        if (price != 0) {
            this.price = price;
        }
    }

    public void setPrice(float price) {
        if (price != 0) {
            this.price = (int) (price * 100);
        }
    }
    public void setPrice(double price) {
        if (price != 0) {
            this.price = (int) (price * 100);
        }
    }

    public void setPayId(String id) {
        this.id = id;
    }
    public void setCouponId(String usedCouponId) {
        this.usedCouponId = usedCouponId;
    }
    
    /**
     *
     */
    public interface OnPayClickListener { void onPaySuccess(int platform); void onPayFailed(); }
    public interface OnPayCallBack { void onPaySuccess(int platform); void onPayFailed(); }

    public interface OnPayFinishListener { void onFinish(); }

    public void setOnPayFinishListener(OnPayFinishListener onPayFinishListener) {
        this.onPayFinishListener = onPayFinishListener;
    }

    public void setOnPayClickListener(OnPayCallBack moreClickListener) {
        this.payClickListener = moreClickListener;
    }
    public void setOnPayCallBack(OnPayCallBack moreClickListener) {
        this.payClickListener = moreClickListener;
    }
    public void weChatPay() {
        curr_pay = PAY_REQUSE_WECHAT;
        loadingDialog.show();
        // 在发起微信请求之前必须先initWechatPay
        // 第二个参数实现BCCallback接口，在done方法中查看支付结果
        setMaps();

        if (BCPay.isWXAppInstalledAndSupported() && BCPay.isWXPaySupported()) {

            BCPay.getInstance(mContext).reqWXPaymentAsync(
                    title,               //订单标题
                    price,                           //订单金额(分)
                    mBillNum,  //订单流水号
                    mapOptional,            //扩展参数(可以null)
                    bcCallback);            //支付完成后回调入口
        } else {
            try {
                AppStartUtil.startWechat(mContext);
                HandlerUtil.runMain(new Runnable() {
                    @Override
                    public void run() {
                        BCPay.getInstance(mContext).reqWXPaymentAsync(
                                title,               //订单标题
                                price,                           //订单金额(分)
                                mBillNum,  //订单流水号
                                mapOptional,            //扩展参数(可以null)
                                bcCallback);            //支付完成后回调入口
                    }
                }, 1800);
            } catch (ActivityNotFoundException e) {
                ToastUitl.showShort(mContext.getString(R.string.noinstalled_wechat));
                loadingDialog.dismiss();
                isPaySuccess = false;
            }
        }

    }

    private void setMaps() {
        mapOptional.clear();
        mapOptional.put("type", type);
        mapOptional.put("id", id);
        mapOptional.put("detailId", detailId);
        mapOptional.put("billNo",mBillNum);
        mapOptional.put("accessToken", MyAPP.getAccessToken());
        if (EmptyUtils.isNotEmpty(usedCouponId)) {
            mapOptional.put("usedCouponId", usedCouponId);
        }
    }

    public void alipayPay() {
        curr_pay = PAY_REQUSE_ALIPAY;
        if (loadingDialog != null) {
            loadingDialog.show();
        }
        setMaps();
        BCPay.getInstance(mContext).reqAliPaymentAsync(
                title,
                price,
                mBillNum,
                mapOptional,
                bcCallback);
    }
    private BCCallback bcCallback = new BCCallback() {
        @Override
        public void done(final BCResult bcResult) {
            final BCPayResult bcPayResult = (BCPayResult) bcResult;
            //此处关闭loading界面
            loadingDialog.dismiss();
            //根据你自己的需求处理支付结果
            String result = bcPayResult.getResult();
            /*
              注意！
              所有支付渠道建议以服务端的状态金额为准，此处返回的RESULT_SUCCESS仅仅代表手机端支付成功
            */
            Message msg = mHandler.obtainMessage();
            //单纯的显示支付结果
            msg.what = 2;
            if (result.equals(BCPayResult.RESULT_SUCCESS)) {
                toastMsg = "用户支付成功";
                isPaySuccess = true;
            } else if (result.equals(BCPayResult.RESULT_CANCEL)) {
                toastMsg = "用户取消支付";
                isPaySuccess = false;
            } else if (result.equals(BCPayResult.RESULT_FAIL)) {
                toastMsg = "支付失败, 原因: " + bcPayResult.getErrCode() +
                        " # " + bcPayResult.getErrMsg() +
                        " # " + bcPayResult.getDetailInfo();
                isPaySuccess = false;
                /**
                 * 你发布的项目中不应该出现如下错误，此处由于支付宝政策原因，
                 * 不再提供支付宝支付的测试功能，所以给出提示说明
                 */
                if (bcPayResult.getErrMsg().equals("PAY_FACTOR_NOT_SET") &&
                        bcPayResult.getDetailInfo().startsWith("支付宝参数")) {
                    toastMsg = "支付失败：由于支付宝政策原因，故不再提供支付宝支付的测试功能，给您带来的不便，敬请谅解";
                }
                /**
                 * 以下是正常流程，请按需处理失败信息
                 */
                if (bcPayResult.getErrMsg().equals(BCPayResult.FAIL_PLUGIN_NOT_INSTALLED)) {
                    //银联需要重新安装控件
                    msg.what = 1;
                }
            } else if (result.equals(BCPayResult.RESULT_UNKNOWN)) {
                //可能出现在支付宝8000返回状态
                toastMsg = "订单状态未知";
                isPaySuccess = false;
            } else {
                isPaySuccess = false;
                toastMsg = "invalid return";
            }
            mHandler.sendMessage(msg);
        }
    };

    // Defines a Handler object that's attached to the UI thread.
    // 通过Handler.Callback()可消除内存泄漏警告
    private Handler mHandler = new Handler(new Handler.Callback() {
        /**
         * Callback interface you can use when instantiating a Handler to avoid
         * having to implement your own subclass of Handler.
         *
         * handleMessage() defines the operations to perform when
         * the Handler receives a new Message to process.
         */
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //如果用户手机没有安装银联支付控件,则会提示用户安装
                    DialogUtils.showUPPayToast(mContext);
                    break;
                case 2:
                    ToastUitl.showShort(toastMsg);
                    if (!isSetCallBack && payClickListener != null) {
                        if (isPaySuccess){
                            payClickListener.onPaySuccess(curr_pay);
                        }else{
                            payClickListener.onPayFailed();
                        }
                    }
                    loadingDialog.dismiss();
                    if (onPayFinishListener != null) {
                        onPayFinishListener.onFinish();
                    }
                    break;
            }
            return true;
        }
    });


    public boolean isPaySuccess() {
        return isPaySuccess;
    }

    private boolean isSetCallBack = false;

    public void callBack() {
        if (payClickListener != null) {
            if (isPaySuccess) {
                payClickListener.onPaySuccess(curr_pay);
            } else {
                payClickListener.onPayFailed();
            }
        }
        isSetCallBack = true;
    }

//    protected void getBillInfoByID(String id) {
//
//        BCQuery.getInstance().queryBillByIDAsync(id,
//                new BCCallback() {
//                    @Override
//                    public void done(BCResult result) {
//                        BCQueryBillResult billResult = (BCQueryBillResult) result;
//
//                        Log.d(TAG, "------ response info ------");
//                        Log.d(TAG, "------getResultCode------" + billResult.getResultCode());
//                        Log.d(TAG, "------getResultMsg------" + billResult.getResultMsg());
//                        Log.d(TAG, "------getErrDetail------" + billResult.getErrDetail());
//
//                        if (billResult.getResultCode() != 0)
//                            return;
//
//                        Log.d(TAG, "------- bill info ------");
//                        BCBillOrder billOrder = billResult.getBill();
//                        Log.d(TAG, "订单唯一标识符：" + billOrder.getId());
//                        Log.d(TAG, "订单号:" + billOrder.getBillNum());
//                        Log.d(TAG, "订单金额, 单位为分:" + billOrder.getTotalFee());
//                        Log.d(TAG, "渠道类型:" + BCReqParams.BCChannelTypes.getTranslatedChannelName(billOrder.getChannel()));
//                        Log.d(TAG, "子渠道类型:" + BCReqParams.BCChannelTypes.getTranslatedChannelName(billOrder.getSubChannel()));
//                        Log.d(TAG, "订单是否成功:" + billOrder.getPayResult());
//
//                        if (billOrder.getPayResult())
//                            Log.d(TAG, "渠道返回的交易号，未支付成功时，是不含该参数的:" + billOrder.getTradeNum());
//                        else
//                            Log.d(TAG, "订单是否被撤销，该参数仅在线下产品（例如二维码和扫码支付）有效:"
//                                    + billOrder.getRevertResult());
//
//                        Log.d(TAG, "订单创建时间:" + new Date(billOrder.getCreatedTime()));
//                        Log.d(TAG, "扩展参数:" + billOrder.getOptional());
//                        Log.d(TAG, "订单是否已经退款成功(用于后期查询): " + billOrder.getRefundResult());
//                        Log.d(TAG, "渠道返回的详细信息，按需处理: " + billOrder.getMessageDetail());
//
//                    }
//                });
//    }

    public static void checkPaySecreId() {
        if (TextUtils.isEmpty(MyAPP.getSecretId())) {
            OkHttpClientManager.getAsync(ApiKey.GET_BEECLOUD_APPSECRET, MapUtils.getDefMap(false), new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
                @Override
                public void onSuccess(StringDataBean result) {
                    MyAPP.setSecretId(result.getData());
                }
            });
        }
    }
}
