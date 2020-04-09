package com.ziran.meiliao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.OnProgressChangeListener;
import com.ziran.meiliao.ui.settings.widget.DonutProgress;
import com.ziran.meiliao.widget.wheelpicker.WheelPicker;

/**
 * 练习,音乐播放动画效果及时间选择控件
 * Created by Administrator on 2017/2/20.
 */

public class RippleView extends FrameLayout implements WheelPicker.OnItemSelectedListener,OnProgressChangeListener {


    View rootView;
    DonutProgress progress;
    RippleBackground rippleBackground;
    WheelPicker wheelPicker;
    TextView tv_time;
    View fl_content;

    private int rippleColor;
    private float rippleStrokeWidth;
    private float rippleRadius;
    private int rippleDurationTime;
    private int rippleAmount;
    private float rippleScale;
    private boolean needDrawBg;
    private float rippleFix;
    private int rippleType;
    private boolean firstShow;

    public RippleView(Context context) {
        this(context, null);
    }

    public RippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (isInEditMode()) return;

        if (null == attrs) {
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleView);
        if (typedArray.hasValue(R.styleable.RippleView_rv_color)) {
            rippleColor = typedArray.getColor(R.styleable.RippleView_rv_color, 0);
//            rippleBackground.setRippleColor(rippleColor);
        }
        if (typedArray.hasValue(R.styleable.RippleView_rv_strokeWidth)) {
            rippleStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.RippleView_rv_strokeWidth, 0);
//            rippleBackground.setRippleStrokeWidth(rippleStrokeWidth);
        }
        if (typedArray.hasValue(R.styleable.RippleView_rv_radius)) {
            rippleRadius = typedArray.getDimensionPixelSize(R.styleable.RippleView_rv_radius, 0);
//            rippleBackground.setRippleRadius(rippleRadius);
        }
        if (typedArray.hasValue(R.styleable.RippleView_rv_textsize)) {
            int textSize = typedArray.getDimensionPixelSize(R.styleable.RippleView_rv_textsize, 0);
            progress.setTextSize(textSize);
        }
        if (typedArray.hasValue(R.styleable.RippleView_rv_duration)) {
            rippleDurationTime = typedArray.getInt(R.styleable.RippleView_rv_duration, 0);
            rippleBackground.setRippleDurationTime(rippleDurationTime);
        }
        if (typedArray.hasValue(R.styleable.RippleView_rv_rippleAmount)) {
            rippleAmount = typedArray.getInt(R.styleable.RippleView_rv_rippleAmount, 0);
//            rippleBackground.setRippleAmount(rippleAmount);
        }
        if (typedArray.hasValue(R.styleable.RippleView_rv_type)) {
            rippleType = typedArray.getInt(R.styleable.RippleView_rv_type, 0);
//            rippleBackground.setRippleType(rippleType);
        }
        if (typedArray.hasValue(R.styleable.RippleView_rv_first_show)) {
            firstShow = typedArray.getBoolean(R.styleable.RippleView_rv_first_show, true);
//            rippleBackground.setRippleType(rippleType);
        }
        if (typedArray.hasValue(R.styleable.RippleView_rv_scale)) {
            rippleScale = typedArray.getFloat(R.styleable.RippleView_rv_scale, 0);
//            rippleBackground.setRippleScale(rippleScale);
        }
        if (typedArray.hasValue(R.styleable.RippleView_rv_need_draw_bg)) {
            needDrawBg = typedArray.getBoolean(R.styleable.RippleView_rv_need_draw_bg, false);
            if (needDrawBg) {
                setProgressBack();
            }
        }
        rippleFix = typedArray.getFloat(R.styleable.RippleView_rv_fix, 2.0f);
        typedArray.recycle();
    }

    private void initViews() {

        rootView = LayoutInflater.from(getContext()).inflate(R.layout.custom_rippler_view, this, true);
        rippleBackground = ViewUtil.getView(rootView, R.id.rippleBackground);
        progress = ViewUtil.getView(rootView, R.id.progress);
        fl_content = ViewUtil.getView(rootView, R.id.fl_content);
        wheelPicker = ViewUtil.getView(rootView, R.id.weelPicker);
        tv_time = ViewUtil.getView(rootView, R.id.tv_picker_fen);
        changeSize();
        wheelPicker.setOnItemSelectedListener(this);
        progress.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onResStartListener != null) {
                    onResStartListener.onResStart();
                }
            }
        });
        wheelPicker.setmCurrentItemPosition(2);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
    }

    private void changeSize() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams params = fl_content.getLayoutParams();
                params.width = params.height = (int) (mWidth / rippleFix);
                fl_content.requestLayout();
                rippleRadius = params.width / 2;
                rippleBackground.setAllAttrs(rippleColor, rippleAmount, rippleRadius, rippleScale, rippleStrokeWidth, rippleType,
                        rippleDurationTime);
                if (firstShow) {
                    setVisibility(VISIBLE);
                }
            }
        }, 10);
    }

    int mWidth;

    public void setProgressColor(int color) {
        progress.setFinishedStrokeColor(color);
    }

    public void showPicker() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                wheelPicker.setVisibility(VISIBLE);
                tv_time.setVisibility(VISIBLE);
                progress.setShowText(false);
            }
        }, 12);
    }

    public void setProgressBackground(int color) {
        progress.setInnerBackgroundColor(getResources().getColor(color));
    }


    public void hidePicker(boolean setMax) {
        try {
            wheelPicker.setVisibility(GONE);
            tv_time.setVisibility(GONE);
            progress.setShowText(true);
            if (setMax) {
                int max = Integer.parseInt(wheelPicker.getItem().toString()) * 60;
                progress.setMax(max);
            }
            setResProgress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hidePicker() {
        try {
            wheelPicker.setVisibility(GONE);
            tv_time.setVisibility(GONE);
            progress.setShowText(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean animationRunning;

    public void startRippleAnimation() {
        if (animationRunning) return;
        animationRunning = true;
        rippleBackground.startRippleAnimation();
    }


    public long getWheelPickerTime() {
        return Integer.parseInt(wheelPicker.getItem().toString()) * 60 * 1000;
    }


    public void stopRippleAnimation() {
        if (!animationRunning) return;
        animationRunning = false;
        rippleBackground.stopRippleAnimation();
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {

    }

    boolean isPause = false;

    public void setPause(String pause) {
        if (EmptyUtils.isNotEmpty(pause)) {
            progress.setCourseText(pause);
            isPause = true;
            stopRippleAnimation();
        } else {
            progress.setShowText(true);
            progress.setCourseText(null);
            startRippleAnimation();
            isPause = false;
        }
    }



    public void setMax(int max) {
        progress.setMax(max);
    }

    public void setMax(String s) {
        progress.setMax(TimeUtil.getMax(s));
    }

    public void setProgressTextColor(int color) {
        progress.setTextColor(color);
    }

    OnResStartListener onResStartListener;

    public void setOnResStartListener(OnResStartListener onResStartListener) {
        this.onResStartListener = onResStartListener;
    }


//    public void setCurrentPosition(int currProgress) {
//        progress.setProgress(currProgress);
//    }

    public int getMax() {
        return progress.getMax();
    }

    public void setTimeTextSize() {
        progress.setTimeTextSize(0);
    }

    public int getCurrentProgress() {
        return (int) progress.getProgress();
    }

    @Override
    public void setProgress(int pos) {
        progress.setProgress(pos);
    }

    public interface OnResStartListener {
        void onResStart();
    }


    public void setProgressBack() {

        progress.setBackgroundBitmap(getResources().getDrawable(R.mipmap.ic_jyg_player_bg_green));
    }

    public void setResProgress() {
        progress.setProgress(0f);
    }


    public boolean isAnimationRunning() {
        return rippleBackground.isRippleAnimationRunning();
    }


    public void setIsShowArrow(boolean isShowArrow) {
        progress.setShowAllow(isShowArrow);
    }

    public void setProgressChangeListener(DonutProgress.OnProgressChangeListener changeListener) {
        progress.setChangeListener(changeListener);
    }
}
