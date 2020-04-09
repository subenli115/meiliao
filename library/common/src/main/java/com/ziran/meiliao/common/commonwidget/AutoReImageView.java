package com.ziran.meiliao.common.commonwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/12/19.
 */

public class AutoReImageView extends ImageView {
    public AutoReImageView(Context context) {
        super(context);
    }

    public AutoReImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoReImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
       setBackgroundResource(0);
    }
}
