package com.ziran.meiliao.envet;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * 第三方登录(授权)监听
 * Created by Administrator on 2017/3/13.
 */

public class ThreeLoginCallBack implements UMAuthListener {

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {

    }
}
