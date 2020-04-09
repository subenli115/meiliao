package com.ziran.meiliao.ui.settings.presenter;

import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.GainCouponBean;
import com.ziran.meiliao.ui.bean.ShareCouponBean;
import com.ziran.meiliao.ui.settings.contract.ShareCouponContract;

import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class ShareCouponPresenter extends ShareCouponContract.Presenter {


    @Override
    public void postContentAndFiles(Map<String, String> map, List<File> files) {
        mModel.postContentAndFiles(ApiKey.UPLOAD_POSTER, files, map, new NewRequestCallBack<ShareCouponBean>(ShareCouponBean.class) {
            @Override
            public void onSuccess(ShareCouponBean result) {
                mView.showUploadFile(result);
            }
        });
    }

    @Override
    public void getGoinCoupon(Map<String, String> map) {
        mModel.getGoinCoupon(ApiKey.GAIN_COUPON, map, new NewRequestCallBack<GainCouponBean>(GainCouponBean.class) {
            @Override
            public void onSuccess(GainCouponBean result) {
                mView.showGoinCoupon(result);
            }
        });
    }
}
