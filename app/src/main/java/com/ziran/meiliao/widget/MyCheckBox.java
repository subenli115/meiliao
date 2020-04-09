package com.ziran.meiliao.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Checkable;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;

/**
 * 复选框,自定义动画效果
 * Created by Administrator on 2017/2/9.
 */

public class MyCheckBox extends View implements Checkable {
    private static final String KEY_INSTANCE_STATE = "InstanceState";

    private static final int COLOR_TICK = Color.WHITE;
    private static final int COLOR_UNCHECKED = Color.WHITE;
    private static final int COLOR_CHECKED = Color.parseColor("#45B000");
    private static final int COLOR_FLOOR_UNCHECKED = Color.parseColor("#cccccc");
//    private static final int COLOR_FLOOR_UNCHECKED = Color.parseColor("#DFDFDF");

    private static final int DEF_DRAW_SIZE = 20;
    private static final int DEF_ANIM_DURATION = 300;

    private Paint mPaint, mTickPaint, mFloorPaint, mExtraTextPaint;
    private Point[] mTickPoints;
    private Point mCenterPoint;
    private Path mTickPath;


    private float mLeftLineDistance, mRightLineDistance, mDrewDistance;
    private float mScaleVal = 1.0f, mFloorScale = 1.0f;
    private int mWidth, mAnimDuration, mStrokeWidth;
    private int mCheckedColor, mUnCheckedColor, mFloorColor, mFloorUnCheckedColor;

    private boolean mChecked;
    private boolean openAnimation;
    private boolean mTickDrawing;
    private OnCheckedChangeListener mListener;

    private String extraText = "男";
    private int extraTextAlgin = 0; //0  右侧  1 左侧
    private float textSize;
    private int textColor;

    public MyCheckBox(Context context) {
        this(context, null);
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        textSize = DisplayUtil.sp2px(getResources(), 14f);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyCheckBox);
        int tickColor = ta.getColor(R.styleable.MyCheckBox_mcb_color_tick, COLOR_TICK);
        mAnimDuration = ta.getInt(R.styleable.MyCheckBox_mcb_duration, DEF_ANIM_DURATION);
        mFloorColor = ta.getColor(R.styleable.MyCheckBox_mcb_color_unchecked_stroke, COLOR_FLOOR_UNCHECKED);
        openAnimation = ta.getBoolean(R.styleable.MyCheckBox_mcb_open_animation, false);
        mChecked = ta.getBoolean(R.styleable.MyCheckBox_mcb_scb_checked, false);
        mCheckedColor = ta.getColor(R.styleable.MyCheckBox_mcb_color_checked, COLOR_CHECKED);
        mUnCheckedColor = ta.getColor(R.styleable.MyCheckBox_mcb_color_unchecked, COLOR_UNCHECKED);
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.MyCheckBox_mcb_stroke_width, (int) DisplayUtil.dp2px(getResources(), 0));

        extraText = ta.getString(R.styleable.MyCheckBox_mcb_extra_text);
        textSize = ta.getDimension(R.styleable.MyCheckBox_mcb_extra_text_size, textSize);
        textColor = ta.getColor(R.styleable.MyCheckBox_mcb_extra_text_color, mUnCheckedColor);

        ta.recycle();

        mFloorUnCheckedColor = mFloorColor;
        mTickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTickPaint.setStyle(Paint.Style.STROKE);
        mTickPaint.setStrokeCap(Paint.Cap.ROUND);
        mTickPaint.setColor(tickColor);

        mFloorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFloorPaint.setStyle(Paint.Style.FILL);
        mFloorPaint.setColor(mFloorColor);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mCheckedColor);

        mExtraTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        mTickPath = new Path();
        mCenterPoint = new Point();
        mTickPoints = new Point[3];
        mTickPoints[0] = new Point();
        mTickPoints[1] = new Point();
        mTickPoints[2] = new Point();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
                mTickDrawing = false;
                mDrewDistance = 0;
                setChecked(isChecked(), openAnimation);
            }
        });
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putBoolean(KEY_INSTANCE_STATE, isChecked());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            boolean isChecked = bundle.getBoolean(KEY_INSTANCE_STATE);
            setChecked(isChecked);
            super.onRestoreInstanceState(bundle.getParcelable(KEY_INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        this.setChecked(!isChecked());
    }

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
        reset();
        invalidate();
        if (mListener != null) {
            mListener.onCheckedChanged(MyCheckBox.this, mChecked);
        }
    }

    /**
     * checked with animation
     *
     * @param checked checked
     * @param animate change with animation
     */
    public void setChecked(boolean checked, boolean animate) {
        if (animate) {
            mTickDrawing = false;
            mChecked = checked;
            mDrewDistance = 0f;
            if (checked) {
                startCheckedAnimation();
            } else {
                startUnCheckedAnimation();
            }
            if (mListener != null) {
                mListener.onCheckedChanged(MyCheckBox.this, mChecked);
            }
        } else {
            this.setChecked(checked);
        }
    }

    private void reset() {
        mTickDrawing = true;
        mFloorScale = 1.0f;
        mScaleVal = isChecked() ? 0f : 1.0f;
        mFloorColor = isChecked() ? mCheckedColor : mFloorUnCheckedColor;
        mDrewDistance = isChecked() ? (mLeftLineDistance + mRightLineDistance) : 0;
    }

    private int measureSize(int measureSpec) {
        int defSize = (int) DisplayUtil.dp2px(getResources(), DEF_DRAW_SIZE);
        int specSize = MeasureSpec.getSize(measureSpec);
        int specMode = MeasureSpec.getMode(measureSpec);

        int result = 0;
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                result = Math.min(defSize, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(widthMeasureSpec);
        if (!TextUtils.isEmpty(extraText)) {
            mExtraTextPaint.setTextSize(textSize);
            extraTextWidth = mExtraTextPaint.measureText(extraText) + margin;
            width += extraTextWidth;
        }
        setMeasuredDimension(width, measureSize(heightMeasureSpec));
    }

    private float extraTextWidth;
    private int margin = 30;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mWidth = getMeasuredWidth();

        int realWidth = (int) (mWidth - extraTextWidth);

        mStrokeWidth = (mStrokeWidth == 0 ? realWidth / 10 : mStrokeWidth);
        mStrokeWidth = mStrokeWidth > realWidth / 5 ? realWidth / 5 : mStrokeWidth;
        mStrokeWidth = (mStrokeWidth < 3) ? 3 : mStrokeWidth;
        mCenterPoint.x = realWidth / 2;
        mCenterPoint.y = getMeasuredHeight() / 2;

        mTickPoints[0].x = Math.round((float) realWidth / 30 * 7);
        mTickPoints[0].y = Math.round((float) getMeasuredHeight() / 30 * 14);
        mTickPoints[1].x = Math.round((float) realWidth / 30 * 13);
        mTickPoints[1].y = Math.round((float) getMeasuredHeight() / 30 * 20);
        mTickPoints[2].x = Math.round((float) realWidth / 30 * 22);
        mTickPoints[2].y = Math.round((float) getMeasuredHeight() / 30 * 10);

        mLeftLineDistance = (float) Math.sqrt(Math.pow(mTickPoints[1].x - mTickPoints[0].x, 2) + Math.pow(mTickPoints[1].y -
                mTickPoints[0].y, 2));
        mRightLineDistance = (float) Math.sqrt(Math.pow(mTickPoints[2].x - mTickPoints[1].x, 2) + Math.pow(mTickPoints[2].y -
                mTickPoints[1].y, 2));
        mTickPaint.setStrokeWidth(mStrokeWidth);
    }

    private float textCx, textCy;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (EmptyUtils.isNotEmpty(extraText)) {
            textCx = w - extraTextWidth + margin / 2;
            textCy = h / 2 + ViewUtil.getBaseLine(mExtraTextPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBorder(canvas);
        drawCenter(canvas);
        drawTick(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        if (EmptyUtils.isNotEmpty(extraText)) {
            mExtraTextPaint.setColor(textColor);
            canvas.drawText(extraText, textCx, textCy, mExtraTextPaint);
        }
    }

    private void drawCenter(Canvas canvas) {
        mPaint.setColor(mUnCheckedColor);
        float radius = (mCenterPoint.x - mStrokeWidth) * mScaleVal;
        canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, radius, mPaint);
    }

    private void drawBorder(Canvas canvas) {
        mFloorPaint.setColor(mFloorColor);
        int radius = mCenterPoint.x;
        canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, radius * mFloorScale, mFloorPaint);
    }

    private void drawTick(Canvas canvas) {
        if (mTickDrawing && isChecked()) {
            drawTickPath(canvas);
        }
    }

    private void drawTickPath(Canvas canvas) {
        mTickPath.reset();
        // draw left of the tick
        if (mDrewDistance < mLeftLineDistance) {
            float step = (mWidth / 20.0f) < 3 ? 3 : (mWidth / 20.0f);
            mDrewDistance += step;
            float stopX = mTickPoints[0].x + (mTickPoints[1].x - mTickPoints[0].x) * mDrewDistance / mLeftLineDistance;
            float stopY = mTickPoints[0].y + (mTickPoints[1].y - mTickPoints[0].y) * mDrewDistance / mLeftLineDistance;

            mTickPath.moveTo(mTickPoints[0].x, mTickPoints[0].y);
            mTickPath.lineTo(stopX, stopY);
            canvas.drawPath(mTickPath, mTickPaint);

            if (mDrewDistance > mLeftLineDistance) {
                mDrewDistance = mLeftLineDistance;
            }
        } else {

            mTickPath.moveTo(mTickPoints[0].x, mTickPoints[0].y);
            mTickPath.lineTo(mTickPoints[1].x, mTickPoints[1].y);
            canvas.drawPath(mTickPath, mTickPaint);

            // draw right of the tick
            if (mDrewDistance < mLeftLineDistance + mRightLineDistance) {
                float stopX = mTickPoints[1].x + (mTickPoints[2].x - mTickPoints[1].x) * (mDrewDistance - mLeftLineDistance) /
                        mRightLineDistance;
                float stopY = mTickPoints[1].y - (mTickPoints[1].y - mTickPoints[2].y) * (mDrewDistance - mLeftLineDistance) /
                        mRightLineDistance;

                mTickPath.reset();
                mTickPath.moveTo(mTickPoints[1].x, mTickPoints[1].y);
                mTickPath.lineTo(stopX, stopY);
                canvas.drawPath(mTickPath, mTickPaint);

                float step = (mWidth / 20) < 3 ? 3 : (mWidth / 20);
                mDrewDistance += step;
            } else {
                mTickPath.reset();
                mTickPath.moveTo(mTickPoints[1].x, mTickPoints[1].y);
                mTickPath.lineTo(mTickPoints[2].x, mTickPoints[2].y);
                canvas.drawPath(mTickPath, mTickPaint);
            }
        }

        // invalidate
        if (mDrewDistance < mLeftLineDistance + mRightLineDistance) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    postInvalidate();
                }
            }, 10);
        }
    }

    private void startCheckedAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(1.0f, 0f);
        animator.setDuration(mAnimDuration / 3 * 2);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mScaleVal = (float) animation.getAnimatedValue();
                mFloorColor = getGradientColor(mUnCheckedColor, mCheckedColor, 1 - mScaleVal);
                postInvalidate();
            }
        });
        animator.start();

        ValueAnimator floorAnimator = ValueAnimator.ofFloat(1.0f, 0.8f, 1.0f);
        floorAnimator.setDuration(mAnimDuration);
        floorAnimator.setInterpolator(new LinearInterpolator());
        floorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFloorScale = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        floorAnimator.start();

        drawTickDelayed();
    }

    private void startUnCheckedAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f);
        animator.setDuration(mAnimDuration);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mScaleVal = (float) animation.getAnimatedValue();
                mFloorColor = getGradientColor(mCheckedColor, mFloorUnCheckedColor, mScaleVal);
                postInvalidate();
            }
        });
        animator.start();

        ValueAnimator floorAnimator = ValueAnimator.ofFloat(1.0f, 0.8f, 1.0f);
        floorAnimator.setDuration(mAnimDuration);
        floorAnimator.setInterpolator(new LinearInterpolator());
        floorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFloorScale = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        floorAnimator.start();
    }

    private void drawTickDelayed() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                mTickDrawing = true;
                postInvalidate();
            }
        }, mAnimDuration);
    }

    private static int getGradientColor(int startColor, int endColor, float percent) {
        int startA = Color.alpha(startColor);
        int startR = Color.red(startColor);
        int startG = Color.green(startColor);
        int startB = Color.blue(startColor);

        int endA = Color.alpha(endColor);
        int endR = Color.red(endColor);
        int endG = Color.green(endColor);
        int endB = Color.blue(endColor);

        int currentA = (int) (startA * (1 - percent) + endA * percent);
        int currentR = (int) (startR * (1 - percent) + endR * percent);
        int currentG = (int) (startG * (1 - percent) + endG * percent);
        int currentB = (int) (startB * (1 - percent) + endB * percent);
        return Color.argb(currentA, currentR, currentG, currentB);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener l) {
        this.mListener = l;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MyCheckBox checkBox, boolean isChecked);
    }

    public void setExtraText(String text) {
        this.extraText = text;
        requestLayout();
    }
}