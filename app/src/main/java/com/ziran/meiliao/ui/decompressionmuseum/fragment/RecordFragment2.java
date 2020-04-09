package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.PractiveChartBean;
import com.ziran.meiliao.ui.bean.PunchHisBean;
import com.ziran.meiliao.ui.bean.RecordChartBean;
import com.ziran.meiliao.ui.bean.RecordTotalBean;
import com.ziran.meiliao.ui.bean.UserNoteBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.UpdateNotesActivity;
import com.ziran.meiliao.ui.decompressionmuseum.adapter.RecordCalendarNewAdapter;
import com.ziran.meiliao.ui.decompressionmuseum.contract.RecordContract;
import com.ziran.meiliao.ui.decompressionmuseum.presenter.RecordPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.CustomNumbersView;
import com.ziran.meiliao.widget.calendar.component.MonthView;
import com.ziran.meiliao.widget.calendar.entity.CalendarInfo;
import com.ziran.meiliao.widget.calendar.views.CirclePointCalendarView;
import com.ziran.meiliao.widget.hellocharts.gesture.ZoomType;
import com.ziran.meiliao.widget.hellocharts.listener.ComboLineColumnChartOnValueSelectListener;
import com.ziran.meiliao.widget.hellocharts.model.Axis;
import com.ziran.meiliao.widget.hellocharts.model.AxisValue;
import com.ziran.meiliao.widget.hellocharts.model.Column;
import com.ziran.meiliao.widget.hellocharts.model.ColumnChartData;
import com.ziran.meiliao.widget.hellocharts.model.ComboLineColumnChartData;
import com.ziran.meiliao.widget.hellocharts.model.Line;
import com.ziran.meiliao.widget.hellocharts.model.LineChartData;
import com.ziran.meiliao.widget.hellocharts.model.PointValue;
import com.ziran.meiliao.widget.hellocharts.model.SubcolumnValue;
import com.ziran.meiliao.widget.hellocharts.view.ComboLineColumnChartView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * 练习记录页面Fragment
 */

public class RecordFragment2 extends CommonRefreshFragment<RecordPresenter, CommonModel> implements RecordContract.View {


    @Bind(R.id.circlePointMonthView)
    CirclePointCalendarView circlePointMonthView;
    @Bind(R.id.customNumbersView)
    CustomNumbersView mCustomNumbersView;
    @Bind(R.id.columnChartView)
    ComboLineColumnChartView mColumnChartView;
    @Bind(R.id.rg_record_calendar_chart)
    RadioGroup rgRecordCalendarChart;
    @Bind(R.id.rl_record_calendar_chart)
    RelativeLayout rlRecordCalendarChart;
    @Bind(R.id.tv_record_chart_time)
    TextView tvChartTime;
    private boolean showCalendar;
    private Map<String, String> tagByMonthMap;

    /**
     * 图片显示数据的枚举类
     */
    private enum ChartState {
        DAY, MONTH, YEAR, WEEK
    }


    private ColumnChartData columnData;
    //默认显示月份
    private ChartState mCurrentChartState = ChartState.MONTH;

    private boolean firstInitChart = false;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_record_calendar1;
    }

    @Override
    protected void initOther() {
        super.initOther();
        showCalendar = true; //显示日历
        tagByMonthMap = MapUtils.getDefMap(true);
        mColumnChartView.setZoomEnabled(false);
        //图表标签切换监听
        rgRecordCalendarChart.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_record_calendar_chart_day:
                        mCurrentChartState = ChartState.DAY;
                        refreshChart("day", "yyyy-MM-dd");
                        break;
                    case R.id.rb_record_calendar_chart_month:
                        mCurrentChartState = ChartState.MONTH;
                        refreshChart("month", "yyyy-MM");
                        break;
                    case R.id.rb_record_calendar_chart_year:
                        mCurrentChartState = ChartState.YEAR;
                        refreshChart("year", "yyyy");
                        break;
                    case R.id.rb_record_calendar_chart_week:
                        mCurrentChartState = ChartState.WEEK;
                        refreshChart("week", "yyyy");
                        break;
                }
            }
        });
        //  点击日期刷新对应的笔记
        circlePointMonthView.setDateClick(new MonthView.IDateClick() {
            @Override
            public void onClickOnDate(int year, int month, int day) {
                //检查是否有日志信息,有才刷新
                if (circlePointMonthView.checkHasNote(year, month - 1, day)) {
                    refreshUserNoteByDay(year, month, day, null);
                } else {
                    if (!mAdapter.getPageBean().isRefresh()) {
                        mAdapter.getPageBean().setRefresh(true);
                        mAdapter.clear();
                    }
                }
            }
        });
        // 点击月份刷新圆点提示
        circlePointMonthView.setIMonthLisener(new CirclePointCalendarView.IMonthLisener() {
            @Override
            public void setTextMonth(String textMonth) {
                tagByMonthMap.put("time", textMonth);
                tagByMonthMap.put("type", "month");
                mPresenter.getTagByMonth(ApiKey.PRACTICE_GET_TAG_BY_MONTH, tagByMonthMap);
            }
        });
        //订阅删除笔记
        mRxManager.on(AppConstant.RXTag.DELETE_NOTES, new Action1<UserNoteBean.DataBean>() {
            @Override
            public void call(UserNoteBean.DataBean dataBean) {
                mPresenter.postDeleteNote(ApiKey.PRACTICE_DEL_NOTE, MapUtils.getOnlyCan("noteIds", dataBean.getNoteId()));
                mAdapter.remove(dataBean);
            }
        });
        mColumnChartView.setOnValueTouchListener(new ValueTouchListener());
    }

    private class ValueTouchListener implements ComboLineColumnChartOnValueSelectListener {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub
        }
        @Override
        public void onColumnValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
//            Toast.makeText(getActivity(), "Selected column: " + value, Toast.LENGTH_SHORT).show();
            if (EmptyUtils.isNotEmpty(mChartBeen)) ViewUtil.setText(tvChartTime, mChartBeen.get(columnIndex).getText());
        }

        @Override
        public void onPointValueSelected(int lineIndex, int pointIndex, PointValue value) {
//            Toast.makeText(getActivity(), "Selected line point: " + value, Toast.LENGTH_SHORT).show();
            if (EmptyUtils.isNotEmpty(mChartBeen)) ViewUtil.setText(tvChartTime, mChartBeen.get(pointIndex).getText());
        }

    }

    //创建具体莫一天做笔记的适配器
    @Override
    public CommonRecycleViewAdapter getAdapter() {
//        return new RecordCalendarAdapter(getContext(), R.layout.item_record_calendar);
        return new RecordCalendarNewAdapter(getContext(), R.layout.item_record_calendar_new);
    }

    //请求用户具体一天做笔记的记录
    private void refreshUserNoteByDay(int year, int month, int day, String dayStr) {
        mLastClickDate = new Date(year, month, day, dayStr);
        tagByMonthMap.put("day", mLastClickDate.getDayStr());
        mPresenter.getUserNote(ApiKey.PRACTICE_GET_USER_NOTE, tagByMonthMap);
    }


    private Date mLastClickDate;

    public class Date {
        private int year;
        private int month;
        private int day;
        private String dayStr;

        public Date(int year, int month, int day, String dayStr) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.dayStr = dayStr;
        }


        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getDayStr() {
            return EmptyUtils.isNotEmpty(dayStr) ? dayStr : StringUtils.format("%d-%d-%d", year, month, day);
        }

        public void setDayStr(String dayStr) {
            this.dayStr = dayStr;
        }
    }

    //第一次刷新数据
    @Override
    protected void loadData() {
        tagByMonthMap.put("time", TimeUtil.getCurrentDate("yyyy-MM"));
        tagByMonthMap.put("type", "month");
        //请求获取当前月份的练习提示
        mPresenter.getTagByMonth(ApiKey.PRACTICE_GET_TAG_BY_MONTH, tagByMonthMap);
        //请求获取总练习时间,冥想天数
        mPresenter.getTotalTime(ApiKey.PRACTICE_GET_TOTAL, tagByMonthMap);
    }

    //点击item监听
    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        UserNoteBean.DataBean dataBean = EmptyUtils.parseObject(o);
        Bundle bundle = new Bundle();
        bundle.putString("hisId", String.valueOf(dataBean.getNoteId()));
        bundle.putParcelable(AppConstant.ExtraKey.BEAN, dataBean);
        //跳转到编辑笔记界面
        startActivity(UpdateNotesActivity.class, bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (WpyxConfig.isUpdateNotes() && EmptyUtils.isNotEmpty(mLastClickDate)) {
            tagByMonthMap.put("day", mLastClickDate.getDayStr());
            mPresenter.getUserNote(ApiKey.PRACTICE_GET_USER_NOTE, tagByMonthMap);
        }
    }

    //显示总时间
    @Override
    public void showTotalTime(RecordTotalBean.DataBean result) {
        mCustomNumbersView.setDatas(result.getDays(), result.getTimes(), result.getAmounts(), true);
        refreshUserNoteByDay(0, 0, 0, TimeUtil.getCurrentDate("yyyy-MM-dd"));
    }


    @Override
    public void showTagByMonth(List<String> result) {
        List<CalendarInfo> calendarInfos = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Calendar calendar = TimeUtil.getDateCalendar("yyyy-MM-dd", result.get(i));
            CalendarInfo calendarInfo = new CalendarInfo(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get
                    (Calendar.DATE), "");
            calendarInfos.add(calendarInfo);
        }
        circlePointMonthView.setCalendarInfos(calendarInfos);
    }

    @Override
    public void showTotalTimeByDate(Result result) {

    }

    @Override
    public void showNewChartData(PractiveChartBean.DataBean result) {

    }

    @Override
    public void showPunchHisData(PunchHisBean.DataBean data) {

    }


    @Override
    public void showChartData(RecordChartBean result) {
//        generateValues();
        generateColumnData(result.getData().getChart());
    }

    @Override
    public void deleteNoteResult(Result result) {
        showShortToast("笔记删除成功");
    }

    @Override
    public void showUserNote(List<UserNoteBean.DataBean> result) {
        mAdapter.getPageBean().setRefresh(true);
        mAdapter.clear();
        updateData(result);
    }
    private int numberOfLines = 1;
    private List<RecordChartBean.DataBean.ChartBean> mChartBeen;
    private ComboLineColumnChartData data;
//    private boolean hasAxes = true;
//    private boolean hasPoints = true;
//    private boolean hasLines = true;
//    private boolean isCubic = true;
//    private boolean hasLabels = false;
    private void generateColumnData(List<RecordChartBean.DataBean.ChartBean> dataList) {
        if (EmptyUtils.isEmpty(dataList)) return;
        mChartBeen = dataList;
        data = new ComboLineColumnChartData(generateColumnData2(dataList), generateLineData(dataList));
        Axis axisX = new Axis();
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            AxisValue axisValue = new AxisValue(i);
            axisValue.setLabel(dataList.get(i).getTitle());
            axisValues.add(axisValue);
        }

        axisX.setValues(axisValues);
        data.setAxisXBottom(axisX);

        mColumnChartView.setValueSelectionEnabled(true);
        mColumnChartView.setZoomType(ZoomType.HORIZONTAL);
        mColumnChartView.setComboLineColumnChartData(data);
    }


    private LineChartData generateLineData(List<RecordChartBean.DataBean.ChartBean> dataList) {

        List<Line> lines = new ArrayList<>();
        int size = dataList.size();
        for (int i = 0; i < numberOfLines; ++i) {
            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j < size; ++j) {
                values.add(new PointValue(j, dataList.get(j).getTotal()+1));
            }
            Line line = new Line(values);
//            line.setCubic(isCubic);
            line.setHasLabels(false);
            line.setHasLines(true);
            line.setHasPoints(true);
            line.setPointRadius(3);
            line.setStrokeWidth(2);
            lines.add(line);
        }
        return new LineChartData(lines);
    }


    private ColumnChartData generateColumnData2(List<RecordChartBean.DataBean.ChartBean> dataList) {
        int numSubcolumns = 1;
        int numColumns = dataList.size();
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        int parseColor = getColor(R.color.transparent);
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add( new SubcolumnValue(dataList.get(i).getTotal(), parseColor));
            }
            columns.add(new Column(values));
        }
        ColumnChartData chartData = new ColumnChartData(columns);
        chartData.setFillRatio(0.2f);
        return chartData;
    }
    /**
     * 日历和图标显示方式的切换
     */
    public boolean change() {
        this.showCalendar = !showCalendar;
        circlePointMonthView.setVisibility(showCalendar ? View.VISIBLE : View.GONE);
        rlRecordCalendarChart.setVisibility(showCalendar ? View.GONE : View.VISIBLE);
        if (!showCalendar && !firstInitChart) {
            firstInitChart = true;
            refreshChart("day", "yyyy-MM-dd");
        }
        return this.showCalendar;
    }

    private Map<String, String> chartMap;

    private void refreshChart(String type, String format) {
        if (EmptyUtils.isEmpty(chartMap)) {
            chartMap = MapUtils.getDefMap(true);
        }
        chartMap.put("type", type);
        mPresenter.getChartData(ApiKey.PRACTICE_GET_CHART_DATA, chartMap);
    }


}
