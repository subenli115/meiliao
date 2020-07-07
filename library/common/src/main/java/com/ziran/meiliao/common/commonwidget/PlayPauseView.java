package com.ziran.meiliao.common.commonwidget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.listener.PlayPauseListener;

/**
 * Desc : PlayPauseView
 * Author : Lauzy
 * Date : 2017/8/11
 * Blog : http://www.jianshu.com/u/e76853f863a9
 * Email : freedompaladin@gmail.com
 */
public class PlayPauseView extends View  implements PlayPauseListener {

    private int mWidth; //View宽度
    private int mHeight; //View高度
    private Paint mPaint;
    private Path mLeftPath; //暂停时左侧竖条Path
    private Path mRightPath; //暂停时右侧竖条Path
    private float mGapWidth; //两个暂停竖条中间的空隙,默认为两侧竖条的宽度
    private float mProgress; //动画Progress
    private Rect mRect;
    private boolean isPlaying;
    private int mRectWidth;  //圆内矩形宽度
    private int mRectHeight; //圆内矩形高度
    private int mRectLT;  //矩形左侧上侧坐标
    private int mRadius;  //圆的半径
    private int mBgColor = Color.GRAY;
    private int mShadowColor = Color.WHITE;
    private int mBorderColor = Color.WHITE;
    private int mBtnColor = Color.WHITE;
    private int mDirection = Direction.POSITIVE.value;
    private float mPadding;
    private int shadowColorAlpha;
    private float shadowSize;
    private int mAnimDuration = 300;//动画时间
    private boolean isDrawBorder = true;
    private float borderWidth;

    public PlayPauseView(Context context) {
        super(context);
    }

    public PlayPauseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PlayPauseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mLeftPath = new Path();
        mRightPath = new Path();
        mRect = new Rect();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PlayPauseView);
        mBgColor = ta.getColor(R.styleable.PlayPauseView_pp_bg_color, mBgColor);
        mBorderColor = ta.getColor(R.styleable.PlayPauseView_pp_border_color, mBorderColor);
        mShadowColor = ta.getColor(R.styleable.PlayPauseView_pp_bg_shadow_color, 0);
        mBtnColor = ta.getColor(R.styleable.PlayPauseView_pp_btn_color, mBtnColor);
        mGapWidth = ta.getFloat(R.styleable.PlayPauseView_pp_gap_width, 18);
        mDirection = ta.getInt(R.styleable.PlayPauseView_pp_anim_direction, Direction.POSITIVE.value);
        shadowColorAlpha = ta.getInt(R.styleable.PlayPauseView_pp_bg_shadow_color_alpha, 60);
        mPadding = ta.getFloat(R.styleable.PlayPauseView_pp_space_padding, 0);
        shadowSize = ta.getDimension(R.styleable.PlayPauseView_pp_bg_shadow_size, 0);
        mAnimDuration = ta.getInt(R.styleable.PlayPauseView_pp_anim_duration, 300);
        borderWidth = ta.getDimension(R.styleable.PlayPauseView_pp_border_width, 0);
        isDrawBorder = ta.getBoolean(R.styleable.PlayPauseView_pp_border_show, false);
        ta.recycle();
        initBackground();
    }


    public void initBackground() {
        Drawable background = getBackground();
        if (background != null || shadowSize == 0) return;
        Drawable[] layers = new Drawable[1];
        if (mShadowColor == 0) {
            mShadowColor = mBgColor;
        }
        int alphaColor = alphaColor(mShadowColor, shadowColorAlpha);
        int colors[] = {alphaColor, alphaColor};//分别为开始颜色，中间夜色，结束颜色
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        gd.setShape(GradientDrawable.OVAL);
        layers[0] = gd;
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        setBackgroundDrawable(layerDrawable);
    }

    @SuppressWarnings("unused")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                mWidth = mHeight = Math.min(mWidth, mHeight);
                setMeasuredDimension(mWidth, mHeight);
                break;
            case MeasureSpec.AT_MOST:
                float density = getResources().getDisplayMetrics().density;
                mWidth = mHeight = (int) (50 * density); //默认50dp
                setMeasuredDimension(mWidth, mHeight);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mHeight = w;
        initValue();
    }

    private void initValue() {
        mRadius = mWidth / 2;

        mPadding = getSpacePadding() == 0 ? mRadius / 2.2f : getSpacePadding();
        if (getSpacePadding() > mRadius / Math.sqrt(2) || mPadding < 0) {
            mPadding = mRadius / 3f; //默认值
        }
        float space = (float) (mRadius / Math.sqrt(2) - mPadding); //矩形宽高的一半
        mRectLT = (int) (mRadius - space);
        int rectRB = (int) (mRadius + space);
        mRect.top = mRectLT;
        mRect.bottom = rectRB;
        mRect.left = mRectLT;
        mRect.right = rectRB;
        mRectWidth = mRect.width();
        mRectHeight = mRect.height();
        mGapWidth = getGapWidth() != 0 ? getGapWidth() : mRectWidth / 3;
        mProgress = isPlaying ? 0 : 1;
        mAnimDuration = getAnimDuration() < 0 ? 200 : getAnimDuration();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mLeftPath.rewind();
        mRightPath.rewind();

        mPaint.setColor(mBgColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius - shadowSize, mPaint);

        if (isDrawBorder && borderWidth > 0) {
            mPaint.setStrokeWidth(borderWidth);
            mPaint.setColor(mBorderColor);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius - borderWidth, mPaint);
        }
        mPaint.reset();

        float distance = mGapWidth * (1 - mProgress);  //暂停时左右两边矩形距离
        float barWidth = mRectWidth / 2 - distance / 2f;     //一个矩形的宽度
        float leftLeftTop = barWidth * mProgress;       //左边矩形左上角

        float rightLeftTop = barWidth + distance;       //右边矩形左上角
        float rightRightTop = 2 * barWidth + distance;  //右边矩形右上角
        float rightRightBottom = rightRightTop - barWidth * mProgress; //右边矩形右下角

        mPaint.setColor(mBtnColor);
        mPaint.setStyle(Paint.Style.FILL);

        if (mDirection == Direction.NEGATIVE.value) {
            mLeftPath.moveTo(mRectLT, mRectLT);
            mLeftPath.lineTo(leftLeftTop + mRectLT, mRectHeight + mRectLT);
            mLeftPath.lineTo(barWidth + mRectLT, mRectHeight + mRectLT);
            mLeftPath.lineTo(barWidth + mRectLT, mRectLT);
            mLeftPath.close();

            mRightPath.moveTo(rightLeftTop + mRectLT, mRectLT);
            mRightPath.lineTo(rightLeftTop + mRectLT, mRectHeight + mRectLT);
            mRightPath.lineTo(rightRightBottom + mRectLT, mRectHeight + mRectLT);
            mRightPath.lineTo(rightRightTop + mRectLT, mRectLT);
            mRightPath.close();
        } else {
            mLeftPath.moveTo(leftLeftTop + mRectLT, mRectLT);
            mLeftPath.lineTo(mRectLT, mRectHeight + mRectLT);
            mLeftPath.lineTo(barWidth + mRectLT, mRectHeight + mRectLT);
            mLeftPath.lineTo(barWidth + mRectLT, mRectLT);
            mLeftPath.close();

            mRightPath.moveTo(rightLeftTop + mRectLT, mRectLT);
            mRightPath.lineTo(rightLeftTop + mRectLT, mRectHeight + mRectLT);
            mRightPath.lineTo(rightLeftTop + mRectLT + barWidth, mRectHeight + mRectLT);
            mRightPath.lineTo(rightRightBottom + mRectLT, mRectLT);
            mRightPath.close();
        }

        canvas.save();

        canvas.translate(mRectHeight / 8f * mProgress, 0);

        float progress = isPlaying ? (1 - mProgress) : mProgress;
        int corner = mDirection == Direction.NEGATIVE.value ? -90 : 90;
        float rotation = isPlaying ? corner * (1 + progress) : corner * progress;
        canvas.rotate(rotation, mWidth / 2f, mHeight / 2f);

        canvas.drawPath(mLeftPath, mPaint);
        canvas.drawPath(mRightPath, mPaint);

        canvas.restore();
    }

    private int dip(int value) {
        return (int) TypedValue.applyDimension(1, value, getResources().getDisplayMetrics());
    }

    public ValueAnimator getPlayPauseAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(isPlaying ? 1 : 0, isPlaying ? 0 : 1);
        valueAnimator.setDuration(mAnimDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        return valueAnimator;
    }

    public void play() {
        if (getPlayPauseAnim() != null) {
            getPlayPauseAnim().cancel();
        }
        setPlaying(true);
        getPlayPauseAnim().start();
    }

    public void pause() {
        if (getPlayPauseAnim() != null) {
            getPlayPauseAnim().cancel();
        }
        setPlaying(false);
        getPlayPauseAnim().start();
    }


    /* ------------下方是参数------------- */

    public boolean isPlaying() {
        return isPlaying;
    }

    public void toggle(boolean isPlaying) {
//        LogUtils.logd("toggle:" + isPlaying);
        if (!isPlaying) {
            pause();
        } else {
            play();
        }
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public void setPlaying(boolean playing, boolean isAnimator) {
        if (isAnimator) {
            toggle(playing);
        } else {
            isPlaying = playing;
            invalidate();
        }
    }

    public void setGapWidth(int gapWidth) {
        mGapWidth = gapWidth;
    }

    public float getGapWidth() {
        return mGapWidth;
    }

    public int getBgColor() {
        return mBgColor;
    }

    public int getBtnColor() {
        return mBtnColor;
    }

    public int getDirection() {
        return mDirection;
    }

    public void setBgColor(int bgColor) {
        mBgColor = bgColor;
    }

    public void setBtnColor(int btnColor) {
        mBtnColor = btnColor;
    }

    public void setDirection(Direction direction) {
        mDirection = direction.value;
    }

    public float getSpacePadding() {
        return mPadding;
    }

    public void setSpacePadding(float padding) {
        mPadding = padding;
    }

    public int getAnimDuration() {
        return mAnimDuration;
    }

    public void setAnimDuration(int animDuration) {
        mAnimDuration = animDuration;
    }

    public enum Direction {
        POSITIVE(1),//顺时针
        NEGATIVE(2);//逆时针
        int value;

        Direction(int value) {
            this.value = value;
        }
    }

    public int alphaColor(int color, int alpha) {
        alpha = alpha < 0 ? 0 : (alpha > 255 ? 255 : alpha);
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }
}
