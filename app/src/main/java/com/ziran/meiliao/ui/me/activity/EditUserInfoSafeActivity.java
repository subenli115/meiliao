package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.LoginApi;
import com.umeng.soexample.UserInfo;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.envet.ThreeLoginCallBack;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.util.ConfigUtils;
import com.ziran.meiliao.ui.settings.activity.BindPhoneActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.ItemGroupView;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_SOCIAL_BIND;

public class EditUserInfoSafeActivity extends BaseActivity {
    private static final int REQUEST_CODE_C = 3;
    private static final int REQUEST_CODE_A = 2;
    @Bind(R.id.tv_me_main_new_realname)
    ItemGroupView tv_me_main_new_realname;
    @Bind(R.id.tv_me_main_new_phone)
    ItemGroupView tv_me_main_new_phone;
    @Bind(R.id.tv_me_main_new_pwd)
    ItemGroupView tv_me_main_new_pwd;
    @Bind(R.id.tv_me_main_new_wechat)
    ItemGroupView tv_me_main_new_wechat;
    @Bind(R.id.tv_me_main_new_qq)
    ItemGroupView tv_me_main_new_qq;
    public final int REQUEST_CODE_B = 1;
    private UserBean.DataBean bean;
    private String shareString;
    private UserInfo userInfo;

    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, EditUserInfoSafeActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_userinfo_safe;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected void initBundle(Bundle extras) {
    }

    @Override
    public void initView() {
        update();
    }

    //点击监听
    @OnClick({R.id.tv_me_main_new_realname, R.id.tv_me_main_new_phone, R.id.tv_me_main_new_pwd, R.id.tv_me_main_new_wechat, R.id
            .tv_me_main_new_qq})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        if (Utils.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_me_main_new_realname:
                SetRealNameActivity.startAction("real", REQUEST_CODE_B);
                break;
            case R.id.tv_me_main_new_phone:
                SetPhoneActivity.startAction("phone", REQUEST_CODE_A);
                break;
            case R.id.tv_me_main_new_pwd:
                SetPwdActivity.startAction(bean.getPassword(),REQUEST_CODE_C);
                break;
            case R.id.tv_me_main_new_wechat:
                if(bean.getWxOpenid()==null){
                    shareString = "WX";
                    LoginApi.get().login(this, SHARE_MEDIA.WEIXIN,threeLoginCallBack);
                }
                break;
            case R.id.tv_me_main_new_qq:
                if(bean.getQqOpenid()==null){
                    shareString = "QQ";
                    LoginApi.get().login(this, SHARE_MEDIA.QQ,threeLoginCallBack);
                }
                break;
        }
    }

    private void wxBind(ItemGroupView tv) {
        tv.setRigthText("已绑定");
        tv.setRigthColor(Color.parseColor("#459BFF"));
        tv.getRightTextView().setEnabled(false);
    }

    public ThreeLoginCallBack threeLoginCallBack = new ThreeLoginCallBack() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            super.onComplete(share_media, i, map);
            userInfo = UserInfo.parse(map, share_media.name());
            Map<String, String> defMap = MapUtils.getDefMap(true);
            defMap.put("code",userInfo.getUnionid());
            defMap.put("state",shareString);
            OkHttpClientManager.postAsyncAddHead(ApiKey.ADMIN_SOCIAL_BIND,defMap, "", new
                    NewRequestCallBack<StringDataBean>(StringDataBean.class) {

                        @Override
                        public void onSuccess(StringDataBean bean) {
                            if(shareString.equals("WX")){
                                wxBind(tv_me_main_new_wechat);
                                MyAPP.getmUserBean().setWxOpenid(userInfo.getUnionid());
                            }else {
                                wxBind(tv_me_main_new_qq);
                                MyAPP.getmUserBean().setQqOpenid(userInfo.getUnionid());
                            }
                            ToastUitl.showShort("绑定成功");
                        }

                        @Override
                        public void onError(String msg, int code) {
                            ToastUitl.showShort("绑定失败");
                        }
                    });
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_B: //返回的结果是来自于Activity B
                if (resultCode == Activity.RESULT_OK) {
                    tv_me_main_new_realname.setRigthText("已认证");
                    tv_me_main_new_realname.setRigthColor(Color.parseColor("#459BFF"));
                }
                break;
            case REQUEST_CODE_A:
                if (data != null) {
                    String newPhone = data.getStringExtra("newPhone");
                    setPhone(newPhone);
                }
                break;
            case REQUEST_CODE_C:
                tv_me_main_new_pwd.setRigthText(" ");
                break;
            default:
                break;

        }
    }

    public void setPhone(String phonenum) {
        phonenum = phonenum.substring(0, 3) + "****" + phonenum.substring(7, 11);
        tv_me_main_new_phone.setRigthText(phonenum);
    }

    private void update() {
         bean = MyAPP.getmUserBean();
        if (bean != null) {
            if (bean.getPassword() == null || bean.getPassword().equals("")) {
                tv_me_main_new_pwd.setRigthText("未设置密码");
            }
            if (bean.getIdCard() == null || bean.getIdCard().equals("")) {
                tv_me_main_new_realname.setRigthText("未认证");
            } else {
                tv_me_main_new_realname.setRigthText("已认证");
                tv_me_main_new_realname.setRigthColor(Color.parseColor("#459BFF"));
            }
            if (bean.getPhone() != null) {
                setPhone(bean.getPhone());
            }
            tv_me_main_new_wechat.setRigthText("去绑定");
            tv_me_main_new_qq.setRigthText("去绑定");

            if(bean.getWxOpenid()!=null){
                wxBind(tv_me_main_new_wechat);
            }

            if(bean.getQqOpenid()!=null){
                wxBind(tv_me_main_new_qq);
            }
        }
    }

}