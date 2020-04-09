package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.jkb.vcedittext.VerificationCodeEditText;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.sms.SmsObserver;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.ChangePhoneBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.bean.UserInfoBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.SmsCodeView;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.widget.SmsCodeView.TYPE_POST_LOGIN;

public class ChangePhoneFinishActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {
    private String phone;
    @Bind(R.id.tv_phone)
     TextView tvPhone;
    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.vcet_input_code)
    VerificationCodeEditText vic;
    @Bind(R.id.tv_code)
    TextView tvCode;
    private Thread thread;
    private SmsObserver observer;
    private int TYPE_POST = TYPE_POST_LOGIN;
    private String codeNumber="+86";
    /**
     * 吐丝内容
     */
    private String toast ="重获验证码";

    /**
     * 自动填写短信验证码回调
     */
    private SmsCodeView.OnSmsCallBack mOnSmsCallBack;

    /**
     * 标记是否注册短信观察者
     */
    private boolean isRegistSms;
    private Map<String, String> codeMap;

    @Override
    public int getLayoutId() {
        return R.layout.ac_change_phone_finish;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }
    public static void startAction(Context mContext, String phone) {
        Intent intent = new Intent(mContext, ChangePhoneFinishActivity.class);
        intent.putExtra("phone",phone);
        mContext.startActivity(intent);
    }
    @Override
    public void initView() {
        super.initView();
        ntb.setNewTitleText("更换手机号");
        if(getIntent()!=null){
             phone = getIntent().getStringExtra("phone");
        }
        tvPhone.setText(phone);

        setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> codeMap) {
                codeMap.put("areaCode", codeNumber);
                getSmsCode(codeMap);
            }
        });
        if (mOnSmsCallBack != null) {
            registerSms();
            Map<String, String> codeMap = MapUtils.getCodeMap("", phone, "false");
            mOnSmsCallBack.call(TYPE_POST, codeMap);
        }
    }
    public void setOnSmsCallBack(SmsCodeView.OnSmsCallBack callBack) {
        this.mOnSmsCallBack = callBack;
    }
    private void getSmsCode(Map<String, String> codeMap) {
        this.codeMap = codeMap;
        mPresenter.postData(ApiKey.SMS_GET_SMSCODE,codeMap,StringDataBean.class);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg==null)
                return;
            if (msg.what == 0) {
                tvCode.setEnabled(true);
                tvCode.setText(toast);
                return;
            }
            if(tvCode!=null){
                tvCode.setText(msg.what + "s");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(getCodeTask);
        if(thread!=null){
            thread.interrupt();
        }
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



    @Override
    public void returnData(Result result) {
        if(result instanceof StringDataBean){
            ToastUitl.showShort(getString(R.string.getcode_ok));
//        ivSubmit.setEnabled(false);
            tvCode.setEnabled(false);
            thread = new Thread(getCodeTask);
            thread.start();
        }else {

            if (!MyAPP.isLogout()) {
                OkHttpClientManager.postAsync(ApiKey.USER_INFO, MapUtils.getDefMap(true), new NewRequestCallBack<UserInfoBean>(UserInfoBean.class) {
                    @Override
                    public void onSuccess(UserInfoBean result) {//登录请求成功后执行
                        //判断数据库是否有该用户,没有则添加进去
                        MyAPP.getDBUserInfo(result.getUserInfo());
                    }

                    @Override
                    public void onError(String msg, int code) {
                    }
                });
            }
        }


    }

    @Override
    public void returnAction(Result result) {

}
    //点击监听
    @OnClick({R.id.tv_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change:
                if(vic.getText().toString().length()!=4){
                    ToastUitl.showShort("请输入验证码");
                }
                Map<String, String> codeMap = MapUtils.getSmsMap( phone, vic.getText().toString(),2);
                OkHttpClientManager.postAsync(ApiKey.SMS_CHECK_SMSCODE, codeMap, new NewRequestCallBack<ChangePhoneBean>(ChangePhoneBean.class) {
                    @Override
                    protected void onSuccess(ChangePhoneBean result) {
                        if (result.getResultCode()==1){
                            ToastUitl.show("绑定成功",0);
                            finish();
                            startActivity(SuccessNewphoneActivity.class);
                            MyAPP.getUserInfo().setPhone(phone);
//                            MyAPP.setAccessToken(result.getData().getAccessToken());
                        }
                    }

                    @Override
                    public void onError(String msg, int code) {
                        super.onError(msg, code);
                        ToastUitl.show(msg,0);
                    }
                });

                break;
        }
    }


}
