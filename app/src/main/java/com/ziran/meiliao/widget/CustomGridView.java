package com.ziran.meiliao.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * 自定义GridView
 */
public class CustomGridView extends GridView {

    private float mTouchX;
    private float mTouchY;
    private OnTouchBlankPositionListener mTouchBlankPosListener;

    public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridView(Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTouchBlankPosListener != null) {
            if (!isEnabled()) {
                return isClickable() || isLongClickable();
            }
            int action = event.getActionMasked();
            float x = event.getX();
            float y = event.getY();
            final int motionPosition = pointToPosition((int) x, (int) y);
            if (motionPosition == INVALID_POSITION) {
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchX = x;
                        mTouchY = y;
                        mTouchBlankPosListener.onTouchBlank(event);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(mTouchX - x) > 10
                                || Math.abs(mTouchY - y) > 10) {
                            mTouchBlankPosListener.onTouchBlank(event);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mTouchX = 0;
                        mTouchY = 0;
                        mTouchBlankPosListener.onTouchBlank(event);
                        break;
                }
            }
        }
        return super.onTouchEvent(event);
    }



    public void setOnTouchBlankPositionListener(
            OnTouchBlankPositionListener listener) {
        mTouchBlankPosListener = listener;
    }


    public interface OnTouchBlankPositionListener {
        void onTouchBlank(MotionEvent event);
    }
}
