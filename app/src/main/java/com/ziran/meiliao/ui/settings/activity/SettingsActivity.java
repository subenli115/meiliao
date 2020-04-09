package com.ziran.meiliao.ui.settings.activity;

import android.app.Dialog;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.utils.DeviceUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.UpdateManager;
import com.ziran.meiliao.widget.ItemGroupView;
import com.ziran.meiliao.widget.SwitchButton;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.SimpleDialogClickListener;

import butterknife.Bind;
import butterknife.OnClick;


public class SettingsActivity extends ShareActivity {

    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.itemview_phone)
    ItemGroupView mItemViewPhone;
    @Bind(R.id.itemView_clear_cache)
    ItemGroupView mItemGroupViewClearCache;
    @Bind(R.id.itemView_update_version)
    ItemGroupView mItemGroupViewUpdateVersion;

    @Bind(R.id.itemView_message)
    ItemGroupView mItemGroupView_message;
    SharePopupWindow mSharePopupWindow;


    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MyAPP.getUserInfo().getPhone()!=null){
            mItemViewPhone.setRigthText(MyAPP.getUserInfo().getPhone());
        }
    }

    @Override
    public void initView() {
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(getString(R.string.settings_title));
        FileUtil.setCache(mItemGroupViewClearCache.getRightTextView());
        String versionName = DeviceUtil.getVersionName(this);
        mItemGroupViewUpdateVersion.setRigthText(String.format("当前版本%s", versionName));
        mItemGroupView_message.setSlideListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SPUtils.setBoolean(AppConstant.SPKey.MESSAGE_PUSH, isChecked);
            }
        });
    }


    @OnClick({R.id.itemView_update_pwd, R.id.itemView_clear_cache, R.id.itemView_update_version, R.id
            .bt_settings_logout, R.id.itemView_about,R.id.itemview_phone})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.itemView_update_pwd:
                ForgetPasswordActivity.startAction(SettingsActivity.this, ForgetPwdActivity.FROM_UPDATE_PWD);
                break;
            case R.id.itemView_clear_cache:
                clearCache();
                break;

            case R.id.bt_settings_logout:
                MDAlertDialog.createDialog(this, getString(R.string.confirm_logout), new SimpleDialogClickListener() {
                    @Override
                    public void clickRightButton(Dialog dialog, View view) {
                        dialog.dismiss();
                        MyAPP.logout(SettingsActivity.this);
                    }
                });
                break;
            case R.id.itemView_update_version:
                updateVersion(view);
                break;
            case R.id.itemView_about:
                startActivity(AboutActivity.class);
                break;
            case R.id.itemview_phone:

                startActivity(ChangePhoneActivity.class);
                break;
        }
    }

    private void updateVersion(final View view) {
        new UpdateManager(this).checkUpdate(true, true);
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 3000);
        view.setEnabled(false);
    }



    private void clearCache() {
        MDAlertDialog.createDialog(this, getString(R.string.dialog_warning), getString(R.string.dialog_content_clear_cache), new
                SimpleDialogClickListener() {
            @Override
            public void clickRightButton(Dialog dialog, View view) {
                dialog.dismiss();
                FileUtil.deleteDef();
                mItemGroupViewClearCache.setRigthText("0.00 B");
            }
        });
    }


    @Override
    public void onResult(SHARE_MEDIA share_media) {
        super.onResult(share_media);
        if (mSharePopupWindow != null) {
            mSharePopupWindow.dismiss();
        }
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
//        startProgressDialog("加载中");
    }
}
