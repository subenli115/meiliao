package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.CheckVipLevelBean;
import com.ziran.meiliao.ui.bean.UserVipV1Bean;
import com.ziran.meiliao.ui.settings.activity.JoinAppActivity;
import com.ziran.meiliao.ui.settings.adapter.RechargeVipAdapter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PayUtil;
import com.ziran.meiliao.widget.SmoothCheckBox;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 加入VIP 的Fragment
 * Created by Administrator on 2017/1/16.
 */

public class JoinAppFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.ActionView<UserVipV1Bean,
        CheckVipLevelBean>, PayUtil.OnPayCallBack {

    @Bind(R.id.smoothCheckBox_wechat)
    SmoothCheckBox smoothCheckBoxWechat;
    @Bind(R.id.smoothCheckBox_zfb)
    SmoothCheckBox smoothCheckBoxZfb;
    @Bind(R.id.gv_recharge_amount)
    GridView gvRechargeAmount;
    @Bind(R.id.btn_joinapp_pay)
    Button mBtnJoinAppPay;
    private PayUtil payUtil;
    private JoinAppActivity appActivity;
    private RechargeVipAdapter mRechargeVipAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_joinvip;
    }

    @Override
    public void initView() {
        if (payUtil == null) {
            if (getActivity() instanceof JoinAppActivity) {
                appActivity = (JoinAppActivity) getActivity();
            }
            //支付请求类
            payUtil = new PayUtil(getContext());
            payUtil.setOnPayCallBack(this);
            //默认微信支付
            smoothCheckBoxWechat.setChecked(true);
            //支付选择发生变化
            SmoothCheckBox.OnCheckedChangeListener mOnCheckedChangeListener = new SmoothCheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                    if (isChecked) {
                        checkBox.setEnabled(false);
                        if (checkBox == smoothCheckBoxWechat) {
                            smoothCheckBoxZfb.setChecked(false);
                            smoothCheckBoxZfb.setEnabled(true);
                        } else {
                            smoothCheckBoxWechat.setChecked(false);
                            smoothCheckBoxWechat.setEnabled(true);
                        }
                    }
                }
            };
            //添加监听
            smoothCheckBoxWechat.setOnCheckedChangeListener(mOnCheckedChangeListener);
            smoothCheckBoxZfb.setOnCheckedChangeListener(mOnCheckedChangeListener);

            mRechargeVipAdapter = new RechargeVipAdapter(getContext(), R.layout.item_grid_joinvip);
            gvRechargeAmount.setAdapter(mRechargeVipAdapter);
            gvRechargeAmount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //改变选择的数值
                    mRechargeVipAdapter.changeSelect(position);
                }
            });

            if (EmptyUtils.isEmpty(WpyxConfig.getUserVipV1Bean())) {
                mPresenter.getData(ApiKey.VIP_YEAR_MEMBER_1, MapUtils.getDefMap(false), UserVipV1Bean.class);
            } else {
                mRechargeVipAdapter.replaceAll(WpyxConfig.getUserVipV1Bean());
                appActivity.show(View.VISIBLE);
            }
        }
    }

    //点击监听
    @OnClick({R.id.check_wechat, R.id.check_alipay, R.id.btn_joinapp_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_wechat:
                smoothCheckBoxWechat.setChecked(true);
                break;
            case R.id.check_alipay:
                smoothCheckBoxZfb.setChecked(true);
                break;
            case R.id.btn_joinapp_pay:
                UserVipV1Bean.DataBean item = mRechargeVipAdapter.getSelect();
                payUtil.setType(item.getType());
                payUtil.setPayId(String.valueOf(item.getVipId()));
                payUtil.setTitle(item.getTitle());
                payUtil.setPrice(item.getPrice());
                if (smoothCheckBoxWechat.isChecked()) {
                    //使用微信支付
                    payUtil.weChatPay();
                } else {
                    //使用支付宝支付
                    payUtil.alipayPay();
                }
                break;
        }
    }

    @Override
    public void onPaySuccess(int platform) {
        UserVipV1Bean.DataBean item = mRechargeVipAdapter.getSelect();
        //再次确认是否支付成功
        mPresenter.postAction(ApiKey.VIP_CHECK_LEVEL, MapUtils.getOnlyCan("vipId", item.getVipId()), CheckVipLevelBean.class);
    }

    @Override
    public void onPayFailed() {

    }

    @Override
    public void returnAction(CheckVipLevelBean result) {
        MyAPP.setIsVip(result);
        if (MyAPP.isVip()) {
            ToastUitl.showShort(getString(R.string.bocome_vip));
            mBtnJoinAppPay.setVisibility(View.GONE);
            if (appActivity != null) {
                appActivity.checkInitFragment(true);
            }
        }
    }

    @Override
    public void returnData(UserVipV1Bean result) {
        List<UserVipV1Bean.DataBean> resultData = result.getData();
        if (resultData != null) {
            resultData.get(0).setSelect(true);
            WpyxConfig.setUserVipV1Bean(resultData);
            mRechargeVipAdapter.replaceAll(resultData);
            appActivity.show(View.VISIBLE);
        }
    }
}
