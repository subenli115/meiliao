package com.ziran.meiliao.common.commonwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatImageView;

import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonwidget.helper.RatioViewHelper;


/**
 * 圆角图片控件
 *
 * @author zhy
 */
public class RoundImageView extends AppCompatImageView {
    /**
     * 图片的类型，圆形or圆角
     */
    private int type;
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    /**
     * 圆角大小的默认值
     */
    private static final int BODER_RADIUS_DEFAULT = 18;
    /**
     * 圆角的大小
     */
    private int mBorderRadius;

    /**
     * 圆角的大小
     */
    private float mBorderWidth;

    /**
     * 圆角的颜色
     */
    private int mBorderColor;

    /**
     * 绘图的Paint
     */
    private Paint mBitmapPaint;


    /**
     * 边框的Paint
     */
    private Paint mBorderPaint;

    /**
     * 圆角的半径
     */
    private int mRadius;
    /**
     * 3x3 矩阵，主要用于缩小放大
     */
    private Matrix mMatrix;
    /**
     * 渲染图像，使用图像为绘制图形着色
     */
    private BitmapShader mBitmapShader;

    private RectF mRoundRect;
    private RatioViewHelper mRatioViewHelper;

    public RoundImageView(final Context context, AttributeSet attrs) {

        super(context, attrs);
        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);

        mBorderRadius = a.getDimensionPixelSize(R.styleable.RoundImageView_roundimageview_borderRadius, (int) TypedValue.applyDimension
                (TypedValue.COMPLEX_UNIT_DIP, BODER_RADIUS_DEFAULT, getResources().getDisplayMetrics()));// 默认为10dp
        type = a.getInt(R.styleable.RoundImageView_roundimageview_type, TYPE_ROUND);// 默认为Circle
        float widthRatio = a.getFloat(R.styleable.RoundImageView_roundimageview_width_ratio, 0);
        mBorderColor = a.getColor(R.styleable.RoundImageView_roundimageview_border_color, Color.GRAY);// 默认为Circle
        mBorderWidth = a.getDimension(R.styleable.RoundImageView_roundimageview_border_width, 0);// 默认为Circle
        float heightRatio = a.getFloat(R.styleable.RoundImageView_roundimageview_height_ratio, 0);
        a.recycle();
        mRatioViewHelper = new RatioViewHelper(context, widthRatio, heightRatio);

    }

    public RoundImageView(Context context) {
        this(context, null);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int[] onMeasure = mRatioViewHelper.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 如果类型是圆形，则强制改变view的宽高一致，以小值为准
         */
        if (type == TYPE_CIRCLE) {
            int size = Math.min(onMeasure[0], onMeasure[1]);
            mRadius = size / 2;
            setMeasuredDimension(size, size);
            return;
        }
        setMeasuredDimension(onMeasure[0], onMeasure[1]);
    }

    /**
     * 初始化BitmapShader
     */
    private void setUpShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        Bitmap bmp = mRatioViewHelper.drawableToBitmap(drawable);
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        mBitmapShader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
        float scale = 1.0f;
        if (type == TYPE_CIRCLE) {
            // 拿到bitmap宽或高的小值
            int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
            scale = mRadius * 2.0f / bSize;

        } else if (type == TYPE_ROUND) {
            if (!(bmp.getWidth() == getWidth() && bmp.getHeight() == getHeight())) {
                // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
                scale = Math.max(getWidth() * 1.0f / bmp.getWidth(), getHeight() * 1.0f / bmp.getHeight());
            }

        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mBitmapPaint.setShader(mBitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
//            mRatioViewHelper.loadDefault();
            return;
        }
        if (mBorderRadius == 0) {
            super.onDraw(canvas);
            return;
        }
        setUpShader();
        if (type == TYPE_ROUND) {
            canvas.drawRoundRect(mRoundRect, mBorderRadius, mBorderRadius, mBitmapPaint);
            drawBorder(canvas);
        } else {
            canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
        }
    }

    private void drawBorder(Canvas canvas) {
        if (mBorderWidth > 0) {
            mBorderPaint.setColor(mBorderColor);
            mBorderPaint.setStyle(Paint.Style.STROKE);
            mBorderPaint.setStrokeWidth(mBorderWidth);
            canvas.drawRoundRect(mRoundRect, mBorderRadius, mBorderRadius, mBorderPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 圆角图片的范围
        if (type == TYPE_ROUND) mRoundRect = new RectF(0, 0, w, h);
    }


    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    private static final String STATE_BORDER_RADIUS = "state_border_radius";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, type);
        bundle.putInt(STATE_BORDER_RADIUS, mBorderRadius);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state).getParcelable(STATE_INSTANCE));
            this.type = bundle.getInt(STATE_TYPE);
            this.mBorderRadius = bundle.getInt(STATE_BORDER_RADIUS);
        } else {
            super.onRestoreInstanceState(state);
        }

    }

    public void setBorderRadius(int borderRadius) {
        int pxVal = DisplayUtil.dip2px(getResources(), borderRadius);
        if (this.mBorderRadius != pxVal) {
            this.mBorderRadius = pxVal;
            invalidate();
        }
    }

    public void setType(int type) {
        if (this.type != type) {
            this.type = type;
            if (this.type != TYPE_ROUND && this.type != TYPE_CIRCLE) {
                this.type = TYPE_CIRCLE;
            }
            requestLayout();
        }

    }

    public void setHeightRatio(final float ratio) {
        if (mRatioViewHelper.setHeightRatio(ratio)) requestLayout();
    }
}