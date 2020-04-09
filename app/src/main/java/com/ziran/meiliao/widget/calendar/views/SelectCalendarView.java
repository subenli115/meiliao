package com.ziran.meiliao.widget.calendar.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.calendar.component.MonthView;
import com.ziran.meiliao.widget.calendar.component.SelectMonthView;
import com.ziran.meiliao.widget.calendar.component.WeekView;
import com.ziran.meiliao.widget.calendar.entity.CalendarInfo;
import com.ziran.meiliao.widget.calendar.theme.IDayTheme;
import com.ziran.meiliao.widget.calendar.theme.IWeekTheme;
import com.ziran.meiliao.widget.calendar.theme.SelectWeekTheme;

import java.util.List;

/**
 * Created by Administrator on 2016/8/7.
 */
public class SelectCalendarView extends LinearLayout implements View.OnClickListener {
    private WeekView weekView;
    private SelectMonthView circleMonthView;
    private TextView textViewYear,textViewMonth;
    private int textColor = Color.parseColor("#333333");
    public SelectCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        LayoutParams llParams =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(R.layout.display_grid_date,null);
        weekView = new WeekView(context,null);
        weekView.setWeekTheme(new SelectWeekTheme());
        circleMonthView = new SelectMonthView(context,null);
        addView(view,llParams);
        addView(weekView,llParams);
        addView(circleMonthView,llParams);

        view.findViewById(R.id.left).setOnClickListener(this);
        view.findViewById(R.id.right).setOnClickListener(this);
        textViewYear = (TextView) view.findViewById(R.id.year);
        textViewMonth = (TextView) view.findViewById(R.id.month);
        textViewYear.setTextColor(textColor);
        textViewMonth.setTextColor(textColor);
        circleMonthView.setMonthLisener(new MonthView.IMonthLisener() {
            @Override
            public void setTextMonth() {
                if (mIMonthLisener!=null){
                    mIMonthLisener.setTextMonth(StringUtils.format("%s-%s",circleMonthView.getSelYear(),(circleMonthView.getSelMonth() + 1)));
                }
                textViewYear.setText(circleMonthView.getSelYear()+"年");
                textViewMonth.setText((circleMonthView.getSelMonth() + 1)+"月");
            }
        });

        textViewYear.setText(circleMonthView.getSelYear()+"年");
        textViewMonth.setText((circleMonthView.getSelMonth() + 1)+"月");
    }

    /**
     * 设置日历点击事件
     * @param dateClick
     */
    public void setDateClick(MonthView.IDateClick dateClick){
        circleMonthView.setDateClick(dateClick);
    }

    /**
     * 设置星期的形式
     * @param weekString
     * 默认值	"日","一","二","三","四","五","六"
     */
    public void setWeekString(String[] weekString){
        weekView.setWeekString(weekString);
    }

    public void setCalendarInfos(List<CalendarInfo> calendarInfos){
        circleMonthView.setCalendarInfos(calendarInfos);
        textViewYear.setText(circleMonthView.getSelYear()+"年");
        textViewMonth.setText((circleMonthView.getSelMonth() + 1)+"月");
    }

    public void setDayTheme(IDayTheme theme){
        circleMonthView.setTheme(theme);
    }

    public void setWeekTheme(IWeekTheme weekTheme){
        weekView.setWeekTheme(weekTheme);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.left){
            circleMonthView.onLeftClick();
        }else{
            circleMonthView.onRightClick();
        }
    }
    private IMonthLisener mIMonthLisener ;

    public boolean checkHasNote(int year, int month, int day) {
        return circleMonthView.isCalendarInfoNew(year, month, day);
    }



    public static interface IMonthLisener{
        void setTextMonth(String textMonth);
    }

    public SelectMonthView getSelectMonthView(){
        return circleMonthView;
    }

    public void setIMonthLisener(IMonthLisener IMonthLisener) {
        mIMonthLisener = IMonthLisener;
    }
}
