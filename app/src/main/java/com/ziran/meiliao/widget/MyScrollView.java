package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private onScrollChangedListener mListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mListener != null) {
            mListener.onScrollChanged(t);
        }

    }

    public void addOnScrollChangedListener(onScrollChangedListener listener) {
        mListener = listener;
    }

    public interface onScrollChangedListener {
        void onScrollChanged(int t);
    }


}
