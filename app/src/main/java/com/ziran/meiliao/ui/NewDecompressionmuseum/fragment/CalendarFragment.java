package com.ziran.meiliao.ui.NewDecompressionmuseum.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.ziran.meiliao.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/26.
 */

public class CalendarFragment extends Fragment {
    @Bind(R.id.calendarView)
    CalendarView mCalendarView;
    private String[] days;
    private String date;
    private Date beginDate;
    private SimpleDateFormat format;
    private Date endDate;
    private int selectyear;
    private int selecday;
    private int selecmonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //引用创建好的xml布局
        View view = inflater.inflate(R.layout.fragment_jdx_exe_record_calendar, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle=getArguments();
        days = bundle.getStringArray("days");
         date = bundle.getString("date");
        initCalender();
        return view;

    }


/**
 16      * 指定日期加上天数后的日期
 17      * @param num 为增加的天数
 18      * @param newDate 创建时间
 19      * @return
 20      * @throws ParseException
 21      */
     public static String plusDay(int num,String newDate) throws ParseException{
                  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                  Date  currdate = sdf.parse(newDate);
         java.util.Calendar ca = java.util.Calendar.getInstance();
         ca.setTime(currdate);
         currdate = ca.getTime();
         System.out.println("现在的日期是：" + currdate);
         ca.add(java.util.Calendar.DATE, num);// num为增加的天数，可以改变的

                 String temp = "";
                  temp = sdf.format(ca.getTime());
                      System.out.println(temp);
                  System.out.println("增加天数以后的日期：" + temp);
                 return temp;
             }

    public void setCalenderItemClickListener(CalenderItemClickListener itemClickListener){
        this.calenderitemClickListener=itemClickListener;

    }
    public interface CalenderItemClickListener{
        void itemClick(long day, int week);
    }
    public void update(Bundle bundle){
        days = bundle.getStringArray("days");
        date = bundle.getString("date");
        setData();
        if(mCalendarView!=null){
            mCalendarView.update();
        }

    }

    public void scrollCurrentDay(int y,int m,int d,boolean sm){

        mCalendarView.scrollToCalendar(y,m,d,sm);
    }
    private CalenderItemClickListener calenderitemClickListener;
    private void initCalender() {
        mCalendarView.setWeekStarWithSun();
        List<Calendar> calendars = new ArrayList<>();
        Calendar calendar = new Calendar();
        calendar.setCurrentDayTwo(true);
        calendars.add(calendar);
        mCalendarView.setSchemeDate(calendars);

        mCalendarView.setTextColor(Color.parseColor("#666666"), Color.WHITE, Color.parseColor("#666666"), Color.parseColor("#666666"), Color.parseColor("#666666"));
        setData();

    }

    private void setData() {
        try {
            if(date!=null&&!date.equals("null")&&date.length()>0){
                format = new SimpleDateFormat("yyyy-MM-dd");
                beginDate= format.parse(date);
                List<Calendar> schemes = new ArrayList<>();
                if(days!=null){
                    java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                    java.util.Calendar calendar2 = java.util.Calendar.getInstance();
                    calendar2.setTime(beginDate);
                    java.util.Calendar calendar3 = java.util.Calendar.getInstance();
                    Date date3 = format.parse(plusDay(56, date));
                    calendar3.setTime(date3);
                    if((calendar2.get(java.util.Calendar.MONTH)+1)>(calendar3.get(java.util.Calendar.MONTH)+1)){
                        mCalendarView.setRange(calendar2.get(java.util.Calendar.YEAR),calendar2.get(java.util.Calendar.MONTH)+1,calendar2.get(java.util.Calendar.YEAR)+1,calendar3.get(java.util.Calendar.MONTH)+1);
                    }else{
                        mCalendarView.setRange(calendar2.get(java.util.Calendar.YEAR),calendar2.get(java.util.Calendar.MONTH)+1,calendar2.get(java.util.Calendar.YEAR),calendar3.get(java.util.Calendar.MONTH)+1);
                    }
                    for(int i=0;i<days.length;i++){
                        if(days[i]!=null&&days[i]!="0"){
                            calendar1.setTime(format.parse( plusDay(Integer.parseInt(days[i]), date)));
                            //已练习数据
                            if(calendar1.get(java.util.Calendar.DAY_OF_MONTH)!=calendar2.get(java.util.Calendar.DAY_OF_MONTH)){
                                schemes.add(getSchemeCalendar(calendar1.get(java.util.Calendar.YEAR),calendar1.get(java.util.Calendar.MONTH)+1,calendar1.get(java.util.Calendar.DAY_OF_MONTH), R.color.black, "假",false));
                                         }
                              }
                    }
                }
                               mCalendarView.setSchemeDate(schemes);
                               mCalendarView.update();
                               mCalendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(Calendar calendar, boolean isClick) {
                       try {
                             selectyear = calendar.getYear();
                             selecday = calendar.getDay();
                              selecmonth = calendar.getMonth();
                            endDate= format.parse(calendar.getYear()+"-"+calendar.getMonth()+"-"+calendar.getDay());
                            long day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
                            if(calenderitemClickListener!=null&&isClick) {
                                calenderitemClickListener.itemClick(day+1,calendar.getWeek());
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text,boolean isDraw) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.setDraw(isDraw);

        return calendar;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
