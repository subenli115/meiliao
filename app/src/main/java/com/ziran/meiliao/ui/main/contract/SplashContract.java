package com.ziran.meiliao.ui.main.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.AdvertBean;
import com.ziran.meiliao.ui.bean.VersionBean;
import com.ziran.meiliao.ui.bean.VersionNewBean;

import java.util.Map;

/**
 * 忘记密码/修改密码契约类
 */
public interface SplashContract {
    interface View extends BaseView {
        //返回修改密码请求结果
        void showVersionResult(VersionNewBean result);

        //返回获取验证码结果
        void showCheckTokenResult(boolean result);

        void showAdvertResultError();
        void showAdvertResult(AdvertBean.DataBean result);
    }

    abstract class Presenter extends BasePresenter<View, CommonModel> {

        public abstract void checkToken(Map<String, String> params);

        public abstract void getVersion(Map<String, String> params);

        public abstract void getAdvert(Map<String, String> params);
    }
}
