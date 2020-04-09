//package com.ziran.meiliao.common.commonwidget;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.RectF;
//import android.graphics.drawable.Drawable;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.AppCompatImageView;
//import android.util.AttributeSet;
//import android.util.TypedValue;
//
//import com.ziran.meiliao.common.R;
//import com.ziran.meiliao.common.commonwidget.helper.RatioViewHelper;
//
///**
// * @author 吴祖清
// * @version $Rev$
// * @createTime 2017/12/1 11:08
// * @des ${TODO}
// * @updateAuthor $Author$
// * @updateDate 2017/12/1$
// * @updateDes ${TODO}
// */
//
//public class RoundImageView1 extends AppCompatImageView {
//    /**
//     * 图片的类型，圆形or圆角
//     */
//    private int type;
//    public static final int TYPE_CIRCLE = 0;
//    public static final int TYPE_ROUND = 1;
//    /**
//     * 圆角大小的默认值
//     */
//    private static final int BODER_RADIUS_DEFAULT = 4;
//    private RatioViewHelper mRatioViewHelper;
//    private Paint mBorderPaint;
//    private boolean usedRatio;
//    private RectF mRectF;
//    private float mBorderRadius = 12;
//    private int mBorderColor = Color.parseColor("#fafafa");
//    private float mBorderWidth;
//
//    public RoundImageView1(Context context) {
//        this(context, null);
//    }
//
//    public RoundImageView1(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public RoundImageView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
//        mBorderRadius = a.getDimensionPixelSize(R.styleable.RoundImageView_roundimageview_borderRadius, (int) TypedValue.applyDimension
//                (TypedValue.COMPLEX_UNIT_DIP, BODER_RADIUS_DEFAULT, getResources().getDisplayMetrics()));// 默认为10dp
//        type = a.getInt(R.styleable.RoundImageView_roundimageview_type, TYPE_ROUND);// 默认为Circle
//        float widthRatio = a.getFloat(R.styleable.RoundImageView_roundimageview_width_ratio, 0);
//        usedRatio = a.getBoolean(R.styleable.RoundImageView_roundimageview_used_ratio, true);
//        mBorderColor = a.getColor(R.styleable.RoundImageView_roundimageview_border_color, mBorderColor);// 默认为Circle
//        mBorderWidth = a.getDimension(R.styleable.RoundImageView_roundimageview_border_width, 0);// 默认为Circle
//        float heightRatio = a.getFloat(R.styleable.RoundImageView_roundimageview_height_ratio, 0);
//        a.recycle();
//        mRatioViewHelper = new RatioViewHelper(context, widthRatio, heightRatio);
//        initBorderPaint();
//    }
//
//    private void initBorderPaint() {
//        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mBorderPaint.setDither(true);
//        mBorderPaint.setStyle(Paint.Style.STROKE);
//        mBorderPaint.setColor(mBorderColor);
//        mBorderWidth = mBorderWidth == 0 ? mBorderRadius : mBorderWidth;
//        mBorderPaint.setStrokeWidth(mBorderWidth);
//        mRectF = new RectF();
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        if (usedRatio) {
//            int[] onMeasure = mRatioViewHelper.onMeasure(widthMeasureSpec, heightMeasureSpec);
//            setMeasuredDimension(onMeasure[0], onMeasure[1]);
//        } else {
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        }
//    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        mRectF.left = 0;
//        mRectF.top = 0;
//        mRectF.right = w;
//        mRectF.bottom = h;
//    }
//
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        if (mBorderRadius > 0 && mBorderColor != 0) {
//            canvas.drawRoundRect(mRectF, mBorderRadius, mBorderRadius, mBorderPaint);
//        }
//    }
//
//    @Override
//    public void setImageBitmap(Bitmap bm) {
//        if (bm != null) {
//            float ratio = bm.getHeight() * 1f / bm.getWidth();
//            setRatioHeight(ratio);
//        }
//        super.setImageBitmap(bm);
//    }
//
//    @Override
//    public void setImageDrawable(@Nullable Drawable drawable) {
//        if (drawable != null) {
//            float ratio = drawable.getIntrinsicHeight() * 1f / drawable.getIntrinsicWidth();
//            setRatioHeight(ratio);
//        }
//        super.setImageDrawable(drawable);
//
//    }
//
//    public void setRatioHeight(float ratioHeight) {
//        if (mRatioViewHelper != null && mRatioViewHelper.setHeightRatio(ratioHeight) && usedRatio) requestLayout();
//    }
//
//    public void setRatioWidth(float ratioWidth) {
//        if (mRatioViewHelper != null && mRatioViewHelper.setWidthRatio(ratioWidth) && usedRatio) requestLayout();
//    }
//}
