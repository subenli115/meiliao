package com.ziran.meiliao.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.main.activity.SplashActivity;
import com.ziran.meiliao.utils.ExitUtil;
import com.ziran.meiliao.utils.UMengKit;
import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;

import org.json.JSONObject;

/**
 *  友盟推送消息的广播后显示通知消息的点击处理
 */
public class NotificationBroadcast extends BroadcastReceiver {
    public static final String EXTRA_KEY_ACTION = "ACTION";
    public static final String EXTRA_KEY_MSG = "MSG";
    public static final int ACTION_CLICK = 10;
    public static final int ACTION_DISMISS = 11;
    public static final int EXTRA_ACTION_NOT_EXIST = -1;

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(EXTRA_KEY_MSG);
        int action = intent.getIntExtra(EXTRA_KEY_ACTION, EXTRA_ACTION_NOT_EXIST);
        try {
            UMessage msg = new UMessage(new JSONObject(message));
            switch (action) {
                case ACTION_DISMISS:
                    UTrack.getInstance(context).setClearPrevMessage(true);
                    UTrack.getInstance(context).trackMsgDismissed(msg);
                    break;
                case ACTION_CLICK:
                    if (ExitUtil.getAppSatus(context, context.getPackageName()) == 3) {
                        Intent intent1 = new Intent(context, SplashActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent1);
                    } else {
                        RxManagerUtil.post(AppConstant.RXTag.UMENG_MSG_CLICK, msg);
                        UTrack.getInstance(context).setClearPrevMessage(true);
                        UMengKit.oldMessage = null;
                        UTrack.getInstance(context).trackMsgClick(msg);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
