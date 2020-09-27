package com.ziran.meiliao.ui.settings.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.im.utils.CommonUtils;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.utils.AesUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 输入密码
 * Created by Administrator on 2018/6/12.
 */

public class InputPasswordActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View, ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int REQUEST_READ_PHONE_STATE = 4;
    private String phoneNum;
    @Bind(R.id.ed_pwd)
    EditText etPwd;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    private String codeNumber;
    Intent intent;

    @Override
    public void returnLoginData(LoginBean registerBean) {
    }

    @Override
    public void showCheckSaveData(TagCheckBean data) {
    }

    @Override
    public void returnBindPhoneData(LoginBean registerBean) {

    }

    @Override
    public void returnLoginCode(StringDataV2Bean registerBean) {

    }


    @Override
    public void returnBindCode(LoginBean registerBean) {

    }

    @Override
    public void returnPartyLogin(LoginBean registerBean) {

    }

    @Override
    public void showPwdLogin(LoginBean result) {
        //讲用户phone和token保存到偏好设置
        loginSuccess(result);
    }

    @Override
    public void showUserInfo(UserBean result) {
        UserBean.DataBean data = result.getData();
        if (data.getNickname() != null && data.getNickname().length() > 0) {
            MyAPP.setmUserBean(data);
            startProgressDialog("正在登录");
            MyAPP.imLogin(mContext);
            //跳转到主界面
        } else {
            InputUserInfoActivity.startAction(mContext);
        }
    }

    @Override
    public void showLoginCheck(CheckPhoneBean result) {

    }

    //返回登录失败结果时执行
    @Override
    public void showErrorTip(String msg) {
        stopProgressDialog();
        showShortToast(getString(R.string.login_erroe) + msg);
        tvLogin.setEnabled(true);
    }

    @Override
    public void showEmtry() {
        stopProgressDialog();
        etPwd.setText("");
        tvLogin.setEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            default:
                break;
        }
    }

    //登录成功跳转页面
    private void loginSuccess(LoginBean loginBean) {
        KeyBordUtil.hideSoftKeyboard(etPwd);
        SPUtils.setString(AppConstant.SPKey.PHONE, phoneNum);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            //TODO
        }
        //保存token
        MyAPP.saveUserLoginData(loginBean);
        mPresenter.getUserInfo(MyAPP.getUserId(),MyAPP.getAccessToken());
    }


    //开始请求登录
    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(this, getString(R.string.is_login), true);
    }

    //请求结束时执行
    @Override
    public void stopLoading() {
        tvLogin.setEnabled(true);
        LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_password_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        if (getIntent() != null) {
            intent = getIntent();
            codeNumber = intent.getStringExtra("CodeNumber");
        }
        DisplayUtil.measureSoftKeyBoardHeight(this);

        etPwd.setOnEditorActionListener(mOnEditorActionListener);
        etPwd.addTextChangedListener(textWatcher);
        edPhone.addTextChangedListener(textWatcher);
        intent = new Intent();

    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (etPwd.getText().toString().length() >= 8 && edPhone.getText().toString().length() == 11) {
                tvLogin.setBackgroundResource(R.drawable.normal_bg_bule);
                tvLogin.setEnabled(true);
                phoneNum = edPhone.getText().toString();
            } else {
                tvLogin.setBackgroundResource(R.drawable.normal_bg_bule50);
                tvLogin.setEnabled(false);
            }
        }
    };


    //点击监听
    @OnClick({R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                CommonUtils.hideKeyboard(this);
                login();
                break;
        }

    }

    //执行登录请求
    private void login() {
        String phone = edPhone.getText().toString();
        String pwd = etPwd.getText().toString();
        Map<String, String> loginMap = MapUtils.getDefMap(false);
        loginMap.put("grant_type", "password");
        loginMap.put("username", phone);
        loginMap.put("password", AesUtil.get().encrypt(pwd));
        loginMap.put("scope", "server");
        mPresenter.postPwdLogin(loginMap);

    }

    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, InputPasswordActivity.class);
        mContext.startActivity(intent);
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
}
