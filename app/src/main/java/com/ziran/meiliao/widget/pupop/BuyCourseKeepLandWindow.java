package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class BuyCourseKeepLandWindow extends BasePopupWindow {
    private ImageView ivHead;
    private TextView tvBuy;
    private TextView tvTitle;
    private TextView tvBalance;
    private TextView tvAmount;
    private TextView tvBalanceTips;
    private TextView tvMemberPrice;
    private TextView tvAmountMember;
    private TextView tvNeedAmout;
    private TextView tvNeedCoupon;

    public BuyCourseKeepLandWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.pop_fitness_buy_course_land;
    }

    @Override
    protected void initViews(View contentView) {
        touchDismiss(R.id.touch_outside);
        ivHead = ViewUtil.getView(mContentView, R.id.iv_popuw_buy_video_coupon_pic);
        tvBuy = ViewUtil.getView(mContentView, R.id.iv_popuw_buy_video_coupon_buy);
        tvTitle = ViewUtil.getView(mContentView, R.id.tv_popuw_buy_video_coupon_title);
        tvBalance = ViewUtil.getView(mContentView, R.id.tv_popuw_buy_video_coupon_balance);
        tvAmount = ViewUtil.getView(mContentView, R.id.tv_popuw_buy_video_coupon_need_amount);
        tvBalanceTips = ViewUtil.getView(mContentView, R.id.tv_popuw_buy_video_coupon_tips);
        tvAmountMember=ViewUtil.getView(mContentView, R.id.tv_popuw_buy_album_amount_member);
        tvNeedAmout=ViewUtil.getView(mContentView, R.id.tv_need_amount_member);
        tvNeedCoupon=ViewUtil.getView(mContentView, R.id.tv_popuw_buy_coupon_need_amount);
        touchDismiss(R.id.touch_outside);
        tvBuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_popuw_buy_video_coupon_buy:
                if (needRecharge) {
                    RechargeActivity.startAction(mContext, balance);
                } else {
                    RxManagerUtil.post(AppConstant.RXTag.REQ_BUY_COURSE, true);
                }
                dismiss();
                break;
        }
        dismiss();
    }





    private String balance;

    public void setText(String title, String balance, int amount,int NewAmount) {
        this.balance = balance;
        ViewUtil.setText(tvTitle, "正在购买"+title+"课程");
        ViewUtil.setText(tvBalance, balance);
        ViewUtil.setText(tvAmount, amount/10+"元");
        ViewUtil.setText(tvAmountMember, NewAmount/10+"元");
        ViewUtil.setText(tvNeedAmout, NewAmount+"");
        ViewUtil.setText(tvNeedCoupon, amount+"");
    }

    public void setUrl(String picUrl) {
        ImageLoaderUtils.display(mContext, ivHead, picUrl);
    }

    private boolean needRecharge;

    public void setStyle(boolean needRecharge) {
        this.needRecharge = needRecharge;
        String buyText = needRecharge ? "去充值" : "确认购买";
        String tipsText = needRecharge ? "您的余额不足" : "您有";
        tvBalance.setVisibility(needRecharge ? View.GONE : View.VISIBLE);
        tvBalanceTips.setText(tipsText);
        tvBuy.setText(buyText);
    }
}