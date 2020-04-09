package com.ziran.meiliao.widget.pupop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;

import io.rong.message.InformationNotificationMessage;

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


    public static PopupWindow showTipJoinRoom(final View anchorView, final View.OnClickListener onClickListener,
                                              InformationNotificationMessage msg) {
        dismiss();
        final View contentView = LayoutInflater.from(anchorView.getContext()).inflate(R.layout.popuw_content_top_arrow_layout, null);
        TextView tv = ViewUtil.getView(contentView, R.id.tip_text);
        String text = String.format("%s%s", msg.getUserInfo().getName(), msg.getMessage());
        tv.setText(text);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 创建PopupWindow时候指定高宽时showAsDropDown能够自适应
        // 如果设置为wrap_content,showAsDropDown会认为下面空间一直很充足（我以认为这个Google的bug）
        // 备注如果PopupWindow里面有ListView,ScrollView时，一定要动态设置PopupWindow的大小
        popupWindow = new PopupWindow(contentView, contentView.getMeasuredWidth(), contentView.getMeasuredHeight(), false);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        if (onClickListener != null) {
            contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    onClickListener.onClick(v);
                }
            });
        }
//        ViewUtil.addOnGlobalLayoutListener(spotlightView, new ViewUtil.BaseCallBack() {
//            @Override
//            public void call() {
//                layout.setY(point.y - radius - 100 - layout.getHeight());
//            }
//        });
//        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                // 自动调整箭头的位置
//                contentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//            }
//        });
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // setOutsideTouchable设置生效的前提是setTouchable(true)和setFocusable(false)
        popupWindow.setOutsideTouchable(true);

        // 设置为true之后，PopupWindow内容区域 才可以响应点击事件
        popupWindow.setTouchable(true);

        // true时，点击返回键先消失 PopupWindow
        // 但是设置为true时setOutsideTouchable，setTouchable方法就失效了（点击外部不消失，内容区域也不响应事件）
        // false时PopupWindow不处理返回键
        popupWindow.setFocusable(false);

        // 如果希望showAsDropDown方法能够在下面空间不足时自动在anchorView的上面弹出
        // 必须在创建PopupWindow的时候指定高度，不能用wrap_content
//        popupWindow.showAtLocation(anchorView,Gravity.BOTTOM,0,0);
        int windowPos[] = PopupWindowUtil.calculatePopWindowPos1(anchorView, contentView);

        popupWindow.showAtLocation(anchorView, Gravity.TOP | Gravity.START, DisplayUtil.dip2px(12), windowPos[1]);
        return popupWindow;
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
        popupWindow.showAtLocation(decorView, Gravity.NO_GRAVITY, 0, 0);
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
