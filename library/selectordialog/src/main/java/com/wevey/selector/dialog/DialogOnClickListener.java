package com.wevey.selector.dialog;

import android.app.Dialog;
import android.view.View;

/**
 * Created by weavey
 * on 2016-09-05.
 * todo
 */
public interface DialogOnClickListener {

    void clickLeftButton(Dialog dialog,View view);

    void clickRightButton(Dialog dialog,View view);
}
