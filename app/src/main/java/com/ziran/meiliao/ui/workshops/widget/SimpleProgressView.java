package com.ziran.meiliao.ui.workshops.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ziran.meiliao.R;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/10 18:15
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/10$
 * @updateDes ${TODO}
 */

public class SimpleProgressView extends View {
    private int progress;
    private int finishColor;
    private int unFinishColor;
    private float radius;

    private Paint mPaint;

    public SimpleProgressView(Context context) {
        this(context, null);
    }

    public SimpleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SimpleProgressView);
        finishColor = array.getColor(R.styleable.SimpleProgressView_spv_finish_color, Color.parseColor("#45b000"));
        unFinishColor = array.getColor(R.styleable.SimpleProgressView_spv_finish_color, Color.parseColor("#d8d8d8"));
        progress = array.getInteger(R.styleable.SimpleProgressView_spv_progress, 0);
        progress = checkProgress(progress);
        array.recycle();
        mRectF = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private int fullWidth;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        fullWidth = w - getPaddingLeft() - getPaddingRight();
        mRectF.bottom = h - getPaddingBottom();
        mRectF.left = getPaddingLeft();
        mRectF.top = getPaddingTop();
        radius = mRectF.height() / 2;
    }

    private RectF mRectF;

    @Override
    protected void onDraw(Canvas canvas) {

        mRectF.right = mRectF.left + fullWidth;
        mPaint.setColor(unFinishColor);
        canvas.drawRoundRect(mRectF, radius, radius, mPaint);

        if (progress != 0) {
            mRectF.right = mRectF.left + fullWidth * progress / 100;
            mPaint.setColor(finishColor);
            canvas.drawRoundRect(mRectF, radius, radius, mPaint);
        }
    }

    public void setProgress(int progress) {
        progress = checkProgress(progress);
        if (this.progress != progress) {
            this.progress = progress;
            invalidate();
        }
    }

    private int checkProgress(int progress) {
        progress = progress > 100 ? 100 : (progress < 0 ? 0 : progress);
        return progress;
    }
}
