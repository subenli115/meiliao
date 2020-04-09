package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mob.moblink.MobLink;
import com.mob.moblink.Scene;
import com.mob.moblink.SceneRestorable;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.FitnessActionListAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.FitnessRecommendAdapter;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.FintessDetailBean;
import com.ziran.meiliao.ui.bean.FitnessCollectBean;
import com.ziran.meiliao.ui.settings.activity.MemberRuleActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.MyScrollView;
import com.ziran.meiliao.widget.pupop.BuyCourseKeepWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

import static com.ziran.meiliao.constant.ApiKey.JSCOURSE_COLLECT;
import static com.ziran.meiliao.constant.ApiKey.JSCOURSE_DETAIL;
import static com.ziran.meiliao.constant.ApiKey.JSCOURSE_SAVEEXITREASON;

/**
 *
 * 健身功法主页
 */
public class FitnessExeActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result>  ,MyScrollView.onScrollChangedListener, SceneRestorable {

    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.ll_bottom_container)
    AutoLinearLayout llBottomContainer;
    @Bind(R.id.iv_top)
    ImageView ivTop;
    @Bind(R.id.tv_difficulty)
    TextView tvDifficulty;
    @Bind(R.id.tv_activity_level)
    TextView tvLevel;
    @Bind(R.id.tv_length)
    TextView tvLength;
    FintessDetailBean listBean;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.all_start)
    AutoLinearLayout allStart;
    @Bind(R.id.tv_buy)
    TextView tvBuy;
    @Bind(R.id.tv_action_tab)
    TextView tvActionTab;
    @Bind(R.id.tv_recommend_tab)
    TextView tvRecommendTab;
    @Bind(R.id.iv_dot_action)
    ImageView ivDotAction;
    @Bind(R.id.iv_dot_recommend)
    ImageView ivDotRecommend;
    @Bind(R.id.tv_action_tab1)
    TextView tvActionTab1;
    @Bind(R.id.tv_recommend_tab1)
    TextView tvRecommendTab1;
    @Bind(R.id.iv_dot_action1)
    ImageView ivDotAction1;
    @Bind(R.id.iv_dot_recommend1)
    ImageView ivDotRecommend1;
    @Bind(R.id.view1)
    View viewLine;
    @Bind(R.id.view2)
    View view2;
    @Bind(R.id.scrollView)
    MyScrollView scrollView;
    @Bind(R.id.tab_title_all)
    AutoLinearLayout tabTitle;
    @Bind(R.id.arl)
    AutoRelativeLayout arl;
    @Bind(R.id.tv_count)
    TextView tvCount;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.scrolling_header)
    AutoRelativeLayout scrollingHeader;
    @Bind(R.id.tv_open_tips)
    TextView tvOpenTips ;
    @Bind(R.id.tv_start)
    TextView tvStart ;
    @Bind(R.id.all_tab)
    AutoLinearLayout allTab;
    @Bind(R.id.all_tab1)
    AutoLinearLayout allTab1;
    @Bind(R.id.recyclerView_recommend)
    RecyclerView recyclerViewRecommend;
    List<FintessDetailBean.DataBean.DetailsBean> openList;
    List<FintessDetailBean.DataBean.DetailsBean> hideList;
    private FintessDetailBean.DataBean.JsCourseBean bean;
    private boolean isOpen;//展开
    private FitnessActionListAdapter faAdapter;
    private List<FintessDetailBean.DataBean.DetailsBean> detailList;
    private FitnessRecommendAdapter frAadapter;
    private FintessDetailBean.DataBean mData;
    private int mHeight;
    private RelativeLayout buySuccess;
    private RelativeLayout backPop;
    private boolean isCollect;
    private int[] location;
    private boolean isBoolean=true;
    private String courseId;

    @Override
    public int getLayoutId() {
        return R.layout.ac_fintness_main;
    }

    @Override
    public void returnData(Result result) {
        if(result instanceof FintessDetailBean){
            listBean = (FintessDetailBean) result;
             mData = listBean.getData();
             bean=mData.getJsCourse();
            if(listBean.getData().isIsBuy()){
                llBottomContainer.setVisibility(View.GONE);
                allStart.setVisibility(View.VISIBLE);
            }else {
                llBottomContainer.setVisibility(View.VISIBLE);
                allStart.setVisibility(View.GONE);
                tvBuy.setText("购买课程（¥"+bean.getPrice()+")");
            }
             isCollect = mData.isIsCollect();
            initHead();
            updateCollect();
            tvCount.setText("共"+listBean.getData().getDetails().size()+"式");
            tvStart.setText("开始第"+listBean.getData().getUserPracticeCount()+"次练习");
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            detailList = listBean.getData().getDetails();
            faAdapter = new FitnessActionListAdapter(mContext,detailList,listBean.getData().isIsBuy(),listBean.getData());
            setData();
            recyclerView.setAdapter(faAdapter);
            recyclerView.setNestedScrollingEnabled(false);//禁止滑动
            List<FintessDetailBean.DataBean.JsCoursesRecommendBean> jsCoursesRecommend = listBean.getData().getJsCoursesRecommend();
            recyclerViewRecommend.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            recyclerViewRecommend.setNestedScrollingEnabled(false);//禁止滑动
            frAadapter = new FitnessRecommendAdapter(jsCoursesRecommend,mContext,this);
            recyclerViewRecommend.setAdapter(frAadapter);
            frAadapter.setItemClickListener(new FitnessRecommendAdapter.OnItemClickListener() {
                @Override
                public void itemClick(int position, int itemId) {
                    finish();
                    FitnessExeActivity.startAction(mContext,itemId);
                }
            });
        }else if(result instanceof FitnessCollectBean){
            isCollect=!isCollect;
            updateCollect();
        }else {
            showPopSuccess();
            postData();
        }
    }

    private void updateCollect() {
        if(isCollect){
            ntbTitle.setRightImag2Src(R.mipmap.btn_collected_black);
        }else {
            ntbTitle.setRightImag2Src(R.mipmap.btn_collect_white);
        }
    }

    public static void startAction(Context context,int id) {
        Intent intent = new Intent();
        intent.setClass(context,FitnessExeActivity.class);
        intent.putExtra("courseId",id+"");
        context.startActivity(intent);
    }
    @Override
    public void returnAction(Result result) {





    }
    SharePopupWindow mSharePopupWindow;
    private void share() {
        SharePopupWindow.showPopup(mContext, mSharePopupWindow, bean.getShareTitle(), bean.getShareDesc(), bean
                .getShareUrl(),bean.getSharePic());
    }


    /**
     *
     *  购买成功弹窗
     */
    private void showPopSuccess() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];

        buySuccess = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.pop_fitness_share_course_two, null);
        buySuccess.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(buySuccess,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(arl,Gravity.CENTER,0,0);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        TextView   tvShare = buySuccess.findViewById(R.id.tv_share_bottom);
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   share();
                   popupWindow.dismiss();
            }
        });
    }
    /**
     *
     *  购买成功弹窗
     */
    private void showPopBack() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        backPop = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.pop_fitness_bottom_quit, null);
        backPop.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(backPop,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(arl,Gravity.BOTTOM,0,0);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        final TextView   tv_one = backPop.findViewById(R.id.tv_one);
        final TextView   tv_two = backPop.findViewById(R.id.tv_two);
        final  TextView   tv_three = backPop.findViewById(R.id.tv_three);
        final  TextView   tv_four = backPop.findViewById(R.id.tv_four);

        final Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("courseId",bean.getId()+"");
        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postSave(defMap, tv_one);
                popupWindow.dismiss();
            }
        });
        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postSave(defMap, tv_two);
                popupWindow.dismiss();
            }
        });
        tv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postSave(defMap, tv_three);
                popupWindow.dismiss();
            }
        });
        tv_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postSave(defMap, tv_four);
                popupWindow.dismiss();
            }
        });

    }

    private void postSave(Map<String, String> defMap, TextView tv_one) {
        defMap.put("reason",tv_one.getText().toString());
        mPresenter.postData(JSCOURSE_SAVEEXITREASON,defMap, Result.class);
    }


    private void setData() {
         openList=new ArrayList<>();
        hideList=new ArrayList<>();
        if (detailList.size() > 4) { //设置大于多少条数据开始隐藏
            for (int i = 0, j = detailList.size(); i < j; i++) {
                openList.add(detailList.get(i));
            }
            for (int i = 0; i < 4; i++) {
                hideList.add(detailList.get(i));
            }
            faAdapter.setHideList(hideList);
        } else {
            faAdapter.setRealList(detailList);
        }
        tvOpenTips.setVisibility(detailList.size() > 4 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void initPresenter () {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(isBoolean){
            location = new int[2];
            allTab.getLocationOnScreen(location);
            isBoolean=false;
        }
    }

    @Override
    public void initView() {
        super.initView();
        mRxManager.on(AppConstant.RXTag.FITNESS_BACK, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                switch (msg.what) {
                    case 0:
                        Log.e("FITNESS_BACK","FITNESS_BACK");
                        showPopBack();

                        break;
                }
            }
        });
        if(getIntent()!=null){
             courseId = getIntent().getStringExtra("courseId");
        }
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        ntbTitle.setRightImagSrc(R.mipmap.fitness_video_down);
        ntbTitle.setRightImag2Src(R.mipmap.btn_collect_white);
        ntbTitle.setRightImag3Src(R.mipmap.share_white);
        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        ntbTitle.setOnRightImag2Listener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> collectMap = MapUtils.getDefMap(true);
                collectMap.put("courseId",bean.getId()+"");
                collectMap.put("cancel",(!isCollect ? 1 : 0)+"");
                 mPresenter.postData(JSCOURSE_COLLECT,collectMap,FitnessCollectBean.class);

            }
        });
        ntbTitle.setOnRightImag3Listener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     share();
            }
        });
        scrollView.addOnScrollChangedListener(this);
        postData();
        mRxManager.on(AppConstant.RXTag.REQ_BUY_COURSE, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                mPresenter.postData(ApiKey.JSCOURSE_BUY, MapUtils.getCourseMap(bean.getId()+""),Result.class);
            }
        });
        mRxManager.on(AppConstant.RXTag.REQ_BUY_COURSE_FINISH, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                postData();
            }
        });
        arl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeight = ivTop.getHeight();
                onScrollChanged(scrollView.getScrollY());
            }
        });


    }

    private void postData() {
        Map<String, String> map = MapUtils.getDefMap(true);
        map.put("courseId", courseId);
        mPresenter.postData(JSCOURSE_DETAIL, map, FintessDetailBean.class);
    }


    private void initHead() {
        Glide.with(mContext).load(bean.getDetailPic()).into(ivTop);
        tvLength.setText(bean.getDuration()/60+"分钟");
        tvLevel.setText(bean.getDifficult());
        tvDifficulty.setText(bean.getActivityAmount());
        tvTitle.setText(bean.getName());
        tvContent.setText(bean.getDescription());
    }
//scrollTo指定位置
    private void changeViewShow(int position) {
        switch (position) {
            case R.id.tv_action_tab1:
            case R.id.tv_action_tab:
                //封面
                scrollView.scrollTo(0, viewLine.getTop());
                break;
            case R.id.tv_recommend_tab1:
            case R.id.tv_recommend_tab:
                //基本信息
                scrollView.scrollTo(0, tabTitle.getTop());
                break;

            default:
                break;
        }
    }


    //点击监听
    @OnClick({R.id.tv_recommend_tab,R.id.tv_action_tab,R.id.tv_open_tips,R.id.tv_buy,R.id.all_start,R.id.tv_recommend_tab1,R.id.tv_action_tab1,R.id.tv_look_all,R.id.tv_free_listen})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_free_listen:
                FitnessLoadActivity.startAction(mContext,mData);
                break;
            case R.id.tv_recommend_tab1:
                setScrollTab(ivDotRecommend1, ivDotAction1, tvRecommendTab1, tvActionTab1, R.id.tv_recommend_tab1);
                break;
            case R.id.tv_recommend_tab:
                changeViewShow( R.id.tv_recommend_tab);
                break;
            case R.id.tv_action_tab1:
                setScrollTab(ivDotAction1, ivDotRecommend1, tvActionTab1, tvRecommendTab1, R.id.tv_action_tab1);
                break;
            case R.id.tv_action_tab:
                changeViewShow( R.id.tv_action_tab);
                break;
            case R.id.tv_open_tips:
                if(faAdapter!=null){
                if (isOpen) {
                    faAdapter.setHideList(hideList);
                    tvOpenTips.setText("展开全部");
                    tvOpenTips.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.course_arrow_down, 0);
                    isOpen = false;
                } else {
                        faAdapter.setOpenList(openList);
                        tvOpenTips.setText("收起全部");
                        tvOpenTips.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.course_arrow_up, 0);
                        isOpen = true;
                    }
                }
                break;
            case R.id.tv_buy:
                showBuyPop();
                break;
            case R.id.all_start:
              FitnessLoadActivity.startAction(mContext,mData);
//                PrefUtils.putLong("FitnessStartTime",System.currentTimeMillis(),mContext);
//                FitnessVideoPlayerActivity.startAction(mContext,mData,bean.getId());
                break;
            case R.id.tv_look_all:
                MemberRuleActivity.startAction(mContext,mData.getShowAllUrl(),mData);
                break;

        }
    }

    private void setScrollTab(ImageView ivDotRecommend, ImageView ivDotAction, TextView tvRecommendTab, TextView tvActionTab, int p) {
        ivDotRecommend.setVisibility(View.VISIBLE);
        ivDotAction.setVisibility(View.INVISIBLE);
        tvRecommendTab.setTextColor(Color.parseColor("#FF333333"));
        tvActionTab.setTextColor(Color.parseColor("#999999"));
        changeViewShow(p);
    }

    public void showBuyPop() {
        BuyCourseKeepWindow popupwindow = new BuyCourseKeepWindow(mContext);
        popupwindow.setText(mData.getJsCoursesRecommend().get(0).getName(), String.valueOf(mData.getUserCoin()), mData.getNeedCoin()+1000,mData.getNeedCoin());
        popupwindow.setUrl(bean.getThumbnailPic());
        popupwindow.setStyle(mData.getUserCoin()<=mData.getNeedCoin());
        PopupWindowUtil.show((Activity) mContext, popupwindow);
    }


    @Override
    public void onScrollChanged(int y) {

        if (y <= 0) {//未滑动
            ntbTitle.setBackGroundDraw(Color.argb( 0, 192, 192, 192));
        } else if (y > 0 && y <= mHeight) { //滑动过程中 并且在mHeight之内
            float scale = (float) y / mHeight;
            float alpha = (255 * scale);
            ntbTitle.setBackGroundDraw(Color.argb((int) alpha, 192, 192, 192));
        } else {//超过mHeight
            ntbTitle.setBackGroundDraw(Color.argb( 255, 192, 192, 192));
        }
    if(location!=null){

        if(scrollView.getScrollY()+ntbTitle.getHeight()>=location[1]){
            allTab1.setVisibility(View.VISIBLE);
        }else {
            allTab1.setVisibility(View.GONE);
        }
    }
    }
    @Override
    // 必须重写该方法，防止MobLink在某些情景下无法还原
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        MobLink.updateNewIntent(getIntent(), this);
    }
    @Override
    public void onReturnSceneData(Scene scene) {
        courseId = (String) scene.getParams().get("courseId");
    }
}
