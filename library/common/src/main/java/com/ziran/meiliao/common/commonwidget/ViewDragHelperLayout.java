package com.ziran.meiliao.common.commonwidget;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.customview.widget.ViewDragHelper;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/6 13:25
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/6$
 * @updateDes ${TODO}
 */

public class ViewDragHelperLayout extends RelativeLayout {

    private ViewDragHelper mDragger;
    private Point mAutoBackOriginPos = new Point();
    private View mAutoBackView;
    private int screenWidth;

    public ViewDragHelperLayout(Context context) {
        this(context, null);
    }

    public ViewDragHelperLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewDragHelperLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
//        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragCallback());
//        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_RIGHT);

    }

    private float downX, downY;

  /*  @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (downY == event.getY() && downX == event.getX() && mOnClickListener != null) {
                    mOnClickListener.onClick(this);
                }
                break;
        }
        if (canTouch) {
            mDragger.shouldInterceptTouchEvent(event);
        }
        return true;
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_UP:
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(this);
                }
                break;
        }
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = event.getX();
//                downY = event.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                if (downY == event.getY() && downX == event.getX() && mOnClickListener != null) {
//                    mOnClickListener.onClick(this);
//                }
//                break;
//        }
//        if (canTouch) {
//            mDragger.processTouchEvent(event);
//        }
        return true;
//        return super.onTouchEvent(event);
    }

//    @Override
//    public void computeScroll() {
//        if (mDragger.continueSettling(true)) {
//            invalidate();
//        }
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        mAutoBackOriginPos.x = mAutoBackView.getLeft();
//        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException("child count must only one");
        }
        mAutoBackView = getChildAt(0);

    }

    public boolean changeChildViewShown() {
        mAutoBackView.setVisibility(mAutoBackView.isShown() ? GONE : VISIBLE);
        return mAutoBackView.isShown();
    }

    public class ViewDragCallback extends ViewDragHelper.Callback {


        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //mEdgeTrackerView禁止直接移动
            return child == mAutoBackView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left < 0 ? 0 : left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        //手指释放的时候回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //mAutoBackView手指释放时可以自动回去
            if (releasedChild == mAutoBackView) {
                float newX = releasedChild.getX();
                if (screenWidth / 2 > newX) {
                    mDragger.settleCapturedViewAt(0, 0);
                } else {
                    mDragger.settleCapturedViewAt(screenWidth, 0);
                }
                invalidate();
            }
        }

        //在边界拖动时回调
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            mDragger.captureChildView(mAutoBackView, pointerId);
        }


        @Override
        public int getViewHorizontalDragRange(View child) {
            return getMeasuredWidth() - child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return getMeasuredHeight() - child.getMeasuredHeight();
        }
    }

    private View.OnClickListener mOnClickListener;

    public void setOnClick(View.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    private boolean canTouch = true;

    public boolean isCanTouch() {
        return canTouch;
    }

    public void setCanTouch(boolean canTouch) {
        this.canTouch = canTouch;
    }
}
