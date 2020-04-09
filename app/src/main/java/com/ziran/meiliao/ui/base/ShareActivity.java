package com.ziran.meiliao.ui.base;

import android.content.Intent;
import android.os.Bundle;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.StringUtils;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;


/**
 * author 吴祖清
 * create  2017/3/13.
 * des     友盟分享时回调和页面关闭时释放资源的监听
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public abstract class ShareActivity<T extends BasePresenter, E extends BaseModel> extends BaseActivity<T, E> implements UMShareListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareUtil.addCallBack(this);
    }

    //分享回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    //释放资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        stopProgressDialog();
        ShareUtil.removeCallBack(this);
        UMShareAPI.get(this).release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isShowDialog) {
            stopProgressDialog();
            isShowDialog = false;
        }
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        showTips(null, StringUtils.getText(R.string.share_ok));
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        showTips(share_media, throwable.getMessage());
    }

    protected boolean isShowDialog;

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        showTips(share_media, StringUtils.getText(R.string.share_cancel));
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    void showTips(SHARE_MEDIA share_media, String text) {
        if (!isPause) {
            if (share_media == null) {
                ToastUitl.showShort(text);
            } else if (text.contains("2008")) {

                String shareMedia = share_media.toString();
                if (shareMedia.contains("WEIXIN")) {
                    HandlerUtil.sendToast(StringUtils.format("分享失败,您的手机尚未安装%s", "微信"));
                } else if (shareMedia.contains("QQ")) {
                    HandlerUtil.sendToast(StringUtils.format("分享失败,您的手机尚未安装%s", "QQ"));
                } else if (shareMedia.contains("SINA")) {
                    HandlerUtil.sendToast(StringUtils.format("分享失败,您的手机尚未安装%s", "新浪微博"));
                }
            } else {
                ToastUitl.showShort("分享失败");
            }
        }
        stopProgressDialog();
        isShowDialog = false;
    }
}
