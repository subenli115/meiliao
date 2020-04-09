package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.utils.PayUtil;
import com.ziran.meiliao.widget.SmoothCheckBox;


import butterknife.Bind;
import butterknife.OnClick;



public class CoursePayActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.iv_recharge_req)
    TextView tvPay;
    @Bind(R.id.tv_js)
    TextView tvJs;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_first_price)
    TextView tvFirstPrice;
    @Bind(R.id.tv_get_point)
    TextView tvPoint;

    @Bind(R.id.smoothCheckBox_wechat)
    SmoothCheckBox smoothCheckBoxWechat;
    @Bind(R.id.smoothCheckBox_zfb)
    SmoothCheckBox smoothCheckBoxZfb;

    private String allPrice;
    private String detailId;
    private String billNo;
    private String savePrice;
    private PayUtil payUtil;

    @Override
    public int getLayoutId() {
        return R.layout.ac_course_pay;
    }

    @Override
    public void returnData(Result result) {


    }

    @Override
    public void returnAction(Result result) {

    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        super.initView();
        if (getIntent() != null) {
            detailId = getIntent().getStringExtra("detailId");
            billNo = getIntent().getStringExtra("billNo");
            allPrice = getIntent().getStringExtra("allPrice");
            savePrice = getIntent().getStringExtra("savePrice");
        }
        tvPrice.setText("￥" + allPrice);
        tvJs.setText("已为您节省￥" + savePrice);
        tvFirstPrice.setText("￥" + (Double.parseDouble(allPrice) +
                Double.valueOf(savePrice)) + "");
        ntbTitle.setBackGroundColor1();
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        ntbTitle.setTitleWeizhi();
        ntbTitle.setTitleText("支付");
        tvPoint.setText("支付成功后，可获得" + (int) (Double.parseDouble(allPrice) / 10) + "积分");
        tvFirstPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

        smoothCheckBoxWechat.setChecked(true);
        smoothCheckBoxWechat.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked) {
                    smoothCheckBoxZfb.setChecked(false);
                    checkBox.setEnabled(false);
                    smoothCheckBoxZfb.setEnabled(true);
                }
            }
        });
        smoothCheckBoxZfb.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked) {
                    smoothCheckBoxWechat.setChecked(false);
                    checkBox.setEnabled(false);
                    smoothCheckBoxWechat.setEnabled(true);
                }
            }
        });
        if (payUtil == null) {
            payUtil = new PayUtil(mContext);
            payUtil.setOnPayCallBack(mOnPayCallBack);
        }
    }

    private PayUtil.OnPayCallBack mOnPayCallBack = new PayUtil.OnPayCallBack() {
        @Override
        public void onPaySuccess(int platform) {
            CoursePaySuccessActivity.startAction(mContext,(int)(Double.parseDouble(allPrice) / 10) +"",detailId);
            finish();
        }

        @Override
        public void onPayFailed() {
            CoursePayFailedActivity.startAction(mContext);
            finish();

        }
    };

    public static void startAction(Context mContext, String detailIds, String billNo,  String allPrice, String savePrice) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("detailId", detailIds + "");
        intent.putExtra("billNo", billNo);
        intent.putExtra("allPrice", allPrice);

        intent.putExtra("savePrice", savePrice);
        intent.setClass(mContext, CoursePayActivity.class);
        mContext.startActivity(intent);

    }

    @OnClick({R.id.iv_recharge_req, R.id.check_wechat, R.id.check_alipay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_recharge_req:
                payUtil.setPrice(Double.parseDouble(allPrice));
                payUtil.setDetailId(detailId);
                payUtil.setType("activity");
                payUtil.setmBillNum(billNo);
                payUtil.setTitle("购买课程");
                if (smoothCheckBoxWechat.isChecked()) {
                    //使用微信支付
                    payUtil.weChatPay();
                } else {
                    //使用支付宝支付
                    payUtil.alipayPay();
                }
                break;

            case R.id.check_wechat:
                smoothCheckBoxWechat.setChecked(true);
                break;

            case R.id.check_alipay:
                smoothCheckBoxZfb.setChecked(true);
                break;
        }
    }

}

