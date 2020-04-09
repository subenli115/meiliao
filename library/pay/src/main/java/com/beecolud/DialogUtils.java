package com.beecolud;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 *
 * Created by Administrator on 2016/12/24.
 */

public class DialogUtils {

    public static void showUPPayToast(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("完成支付需要安装或者升级银联支付控件，是否安装？");

        builder.setNegativeButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        UPPayAssistEx.installUPPayPlugin(context);
                        dialog.dismiss();
                    }
                });
        builder.setPositiveButton("取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    public static ProgressDialog getPayProgressDialog(Context context){
        ProgressDialog loadingDialog = new ProgressDialog(context);
        loadingDialog.setMessage("处理中，请稍候...");
        loadingDialog.setIndeterminate(true);
        loadingDialog.setCancelable(true);
        return loadingDialog;
    }
}
