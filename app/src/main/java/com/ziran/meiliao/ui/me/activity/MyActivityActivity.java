package com.ziran.meiliao.ui.me.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.activity.UserAgreementWebActivity;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.DeviceUtil;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.ItemGroupView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的界面
 * Created by Administrator on 2017/1/4.
 */

public class MyActivityActivity extends BaseActivity {


    private View contentView;
    @Bind(R.id.ll_bg)
    RelativeLayout llBg;
    @Bind(R.id.tv_me_main_new_wallet)
    ItemGroupView itemWalletView;

    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, MyActivityActivity.class);
        if (!(mContext instanceof Activity)) { intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); }
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_more;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {


    }

    @Override
    protected void onResume() {
        super.onResume();
        UserBean.DataBean dataBean = MyAPP.getmUserBean();
        if(dataBean.getTeenagersIsOpen()!=null&&dataBean.getTeenagersIsOpen().equals("0")){
            //青少年模式
            itemWalletView.setVisibility(View.GONE);
        }
    }

    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];

        contentView = LayoutInflater.from(getBaseContext()).inflate(R.layout.pop_logout, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(llBg, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
        TextView qx = contentView.findViewById(R.id.tv_qx);
        setBackgroundAlpha(0.5f);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAPP.logout(mContext);
                popupWindow.dismiss();

            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });

    }
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = (this).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        llBg.setAlpha(bgAlpha);
    }

    //点击监听
    @OnClick({R.id.tv_me_main_new_info, R.id.tv_me_main_new_black, R.id.tv_me_main_new_wallet, R.id.tv_me_main_new_safe, R.id
            .tv_me_main_new_model, R.id.tv_me_main_new_version, R.id.tv_me_main_new_privacy, R.id
            .tv_me_main_new_agreement, R.id.tv_me_main_new_lsgy,R.id.tv_me_main_new_report,R.id.tv_me_main_new_follow, R.id.tv_logut})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        if (Utils.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_me_main_new_info:
                EditUserInfoActivity.startAction();
                break;
            case R.id.tv_me_main_new_black:
                UserBlackListActivity.startAction(mContext);
                break;
            case R.id.tv_me_main_new_wallet:
                RechargeActivity.startAction(mContext,"");
                break;
            case R.id.tv_me_main_new_safe:
                EditUserInfoSafeActivity.startAction();
                break;
            case R.id.tv_me_main_new_model:
                YouthModelActivity.startAction();
                break;
            case R.id.tv_me_main_new_version:
                ToastUitl.showShort( "当前版本"+DeviceUtil.getVersionName(this));
                break;
            case R.id.tv_me_main_new_privacy:
                String privacyUrl = MeiliaoConfig.getPrivacyUrl();
                UserAgreementWebActivity.startAction(mContext, privacyUrl, "用户隐私政策");
                break;
            case R.id.tv_me_main_new_agreement:
                String agreement = MeiliaoConfig.getUserAgreement();
                UserAgreementWebActivity.startAction(mContext, agreement, "用户服务协议");
                break;
            case R.id.tv_me_main_new_lsgy:
                String greenUrl = MeiliaoConfig.getGreenUrl();
                UserAgreementWebActivity.startAction(mContext, greenUrl, "绿色公约");
                break;

            case R.id.tv_logut:
                //退出登录
                showPopWindow();
                break;
        }
    }



}