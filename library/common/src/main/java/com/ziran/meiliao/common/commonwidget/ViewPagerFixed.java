package com.ziran.meiliao.common.commonwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * des:修复viewpager
 * Created by xsf
 * on 2016.07.15:33
 */
public class ViewPagerFixed extends android.support.v4.view.ViewPager {
    private boolean isCanScroll = true;
    public ViewPagerFixed(Context context) {
        super(context);
    }

    public ViewPagerFixed(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            if (isCanScroll) {
                return super.onTouchEvent(ev);
            } else {
                return false;
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            if (isCanScroll) {
                return super.onInterceptTouchEvent(ev);
            } else {
                return false;
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    public void setScanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;

    }

    public boolean isCanScroll() {
        return isCanScroll;
    }

    @Override
    public void scrollTo(int x, int y){
        if (isCanScroll){
            super.scrollTo(x, y);
//        }else{
//            ToastUitl.showLong("请点击取消,然后再切换界面");
        }
    }
}
