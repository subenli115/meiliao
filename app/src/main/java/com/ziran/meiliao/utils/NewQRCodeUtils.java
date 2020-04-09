package com.ziran.meiliao.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.QRCodeUtil;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des      生成二维码的工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class NewQRCodeUtils {

    //创建二维码位图
    public static Bitmap createQRImage(String content, View view) {
        Drawable drawable = view.getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap logo = null;
        if (drawable instanceof BitmapDrawable) {
            logo = ((BitmapDrawable) drawable).getBitmap();
        }
        int width = DisplayUtil.getViewWidth(view);
        if (width ==0){
            width = DisplayUtil.dip2px(60);
        }
        return QRCodeUtil.createQRImage(content, width, width, logo, null);
    }
}
