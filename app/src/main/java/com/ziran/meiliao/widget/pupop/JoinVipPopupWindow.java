package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.CheckVipLevelBean;
import com.ziran.meiliao.ui.settings.adapter.RechargeVipAdapter;
import com.ziran.meiliao.ui.bean.UserVipV1Bean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PayUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class JoinVipPopupWindow extends BasePopupWindow implements PayUtil.OnPayCallBack {

    private RechargeVipAdapter mRechargeVipAdapter;
    private PayUtil payUtil;

    public JoinVipPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_joinvip;
    }

    @Override
    protected void initViews(View contentView) {
        GridView gvRechargeAmount = ViewUtil.getView(contentView, R.id.gv_joinvip);

        if (mRechargeVipAdapter == null) {
            mRechargeVipAdapter = new RechargeVipAdapter(contentView.getContext(), R.layout.item_grid_joinvip);
            gvRechargeAmount.setAdapter(mRechargeVipAdapter);
            gvRechargeAmount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mRechargeVipAdapter.changeSelect(position);
                }
            });

            payUtil = new PayUtil(contentView.getContext());
            payUtil.setOnPayCallBack(this);
        }
        ViewUtil.getView(contentView, R.id.iv_popuw_joinvip_alipay).setOnClickListener(this);
        ViewUtil.getView(contentView, R.id.iv_popuw_joinvip_wechat).setOnClickListener(this);
        touchDismiss(R.id.touch_outside);
        if (WpyxConfig.getUserVipV1Bean() == null) {
            OkHttpClientManager.getAsync(ApiKey.VIP_YEAR_MEMBER_1, MapUtils.getDefMap(false), new NewRequestCallBack<UserVipV1Bean>(UserVipV1Bean.class) {
                @Override
                public void onSuccess(UserVipV1Bean result) {
                    if (result==null) return;
                    result.getData().get(0).setSelect(true);
                    WpyxConfig.setUserVipV1Bean(result.getData());
                    mRechargeVipAdapter.replaceAll(result.getData());
                }
            });
        } else {
            mRechargeVipAdapter.replaceAll(WpyxConfig.getUserVipV1Bean());
        }
    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_popuw_joinvip_wechat:
                pay(1);
                break;
            case R.id.iv_popuw_joinvip_alipay:
                pay(0);
                break;
        }
    }

    private void pay(int i) {
        UserVipV1Bean.DataBean item = mRechargeVipAdapter.getSelect();
        if (item == null) return;
        payUtil.setType(item.getType());
        payUtil.setPayId(String.valueOf(item.getVipId()));
        payUtil.setTitle(item.getTitle());
        payUtil.setPrice(item.getPrice());
        if (i == 1) {
            payUtil.weChatPay();
        } else {
            payUtil.alipayPay();
        }
    }
    private PayUtil.OnPayCallBack mOnPayCallBack;

    public void setOnPayCallBack(PayUtil.OnPayCallBack onPayCallBack) {
        mOnPayCallBack = onPayCallBack;
    }

    @Override
    public void onPaySuccess(final int platform) {
        UserVipV1Bean.DataBean item = mRechargeVipAdapter.getSelect();
        OkHttpClientManager.postAsync(ApiKey.VIP_CHECK_LEVEL, MapUtils.getOnlyCan("vipId", item.getVipId()), new
                NewRequestCallBack<CheckVipLevelBean>(CheckVipLevelBean.class) {
            @Override
            public void onSuccess(CheckVipLevelBean result) {
                if (mOnPayCallBack!=null){
                    mOnPayCallBack.onPaySuccess(platform);
                }
                dismiss();

            }
        });
    }

    @Override
    public void onPayFailed() {
        dismiss();
        if (mOnPayCallBack!=null){
            mOnPayCallBack.onPayFailed();
        }
    }
}