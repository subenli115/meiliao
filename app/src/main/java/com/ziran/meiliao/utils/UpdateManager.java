package com.ziran.meiliao.utils;

import android.app.Activity;
import android.content.Context;

import com.ziran.meiliao.BuildConfig;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.VersionBean;
import com.ziran.meiliao.ui.bean.VersionNewBean;
import com.ziran.meiliao.ui.workshops.util.ServiceDialogUtil;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.UpdateVersionPopupWindow;

import java.util.Map;


/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     检查版本更新
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class UpdateManager {
    private Context mContext;
    private static VersionNewBean sVersionBean;

    public static VersionNewBean getVersionBean() {
        return sVersionBean;
    }

    public static void setVersionBean(VersionNewBean versionBean) {
        sVersionBean = versionBean;
    }

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate() {
        if (EmptyUtils.isNotEmpty(getVersionBean())) {
            showCheckVersionResult(getVersionBean());
        } else checkUpdate(!MeiliaoConfig.isCheckUpdate(), false);
    }

    private boolean needToast = false;

    /**
     * 检测软件更新
     */
    public void checkUpdate(boolean needCheck, boolean needToast) {
        this.needToast = needToast;
        if (NetWorkUtils.isNetConnected(MyAPP.getContext()) && needCheck) {
            isUpdate();
            MeiliaoConfig.setIsCheckUpdateVersion(true);
        }
    }


    /**
     * 检查软件是否有更新版本
     *
     * @return
     */
    private void isUpdate() {
        if (needToast) {
            LoadingDialog.showDialogForLoading((Activity) mContext, "正在检查更新", true);
        }
        final String currentVersion = DeviceUtil.getVersionName(mContext);
        Map<String, String> defMap = MapUtils.getDefMap(false);
        defMap.put("isNew", "0");
        defMap.put("type", "2");
        OkHttpClientManager.getAsync(ApiKey.ADMIN_APPVERSION_APPVERSION, defMap, new NewRequestCallBack<VersionNewBean>(VersionNewBean.class) {
            @Override
            public void onSuccess(final VersionNewBean result) {
                UpdateManager.setVersionBean(result);
                showCheckVersionResult(result);
            }

            @Override
            public void onError(String msg, int code) {
                super.onError(msg, code);
                LoadingDialog.cancelDialogForLoading();
            }
        });
    }

    private void showCheckVersionResult(final VersionNewBean result) {
        LoadingDialog.cancelDialogForLoading();
        if(result.getData()!=null){

            if (CheckUtil.compareVersion(BuildConfig.VERSION_NAME,result.getData().getAppVersion())==-1&& !SPUtils.getString("isVersionRemind").equals(result.getData().getAppVersion())) {
                // 显示提示对话框
                HandlerUtil.runMain(new Runnable() {
                    @Override
                    public void run() {
                        MeiliaoConfig.setLastVersion(result.getData().getAppVersion());
                        showNoticeDialog(result.getData().getAppVersion(),result.getData().getAddress(), result.getData().getUpdateContent(),result.getData().getIsUpdate());
                    }
                }, 200);
            } else {
                RxManagerUtil.post(AppConstant.RXTag.CONFERENCE_GET_CONFERENCE, true);
                if (needToast) {
                    needToast = false;
                    ToastUitl.showShort("已经是最新版本!");
                }
            }
        }
    }

    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog(String version, final String downUrl, final String content, int isUpdate) {
        UpdateVersionPopupWindow popupwindow = new UpdateVersionPopupWindow(mContext);
        popupwindow.setDownloadUrl(downUrl);
        popupwindow.setContent(content,version);
        popupwindow.setIsForceUpdate(isUpdate);
        PopupWindowUtil.show(popupwindow);
    }


}
