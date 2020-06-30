package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

public class SetPhoneActivity extends BaseActivity {

    @Bind(R.id.smsCodeView)
    SmsCode2View smsCodeView;
    @Bind(R.id.smsCodeView2)
    SmsCode2View smsCodeView2;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.ed_phone)
    EditText edPhone;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntb;
    private String type;
    private Map<String, String> oldDefMap;
    private String phone;

    public static void startAction(String type,int code) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, SetPhoneActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,code);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_phone;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        smsCodeView.setTvPhone(edPhone);
        smsCodeView2.setTvPhone(edPhone);
         phone = PrefUtils.getString("phone", "", mContext);
        smsCodeView.setbg(phone);
        smsCodeView2.setbg("");
         String  mPhone = phone.substring(0, 3) + "****" + phone.substring(7, 11);
        edPhone.setText(mPhone);
        edPhone.setEnabled(false);
        smsCodeView.setOnSmsCallBack(new SmsCode2View.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> map) {
                getSmsCode(map,smsCodeView);
            }
        });
        smsCodeView2.setOnSmsCallBack(new SmsCode2View.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> map) {
                getSmsCode(map,smsCodeView2);
            }
        });
        ntb.setTitleText("修改手机号");
    }
    private void getSmsCode(Map<String, String> codeMap, SmsCode2View smsCodeView) {
        codeMap.put("type","3");
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
                if(tvSave.getText().toString().equals("下一步")){
                    phoneVerCode();
                }else{
                    editPhone();
                }
                break;

        }
    }

    private void phoneVerCode() {
        oldDefMap = MapUtils.getDefMap(true);
        oldDefMap.put("code",smsCodeView.getCode());
        oldDefMap.put("phone",phone);
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_VERIFICATION, oldDefMap, new
                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
                    @Override
                    public void onSuccess(StringDataV2Bean result) {
                        if(result.getData()){
                            tvSave.setText("绑定");
                            tvSave.setBackgroundResource(R.drawable.normal_bg_bule_px);
                            edPhone.setEnabled(true);
                            edPhone.setText("");
                            smsCodeView.setVisibility(View.GONE);
                            smsCodeView2.setVisibility(View.VISIBLE);
                        }else {
                            ToastUitl.showShort(result.getResultMsg());
                        }
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });

    }

    private void editPhone() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id",MyAPP.getUserId());
        defMap.put("phone",oldDefMap.get("phone"));
        defMap.put("code",oldDefMap.get("code"));
        defMap.put("newPhone",edPhone.getText().toString());
        defMap.put("newCode",smsCodeView2.getCode());
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_UPDATEUSERPHONE, defMap, new
                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
                    @Override
                    public void onSuccess(StringDataV2Bean result) {
                        ToastUitl.showShort(result.getResultMsg());
//                        MyAPP.setmUserBean(result.getData());
//                        Intent intent = new Intent();
//                        intent.putExtra("newPhone",edPhone.getText().toString());
//                        setResult(Activity.RESULT_OK, intent);
//                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
//                        finish();
                        MyAPP.logout(mContext);

                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });

    }


}
