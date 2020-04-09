package com.ziran.meiliao.ui.main.presenter;


import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.CheckVipLevelBean;
import com.ziran.meiliao.ui.bean.GainSpreadBean;
import com.ziran.meiliao.ui.bean.ResBean;
import com.ziran.meiliao.ui.bean.TrailerBean;
import com.ziran.meiliao.ui.bean.UserLevelBean;
import com.ziran.meiliao.ui.main.contract.MainMeContract;

import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class MainMePresenter extends MainMeContract.Presenter {


    @Override
    public void downloadHeadImage(String url, String fileName) {
        mModel.downloadHeadImage(url, fileName, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }

    @Override
    public void postRecordToken(Map<String, String> map) {  //ApiKey.USER_RECORD_TOKEN
        mModel.postRecordToken(ApiKey.USER_RECORD_TOKEN, map, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }

    @Override
    public void postCheckLevel(Map<String, String> map) {   //ApiKey.VIP_CHECK_LEVEL
        mModel.postCheckLevel(ApiKey.VIP_CHECK_LEVEL, map, new NewRequestCallBack<CheckVipLevelBean>(CheckVipLevelBean.class) {
            @Override
            public void onSuccess(CheckVipLevelBean result) {
                mView.showCheckLevel(result);
            }
        });
    }

    @Override
    public void getUserHomeRes(Map<String, String> map) { //ApiKey.USER_HOME_RES
        mModel.getUserHomeRes(ApiKey.USER_HOME_RES, map, new NewRequestCallBack<ResBean>(ResBean.class) {
            @Override
            public void onSuccess(ResBean result) {
                mView.showUserHomeRes(result);
            }
        });
    }

    @Override
    public void getTrialer(Map<String, String> map) {
        mModel.getUpdateTrialer(ApiKey.USER_HOME_TRAILER, map, new NewRequestCallBack<TrailerBean>(TrailerBean.class) {
            @Override
            public void onSuccess(TrailerBean result) {
                mView.showUpdateTrialer(result);
            }
        });
    }

    @Override
    public void getGainSpread(Map<String, String> map) {
        mModel.getGainSpread(ApiKey.ALBUM_GAIN_SPREAD, map, new NewRequestCallBack<GainSpreadBean>(GainSpreadBean.class) {
            @Override
            public void onSuccess(GainSpreadBean result) {
                mView.gainSpread(result.getData());
            }
        });
    }

    @Override
    public void giveAlbum(Map<String, String> map) {
        mModel.postGiveAlbum(ApiKey.ALBUM_GIVE_ALBUM, map, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }

    @Override
    public void getResUrl(Map<String, String> map) {
        OkHttpClientManager.getAsync(ApiKey.USER_HOME_REC_URL,map , new NewRequestCallBack<ResBean>(ResBean.class) {
            @Override
            protected void onSuccess(ResBean result) {
                mView.showResUrl(result.getData());
            }
        });
    }

    @Override
    public void getMemberLevel(Map<String, String> map) {
        mModel.postGetLevel(ApiKey.USER_MEMBER_LEVEL,map , new NewRequestCallBack<UserLevelBean>(UserLevelBean.class) {
            @Override
            protected void onSuccess(UserLevelBean result) {
                mView.showUserLevel(result);
            }
        });
    }
}
