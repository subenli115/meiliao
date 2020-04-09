package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.GetTicketBean;
import com.ziran.meiliao.ui.priavteclasses.contract.GetVideoCouponContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.GetVideoCouponPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.ShareVideoCouponPopupWindow;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 私家课-活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class GetVideoCouponFragment extends CommonHttpFragment<GetVideoCouponPresenter, CommonModel> implements GetVideoCouponContract
        .View {


    @Bind(R.id.iv_video_coupon_item_bg)
    ImageView ivVideoCouponBg;
    @Bind(R.id.tv_video_coupon_tips)
    TextView tvVideoCouponTips;

    public GetVideoCouponFragment() {

    }

    @Override
    protected void initOther() {
        String courseId = getIntentExtra(AppConstant.SPKey.COURSE_ID);
        mPresenter.getVideoCouponData(ApiKey.COURSE_TICKET_GET_TICKET, MapUtils.getCourseMap(courseId));
        ShareUtil.addCallBack(mUMShareListener);
    }

    private UMShareListener mUMShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            ToastUitl.showShort("分享成功");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ShareUtil.removeCallBack(mUMShareListener);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_get_video_coupon;
    }


    @OnClick(R.id.iv_video_coupon_item_yaoqing)
    public void onClick(View view) {
        onShare();
    }

    public void onShare() {
        ShareVideoCouponPopupWindow shareVideoCouponPopupwindow = new ShareVideoCouponPopupWindow(getContext());
        shareVideoCouponPopupwindow.setShareDescript(mDataBean.getShareDescript());
        shareVideoCouponPopupwindow.setShareTitle(mDataBean.getShareTitle());
        shareVideoCouponPopupwindow.setShareUrl(mDataBean.getShareUrl());
        PopupWindowUtil.show(getActivity(), shareVideoCouponPopupwindow);
    }

    private GetTicketBean.DataBean mDataBean;

    @Override
    public void getVideoCouponData(GetTicketBean.DataBean result) {
        mDataBean = result;
        setIvGetVideoCouponLantern(5, result.getValidTimes());
        ImageLoaderUtils.display(getContext(), ivVideoCouponBg, result.getPic());
    }

    private void setIvGetVideoCouponLantern(int total, int validTimes) {
        tvVideoCouponTips.setText(StringUtils.format("成功邀请%d位好友免费获得观看券（已成功邀请%d位）", total, validTimes));
    }

}
