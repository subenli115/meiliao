package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonwidget.RoundImageView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.SJKLiveOverBean;
import com.ziran.meiliao.utils.MapUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 *  手机开播结束界面
 */

public class MobileLiveOverActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements  CommonContract
        .View<SJKLiveOverBean>  {


    @Bind(R.id.iv_sjk_fulllive_over_picture)
    RoundImageView ivSjkMobileLiveOverPicture;
    @Bind(R.id.tv_sjk_fulllive_over_look)
    TextView tvSjkFullliveOverLook;
    @Bind(R.id.tv_sjk_fulllive_over_like)
    TextView tvSjkFullliveOverLike;
    @Bind(R.id.tv_sjk_fulllive_over_time)
    TextView tvSjkFullliveOverTime;

    public static void startAction(Context context, String courseId) {
        Intent intent = new Intent(context, MobileLiveOverActivity.class);
        intent.putExtra(AppConstant.SPKey.COURSE_ID, courseId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sjk_full_live_over;
    }


    @Override
    public void initView() {
        super.initView();
        String courseId = getIntentExtra(getIntent(), AppConstant.SPKey.COURSE_ID);
        mPresenter.getData(ApiKey.TEATHER_END, MapUtils.getOnlyCan("courseId", courseId), SJKLiveOverBean.class);
    }

    @Override
    public void returnData(SJKLiveOverBean result) {
        try {
            SJKLiveOverBean.DataBean data = result.getData();
            if (EmptyUtils.isEmpty(data)) return;
            tvSjkFullliveOverLook.setText(String.valueOf(data.getWatchCount()));
            tvSjkFullliveOverLike.setText(String.valueOf(data.getLikeCount()));
            tvSjkFullliveOverTime.setText(data.getTotal());
            ImageLoaderUtils.display(this, ivSjkMobileLiveOverPicture, data.getPicture(), R.mipmap.ic_loading_square);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClick(R.id.iv_sjk_fulllive_over_close)
    public void onViewClicked() {
        finish();
    }
}
