package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.citypicker.citylist.sortlistview.SortModel;
import com.citypicker.citylist.utils.CityDataDb;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.permission.PermissionManager;
import com.ziran.meiliao.common.permission.PermissionUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.ui.main.activity.RegionActivity;
import com.ziran.meiliao.ui.main.contract.ForgetContract;
import com.ziran.meiliao.ui.main.model.ForgetModel;
import com.ziran.meiliao.ui.main.presenter.ForgetPresenter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.PhoneCodeView;
import com.ziran.meiliao.widget.SmsCodeView;

import java.util.Map;

import butterknife.Bind;


/**
 * 忘记密码界面
 * Created by Administrator on 2017/1/4.
 */

public class ForgetPwdActivity extends BaseActivity<ForgetPresenter, ForgetModel> implements ForgetContract.View {

    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    @Bind(R.id.phone_code_view)
    PhoneCodeView mPhoneCodeView;
    @Bind(R.id.et_new_pwd)
    EditText etPwd;

    @Bind(R.id.bt_forget_pwd_finish)
    Button ben_reset;

    @Bind(R.id.smsCodeView)
    SmsCodeView smsCodeView;
    //从忘记密码跳转来的的
    public static final int FROM_FORGET_PWD = 0;
    //从修改密码跳转来的
    public static final int FROM_UPDATE_PWD = 1;
    //从哪个界面跳转过来
    int from;
    //权限管理器,用于获取短信自动输入
    PermissionManager permissionManager;

    /**
     * 入口
     */
    public static void startAction(Context activity, int from) {
        Intent intent = new Intent(activity, ForgetPwdActivity.class);
        intent.putExtra(AppConstant.KEY_EDIT_USERINFO_FROM, from);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        //获取从哪个界面跳转过来的
        from = getIntent().getIntExtra(AppConstant.KEY_EDIT_USERINFO_FROM, FROM_UPDATE_PWD);
        //设置标题栏的数据
        ntb.setTvLeftVisiable(true, false);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyAPP.isLogout) {
                    NewLoginActivity.startAction(ForgetPwdActivity.this);
                } else {
                    finish();
                }
            }
        });
        if (from == FROM_FORGET_PWD) {
            ntb.setTitleText(getString(R.string.forget_pwd));
        } else {
            ntb.setTitleText(getString(R.string.updata_pwd));
        }
        mCityItem = CityDataDb.getCountryZipCode(this);
        mPhoneCodeView.setPhoneText(SPUtils.getString(AppConstant.SPKey.PHONE));
        mPhoneCodeView.setTvAreaCodeClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivityForResult(new Intent(ForgetPwdActivity.this, RegionActivity.class), 100);
            }
        });
        smsCodeView.setTvPhone(mPhoneCodeView.getEtPhone());
        smsCodeView.setOnSmsCallBack(new SmsCodeView.OnSmsCallBack() {
            @Override
            public void call(int type, Map<String, String> map) {
                if (!CheckUtil.checkNet(ForgetPwdActivity.this, ntb)) return;
                map.put("areaCode", mCityItem.getCodeNumber());
                mPresenter.postForgetCode(map);
            }
        });
        smsCodeView.setTvPwd(etPwd);
        smsCodeView.setCodeNumberCallBack(new SmsCodeView.CodeNumberCallBack() {
            @Override
            public String getCodeNumber() {
                return mCityItem != null ? mCityItem.getCodeNumber() : "";
            }
        });
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
        //执行网络请求修改密码
        ben_reset.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                String phone = mPhoneCodeView.getPhoneText();
                String code = smsCodeView.getCode();
                String pwd = ViewUtil.getText(etPwd);
                //检查是否填写完整数据
                if (!RegexUtils.regex(phone, pwd, code,mCityItem.getCodeNumber())) {
                    return;
                }
                Map<String, String> resMap = MapUtils.getResetMap(phone, code, pwd);
                mPresenter.postForgetRequest(resMap);
            }
        });
    }

    SortModel mCityItem;

    //请求成功,关闭界面
    @Override
    public void returnForgetData(Result result) {
        ToastUitl.showShort(result.getResultMsg() + getString(R.string.login_algin));
        MyAPP.logout(this);
    }

    //获取验证码成功
    @Override
    public void returnForgetCode(Result result) {
        ToastUitl.showShort(R.string.getcode_ok);
        smsCodeView.startDjs();
        ben_reset.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null && resultCode == RESULT_OK){
            SortModel cityData = data.getParcelableExtra("cityData");
            if (EmptyUtils.isNotEmpty(cityData)) {
                setAreaCode(cityData);
            }
        }
    }

    public void setAreaCode(SortModel cityItem) {
        if (EmptyUtils.isEmpty(cityItem)) return;
        this.mCityItem = cityItem;
        mPhoneCodeView.setAreaCode(mCityItem.getData());
    }

    //判断请求权限是否成功
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        if (MyAPP.isLogout) {
            NewLoginActivity.startAction(this);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        permissionManager.onDestroy();
        MyAPP.setReqPerOk(false);
    }
}
