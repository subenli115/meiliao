package com.ziran.meiliao.ui.main.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.settings.activity.IntputCodeActivity;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_USER_GETFROZEN;


/**
 * des: 登录控制器
 * Created by xsf
 * on 2016.09.12:01
 */
public class LoginPresenter extends LoginContract.Presenter {

    private View contentView;

    @Override
    public void postLoginRequest(Map<String, String> map) {

        mModel.getLoginData(map, new NewRequestCallBack<LoginBean>(LoginBean.class,mView) {
            @Override
            public void onSuccess(LoginBean result) {

                mView.stopLoading();
                mView.returnLoginData(result);
            }

            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort("验证码有误");
                mView.stopLoading();
                OneKeyLoginManager.getInstance().setLoadingVisibility(false);
            }
        });
    }

    @Override
    public void postBindPhoneRequest(Map<String, String> map) {

        mModel.getBindPhoneData(map, new NewRequestCallBack<LoginBean>(LoginBean.class) {

            @Override
            public void onSuccess(LoginBean d) {
                if (d==null) return;
                mView.returnBindPhoneData(d);
            }
        });
    }

    @Override
    public void postLoginCode(Map<String, String> map) {
        mModel.getLoginCode(map, new NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
            @Override
            public void onSuccess(StringDataV2Bean result) {
                mView.returnLoginCode(result);
            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }

    @Override
    public void postBindCode(Map<String, String> map) {
        mModel.getBindCode(map, new NewRequestCallBack<LoginBean>(LoginBean.class) {

            @Override
            public void onSuccess(LoginBean result) {
                mView.returnBindCode(result);
            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }

    @Override
    public void postPwdLogin(Map<String, String> map) {
        mModel.postPwdLogin(map, new NewRequestCallBack<LoginBean>(LoginBean.class) {

            @Override
            public void onSuccess(LoginBean result) {
                mView.showPwdLogin(result);
            }

            @Override
            public void onError(String msg, int code) {
                    ToastUitl.showShort(msg);
            }
        });
    }
    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];

        contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_login_result, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
//        popupWindow.showAtLocation(llBg, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
        TextView qx = contentView.findViewById(R.id.tv_qx);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAPP.logout(mContext);
                popupWindow.dismiss();

            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });

    }

    @Override
    public void getUserInfo(String map,String token) {

        mModel.getUserInfo(map, token,new NewRequestCallBack<UserBean>(UserBean.class) {

            @Override
            protected void onSuccess(UserBean result) {
                mView.showUserInfo(result);

            }

            @Override
            public void onError(String msg, int code) {
             if(code==500){
                    ToastUitl.showShort(msg);
                }

            }
        });
    }


    @Override
    public void postCheckLoginPhone(Map<String, String> map) {
        mModel.getCheckLoginPhone(map, new NewRequestCallBack<CheckPhoneBean>(CheckPhoneBean.class) {

            @Override
            protected void onSuccess(CheckPhoneBean result) {
                mView.showLoginCheck(result);
            }

            @Override
            public void onError(String msg, int code) {
                OneKeyLoginManager.getInstance().setLoadingVisibility(false);
                ToastUitl.showShort(msg);
            }
        });
    }
    @Override
    public void postPartyLoginData(Map<String, String> map) {

        mModel.getPartyLoginData(map, new NewRequestCallBack<LoginBean>(LoginBean.class) {
            @Override
            public void onSuccess(LoginBean result) {
                mView.returnPartyLogin(result);
            }
            @Override
            public void onError(String msg, int code) {
                    if(code==1002){

                    }
            }
        });
    }


    @Override
    public void postTagSaveCheck(Map<String, String> map) {

        mModel.getTagSaveCheck(map, new NewRequestCallBack<TagCheckBean>(TagCheckBean.class) {

            @Override
            protected void onSuccess(TagCheckBean result) {
                mView.showCheckSaveData(result);
            }

            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });
    }
}
