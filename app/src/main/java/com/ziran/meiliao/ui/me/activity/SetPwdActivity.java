package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PrefUtils;
import com.ziran.meiliao.widget.SmsCode2View;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ${chenyn} on 2017/5/7.
 */

public class SetPwdActivity extends BaseActivity {

    @Bind(R.id.smsCodeView)
    SmsCode2View smsCodeView;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.ed_pwd)
    EditText edPwd;
    @Bind(R.id.ed_pwd_two)
    EditText edPwdTwo;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntb;
    @Bind(R.id.all_set)
    AutoLinearLayout allSet;
    @Bind(R.id.all_code)
    AutoLinearLayout allCode;
    @Bind(R.id.all_phone)
    AutoLinearLayout allPhone;
    private String type;
    private Map<String, String> oldDefMap;
    private int typeSet;
    private String nowPhone;

    public static void startAction(String type, int code) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SetPwdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, code);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_pwd;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        if (getBundle() != null) {
            String type = getBundle().getString("type");
            if (type != null&& type.length() > 0) {
                //修改密码
                typeSet = 1;
                smsCodeView.setTvPhone(edPhone);
                 nowPhone = PrefUtils.getString("phone", "", mContext);
                smsCodeView.setbg(nowPhone);
               String phone = nowPhone.substring(0, 3) + "****" + nowPhone.substring(7, 11);
                edPhone.setText(phone);
                edPhone.setEnabled(false);
                smsCodeView.setOnSmsCallBack(new SmsCode2View.OnSmsCallBack() {
                    @Override
                    public void call(int type, Map<String, String> map) {
                        getSmsCode(map);
                    }
                });
            } else {
                //设置新密码
                typeSet = 2;
                allCode.setVisibility(View.GONE);
                allPhone.setVisibility(View.GONE);
                allSet.setVisibility(View.VISIBLE);
            }
        }
        ntb.setTitleText("修改密码");
    }

    private void getSmsCode(Map<String, String> codeMap) {
        codeMap.put("type", "3");
        OkHttpClientManager.getAsyncMore(ApiKey.USER_GET_LOG_CODE, codeMap, new NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
            @Override
            protected void onSuccess(StringDataV2Bean result) {
                smsCodeView.startDjs();
        }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });

    }

    //点击监听
    @OnClick({R.id.tv_save})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        switch (view.getId()) {
            case R.id.tv_save:
                if(edPwd.equals("")||edPwd.length()<8){
                    ToastUitl.showShort("请重新输入");
                    return;
                }
                if(typeSet==1){
                    eidtPwd();
                }else {
                    setPwd();

                }
                break;

        }
    }

    private void eidtPwd() {
        //修改密码
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id",MyAPP.getUserId());
        defMap.put("phone",nowPhone);
        defMap.put("code",smsCodeView.getCode());
        defMap.put("newpassword1",edPwd.getText().toString());
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_UPDATE, defMap, new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                        MyAPP.setmUserBean(result.getData());
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_OK, intent);
                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                        finish();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort("验证码错误");
                    }
                });
    }


    private void setPwd() {
        if(edPwd.getText().toString().equals(edPwdTwo.getText().toString())){
            Map<String, String> defMap = MapUtils.getDefMap(true);
            defMap.put("id", MyAPP.getUserId());
            defMap.put("password",edPwd.getText().toString());
            defMap.put("newpassword1",edPwd.getText().toString());
            OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_EDIT, defMap, new
                    NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
                        @Override
                        public void onSuccess(StringDataV2Bean result) {
                            UserBean.DataBean dataBean = MyAPP.getmUserBean();
                            dataBean.setPassword(edPwd.getText().toString());
                            MyAPP.setmUserBean(dataBean);
                            Intent intent = new Intent();
                            setResult(Activity.RESULT_OK, intent);
                            // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                            finish();
                        }
                        @Override
                        public void onError(String msg, int code) {
                            ToastUitl.showShort(msg);
                        }
                    });
        }else {
            ToastUitl.showShort("两次密码不一致");
            return;
        }

    }


}
