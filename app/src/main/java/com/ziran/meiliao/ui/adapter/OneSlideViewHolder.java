package com.ziran.meiliao.ui.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.irecyclerview.slide.ISlide;
import com.ziran.meiliao.common.irecyclerview.slide.helper.SlideAnimationHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.widget.SmoothCheckBox;

/**
 * author 吴祖清
 * create  2017/3/8
 * des     复选框和删除的viewHelper
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class OneSlideViewHolder extends ViewHolderHelper implements ISlide {
    
    /**
     * 打开的动画时长
     */
    private static final int DURATION_OPEN = 400;
    /**
     * 关闭的动画时长
     */
    private static final int DURATION_CLOSE = 400;
    
    /**
     * 偏移的像素
     */
    private static final int NORMAL_OFFSET = 30;
    
    /**
     *
     */
    private SlideAnimationHelper mSlideAnimationHelper;
    
    private OneSlideViewHolder.OpenUpdateListener mOpenUpdateListener;
    private OneSlideViewHolder.CloseUpdateListener mCloseUpdateListener;
    
    private int mOffset;
    
    
    public static OneSlideViewHolder get (Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        
        if (convertView == null) {
            View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            OneSlideViewHolder holder = new OneSlideViewHolder(context, itemView, parent, position);
            holder.mLayoutId = layoutId;
            return holder;
        } else {
            try {
                OneSlideViewHolder holder = (OneSlideViewHolder) convertView.getTag();
                holder.mPosition = position;
                return holder;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public OneSlideViewHolder (Context context, View itemView, ViewGroup parent, int position) {
        super(context, itemView, parent, position);
        mOffset = SlideAnimationHelper.getOffset(itemView.getContext(), NORMAL_OFFSET);
        mSlideAnimationHelper = new SlideAnimationHelper();
    }
    
    
    public void setOffset (int offset) {
        mOffset = SlideAnimationHelper.getOffset(itemView.getContext(), offset);
    }
    
    public int getOffset () {
        return mOffset;
    }
    
    //keep change state
    public void onBindSlide (View targetView) {
        if (this.targetView == null) {
            this.targetView = targetView;
        }
        switch (mSlideAnimationHelper.getState()) {
            case SlideAnimationHelper.STATE_CLOSE:
                targetView.scrollTo(0, 0);
                onBindSlideClose(SlideAnimationHelper.STATE_CLOSE);
                break;
            
            case SlideAnimationHelper.STATE_OPEN:
                targetView.scrollTo(-mOffset, 0);
                doAnimationSetOpen(SlideAnimationHelper.STATE_OPEN);
                break;
        }
    }
    
    @Override
    public void slideOpen () {
        if (mOpenUpdateListener == null) {
            mOpenUpdateListener = new OneSlideViewHolder.OpenUpdateListener();
        }
        if (checkBox != null) {
            checkBox.setVisibility(View.VISIBLE);
        }
        mSlideAnimationHelper.openAnimation(DURATION_OPEN, mOpenUpdateListener);
    }
    
    public View targetView;
    public SmoothCheckBox checkBox;
    
    @Override
    public void slideClose () {
        if (mCloseUpdateListener == null) {
            mCloseUpdateListener = new OneSlideViewHolder.CloseUpdateListener();
        }
        if (checkBox != null) {
            checkBox.setChecked(false);
        }
        mSlideAnimationHelper.closeAnimation(DURATION_CLOSE, mCloseUpdateListener);
    }

    public void setCheckBox (SmoothCheckBox view) {
        checkBox = view;
    }
    
    public void doAnimationSet (int offset, float fraction) {
        if (targetView != null) {
            targetView.scrollTo(offset, 0);
        }
        if (checkBox != null) {
            checkBox.setScaleX(fraction);
            checkBox.setScaleY(fraction);
            checkBox.setAlpha(fraction * 255);
        }
    }
    
    public void onBindSlideClose (int state) {
        if (checkBox != null) {
            checkBox.setVisibility(View.GONE);
        }
    }
    
    
    public void doAnimationSetOpen (int state) {
        if (checkBox != null) {
            checkBox.setVisibility(View.VISIBLE);
        }
    }
    
    private class OpenUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        
        @Override
        public void onAnimationUpdate (ValueAnimator animation) {
            float fraction = animation.getAnimatedFraction();
            int endX = (int) (-mOffset * fraction);
            
            doAnimationSet(endX, fraction);
        }
    }
    
    private class CloseUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        
        @Override
        public void onAnimationUpdate (ValueAnimator animation) {
            float fraction = animation.getAnimatedFraction();
            fraction = 1.0f - fraction;
            int endX = (int) (-mOffset * fraction);
            
            doAnimationSet(endX, fraction);
        }
    }
}
