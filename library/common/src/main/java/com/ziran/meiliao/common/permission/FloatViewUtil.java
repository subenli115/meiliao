package com.ziran.meiliao.common.permission;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.ziran.meiliao.common.commonutils.LogUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/10/12 16:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/10/12$
 * @updateDes ${TODO}
 */

public class FloatViewUtil implements View.OnTouchListener {

    /**
     * 记录手指按下时在小悬浮窗的View上的横坐标的值
     */
    private float xInView;

    /**
     * 记录手指按下时在小悬浮窗的View上的纵坐标的值
     */
    private float yInView;
    /**
     * 记录当前手指位置在屏幕上的横坐标值
     */
    private float xInScreen;

    /**
     * 记录当前手指位置在屏幕上的纵坐标值
     */
    private float yInScreen;

    /**
     * 记录手指按下时在屏幕上的横坐标的值
     */
    private float xDownInScreen;

    /**
     * 记录手指按下时在屏幕上的纵坐标的值
     */
    private float yDownInScreen;

    private boolean isAnchoring = false;
    private boolean isShowing = false;
    private WindowManager windowManager = null;
    private WindowManager.LayoutParams mParams = null;

    private View targetView;
    private Context mContext;

    public FloatViewUtil(View view) {
        this.targetView = view;
        mContext = view.getContext();
        windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        targetView.setOnTouchListener(this);
    }

    public void setParams(WindowManager.LayoutParams params) {
        mParams = params;
    }

    public void setIsShowing(boolean isShowing) {
        this.isShowing = isShowing;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isAnchoring) {
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY();
                // 手指移动的时候更新小悬浮窗的位置
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(xDownInScreen - xInScreen) <= ViewConfiguration.get(mContext).getScaledTouchSlop() && Math.abs(yDownInScreen
                        - yInScreen) <= ViewConfiguration.get(mContext).getScaledTouchSlop()) {
                    // 点击效果
                    targetView.performClick();

                } else {
                    //吸附效果
                    anchorToSide();
                }
                break;
            default:
                break;
        }
        return true;
    }


    private void anchorToSide() {
        isAnchoring = true;
        Point size = new Point();
        int viewWidth = targetView.getWidth();
        int viewHeight = targetView.getHeight();
        windowManager.getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        int middleX = mParams.x + viewWidth / 2;

        int animTime = 0;
        int xDistance = 0;
        int yDistance = 0;

        int dp_25 = dp2px(15);

        //1
        if (middleX <= dp_25 + viewWidth / 2) {
            xDistance = dp_25 - mParams.x;
        }
        //2
        else if (middleX <= screenWidth / 2) {
            xDistance = dp_25 - mParams.x;
        }
        //3
        else if (middleX >= screenWidth - viewWidth / 2 - dp2px(25)) {
            xDistance = screenWidth - mParams.x - viewWidth - dp_25;
        }
        //4
        else {
            xDistance = screenWidth - mParams.x - viewWidth - dp_25;
        }

        //1
        if (mParams.y < dp_25) {
            yDistance = dp_25 - mParams.y;
        }
        //2
        else if (mParams.y + viewHeight + dp_25 >= screenHeight) {
            yDistance = screenHeight - dp_25 - mParams.y - viewHeight;
        }
        LogUtils.logd("xDistance  " + xDistance + "   yDistance" + yDistance);

        animTime = Math.abs(xDistance) > Math.abs(yDistance) ? (int) (((float) xDistance / (float) screenWidth) * 600f) : (int) (((float)
                yDistance / (float) screenHeight) * 900f);
        targetView.post(new AnchorAnimRunnable(Math.abs(animTime), xDistance, yDistance, System.currentTimeMillis()));
    }

    public int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public View getView() {
        return targetView;
    }


    private class AnchorAnimRunnable implements Runnable {

        private int animTime;
        private long currentStartTime;
        private Interpolator interpolator;
        private int xDistance;
        private int yDistance;
        private int startX;
        private int startY;

        public AnchorAnimRunnable(int animTime, int xDistance, int yDistance, long currentStartTime) {
            this.animTime = animTime;
            this.currentStartTime = currentStartTime;
            interpolator = new AccelerateDecelerateInterpolator();
            this.xDistance = xDistance;
            this.yDistance = yDistance;
            startX = mParams.x;
            startY = mParams.y;
        }

        @Override
        public void run() {
            if (System.currentTimeMillis() >= currentStartTime + animTime) {
                isAnchoring = false;
                return;
            }
            float delta = interpolator.getInterpolation((System.currentTimeMillis() - currentStartTime) / (float) animTime);
            int xMoveDistance = (int) (xDistance * delta);
            int yMoveDistance = (int) (yDistance * delta);
            LogUtils.logd("delta:  " + delta + "  xMoveDistance  " + xMoveDistance + "   yMoveDistance  " + yMoveDistance);
            mParams.x = startX + xMoveDistance;
            mParams.y = startY + yMoveDistance;
            if (!isShowing) {
                return;
            }
            windowManager.updateViewLayout(targetView, mParams);
            targetView.postDelayed(this, 16);
        }
    }

    private void updateViewPosition() {
        //增加移动误差
        mParams.x = (int) (xInScreen - xInView);
        mParams.y = (int) (yInScreen - yInView);
//        LogUtils.logd("x  " + mParams.x + "   y  " + mParams.y);
        windowManager.updateViewLayout(targetView, mParams);
    }
}
