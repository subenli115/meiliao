package com.ziran.meiliao.ui.main.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.OneKeyLoginListener;
import com.chuanglan.shanyan_sdk.listener.OpenLoginAuthListener;
import com.google.gson.Gson;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.common.security.EncodeUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.envet.ThreeLoginCallBack;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.TokenBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.ui.settings.activity.IntputCodeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.UserInfo;

import java.util.Map;


/**
 * Created by Administrator on 2018/6/12.
 */

public class NewLoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements RadioGroup.OnCheckedChangeListener, LoginContract.View{



    private NewLoginActivity loginActivity;
    private Dialog threeLogindialog;
    private Intent intent;
    private TokenBean bean;
    private CheckPhoneBean databean;

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyAPP.setReqPerOk(false);
        UMShareAPI.get(this).release();
    }
    @Override
    public void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        loginActivity = (NewLoginActivity) this;
        DisplayUtil.measureSoftKeyBoardHeight(this);
        intent = new Intent();
//        if (OnMultiClickUtils.isMultiClickClick(getApplicationContext())) {
//            OneKeyLoginManager.getInstance().setAuthThemeConfig(ConfigUtils.getCJSConfig(getApplicationContext()));
//            openLoginActivity();
//        }
    }
    
    
    
    private void login(CheckPhoneBean result){
            Map<String, String> loginMap = MapUtils.getLoginMap(result.getData().getMobile(),result.getData().getMobileCode(), null);
            mPresenter.postLoginRequest(loginMap);
    }


    private void openLoginActivity() {
        //拉授权页方法
        OneKeyLoginManager.getInstance().openLoginAuth(false, new OpenLoginAuthListener() {
            @Override
            public void getOpenLoginAuthStatus(int code, String result) {
                if (1000 == code) {
                    //拉起授权页成功
                    Log.e("VVV", "拉起授权页成功： _code==" + code + "   _result==" + result);
                } else {
                    //拉起授权页失败
                    Log.e("VVV", "拉起授权页失败： _code==" + code + "   _result==" + result);
                    Intent intent=new Intent();
                    intent.putExtra("Connected","false");
                    intent.setClass(mContext,IntputCodeActivity.class);
                    startActivity(intent);
                }
            }
        }, new OneKeyLoginListener() {
            @Override
            public void getOneKeyLoginStatus(int code, String result) {
                if (1011 == code) {
                    Log.e("VVV", "用户点击授权页返回： _code==" + code + "   _result==" + result);
                    intent.setClass(getBaseContext(),IntputCodeActivity.class);
                    startActivity(intent);
                    return;
                } else if (1000 == code) {
                    Log.e("VVV", "用户点击登录获取token成功： _code==" + code + "   _result==" + result);
                    Map<String, String> defMap = MapUtils.getDefMap(false);
                    Gson gson = new Gson();
                    bean = gson.fromJson(result, TokenBean.class);
                    defMap.put("token", bean.getToken());
                    mPresenter.postCheckLoginPhone(defMap);

                } else {
                    Log.e("VVV", "用户点击登录获取token失败： _code==" + code + "   _result==" + result);
                }
            }
        });
    }
    private boolean isExit;
    //主线程处理视图，isExit默认为false，就是点击第一次时，弹出"再按一次退出程序"
    //点击第二次时关闭应用
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点击两次退出程序
     */
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            //参数用作状态码；根据惯例，非 0 的状态码表示异常终止。
            System.exit(0);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private String jsonData;
    private String platName;
    private ThreeLoginCallBack threeLoginCallBack = new ThreeLoginCallBack() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            super.onComplete(share_media, i, map);
            UserInfo userInfo = UserInfo.parse(map, share_media.name());
            LogUtils.logd("userInfo" + userInfo.toString());
            String toJson = JsonUtils.toJson(userInfo);
            String utf = EncodeUtil.encodeUTF(toJson);
            jsonData = AES.get().encrypt(utf.getBytes());
            loginActivity.setPlatName(userInfo.getPlatName());
            loginActivity.setJsonData(jsonData);
            platName = userInfo.getPlatName();
            Map<String, String> partyLoginMap = MapUtils.getPartyLoginMap(share_media.name(), jsonData);
            //检测第三方登录是否绑定
            mPresenter.postPartyLoginData(partyLoginMap);
        }
    };




    @Override
    public void returnLoginData(LoginBean registerBean) {
        OneKeyLoginManager.getInstance().setLoadingVisibility(false);
//        OneKeyLoginManager.getInstance().finishAuthActivity();
        ToastUitl.show("登录成功",1000);
//        finish();
    }


    @Override
    public void returnBindPhoneData(LoginBean registerBean) {
        if (EmptyUtils.isNotEmpty(registerBean)) {
            loginSuccess(registerBean);
        }
        if (threeLogindialog != null) {
            threeLogindialog.dismiss();
        }
    }

    @Override
    public void returnLoginCode(StringDataV2Bean registerBean) {

    }

    @Override
    public void returnBindCode(LoginBean registerBean) {

    }

    //登录成功跳转页面
    private void loginSuccess(LoginBean registerBean) {
        //保存token
//       MeiliaoConfig.setPhone(registerBean.getData().get.getPhone());
//        PrefUtils.putString("phone",registerBean.getData().getUserInfo().getPhone(),mContext);
//        SPUtils.setString(AppConstant.SPKey.PHONE,registerBean.getData().getUserInfo().getPhone());
//        com.ziran.meiliao.entry.UserInfo userInfo = registerBean.getData().getUserInfo();
//        MyAPP.setmUserInfo(userInfo);
//        if (!registerBean.getData().isPhoneRegister()) {
//           BindPhoneActivity.startAction(this);
//           return;
//        }
        //跳转到主界面
        MainActivity.startAction(this, 1);
        if (!MyAPP.isLogout()) {
            mRxManager.post(AppConstant.RXTag.UPDATE_USER, "login");
        }
        mRxManager.post("exercise", "login");
        finish();
    }
    @Override
    public void returnPartyLogin(LoginBean loginBean) {
        if (loginBean.getData() == null) {
            ToastUitl.showShort(loginBean.getResultMsg());
            return;
        }
        //如果已经绑定则跳转到跳转到首页
        loginSuccess(loginBean);
    }

    @Override
    public void showPwdLogin(LoginBean result) {

    }

    @Override
    public void showUserInfo(UserBean result) {

    }

    @Override
    public void showLoginCheck(CheckPhoneBean result) {
        login(result);
    }


    @Override
    public void showCheckSaveData(TagCheckBean data) {

    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getJsonData() {
        return jsonData;
    }

    public static void startAction(Context mContext) {
        if(mContext!=null){
            Intent intent = new Intent(mContext, NewLoginActivity.class);
            mContext.startActivity(intent);
        }
    }
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
    //从区号选择页面返回数据处理
    @Override
    public void onBackPressed() {
        if (MyAPP.isLogout) {
//            MainActivity.startAction(this);
            finish();
        } else {
        }
    }
    //第三方登录数据的封装
    public static class ThreeLoginBean {
        private String third;//0  or 1
        private String platName;
        private String jsonData;

        public String getThird() {
            return third;
        }

        public void setThird(String third) {
            this.third = third;
        }

        public String getPlatName() {
            return platName;
        }

        public void setPlatName(String platName) {
            this.platName = platName;
        }

        public String getJsonData() {
            return jsonData;
        }

        public void setJsonData(String jsonData) {
            this.jsonData = jsonData;
        }
    }
    }
