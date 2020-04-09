package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;

/**
 * bottom组合控件的基类,处理点击空白地方隐藏控件
 * Created by Administrator on 2017/3/14.
 */

public abstract class CustomRelativeLayout extends RelativeLayout {
    /**
     * 根元素
     */
    protected View rootView;

    //显示内容的父容器
    protected View ll_more_more;
    //点击的空间ID
    protected int checkId;
    //控件是否显示
    protected boolean showOrHide;

    public CustomRelativeLayout(Context context) {
        super(context);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rootView = android.view.LayoutInflater.from(getContext()).inflate(getResourseLayoutId(), this, true);
    }

    protected abstract int getResourseLayoutId();

    /**
     * @param rootView 触摸的容器
     */
    protected void initMore(View rootView) {
        initMore(rootView, R.id.layout_custom_container);
    }

    /**
     * 设置拦截触摸事件
     *
     * @param rootView 触摸的view
     * @param id       触摸View的id
     */
    protected void initMore(View rootView, int id) {
        ll_more_more = ViewUtil.getView(rootView, id);
        ll_more_more.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            checkId = -1;
            setShow(false);
        }
        return true;
    }

    /**
     * @return 是否隐藏控件
     */
    public boolean isShowOrHide() {
        return showOrHide;
    }

    //返回true 自行处理, false  系统处理
    public boolean checkShow() {
        if (isShowOrHide()) {
            setShow(false);
            if (onTouchHideListener != null) onTouchHideListener.onTouchHide(this);
            return true;
        }
        return false;
    }

    /**
     * 设置 隐藏控件
     *
     * @param isShow true 显示 FALSE 隐藏
     */
    protected void setShow(boolean isShow) {
    }

    protected OnTouchHideListener onTouchHideListener;

    public void setOnTouchHideListener(OnTouchHideListener onTouchHideListener) {
        this.onTouchHideListener = onTouchHideListener;
    }

    public interface OnTouchHideListener {
        void onTouchHide(View view);
    }
}
