package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonWebActivity;
import com.ziran.meiliao.ui.bean.TrailerBean;
import com.ziran.meiliao.ui.priavteclasses.util.TrailerCollectConfig;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

/**
 * 预告H5页面
 * Created by Administrator on 2017/3/2.
 */

public class TrailerWebActivity extends CommonWebActivity {


    public static void startAction(Context context, final TrailerBean.DataBean dataBean, String url) {
        if (EmptyUtils.isEmpty(url)) {
            ToastUitl.showShort("当前服务器繁忙,请稍后重试");
            return;
        }
        TrailerCollectConfig.addConfig(url,dataBean);

        Intent intent = new Intent(context, TrailerWebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.SPKey.EXTRAS_URL, url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private TrailerBean.DataBean dataBean;

    @Override
    protected void initBundle(Bundle extras) {

        dataBean =TrailerCollectConfig.getConfig(url);
        if (dataBean != null)
            setCollect(ivCollect, dataBean.isCollect());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }


    @Override
    public void initView() {
        super.initView();
        tvHeadviewTitle.setText(R.string.trailer_detail);
    }

    @Override
    protected void setShow(boolean show) {
        PopupWindowUtil.show(mSharePopupWindow);
    }

    @Override
    protected void doCollcet() {
        if (dataBean==null) return;
        OkHttpClientManager.postAsync(ApiKey.COLLECT_COURSE, MapUtils.getCollect(dataBean.isCollect(), "courseIds",
                dataBean.getId()), new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                ToastUitl.showShort(result.getResultMsg());
                dataBean.setCollect(!dataBean.isCollect());
                setCollect(ivCollect, dataBean.isCollect());
                RxManagerUtil.post(AppConstant.RXTag.TRAILER_WEB_ACTIVITY_DATA, dataBean.isCollect());
            }
        });
    }

    @Override
    protected void share() {
        if (dataBean==null) return;
        SharePopupWindow.showPopup(this,mSharePopupWindow,dataBean.getTitle(),dataBean.getDescript(),url,dataBean.getPicture());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
