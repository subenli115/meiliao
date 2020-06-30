package com.ziran.meiliao.envet;

import android.util.Log;

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
        Log.e("onErroronErroronError1","3");

    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        Log.e("onErroronErroronError2",""+i);
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        Log.e("onErroronErroronError3",""+throwable.toString());
        throwable.printStackTrace();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        Log.e("onErroronErroronError4",""+i);
    }
}
