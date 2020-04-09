package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.weavey.utils.ScreenSizeUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/20 15:10
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/20$
 * @updateDes ${TODO}
 */

public class SimpleDialog extends Dialog {

    private float widthRatio = 0.73f;
    private float heightRatio = 0.21f;
    public SimpleDialog(@NonNull Context context) {
        super(context, R.style.MyDialogStyle);
        initDialog();
    }

    private void initDialog() {

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(getContext()).getScreenWidth() * getWidthRatio());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void setContentView(@NonNull View view) {
        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(getContext()).getScreenHeight() * getHeightRatio()));
        super.setContentView(view);
    }

    public <T> T getView(int viewId){
        return (T) findViewById(viewId);
    }

    public float getWidthRatio() {
        return widthRatio;
    }

    public void setWidthRatio(float widthRatio) {
        this.widthRatio = widthRatio;
    }

    public float getHeightRatio() {
        return heightRatio;
    }

    public void setHeightRatio(float heightRatio) {
        this.heightRatio = heightRatio;
    }
}
