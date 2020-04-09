package com.ziran.meiliao.common.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class PhoneStateReceive extends BroadcastReceiver {

    private int mCurrentState = TelephonyManager.CALL_STATE_IDLE;
    private int mOldState = TelephonyManager.CALL_STATE_IDLE;

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            tm.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    private class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            mOldState = SPUtils.getInt("FLAG_CALL_STATE");

            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    mCurrentState = TelephonyManager.CALL_STATE_IDLE;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    mCurrentState = TelephonyManager.CALL_STATE_OFFHOOK;
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    mCurrentState = TelephonyManager.CALL_STATE_RINGING;
                    break;
            }
            if (mCurrentState == TelephonyManager.CALL_STATE_RINGING){
                callBack(true);
            }
            if (mOldState == TelephonyManager.CALL_STATE_IDLE && mCurrentState == TelephonyManager.CALL_STATE_OFFHOOK) {
//                Log.i(Config.TAG, "onCallStateChanged: 接通");
                SPUtils.setInt( "FLAG_CALL_STATE", mCurrentState);
//                callBack(true);
            } else if (mOldState == TelephonyManager.CALL_STATE_OFFHOOK && mCurrentState == TelephonyManager.CALL_STATE_IDLE) {
//                Log.i(Config.TAG, "onCallStateChanged: 挂断");
                SPUtils.setInt( "FLAG_CALL_STATE", mCurrentState);
                callBack(false);
            }
        }

        private void callBack(boolean isCall) {
            if (EmptyUtils.isNotEmpty(callbacks)) {
                for (int i = 0; i < callbacks.size(); i++) {
                    callbacks.get(i).call(isCall);
                }
            }
        }
    }

    private static List<PhoneStateCallback> callbacks = new ArrayList<>();

    public static void addCallBack(PhoneStateCallback callback) {
        if (callback != null && !callbacks.contains(callback)) {
            callbacks.add(callback);
        }
    }

    public static void removeCallBack(PhoneStateCallback callback) {
        if (callback != null ) {
            callbacks.remove(callback);
        }
    }


    public interface PhoneStateCallback {
        void call(boolean isCall);
    }

}