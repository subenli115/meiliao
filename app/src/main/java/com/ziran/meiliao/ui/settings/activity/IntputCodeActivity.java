package com.ziran.meiliao.ui.settings.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.LoginApi;
import com.umeng.soexample.UserInfo;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.receiver.NetUtil;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.envet.ThreeLoginCallBack;
import com.ziran.meiliao.im.utils.DialogCreator;
import com.ziran.meiliao.im.utils.FileHelper;
import com.ziran.meiliao.im.utils.SharePreferenceManager;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.activity.PrivatePopActivity;
import com.ziran.meiliao.ui.main.activity.SplashActivity;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.SmsCodeView;

import java.io.File;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

import static com.ziran.meiliao.widget.SmsCodeView.TYPE_POST_LOGIN;

/**
 * 验证码登录
 * Created by Administrator on 2018/6/13.
 */

public class IntputCodeActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    private int TYPE_POST = TYPE_POST_LOGIN;
    private String phoneNum;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.smsCodeView)
    SmsCodeView smsCodeView;
    @Bind(R.id.iv_other)
    ImageView ivOther;
    @Bind(R.id.arl_login)
    AutoRelativeLayout arlLogin;
    @Bind(R.id.tv_other)
    TextView tvOther;
    @Bind(R.id.bg)
    AutoLinearLayout bg;



    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    /**
     * 吐丝内容
     */
    private Intent intent;
    private String shareString;
    private IntputCodeActivity codeActivity;
    private Dialog dialog;
    private int mWidth;
    private View contentView;
    private boolean flag;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private UserInfo userInfo;


    @Override
    public int getLayoutId() {
        return R.layout.activity_phone_code_new;
    }

    //登录请求成功后执行
    @Override
    public void returnLoginData(LoginBean registerBean) {
        //讲用户phone和token保存到偏好设置

        SPUtils.setString(AppConstant.SPKey.PHONE, phoneNum);
        FileUtil.createFileFolder(MeiliaoConfig.setPhone(phoneNum), mContext);
        loginSuccess(registerBean);
    }

    @Override
    public void showCheckSaveData(TagCheckBean data) {
    }

    //登录成功跳转页面
    private void loginSuccess(LoginBean loginBean) {
        //保存token
        MyAPP.saveUserLoginData(loginBean);

        if (!MyAPP.isLogout()) {
            mRxManager.post(AppConstant.RXTag.UPDATE_USER, "login");
        }
        mPresenter.getUserInfo(MyAPP.getUserId(),MyAPP.getAccessToken());
    }

    //返回登录失败结果时执行
    @Override
    public void showErrorTip(String msg) {
        stopProgressDialog();
        showShortToast(getString(R.string.login_erroe) + msg);
        tvLogin.setEnabled(true);
    }

    //开始请求登录
    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(this, getString(R.string.is_login), true);
    }

    //请求结束时执行
    @Override
    public void stopLoading() {
        LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void showEmtry() {
        stopProgressDialog();
        tvLogin.setEnabled(true);
    }

    @Override
    public void returnBindPhoneData(LoginBean registerBean) {

    }

    @Override
    public void returnLoginCode(StringDataV2Bean registerBean) {
        ToastUitl.showShort(getString(R.string.getcode_ok));
        smsCodeView.startDjs();
    }


    @Override
    public void returnBindCode(LoginBean loginBean) {
        MyAPP.saveUserLoginData(loginBean);
        mPresenter.getUserInfo(MyAPP.getUserId(),MyAPP.getAccessToken());
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
        if (data.getNickname() != null && data.getNickname().length() > 0) {
            MyAPP.setmUserBean(data);
            startProgressDialog("正在登录");
            MyAPP.imLogin(mContext);
            //跳转到主界面`
//            if (!MyAPP.isLogout()) {
//                mRxManager.post(AppConstant.RXTag.UPDATE_USER, "login");
//            }
        } else {
            InputUserInfoActivity.startAction(mContext);
        }

    }

    @Override
    public void showLoginCheck(CheckPhoneBean result) {

    }

    //点击监听
    @OnClick({R.id.tv_login, R.id.iv_wechat, R.id.iv_qq,R.id.arl_down,R.id.iv_pwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                //提交
                login();
                break;
            case R.id.iv_wechat:
                //微信登录
                shareString = "WX";
                LoginApi.get().login(this, SHARE_MEDIA.WEIXIN, threeLoginCallBack);
                break;
            case R.id.iv_qq:
                //qq登录
                shareString = "QQ";
                LoginApi.get().login(this, SHARE_MEDIA.QQ, threeLoginCallBack);
                break;
            case R.id.iv_pwd:
                InputPasswordActivity.startAction(this);
                break;

            case R.id.arl_down:
                tvOther.setVisibility(View.INVISIBLE);
                arlLogin.setVisibility(View.VISIBLE);
                view.setVisibility(View.INVISIBLE);
                break;


        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    private ThreeLoginCallBack threeLoginCallBack = new ThreeLoginCallBack() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            super.onComplete(share_media, i, map);
            userInfo = UserInfo.parse(map, share_media.name());
            codeActivity.setUserInfo(userInfo);
            OkHttpClientManager.getAsyncOne(ApiKey.ADMIN_SOCIAL_SOCIAL, shareString + "@" +userInfo.getUnionid(), new
                    NewRequestCallBack<StringDataBean>(StringDataBean.class) {
                        @Override
                        public void onSuccess(StringDataBean result) {
                            Map<String, String> defMap = MapUtils.getDefMap(false);
                            defMap.put("grant_type", "mobile");
                            defMap.put("mobile", shareString + "@" + userInfo.getUnionid());
                            mPresenter.postBindCode(defMap);
                        }

                        @Override
                        public void onError(String msg, int code) {
                            if (code == 1002) {
                                BindPhoneActivity.startAction(mContext, shareString,userInfo.getUnionid());
                            }
                        }
                    });
        }
    };


    //执行登录请求
    private void login() {
        phoneNum = edPhone.getText().toString();
        Map<String, String> loginMap = MapUtils.getLoginMap(phoneNum, smsCodeView.getCode(), null);
        loginMap.put("areaCode", "+86");
        mPresenter.postLoginRequest(loginMap);



    }
    //成功获取验证码后执行,执行倒计时

    @Override
    protected void onDestroy() {
        onDetachedFromWindow();
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!flag){
            if(getIntent()!=null){
                int code = getIntent().getIntExtra("code",0);
                if(code==1005){
                    showPopWindow(0);
                }
            }

            flag=true;
        }
    }

    private void showPopWindow(int type) {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(MyAPP.context).inflate(R.layout.pop_login_result, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(bg, Gravity.CENTER, 0, 0);
        TextView title = contentView.findViewById(R.id.tv_title);
        TextView content = contentView.findViewById(R.id.tv_update_version_content);
        TextView qd = contentView.findViewById(R.id.tv_qd);

        TextView qx = contentView.findViewById(R.id.tv_qx);
        if(type==1){
            content.setText("账户违规，已被永久封号");
            title.setText("封号");
        }else if(type==3){
            content.setText("请发邮件联系客服：meiliao@51sqbb.com");
            title.setText("申述");
            qx.setVisibility(View.GONE);
        }
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                showPopWindow(3);
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });

    }

    @Override
    public void initView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mWidth = dm.widthPixels;
        codeActivity = (IntputCodeActivity) this;
        if (getIntent() != null) {
            Intent intent = getIntent();
            String connected = intent.getStringExtra("Connected");
            String code = intent.getStringExtra("code");
            if (connected != null && connected.equals("false")) {
                ntb.setTvLeftVisiable(false);
            }

        }
        if (SPUtils.getBoolean("First", false)) {
            intent.setClass(mContext, PrivatePopActivity.class);
            SPUtils.setBoolean("First", false);
            startActivity(intent);
        }
        mRxManager.on(AppConstant.RXTag.NETWORK_CHANGE_STATE, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
//                    case NetUtil.NETWORK_MOBILE:
//                        break;
                    case NetUtil.NETWORK_WIFI:
                        intent = new Intent();
                        intent.setClass(getBaseContext(), IntputCodeActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
        smsCodeView.setTvPhone(edPhone);
        smsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> map) {
                map.put("type", "2");
                mPresenter.postLoginCode(map);
            }
        });
        smsCodeView.getEtitTextCode().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (smsCodeView.getCode().length() == 4 && edPhone.getText().toString().length() == 11) {
                    tvLogin.setBackgroundResource(R.drawable.normal_bg_bule);
                    tvLogin.setEnabled(true);
                } else {
                    tvLogin.setBackgroundResource(R.drawable.normal_bg_bule50);
                    tvLogin.setEnabled(false);
                }
            }
        });
    }

}
