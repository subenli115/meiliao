package com.ziran.meiliao.ui.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.citypicker.citylist.sortlistview.SortModel;
import com.citypicker.citylist.utils.CityDataDb;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.dao.UserInfoDao;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.entry.UserInfo;
import com.ziran.meiliao.ui.main.activity.LoginActivity;
import com.ziran.meiliao.ui.main.activity.RegionActivity;
import com.ziran.meiliao.ui.main.activity.UserAgreementWebActivity;
import com.ziran.meiliao.ui.main.contract.RegisterContract;
import com.ziran.meiliao.ui.main.model.RegisterModel;
import com.ziran.meiliao.ui.main.presenter.RegisterPresenter;
import com.ziran.meiliao.ui.settings.activity.UserInfoActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.CustomEditText;
import com.ziran.meiliao.widget.PhoneCodeView;
import com.ziran.meiliao.widget.SmsCodeView;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 整理界面注册Fragment
 */
public class RegisterFragment extends BaseFragment<RegisterPresenter, RegisterModel> implements RegisterContract.View {


    @Bind(R.id.phone_code_view)
    PhoneCodeView mPhoneCodeView;
    @Bind(R.id.et_register_pwd)
    CustomEditText etPwd;
    @Bind(R.id.scv_register)
    SmsCodeView smsCodeView;
    @Bind(R.id.bt_register_register)
    Button bt_register_register;
    LoginActivity loginActivity;

//    @Override
//    protected void onFragmentVisibleChange(boolean isVisible) {
//        if (isVisible && bt_register_register != null) {
//            loginActivity.setKeyboardHeightLayoutViewGroup(bt_register_register);
//        }
//    }

    public static RegisterFragment newInstance(String data1, String data2) {
        RegisterFragment registerFragment = new RegisterFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data1", data1);
        bundle.putString("data2", data2);
        registerFragment.setArguments(bundle);
        return registerFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_register;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    private String phone;

    @Override
    protected void initView() {
        loginActivity = (LoginActivity) getActivity();
        smsCodeView.setTvPhone(mPhoneCodeView.getEtPhone());
        smsCodeView.setTvPwd(etPwd);

        mSortModel = CityDataDb.getCountryZipCode(getContext());
        mPhoneCodeView.setPhoneText(SPUtils.getString(AppConstant.SPKey.PHONE));
        mPhoneCodeView.setTvAreaCodeClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                getActivity().startActivityForResult(new Intent(getContext(), RegionActivity.class), 100);
            }
        });
        //注册点击获取验证码监听
        smsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> codeMap) {
                codeMap.put("areaCode", mSortModel.getCodeNumber());
                if (EmptyUtils.isNotEmpty(mThreeLoginBean)) {
                    codeMap.put("jsonData", mThreeLoginBean.getJsonData());
                    codeMap.put("platName", mThreeLoginBean.getPlatName());
                    codeMap.put("third", mThreeLoginBean.getThird());
                }
                mPresenter.postRegisterCode(codeMap);
            }
        });
        smsCodeView.setCodeNumberCallBack(new SmsCodeView.CodeNumberCallBack() {
            @Override
            public String getCodeNumber() {
                return mSortModel != null ? mSortModel.getCodeNumber() : "";
            }
        });
        bt_register_register.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                register();
            }
        });

        etPwd.setOnEditorActionListener(mOnEditorActionListener);
    }

    private void register() {
        phone = mPhoneCodeView.getPhoneText();
        String pwd = etPwd.getText().toString();
        String code = smsCodeView.getCode();
        if (!RegexUtils.regex(phone, pwd, code, mSortModel.getCodeNumber())) {
            return;
        }
        Map<String, String> registMap = MapUtils.getRegisterMap(mSortModel.getCodeNumber(), phone, code, pwd);
//        if (EmptyUtils.isNotEmpty(loginActivity.getJsonData())) {
//            registMap.put("platName", loginActivity.getPlatName());
//            registMap.put("jsonData", loginActivity.getJsonData());
//        }
        mPresenter.postRegisterRequest(registMap);
    }

    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId) {
                case EditorInfo.IME_ACTION_UNSPECIFIED :
                    register();
                    break;
            }
            return true;
        }
    };

    //注册成功后执行
    @Override
    public void returnRegisterData(LoginBean registerBean) {
        UserInfo userInfo = registerBean.getData().getUserInfo();
        //讲token保存到偏好设置里
        SPUtils.setString(AppConstant.SPKey.TOKEN, registerBean.getData().getAccessToken());
        WpyxConfig.setPhone(mPhoneCodeView.getPhoneText());
        SPUtils.setString(AppConstant.SPKey.PHONE, mPhoneCodeView.getPhoneText());
        //全局设置AccessToken
        MyAPP.setAccessToken(registerBean.getData().getAccessToken());
        //讲注册用户添加到数据库
        UserInfoDao userInfoDao = DbCore.getDaoSession().getUserInfoDao();
        userInfo.setId(1L);
        userInfoDao.insertOrReplace(userInfo);
        //加载数据库信息,添加到全局
        MyAPP.setmUserInfo(userInfo);
        mRxManager.post("exercise", "login");
        //跳转到完善用户信息界面
        UserInfoActivity.startAction(getContext(), 2);
    }

    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(getActivity(), getString(R.string.is_register), true);
    }

    @Override
    public void stopLoading() {
        LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void showErrorTip(String msg) {
        LoadingDialog.cancelDialogForLoading();
        ToastUitl.showShort(getString(R.string.register_error) + msg);
    }

    @Override
    public void showEmtry() {
        LoadingDialog.cancelDialogForLoading();
    }

    //获取短信验证码成功后执行
    @Override
    public void returnRegisterCode(Result registerBean) {
        if (registerBean.getResultCode() == 10) {
            ToastUitl.showShort(registerBean.getResultMsg());

        } else {
            ToastUitl.showShort(R.string.getcode_ok);
            smsCodeView.startDjs();
            bt_register_register.setEnabled(true);
        }
    }

    private LoginFragment.ThreeLoginBean mThreeLoginBean;

    public void setJsonData(LoginFragment.ThreeLoginBean i) {
        this.mThreeLoginBean = i;
        if (etPwd != null) {
            etPwd.setVisibility(EmptyUtils.isNotEmpty(i) ? View.GONE : View.VISIBLE);
        }
    }

    private SortModel mSortModel;

            public void setAreaCode(SortModel SortModel) {
        if (EmptyUtils.isEmpty(SortModel)) return;
        this.mSortModel = SortModel;
        mPhoneCodeView.setAreaCode(mSortModel.getData());
    }

    @OnClick(R.id.tv_register_user_xy)
    public void onViewClicked(View view) {
        String agreement = WpyxConfig.getUserAgreement();
        if (EmptyUtils.isNotEmpty(agreement)) {
            UserAgreementWebActivity.startAction(getContext(), agreement,"用户协议");
        }
    }
}
