package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.utils.PayUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/11 15:13
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/11$
 * @updateDes ${TODO}
 */

public class SimplePayPopupWindow extends BasePopupWindow {
    private PayUtil payUtil;

    private TextView tvConent;

    public SimplePayPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected void initViews(View contentView) {
        super.initViews(contentView);
        setOnClickListener(R.id.tv_wechat_pay);
        setOnClickListener(R.id.tv_aplipay_pay);
        tvConent = ViewUtil.getView(contentView, R.id.tv_content);
        payUtil = new PayUtil(mContext);
        payUtil.setOnPayCallBack(new PayUtil.OnPayCallBack() {
            @Override
            public void onPaySuccess(int platform) {
                if (mOnPayCallBack != null) mOnPayCallBack.onPaySuccess(platform);
            }

            @Override
            public void onPayFailed() {
                if (mOnPayCallBack != null) mOnPayCallBack.onPayFailed();
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popup_simple_pay;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_wechat_pay:  //使用微信支付
                payUtil.weChatPay();
                break;
            case R.id.tv_aplipay_pay:   //使用支付宝支付
                payUtil.alipayPay();
                break;
        }
    }

    private PayUtil.OnPayCallBack mOnPayCallBack;

    public void setOnPayCallBack(PayUtil.OnPayCallBack onPayCallBack) {
        mOnPayCallBack = onPayCallBack;
    }


    public void setPamras(float price, String title, String id, String type) {
        payUtil.setPrice(price);
        payUtil.setTitle(title);
        ViewUtil.setText(tvConent, title);
        payUtil.setPayId(id);
        payUtil.setType(type);
    }
}
