package com.ziran.meiliao.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentTransaction;

import com.citypicker.citylist.sortlistview.SortModel;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.permission.PermissionUtil;
import com.ziran.meiliao.ui.base.PermissionActivity;
import com.ziran.meiliao.ui.main.fragment.LoginFragment;
import com.umeng.socialize.UMShareAPI;

import butterknife.Bind;

/**
 * 登录界面
 * Created by Administrator on 2016/12/26.
 */

public class LoginActivity extends PermissionActivity implements RadioGroup.OnCheckedChangeListener {
    //登录Fragment
    LoginFragment loginFragment;
    //注册Fragment
    //切换登录与注册的RadioGroup
    @Bind(R.id.rg_login)
    RadioGroup radioGroup;
    @Bind(R.id.iv_login_bg)
    ImageView iv_bg;
    public String platName;
    private LoginFragment.ThreeLoginBean mThreeLoginBean;

    public String jsonData;

    public static void startAction(Context mContext) {
        try{
            Intent intent = new Intent(mContext, NewLoginActivity.class);
            mContext.startActivity(intent);
        }catch(Exception e){
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.act_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void initView() {
        radioGroup.setOnCheckedChangeListener(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        loginFragment = LoginFragment.newInstance(null, null);
        ft.add(R.id.fl_login_container, loginFragment, "login");
        ft.commit();
        setPermission(PermissionUtil.getSMS());
        DisplayUtil.measureSoftKeyBoardHeight(this);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_register:
                showRegisterFragment(mThreeLoginBean);
                break;
            case R.id.rb_login:
                showLoginFragment();
                break;
        }
    }

    public void showRegisterFragment(LoginFragment.ThreeLoginBean flag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(loginFragment);
        ft.commit();
    }

    public void showLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.show(loginFragment);
        ft.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyAPP.setReqPerOk(false);
        UMShareAPI.get(this).release();
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    //从区号选择页面返回数据处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            SortModel cityData = data.getParcelableExtra("cityData");
            if (EmptyUtils.isNotEmpty(cityData)) {
                loginFragment.setAreaCode(cityData);
            } else {
                UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (MyAPP.isLogout) {
//            MainActivity.startAction(this);
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
