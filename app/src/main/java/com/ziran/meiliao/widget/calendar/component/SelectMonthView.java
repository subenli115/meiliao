package com.ziran.meiliao.widget.calendar.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.widget.calendar.entity.CalendarInfo;
import com.ziran.meiliao.widget.calendar.theme.SelectTheme;
import com.ziran.meiliao.widget.calendar.utils.DateUtils;

import java.util.List;


/**
 * Created by Administrator on 2016/8/9.
 */
public class SelectMonthView extends MonthView {

    public SelectMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRect = new RectF();
    }

    @Override
    protected void drawLines(Canvas canvas, int rowsCount) {
    }

    @Override
    protected void drawBG(Canvas canvas, int column, int row, int day) {
//
        LogUtils.logd("flag:"+flag);
        if ( flag ==0 || flag==2 ) { //绘制背景色圆形
//        if (day == selDay ) { //绘制背景色圆形
            float startRecX = columnSize * column;
            float startRecY = rowSize * row;
            float endRecX = startRecX + columnSize;
            float endRecY = startRecY + rowSize;
            float cx = (startRecX + endRecX) / 2;
            float cy = (startRecY + endRecY) / 2;
            float radius = (columnSize < rowSize ? columnSize * 0.95f : rowSize) / 2;
//            float radius = columnSize < (rowSize * 0.9) ? columnSize / 1.6f : (float) (rowSize * 0.9) / 2;
            paint.setColor(theme.colorSelectBG());
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(cx, cy, radius, paint);
        }

    }

    private RectF mRect;

    private int flag;

    @Override
    protected void drawDate(Canvas canvas, int year, int month, int startX, int startY) {
        canvas.save();
        canvas.translate(startX, startY);
        flag = -1;
//        NUM_ROWS = getMonthRowNumber(year, month);
        columnSize = getWidth() * 1.0F / NUM_COLUMNS;
        rowSize = getHeight() * 1.0F / NUM_ROWS;
        daysString = new int[6][7];
        int mMonthDays = DateUtils.getMonthDays(year, month);
        int weekNumber = DateUtils.getFirstDayWeek(year, month);
        int column, row;
        for (int day = 0; day < mMonthDays; day++) {
            column = (day + weekNumber - 1) % 7;
            row = (day + weekNumber - 1) / 7;
            daysString[row][column] = day + 1;
            flag = isCalendarInfoNew2(year, month, daysString[row][column]);
//            if (isSingleSelect){
//                drawBG(canvas, column, row, daysString[row][column]);
//            }else{
//            }
            drawDecor(canvas, column, row, year, month, daysString[row][column]);
            drawText(canvas, column, row, year, month, daysString[row][column]);
        }
        canvas.restore();

    }

    @Override
    protected void drawDecor(Canvas canvas, int column, int row, int year, int month, int day) {

        if (isSelect()) { //绘制背景色圆形
//        if (day == selDay ) { //绘制背景色圆形
            float startRecX = columnSize * column;
            float startRecY = rowSize * row;
            float endRecX = startRecX + columnSize;
            float endRecY = startRecY + rowSize;
            float cx = (startRecX + endRecX) / 2;
            float cy = (startRecY + endRecY) / 2;
            float radius = (columnSize < rowSize ? columnSize * 0.95f : rowSize) / 2;
//            float radius = columnSize < (rowSize * 0.9) ? columnSize / 1.6f : (float) (rowSize * 0.9) / 2;
            paint.setColor(theme.colorSelectBG());
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(cx, cy, radius, paint);
            return;
        }

        if (flag == -1) return;
        paint.setStyle(Paint.Style.FILL);
        mRect.left = columnSize * column;
        mRect.top = rowSize * row;
        mRect.right = mRect.left + columnSize;
        mRect.bottom = mRect.top + rowSize;
        if (flag == 1) {
            paint.setColor(theme.colorRest());
            canvas.drawRoundRect(mRect, 0, 0, paint);
        } else {
            paint.setColor(theme.colorDecor());
            canvas.drawRoundRect(mRect, rowSize / 2, rowSize / 2, paint);

            if (calendarInfos.size() == 2 && calendarInfos.get(0).compareTo(calendarInfos.get(1)) != 0) {
                if (flag == 0) {
                    mRect.left += mRect.width() / 2;
                } else {
                    mRect.right -= mRect.width() / 2;
                }
                canvas.drawRect(mRect, paint);
            }
        }
    }

    private boolean isSelect() {
        return isSingleSelect  && (flag==0||flag==2);
    }


    //  0 开始日期 2 结束日期
    public int isCalendarInfoNew3(int year, int month, int day) {
        for (CalendarInfo calendarInfo : calendarInfos) {
            if (calendarInfo.day == day && calendarInfo.month == month + 1 && calendarInfo.year == year) {
                return startInfo == calendarInfo ? 0 : 2;
            }
        }
        return -1;
    }

    public int isCalendarInfoNew2(int year, int month, int day) {
        if (calendarInfos == null || calendarInfos.size() == 0) return -1;
        int result = isCalendarInfoNew3(year, month, day);
        if (result == 0 || result == 2) {
            return result;
        }
        if (calendarInfos.size() == 2) {
            CalendarInfo calendarInfo1 = calendarInfos.get(0);
            CalendarInfo calendarInfo2 = calendarInfos.get(1);
            int i1 = calendarInfo1.checkBig(year, month + 1, day);
            int i2 = calendarInfo2.checkBig(year, month + 1, day);
            result = (i1 == -1 && i2 == -1) ? -1 : (i1 == 1 && i2 == 1) ? -1 : 1;
        }
        return result;
    }

    @Override
    protected void drawRest(Canvas canvas, int column, int row, int year, int month, int day) {
    }

    @Override
    protected void drawText(Canvas canvas, int column, int row, int year, int month, int day) {
        paint.setTextSize(DisplayUtil.sp2px(getResources(), theme.sizeDay()));
        paint.setStyle(Paint.Style.STROKE);
        float startX = columnSize * column + (columnSize - paint.measureText(day + "")) / 2;
        float startY = rowSize * row + rowSize / 2 - (paint.ascent() + paint.descent()) / 2;
        if (isSelect()) {
            paint.setColor(theme.colorSelectDay());
        } else {
            paint.setColor(theme.colorWeekday());
        }
        canvas.drawText(day + "", startX, startY, paint);
    }

    @Override
    protected void createTheme() {
        theme = new SelectTheme();
    }

    private CalendarInfo startInfo;

    @Override
    public void setCalendarInfos(List<CalendarInfo> calendarInfos) {
        startInfo = null;
        for (CalendarInfo calendarInfo : calendarInfos) {
            if (startInfo == null) {
                this.startInfo = calendarInfo;
            } else {
                if (CalendarInfo.checkData(startInfo, calendarInfo) == 1) {
                    startInfo = calendarInfo;
                }
            }
        }
        super.setCalendarInfos(calendarInfos);

    }

    private boolean isSingleSelect;
    public void setSingleSelect(boolean singleSelect) {
        isSingleSelect = singleSelect;
        invalidate();
    }
}
