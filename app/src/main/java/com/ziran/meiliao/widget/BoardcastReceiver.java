package com.ziran.meiliao.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.jpush.android.service.PushService;

//自定义的接收器
public class BoardcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent pushintent=new Intent(context, PushService.class);//启动极光推送的服务
        context.startService(pushintent);
    }
}