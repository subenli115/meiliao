package com.ziran.meiliao.common.commonutils;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

/**
 * author admin
 * create  2017/4/1 11
 * des     ${TODO}
 * <p>
 * updateAuthor     $admin
 * updateDate   2017/4/1 11
 * updateDes    ${TODO}
 */

public class SettingUtil {
    public static void startSettings(Context context, String action) {
        Intent intent = new Intent(action);
        context.startActivity(intent);
    }

    public static void startWifiSettings(Context context){
        startSettings(context, Settings.ACTION_WIFI_SETTINGS);
    }

    public static void startSettings(Context context){
        startSettings(context, Settings.ACTION_SETTINGS);
    }

}
