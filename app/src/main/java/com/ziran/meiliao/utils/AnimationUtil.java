package com.ziran.meiliao.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.ziran.meiliao.common.irecyclerview.SimpleAnimatorListener;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     动画管理类,边缘显示和隐藏
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class AnimationUtil {
    /**
     * 动画执行的控件
     */
    private View mAnimationView;
    //是否需要隐藏控件
    private static boolean isCanHideView;
    //是否显示
    private int visible;
    //动画的默认时间
    private int duan = 300;

    public AnimationUtil(View animationView) {
        this.mAnimationView = animationView;
    }

    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return 平移动画
     */
    public TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(duan);
        mHiddenAction.setAnimationListener(animationListener);
        visible = View.GONE;
        mAnimationView.setVisibility(visible);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return 平移动画
     */
    public TranslateAnimation moveToViewLocation() {
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(duan);
        mShowAction.setAnimationListener(animationListener);
        visible = View.VISIBLE;
        return mShowAction;
    }

    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (mAnimationView != null) {
                mAnimationView.setVisibility(visible);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return 平移动画
     */
    public TranslateAnimation moveToViewRight() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,  //fromX
                Animation.RELATIVE_TO_SELF, 0.5f,    //toX
                Animation.RELATIVE_TO_SELF, 0.0f,   //fromY
                Animation.RELATIVE_TO_SELF, 0.0f);  //toY
        mHiddenAction.setDuration(duan);
        visible = View.VISIBLE;
        mHiddenAction.setAnimationListener(animationListener);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return 平移动画
     */
    public TranslateAnimation moveToViewLeft() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.5f,  //fromX
                Animation.RELATIVE_TO_SELF, 0.0f,    //toX
                Animation.RELATIVE_TO_SELF, 0.0f,   //fromY
                Animation.RELATIVE_TO_SELF, 0.0f);  //toY
        mHiddenAction.setDuration(duan);
        visible = View.GONE;
        mHiddenAction.setAnimationListener(animationListener);
        return mHiddenAction;
    }


    /**
     * 菜单显示隐藏动画
     *
     * @param showOrHide true 显示  FALSE 隐藏
     */
    public static void startAnimation(boolean showOrHide, final View view) {
        if (!showOrHide && !isCanHideView) return;//如果是隐藏,并且不能隐藏
        startAnimationVer(showOrHide, view);
    }


    /**
     * 菜单显示隐藏动画
     *
     * @param showOrHide true 显示  FALSE 隐藏
     * @param view       执行动画的控件
     */
    public static void startAnimationVer(final boolean showOrHide, final View view) {
        startAnimationVer(showOrHide, view, true, 800, new SimpleAnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (showOrHide) view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!showOrHide) view.setVisibility(View.GONE);
            }
        });
    }


    /**
     * 菜单显示隐藏动画
     *
     * @param showOrHide       true 显示  FALSE 隐藏
     * @param view             执行动画的控件
     * @param showAlpha        是否改变透明体
     * @param duration         动画时长
     * @param animatorListener 动画监听
     */
    public static void startAnimationVer(final boolean showOrHide, final View view, boolean showAlpha, int duration, Animator
            .AnimatorListener animatorListener) {
        view.measure(0, 0);
        int tabLayoutHeight = view.getMeasuredHeight();
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha = null;
        if (!showOrHide) {
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            if (showAlpha) {
                alpha = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
            }
        } else {
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            if (showAlpha) {
                alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
            }
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height = (int) valueAnimator.getAnimatedValue();
                view.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(duration);
        if (showAlpha) {
            animatorSet.playTogether(valueAnimator, alpha);
        } else {
            animatorSet.playTogether(valueAnimator);
        }
        if (animatorListener != null) {
            animatorSet.addListener(animatorListener);
        }
        animatorSet.start();

    }



    /**
     * 启动旋转动画 （目前在首页减压管的推荐界面标题栏使用）
     *
     * @param view 执行动画的控件
     */
    public static void startAlphaAnimation(final View view) {
        if (view == null) return;
        //参数1：从哪个旋转角度开始
        //参数2：转到什么角度
        //后4个参数用于设置围绕着旋转的圆的圆心在哪里
        //参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
        //参数4：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
        //参数5：确定y轴坐标的类型
        //参数6：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴

        AlphaAnimation rotateAnimation = new AlphaAnimation(1f, 0.2f);
        rotateAnimation.setDuration(800);
        rotateAnimation.setRepeatMode(RotateAnimation.RESTART);
        //插值器（匀速）
        rotateAnimation.setInterpolator(new LinearInterpolator());
        //设置播出次数（-1 为无限循环）
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(rotateAnimation);
    }

    public static void setIsCanHideView(boolean isCanHideView) {
        AnimationUtil.isCanHideView = isCanHideView;
    }

    public static void startAlphaAnimation(final boolean showOrHide, final View viewFragment) {
        startAlphaAnimation(showOrHide, viewFragment, null);
    }

    public static void startAlphaAnimation(final boolean showOrHide, final View viewFragment, Animator.AnimatorListener animatorListener) {
        ObjectAnimator discAnimation = null;
        if (showOrHide) {
            discAnimation = ObjectAnimator.ofFloat(viewFragment, "alpha", 0, 1f);
        } else {
            discAnimation = ObjectAnimator.ofFloat(viewFragment, "alpha", 1f, 0);
        }
        viewFragment.setVisibility(View.VISIBLE);
        discAnimation.setDuration(1200);
        if (animatorListener != null) {
            discAnimation.addListener(animatorListener);
        }
        discAnimation.start();
    }

}
