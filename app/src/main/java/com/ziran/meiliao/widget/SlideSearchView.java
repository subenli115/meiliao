package com.ziran.meiliao.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;

import com.ziran.meiliao.R;


/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/26 12:37
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/26$
 * @updateDes ${TODO}
 */

public class SlideSearchView extends View {


    Paint mTextPaint;
    Paint mBackPaint;
    private String text = "";
    private Bitmap searchBitmap;
    private boolean showFull;
    private int fullWidth;
    private int shortWidth;
    private int textColor;
    private int backgrondColor;
    private float textSize;
    private int defaultShortWidth;
    private ValueAnimator mValueAnimator;
    private RectF mRectF;
    private int defMargin;
    private int duration = 1200;
    private int rx;
    private int mWidth;


    public SlideSearchView(Context context) {
        this(context, null);
    }

    public SlideSearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        defaultShortWidth = (int) TypedValue.applyDimension(1, 120f, getResources().getDisplayMetrics());
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlideSearchView);
        Drawable drawable = null;
        if (array.hasValue(R.styleable.SlideSearchView_ssv_search_icon)) {
            drawable = array.getDrawable(R.styleable.SlideSearchView_ssv_search_icon);
        } else {
            drawable = getResources().getDrawable(R.mipmap.ic_search_white);
        }
        if (drawable instanceof BitmapDrawable) {
            searchBitmap = ((BitmapDrawable) drawable).getBitmap();
        }

        textSize = (int) array.getDimension(R.styleable.SlideSearchView_ssv_text_size,TypedValue.applyDimension(2, 14f, getResources().getDisplayMetrics()));
        defMargin = (int) array.getDimension(R.styleable.SlideSearchView_ssv_margin, TypedValue.applyDimension(1, 8f, getResources().getDisplayMetrics()));
        textColor = array.getColor(R.styleable.SlideSearchView_ssv_text_color, Color.WHITE);
        backgrondColor = array.getColor(R.styleable.SlideSearchView_ssv_back_color,Color.parseColor("#38000000"));
        showFull = array.getBoolean(R.styleable.SlideSearchView_ssv_show_state, false);
        duration = array.getInteger(R.styleable.SlideSearchView_ssv_duration, 1200);

        array.recycle();

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setAntiAlias(true);

        mBackPaint = new Paint();
        mBackPaint.setAntiAlias(true);
        mBackPaint.setDither(true);

        shortWidth = defaultShortWidth;
        mRectF = new RectF();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (fullWidth==0){
            fullWidth = getMeasuredWidth();
            mTextPaint.setTextSize(textSize);
            if (!TextUtils.isEmpty(text)) {
                shortWidth = (int) (defMargin * 3 + searchBitmap.getHeight() + mTextPaint.measureText(text));
            }
            currentWidth = showFull ? fullWidth : shortWidth;
        }
        setMeasuredDimension(currentWidth,getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mRectF.bottom = h;
        mRectF.right = w;
        rx = h / 2;
        bitmapTop = (h - searchBitmap.getHeight()) / 2;
        textCy = rx - getBaseLine(mTextPaint);
    }

    private float textCy;
    private int bitmapTop;
    private float drawLeft;

    @Override
    protected void onDraw(Canvas canvas) {
        mBackPaint.setColor(backgrondColor);
        canvas.drawRoundRect(mRectF, rx, rx, mBackPaint);
        drawLeft = mRectF.left + defMargin;
        canvas.drawBitmap(searchBitmap, drawLeft, bitmapTop, null);
        mTextPaint.setColor(textColor);
        drawLeft += defMargin / 2 + searchBitmap.getWidth();
        canvas.drawText(text, drawLeft, textCy, mTextPaint);
    }

    public void change(boolean showFull) {
        if (mValueAnimator != null && mValueAnimator.isRunning()) {
            mValueAnimator.cancel();
        }
        if (mLayoutParams==null){
            mLayoutParams = getLayoutParams();
        }
        mValueAnimator = ValueAnimator.ofInt(showFull ? shortWidth : fullWidth, showFull ? fullWidth : shortWidth);
        mValueAnimator.setDuration(duration);
        mValueAnimator.setInterpolator(new DecelerateInterpolator());
        mValueAnimator.addUpdateListener(mUpdateListener);
        mValueAnimator.start();
    }

    public void setText(String text) {
        this.text = text;
        requestLayout();
    }

    private int currentWidth;

    public int getBaseLine(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) (fontMetrics.ascent + fontMetrics.descent) / 2;
    }
    private ViewGroup.LayoutParams mLayoutParams;
    private ValueAnimator.AnimatorUpdateListener mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            currentWidth = (int) animation.getAnimatedValue();
            requestLayout();
        }
    };

    public void toggle() {
        this.showFull = !this.showFull;
        change(showFull);
    }
}
