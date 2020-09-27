package com.ziran.meiliao.widget.pupop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.DisplayUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/9 17:13
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/9$
 * @updateDes ${TODO}
 */

public class PopupWindowUtil {

    private static PopupWindow popupWindow;


    public static PopupWindow createPopupView(Context context, View contentView, View anchorView) {
        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundColor(anchorView.getResources().getColor(R.color.transparent30));
        layout.addView(contentView);
        popupWindow = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
//        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0], location[1] - popupWindow.getHeight());
        popupWindow.showAsDropDown(anchorView);
        return popupWindow;
    }

    public static PopupWindow createPopupView1(Context context, View contentView, View anchorView) {
        dismiss();
        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundColor(anchorView.getResources().getColor(R.color.transparent30));
        layout.removeAllViews();
        layout.addView(contentView);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });
        popupWindow = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
        popupWindow.setAnimationStyle(R.style.mystyle);
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0], location[1] - popupWindow.getHeight());
        return popupWindow;
    }


    public static void dismiss() {
        try {
            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    public static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = DisplayUtil.getScreenHeight(anchorView.getContext());
        final int screenWidth = DisplayUtil.getScreenWidth(anchorView.getContext());
        // 测量contentView
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    public static int[] calculatePopWindowPos1(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationInWindow(anchorLoc);
        // 获取屏幕的高宽
        final int screenWidth = DisplayUtil.getScreenWidth(anchorView.getContext());
        // 测量contentView
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        windowPos[0] = screenWidth - windowWidth;
        windowPos[1] = anchorLoc[1] - windowHeight;
        return windowPos;
    }


    public static void show(PopupWindow popupWindow) {
        show(AppManager.getAppManager().currentActivity(), popupWindow);
    }

    public static void show(Activity activity, PopupWindow popupWindow) {
        View decorView = activity.getWindow().getDecorView();
        if(popupWindow!=null){
            popupWindow.setClippingEnabled(false);
            popupWindow.showAtLocation(decorView, Gravity.BOTTOM, 0, 0);
        }
    }
    public static void showTop(Activity activity, PopupWindow popupWindow) {
        View decorView = activity.getWindow().getDecorView();
        popupWindow.showAtLocation(decorView, Gravity.TOP, 0, 0);
    }
//
//    public static void showBottom(Activity activity, PopupWindow popupWindow) {
//        View decorView = activity.getWindow().getDecorView();
//        int statusBarHeight = -(DisplayUtil.getStatusBarHeight(activity));
//        LogUtils.logd("statusBarHeight" +statusBarHeight);
//        popupWindow.showAtLocation(decorView, Gravity.BOTTOM, 0, statusBarHeight);
//    }
}
