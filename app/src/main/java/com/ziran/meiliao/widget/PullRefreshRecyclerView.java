package com.ziran.meiliao.widget;

/**
 * Created by Administrator on 2019/1/26.
 */


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by 刘龙 on 2017/7/18.
 * 公司:北京华星成汇文化发展有限公司
 * 描述:
 */

class PullRefreshRecyclerView extends RecyclerView {

    private float mPosX, mPosY, mCurPosX, mCurPosY;
    private float yDistance;
    private float xDistance;

    public PullRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {


        return super.onInterceptTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                mPosX = event.getX();
                mPosY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurPosX = event.getX();
                mCurPosY = event.getY();

                break;
        }

        xDistance=Math.abs(mCurPosX-mPosX);
        yDistance=Math.abs(mCurPosY-mPosY);
        if(xDistance<yDistance)
//            return false;
    Log.e("xDistance",""+xDistance+"            "+yDistance);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}