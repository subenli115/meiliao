package com.ziran.meiliao.ui.decompressionmuseum.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;

import java.util.Arrays;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/12/2 12:58
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/12/2
 * @updateDes ${TODO}
 */


public class ImageViewUtil {
    private ImageView mFromView;
    private View mToView;
    private ViewGroup mViewGroup;
    private int[] formLocation;
    private int[] toLocation;
    private int[] rootLocation;
    private float ratioX = -0.425f, ratioY = -0.3f;
    private long duantion = 4000L;
    private Point mPointX;
    private Point mPointY;

    public float getRatioX() {
        return ratioX;
    }

    public void setRatioX(float ratioX) {
        this.ratioX = ratioX;
    }

    public float getRatioY() {
        return ratioY;
    }

    public void setRatioY(float ratioY) {
        this.ratioY = ratioY;
    }

    public ImageViewUtil(ViewGroup rootView, ImageView fromView, View toView) {
        mFromView = fromView;
        mViewGroup = rootView;
        this.mToView = toView;
        initLocation();
    }

    boolean isRuning;

    public void initLocation() {
        ViewUtil.addOnGlobalLayoutListener(mFromView, new ViewUtil.BaseCallBack() {
            @Override
            public void call() {
                updateLocation();
            }
        });
    }

    public void updateLocation() {
        rootLocation = getLocation(mViewGroup); //root
        formLocation = getLocation(mFromView); //form
        toLocation = getLocation(mToView);    //to
        mPointX = new Point((int) (formLocation[0] + mFromView.getPivotX()), toLocation[0]);
        mPointY = new Point((int) (formLocation[1] + mFromView.getPivotY() - rootLocation[1]), toLocation[1] - rootLocation[1]);
    }

    private ImageView targetView;

    public void start() {
        if (isRuning) return;
        isRuning = true;
        Drawable background = mFromView.getDrawable();
        if (targetView == null) {
            targetView = new ImageView(mFromView.getContext());
            targetView.setX(mFromView.getX());
            targetView.setY(mFromView.getY());
            targetView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        if (background != null) {
            targetView.setImageDrawable(background);
            mViewGroup.addView(targetView, new ViewGroup.LayoutParams(mToView.getWidth(), mToView.getHeight()));

            ViewUtil.addOnGlobalLayoutListener(targetView, new ViewUtil.BaseCallBack() {
                @Override
                public void call() {
                    LogUtils.logd(Arrays.toString(formLocation) + ":" + mFromView + " :" + mFromView.getWidth());
                    LogUtils.logd(Arrays.toString(toLocation) + ":" + mToView + " :" + mToView.getWidth());

                    ObjectAnimator animatorX = getObjectAnimator("X", mPointX.x, mPointX.y);
                    ObjectAnimator animatorY = getObjectAnimator("Y", mPointY.x, mPointY.y);

                    animatorX.addListener(mAnimatorListener);
                    animatorX.start();
                    animatorY.start();
                }
            });
        }
    }

    private ObjectAnimator getObjectAnimator(String tag, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, tag, from, to);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(duantion);
        return animator;

    }

    private Animator.AnimatorListener mAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {
            isRuning = false;
            mViewGroup.removeView(targetView);
        }

        @Override
        public void onAnimationCancel(Animator animator) {
            isRuning = false;
            mViewGroup.removeView(targetView);
        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    };

    public int[] getLocation(View view) {
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        return locations;
    }
}
