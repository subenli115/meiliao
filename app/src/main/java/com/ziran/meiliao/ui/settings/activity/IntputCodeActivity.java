package com.ziran.meiliao.ui.settings.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jkb.vcedittext.VerificationCodeEditText;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.sms.SmsObserver;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.main.activity.LabelActivity;
import com.ziran.meiliao.ui.main.activity.MainActivity;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.SmsCodeView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.widget.SmsCodeView.TYPE_POST_LOGIN;

/**
 * Created by Administrator on 2018/6/13.
 */

public class IntputCodeActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View

{

    /**
     * 自动填写短信验证码回调
     */
    private SmsCodeView.OnSmsCallBack mOnSmsCallBack;
    /**
     * 标记是否注册短信观察者
     */
    private boolean isRegistSms;
    private int TYPE_POST = TYPE_POST_LOGIN;
    @Bind(R.id.vcet_input_code)
    VerificationCodeEditText vic;
    private String phoneNum;
    @Bind(R.id.iv_submit)
    TextView ivSubmit;
    @Bind(R.id.all_time)
    AutoLinearLayout allTime;
    @Bind(R.id.tv_code)
    TextView tvCode;
    private SmsObserver observer;
    private String codeNumber="+86";
    /**
     * 吐丝内容
     */
    private String toast ="重获验证码";
    private Thread thread
            ;
    private int tagStatus;

    //登录请求成功后执行
    @Override
    public void returnLoginData(LoginBean registerBean) {
        //讲用户phone和token保存到偏好设置
        SPUtils.setString(AppConstant.SPKey.PHONE, phoneNum);
        FileUtil.createFileFolder(WpyxConfig.setPhone(phoneNum), mContext);
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

    //登录成功跳转页面
    private void loginSuccess(LoginBean registerBean) {
        handler.removeCallbacks(getCodeTask);
        //保存token
        SPUtils.setString(AppConstant.SPKey.TOKEN, registerBean.getData().getAccessToken());
        MyAPP.setAccessToken(registerBean.getData().getAccessToken());
        com.ziran.meiliao.entry.UserInfo userInfo = registerBean.getData().getUserInfo();
        MyAPP.setmUserInfo(userInfo);
        //跳转到主界面
        mPresenter.postTagSaveCheck(MapUtils.getDefMap(true));
        if (!MyAPP.isLogout()) {
            mRxManager.post(AppConstant.RXTag.UPDATE_USER, "login");
        }
        mRxManager.post("exercise", "login");
        finish();
    }

    //返回登录失败结果时执行
    @Override
    public void showErrorTip(String msg) {
        stopProgressDialog();
        showShortToast(getString(R.string.login_erroe) + msg);
        ivSubmit.setEnabled(true);
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
        ivSubmit.setEnabled(true);
    }
    @Override
    public void returnBindPhoneData(LoginBean registerBean) {

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

    //点击监听
    @OnClick({R.id.iv_back,R.id.iv_submit,R.id.all_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_submit:
                //提交
                login();
                break;
            case R.id.iv_back:
                //返回
                NewLoginActivity.startAction(this);
                finish();
                break;
        }


    }
    //执行登录请求
    private void login() {
        String phone =phoneNum;
        Map<String, String> loginMap = MapUtils.getLoginMap(phone, vic.getText().toString(), null);
        loginMap.put("areaCode", vic.getText().toString());
        mPresenter.postLoginRequest(loginMap);

    }
    //成功获取验证码后执行,执行倒计时
    @Override
    public void returnLoginCode(Result registerBean) {
        ToastUitl.showShort(getString(R.string.getcode_ok));
        allTime.setEnabled(false);
         thread = new Thread(getCodeTask);
        thread.start();
    }
    Runnable getCodeTask = new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 60; i >= 0; i--) {
                    handler.sendEmptyMessage(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg==null)
                return;
            if (msg.what == 0) {
                allTime.setEnabled(true);
                tvCode.setText(toast);
                return;
            }
                tvCode.setText(msg.what + "s");
        }
    };

    @Override
    protected void onDestroy() {
        onDetachedFromWindow();
        handler.removeCallbacks(getCodeTask);
        if(thread!=null){
            thread.interrupt();
        }
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_phone_code;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
            if(getIntent()!=null){
                Intent intent = getIntent();
                 phoneNum = intent.getStringExtra("phoneNum");
                codeNumber = intent.getStringExtra("codeNumber");
                Log.e("phoneNum",""+phoneNum+"   "+codeNumber);
            }
        allTime.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if (phoneNum != null) {
                    String phone = phoneNum;

                    if (!RegexUtils.regexMoble(phone, codeNumber)) {
                        return;
                    }
                    if (mOnSmsCallBack != null) {
                        registerSms();
                        Map<String, String> codeMap = MapUtils.getCodeMap("", phone, "false");
                        mOnSmsCallBack.call(TYPE_POST, codeMap);
                    }
                }
            }
        });
        setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> codeMap) {
                codeMap.put("areaCode", codeNumber);
                mPresenter.postLoginCode(codeMap);
            }
        });
    }

    public void setOnSmsCallBack(SmsCodeView.OnSmsCallBack callBack) {
        this.mOnSmsCallBack = callBack;
    }
    //注册短信观察者
    public void registerSms() {
        if (isRegistSms) return;

        Handler mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg==null)
                    return;
                if (msg.what == SmsObserver.MSG_RECEIVED_CODE) {
                    String code = (String) msg.obj;
                    /**
                     * 更新UI：实现自动填写短信验证码
                     */
                    if(code!=null){
//                        vic.setText(code);
                    }
//                    if (tvPwd != null) {
//                        KeyBordUtil.reqFocus(tvPwd);
//                    }
                }
            }
        };
        observer = new SmsObserver(this, mHandler);
        observer.register();
        isRegistSms = true;
    }
}
