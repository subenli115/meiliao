package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.constant.AppConstant;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/13 19:38
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/13$
 * @updateDes ${TODO}
 */

public class ObservableScrollView extends ScrollView {


    private ScrollViewListener scrollViewListener = null;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    public void openScroll(){
        setScrollViewListener(new ScrollViewListener() {
            int lastY;
            int count;
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldX, int oldY, int downX, int downY) {
                if (lastY > y) { //下拉
                    if (count>12){
                        count = 0;
                    }
                    count++;
                    LogUtils.logd("下拉" + (downY - y) + " downY:" + downY + "  y:" + y);
                } else if (lastY < y) { //上拉
                    if (count<-12){
                        count = 0;
                    }
                    count--;
//                    LogUtils.logd("上拉" + (downY - y) + " downY:" + downY + "  y:" + y);
                }
                lastY = y;
            }
        });
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy, downX, downY);
        }
    }

    private int downY, downX;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getRawX();
                downY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                downY = 0;
                downX = 0;
                break;
        }
        return super.onTouchEvent(ev);
    }

    public interface ScrollViewListener {
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldX, int oldY, int downX, int downY);
    }
}
