/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ziran.meiliao.common.permission;

import android.content.Context;

import com.ziran.meiliao.common.R;


/**
 * Description:
 *
 * @author zhaozp
 * @since 2016-05-19
 */
public class AVCallFloatView extends BaseAVCallFloatView {
    public AVCallFloatView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResourseId() {
        return R.layout.float_window_layout;
    }
}
