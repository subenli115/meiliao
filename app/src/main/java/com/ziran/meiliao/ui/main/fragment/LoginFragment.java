package com.ziran.meiliao.ui.main.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.citypicker.citylist.sortlistview.SortModel;
import com.citypicker.citylist.utils.CityDataDb;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.common.security.EncodeUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.envet.ThreeLoginCallBack;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.activity.LoginActivity;
import com.ziran.meiliao.ui.main.activity.MainActivity;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.PhoneCodeView;
import com.ziran.meiliao.widget.SmsCodeView;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.LoginApi;
import com.umeng.soexample.UserInfo;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 登录页面Fragment
 */
public class LoginFragment extends CommonHttpFragment<LoginPresenter, LoginModel> implements LoginContract.View {

    @Bind(R.id.phone_code_view)
    PhoneCodeView mPhoneCodeView;

    @Bind(R.id.et_login_pwd)
    EditText etPwd;

    @Bind(R.id.scv_login)
    SmsCodeView smsCodeView;

    @Bind(R.id.tv_sms_login)
    TextView tv_sms_login;

    @Bind(R.id.tv_pwd_login)
    TextView tv_pwd_login;

    @Bind(R.id.tv_login_forgetPwd)
    TextView tv_login_forgetPwd;

    @Bind(R.id.bt_login_login)
    Button bt_login_login;

    private LoginActivity loginActivity;
    //第三方登录平台的名称
    private String platName;
    //第三方登录返回的json数据
    private String jsonData;
    //当前城市的对象
    private SortModel mCityItem;
    //绑定对话框的验证码组合控件
    private SmsCodeView bindDialogSmsCodeView;


    public static LoginFragment newInstance(String data1, String data2) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data1", data1);
        bundle.putString("data2", data2);
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_login;
    }


    @Override
    protected void initView() { //初始化控件
        loginActivity = (LoginActivity) getActivity();
        smsCodeView.setTvPhone(mPhoneCodeView.getEtPhone());
        smsCodeView.setTvPwd(etPwd);
        //点击验证码是执行
        smsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> codeMap) {
                codeMap.put("areaCode", mCityItem.getCodeNumber());
                mPresenter.postLoginCode(codeMap);
            }
        });
        smsCodeView.setFocusListener(new SmsCodeView.OnNotFocusListener() {
            @Override
            public void notFocus(boolean focus) {
                if (bt_login_login != null) {
                    bt_login_login.setEnabled(true);
                }
            }
        });
        mCityItem = CityDataDb.getCountryZipCode(getContext());
        smsCodeView.setCodeNumberCallBack(new SmsCodeView.CodeNumberCallBack() {
            @Override
            public String getCodeNumber() {
                return mCityItem != null ? mCityItem.getCodeNumber() : "";
            }
        });
        mPhoneCodeView.setPhoneText(SPUtils.getString(AppConstant.SPKey.PHONE));
        mPhoneCodeView.setTvAreaCodeClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
            }
        });
        smsCodeView.setOnEditActionListener(mOnEditorActionListener, "登录", EditorInfo.IME_ACTION_SEND);
        etPwd.setOnEditorActionListener(mOnEditorActionListener);

//
//        HandlerUtil.runMain(new Runnable() {
//            @Override
//            public void run() {
//                etPwd.setText("123456");
//                login();
//            }
//        });
    }

    //输入法回车点击监听
    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            LogUtils.logd("actionId" + actionId);
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEND:
                case EditorInfo.IME_ACTION_UNSPECIFIED:
                    login();
                    break;
            }
            return true;
        }
    };

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

    //点击监听
    @OnClick({R.id.bt_login_login, R.id.tv_login_forgetPwd, R.id.tv_sms_login, R.id.tv_pwd_login, R.id.tv_login_wc, R.id.tv_login_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login_login:
                //执行登录请求
                login();
                break;
            case R.id.tv_login_forgetPwd:
                //执行跳转到忘记密码界面
                break;
            case R.id.tv_sms_login:
                //点击切换短信验证码登录
                smsCodeView.setVisibility(View.VISIBLE);
                tv_pwd_login.setVisibility(View.VISIBLE);
                etPwd.setVisibility(View.GONE);
                tv_sms_login.setVisibility(View.GONE);
                tv_login_forgetPwd.setVisibility(View.GONE);
                break;
            case R.id.tv_pwd_login:
                //点击切换密码登录
                smsCodeView.setVisibility(View.GONE);
                etPwd.setVisibility(View.VISIBLE);
                tv_sms_login.setVisibility(View.VISIBLE);
                tv_pwd_login.setVisibility(View.GONE);
                tv_login_forgetPwd.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_login_wc:
                //微信登录
                LoginApi.get().login(getActivity(), SHARE_MEDIA.WEIXIN, threeLoginCallBack);
                break;
            case R.id.tv_login_qq:
                //QQ登录
                LoginApi.get().login(getActivity(), SHARE_MEDIA.QQ, threeLoginCallBack);
                break;
//            case R.id.tv_login_xl:
//                //新浪微博登录
//                LoginApi.get().login(getActivity(), SHARE_MEDIA.SINA, threeLoginCallBack);
//                break;
        }
    }

    //设置区号
    public void setAreaCode(SortModel cityItem) {
        if (EmptyUtils.isEmpty(cityItem)) return;
        this.mCityItem = cityItem;
        mPhoneCodeView.setAreaCode(mCityItem.getData());
    }


    //执行登录请求
    private void login() {
        String phone = mPhoneCodeView.getPhoneText();
        String pwd = etPwd.getText().toString();
        String code = smsCodeView.getCode();
        if (smsCodeView.isShown()) {
            pwd = null;
            if (!RegexUtils.regex(phone, "notCheck", code, mCityItem.getCodeNumber())) {
                return;
            }
        } else {
            if (!RegexUtils.regex(phone, pwd, "notCheck", mCityItem.getCodeNumber())) {
                return;
            }
            code = null;
        }
        Map<String, String> loginMap = MapUtils.getLoginMap(phone, code, pwd);
        loginMap.put("areaCode", mCityItem.getCodeNumber());
        mPresenter.postLoginRequest(loginMap);
        bt_login_login.setEnabled(false);
        KeyBordUtil.hideSoftKeyboard(etPwd);
    }


    //登录请求成功后执行
    @Override
    public void returnLoginData(LoginBean registerBean) {
        //讲用户phone和token保存到偏好设置
        SPUtils.setString(AppConstant.SPKey.PHONE, mPhoneCodeView.getPhoneText());
        FileUtil.createFileFolder(MeiliaoConfig.setPhone(mPhoneCodeView.getPhoneText()),getContext());
        loginSuccess(registerBean);
    }

    @Override
    public void showCheckSaveData(TagCheckBean data) {

    }

    //返回绑定手机结果
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
        //跳转到主界面
        MainActivity.startAction(getActivity(), 1);
        if (!MyAPP.isLogout()) {
            mRxManager.post(AppConstant.RXTag.UPDATE_USER, "login");
        }
        mRxManager.post("exercise", "login");
        getActivity().finish();
    }



    //返回第三方登录结果
    @Override
    public void returnPartyLogin(LoginBean loginBean) {
        if (loginBean.getData() == null) {
            ToastUitl.showShort(loginBean.getResultMsg());
            return;
        }
        //如果已经绑定则跳转到跳转到首页
//        if (loginBean.getData().isUser()) {
            loginSuccess(loginBean);
//        } else { //否则执行绑定请求
////            ThreeLoginBean bean = new ThreeLoginBean();
////            bean.setJsonData(jsonData);
////            bean.setThird("1");
////            bean.setPlatName(platName);
////            //弹出绑定对话框
////            showBindDialog(bean);
//        }
    }

    @Override
    public void showPwdLogin(LoginBean result) {

    }

    @Override
    public void showUserInfo(UserBean result) {

    }

    @Override
    public void showLoginCheck(CheckPhoneBean result) {

    }


    Dialog threeLogindialog;

    //绑定手机的对话框
    private  void showBindDialog(final ThreeLoginBean bean) {
        threeLogindialog = new Dialog(getContext(), R.style.MyDialogStyle);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_bind_phone, null);
        bindDialogSmsCodeView = ViewUtil.getView(contentView, R.id.smsCodeView);
        final PhoneCodeView phoneCodeView = ViewUtil.getView(contentView, R.id.phone_code_view);
        final TextView tvBind = ViewUtil.getView(contentView, R.id.btn_bing);
        bindDialogSmsCodeView.setTvPhone(phoneCodeView.getEtPhone());
        bindDialogSmsCodeView.setCodeNumberCallBack(new SmsCodeView.CodeNumberCallBack() {
            @Override
            public String getCodeNumber() {
                return mCityItem.getCodeNumber();
            }
        });
        bindDialogSmsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> codeMap) {
                codeMap.put("areaCode", mCityItem.getCodeNumber());
                if (EmptyUtils.isNotEmpty(bean)) {
                    codeMap.put("jsonData", bean.getJsonData());
                    codeMap.put("platName", bean.getPlatName());
                    codeMap.put("third", bean.getThird());
                }
                mPresenter.postBindCode(codeMap);
            }
        });
        bindDialogSmsCodeView.bindBtn(tvBind);
        phoneCodeView.setTvAreaCodeClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
            }
        });
        tvBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneCodeView.getPhoneText();
                String code = bindDialogSmsCodeView.getCode();
                if (RegexUtils.regex(phone, "notCheck", code, mCityItem.getCodeNumber())) {
//                    threeLogindialog.dismiss();
                    Map<String, String> registMap = MapUtils.getRegisterMap(mCityItem.getCodeNumber(), phone, code, "");
                    if (EmptyUtils.isNotEmpty(loginActivity.getJsonData())) {
                        registMap.put("platName", loginActivity.getPlatName());
                        registMap.put("jsonData", loginActivity.getJsonData());
                    }
                    mPresenter.postBindPhoneRequest(registMap);
                }
            }
        });
        Window dialogWindow = threeLogindialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        threeLogindialog.setContentView(contentView);
        threeLogindialog.show();
    }

    //开始请求登录
    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(getActivity(), getString(R.string.is_login), true);
    }

    //请求结束时执行
    @Override
    public void stopLoading() {
        bt_login_login.setEnabled(true);
        LoadingDialog.cancelDialogForLoading();
    }

    //返回登录失败结果时执行
    @Override
    public void showErrorTip(String msg) {
        stopProgressDialog();
        showShortToast(getString(R.string.login_erroe) + msg);
        bt_login_login.setEnabled(true);
    }

    @Override
    public void showEmtry() {
        stopProgressDialog();
        etPwd.setText("");
        bt_login_login.setEnabled(true);
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
