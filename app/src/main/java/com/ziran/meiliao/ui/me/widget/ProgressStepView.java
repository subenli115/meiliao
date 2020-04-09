package com.ziran.meiliao.ui.me.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 19:59
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class ProgressStepView extends View {

    private List<Item> mItems;
    private float circleWidth;
    private int finishColor = Color.parseColor("#45b000");
    private int unFinishColor = Color.parseColor("#D8D8D8");
    private int finishTextColor = Color.parseColor("#666666");
    private int unFinishTextColor = Color.parseColor("#999999");
    private float margin;
    private float dateTextSize;
    private float textSize;

    private float lineWidth;
    private Paint mLinePaint;
    private Paint mTextPaint;
    private Paint mDataTextPaint;
    private Rect mRect;

    public ProgressStepView(Context context) {
        this(context, null);
    }

    public ProgressStepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressStepView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        textSize = DisplayUtil.sp2px(getResources(), 14);
        dateTextSize = DisplayUtil.sp2px(getResources(), 11);
        margin = DisplayUtil.dp2px(getResources(), 3);
        circleWidth = DisplayUtil.dp2px(getResources(), 10);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressStepView);
        if (array.hasValue(R.styleable.ProgressStepView_psv_line_finish_color)) {
            finishColor = array.getColor(R.styleable.ProgressStepView_psv_line_finish_color, finishColor);
        }
        if (array.hasValue(R.styleable.ProgressStepView_psv_line_unFinish_color)) {
            unFinishColor = array.getColor(R.styleable.ProgressStepView_psv_line_unFinish_color, unFinishColor);
        }
        if (array.hasValue(R.styleable.ProgressStepView_psv_text_finish_color)) {
            finishTextColor = array.getColor(R.styleable.ProgressStepView_psv_text_finish_color, finishTextColor);
        }
        if (array.hasValue(R.styleable.ProgressStepView_psv_text_unFinish_color)) {
            unFinishTextColor = array.getColor(R.styleable.ProgressStepView_psv_text_unFinish_color, unFinishTextColor);
        }
        circleWidth = array.getDimension(R.styleable.ProgressStepView_psv_circle_width, circleWidth);
        lineWidth = circleWidth / 2;

        margin = array.getDimension(R.styleable.ProgressStepView_psv_margin, margin);
        textSize = array.getDimension(R.styleable.ProgressStepView_progress_step_text_size, textSize);
        dateTextSize = array.getDimension(R.styleable.ProgressStepView_psv_date_text_size, dateTextSize);


        array.recycle();

        initDefaultData();
        mRect = new Rect();
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mLinePaint.setColor(unFinishColor);
        mDataTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    private void initDefaultData() {
        mItems = new ArrayList<>();
        mItems.add(new Item(true, "用户退款", "2017-10-10 17:33"));
        mItems.add(new Item(false, "银行受理", "2017-10-10 17:33"));
        mItems.add(new Item(false, "退款成功", "2017-10-10 17:33"));
    }

    private int textLineHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(200, widthMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom();
        height += circleWidth + margin;
        mTextPaint.setTextSize(textSize);
        mTextPaint.getTextBounds("款", 0, 1, mRect);
        textLineHeight = mRect.height();
        height += textLineHeight + margin;
        mDataTextPaint.setTextSize(dateTextSize);
        mDataTextPaint.getTextBounds("款", 0, 1, mRect);
        height += mRect.height();
        setMeasuredDimension(width, height);
    }

    private float lineCy, textBaseLine, dataBaseLine;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        lineCy = getPaddingTop() + circleWidth / 2;
        float baseLine = ViewUtil.getBaseLine(mTextPaint);
        textBaseLine = getPaddingTop() + circleWidth + margin + textLineHeight / 2 + baseLine;
        baseLine = ViewUtil.getBaseLine(mDataTextPaint);
        dataBaseLine = h - getPaddingBottom() - textLineHeight / 2 + baseLine;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mLinePaint.setStrokeWidth(lineWidth);
        mDataTextPaint.setColor(unFinishTextColor);
        float dateTextWidth = mDataTextPaint.measureText("2017-10-10 17:33");
        float cx;
        mLinePaint.setColor(unFinishColor);
        canvas.drawLine(getPaddingLeft() + dateTextWidth / 2, lineCy, getWidth() - getPaddingRight() - dateTextWidth / 2, lineCy,
                mLinePaint);
        mLinePaint.setColor(finishColor);
        float endCx = getUnFinishCx(dateTextWidth); //2
        if (endCx > 0) {
            mLinePaint.setColor(finishColor);
            canvas.drawLine(getPaddingLeft() + dateTextWidth / 2, lineCy, endCx, lineCy, mLinePaint);
        }
        for (int i = 0; i < mItems.size(); i++) {
            Item item = mItems.get(i);
            if (i == 0) {
                cx = getPaddingLeft() + dateTextWidth / 2;
            } else if (i == mItems.size() - 1) {
                cx = getWidth() - getPaddingRight() - dateTextWidth / 2;
            } else {
                cx = getWidth() / 2;
            }
            if (item.isStep()) {
                mTextPaint.setColor(finishTextColor);
                canvas.drawText(item.getDate(), cx - dateTextWidth / 2, dataBaseLine, mDataTextPaint);
            } else {
                mTextPaint.setColor(unFinishColor);
            }
            if (i != 0) {
                if (item.isStep) {
                    mLinePaint.setColor(finishColor);
                } else {
                    mLinePaint.setColor(unFinishColor);
                }
            }
            canvas.drawCircle(cx, lineCy, circleWidth / 2, mLinePaint);
            canvas.drawText(item.getTitle(), cx - mTextPaint.measureText(item.getTitle()) / 2, textBaseLine, mTextPaint);
        }
    }

    /**
     * @param index      完成到第几步
     * @param finishDate 完成的时间
     */
    public void setStep(int index, String finishDate) { //2
        if (!TextUtils.isEmpty(finishDate)){
            mItems.get(index).setDate(finishDate);
        }
        for (int i = 0; i < mItems.size(); i++) {
            mItems.get(i).setStep(i <= index);
        }
        invalidate();
    }

    /**
     * @param i    完成到第几步
     * @param date 完成的时间
     */
    public void setStep(int i, long date) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(date));
        setStep(i, format);
    }

    /**
     * @param i    完成到第几步
     */
    public void setStep(int i) {
        setStep(i, "");
    }

    public List<Item> getItems(){
        return mItems;
    }

    /**
     * 获取已完成的步数
     *
     * @param dateTextWidth
     * @return
     */
    private float getUnFinishCx(float dateTextWidth) {
        for (int i = mItems.size() - 1; i >= 0; i--) {
            if (mItems.get(i).isStep()) {
                if (i == 2) {
                    return getWidth() - getPaddingRight() - dateTextWidth / 2;
                } else if (i == 1) {
                    return getWidth() / 2;
                }
            }
        }
        return 0;
    }

    public static class Item {
        private boolean isStep;
        private String title;
        private String date;

        public Item(boolean isStep, String title, String date) {
            this.isStep = isStep;
            this.title = title;
            this.date = date;
        }

        public boolean isStep() {
            return isStep;
        }

        public void setStep(boolean step) {
            isStep = step;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
        public void setDateLong(long date) {
            this.date = TimeUtil.getStringByFormat(date,TimeUtil.dateFormatYMDHM);
        }

        @Override
        public String toString() {
            return "Item{" + "isStep=" + isStep + ", title='" + title + '\'' + ", date='" + date + '\'' + '}';
        }
    }

    public void setItems(List<Item> data) {
        mItems.clear();
        mItems.addAll(data);
        invalidate();
    }
}
