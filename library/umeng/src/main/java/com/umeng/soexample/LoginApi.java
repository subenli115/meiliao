package com.umeng.soexample;

import android.app.Activity;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/13.
 */

public class LoginApi {

    private static LoginApi LOGINAPI = new LoginApi();

    public LoginApi() {
        umAuthListeners = new ArrayList<>();

    }

    public static LoginApi get() {
        return LOGINAPI;
    }

    public void login(Activity activity, SHARE_MEDIA shareMedia) {
        UMShareAPI.get(activity).getPlatformInfo(activity, shareMedia, authListener);
    }
    public void login(Activity activity, SHARE_MEDIA shareMedia,UMAuthListener authListener) {

        UMShareAPI.get(activity).getPlatformInfo(activity, shareMedia, authListener);
    }

    public void addCallBack(UMAuthListener listener) {
        if (!umAuthListeners.contains(listener))
            umAuthListeners.add(listener);
    }

    public void removeCallBack(UMAuthListener umAuthListener) {
        umAuthListeners.remove(umAuthListener);
    }

    private List<UMAuthListener> umAuthListeners;

    private UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            if (umAuthListeners.size() > 0) {
                for (int i = 0; i < umAuthListeners.size(); i++) {
                    umAuthListeners.get(i).onStart(platform);
                }
            }
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (umAuthListeners.size() > 0) {
                for (int i = 0; i < umAuthListeners.size(); i++) {
                    umAuthListeners.get(i).onComplete(platform, action, data);
                }
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            if (umAuthListeners.size() > 0) {
                for (int i = 0; i < umAuthListeners.size(); i++) {
                    umAuthListeners.get(i).onError(platform, action, t);
                }
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            if (umAuthListeners.size() > 0) {
                for (int i = 0; i < umAuthListeners.size(); i++) {
                    umAuthListeners.get(i).onCancel(platform, action);
                }
            }
        }
    };


}
