package com.ziran.meiliao.utils;

import android.app.Activity;
import android.content.Context;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.NetWorkUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.VersionBean;
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
    private static VersionBean sVersionBean;

    public static VersionBean getVersionBean() {
        return sVersionBean;
    }

    public static void setVersionBean(VersionBean versionBean) {
        sVersionBean = versionBean;
        ServiceDialogUtil.setServicePhone(versionBean.getData().getServicePhone());
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
        } else checkUpdate(!WpyxConfig.isCheckUpdate(), false);
    }

    private boolean needToast = false;

    /**
     * 检测软件更新
     */
    public void checkUpdate(boolean needCheck, boolean needToast) {
        this.needToast = needToast;
        if (NetWorkUtils.isNetConnected(MyAPP.getContext()) && needCheck) {
            isUpdate();
            WpyxConfig.setIsCheckUpdateVersion(true);
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
        defMap.put("version", currentVersion);
        defMap.put("platform", "android");
        OkHttpClientManager.getAsync(ApiKey.VERSION, defMap, new NewRequestCallBack<VersionBean>(VersionBean.class) {
            @Override
            public void onSuccess(final VersionBean result) {
                showCheckVersionResult(result);
            }

            @Override
            public void onError(String msg, int code) {
                super.onError(msg, code);
                LoadingDialog.cancelDialogForLoading();
            }
        });
    }

    private void showCheckVersionResult(final VersionBean result) {
        LoadingDialog.cancelDialogForLoading();
        if (result.getData().isIsNew()) {
            // 显示提示对话框
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    WpyxConfig.setLastVersion(result.getData().getLastestVersion());
                    showNoticeDialog(result.getData().getUrl(), result.getData().getContent());
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

    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog(final String downUrl, final String content) {
        UpdateVersionPopupWindow popupwindow = new UpdateVersionPopupWindow(mContext);
        popupwindow.setDownloadUrl(downUrl);
        popupwindow.setContent(content);
        PopupWindowUtil.show(popupwindow);
    }


}
