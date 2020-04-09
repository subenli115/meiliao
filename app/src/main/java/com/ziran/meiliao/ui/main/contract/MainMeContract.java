package com.ziran.meiliao.ui.main.contract;

import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.bean.CheckVipLevelBean;
import com.ziran.meiliao.ui.bean.GainSpreadBean;
import com.ziran.meiliao.ui.bean.ResBean;
import com.ziran.meiliao.ui.bean.TrailerBean;
import com.ziran.meiliao.ui.bean.UserLevelBean;

import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface MainMeContract {
    interface Model extends BaseModel {
        //执行下载用户头像
        void downloadHeadImage(String url, String fileName, final OkHttpClientManager.ResultCallback callback);
        //执行上传融云Token
        void postRecordToken(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        //执行检查是否是VIP操作
        void postCheckLevel(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        //执行获取用户其他信息
        void getUserHomeRes(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        //执行获取更新消息通知的请求
        void getUpdateTrialer(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        //获取推广音频信息
        void getGainSpread(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        //执行赠送专辑请求
        void postGiveAlbum(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);
        //执行获取用户会员信息
        void postGetLevel(String url,Map<String, String> params, OkHttpClientManager.ResultCallback callback);
    }

    interface View   extends BaseView {
        void showUploadHeadImg(Result result);
        void showUpdateTrialer(TrailerBean result);
        void showCheckLevel(CheckVipLevelBean result);
        void showUserHomeRes(ResBean result);
        void gainSpread(GainSpreadBean.DataBean result);
        void showResUrl(ResBean.DataBean result);
        void showUserLevel(UserLevelBean result);
    }

    abstract  class Presenter extends BasePresenter<View, Model> {
        public abstract void downloadHeadImage(String url, String fileName);
        public abstract void postRecordToken(Map<String,String> map);
        public abstract void postCheckLevel(Map<String,String> map);
        public abstract void getUserHomeRes(Map<String,String> map);
        public abstract void getTrialer(Map<String,String> map);
        public abstract void getGainSpread(Map<String,String> map);
        public abstract void giveAlbum(Map<String,String> map);
        public abstract void getResUrl(Map<String,String> map);
        public abstract void getMemberLevel(Map<String,String> map);
    }
}
