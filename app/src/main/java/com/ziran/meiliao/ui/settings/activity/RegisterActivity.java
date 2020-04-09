package com.ziran.meiliao.ui.settings.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.citypicker.citylist.sortlistview.SortModel;
import com.citypicker.citylist.utils.CityDataDb;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.permission.PermissionManager;
import com.ziran.meiliao.common.permission.PermissionUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.dao.UserInfoDao;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.entry.UserInfo;
import com.ziran.meiliao.ui.main.activity.MainActivity;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.ui.main.activity.RegionActivity;
import com.ziran.meiliao.ui.main.contract.RegisterContract;
import com.ziran.meiliao.ui.main.model.RegisterModel;
import com.ziran.meiliao.ui.main.presenter.RegisterPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.SmsCodeView;
import com.umeng.socialize.UMShareAPI;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/13.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter, RegisterModel> implements RegisterContract.View{

    @Bind(R.id.tv_input_phone)
    EditText et_phone;
    @Bind(R.id.scv_register)
    SmsCodeView smsCodeView;
    @Bind(R.id.tv_86)
    TextView tv86;
    @Bind(R.id.tv_input_password)
    EditText etPwd;
    @Bind(R.id.all_register)
    AutoLinearLayout allRegister;
    private SortModel mCityItem;
    private String phone;
    private PermissionManager permissionManager;

    @Override
    public void returnRegisterData(LoginBean registerBean) {
        UserInfo userInfo = registerBean.getData().getUserInfo();
        //讲token保存到偏好设置里
        SPUtils.setString(AppConstant.SPKey.TOKEN, registerBean.getData().getAccessToken());
        WpyxConfig.setPhone(et_phone.getText().toString());
        SPUtils.setString(AppConstant.SPKey.PHONE, et_phone.getText().toString());
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
        //跳转到主界面
        //跳转到主界面
        MainActivity.startAction(this, 1);
        if (!MyAPP.isLogout()) {
            mRxManager.post(AppConstant.RXTag.UPDATE_USER, "login");
        }
        finish();
    }
    private void register() {

        phone =et_phone.getText().toString();
        String pwd = etPwd.getText().toString();
        if(tv86.getText().toString().equals("+86")){
            if(et_phone.getText().toString().length()!=11){
                ToastUitl.showShort("手机号码不正确");
                return;
            }
            String number = et_phone.getText().toString();
            boolean judge = NewLoginActivity.isMobile(number);
            if (judge == true) {
            } else {
                ToastUitl.showShort("手机号码不合法");
                return;
            }
        }
        String code = smsCodeView.getCode();
        if (!RegexUtils.regex(phone, pwd, code, mCityItem.getCodeNumber())) {
            return;
        }
        Map<String, String> registMap = MapUtils.getRegisterMap(mCityItem.getCodeNumber(), phone, code, pwd);
        mPresenter.postRegisterRequest(registMap);
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
    @Override
    public void returnRegisterCode(Result registerBean) {
        if (registerBean.getResultCode() == 10) {
            ToastUitl.showShort(registerBean.getResultMsg());

        } else {
            ToastUitl.showShort(R.string.getcode_ok);
            smsCodeView.startDjs();
            allRegister.setEnabled(true);
        }
    }
    //从区号选择页面返回数据处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            SortModel cityData = data.getParcelableExtra("cityData");
            if (EmptyUtils.isNotEmpty(cityData)) {
                setAreaCode(cityData);

            } else {
                UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
            }
        }
    }
    public void setAreaCode(SortModel cityItem) {
        if (EmptyUtils.isEmpty(cityItem)) return;
        this.mCityItem = cityItem;
        if (EmptyUtils.isNotEmpty(mCityItem.getData())) {
            tv86.setText((mCityItem.getData()));
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }
    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(this, getString(R.string.is_register), true);
    }
    //点击监听
    @OnClick({R.id.tv_86,R.id.iv_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_86:
                startActivityForResult(new Intent(getBaseContext(), RegionActivity.class), 100);
                break;
            case R.id.iv_close:
                finish();
                break;
        }
    }
    private NewLoginActivity.ThreeLoginBean mThreeLoginBean;
    @Override
    public void stopLoading() {
        LoadingDialog.cancelDialogForLoading();
    }
    @Override
    public void initView() {
        smsCodeView.setTvPhone(et_phone);
        mCityItem = CityDataDb.getCountryZipCode(this);
        mCityItem.setCodeNumber(tv86.getText().toString());
        et_phone.setText(SPUtils.getString(AppConstant.SPKey.PHONE));
        smsCodeView.setCodeNumberCallBack(new SmsCodeView.CodeNumberCallBack() {
            @Override
            public String getCodeNumber() {
                return mCityItem != null ? mCityItem.getCodeNumber() : "";
            }
        });
        //注册点击获取验证码监听
        smsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> codeMap) {
                codeMap.put("areaCode", tv86.getText().toString());
                if (EmptyUtils.isNotEmpty(mThreeLoginBean)) {
                    codeMap.put("jsonData", mThreeLoginBean.getJsonData());
                    codeMap.put("platName", mThreeLoginBean.getPlatName());
                    codeMap.put("third", mThreeLoginBean.getThird());
                }
                mPresenter.postRegisterCode(codeMap);
            }
        });
        smsCodeView.setTvPwd(etPwd);
        //创建权限管理器
        permissionManager = new PermissionManager(this);
        permissionManager.setmAccessToSuccess(new PermissionManager.AccessToSuccess() {
            @Override
            public void onSuccess() {
                MyAPP.setReqPerOk(true);
            }
        });
        //检查是否拥有短信管理的权限
        permissionManager.setPermission(true, PermissionUtil.getSMS());
        etPwd.setOnEditorActionListener(mOnEditorActionListener);
        allRegister.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                register();
            }
        });
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
}
