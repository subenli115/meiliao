package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.widget.BaseItemView;
import com.ziran.meiliao.widget.calendar.component.MonthView;
import com.ziran.meiliao.widget.calendar.entity.CalendarInfo;
import com.ziran.meiliao.widget.calendar.views.SelectCalendarView;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/29 18:06
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/29$
 * @updateDes ${TODO}
 */

public class CalendarPopupWindow extends BasePopupWindow {
    private SelectCalendarView mSelectCalendarView;
    private LinkedList<CalendarInfo> checkDatas;
    private TextView tvTitle;

    public CalendarPopupWindow(Context context) {
        super(context);
        checkDatas = new LinkedList<>();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popup_calendar;
    }

    @Override
    protected void initViews(View contentView) {
        mSelectCalendarView = ViewUtil.getView(contentView, R.id.selectCalendarView);
        tvTitle = ViewUtil.getView(contentView, R.id.tv_title);
        mSelectCalendarView.setDateClick(new MonthView.IDateClick() {
            @Override
            public void onClickOnDate(int year, int month, int day) {
                if (isSingleSelect) {
                    checkDatas.clear();
                    checkDatas.add(new CalendarInfo(year, month, day, ""));
                    if (mBaseItemView != null) {
                        mBaseItemView.setContent(checkDatas.get(0).getString());
                    }
                    mSelectCalendarView.getSelectMonthView().setCalendarInfos(checkDatas);
                    HandlerUtil.runMain(new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                        }
                    }, 300);
                    return;
                }
                if (checkDatas.size() == 2) {
                    checkDatas.removeFirst();
                }
                checkDatas.add(new CalendarInfo(year, month, day, ""));
                if (checkDatas.size() == 2) {
                    if (!check(checkDatas.get(0), checkDatas.get(1))) {
                        checkDatas.removeFirst();
                    }
                    LogUtils.logd(checkDatas.toString());
                }
                Collections.sort(checkDatas);
                mSelectCalendarView.getSelectMonthView().setCalendarInfos(checkDatas);
                if (checkDatas.size()==1){
                    tvTitle.setText(HtmlUtil.format("%s 至",checkDatas.get(0).getString()));
                }else if (checkDatas.size()==2){
                    tvTitle.setText(HtmlUtil.format("%s 至 %s",checkDatas.get(0).getString(),checkDatas.get(1).getString()));
                }
            }
        });
//        touchOuterSize(R.id.fl_content);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (checkDatas.size() == 2 && checkDatas.size() == 2) {
                    dismiss();
                    if (mBaseItemView != null ) {
                        if ( checkDatas.size() == 2){
                            mBaseItemView.setContent(HtmlUtil.format("%s至%s", checkDatas.get(0).getString(), checkDatas.get(1).getString()));
                        }else{
                            mBaseItemView.setContent("");
                        }
                    }
                }
//            }
        });
    }

    //第一个比第二个早则false 否则true
    private boolean check(CalendarInfo date1, CalendarInfo date2) {
        //          2007        2008
        if (date1.year < date2.year) {
            return true;
        } else if (date1.year > date2.year) {
            return false;
        } else {
            //      8                       9
            if (date1.month < date2.month) {
                return true;
            } else if (date1.month > date2.month) {
                return false;
            } else {
                return date1.day <= date2.day;
            }
        }
    }

    public LinkedList<CalendarInfo> getCheckDatas() {
        return checkDatas;
    }

    private boolean isSingleSelect;

    public void setSingleSelect() {
        setSingleSelect(true);
    }

    public void setSingleSelect(boolean singleSelect) {
        isSingleSelect = singleSelect;
        mSelectCalendarView.getSelectMonthView().setSingleSelect(singleSelect);
        checkDatas.clear();
        tvTitle.setVisibility(singleSelect ? View.GONE : View.VISIBLE);
    }

    private BaseItemView mBaseItemView;

    public void setBaseItemView(BaseItemView bivTime) {
        this.mBaseItemView = bivTime;
    }
}
