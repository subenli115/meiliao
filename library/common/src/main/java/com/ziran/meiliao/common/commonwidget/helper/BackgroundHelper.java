package com.ziran.meiliao.common.commonwidget.helper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/8 10:39
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/8$
 * @updateDes ${TODO}
 */


//how to used    copy ViewName replse ViewMeReplase

//      private BackgroundHelper mBackgroundHelper;
//    private void initAttrs(Context context, AttributeSet attrs) {
//        if (getBackground()!=null) return;
//        mBackgroundHelper = new BackgroundHelper(this);
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FilterTextView);
//        int defaultClickColor = Color.parseColor("#d9d9d9");
//        int bgColor = 0, bgClickColor = 0;
//        if (a.hasValue(R.styleable.FilterTextView_cs_bg_style)) {
//            int bgStyle = a.getInt(R.styleable.FilterTextView_cs_bg_style, 0);
//            switch (bgStyle) {
//                case 0:
//                    bgClickColor = defaultClickColor;
//                    break;
//                case 1:
//                    bgColor = Color.WHITE;
//                    bgClickColor = defaultClickColor;
//                    break;
//            }
//        } else {
//            bgColor = a.getColor(R.styleable.FilterTextView_cs_normal_bg_color, Color.TRANSPARENT);
//            bgClickColor = a.getColor(R.styleable.FilterTextView_cs_fitter_bg_color, Color.TRANSPARENT);
//        }
//        int bgCircleAngle = a.getDimensionPixelOffset(R.styleable.FilterTextView_cs_bg_radius, 0);
//        int borderWidth = a.getDimensionPixelOffset(R.styleable.FilterTextView_cs_border_width, 0);
//        int borderColor = a.getColor(R.styleable.FilterTextView_cs_border_color, Color.TRANSPARENT); //边框默认为透明色 0
//        int pressTextColor = a.getColor(R.styleable.FilterTextView_csv_pressTextColor, Color.TRANSPARENT); //透明色值为 0
//        a.recycle();
//        //可选
////        mBackgroundHelper.setPressTextColor(pressTextColor);
//        mBackgroundHelper.init(bgColor, bgCircleAngle, bgClickColor, borderWidth, borderColor);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (mBackgroundHelper!=null) mBackgroundHelper.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }

public class BackgroundHelper {
    private int bgColor; //背景色

    private int bgCircleAngle; //背景圆角

    private int bgClickColor; //点击后的背景色

    private int normalStrokeWidth; //未点击的边框宽度

    private int normalStrokeColor; // 未点击的边框色

    private int pressStrokeWidth; //点击后的边框宽度

    private int pressStrokeColor; //点击后的边框颜色

    private int topLeftCA; //上左圆角

    private int topRightCA; //上右圆角

    private int buttonLeftCA; //下左圆角

    private int buttonRightCA; //下右圆角

    private int textColor; //未点击时文字颜色

    private int pressTextColor; //点击时文字颜色

    private View targetView;
    private int mBorderStyle;
    private Paint borderPaint;

    public BackgroundHelper(View targetView) {
        this.targetView = targetView;
    }

    public void init(int bgColor, int bgCircleAngle, int bgClickColor, int normalStrokeWidth, int normalStrokeColor, int
            pressStrokeWidth, int pressStrokeColor, int topLeftCA, int topRightCA, int buttonLeftCA, int buttonRightCA) {
        setSelect(bgColor, bgCircleAngle, bgClickColor, normalStrokeWidth, normalStrokeColor, pressStrokeWidth, pressStrokeColor,
                topLeftCA, topRightCA, buttonLeftCA, buttonRightCA);
    }

    public void init(int bgColor, int bgCircleAngle, int bgClickColor, int normalStrokeWidth, int normalStrokeColor) {
        setSelect(bgColor, bgCircleAngle, bgClickColor, normalStrokeWidth, normalStrokeColor, normalStrokeWidth, normalStrokeColor,
                bgCircleAngle, bgCircleAngle, bgCircleAngle, bgCircleAngle);
    }

    //    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setSelect(int bgColor, int bgCircleAngle, int bgClickColor, int normalStrokeWidth, int normalStrokeColor, int
            pressStrokeWidth, int pressStrokeColor, int topLeftCA, int topRightCA, int buttonLeftCA, int buttonRightCA) {
        if (targetView instanceof TextView) {
            textColor = ((TextView) targetView).getCurrentTextColor();
            pressTextColor = Color.argb((int) (255 * 0.8f), Color.red(textColor), Color.green(textColor), Color.blue(textColor));
        }
        GradientDrawable normal = null;
        GradientDrawable press = null;
        this.bgCircleAngle = bgCircleAngle;
        setNormalStrokeColor(normalStrokeColor);
        setNormalStrokeWidth(normalStrokeWidth);
        setPressStrokeColor(pressStrokeColor);
        setPressStrokeWidth(pressStrokeWidth);
        if (bgColor != 0) {
            bgClickColor = bgClickColor != 0 ? bgClickColor : Color.argb((int) (255 * 0.8f), Color.red(bgColor), Color.green(bgColor),
                    Color.blue(bgColor));
        }
        if (bgCircleAngle != 0) {
            normal = getDrawable(bgCircleAngle, bgColor, normalStrokeWidth, normalStrokeColor);
            press = getDrawable(bgCircleAngle, bgClickColor, pressStrokeWidth, pressStrokeColor);
        } else {
            normal = getDrawable(topLeftCA, topRightCA, buttonLeftCA, buttonRightCA, bgColor, normalStrokeWidth, normalStrokeColor);
            press = getDrawable(topLeftCA, topRightCA, buttonLeftCA, buttonRightCA, bgClickColor, pressStrokeWidth, pressStrokeColor);
        }

        //没有设置点击背景色，表示不设置Selector 只设置Draw
        if (bgClickColor != 0) {
            setBackground(getSelector(normal, press));
        } else {
            setBackground(normal);
        }
    }

    private void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            targetView.setBackground(drawable);
        } else {
            targetView.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 设置背景选择器
     *
     * @param normalDraw
     * @param pressedDraw
     * @return stateListDrawable
     */
    private StateListDrawable getSelector(Drawable normalDraw, Drawable pressedDraw) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDraw);
        stateListDrawable.addState(new int[]{}, normalDraw);
        return stateListDrawable;
    }

    /**
     * 设置shape
     *
     * @param bgCircleAngle
     * @param bgColor
     * @param StrokeWidth
     * @param strokeColor
     * @return gradientDrawable
     */
    private GradientDrawable getDrawable(int bgCircleAngle, int bgColor, int StrokeWidth, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(bgCircleAngle);
        gradientDrawable.setColor(bgColor);
        if (mBorderStyle == -1) {
            gradientDrawable.setStroke(StrokeWidth, strokeColor);
        }
        return gradientDrawable;
    }

    private GradientDrawable getDrawable(float topLeftCA, float topRightCA, float buttonLeftCA, float buttonRightCA, int bgColor, int
            StrokeWidth, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();

        //top-left, top-right, bottom-right, bottom-left
        float[] radius = new float[]{topLeftCA, topLeftCA, topRightCA, topRightCA, buttonLeftCA, buttonLeftCA, buttonRightCA,
                buttonRightCA};
        //这个方法传入的数组长度必须是 > = 8 否则会抛数值下标越界  数组值分别对应 top-left, top-right, bottom-right, bottom-left
        gradientDrawable.setCornerRadii(radius);
        gradientDrawable.setColor(bgColor);
        if (mBorderStyle == -1) {
            gradientDrawable.setStroke(StrokeWidth, strokeColor);
        }
        return gradientDrawable;
    }


    public BackgroundHelper setBgColor(int color) {
        bgColor = color;
        return this;
    }

    public BackgroundHelper setBgCircleAngle(int angle) {
        bgCircleAngle = dp2px(angle);
        return this;
    }

    public BackgroundHelper setBgClickColor(int color) {
        bgClickColor = color;
        return this;
    }

    public BackgroundHelper setNormalStrokeWidth(int width) {
        normalStrokeWidth = dp2px(width);
        return this;
    }

    public BackgroundHelper setNormalStrokeColor(int color) {
        normalStrokeColor = color;
        return this;
    }

    public BackgroundHelper setPressStrokeWidth(int width) {
        pressStrokeWidth = dp2px(width);
        return this;
    }

    public BackgroundHelper setPressStrokeColor(int color) {
        pressStrokeColor = color;
        return this;
    }

    public BackgroundHelper setTopLeftCA(int angle) {
        topLeftCA = dp2px(angle);
        return this;
    }

    public BackgroundHelper setTopRightCA(int angle) {
        topRightCA = dp2px(angle);
        return this;
    }

    public BackgroundHelper setbuttonLeftCA(int angle) {
        buttonLeftCA = dp2px(angle);
        return this;
    }

    public BackgroundHelper setbuttonRightCA(int angle) {
        buttonRightCA = dp2px(angle);
        return this;
    }

    public BackgroundHelper setPressTextColor(int color) {
        pressTextColor = color;
        return this;
    }

    public void build() {
        setSelect(bgColor, bgCircleAngle, bgClickColor, normalStrokeWidth, normalStrokeColor, pressStrokeWidth, pressStrokeColor,
                topLeftCA, topRightCA, buttonLeftCA, buttonRightCA);
    }


    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(float dpValue) {
        final float scale = targetView.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * used In TextView
     *
     * @param event
     */
    public void onTouchEvent(MotionEvent event) {
        if (pressTextColor != 0 && targetView instanceof TextView) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ((TextView) targetView).setTextColor(pressTextColor);
                    break;
                case MotionEvent.ACTION_UP:
                    ((TextView) targetView).setTextColor(textColor);
                    break;
            }
        }
    }

    private static final int LEFT = 1;
    private static final int TOP = 2;
    private static final int RIGHT = 4;
    private static final int BOTTOM = 8;
    private static final int ALL = 15;

    private void initBorderPaint() {
        if (borderPaint == null) {
            borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
    }

    public void onDraw(Canvas canvas) {
        if (mBorderStyle == -1) return;

        initBorderPaint();
        borderPaint.setColor(normalStrokeColor);
        borderPaint.setStrokeWidth(normalStrokeWidth);
        borderPaint.setStyle(Paint.Style.FILL);
        int width = targetView.getWidth();
        int height = targetView.getHeight();
        switch (mBorderStyle) {
            case LEFT:
                canvas.drawLine(0, 0, 0, height, borderPaint);
                break;
            case TOP:
                canvas.drawLine(0, 0, width, 0, borderPaint);
                break;
            case RIGHT:
                canvas.drawLine(width, 0, width, height, borderPaint);
                break;
            case BOTTOM:
                canvas.drawLine(0, height, width, height, borderPaint);
                break;
            case ALL:
                borderPaint.setStyle(Paint.Style.STROKE);
                canvas.drawRoundRect(new RectF(0, 0, width, height),bgCircleAngle,bgCircleAngle,borderPaint );
//                canvas.drawLine(0, 0, 0, height, borderPaint);
//                canvas.drawLine(0, 0, width, 0, borderPaint);
//                canvas.drawLine(width, 0, width, height, borderPaint);
//                canvas.drawLine(0, height, width, height, borderPaint);
                break;
            case LEFT | RIGHT:
                canvas.drawLine(0, 0, 0, height, borderPaint);
                canvas.drawLine(width, 0, width, height, borderPaint);
                break;
            case TOP | BOTTOM:
                canvas.drawLine(0, 0, width, 0, borderPaint);
                canvas.drawLine(0, height, width, height, borderPaint);
                break;
        }
    }

    public void setBorderStyle(int borderStyle) {
        mBorderStyle = borderStyle > 15 ? 15 : borderStyle;
    }
}
