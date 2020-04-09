package com.ziran.meiliao.widget.pupop;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.weavey.utils.ScreenSizeUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/7 15:26
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/7$
 * @updateDes ${TODO}
 */

public class TipsDialog {

    private Dialog mDialog;
    private View mDialogView;
    private TextView mTitle;
    private TextView mMessage;
    private Context mContext;

    public TipsDialog(Context context) {
        mContext = context;
        mDialog = new Dialog(mContext, R.style.MyDialogStyle);
        mDialogView = View.inflate(mContext, R.layout.dialog_tips, null);
        mTitle = (TextView) mDialogView.findViewById(R.id.edit_dialog_title);
        mMessage = (TextView) mDialogView.findViewById(R.id.edit_dialog_message);
        ViewUtil.getView(mDialogView, R.id.tv_dialog_tops_know).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.setContentView(mDialogView);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mContext).getScreenWidth() * 0.8);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
    }

    public TipsDialog(Context context, String title, String message) {
        this(context);
        setTitle(title);
        setMessage(message);
    }

    public void show(){
        mDialog.show();
    }

    public void setTitle(String title) {
        ViewUtil.setText(mTitle, title);
    }

    public void setMessage(String message) {
        ViewUtil.setText(mMessage, message);
    }
}
