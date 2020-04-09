package com.ziran.meiliao.widget.pupop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.priavteclasses.activity.GetVideoCouponActivity;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class VideoCouponTipsPopupWindow extends BasePopupWindow {

    public VideoCouponTipsPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_video_coupon_tips;
    }

    @Override
    protected void initViews(View contentView) {
        touchDismiss(R.id.touch_outside);
        setOnClickListener(R.id.tv_popuw_buy_video_coupon_tips_free);
        setOnClickListener(R.id.tv_popuw_buy_video_coupon_tips_buy);
        setOnClickListener(R.id.tv_popuw_buy_video_coupon_tips_cancel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_popuw_buy_video_coupon_tips_buy:
                BuyCoursePopupWindow popupwindow = new BuyCoursePopupWindow(mContext);
                popupwindow.setText(coinTitle, String.valueOf(useCoin), needCoin,memberPrice,levelDetail);
                popupwindow.setUrl(coverUrl);
                popupwindow.setStyle(useCoin<=needCoin);
                PopupWindowUtil.show((Activity) mContext, popupwindow);
                break;
            case R.id.tv_popuw_buy_video_coupon_tips_free:
                Intent intent = new Intent(mContext, GetVideoCouponActivity.class);
                intent.putExtra(AppConstant.SPKey.COURSE_ID, courseId);
                mContext.startActivity(intent);
                break;
        }
        dismiss();
    }
    private String  levelDetail;
    private int memberPrice;
    public int useCoin;
    private int needCoin;
    private String coinTitle;
    private String coinCouponTitle;
    private String coinCouponDes;
    private String coverUrl;
    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setParams(int useCoin, int needCoin, String coinTitle, String coinCouponDes, String coinCouponTitle, String coverUrl,String levelDetail,int memberPrice) {
        this.useCoin = useCoin;
        this.needCoin = needCoin;
        this.coinTitle = coinTitle;
        this.coinCouponDes = coinCouponDes;
        this.coinCouponTitle = coinCouponTitle;
        this.coverUrl = coverUrl;
        this.memberPrice=memberPrice;
        this.levelDetail=levelDetail;
    }

    public int getUseCoin() {
        return useCoin;
    }

    public void setUseCoin(int useCoin) {
        this.useCoin = useCoin;
    }

    public int getNeedCoin() {
        return needCoin;
    }

    public void setNeedCoin(int needCoin) {
        this.needCoin = needCoin;
    }

    public String getCoinTitle() {
        return coinTitle;
    }

    public void setCoinTitle(String coinTitle) {
        this.coinTitle = coinTitle;
    }

    public String getCoinCouponTitle() {
        return coinCouponTitle;
    }

    public void setCoinCouponTitle(String coinCouponTitle) {
        this.coinCouponTitle = coinCouponTitle;
    }

    public String getCoinCouponDes() {
        return coinCouponDes;
    }

    public void setCoinCouponDes(String coinCouponDes) {
        this.coinCouponDes = coinCouponDes;
    }
}