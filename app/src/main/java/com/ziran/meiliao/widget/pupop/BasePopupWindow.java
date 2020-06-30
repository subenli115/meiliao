package com.ziran.meiliao.widget.pupop;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.irecyclerview.SimpleAnimatorListener;
import com.ziran.meiliao.utils.AnimationUtil;
import com.ziran.meiliao.utils.HandlerUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class BasePopupWindow extends PopupWindow implements View.OnClickListener {
    protected int mWidth;
    protected int mHeight;
    protected View mContentView;
    protected View animView;
    protected Context mContext;
    protected int durtion = 100;
    public BasePopupWindow(Context context) {
        super(context);
        this.mContext = context;
        //计算宽度和高度
        calWidthAndHeight(context);
        setWidth(mWidth);
        setHeight(mHeight);
        mContentView = LayoutInflater.from(context).inflate(getLayoutResId(), null);
        initViews(mContentView);
        //设置布局与相关属性
        setContentView(mContentView);
        setFocusable(true);
        setTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
//        setAnimationStyle(getAnimStyle());
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //点击PopupWindow以外区域时PopupWindow消失
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                }
                return false;
            }
        });

    }

    protected void initViews(View contentView) {

    }

    protected float getHeightPercent() {
        return 1f;
    }

    protected int getLayoutResId() {
        return 0;
    }

    protected int getAnimStyle() {
        return R.style.Animation_Popup;
    }

    public void touchDismiss(int id) {
        ViewUtil.getView(mContentView, id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void touchOuterSize(int contentId){

        ViewUtil.getView(mContentView,contentId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    public void setOnClickListener(int id) {
        View view = ViewUtil.getView(mContentView, id);
        if (view != null) view.setOnClickListener(this);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
//        if (animView != null) {
//           showAnimation();
//        }
    }

    protected void showAnimation() {
        AnimationUtil.startAnimationVer(true, animView, false, durtion, null);
    }

    public void setDurtion(int durtion) {
        this.durtion = durtion;
    }

    /**
     * 设置PopupWindow的大小
     *
     * @param context
     */
    private void calWidthAndHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        //设置高度为全屏高度的70%
        mHeight = (int) (metrics.heightPixels * getHeightPercent());
    }

    protected OnDismissListener mOnDissmisListener;

    public void setOnDissmisListener(OnDismissListener onDissmisListener) {
        mOnDissmisListener = onDissmisListener;
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    @Override
    public void dismiss() {
        if (animView != null) {
            AnimationUtil.startAnimationVer(false, animView, false, durtion, new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    BasePopupWindow.super.dismiss();
                }
            });
        } else {
            super.dismiss();
        }
        if (mOnDissmisListener != null) {
            mOnDissmisListener.onDismiss();
        }
    }

    @Override
    public void onClick(View v) {
    }

    public void setBottomMargin(View view) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.bottomMargin = DisplayUtil.getStatusBarHeight(mContext);
    }

    public <T extends View> T getView(int id) {
        return ViewUtil.getView(mContentView, id);
    }

    public void show(Activity activity){
        PopupWindowUtil.show(activity,this);
    }
    public void show(Fragment fragment){
        PopupWindowUtil.show(fragment.getActivity(),this);
    }
    public void show( ){
        show(AppManager.getAppManager().currentActivity());
    }
}