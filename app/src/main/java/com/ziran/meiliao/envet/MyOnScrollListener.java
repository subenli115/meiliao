package com.ziran.meiliao.envet;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.utils.L;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/23 10:38
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/23$
 * @updateDes ${TODO}
 */

public class MyOnScrollListener extends RecyclerView.OnScrollListener {

    private int onScrolledY;
    //    private static boolean showOrHide;
    private View topBar;
    private View listViewTop;
    private String tag;
    private int viewPagerHeight;
    private int topBarHeight;
    public MyOnScrollListener(String tag) {
        this.tag = tag;
    }

    public MyOnScrollListener(final IRecyclerView recyclerView, final View topBar, View listViewTop, String tag) {
        this.topBar = topBar;
        this.listViewTop = listViewTop;
        this.tag = tag;

        topBarHeight = DisplayUtil.getStatusBarHeight(topBar.getContext());
        if ( recyclerView != null) {
            Log.e("setOnMoveListener","onReset");
            recyclerView.setOnMoveListener(new IRecyclerView.OnMoveListener() {
                @Override
                public void moveY(int measuredHeight, int moved) {
                    Log.e("setOnMoveListener","measuredHeight"+measuredHeight+"   "+topBar.isShown());
                    if (measuredHeight > 0 && topBar.isShown()) {
                        topBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onReset() {
                    Log.e("setOnMoveListener","onReset");
                    topBar.setVisibility(View.VISIBLE);
                }
            });
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) { //停止滚动时重置onScrolledY的值
            onScrolledY = 0;
        }

    }
    private  boolean searchShowOrHide;
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        onScrolledY += dy;
        boolean showOrHide = false;
        if (Math.abs(onScrolledY) > 30) {
            if (mOnChangeListener!=null){
                if ( onScrolledY > 0 && !searchShowOrHide){
                    searchShowOrHide = true;
                    mOnChangeListener.change(false);
                }else if (onScrolledY < 0 && searchShowOrHide){
                    searchShowOrHide = false;
                    mOnChangeListener.change(true);
                }
            }

            if (onScrolledY > 0 && showOrHide) {
                RxManagerUtil.post(tag, false);
            } else if (onScrolledY < 0 && !showOrHide) {
                RxManagerUtil.post(tag, true);
            }
            onScrolledY = 0;
        }

        if (topBar==null) return;
        int top = getViewPagerTop();
        if (top != -1) {
            if (!topBar.isShown()) {
                topBar.setVisibility(View.VISIBLE);
            }
            topBar.setBackgroundColor(Color.argb(top, 255, 255, 255));
//            LogUtils.logd("top:"+top);
        }
    }

    public int getViewPagerTop() {
        if (listViewTop == null) return 0;
        int location[] = new int[2];
        listViewTop.getLocationOnScreen(location);
        if (viewPagerHeight == 0) {
            viewPagerHeight = listViewTop.getMeasuredHeight();
        }
        if (location[1] > topBarHeight) {
            return -1;
        } else if (viewPagerHeight != 0) {
            if (location[1] == 0) {
                return 255;
            } else {
                int alpha = (int) (((Math.abs(location[1] - 45f)) / viewPagerHeight) * 255);
                return alpha > 255 ? 255 : alpha;
            }
        }
        return 0;
    }

    public interface OnChangeListener{
        void change(boolean showOrHide);
    }
    private OnChangeListener mOnChangeListener;

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        mOnChangeListener = onChangeListener;
    }


}
