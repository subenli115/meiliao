package com.ziran.meiliao.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/6 9:51
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/6$
 * @updateDes ${TODO}
 */

public class KeyboardHeightLayout extends RelativeLayout {

    private View mViewGroup;
    private View firstViewGroup;

    public KeyboardHeightLayout(Context context) {
        super(context);
    }

    public KeyboardHeightLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardHeightLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Rect r = new Rect();
    private ViewGroup.MarginLayoutParams params;
    private int totalHeight;
    private int nowHeight;
    private boolean needMeasure = true;

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        if (mViewGroup == null && firstViewGroup == null) {
            super.onMeasure(widthSpec, heightSpec);
            return;
        }
        if (!needMeasure) {
            super.onMeasure(widthSpec, heightSpec);
            return;
        }
        getWindowVisibleDisplayFrame(r);
        totalHeight = getRootView().getHeight();
        nowHeight = r.bottom - r.top;
        if (firstViewGroup != null) {
            params = (ViewGroup.MarginLayoutParams) firstViewGroup.getLayoutParams();
        } else {
            params = (ViewGroup.MarginLayoutParams) mViewGroup.getLayoutParams();
        }
        if (totalHeight - nowHeight > totalHeight / 4) {
            isShowKeyBoard = true;
            super.onMeasure(widthSpec, MeasureSpec.makeMeasureSpec(totalHeight, MeasureSpec.EXACTLY));
            if (params.bottomMargin == 0) {
                params.bottomMargin = totalHeight - nowHeight;
                if (mOnKeyboardShowingListener != null) {
                    mOnKeyboardShowingListener.onKeyboardShowing(isShowKeyBoard);
                }
            }
        } else {
            super.onMeasure(widthSpec, heightSpec);
            if (params.bottomMargin > 0) {
                params.bottomMargin = 0;
                isShowKeyBoard = false;
                if (mOnKeyboardShowingListener != null) {
                    mOnKeyboardShowingListener.onKeyboardShowing(false);
                }
            }
        }
    }

    private boolean isShowKeyBoard;

    public boolean isNeedMeasure() {
        return needMeasure;
    }

    public void setNeedMeasure(boolean needMeasure) {
        this.needMeasure = needMeasure;
    }

    public void setViewGroup(View viewGroup) {
        this.mViewGroup = viewGroup;
    }

    public void setFirstViewGroup(View viewGroup) {
        this.firstViewGroup = viewGroup;
    }

    public OnKeyboardShowingListener mOnKeyboardShowingListener;

    public void setOnKeyboardShowingListener(OnKeyboardShowingListener onKeyboardShowingListener) {
        mOnKeyboardShowingListener = onKeyboardShowingListener;
    }

    public boolean isShowKeyBoard() {
        return isShowKeyBoard;
    }

    /**
     * The interface is used to listen the keyboard showing state.
     */
    public interface OnKeyboardShowingListener {

        /**
         * Keyboard showing state callback method.
         *
         * @param isShowing Indicate whether keyboard is showing or not.
         */
        void onKeyboardShowing(boolean isShowing);

    }

}
