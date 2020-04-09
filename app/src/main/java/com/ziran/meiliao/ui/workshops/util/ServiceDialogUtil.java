package com.ziran.meiliao.ui.workshops.util;

import android.app.Activity;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.utils.DeviceUtil;
import com.wevey.selector.dialog.SimpleDialog;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/23 9:46
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/23$
 * @updateDes ${TODO}
 */

public class ServiceDialogUtil {
    private static String servicePhone = "18826447088";
    private static SimpleDialog simpleDialog;
    private static View contentView;
    private static String callPhone;


    public static String getServicePhone() {
        return servicePhone;
    }

    public static void setServicePhone(String servicePhone) {
        ServiceDialogUtil.servicePhone = servicePhone;
    }

    public static void showDialog(Activity activity) {
        showDialog(activity, servicePhone);
    }

    public static void showDialog(final Activity context, final String phone) {
        if (EmptyUtils.isEmpty(phone)) return;
        if (simpleDialog == null || ServiceDialogUtil.callPhone.equals(phone)) {
            simpleDialog = new SimpleDialog(context);
            simpleDialog.setCancelable(true);
            simpleDialog.setCanceledOnTouchOutside(true);
            contentView = View.inflate(context, R.layout.dialog_service, null);
            simpleDialog.setContentView(contentView);
            View view = ViewUtil.getView(contentView, R.id.tv_dialog_service_call);
            View close = ViewUtil.getView(contentView, R.id.iv_close);
            if (close != null) {
                close.setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        simpleDialog.dismiss();
                    }
                });
            }
            if (view != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeviceUtil.call(context, phone);
                        simpleDialog.dismiss();
                    }
                });
            }
        }
        ServiceDialogUtil.callPhone = phone;
        ViewUtil.setText(contentView, R.id.tv_dialog_service_phone, String.format("客服咨询：%s", phone));
        if (!simpleDialog.isShowing()) {
            simpleDialog.show();
        }
    }

    public static void cancel() {
        if (simpleDialog != null) {
            simpleDialog.cancel();
            simpleDialog = null;
            contentView = null;
        }
    }
}
