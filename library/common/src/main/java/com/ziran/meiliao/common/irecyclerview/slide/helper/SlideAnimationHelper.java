package com.ziran.meiliao.common.irecyclerview.slide.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;

/**
 * author 吴祖清
 * create  2017/3/8
 * des     滑动动画工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class SlideAnimationHelper {
    
    /**
     * 关闭的状态
     */
    public static final int STATE_CLOSE = 20000;
    /**
     * 打开的状态
     */
    public static final int STATE_OPEN = 30000;
    
    /**
     * 当前状态 默认是关闭
     */
    private int mCurrentState = STATE_CLOSE;
    
    /**
     * 属性动画
     */
    private ValueAnimator mValueAnimator;
    
    public SlideAnimationHelper () {
        
    }
    
    /**
     * @return 返回当前状态
     */
    public int getState () {
        return mCurrentState;
    }
    
    /**
     * 创建属性动画对象
     *
     * @return
     */
    public ValueAnimator getAnimation () {
        if (mValueAnimator == null) {
            mValueAnimator = new ValueAnimator();
            mValueAnimator.setFloatValues(0.0f, 1.0f);
        }
        return mValueAnimator;
    }
    
    public void openAnimation (long duration, AnimatorUpdateListener animatorUpdateListener, Animator.AnimatorListener listener, float... values) {
        mCurrentState = STATE_OPEN;
        setValueAnimator(duration, animatorUpdateListener, listener, values);
    }
    
    public void closeAnimation (long duration, AnimatorUpdateListener animatorUpdateListener, Animator.AnimatorListener listener, float... values) {
        mCurrentState = STATE_CLOSE;
        setValueAnimator(duration, animatorUpdateListener, listener, values);
    }
    
    public void openAnimation (long duration, AnimatorUpdateListener animatorUpdateListener, float... values) {
        mCurrentState = STATE_OPEN;
        setValueAnimator(duration, animatorUpdateListener, null, values);
    }
    
    public void closeAnimation (long duration, AnimatorUpdateListener animatorUpdateListener, float... values) {
        mCurrentState = STATE_CLOSE;
        setValueAnimator(duration, animatorUpdateListener, null, values);
    }
    
    public void openAnimation (long duration, AnimatorUpdateListener animatorUpdateListener, Animator.AnimatorListener listener) {
        mCurrentState = STATE_OPEN;
        setValueAnimator(duration, animatorUpdateListener, listener);
    }
    
    public void closeAnimation (long duration, AnimatorUpdateListener animatorUpdateListener, Animator.AnimatorListener listener) {
        mCurrentState = STATE_CLOSE;
        setValueAnimator(duration, animatorUpdateListener, listener);
    }
    
    public void openAnimation (long duration, AnimatorUpdateListener animatorUpdateListener) {
        mCurrentState = STATE_OPEN;
        setValueAnimator(duration, animatorUpdateListener, null);
    }
    
    public void closeAnimation (long duration, AnimatorUpdateListener animatorUpdateListener) {
        mCurrentState = STATE_CLOSE;
        setValueAnimator(duration, animatorUpdateListener, null);
    }
    
    private void setValueAnimator (long duration, AnimatorUpdateListener animatorUpdateListener, Animator.AnimatorListener listener) {
        mValueAnimator = getAnimation();
        mValueAnimator.setDuration(duration);
        
        if (animatorUpdateListener != null) {
            mValueAnimator.addUpdateListener(animatorUpdateListener);
        }
        if (listener != null) {
            mValueAnimator.addListener(listener);
        }
        start();
    }
    
    private void setValueAnimator (long duration, AnimatorUpdateListener animatorUpdateListener, Animator.AnimatorListener listener, float... values) {
        mValueAnimator = getAnimation();
        mValueAnimator.setDuration(duration);
        mValueAnimator.setFloatValues(values);
        
        if (animatorUpdateListener != null) {
            mValueAnimator.addUpdateListener(animatorUpdateListener);
        }
        if (listener != null) {
            mValueAnimator.addListener(listener);
        }
        start();
    }
    
    private void start () {
        if (mValueAnimator != null && !mValueAnimator.isRunning()) {
            mValueAnimator.start();
        }
    }
    
    public static int getOffset (Context context, int offset) {
        return (int) (context.getResources().getDisplayMetrics().density * offset + 0.5f);
    }
}
