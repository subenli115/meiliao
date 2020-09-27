package com.ziran.meiliao.ui.main.activity;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;

import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.LoginApi;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.envet.ThreeLoginCallBack;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.GetPhoneInfoListener;
import com.chuanglan.shanyan_sdk.listener.OneKeyLoginListener;
import com.chuanglan.shanyan_sdk.listener.OpenLoginAuthListener;
import com.google.gson.Gson;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.permission.PermissionUtil;
import com.ziran.meiliao.common.permission.rom.PermisionUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.ui.base.PermissionActivity;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.TokenBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.ui.main.util.ConfigUtils;
import com.ziran.meiliao.ui.settings.activity.BindPhoneActivity;
import com.ziran.meiliao.ui.settings.activity.InputUserInfo2Activity;
import com.ziran.meiliao.ui.settings.activity.InputUserInfoActivity;
import com.ziran.meiliao.ui.settings.activity.IntputCodeActivity;
import com.ziran.meiliao.utils.ExitUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;


/**
 * des:启动页
 * Created by xsf
 * on 2016.09.15:16
 */
public class SplashActivity extends PermissionActivity<LoginPresenter, LoginModel> implements LoginContract.View  {

    private Intent intent;
    private SplashActivity activity;
    private int MY_READ_PHONE_STATE = 0;
    private TokenBean bean;
    private com.umeng.soexample.UserInfo userInfo;

    @Override
    public int getLayoutId() {
        return R.layout.act_splash;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }



    @Override
    public void initView() {
        activity = this;
        intent = new Intent();
        String token = SPUtils.getString(AppConstant.SPKey.TOKEN);
        String userId = SPUtils.getString(AppConstant.SPKey.USERID);
        UserInfo myInfo = JMessageClient.getMyInfo();
        if (!EmptyUtils.isEmpty(token)&&!EmptyUtils.isEmpty(userId)&&myInfo!=null) {
            MyAPP.setUserId(userId);
            MyAPP.setAccessToken(token);
            mPresenter.getUserInfo(userId,token);
            return;
        }else {
            requestPermission(Manifest.permission.READ_PHONE_STATE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            View decorView = window.getDecorView();
            decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
                @Override
                public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                    WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                    return defaultInsets.replaceSystemWindowInsets(
                            defaultInsets.getSystemWindowInsetLeft(),
                            0,
                            defaultInsets.getSystemWindowInsetRight(),
                            defaultInsets.getSystemWindowInsetBottom());
                }
            });
            ViewCompat.requestApplyInsets(decorView);
            //将状态栏设成透明，如不想透明可设置其他颜色
            window.setStatusBarColor(ContextCompat.getColor(mContext, android.R.color.white));
        }

    }

    public void getUserInfo(String userId,String token){

        mPresenter.getUserInfo(userId,token);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    private void requestPermission(String permission) {
        if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.CAMERA,
            }, MY_READ_PHONE_STATE);
        } else {
            //闪验SDK预取号（可缩短拉起授权页时间）
            OneKeyLoginManager.getInstance().getPhoneInfo(new GetPhoneInfoListener() {
                @Override
                public void getPhoneInfoStatus(int code, String result) {
                    //网络状态发生变化时执行,用于没有加载预告数据时使用
                    OneKeyLoginManager.getInstance().setAuthThemeConfig(ConfigUtils.getCJSConfig(getApplicationContext(),activity));
                    openLoginActivity();
                }
            });
        }
    }

    public void loginQQ(){
//        UploadUserPhotoAcitivty.startAction(mContext,new UserRegBean());
        LoginApi.get().login(activity, SHARE_MEDIA.WEIXIN,threeLoginCallBack);
    }
    private void openLoginActivity() {
        //拉授权页方法
        intent = new Intent();
        OneKeyLoginManager.getInstance().openLoginAuth(false, new OpenLoginAuthListener() {
            @Override
            public void getOpenLoginAuthStatus(int code, String result) {
                if (1000 == code) {
                    //拉起授权页成功
                        intent.setClass(mContext, PrivatePopActivity.class);
                        startActivity(intent);
                }else if(1022==code){

                }else {

                    //拉起授权页失败
                    intent.putExtra("Connected", "false");
                    intent.setClass(mContext, IntputCodeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, new OneKeyLoginListener() {
            @Override
            public void getOneKeyLoginStatus(int code, String result) {
                if (1011 == code) {
                 openLoginActivity();
                    return;
                } else if (1000 == code) {
                    Map<String, String> defMap = MapUtils.getDefMap(false);
                    Gson gson = new Gson();
                    bean = gson.fromJson(result, TokenBean.class);
                    defMap.put("token", bean.getToken());
                    mPresenter.postCheckLoginPhone(defMap);

                } else {
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //无论权限是否申请成功，均调用闪验预取号方法
        if (requestCode == MY_READ_PHONE_STATE&& hasAllPermissionGranted(grantResults)) {

            if(grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //闪验SDK预取号（可缩短拉起授权页时间）
                OneKeyLoginManager.getInstance().getPhoneInfo(new GetPhoneInfoListener() {
                    @Override
                    public void getPhoneInfoStatus(int code, String result) {
                        //预取号回调
                        OneKeyLoginManager.getInstance().setAuthThemeConfig(ConfigUtils.getCJSConfig(getApplicationContext(),activity));
                        openLoginActivity();
                    }
                });
            }
        }
    }

    //获取全部权限
    public boolean hasAllPermissionGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        PermisionUtils.verifyStoragePermissions(this);
        setPermission(PermissionUtil.getAllPermission());
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ExitUtil.exit(mContext);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void returnLoginData(LoginBean bean) {
        //讲用户phone和token保存到偏好设置
        SPUtils.setString(AppConstant.SPKey.PHONE, bean.getUsername());
        FileUtil.createFileFolder(MeiliaoConfig.setPhone(bean.getUsername()), mContext);
        loginSuccess(bean);
    }

    //登录成功跳转页面
    private void loginSuccess(LoginBean loginBean) {
        //保存token
        MyAPP.saveUserLoginData(loginBean);
        if (!MyAPP.isLogout()) {
            mRxManager.post(AppConstant.RXTag.UPDATE_USER, "login");
        }
        mPresenter.getUserInfo(MyAPP.getUserId(),loginBean.getAccess_token());
    }

    @Override
    public void returnBindPhoneData(LoginBean registerBean) {

    }

    @Override
    public void returnLoginCode(StringDataV2Bean registerBean) {

    }

    @Override
    public void returnBindCode(LoginBean loginBean) {
        MyAPP.saveUserLoginData(loginBean);
        mPresenter.getUserInfo(loginBean.getId(),loginBean.getAccess_token());
    }


    @Override
    public void returnPartyLogin(LoginBean registerBean) {

    }

    @Override
    public void showPwdLogin(LoginBean result) {

    }

    @Override
    public void showUserInfo(UserBean result) {
        UserBean.DataBean data = result.getData();
        if(data!=null){
            MyAPP.setmUserBean(data);
            if(data.getNickname()!=null&&data.getNickname().length()>0){
                MyAPP.imLogin(mContext);
            }else {
                OneKeyLoginManager.getInstance().setLoadingVisibility(false);
                InputUserInfoActivity.startAction(mContext);
            }
        }else {
            //拉起授权页失败
            intent.putExtra("Connected", "false");
            intent.setClass(mContext, IntputCodeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void showLoginCheck(CheckPhoneBean result) {
        login(result);
    }

    @Override
    public void showCheckSaveData(TagCheckBean data) {

    }

    private void login(CheckPhoneBean result) {
        Map<String, String> loginMap = MapUtils.getLoginMap(result.getData().getMobile(), result.getData().getMobileCode(), null);
        mPresenter.postLoginRequest(loginMap);
    }


    public ThreeLoginCallBack threeLoginCallBack = new ThreeLoginCallBack() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            super.onComplete(share_media, i, map);
            userInfo = com.umeng.soexample.UserInfo.parse(map, share_media.name());
            OkHttpClientManager.getAsyncOne(ApiKey.ADMIN_SOCIAL_SOCIAL, ConfigUtils.shareString + "@" +userInfo.getUnionid(), new
                    NewRequestCallBack<StringDataBean>(StringDataBean.class) {
                        @Override
                        public void onSuccess(StringDataBean result) {
                            Map<String, String> defMap = MapUtils.getDefMap(false);
                            defMap.put("grant_type", "mobile");
                            defMap.put("mobile", ConfigUtils.shareString + "@" + userInfo.getUnionid());
                            mPresenter.postBindCode(defMap);
                        }

                        @Override
                        public void onError(String msg, int code) {
                            if (code == 1002) {
                                BindPhoneActivity.startAction(mContext, ConfigUtils.shareString,userInfo.getUnionid());
                            }
                        }
                    });
        }
    };

}
