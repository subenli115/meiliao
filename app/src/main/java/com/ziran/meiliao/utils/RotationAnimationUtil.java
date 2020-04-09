package com.ziran.meiliao.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/9/11 22:03
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/9/11
 * @updateDes ${TODO}
 */

public class RotationAnimationUtil {

    private  ObjectAnimator discAnimation;
    private View targetView;

    public RotationAnimationUtil() {
    }

    public RotationAnimationUtil(View targetView) {
        if (targetView == null) {
            throw new NullPointerException("targetView must not null");
        }
        this.targetView = targetView;
        discAnimation = ObjectAnimator.ofFloat(targetView, "rotation", 0, 360);
        discAnimation.setDuration(10000);
        discAnimation.setInterpolator(new LinearInterpolator());
        discAnimation.setRepeatCount(ValueAnimator.INFINITE);
    }



    public void pause() {
        if (discAnimation != null && discAnimation.isRunning() && targetView!=null && targetView.isShown()) {
            discAnimation.cancel();
            float valueAvatar = (float) discAnimation.getAnimatedValue();
            discAnimation.setFloatValues(valueAvatar, 360f + valueAvatar);
        }
    }

    public void start() {
        if (discAnimation != null && !discAnimation.isRunning() && targetView!=null && targetView.isShown()) {
            discAnimation.start();
        }

    }

    public void setTarget(View targetView) {
        if (targetView == null) {
            throw new NullPointerException("targetView must not null");
        }
        discAnimation.setTarget(targetView);
        this.targetView = targetView;

    }

    public void onDestroy() {
        if (discAnimation != null) {
            discAnimation.cancel();
        }
        if (targetView != null) {
            targetView = null;
        }
    }

    public void cancel() {
        if (discAnimation != null && !discAnimation.isRunning() && targetView!=null && targetView.isShown()) {
            discAnimation.cancel();
        }
    }
}
