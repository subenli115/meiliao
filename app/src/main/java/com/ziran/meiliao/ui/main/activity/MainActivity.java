package com.ziran.meiliao.ui.main.activity;

import android.app.Activity;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.ui.bean.AdvertBean;


/**
 * des:主界面
 * Created by xsf
 * on 2016.09.15:32
 */


public class MainActivity{
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MainNewActivity.class);
        activity.startActivity(intent);
        if (MyAPP.isLogout()) {
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }else{
            activity.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        }
    }

    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity, int from) {
        Intent intent = new Intent(activity, MainNewActivity.class);
        activity.startActivity(intent);
    }

    public static void startAction(Activity activity,AdvertBean.DataBean dataBean) {
        Intent intent = new Intent(activity, MainNewActivity.class);
        intent.putExtra("skip",dataBean);
        activity.startActivity(intent);
    }
}