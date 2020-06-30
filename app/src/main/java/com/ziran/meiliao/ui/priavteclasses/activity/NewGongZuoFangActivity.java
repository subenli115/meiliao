package com.ziran.meiliao.ui.priavteclasses.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.CourseDeatilBean;
import com.ziran.meiliao.ui.bean.MTPCourseModel;
import com.ziran.meiliao.ui.priavteclasses.adapter.CourseDeatilAdapter;
import com.ziran.meiliao.utils.CountDownUtils;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.ACTIVITY_GETACTIVITYDETAILS;

public class NewGongZuoFangActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result>  {



    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    AutoRelativeLayout arlBanner;
    @Bind(R.id.tv_endTime)
    TextView tvEndTime;
    @Bind(R.id.arl_activity)
    AutoRelativeLayout arlActivity;
    @Bind(R.id.tv_bird_price)
    TextView tvBirdPrice;
    @Bind(R.id.tv_sign_up)
    TextView tvSignUp;
    @Bind(R.id.tv_NoPrice)
    TextView tvNoPrice;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private String courseId;
    private String[] titleList;
    private List<MTPCourseModel> modelArrayList;
    private Timer timer;
    private TabLayout tabLayout;
    private CourseDeatilBean.DataBean data;
    private String name;
    private double lastPrice;
    private String[] titleTabList;


    @Override
    public int getLayoutId() {
            return R.layout.activity_course_detail;
    }

    @Override
    public void returnData(Result result) {
        CourseDeatilBean allData=(CourseDeatilBean)result;
        data = allData.getData();
        String courseDesc = data.getCourseDesc();
        String courseContent = data.getCourseContent();
        List<CourseDeatilBean.DataBean.TeachersBean> teachers = data.getTeachers();
        String towardPeople = data.getTowardPeople();
        String coursePlan = data.getCoursePlan();
        String stuReport = data.getStuReport();
        String baomingFee = data.getBaomingFee();
        if(data.getEarlyTime()-System.currentTimeMillis()>0){
            tvEndTime.setText("距结束仅剩"+TimeUtil.formatData("dd天HH时mm分ss秒", data.getEarlyTime()-System.currentTimeMillis()));
            arlActivity.setVisibility(View.VISIBLE);
            tvBirdPrice.setText("早鸟价￥"+data.getEarlyPrice());
            tvNoPrice.setText(data.getPrice()+"");
            tvNoPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            lastPrice=data.getEarlyPrice();
            tvSignUp.setText("立即报名(￥"+lastPrice+")");
            tvNum.setText("仅限"+data.getRemain()+"位");
            CountDownUtils.initData((data.getEarlyTime()-System.currentTimeMillis())/1000);
            timer.schedule(new TimerTask() {
                public void run() {
                    if (CountDownUtils.secondNotAlready) {
                        CountDownUtils.startCount(tvEndTime,mContext);
                    } else {
                        cancel();
                    }
                }
            }, 0, 1000);
        }else{
            arlActivity.setVisibility(View.GONE);
            lastPrice=data.getPrice();
            tvSignUp.setText("立即报名(￥"+lastPrice+")");
        }
        if(data.isBuy()){
            tvSignUp.setVisibility(View.GONE);
            arlActivity.setVisibility(View.GONE);
        }
        if (courseDesc!=null&&courseDesc.length() > 0) {
            MTPCourseModel mtpCourseModel = new MTPCourseModel();
            mtpCourseModel.setContent(data.getCourseDesc());
            mtpCourseModel.setTitle(titleList[0]);
            modelArrayList.add(mtpCourseModel);
        }
        for(int i=0;i<teachers.size();i++){
          if(teachers.get(i).getTeacherDesc()!=null&&teachers.get(i).getTeacherDesc().length() > 0){
            MTPCourseModel mtpCourseModel = new MTPCourseModel();
            if(i==0){
                mtpCourseModel.setTitle(titleList[1]);
            }else{
                mtpCourseModel.setTitle("");
            }
            mtpCourseModel.setContent(teachers.get(i).getTeacherDesc());
            mtpCourseModel.setTeacherName(teachers.get(i).getTeacherName());
            mtpCourseModel.setTeacherDegree(teachers.get(i).getDegree());
            mtpCourseModel.setAvatarUrl(teachers.get(i).getTeacherImg());
            modelArrayList.add(mtpCourseModel);
        }
        }

        if (courseContent!=null&&courseContent.length() > 0) {
            MTPCourseModel mtpCourseModel = new MTPCourseModel();
            mtpCourseModel.setContent(data.getCourseContent());
            mtpCourseModel.setTitle(titleList[2]);
            modelArrayList.add(mtpCourseModel);
        }
        if(towardPeople!=null&&towardPeople.length() > 0){
            MTPCourseModel mtpCourseModel = new MTPCourseModel();
            mtpCourseModel.setContent(data.getTowardPeople());
            mtpCourseModel.setTitle(titleList[3]);
            modelArrayList.add(mtpCourseModel);
        }
        if(stuReport!=null&&stuReport.length() > 0){
            MTPCourseModel mtpCourseModel = new MTPCourseModel();
            mtpCourseModel.setContent(data.getStuReport());
            mtpCourseModel.setTitle(titleList[5]);
            modelArrayList.add(mtpCourseModel);
        }
        if(coursePlan!=null&&coursePlan.length() > 0){
            MTPCourseModel mtpCourseModel = new MTPCourseModel();
            mtpCourseModel.setContent(data.getCoursePlan());
            mtpCourseModel.setTitle(titleList[4]);
            mtpCourseModel.setCourseImgHeight(data.getCourseImgHeight());
            mtpCourseModel.setCourseImgWidth(data.getCourseImgWidth());
            mtpCourseModel.setCourseUrl(data.getCourseTableImg());
            modelArrayList.add(mtpCourseModel);
        }
        if(baomingFee!=null&&baomingFee.length()>0){
            MTPCourseModel mtpCourseModel = new MTPCourseModel();
            mtpCourseModel.setContent(data.getBaomingFee());
            mtpCourseModel.setTitle(titleList[6]);
            modelArrayList.add(mtpCourseModel);
        }

        CourseDeatilAdapter courseDeatilAdapter = new CourseDeatilAdapter(modelArrayList,mContext);

        arlBanner = (AutoRelativeLayout) LayoutInflater.from(this).inflate(R.layout.item_imageview, null);
        ImageView ivBanner = arlBanner.findViewById(R.id.iv_banner);
        Glide.with(mContext).load(data.getBanner()).into(ivBanner);
        courseDeatilAdapter.addHeaderView(arlBanner);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) ;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(courseDeatilAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {
                    mShouldScroll = false;
                    smoothMoveToPosition(recyclerView, mToPosition);
                }
            }
        });
    }
    /**
     * 移动到对应的位置
     *
     * @param position tab 的下标
     */
    private void move(int position) {
        String name = titleTabList[position];
        //体系列表的下标
        int index = 0;
        for (MTPCourseModel bean : modelArrayList) {
            if (bean.getTitle().equals(name)) {
                index = modelArrayList.indexOf(bean);
                smoothMoveToPosition(recyclerView,index+1);
            }
        }
    }
    /**
     * 滑动到指定位置
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前，使用smoothScrollToPosition
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后，最后一个可见项之前
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                // smoothScrollToPosition 不会有效果，此时调用smoothScrollBy来滑动到指定位置
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }
    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;
    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void returnAction(Result result) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer=null;
    }

    public static void startAction(Context context, String courseId, String name) {
        Intent intent = new Intent();
        intent.putExtra("courseId",courseId+"");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name",name+"");
        intent.setClass(context, NewGongZuoFangActivity.class);
        context.startActivity(intent);
    }




    @Override
    public void initView() {
        super.initView();
        if(getIntent()!=null){
            courseId=getIntent().getStringExtra("courseId");
            name=getIntent().getStringExtra("name");
        }
         timer = new Timer();
         modelArrayList = new ArrayList<>();
        titleList = ArrayUtils.getArray(this, R.array.course_title);
        titleTabList = ArrayUtils.getArray(this, R.array.course_title_tab);
        ntbTitle.setRightImag2Src(R.mipmap.ic_sjk_live_list_share);
        ntbTitle.setBackGroundColor1();
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        ntbTitle.setTitleWeizhi();
        ntbTitle.setTitleText(name);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        ntbTitle.setOnRightImag2Listener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        for(int i=0;i<titleTabList.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(titleTabList[i]));
        }
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#93DEDB"));
        tabLayout.setTabTextColors(Color.parseColor("#2A2A2A"), Color.parseColor("#93DEDB"));



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listView返回到顶部
                recyclerView.smoothScrollToPosition(0);
            }
        });
        setListener();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                move(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("courseId",courseId);
        mPresenter.postData(ACTIVITY_GETACTIVITYDETAILS,defMap, CourseDeatilBean.class);
    }

    /**
     * 添加ScrollListener监听
     * 以及HideAndShowListener回调
     */
    private void setListener() {
        recyclerView.addOnScrollListener(new MyScrollListener(new MyScrollListener.HideAndShowListener() {
            @Override
            public void hide() {
                // 隐藏动画--属性动画
                fab.hide();
            }

            @Override
            public void show() {
                // 显示动画--属性动画
                fab.show();
            }
        }));
    }

    public static class MyScrollListener extends RecyclerView.OnScrollListener {
        private HideAndShowListener mHideAndShowListener;
        private static final int THRESHOLD = 20;
        private int distance = 0;
        private boolean visible = true;


        public MyScrollListener(HideAndShowListener hideAndShowListener) {
            mHideAndShowListener = hideAndShowListener;
        }


        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            /**
             * dy:Y轴方向的增量
             * 有正和负
             * 当正在执行动画的时候，就不要再执行了
             */
            Log.i("tag","dy--->"+dy);
            if (distance > THRESHOLD && visible) {
                //隐藏动画
                visible = false;
                mHideAndShowListener.hide();
                distance = 0;
            } else if (distance < -20 && !visible) {
                //显示动画
                visible = true;
                mHideAndShowListener.show();
                distance = 0;
            }
            if (visible && dy > 0 || (!visible && dy < 0)) {
                distance += dy;
            }
        }

        public interface HideAndShowListener {
            void hide();

            void show();
        }
    }

    private void share() {
        SharePopupWindow.showPopup(mContext, mSharePopupWindow, data.getShareTitle(), data.getShareDesc(), data
                .getShareUrl(),data.getSharePic());
    }
    SharePopupWindow mSharePopupWindow;
}
