package com.ziran.meiliao.ui.settings.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.main.activity.LabelActivity;
import com.ziran.meiliao.ui.main.activity.MainActivity;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PrefUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/** 输入密码
 * Created by Administrator on 2018/6/12.
 */

public class InputPasswordActivity extends BaseActivity<LoginPresenter, LoginModel> implements  LoginContract.View  , ActivityCompat.OnRequestPermissionsResultCallback{

    private static final int REQUEST_READ_PHONE_STATE =4 ;
    private String phoneNum;
    @Bind(R.id.tv_input_phone)
    EditText etPwd;
    private String codeNumber;
    @Bind(R.id.iv_finish)
    TextView ivFinish;
    Intent intent;

    @Override
    public void returnLoginData(LoginBean registerBean) {
        //讲用户phone和token保存到偏好设置
        SPUtils.setString(AppConstant.SPKey.PHONE, phoneNum);
        loginSuccess(registerBean);
    }

    @Override
    public void showCheckSaveData(TagCheckBean data) {
            if(data.getData().getStatus()==0){
                LabelActivity.startAction(this);
            }else {
                MainActivity.startAction(this, 1);
            }
    }

    @Override
    public void returnBindPhoneData(LoginBean registerBean) {

    }

    @Override
    public void returnLoginCode(Result registerBean) {

    }

    @Override
    public void returnBindCode(StringDataBean registerBean) {

    }

    @Override
    public void returnPartyLogin(LoginBean registerBean) {

    }

    @Override
    public void showLoginCheck(CheckPhoneBean result) {

    }

    //返回登录失败结果时执行
    @Override
    public void showErrorTip(String msg) {
        stopProgressDialog();
        showShortToast(getString(R.string.login_erroe) + msg);
        ivFinish.setEnabled(true);
    }

    @Override
    public void showEmtry() {
        stopProgressDialog();
        etPwd.setText("");
        ivFinish.setEnabled(true);
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
    private void loginSuccess(LoginBean registerBean) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            //TODO
        }
        //保存token
        SPUtils.setString(AppConstant.SPKey.TOKEN, registerBean.getData().getAccessToken());
        com.ziran.meiliao.entry.UserInfo userInfo = registerBean.getData().getUserInfo();
        MyAPP.setAccessToken(registerBean.getData().getAccessToken());
        MyAPP.setmUserInfo(userInfo);
        PrefUtils.putString("phone",registerBean.getData().getUserInfo().getPhone(),mContext);
        mPresenter.postTagSaveCheck(MapUtils.getDefMap(true));

        //跳转到主界面
        if (!MyAPP.isLogout()) {
            mRxManager.post(AppConstant.RXTag.UPDATE_USER, "login");
        }
        mRxManager.post("exercise", "login");
        finish();
    }

    //开始请求登录
    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(this, getString(R.string.is_login), true);
    }

    //请求结束时执行
    @Override
    public void stopLoading() {
        ivFinish.setEnabled(true);
        LoadingDialog.cancelDialogForLoading();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_input_password;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
        public void initView() {
        if (getIntent() != null) {
            intent = getIntent();
            phoneNum = intent.getStringExtra("phone");
            codeNumber = intent.getStringExtra("CodeNumber");
        }
        DisplayUtil.measureSoftKeyBoardHeight(this);
        etPwd.setOnEditorActionListener(mOnEditorActionListener);
        intent = new Intent();
    }
    //点击监听
    @OnClick({R.id.iv_finish,R.id.iv_back,R.id.tv_input_phone,R.id.tv_use_phone,R.id.tv_login_forgetPwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                login();
                break;
            case R.id.iv_back:
                //登录
                finish();
                break;
            case R.id.tv_use_phone:
                //验证码登录
                intent.putExtra("phoneNum",""+phoneNum);
                intent.setClass(this,IntputCodeActivity.class);
                intent.putExtra("codeNumber",""+codeNumber);
                startActivity(intent);
                break;
            case R.id.tv_login_forgetPwd:
                intent.putExtra("phoneNum",""+phoneNum);
                intent.setClass(this,ForgetPasswordActivity.class);
                startActivity(intent);
                break;
        }

    }

    //执行登录请求
    private void login() {
        String phone =phoneNum;
        String pwd = etPwd.getText().toString();
        Map<String, String> loginMap = MapUtils.getLoginMap(phone, null, pwd);
        loginMap.put("areaCode", codeNumber);
        mPresenter.postLoginRequest(loginMap);
        KeyBordUtil.hideSoftKeyboard(etPwd);
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
