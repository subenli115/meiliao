package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.RoundImageView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.utils.StringUtils;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     未购买专辑或视频的提示控件
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class ARewardV1View extends RelativeLayout {
    private View rootView;
    //显示价格
    private int videoCouponCount = 0;
    private TextView tvArewardTips;
    private TextView tvArewardBuy;
    private RoundImageView ivAvd;
    private View flAvd;
    private TextView tvArewardShareMember;

    public ARewardV1View(Context context) {
        this(context, null);
    }

    public ARewardV1View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ARewardV1View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private   View onShareView;
    //初始化控件
    private void initView() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.custom_bottom_areward_v1, this, true);
        setVisibility(GONE);
        tvArewardTips = ViewUtil.getView(rootView, R.id.tv_custom_arewardv1_tips);
        tvArewardBuy = ViewUtil.getView(rootView, R.id.tv_album_buy);
        tvArewardShareMember = ViewUtil.getView(rootView, R.id.tv_custom_share_member);
         onShareView = ViewUtil.getView(rootView, R.id.fl_custom_arewardv1_tips);
        ivAvd = ViewUtil.getView(rootView, R.id.iv_avd);
        flAvd = ViewUtil.getView(rootView, R.id.fl_avd);
    }

    public void setParmes(int count, int shareMember) {
        if (count==0){
            onShareView.setVisibility(GONE);
        }else{
            onShareView.setVisibility(VISIBLE);
        }
        this.videoCouponCount = count;
        ViewUtil.setText(tvArewardTips, StringUtils.format("分享%d人下载App获绿色通道", count));
        tvArewardShareMember.setVisibility(VISIBLE);
        ViewUtil.setText(tvArewardShareMember, StringUtils.format("已成功分享%d人", shareMember));
    }

    public void setAvd(String url){
        if (EmptyUtils.isNotEmpty(url)){
            ImageLoaderUtils.displayRatioTarger(getContext(),ivAvd,url,R.mipmap.ic_loading_square_big);
            flAvd.setVisibility(VISIBLE);
            onShareView.setVisibility(GONE);
        }
    }
    public void setPrice(String text){
        ViewUtil.setText(tvArewardBuy,text);
    }


    public void setParames(int count,String tag, int shareMember, int amount) {
        if (count==0){
            onShareView.setVisibility(GONE);
        }else{
            onShareView.setVisibility(VISIBLE);
        }
        this.videoCouponCount = count;
        ViewUtil.setText(tvArewardTips, StringUtils.format("分享%d人下载App获绿色通道", count));
        tvArewardShareMember.setVisibility(VISIBLE);
        ViewUtil.setText(tvArewardShareMember, StringUtils.format("已成功分享%d人", shareMember));
        ViewUtil.setText(tvArewardBuy, StringUtils.format("%s（¥%d）",tag, amount));
    }


    public void setOnClick(OnClickListener onClick) {
        if (onClick != null) {
            ViewUtil.getView(rootView, R.id.tv_album_buy).setOnClickListener(onClick);
        }
    }

    public void setOnShareClick(OnClickListener onClick) {
        if (onClick != null && onShareView !=null) {
            onShareView.setOnClickListener(onClick);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
