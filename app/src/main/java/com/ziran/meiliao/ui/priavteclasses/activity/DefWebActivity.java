package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonWebActivity;
import com.ziran.meiliao.ui.bean.ShareBean;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * 预告H5页面
 * Created by Administrator on 2017/3/2.
 */

public class DefWebActivity extends CommonWebActivity {


    private String zhibo;
    private View contentViewPop;
    private View viewById;

    public static void startAction(Context context, String url, String title) {
        if (EmptyUtils.isEmpty(url)) {
            ToastUitl.showShort("当前服务器繁忙,请稍后重试");
            return;
        }
        Intent intent = new Intent(context, DefWebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.SPKey.EXTRAS_URL, url);
        bundle.putString(AppConstant.ExtraKey.EXTRAS_TITLE, title);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startAction(Context context, String url, String title,ShareBean shareBean,String zhibourl) {
        if (EmptyUtils.isEmpty(url)) {
            ToastUitl.showShort("当前服务器繁忙,请稍后重试");
            return;
        }
        Intent intent = new Intent(context, DefWebActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString(AppConstant.SPKey.EXTRAS_URL, url);
        bundle.putString(AppConstant.ExtraKey.EXTRAS_TITLE, title);
        bundle.putParcelable(AppConstant.ExtraKey.EXTRAS_DATA, shareBean);
        bundle.putString(AppConstant.ExtraKey.EXTRAS_ZHIBO, zhibourl);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public static void startAction(Context context, String url, String title,ShareBean shareBean) {
        if (EmptyUtils.isEmpty(url)) {
            ToastUitl.showShort("当前服务器繁忙,请稍后重试");
            return;
        }
        Intent intent = new Intent(context, DefWebActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString(AppConstant.SPKey.EXTRAS_URL, url);
        bundle.putString(AppConstant.ExtraKey.EXTRAS_TITLE, title);
        bundle.putParcelable(AppConstant.ExtraKey.EXTRAS_DATA, shareBean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public static void startAction(Context context, ShareBean shareBean) {
        if (EmptyUtils.isEmpty(shareBean) || EmptyUtils.isEmpty(shareBean.getShareUrl())) {
            ToastUitl.showShort("当前服务器繁忙,请稍后重试");
            return;
        }
        Intent intent = new Intent(context, DefWebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.SPKey.EXTRAS_URL, shareBean.getShareUrl());
        bundle.putString(AppConstant.ExtraKey.EXTRAS_TITLE, shareBean.getShareTitle());
        bundle.putParcelable(AppConstant.ExtraKey.EXTRAS_DATA, shareBean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private String title;
    private ShareBean mShareBean;

    @Override
    protected void initBundle(Bundle extras) {
        title = extras.getString(AppConstant.ExtraKey.EXTRAS_TITLE);
         zhibo = extras.getString(AppConstant.ExtraKey.EXTRAS_ZHIBO);
        if (extras.containsKey(AppConstant.ExtraKey.EXTRAS_DATA)) {
            mShareBean = extras.getParcelable(AppConstant.ExtraKey.EXTRAS_DATA);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }


    @Override
    public void initView() {
        super.initView();
        ViewUtil.setText(tvHeadviewTitle, title);
        ivCollect.setVisibility(View.GONE);
        if (EmptyUtils.isNotEmpty(mShareBean)) {
            ivShare.setVisibility(View.VISIBLE);
        } else {
            ivShare.setVisibility(View.GONE);
        }
        ShareUtil.addCallBack(mUMShareListener);
    }

    @Override
    protected void setShow(boolean show) {

    }

    @Override
    protected void doCollcet() {

    }

    @Override
    protected void share() {
//        SharePopupWindow.showPopup(this, mSharePopupWindow, mShareBean);mShareBean

        SharePopupWindow.showPopup(this, mSharePopupWindow, mShareBean,zhibo);
    }
    private void showPopWindow1() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentViewPop = LayoutInflater.from(mContext).inflate(R.layout.popupw_into_zhibo, null);
        contentViewPop.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentViewPop,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 330);
         viewById = contentViewPop.findViewById(R.id.tv_popuw_has_tick_used);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DefWebActivity.startAction(mContext,zhibo,"");
                        popupWindow.dismiss();
            }
        });

    }


    private UMShareListener mUMShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            if(zhibo!=null&&zhibo.length()>0){
                showPopWindow1();
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
}
