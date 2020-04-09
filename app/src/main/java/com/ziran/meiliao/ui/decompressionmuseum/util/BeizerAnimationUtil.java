package com.ziran.meiliao.ui.decompressionmuseum.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonwidget.RoundImageView;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/12/2 15:00
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/12/2
 * @updateDes ${TODO}
 */

public class BeizerAnimationUtil implements ValueAnimator.AnimatorUpdateListener {
    private ViewGroup mViewGroup;
    private ImageView formView;
    private View toView;
    private  Point startPoint;
    private Point endPoint;
    private Point controllPoint;
    private int offset = 120;
    private RoundImageView targetView;
    //    private float scale;

    public BeizerAnimationUtil(ViewGroup viewGroup, final ImageView formView, View toView) {
        mViewGroup = viewGroup;
        this.formView = formView;
        this.toView = toView;

        formView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= 16) {
                    formView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    formView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                updateLocation();
            }
        });
    }

    private void updateLocation() {
        int position[] = new int[2];
        mViewGroup.getLocationOnScreen(position);
        int top = position[1];
        formView.getLocationOnScreen(position);
        if (WpyxConfig.getStartPoint()==null){
            startPoint = new Point((position[0] + formView.getWidth() / 2), position[1] - top + formView.getHeight() / 2);
            WpyxConfig.setStartPoint(startPoint);
        }else{
            startPoint = WpyxConfig.getStartPoint();
        }
//        LogUtils.logd("1111f updateLocation:"+ startPoint.toString() + "formView:"+formView);
        toView.getLocationOnScreen(position);
        endPoint = new Point((position[0]), position[1] - top);
        int pointX = (startPoint.x + endPoint.x) / 2;
        int pointY = (int) (startPoint.y +endPoint.y )/2;
//        int pointY = (int) (startPoint.y - convertDpToPixel(offset, formView.getResources()));
        controllPoint = new Point(pointX, pointY);
    }

    private boolean isPlaying;

    public void start() {
        if (isPlaying) return;
        isPlaying = true;
//        updateLocation();
        createTargetView();
        BezierEvaluator bezierEvaluator = new BezierEvaluator(controllPoint);
        ValueAnimator anim = ValueAnimator.ofObject(bezierEvaluator, startPoint, endPoint);
        anim.addUpdateListener(this);
        anim.setDuration(1000);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (targetView != null) mViewGroup.removeView(targetView);
                isPlaying = false;
            }
        });
//        anim.setInterpolator(new DecelerateInterpolator());
                anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
    }

    private void createTargetView() {
        if (targetView == null) {
            targetView = new RoundImageView(mViewGroup.getContext());
            targetView.setType(RoundImageView.TYPE_CIRCLE);
            targetView.setX(formView.getX());
            targetView.setY(formView.getY());
            targetView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        if (formView.getDrawable() != null) {
            targetView.setImageDrawable(formView.getDrawable());
        }
        mViewGroup.addView(targetView, new ViewGroup.LayoutParams(toView.getWidth(), toView.getHeight()));
    }

    public static float convertDpToPixel(float dp, Resources resources) {
        return TypedValue.applyDimension(1, dp, resources.getDisplayMetrics());
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (targetView == null) return;
        Point point = (Point) animation.getAnimatedValue();
        targetView.setX(point.x);
        targetView.setY(point.y);
    }

    public class BezierEvaluator implements TypeEvaluator<Point> {

        private Point controllPoint;

        public BezierEvaluator(Point controllPoint) {
            this.controllPoint = controllPoint;
        }

        @Override
        public Point evaluate(float t, Point startValue, Point endValue) {
            int x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * controllPoint.x + t * t * endValue.x);
            int y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * controllPoint.y + t * t * endValue.y);
            return new Point(x, y);
        }
    }

}
