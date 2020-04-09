package com.ziran.meiliao.widget;

/**
 * created by yhao on 2017/8/11.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;


public class SlidingMenu extends HorizontalScrollView {


    //菜单占屏幕宽度比
    private static final float radio = 0.12f;
    private int mScreenWidth;
    private int mMenuWidth;


    private boolean once = true;
    private boolean isOpen;

    public SlidingMenu(final Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScreenWidth = DisplayUtil.getScreenWidth(context);
        mMenuWidth = (int) (mScreenWidth * radio);
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setHorizontalScrollBarEnabled(false);
    }

    public SlidingMenu(Context context) {
        super(context);
        init(context);
    }

    /**
     * 关闭菜单
     */

    public void closeMenu() {
        this.smoothScrollTo(0, 0);
        isOpen = false;
    }

    /**
     * 菜单是否打开
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * 当打开菜单时记录此 view ，方便下次关闭
     */
    private void onOpenMenu() {
        isOpen = true;
        if (mSlidingMenuListener != null) {
            mSlidingMenuListener.holdOpenMenu(this);
        }
    }

    /**
     * 当触摸此 item 时，关闭上一次打开的 item
     */
    private void closeOpenMenu() {
//        if (!isOpen) {
//
//        }
        if (mSlidingMenuListener != null) {
            mSlidingMenuListener.closeOpenMenu();
        }
    }

    /**
     * 获取正在滑动的 item
     */
    public SlidingMenu getScrollingMenu() {
        if (mSlidingMenuListener != null) {
            return mSlidingMenuListener.getScrollingMenu();
        }
        return null;
    }

    /**
     * 设置本 item 为正在滑动 item
     */
    private void setScrollingMenu(SlidingMenu scrollingMenu) {
        LogUtils.logd("setScrollingMenu"+scrollingMenu);
        if (mSlidingMenuListener != null) {
            mSlidingMenuListener.setScrollingMenu(scrollingMenu);
        }
    }

    private View mContentView;
    private View menuView;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (once) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            mContentView = wrapper.getChildAt(0);
            menuView = wrapper.getChildAt(1);
            mContentView.setOnClickListener(mOnNoDoubleClickListener);
            menuView.setOnClickListener(mOnNoDoubleClickListener);
            mContentView.getLayoutParams().width = mScreenWidth;
//            menuView.getLayoutParams().width = mMenuWidth;
            once = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        LogUtils.logd("getScrollingMenu"+getScrollingMenu());
//        if (getScrollingMenu() != null && getScrollingMenu() != this) {
//            return false;
//        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downTime = System.currentTimeMillis();
                closeOpenMenu();
                setScrollingMenu(this);
                LogUtils.logd("onTouchEvent MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                setScrollingMenu(null);
                int scrollX = getScrollX();
                if (Math.abs(scrollX) > mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    onOpenMenu();
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return false;
        }
        return super.onTouchEvent(ev);
    }

    long downTime = 0;

    private OnNoDoubleClickListener mOnNoDoubleClickListener = new OnNoDoubleClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {

            if (mCustomOnClickListener != null) {
//                LogUtils.logd("onNoDoubleClickVIEW:" + v + "\n mContentView:"+mContentView + " isOpenMenu"+mSlidingMenuListener.isOpenMenu());
                if (v == mContentView) {
                    if (mSlidingMenuListener.isOpenMenu()) {
                        closeOpenMenu();
                    } else {
                        mCustomOnClickListener.onContentClick();
                    }
                } else if (v == menuView) {
                    mCustomOnClickListener.onMenuClick();
                }
            }
        }
    };

    public interface CustomOnClickListener {
        void onContentClick();

        void onMenuClick();
    }

    private CustomOnClickListener mCustomOnClickListener;
    private SlidingMenuListener mSlidingMenuListener;

    public void setSlidingMenuListener(SlidingMenuListener slidingMenuListener) {
        mSlidingMenuListener = slidingMenuListener;
    }

    public void setCustomOnClickListener(CustomOnClickListener listener) {
        this.mCustomOnClickListener = listener;
    }

    public interface SlidingMenuListener {
        void closeOpenMenu();

        boolean isOpenMenu();

        void holdOpenMenu(SlidingMenu openMenu);

        SlidingMenu getScrollingMenu();

        void setScrollingMenu(SlidingMenu scrollingMenu);
    }

}
