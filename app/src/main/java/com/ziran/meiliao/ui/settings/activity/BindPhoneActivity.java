package com.ziran.meiliao.ui.settings.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.citypicker.citylist.sortlistview.SortModel;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.SmsCodeView;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 绑定手机 on 2019/3/18.
 */

public class BindPhoneActivity extends CommonHttpActivity<LoginPresenter, LoginModel> implements LoginContract.View{


    SortModel mCityItem;
    private Dialog threeLogindialog;
    private Map<String, String> codeMap;
    @Bind(R.id.smsCodeView)
    SmsCodeView bindDialogSmsCodeView;

    @Bind(R.id.tv_input_phone)
    EditText etPhone;
    @Bind(R.id.tv_bind)
    TextView tvBind;
    private String shareString;
    private String unionid;

    /**
     * 入口
     */
    public static void startAction(Context activity, String shareString, String unionid) {
        Intent intent = new Intent(activity, BindPhoneActivity.class);
        intent.putExtra("shareString",shareString);
        intent.putExtra("unionid",unionid);
        activity.startActivity(intent);
    }



    //点击监听
    @OnClick({R.id.tv_bind})
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    private void getSmsCode(Map<String, String> codeMap) {
        this.codeMap = codeMap;
        codeMap.put("type","3");
        OkHttpClientManager.getAsyncMore(ApiKey.USER_GET_LOG_CODE, codeMap, new NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
            @Override
            protected void onSuccess(StringDataV2Bean result) {
                HandlerUtil.startDjs(bindDialogSmsCodeView.getTvSmsGetCode());
                tvBind.setBackgroundResource(R.drawable.normal_bg_bule);
                tvBind.setEnabled(true);

            }
            @Override
            public void onError(String msg, int code) {
                ToastUitl.showShort(msg);
            }
        });

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone_new;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void initView() {
        if(getIntent()!=null){
             shareString = getIntent().getStringExtra("shareString");
            unionid = getIntent().getStringExtra("unionid");
        }
        tvBind.setEnabled(false);
        bindDialogSmsCodeView.setTvPhone(etPhone);
        bindDialogSmsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> codeMap) {
                getSmsCode(codeMap);
            }
        });
        bindDialogSmsCodeView.bindBtn(tvBind);

        tvBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString();
                String code = bindDialogSmsCodeView.getCode();
                if (RegexUtils.regex(phone, "notCheck", code, "+86")) {
                    Map<String, String> codeMap = MapUtils.getSmsMap( phone, code,1);
                    if(shareString.equals("WX")){
                        codeMap.put("wxOpenid",unionid);
                    }else {
                        codeMap.put("qqOpenid",unionid);
                    }
                    startProgressDialog("正在绑定");
                    OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_BINGDINGUSERPHONE, codeMap, new NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
                        @Override
                        protected void onSuccess(StringDataV2Bean result) {
                            LoadingDialog.cancelDialogForLoading();
                            if (result.getResultCode()==0){
                                Map<String, String> defMap = MapUtils.getDefMap(false);
                                defMap.put("grant_type", "mobile");
                                defMap.put("mobile", shareString + "@" + unionid);
                                mPresenter.postBindCode(defMap);

                            }else {
                                ToastUitl.show(result.getResultMsg(),0);
                            }
                        }

                        @Override
                        public void onError(String msg, int code) {
                            super.onError(msg, code);
                            LoadingDialog.cancelDialogForLoading();
                            ToastUitl.showShort(msg);
                        }
                    });
                }
            }
        });
    }


    @Override
    public void returnLoginData(LoginBean registerBean) {

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
        mPresenter.getUserInfo(MyAPP.getUserId(),loginBean.getAccess_token());
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
            //跳转到主界面
        } else {
            InputUserInfoActivity.startAction(mContext);
        }
    }

    @Override
    public void showLoginCheck(CheckPhoneBean result) {

    }

    @Override
    public void showCheckSaveData(TagCheckBean data) {

    }
}
