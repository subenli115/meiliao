package com.ziran.meiliao.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.ziran.meiliao.R;

import java.util.ArrayList;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des       练习和播放器,动画效果控件
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class RippleBackground extends RelativeLayout {
    /**
     * 波纹条数默认为4条 有多少条波纹就创建多少个RippleView控件
     */
    private int rippleAmount;
    private static final int DEFAULT_RIPPLE_COUNT = 4;
    private ArrayList<RippleView> rippleViewList = new ArrayList<>();
    /**
     * 波纹执行一次的动画时长 默认为6000毫秒
     */
    private int rippleDurationTime;
    private static final int DEFAULT_DURATION_TIME = 6000;
    /**
     * 每个RippleView执行一次动画的时长
     */
    private int rippleDelay;

    /**
     * 波纹的放大倍数 默认为2倍
     */
    private float rippleScale;
    private static final float DEFAULT_SCALE = 2.0f;

    /**
     * 默认的波纹类型 （ 0： 实心  ， 1： 空心  2：空心且设置了波纹的宽度）
     */
    private int rippleType;
    private static final int DEFAULT_FILL_TYPE = 0;
    private static final int TYPE_STROKE = 1;
    private static final int TYPE_FILL_AND_STROKE = 2;
    /**
     * 波纹的颜色
     */
    private int rippleColor;
    /**
     * 波纹的空心宽度
     */
    private float rippleStrokeWidth;

    /**
     * 波纹的半径
     */
    private float rippleRadius;

    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 标记是否正在动画
     */
    private boolean animationRunning = false;
    /**
     * 动画效果的集合
     */
    private AnimatorSet animatorSet;

    /**
     * 动画的集合
     */
    private ArrayList<Animator> animatorList;

    private LayoutParams rippleParams;


    public RippleBackground(Context context) {
        super(context);
    }

    public RippleBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RippleBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化属性
     *
     * @param context 上下文对象
     * @param attrs   属性集合
     */
    private void init(final Context context, final AttributeSet attrs) {
        if (isInEditMode())
            return;

        if (null == attrs) {
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleBackground);
        rippleColor = typedArray.getColor(R.styleable.RippleBackground_rb_color, getResources().getColor(R.color.rippelColor));
        rippleStrokeWidth = typedArray.getDimension(R.styleable.RippleBackground_rb_strokeWidth, getResources().getDimension(R.dimen.rippleStrokeWidth));
        rippleRadius = typedArray.getDimension(R.styleable.RippleBackground_rb_radius, getResources().getDimension(R.dimen.rippleRadius));
        rippleDurationTime = typedArray.getInt(R.styleable.RippleBackground_rb_duration, DEFAULT_DURATION_TIME);
        rippleAmount = typedArray.getInt(R.styleable.RippleBackground_rb_rippleAmount, DEFAULT_RIPPLE_COUNT);
        rippleScale = typedArray.getFloat(R.styleable.RippleBackground_rb_scale, DEFAULT_SCALE);
        rippleType = typedArray.getInt(R.styleable.RippleBackground_rb_type, DEFAULT_FILL_TYPE);
        typedArray.recycle();

        rippleDelay = rippleDurationTime / rippleAmount;

        paint = new Paint();
        paint.setAntiAlias(true);
        setRippleType();
        paint.setColor(rippleColor);
        int width;
        width = (int) (2 * (rippleRadius + rippleStrokeWidth));

        rippleParams = new LayoutParams(width, width);
        rippleParams.addRule(CENTER_IN_PARENT, TRUE);

        animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorList = new ArrayList<>();

        for (int i = 0; i < rippleAmount; i++) {
            RippleView rippleView = new RippleView(getContext());
            addView(rippleView, rippleParams);
            rippleViewList.add(rippleView);
            addAnimator(i, rippleView);
        }

        animatorSet.playTogether(animatorList);
    }

    /**
     * 给画笔设置属性
     */
    private void setRippleType() {
        if (rippleType == DEFAULT_FILL_TYPE) {
            rippleStrokeWidth = 0;
            paint.setStyle(Paint.Style.FILL);
        }
        if (rippleType == TYPE_STROKE) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(0);
        } else if (rippleType == TYPE_FILL_AND_STROKE) {
            paint.setStrokeWidth(0);
            paint.setStyle(Paint.Style.STROKE);
        }
    }

    /**
     * 给 RippleView 添加动画
     *
     * @param i          索引
     * @param rippleView 动画执行的控件
     */
    private void addAnimator(int i, RippleView rippleView) {
        final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rippleView, "ScaleX", 1.0f, rippleScale);
        scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
        scaleXAnimator.setStartDelay(i * rippleDelay);
        scaleXAnimator.setDuration(rippleDurationTime);
        animatorList.add(scaleXAnimator);
        final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rippleView, "ScaleY", 1.0f, rippleScale);
        scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
        scaleYAnimator.setStartDelay(i * rippleDelay);
        scaleYAnimator.setDuration(rippleDurationTime);
        animatorList.add(scaleYAnimator);
        final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(rippleView, "Alpha", 1.0f, 0f);
        alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
        alphaAnimator.setStartDelay(i * rippleDelay);
        alphaAnimator.setDuration(rippleDurationTime);
        animatorList.add(alphaAnimator);
    }

    /**
     * 测量，设置宽和高是一样的
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        // Children are just made to fill our space.
        int childWidthSize = getMeasuredWidth();
        //高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 波纹控件
     */
    private class RippleView extends View {

        public RippleView(Context context) {
            super(context);
            this.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int radius = (Math.min(getWidth(), getHeight())) / 2;    //中心点
            paint.setColor(rippleColor);
            if (rippleType == TYPE_FILL_AND_STROKE) {
                canvas.drawCircle(radius, radius, radius - rippleStrokeWidth / 2, paint);
            } else {
                canvas.drawCircle(radius, radius, radius - rippleStrokeWidth, paint);
            }
        }

    }

    /**
     * 开启动画
     */
    public void startRippleAnimation() {
        if (!isRippleAnimationRunning()) {
            for (RippleView rippleView : rippleViewList) {
                rippleView.setVisibility(VISIBLE);
            }
            animatorSet.start();
            animationRunning = true;
        }
    }

    /**
     * 结束动画
     */
    public void stopRippleAnimation() {
        if (isRippleAnimationRunning()) {
            animatorSet.end();
            animationRunning = false;
        }
    }

    public int getRippleColor() {
        return rippleColor;
    }

    /**
     * 设置波纹的颜色
     *
     * @param rippleColor 波纹的颜色
     */
    public void setRippleColor(int rippleColor) {
        if (this.rippleColor != rippleColor) {
            this.rippleColor = rippleColor;
            resInvalidate(true);
        }
    }

    /**
     * 重新绘制波纹
     *
     * @param invalidateOrrequestLayout true 调用onDraw() false 调用 requestLayout()
     */
    private void resInvalidate(boolean invalidateOrrequestLayout) {
        for (int i = 0; i < rippleViewList.size(); i++) {
            if (invalidateOrrequestLayout) {
                rippleViewList.get(i).postInvalidate();
            } else {
                rippleViewList.get(i).requestLayout();
            }
        }
    }

    public float getRippleStrokeWidth() {
        return rippleStrokeWidth;
    }

    /**
     * 设置波纹的画笔空心宽度
     *
     * @param rippleStrokeWidth 画笔空心宽度
     */
    public void setRippleStrokeWidth(float rippleStrokeWidth) {
        if (this.rippleStrokeWidth == rippleStrokeWidth)
            return;
        this.rippleStrokeWidth = rippleStrokeWidth;
        setRippleType();
        resRequestLayout(rippleStrokeWidth);
    }

    private void resRequestLayout(float rippleStrokeWidth) {
        rippleParams.width = rippleParams.height = (int) (2 * (rippleRadius + rippleStrokeWidth));
        resInvalidate(false);
    }

    public float getRippleRadius() {
        return rippleRadius;
    }

    /**
     * 设置波纹的半径
     *
     * @param rippleRadius 波纹半径
     */
    public void setRippleRadius(float rippleRadius) {
        if (this.rippleRadius == rippleRadius)
            return;
        this.rippleRadius = rippleRadius;
        setRippleType();
        resRequestLayout(rippleStrokeWidth);
        //        resInvalidate(false);
    }

    public int getRippleDurationTime() {
        return rippleDurationTime;
    }

    /**
     * 设置波纹总动画时长
     *
     * @param rippleDurationTime 动画时长
     */
    public void setRippleDurationTime(int rippleDurationTime) {
        if (this.rippleDurationTime == rippleDurationTime)
            return;
        this.rippleDurationTime = rippleDurationTime;
        rippleDelay = rippleDurationTime / rippleAmount;
        animatorList.clear();
        for (int i = 0; i < rippleViewList.size(); i++) {
            addAnimator(i, rippleViewList.get(i));
        }
        animatorSet.playTogether(animatorList);
    }

    public int getRippleAmount() {
        return rippleAmount;
    }

    /**
     * 设置波纹的数量
     *
     * @param rippleAmount 波纹数量
     */
    public void setRippleAmount(int rippleAmount) {
        if (this.rippleAmount == rippleAmount)
            return;
        this.rippleAmount = rippleAmount;

    }

    public float getRippleScale() {
        return rippleScale;
    }

    /**
     * 设置波纹的缩放比例
     *
     * @param rippleScale 缩放比例
     */
    public void setRippleScale(float rippleScale) {
        if (this.rippleScale == rippleScale)
            return;
        this.rippleScale = rippleScale;
        animatorList.clear();
        for (int i = 0; i < rippleViewList.size(); i++) {
            addAnimator(i, rippleViewList.get(i));
        }
        animatorSet.playTogether(animatorList);
    }

    public int getRippleType() {
        return rippleType;
    }

    public void setAllAttrs(int rippleColor, int rippleAmount, float rippleRadius, float rippleScale, float rippleStrokeWidth,
                            int rippleType, int rippleDurationTime) {
        if (rippleColor != 0 && this.rippleColor != rippleColor) {
            this.rippleColor = rippleColor;
        }
        if (rippleAmount != 0 && this.rippleAmount != rippleAmount) {
            this.rippleAmount = rippleAmount;
        }
        if (rippleRadius != 0 && this.rippleRadius != rippleRadius) {
            this.rippleRadius = rippleRadius;
        }
        if (rippleScale != 0 && this.rippleScale != rippleScale) {
            this.rippleScale = rippleScale;
            setRippleScale(rippleScale);
        }
        if (rippleStrokeWidth != 0 && this.rippleStrokeWidth != rippleStrokeWidth) {
            this.rippleStrokeWidth = rippleStrokeWidth;
        }
        if (this.rippleType != rippleType) {
            this.rippleType = rippleType;
            setRippleType();
        }
        resRequestLayout(rippleStrokeWidth);
        if (this.rippleDurationTime != 0 && this.rippleDurationTime != rippleDurationTime) {
            this.rippleDurationTime = rippleDurationTime;
            setRippleDurationTime(rippleDurationTime);
        }
    }

    /**
     * 设置波纹的类型
     *
     * @param rippleType
     */
    public void setRippleType(int rippleType) {
        if (this.rippleType == rippleType)
            return;
        this.rippleType = rippleType;
        setRippleType();
    }

    public boolean isRippleAnimationRunning() {
        return animationRunning;
    }
}
