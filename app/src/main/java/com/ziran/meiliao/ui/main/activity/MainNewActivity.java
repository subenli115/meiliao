package com.ziran.meiliao.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.OneKeyLoginListener;
import com.chuanglan.shanyan_sdk.listener.OpenLoginAuthListener;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.entry.LoginBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.CheckPhoneBean;
import com.ziran.meiliao.ui.bean.TagCheckBean;
import com.ziran.meiliao.ui.bean.TokenBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.contract.LoginContract;
import com.ziran.meiliao.ui.main.model.LoginModel;
import com.ziran.meiliao.ui.main.presenter.LoginPresenter;
import com.ziran.meiliao.ui.settings.activity.IntputCodeActivity;
import com.ziran.meiliao.utils.ExitUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.app.MyAPP.mServiceManager;


/**
 * des:主界面
 * Created by xsf
 * on 2016.09.15:32
 */
public class MainNewActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {


    private MainNewActivity activity;
    private Intent intent;
    private TokenBean bean;
    private View contentView;

    @Bind(R.id.main)
    AutoLinearLayout rlBg;

    @Override
    public int getLayoutId() {
        return R.layout.act_main_new;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        activity = this;
//        OneKeyLoginManager.getInstance().setAuthThemeConfig(ConfigUtils.getCJSConfig(getApplicationContext(),activity));
        openLoginActivity();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }


    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
    }

    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_user_privacy, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(rlBg, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
        TextView close = contentView.findViewById(R.id.tv_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitUtil.exit(mContext);
            }
        });
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void openLoginActivity() {
        //拉授权页方法
        intent = new Intent();
        OneKeyLoginManager.getInstance().openLoginAuth(false, new OpenLoginAuthListener() {
            @Override
            public void getOpenLoginAuthStatus(int code, String result) {
                if (1000 == code) {
                    //拉起授权页成功
                    intent.setClass(mContext, PrivatePopActivity.class);
                    startActivity(intent);
                } else {
                    //拉起授权页失败
                    intent.putExtra("Connected", "false");
                    intent.setClass(mContext, IntputCodeActivity.class);
                    startActivity(intent);
                }
            }
        }, new OneKeyLoginListener() {
            @Override
            public void getOneKeyLoginStatus(int code, String result) {
                if (1011 == code) {
                    Log.e("VVV", "用户点击授权页返回： _code==" + code + "   _result==" + result);
                    return;
                } else if (1000 == code) {
                    Log.e("VVV", "用户点击登录获取token成功： _code==" + code + "   _result==" + result);
                    Map<String, String> defMap = MapUtils.getDefMap(false);
                    Gson gson = new Gson();
                    bean = gson.fromJson(result, TokenBean.class);
                    defMap.put("token", bean.getToken());
                    mPresenter.postCheckLoginPhone(defMap);
                } else {
                    Log.e("VVV", "用户点击登录获取token失败： _code==" + code + "   _result==" + result);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //销毁时执行
    @Override
    protected void onDestroy() {
        FileUtil.deleteGlideCache();
        mServiceManager.stop();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        ExitUtil.keyBack(this, new ExitUtil.OnExitCallBack() {
            @Override
            public void exit(Activity context) {
                ExitUtil.exit(MainNewActivity.this);
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
    public void returnBindCode(LoginBean registerBean) {

    }

    @Override
    public void returnPartyLogin(LoginBean registerBean) {

    }

    @Override
    public void showPwdLogin(LoginBean result) {

    }

    @Override
    public void showUserInfo(UserBean result) {

    }

    @Override
    public void showLoginCheck(CheckPhoneBean result) {
        login(result);
    }

    @Override
    public void showCheckSaveData(TagCheckBean data) {

    }

    private void login(CheckPhoneBean result) {
        Map<String, String> loginMap = MapUtils.getLoginMap(result.getData().getMobile(), result.getData().getMobileCode(), null);
        mPresenter.postLoginRequest(loginMap);
    }
}
