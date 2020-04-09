package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     未购买专辑或视频的提示控件
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class ARewardV2View extends RelativeLayout {
    private View rootView;
    //显示价格
    private int videoCouponCount = 0;
    private TextView tvNeedAmount;
    private TextView tvNeedCoin;

    public ARewardV2View(Context context) {
        this(context, null);

    }

    public ARewardV2View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ARewardV2View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    //初始化控件
    private void initView() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.custom_bottom_areward_v2, this, true);
        setVisibility(GONE);
        tvNeedAmount = ViewUtil.getView(rootView, R.id.tv_horizontal_live_use_video_coupon_buy_course);
        tvNeedCoin = ViewUtil.getView(rootView, R.id.tv_horizontal_live_use_video_coupon_coin);
    }

    public void setCount(int count) {
        this.videoCouponCount = count;
    }

    public void setNeedCoin(String tag,int needCoin) {
//        购买课程 ¥30 (300
        tvNeedAmount.setText(String.format("购买%s ¥%d",tag, needCoin / 10));
        tvNeedCoin.setText(String.format(" (%d正念币)", needCoin));
    }

    public boolean hasVideoCoupon() {
        return videoCouponCount > 0;
    }

    public void setOnClick(OnClickListener onClick) {
        if (onClick != null) {
            ViewUtil.getView(rootView, R.id.ll_horizontal_use_video_coupon_buy).setOnClickListener(onClick);
            ViewUtil.getView(rootView, R.id.ll_horizontal_use_video_coupon_vip).setOnClickListener(onClick);
        }
    }

}
