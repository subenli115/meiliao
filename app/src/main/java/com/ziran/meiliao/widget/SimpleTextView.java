package com.ziran.meiliao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.ziran.meiliao.R;


/**
 * author 吴祖清
 * create  2017/3/31 10
 * des      左侧有颜色块的文本控件
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class SimpleTextView extends View {

    /**
     *  Color颜色
     */
    public int leftColor, textColor;

    /**
     *
     */
    private String text;
    private Paint textPaint;
    private Paint colorPaint;
    private float textSize;
    private int block_width, text_margin_left;
    private int mWidth, mHeight;
    private Rect mRect;

    public SimpleTextView (Context context) {
        this(context, null);
    }

    public SimpleTextView (Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleTextView (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
        initPaint();
    }

    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void initViews (Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SimpleTextView);
        leftColor = array.getColor(R.styleable.SimpleTextView_leftColor, getResources().getColor(R.color.textColor_teshe));
        textColor = array.getColor(R.styleable.SimpleTextView_textColor, getResources().getColor(R.color.textColor_333));
        textSize = array.getDimension(R.styleable.SimpleTextView_textSize, getResources().getDimensionPixelSize(R.dimen.button_textsize));
        text = array.getString(R.styleable.SimpleTextView_text);
        array.recycle();
    }

    private void initPaint () {
        textPaint = new Paint();
        textPaint.setFakeBoldText(true);
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(textSize);

        colorPaint = new Paint();
        colorPaint.setAntiAlias(true);
        colorPaint.setColor(leftColor);
        mRect = new Rect();
        block_width = getResources().getDimensionPixelSize(R.dimen.block_width);
        text_margin_left = getResources().getDimensionPixelSize(R.dimen.text_margin_left);
    }


    @Override
    protected void onDraw (Canvas canvas) {
        if (TextUtils.isEmpty(text))
            return;
        canvas.drawRect(0, 0, block_width, mHeight, colorPaint);
        textPaint.getTextBounds(text, 0, text.length(), mRect);
        int baseLine = getBaseLine(textPaint);
        int y = mHeight / 2 - baseLine;
        canvas.drawText(text, text_margin_left, y, textPaint);

    }

    public int getBaseLine (Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) (fontMetrics.ascent + fontMetrics.descent) / 2;
    }

    public void setTitle(String text){
        this.text = text;
        invalidate();
    }
}
