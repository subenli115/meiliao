package com.ziran.meiliao.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonWebActivity;

/**
 * author admin
 * create  2017/3/28 14
 * des     ${用户协议页面或充值协议页面}
 * <p>
 * updateAuthor     $admin
 * updateDate   2017/3/28 14
 * updateDes    ${TODO}
 */

public class UserAgreementWebActivity extends CommonWebActivity {


    public static void startAction(Context context, String url, String title) {
        Intent intent = new Intent(context, UserAgreementWebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString(AppConstant.ExtraKey.EXTRAS_TITLE, title);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        super.initView();
        setShareViewGone(false, false);
    }

    @Override
    protected void initBundle(Bundle extras) {
        super.initBundle(extras);
        if (extras.containsKey(AppConstant.ExtraKey.EXTRAS_TITLE)) {
            setMyTitle(extras.getString(AppConstant.ExtraKey.EXTRAS_TITLE));
        } else {
            setMyTitle("用户协议");
        }
    }
}
