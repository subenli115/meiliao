package com.ziran.meiliao.ui.workshops.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/10 18:15
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/10$
 * @updateDes ${TODO}
 */

public class PeopleSelectView extends View {
    private int progress;
    private int finishColor = Color.parseColor("#45b000");
    private int unFinishColor = Color.parseColor("#d8d8d8");
    private int textColor = Color.parseColor("#999999");
    private int lineColor = Color.parseColor("#F7F7F5");
    //    private int lineColor = Color.parseColor("#F7F7F5");
    private int progressHeight;
    private int thumbHeight;
    private float textSize = 22f;
    private float lineWidth;
    private Paint mPaint;
    private Paint mTextPaint;
    private List<Item> mItems;

    private Rect mRect;
    private RectF mRectF;

    private float radius;

    private float textHeight;
    private float cy;
    private int fullWidth;
    private int oneWidth;

    private int oneTextTotalProgress = 50;
    private int maxProgress;
    private Drawable mTouchThumbDrawable;
    private Drawable mUnTouchThumbDrawable;
    private String extraText = "¥";

    public PeopleSelectView(Context context) {
        this(context, null);
    }

    public PeopleSelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PeopleSelectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
        initData();

    }

    private void initData() {
        mItems = getDefaultData();
        maxProgress = oneTextTotalProgress * (mItems.size() - 1);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        progressHeight = getValue(2f);
        thumbHeight = getValue(30f);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PriceSelectView);
        textSize = array.getDimension(R.styleable.PriceSelectView_psv_text_size, DisplayUtil.sp2px(getResources(), 14f));
        lineWidth = array.getDimension(R.styleable.PriceSelectView_psv_line_width, 0);
        if (array.hasValue(R.styleable.PriceSelectView_psv_extra)) {
            extraText = array.getString(R.styleable.PriceSelectView_psv_extra);
        }
        progressHeight = array.getDimensionPixelSize(R.styleable.PriceSelectView_psv_progress_height, progressHeight);
        thumbHeight = array.getDimensionPixelSize(R.styleable.PriceSelectView_psv_thumb_height, thumbHeight);
        textColor = array.getColor(R.styleable.PriceSelectView_psv_text_color, textColor);
        lineColor = array.getColor(R.styleable.PriceSelectView_psv_line_color, lineColor);
        finishColor = array.getColor(R.styleable.PriceSelectView_psv_finish_color, finishColor);
        unFinishColor = array.getColor(R.styleable.PriceSelectView_psv_un_finish_color, unFinishColor);
        progress = array.getInteger(R.styleable.PriceSelectView_psv_progress, 0);
        progress = checkProgress(progress);

        if (array.hasValue(R.styleable.PriceSelectView_psv_thumb_touch)) {
            mTouchThumbDrawable = array.getDrawable(R.styleable.PriceSelectView_psv_thumb_touch);
        }
        if (mTouchThumbDrawable == null) {
            mTouchThumbDrawable = getResources().getDrawable(R.mipmap.ic_sjk_video_playing_dot_pre);
        }
        if (array.hasValue(R.styleable.PriceSelectView_psv_thumb_un_touch)) {
            mUnTouchThumbDrawable = array.getDrawable(R.styleable.PriceSelectView_psv_thumb_un_touch);
        }
        if (mUnTouchThumbDrawable == null) {
            mUnTouchThumbDrawable = getResources().getDrawable(R.mipmap.ic_sjk_video_playing_dot);
        }
        array.recycle();
        mRectF = new RectF();
        mRect = new Rect();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(textSize);
    }

    private int thumbCy;

    private int getValue(float value) {
        return (int) TypedValue.applyDimension(1, value, getResources().getDisplayMetrics());
    }

    private float getFloatValue(float value) {
        return TypedValue.applyDimension(1, value, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(300, widthMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom();
        mTextPaint.setTextSize(textSize);
        mTextPaint.getTextBounds("廊", 0, 1, mRect);
        textHeight = mRect.height();
        height += (thumbHeight + textHeight);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        fullWidth = w - getPaddingLeft() - getPaddingRight();
        mRectF.left = getPaddingLeft();
        mRectF.top = getPaddingTop() + (thumbHeight) / 2;
        mRectF.bottom = mRectF.top + progressHeight;
        radius = progressHeight / 2;
        oneWidth = fullWidth / (mItems.size() - 1);
        cy = getPaddingTop() + thumbHeight + textHeight / 2 - getBaseLine(mPaint);
        thumbCy = getPaddingTop() + thumbHeight / 2;
        if (mTouchThumbDrawable != null)
            mTouchThumbDrawable.setBounds(0, 0, mTouchThumbDrawable.getIntrinsicWidth(), mTouchThumbDrawable.getIntrinsicHeight());
        if (mUnTouchThumbDrawable != null)
            mUnTouchThumbDrawable.setBounds(0, 0, mUnTouchThumbDrawable.getIntrinsicWidth(), mUnTouchThumbDrawable.getIntrinsicHeight());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawLine(canvas);
        mRectF.right = mRectF.left + fullWidth;
        mPaint.setColor(unFinishColor);
        canvas.drawRoundRect(mRectF, radius, radius, mPaint);

        if (progress != 0) {
            mRectF.right = mRectF.left + fullWidth * progress / maxProgress;
            mPaint.setColor(finishColor);
            canvas.drawRoundRect(mRectF, radius, radius, mPaint);
        }
        drawText(canvas);
        if (isTouchAble) {
            drawBitmap(mTouchThumbDrawable, canvas);
        } else {
            drawBitmap(mUnTouchThumbDrawable, canvas);
        }
    }

    private void drawLine(Canvas canvas) {
        if (lineWidth == 0 || lineColor == 0) return;
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(lineWidth);
        canvas.drawLine(getPaddingLeft(), getHeight() - lineWidth, getWidth(), getHeight() - lineWidth, mPaint);
    }

    private void drawBitmap(Drawable drawable, Canvas canvas) {
        if (drawable == null) return;
        canvas.save();
        canvas.restore();
        float dy = thumbCy - drawable.getIntrinsicHeight() / 2;
        float dx = (progress == 0 ? getPaddingLeft() : mRectF.right) - drawable.getIntrinsicWidth() / 2;
        canvas.translate(dx, dy);
        drawable.draw(canvas);
    }


    private boolean isTouchAble;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isTouchAble = true;
                    onMovePoint(event.getX());
                    break;
                case MotionEvent.ACTION_MOVE:
                    onMovePoint(event.getX());
                    break;
                case MotionEvent.ACTION_UP:
                    isTouchAble = false;
                    invalidate();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private String newValue = "";

    private void onMovePoint(float eventX) {
        float moveWidth = eventX - getPaddingLeft();

        int progress = (int) ((moveWidth / fullWidth) * maxProgress);
        setProgress(progress);
        if (mOnValueChangeListener != null) {
            int index = (int) (moveWidth / oneWidth);
            String value = getValue(index);
            if (!newValue.equals(value)) {
                mOnValueChangeListener.onValueChange(value);
                newValue = value;
            }
        }
        LogUtils.logd(" maxProgress:" + maxProgress + " progress:" + this.progress);
    }

    private int price;

    public String getValue(int index) {
        String result = "";
        if (index == 0 && progress == 0) {
            result = mItems.get(0).getTitle();
        } else if (index == mItems.size() - 1 && progress == maxProgress) {
            result = mItems.get(mItems.size() - 1).getTitle();
        } else {
            if (index == mItems.size() - 1) {
                result = mItems.get(mItems.size() - 1).getTitle();
            } else {
                int startValue = mItems.get(index).getValue();
                int endValue = mItems.get(index + 1).getValue();
                int realProgress = progress - index * oneTextTotalProgress;
                int extra  = ((endValue - startValue) * realProgress / oneTextTotalProgress) / 100 * 100;
                price = startValue + extra;
                if ("人".equals(extraText)) {
                    result = String.format("%d%s", (price), extraText);
                } else if ("¥".equals(extraText)) {
                    result = String.format("%s%d", extraText, price);
                } else {
                    result = String.valueOf(price);
                }

            }
        }
        return result;
    }

    private void drawText(Canvas canvas) {
        String title = mItems.get(mItems.size() - 1).getTitle();
        float cx;
        canvas.drawText(title, getWidth() - getPaddingRight() - mTextPaint.measureText(title), cy, mTextPaint);
        int startX = getPaddingLeft();
        for (int i = 0; i < mItems.size() - 1; i++) {
            cx = startX + i * oneWidth;
            title = mItems.get(i).getTitle();
            if (i > 0) {
                canvas.drawText(title, cx - mTextPaint.measureText(title) / 2, cy, mTextPaint);
            } else {
                canvas.drawText(title, cx, cy, mTextPaint);
            }
        }
    }

    public void setProgress(int progress) {
        progress = checkProgress(progress);
        if (this.progress != progress) {
            this.progress = progress;
            invalidate();
        }
    }

    public int getPrice() {
        return price;
    }

    private OnValueChangeListener mOnValueChangeListener;

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener) {
        mOnValueChangeListener = onValueChangeListener;
    }

    public interface OnValueChangeListener {
        void onValueChange(String newValue);
    }

    private int checkProgress(int progress) {
        progress = progress > maxProgress ? maxProgress : (progress < 0 ? 0 : progress);
        return progress;
    }

    public int getBaseLine(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) (fontMetrics.ascent + fontMetrics.descent) / 2;
    }


    public static List<Item> getDefaultData() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("不限", 0));
        items.add(new Item("5人", 5));
        items.add(new Item("10人", 10));
        items.add(new Item("15人", 15));
        items.add(new Item("20人", 20));
        items.add(new Item("25人", 25));
        items.add(new Item("30人", 30));

        return items;
    }

    public static class Item {
        private int index;
        private String title;
        private int value;

        public Item(String title, int value) {
            this.title = title;
            this.value = value;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
