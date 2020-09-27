package com.ziran.meiliao.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.IConstants;
import com.ziran.meiliao.receiver.NotificationBroadcast;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.SimpleDialogClickListener;

import java.util.Map;
import java.util.Random;

import rx.functions.Action1;


/**
 * author 吴祖清
 * create  2017/4/8 13
 * des     友盟推送和分享的初始化
 * <p>
 * updateAuthor     $admin
 * updateDate   2017/4/8 13
 * updateDes    ${TODO}
 */

public class UMengKit {

    public static UMessage oldMessage = null;

    /**
     * 初始化
     *
     * @param context 上下文对象
     */
    public static void init(final Context context) {


        UMConfigure.setLogEnabled(false);
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(context, "5e9905aa570df3fa1a0001ef", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "c802f1ca9d87419d1c9a43f788f9497e");
        //开启ShareSDK debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(context).setShareConfig(config);

//        final PushAgent mPushAgent = PushAgent.getInstance(context);
        //注册推送服务 每次调用register都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String deviceToken) {
//                Log.e("setDeviceToken",""+deviceToken);
//                MyAPP.setDeviceToken(deviceToken);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                Log.e("setDeviceToken",""+s+s1);
//            }
//        });
        //是否开启log日志打印(true 打印 ,FALSe 不打印 默认为true)
        //处理各种事件
//        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message message) {
//                Map map = StringUtils.getUrlParams(message.obj.toString());
//                switch (message.what) {
//                    case 1:
//                        break;
//                    case 4:
//                        break;
//                    case 5:
//                        break;
//                    case 6:
//                        //专栏
//                        String specialID = (String) map.get("specialID");
//                        String isBuy = (String) map.get("isBuy");
////                        ZhuanLanDetailActivity.startAction(context,specialID,isBuy,subscriptionBean.getHtmlLink(),mActivity,9,subscriptionBean.getSubscriptionNum());
//                        break;
//                    case 7:
//
//                        break;
//                    case 8:
////                        ActisData actisData = new ActisData();
////                        actisData.setUrl(message.obj.toString());
//////                        actisData.setActivityId(bean.getActivityId()+"");
////                        Intent intent = new Intent(context, GongZuoFangActivity.class);
////                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        intent.putExtra("recId",recId);
////                        context.startActivity(intent);
////                        GongZuoFangActivity.startAction(context,actisData);  //工作坊活动详情页面
//                        break;
//                }
//            }
//        };

//        //注册收到消息的事件处理
//        RxManagerUtil.on(AppConstant.RXTag.UMENG_MSG, new Action1<UMessage>() {
//            @Override
//            public void call(UMessage uMessage) {
//                if (oldMessage != null) {
//                    UTrack.getInstance(MyAPP.getContext()).setClearPrevMessage(true);
//                    UTrack.getInstance(MyAPP.getContext()).trackMsgDismissed(oldMessage);
//                }
//                if ( ! handlerEvent(handler, uMessage.extra, uMessage.after_open)){
//                    showNotification(uMessage);
//                }
//            }
//        });
        //sdk开启通知声音
//        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
//        UmengMessageHandler messageHandler = new UmengMessageHandler() {
//            @Override
//            public void dealWithNotificationMessage(Context context, UMessage uMessage) {
//                super.dealWithNotificationMessage(context, uMessage);
//            }
//
//
//    };
//        mPushAgent.setMessageHandler(messageHandler);
//        //设置通知栏显示的消息个数
//        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler(){
//            @Override
//            public void launchApp(Context context, UMessage uMessage) {
//                super.launchApp(context, uMessage);
//                String url = uMessage.extra.get("url");
//                if(  url.contains("wpyx://timing")){
//                    senMessageForInto(url, handler, 1);
//                }else if(url.contains("wpyx://interesting")){
//                    senMessageForInto(url, handler, 2);
//
//                }else  if(url.contains("wpyx://live")){
//                    senMessageForInto(url, handler, 3);
//
//                }else if(url.contains("wpyx://vipCenter")){
//                    senMessageForInto(url, handler, 4);
//
//                }else if(url.contains("wpyx://album")){
//                    senMessageForInto(url, handler, 5);
//
//                }else if(url.contains("wpyx://specialColumn")){
//                    senMessageForInto(url, handler, 6);
//
//                }else if(url.contains("wpyx://record/getRecSummary")){
//                    senMessageForInto(url, handler, 7);
//
//                }else {
//                    senMessageForInto(url, handler, 8);
//                }
//            }
//
//
//        };

//        mPushAgent.setNotificationClickHandler(notificationClickHandler);
//
//        mPushAgent.setDisplayNotificationNumber(3);
//        //友盟分享的初始化
//        UMShareAPI.get(context);
    }

    private static void senMessageForInto(String url, Handler handler, int i) {
        handler.sendMessage(handler.obtainMessage(i, url));
    }

    private static void showDialog(final Context context) {
//
        MDAlertDialog.createDialog(AppManager.getAppManager().currentActivity(), context.getString
                (R.string.dialog_offline), context.getString(R.string.login_exception), "重新登录", "修改密码", new
                SimpleDialogClickListener() {
            @Override
            public void clickLeftButton(Dialog dialog, View view) {
                dialog.dismiss();
                trackMsgDismissed(context);
                MyAPP.setShowDialog(false);
                MyAPP.logout(AppManager.getAppManager().currentActivity(), NewLoginActivity.class);
            }

            @Override
            public void clickRightButton(Dialog dialog, View view) {
                dialog.dismiss();
                trackMsgDismissed(context);
                MyAPP.setShowDialog(false);
            }
        }, true);
    }

    //处理事件
    private static boolean handlerEvent(Handler handler, Map<String, String> extra, String after_open) {
        boolean flag = true;
        if (EmptyUtils.isNotEmpty(extra)) {
            String action = extra.get(IConstants.ACTION);
            String deviceId = extra.get(IConstants.DEVICE_ID);
            if (IConstants.LOGIN.equals(action) && !DeviceUtil.getDeviceId(MyAPP.getContext()).equals(deviceId) && MyAPP.isLogin()) {
//                handler.sendEmptyMessageDelayed(1,2000);
                flag = false;

            } else if ("music".equals(action)) {
//                handler.sendMessage(handler.obtainMessage(2, extra));
                flag = false;
            } else if ("trailer".equals(action)) {
//                handler.sendMessage(handler.obtainMessage(3, extra));
            }
        }
        return flag;
    }


    private static void showNotification(UMessage msg) {
        int id = new Random(System.nanoTime()).nextInt();
        oldMessage = msg;
        NotificationManager manager = (NotificationManager)MyAPP.getContext(). getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
        Notification.Builder mBuilder = new Notification.Builder(MyAPP.getContext());
        mBuilder.setContentTitle(msg.title).setContentText(msg.text).setTicker(msg.ticker).setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true);
        Notification notification = mBuilder.getNotification();
        PendingIntent clickPendingIntent = getClickPendingIntent(MyAPP.getContext(), msg);
        notification.deleteIntent = getDismissPendingIntent(MyAPP.getContext(), msg);
        notification.contentIntent = clickPendingIntent;
        msg.builder_id = id;
        manager.notify(id, notification);
    }

    public  static PendingIntent getClickPendingIntent(Context context, UMessage msg) {
        Intent clickIntent = new Intent();
        clickIntent.setClass(context, NotificationBroadcast.class);
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG, msg.getRaw().toString());
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION, NotificationBroadcast.ACTION_CLICK);

        return PendingIntent.getBroadcast(context, (int) (System.currentTimeMillis()), clickIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public static PendingIntent getDismissPendingIntent(Context context, UMessage msg) {
        Intent deleteIntent = new Intent();
        deleteIntent.setClass(context, NotificationBroadcast.class);
        deleteIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG, msg.getRaw().toString());
        deleteIntent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION, NotificationBroadcast.ACTION_DISMISS);
        return PendingIntent.getBroadcast(context, (int) (System.currentTimeMillis() + 1), deleteIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }


    public static void trackMsgDismissed(Context context) {
        if (oldMessage != null) {
            UTrack.getInstance(context.getApplicationContext()).trackMsgDismissed(oldMessage);
            UTrack.getInstance(context).setClearPrevMessage(true);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(oldMessage.builder_id);
            oldMessage = null;
        }
    }

}
