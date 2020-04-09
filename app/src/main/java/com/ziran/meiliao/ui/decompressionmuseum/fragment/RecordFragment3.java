package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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
import com.ziran.meiliao.ui.decompressionmuseum.adapter.PracticeaHisDayAdapter;
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
import com.ziran.meiliao.widget.hellocharts.listener.ColumnChartOnValueSelectListener;
import com.ziran.meiliao.widget.hellocharts.model.Axis;
import com.ziran.meiliao.widget.hellocharts.model.AxisValue;
import com.ziran.meiliao.widget.hellocharts.model.Column;
import com.ziran.meiliao.widget.hellocharts.model.ColumnChartData;
import com.ziran.meiliao.widget.hellocharts.model.SubcolumnValue;
import com.ziran.meiliao.widget.hellocharts.view.ColumnChartView;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.functions.Action1;

import static com.ziran.meiliao.constant.ApiKey.PRACTICE_GETCHARTDATAV2;
import static com.ziran.meiliao.constant.ApiKey.PRACTICE_GETPUNCHHIS;

/**
 * 练习记录页面Fragment
 */

public class RecordFragment3 extends CommonRefreshFragment<RecordPresenter, CommonModel> implements RecordContract.View {




    @Bind(R.id.circlePointMonthView)
    CirclePointCalendarView circlePointMonthView;
    @Bind(R.id.customNumbersView)
    CustomNumbersView mCustomNumbersView;
    @Bind(R.id.columnChartView)
    ColumnChartView mColumnChartView;
    @Bind(R.id.rg_record_calendar_chart)
    RadioGroup rgRecordCalendarChart;
    @Bind(R.id.rl_record_calendar_chart)
    RelativeLayout rlRecordCalendarChart;
    @Bind(R.id.barchart)
    BarChart mChart;
    @Bind(R.id.tv_record_chart_time)
    TextView tvChartTime;
    @Bind(R.id.recyclerView_top)
    RecyclerView recyclerView;
    @Bind(R.id.scrolling_header)
    AutoFrameLayout mScrollingHeader;
    @Bind(R.id.iv_record_change)
    ImageView ivChange;
    @Bind(R.id.max_num)
    TextView maxNum;
    @Bind(R.id.mid_num)
    TextView midNum;


    private boolean showCalendar;
    private Map<String, String> tagByMonthMap;
    private AutoLinearLayout noDataView;
    private TextView tvRq;
    private TextView tvFz;


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
        return R.layout.fragment_record_calendar2;
    }


    @Override
    protected void initView() {
        super.initView();
        showCalendar = true; //显示日历
        tagByMonthMap = MapUtils.getDefMap(true);
        mColumnChartView.setZoomEnabled(false);
        initChart();
        //图表标签切换监听
        rgRecordCalendarChart.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_record_calendar_chart_day:
                        getForTypeData(0);
                        break;
                    case R.id.rb_record_calendar_chart_month:
                       getForTypeData(2);
                        break;
                    case R.id.rb_record_calendar_chart_year:
                       getForTypeData(3);
                        break;
                    case R.id.rb_record_calendar_chart_week:
                        getForTypeData(1);
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


        getForTypeData(0);
        mPresenter.getPunchHisData(PRACTICE_GETPUNCHHIS,MapUtils.getDefMap(true));
        ivChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rlRecordCalendarChart.getVisibility()==View.GONE){
                    rlRecordCalendarChart.setVisibility(View.VISIBLE);
                    ivChange.setImageResource(R.mipmap.record_calendar);
                    circlePointMonthView.setVisibility(View.GONE);
                }else{
                    rlRecordCalendarChart.setVisibility(View.GONE);
                    ivChange.setImageResource(R.mipmap.record_statistics);
                    circlePointMonthView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getForTypeData(int type) {
        Map<String, String> chartMap = MapUtils.getDefMap(true);
        chartMap.put("type",type+"");
        mPresenter.getNewChartData(PRACTICE_GETCHARTDATAV2, chartMap);
    }

    private void setGiftAdapter(PunchHisBean.DataBean data) {

        PracticeaHisDayAdapter practiceaDayAdapter = new PracticeaHisDayAdapter(data,getContext(),getActivity());
        recyclerView.setAdapter(practiceaDayAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
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
        List<PractiveChartBean.DataBean.ChartDataBean> chartData = result.getChartData();
        setData(chartData.size(),chartData,result.getX(),result.getY(),midNum,maxNum);
    }

    private void initChart() {
        tvRq = mScrollingHeader.findViewById(R.id.tv_rq);
        tvFz = mScrollingHeader.findViewById(R.id.tv_fz);
        noDataView = mChart.findViewById(R.id.all_nodata);
        mChart.setDescription("");
        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);
        mChart.setNoDataText("");
        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);
        mChart.setScaleEnabled(false);// 是否可以缩放
        mChart.setTouchEnabled(false); // 设置是否可以触摸
        mChart.setDragEnabled(false);// 是否可以拖拽
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelsToSkip(0);
        xAxis.setDrawGridLines(false);
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getLegend().setEnabled(false);
        mChart.getAxisRight().setDrawLabels(false);//右侧是否显示Y轴数值
        mChart.getAxisLeft().setDrawLabels(false);
        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getAxisRight().setEnabled(false);//是否显示最右侧竖线
        mChart.getAxisRight().setDrawAxisLine(false);
        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getXAxis().setAxisLineColor(Color.TRANSPARENT);

        int count=14;
        if(count==0){
            noDataView.setVisibility(View.VISIBLE);
        }
//        setData(14,null,"","");
    }

    public String getMax(List<PractiveChartBean.DataBean.ChartDataBean> chartData){
        String max="0";
        for(int i=0;i<chartData.size();i++){
            if(Integer.parseInt(max)<Integer.parseInt(chartData.get(i).getY_axis())){
                max=chartData.get(i).getY_axis();
            }
        }
        return max;
    }

    public void setData(int count, List<PractiveChartBean.DataBean.ChartDataBean> chartData, String x, String y, TextView tv,TextView tv1) {
        tvRq.setText(x);
        tvFz.setText(y);
        tv1.setText(getMax(chartData));
        tv.setText(Integer.parseInt(tv1.getText().toString())/2+"");
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();
        List<BarDataSet> dataSets=new ArrayList<>();
        if(chartData!=null){
            for (int i = 0; i < chartData.size(); i++) {
                float val =(float) Integer.parseInt(chartData.get(i).getY_axis());
                yVals.add(new BarEntry((int) val, i));
                xVals.add(chartData.get(i).getX_axis() + "");
            }

        }

        BarDataSet set = new BarDataSet(yVals, "Data Set1");
        set.setBarSpacePercent(50);
        set.setColor(Color.parseColor("#FEB675"));
        set.setDrawValues(false);
        dataSets.add(set);

        BarData data = new BarData(xVals, set);
        mChart.setData(data);
        mChart.setDrawBarShadow(true);
        mChart.invalidate();
        mChart.animateY(800);
        mChart.notifyDataSetChanged();
    }


    @Override
    public void showPunchHisData(PunchHisBean.DataBean data) {
        setGiftAdapter(data);
    }

    @Override
    public void showChartData(RecordChartBean result) {
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

    private List<RecordChartBean.DataBean.ChartBean> mChartBeen;

    private void generateColumnData(List<RecordChartBean.DataBean.ChartBean> dataList) {
        if (EmptyUtils.isEmpty(dataList)) return;
        mChartBeen = dataList;
        int numColumns = dataList.size();

        List<AxisValue> axisValues = new ArrayList<>();
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;

        for (int i = 0; i < numColumns; ++i) {//12
            values = new ArrayList<>();
            values.add(new SubcolumnValue(dataList.get(i).getTotal(), Color.parseColor("#FBAF6B")));
            axisValues.add(new AxisValue(i).setLabel(dataList.get(i).getTitle()));
            columns.add(new Column(values).setHasLabelsOnlyForSelected(false));
        }
        columnData = new ColumnChartData(columns);
        columnData.setValueLabelBackgroundColor(Color.parseColor("#F0F4F5"));
        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(false));
//        columnData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(2));
        mColumnChartView.setColumnChartData(columnData);

        // Set value touch listener that will trigger changes for chartTop.
        mColumnChartView.setOnValueTouchListener(new ValueTouchListener());

        // Set selection mode to keep selected month column highlighted.
        mColumnChartView.setValueSelectionEnabled(true);
        mColumnChartView.setZoomType(ZoomType.HORIZONTAL);

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

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
//            mChartBeen
            if (EmptyUtils.isNotEmpty(mChartBeen)) ViewUtil.setText(tvChartTime, mChartBeen.get(columnIndex).getText());
        }

        @Override
        public void onValueDeselected() {

        }
    }

}
