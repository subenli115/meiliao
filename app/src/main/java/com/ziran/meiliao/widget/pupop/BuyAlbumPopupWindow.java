package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.utils.AnimationUtil;

import java.math.BigDecimal;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class BuyAlbumPopupWindow extends BasePopupWindow {
    private ImageView ivHead;
    private TextView tvBuy;
    private TextView tvTitle;
    private TextView tvBalance;
    private TextView tvBalanceTips;
    private TextView tvAmount;
    private TextView tvMemberPrice;
    private TextView tvAmountMember;

    public BuyAlbumPopupWindow(Context context) {
        super(context);
    }

    protected int getLayoutResId() {
        return R.layout.popuw_buy_album;
    }

    public void setTitle(String title) {
        ViewUtil.setText(tvTitle, title);
    }

    public void setBalance(int balance) {
        ViewUtil.setText(tvBalance, String.valueOf(balance));
    }

    public void setAmount(float amount) {
        BigDecimal b = new BigDecimal(String.valueOf(amount));
        double amount1 = b.doubleValue();
        int point =(int) amount * 10;
        ViewUtil.setText(tvAmount, point+"("+amount1+"元 )");
    }
    public void setMemberPrice(String levelDetail) {
        ViewUtil.setText(tvMemberPrice, levelDetail+"价");
    }
    public void setTvAmountMember(float amount) {
        BigDecimal b = new BigDecimal(String.valueOf(amount));
        double amount1 = b.doubleValue();
        amount1 = amount1 * 10;
        ViewUtil.setText(tvAmountMember, (int)amount1+"("+amount1/10+"元 )");
    }
    public void setBuyTip(String tips) {
        ViewUtil.setText(tvBuy, tips);
    }

    public void setHead(String picUrl) {
        ImageLoaderUtils.displayTager(mContentView.getContext(), ivHead, picUrl, R.mipmap.ic_loading_rectangle);
    }

    @Override
    protected void initViews(View contentView) {
        animView = ViewUtil.getView(mContentView, R.id.contentView);
        ivHead = ViewUtil.getView(mContentView, R.id.iv_popuw_buy_album_pic);
        tvBuy = ViewUtil.getView(mContentView, R.id.iv_popuw_buy_album_buy);
        tvTitle = ViewUtil.getView(mContentView, R.id.tv_popuw_buy_album_title);
        tvBalance = ViewUtil.getView(mContentView, R.id.tv_popuw_buy_album_balance);
        tvBalanceTips = ViewUtil.getView(mContentView, R.id.tv_popuw_buy_album_tips);
        tvAmount = ViewUtil.getView(mContentView, R.id.tv_popuw_buy_album_amount);
        tvMemberPrice=ViewUtil.getView(mContentView, R.id.tv_member_price);
        tvAmountMember=ViewUtil.getView(mContentView, R.id.tv_popuw_buy_album_amount_member);
        touchDismiss( R.id.touch_outside);
        tvBuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_popuw_buy_album_buy:
                if (mBuyAlbumClickListener != null) {
                    mBuyAlbumClickListener.buyAlbum(needRecharge);
                }
                dismiss();
                break;
        }
    }

    private BuyAlbumClickListener mBuyAlbumClickListener;

    public void setBuyAlbumClickListener(BuyAlbumClickListener listener) {
        mBuyAlbumClickListener = listener;
    }

    public  interface BuyAlbumClickListener {

        void buyAlbum(boolean needRecharge);
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

    @Override
    protected void showAnimation() {
        AnimationUtil.startAnimationVer(true, animView, false, 600 ,null);
    }
}