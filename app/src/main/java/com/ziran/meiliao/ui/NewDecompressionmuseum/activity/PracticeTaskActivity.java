package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.ChooseTeacharAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.PracticeCourseAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.PracticeCourseChildInfoAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeWorkBookContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.fragment.CalendarFragment;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeWorkBookPresenter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeCalendarBean;
import com.ziran.meiliao.ui.bean.PracticeHeadBean;
import com.ziran.meiliao.ui.bean.PracticeJoinBean;
import com.ziran.meiliao.ui.bean.PracticeStartBean;
import com.ziran.meiliao.ui.bean.PracticeStatusBean;
import com.ziran.meiliao.ui.bean.PracticeWorkBookBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_CALENDAR;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_HEAD;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_INFO;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_JOIN;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_START;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_STATUSCALENDAR;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_STATUSUPDATE;

/**
 *  on 2018/7/9.
 */

public class PracticeTaskActivity extends CommonHttpActivity<PracticeWorkBookPresenter, CommonModel> implements PracticeWorkBookContract.View,View.OnClickListener, OnItemClickListener {
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.iv_me_main_avatar)
    ImageView ivMeMainAvatar;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_exe_dyas)
    TextView tvExeDyas;
    @Bind(R.id.tv_teacher_speak)
    TextView tvTeacherSpeak;
    @Bind(R.id.tv_exe_record)
    TextView tvExeRecord;
    @Bind(R.id.all_top)
    AutoLinearLayout allTop;
    @Bind(R.id.tv_line)
    TextView tvLine;
    @Bind(R.id.all_calendy)
    AutoLinearLayout allCalendy;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.recyclerView)
    RecyclerView mainRecyclerView;
    @Bind(R.id.arlMain)
    AutoRelativeLayout arlMain;
    @Bind(R.id.all_weeks)
    LinearLayout  allWeeks;
    @Bind(R.id.tv_join)
    TextView tvJoin;
    @Bind(R.id.tv_week)
    TextView tvWeek;
    @Bind(R.id.all_practice)
    AutoLinearLayout allPractice;
    @Bind(R.id.back_today)
    TextView backToday;
    @Bind(R.id.arl)
    AutoRelativeLayout arl;
    private PracticeCourseAdapter mAdapter;
    private View contentView;
    private RecyclerView recyclerViewPop;
    private ChooseTeacharAdapter mPopAdapter;
    private TextView tvClose;
    private TextView tvQd;
    private View contentViewPop;
    private View contentViewQuit;
    private int status;
    private String shareUrl;
    private String shareDescribe;
    private String shareTitle;
    public boolean isMusicList;
    private String sharePic;
    private int mWeek;
    private PracticeCalendarBean.DataBean mResult;
    private List<PracticeCalendarBean.DataBean.ExerciseListBean> exerciseList;
    private String bookId;
    private String weekDays="";
    private String weekString;
    private int count;
    private boolean first=true;
    private String startDate;
    private List<PracticeStatusBean.DataBean.ExerciseListBean> stutasList;
    private int mPosition;
    private List<PracticeCalendarBean.DataBean.ExerciseListBean> exerciseListBeans;
    private int forWeekDay;
    private PracticeCalendarBean.DataBean.ExerciseListBean exercise;
    private PracticeCourseChildInfoAdapter practiceCourseChildInfoAdapter;
    private CalendarFragment tFragment;
    private Bundle bundle;
    private String[] stringArray;
    private int firstStatus;
    private ArrayList<String> changeList;
    private ArrayList<String> normalList;
    private List<TextView> weekViews;
    private AutoLinearLayout all;
    private Calendar calendar;
    private Map<String, String> calendarMap;
    private int daysNum;
    private ArrayList<PracticeCalendarBean.DataBean.ExerciseListBean> exerciseListOther;
    private PracticeHeadBean.DataBean ShareResult;
    private String textWeek;
    private boolean isSameWeek=true;
    private PracticeCalendarBean.DataBean mData;

    @Override
    public int getLayoutId() {

        return R.layout.activity_jdx_course_main;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, PracticeTaskActivity.class);
        context.startActivity(intent);
    }


    public static void startAction(Context context,String bookId,String days) {
        Intent intent = new Intent(context, PracticeTaskActivity.class);
        intent.putExtra("BookId",bookId);
        intent.putExtra("days",days);
        context.startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateIsExercise();
        if (tvUserName != null && MyAPP.getUserInfo() != null) {
            tvUserName.setText(MyAPP.getUserInfo().getNickName());
        }
    }

    @Override
    public void initView() {
        if(getIntent()!=null){
            Intent intent = getIntent();
            bookId = intent.getStringExtra("BookId");
        }
        backToday.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        backToday.getPaint().setAntiAlias(true);//抗锯齿
         calendar = Calendar.getInstance();
        mWeek = calendar.get(Calendar.DAY_OF_WEEK);
        ntbTitle.setNewTitleText("MBSR八周练习册");
        ntbTitle.setTvLeftVisiable(true, true);
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        ntbTitle.setRightImag2Src(R.mipmap.jdx_title_dot);
        ntbTitle.setOnRightImag2Listener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        calendarMap = MapUtils.getDefMap(true);
        calendarMap.put("id", bookId);
        stringArray =new String[56];
        ntbTitle.setTitleWeizhi();
        initList();
        initListenner();
        //订阅更新用户头像
        ImageLoaderUtils.displayCircle(getBaseContext(), ivMeMainAvatar, StringUtils.headImg(), R.mipmap.ic_user_pic);
        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setOnRightImag2Listener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWindow();
            }
        });
        mPresenter.getPracticeWookBookData(PRACTIEACTIVITY_INFO, MapUtils.getDefMap(true));
        Map<String, String> headMap = MapUtils.getDefMap(true);
        headMap.put("id", bookId);
        mPresenter.getPracticeHead(PRACTIEACTIVITY_HEAD,headMap);
        setCanlendarVis();

    }

    private void initListenner() {
        for(int i=0;i<allWeeks.getChildCount();i++){
            final int finalI = i;
            allWeeks.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int k=0;k<allWeeks.getChildCount();k++){
                        ((LinearLayout) allWeeks.getChildAt(k)).getChildAt(0).setBackgroundResource(R.drawable.shape_dot_gray);
                    }
                    TextView tv = (TextView)((LinearLayout) allWeeks.getChildAt(finalI)).getChildAt(0);
                    tv.setBackgroundResource(R.drawable.shape_dot_orange);
                    //如果选中日期
                    if(tv.getText().toString().equals("今")){
                        backToday.setVisibility(View.INVISIBLE);
                    }else {
                        backToday.setVisibility(View.VISIBLE);
                    }
                    updateInfoForDays("",finalI);
                }
            });
        }
    }

    private void initList() {
         normalList=new ArrayList<>();
        changeList=new ArrayList<>();
        weekViews=new ArrayList<>();
        normalList.add("星期一");
        normalList.add("星期二");
        normalList.add("星期三");
        normalList.add("星期四");
        normalList.add("星期五");
        normalList.add("星期六");
        normalList.add("星期日");
    }

    private void showWeekLayout() {
        if(changeList.size()==0){
            for(int i=0;i<normalList.size();i++){
                if(weekDays!=null&&weekDays.equals(normalList.get(i))){
                    for(int k=i;k<normalList.size();k++){
                        changeList.add(normalList.get(k));
                    }
                    for(int r=0;r<i;r++){
                        changeList.add(normalList.get(r));
                    }
                }
            }
        }
        for(int i=0;i<allWeeks.getChildCount();i++){
            ((TextView)((LinearLayout) allWeeks.getChildAt(i)).getChildAt(0)).setText(changeList.get(i).substring(2,3));
        }
        switch (mWeek){
            case 1:
                weekString="星期日";
                break;
            case 2:
                weekString="星期一";
                break;
            case 3:
                weekString="星期二";
                break;
            case 4:
                weekString="星期三";
                break;
            case 5:
                weekString="星期四";
                break;
            case 6:
                weekString="星期五";
                break;
            case 7:
                weekString="星期六";
                break;
        }
        if( mResult.getDays()==56){
            weekString=weekDays;
        }
        for(int i=0;i<changeList.size();i++){
            LinearLayout all = (LinearLayout) allWeeks.getChildAt(i);
            if(changeList.get(i).equals(weekString)){
                all.getChildAt(0).setBackgroundResource(R.drawable.shape_dot_orange);
                TextView at = ((TextView) all.getChildAt(0));
                if(isSameWeek){
                    at.setText("今");
                }
            }else{
                all.getChildAt(0).setBackgroundResource(R.drawable.shape_dot_gray);
            }
        }


    }


    private void updateInfoForDays(String days,int week) {
        Map<String, String> calendarMap = MapUtils.getDefMap(true);
        calendarMap.put("id", bookId);
        if(week!=-1){
            calendarMap.put("days",count*7+week+1+"");
        }else{
            calendarMap.put("days",days);
        }
            if(Integer.parseInt(calendarMap.get("days"))>56){
                calendarMap.put("days","56");
            }
        mPresenter.getPracticeCalendar(PRACTIEACTIVITY_CALENDAR,calendarMap);
    }
    private void updateIsExercise() {
        mPresenter.getPracticeStatus(PRACTIEACTIVITY_STATUSCALENDAR,calendarMap);
    }

    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(mContext).inflate(R.layout.item_jdx_title_right, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arlMain, Gravity.TOP, 0, 0);
        TextView quit = contentView.findViewById(R.id.tv_quit_description);
        TextView course = contentView.findViewById(R.id.tv_course_description);
        TextView tv_share = contentView.findViewById(R.id.tv_share);

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             MbsrWorkbookMainActivity.startAction(mContext,bookId,daysNum+"");
            }
        });


        tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                share();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                showQuitPopWindow();
            }
        });
    }

    private void share() {
        if (ShareResult == null) return;
        SharePopupWindow.showPopup(mContext, mSharePopupWindow, ShareResult);
    }
    SharePopupWindow mSharePopupWindow;
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        arl.setAlpha(bgAlpha);
    }
    private void showQuitPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentViewQuit = LayoutInflater.from(mContext).inflate(R.layout.pop_jdx_quit, null);
        contentViewQuit.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentViewQuit,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        setBackgroundAlpha(0.5f);
        popupWindow.showAtLocation(arlMain, Gravity.TOP, 0, 666);
        TextView quit = contentViewQuit.findViewById(R.id.tv_qx);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        TextView qd = contentViewQuit.findViewById(R.id.tv_qd);

        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> stringMap = MapUtils.getDefMap(true);
                stringMap.put("status", "0");
                stringMap.put("id",bookId);
                mPresenter.getPracticeJoin(PRACTIEACTIVITY_JOIN, stringMap);
                popupWindow.dismiss();
                finish();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
    }
    /**
     * 选择导师弹窗
     */
    private void showPopWindow1() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentViewPop = LayoutInflater.from(mContext).inflate(R.layout.pop_jdx_choose_mentor, null);
        contentViewPop.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentViewPop,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arlMain, Gravity.TOP, 0, 330);
        recyclerViewPop= contentViewPop.findViewById(R.id.recyclerView);
        recyclerViewPop.setLayoutManager(new LinearLayoutManager(this));
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        recyclerViewPop.setAdapter(mPopAdapter = new ChooseTeacharAdapter(list, mContext));
        mPopAdapter.setItemClickListener(this);
        tvClose = contentViewPop.findViewById(R.id.tv_close);
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        tvQd = contentViewPop.findViewById(R.id.tv_qd);
        tvQd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }


    @OnClick({R.id.all_calendy, R.id.tv_teacher_speak, R.id.tv_exe_record,R.id.tv_join,R.id.back_today})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_calendy:
                if (frameLayout.getVisibility() == View.GONE) {
                    setCanlendarGone();
                } else {
                    setCanlendarVis();
                }
                break;
            case R.id.tv_teacher_speak:
                break;
            case R.id.back_today:
                if( mResult.getDays()!=56){
                    mWeek = calendar.get(Calendar.DAY_OF_WEEK);
                }
                forWeekDay=Integer.parseInt(daysNum+"");
                first=true;
                backToday.setVisibility(View.INVISIBLE);
                updateInfoForDays(daysNum+"",-1);
                backToday.setText("返回今天");
                tFragment.scrollCurrentDay(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH),true);
                break;
            case R.id.tv_exe_record:
                PracticeRecordActivity.startAction(mContext,bookId);
                break;
            case R.id.tv_join:
                //开始练习
                startPractice();
                break;
        }

    }

    private void startPractice() {
        Map<String, String> stringMap = MapUtils.getDefMap(true);
        if(status==0){
            stringMap.put("status", "1");
        }else{
            stringMap.put("status", "0");
        }
        stringMap.put("id",bookId);
        mPresenter.getPracticeStart(PRACTIEACTIVITY_START,stringMap);
    }

    private void setCanlendarGone() {
        allCalendy.setBackgroundResource(R.drawable.normal_bg_tran);
        ImageView imageView = (ImageView) allCalendy.getChildAt(0);
        imageView.setImageResource(R.mipmap.jdx_calendy_top);
        TextView textView = (TextView) allCalendy.getChildAt(1);
        textView.setTextColor(Color.parseColor("#444444"));
        frameLayout.setVisibility(View.VISIBLE);
    }

    private void setCanlendarVis() {
        allCalendy.setBackgroundResource(R.drawable.normal_bg_tran_orange);
        ImageView imageView = (ImageView) allCalendy.getChildAt(0);
        imageView.setImageResource(R.mipmap.jdx_calendy_bottom);
        TextView textView = (TextView) allCalendy.getChildAt(1);
        textView.setTextColor(Color.parseColor("#FF8947"));
        frameLayout.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {

    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }

    @Override
    public void showWookBookData(PracticeWorkBookBean.DataBean result) {
         status = result.getStatus();
    }

    @Override
    public void showPracticeJoinData (PracticeJoinBean.DataBean result) {
        MbsrWorkbookMainActivity.startAction(mContext,bookId+"");
        finish();
    }

    @Override
    public void showPracticeStartData(PracticeStartBean.DataBean result) {
        if(tvJoin.getVisibility()==View.VISIBLE){
            tvJoin.setVisibility(View.GONE);
        }
      startDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        tvExeDyas.setText("已练习1天");
        updateIsExercise();
        first=true;
        updateInfoForDays(1+"",-1);

    }

    @Override
    public void showPracticeCalendarData(PracticeCalendarBean.DataBean result) {
        mData=result;
        initData(result);
        if(first){
            first=false;
            String firstWeek = initWeekData(result);
        }
    }

    private void initData(PracticeCalendarBean.DataBean result) {
         exerciseList = result.getExerciseList();
        exerciseListBeans = new ArrayList<>();
        exerciseListOther= new ArrayList<>();
        mResult=result;
        weekDays = result.getWeekDays();
        if(result.getDays()==56){
            weekString=weekDays;
        }
        for(int i = 0; i< this.exerciseList.size(); i++){
            if(this.exerciseList.get(i).getIsOption()==1){
                exerciseListBeans.add(this.exerciseList.get(i));
            }else{
                exerciseListOther.add(this.exerciseList.get(i));
            }
        }
//        if(mAdapter!=null){
//            mAdapter.setData(exerciseList,exerciseListBeans);
//        }else{
            mAdapter = new PracticeCourseAdapter(exerciseListOther, mContext, bookId, exerciseListBeans);
            mAdapter.setItemClickListener(new PracticeCourseAdapter.ItemClickListener() {

                @Override
                public void itemClick(int position, int itemId, int typeId) {
                    updateIsExercise();
                    mPosition = position;
                    if(typeId!=2){

                        Map<String, String> statusMap = MapUtils.getDefMap(true);
                        isMusicList = false;
                        statusMap.put("id", bookId);
                        statusMap.put("itemId", itemId + "");
                        statusMap.put("status", "1");
                        mPresenter.changePracticeStatus(PRACTIEACTIVITY_STATUSUPDATE, statusMap);
                    }else{
                        exercise = exerciseListOther.get(mPosition);
                        WeekNineDotActivity.startAction(mContext,bookId,exercise.getItemId()+"");
                    }
                }
            });
            mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mainRecyclerView.setAdapter(mAdapter);
//        }
        if(exerciseListBeans!=null&&exerciseListBeans.size()>0) {
//            if (practiceCourseChildInfoAdapter != null) {
//                practiceCourseChildInfoAdapter.setData(exerciseList);
//            } else{
                RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.select_info_jdx_recyclerview, null);
                practiceCourseChildInfoAdapter = new PracticeCourseChildInfoAdapter(exerciseListBeans, mContext, bookId);
                initHeadView();

                practiceCourseChildInfoAdapter.setItemClickListener(new PracticeCourseChildInfoAdapter.ItemClickListener() {
                    @Override
                    public void itemClick(int position, AutoFrameLayout v, int itemId, int typeId, int practiceStatus) {
                        updateIsExercise();
//                        if (typeId == 1 && practiceStatus == 0) {
                            Map<String, String> statusMap = MapUtils.getDefMap(true);
                            mPosition = position;
                            isMusicList = true;
                            statusMap.put("id", bookId);
                            statusMap.put("itemId", itemId + "");
                            statusMap.put("status", "1");
                            mPresenter.changePracticeStatus(PRACTIEACTIVITY_STATUSUPDATE, statusMap);
//                        }
                    }
                });
                practiceCourseChildInfoAdapter.addHeaderView(all);
                recyclerView.setAdapter(practiceCourseChildInfoAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                mAdapter.addHeaderView(recyclerView);
//            }
        }
    }

    private void initHeadView() {
        all = (AutoLinearLayout) LayoutInflater.from(this).inflate(R.layout.item_jdx_header_layout, null);
        ImageView iv = (ImageView) all.findViewById(R.id.iv_music);
        TextView tv= (TextView) all.findViewById(R.id.tv_hint);
        TextView mode=   all.findViewById(R.id.tv_mode);
        if(exerciseListBeans.get(0).getUseMusic()==1){
            iv.setImageResource(R.mipmap.jdx_use_music);
            mode.setText(mData.getOptionNotice());
            tv.setText("");
        }else {
            mode.setText(mData.getOptionNotice());
            tv.setText("建议不使用音频播放");
        }
    }

    private String initWeekData(PracticeCalendarBean.DataBean result) {

        count=(result.getDays()-1)/7;
        String firstWeek = updateWeekData();
        showWeekLayout();
        initCanledar();
        return   firstWeek;
    }

    private String updateWeekData() {
         textWeek="";
        switch ((forWeekDay-1)/7){
            case 0:
                textWeek="第一";
                break;
            case 1:
                textWeek="第二";
                break;
            case 2:
                textWeek="第三";
                break;
            case 3:
                textWeek="第四";
                break;
            case 4:
                textWeek="第五";
                break;
            case 5:
                textWeek="第六";
                break;
            case 6:
                textWeek="第七";
                break;
                default:
                    textWeek="第八";
                    break;
        }
        tvWeek.setText(textWeek+"");
        return textWeek;
    }

    private void initCanledar() {
         bundle = new Bundle();
        if(stutasList!=null){
            for(int i=0;i<stutasList.size();i++){
                if(stutasList.get(i).getPracticeStatus()==1){
                    stringArray[i]=i+"";
                }else{
                    stringArray[i]=0+"";
                }
            }
            bundle.putStringArray("days", stringArray);
        }
        bundle.putString("date",startDate);
        if(tFragment==null){
            tFragment = new CalendarFragment();
            tFragment.setCalenderItemClickListener(new CalendarFragment.CalenderItemClickListener() {
                @Override
                public void itemClick(long day, int week) {
                    if(day>0&&day<=56){
                        backToday.setVisibility(View.VISIBLE);
                        mWeek=week+1;
                        if((daysNum-1)/7==(day-1)/7){
                            isSameWeek=true;
                        }else {
                            isSameWeek=false;
                        }
                        forWeekDay=(int)day;//更新周数
                        setCanlendarVis();
                        first=true;

                        updateInfoForDays((int)day+"",-1);
                    }else{
                        ToastUitl.showShort("不在练习时间内");
                    }
                }
            });
            tFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().add(R.id.frameLayout, tFragment).commit();

        }else{
            tFragment.update(bundle);
        }
    }

    @Override
    public void showPracticeHeadData(PracticeHeadBean.DataBean result) {
        Glide.with(mContext).load(result.getPicture()).into(ivHead);
         daysNum = result.getDays();
         ShareResult=result;
        forWeekDay=daysNum;
        startDate = result.getStartTime();
        updateInfoForDays(daysNum+"",-1);
        updateIsExercise();
        shareDescribe = result.getShareDescribe();
         sharePic = result.getSharePic();
         shareTitle = result.getShareTitle();
         shareUrl = result.getShareUrl();
         firstStatus = result.getFirstStatus();
        if(firstStatus==0){
            tvJoin.setVisibility(View.VISIBLE);
            tvExeDyas.setText("还未练习");
        }else{
        tvJoin.setVisibility(View.GONE);
        tvExeDyas.setText("已练习"+ daysNum+"天");
            tvExeDyas.setBackgroundResource(R.drawable.normal_bg_red);
            tvExeDyas.setTextColor(getResources().getColor(R.color.white));
    }

    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {
        if(isMusicList){
             exercise= exerciseListBeans.get(mPosition);
        }else {
            exercise = exerciseListOther.get(mPosition);

        }
        switch (exercise.getTypeId()){
            case 1:
                if(exerciseListBeans.size()>0){
                    if(exercise.getUseMusic()==0&&exercise.getIsOption()==1){
                        PracticeStartActivity.startAction(mContext,45, result,bookId,exercise.getItemId()+"");
                        return;
                    }
                }
                    WeekPracticeWithPlayerActicity.startAction(mContext,exercise.getItemId()+"",bookId,result,false);

                break;
            case 2:
                WeekNineDotActivity.startAction(mContext,bookId,exercise.getItemId()+"");
                break;
            case 3:
                WeekDietHomeActivity.startAction(mContext,bookId,exercise.getItemId()+"",result,false,exercise.getPracticeStatus());
                break;
            case 4:
                WeekQuestionActivity.startAction(mContext,bookId,exercise.getItemId()+"",result,false,exercise.getPracticeStatus());
                break;
            case 5:
                WeekLiveHomeActivity.startAction(mContext,bookId,exercise.getItemId()+"",result,false,exercise.getPracticeStatus());
                break;
        }
    }


    @Override
    public void showPracticeCalendarStatusData(PracticeStatusBean.DataBean result) {
      stutasList = result.getExerciseList();
      initCanledar();
    }

}
