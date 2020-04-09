package com.ziran.meiliao.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

/**
 * 下标标记的日历控件
 * Created by huanghaibin on 2017/11/15.
 */
public class IndexMonthView extends MonthView {
    private Paint mBaseDayPaint= new Paint();
    private Paint mSchemeBasicPaint = new Paint();
    Paint linep = new Paint();
    private int mPadding;
    private int mH, mW;
    private RectF oval3;

    /**
     * 今天的背景色
     */
    private Paint mCurrentDayPaint = new Paint();
    public IndexMonthView(Context context) {
        super(context);
        mSchemeBasicPaint.setAntiAlias(true);
        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setColor(0xff333333);
        mSchemeBasicPaint.setFakeBoldText(true);
        mSelectedPaint.setColor(Color.parseColor("#444444"));
        mCurrentDayPaint.setAntiAlias(true);
        mCurrentDayPaint.setStyle(Paint.Style.FILL);
        mCurrentDayPaint.setColor(Color.parseColor("#ED7460"));
        mBaseDayPaint.setColor(Color.parseColor("#F0F0F0"));
        mSelectTextPaint.setColor(Color.WHITE);
        mPadding = dipToPx(getContext(), 1);
        linep.setColor(Color.RED);// 设置红
        linep.setStrokeWidth(10f);
        linep.setAntiAlias(true);
        mH = dipToPx(getContext(), 2);
        mW = dipToPx(getContext(), 8);
    }


    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        int top = y - mItemHeight / 6;
        mSelectedPaint.setColor(Color.TRANSPARENT);
        mSelectedPaint.setStyle(Paint.Style.FILL);
         oval3 = new RectF(x + mPadding, y + mPadding, x + mItemWidth - mPadding, y + mItemHeight - mPadding);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 8, 8,mSelectedPaint);
        return true;
    }

    @Override
    protected void onDrawbg(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        mBaseDayPaint.setColor(Color.parseColor("#F0F0F0"));
        oval3 = new RectF(x + mPadding, y + mPadding, x + mItemWidth - mPadding, y + mItemHeight - mPadding);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 8, 8,mBaseDayPaint);
    }
    /**
     * onDrawSelected
     * @param canvas   canvas
     * @param calendar 日历calendar
     * @param x        日历Card x起点坐标
     * @param y        日历Card y起点坐标
     */
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y, boolean isDrawSelected, boolean isDraw) {
                if(calendar.isDraw()){
                    mSchemeBasicPaint.setColor(Color.parseColor("#6BC09F"));
                }else{
                    mSchemeBasicPaint.setColor(Color.RED);
                }
        oval3 = new RectF(x + mPadding, y + mPadding, x + mItemWidth - mPadding, y + mItemHeight - mPadding);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 8, 8,mSchemeBasicPaint);
    }


    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected, boolean draw) {
        int cx = x + mItemWidth / 2;
        int top = y - mItemHeight / 6;

        if (hasScheme) {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mSchemeTextPaint : mOtherMonthTextPaint);

            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10,
                    calendar.isCurrentDay() ? mCurDayLunarTextPaint :
                    mCurMonthLunarTextPaint);

        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthLunarTextPaint : mCurMonthLunarTextPaint);

            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10, mCurMonthLunarTextPaint);
        }
        if (calendar.isCurrentDay() ) {
            oval3 = new RectF(x + mPadding, y + mPadding, x + mItemWidth - mPadding, y + mItemHeight - mPadding);// 设置个新的长方形
            canvas.drawRoundRect(oval3, 8, 8,mCurrentDayPaint);
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    mTextBaseLine + top,
                    mCurDayTextPaint);

            canvas.drawText(calendar.getLunar(),
                    cx,
                    mTextBaseLine + y + mItemHeight / 10,
                    mCurDayLunarTextPaint);
        }

    }



    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
