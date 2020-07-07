package com.ziran.meiliao.im.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.commonutils.ACache;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.adapter.UserSpaceAdapter;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.im.view.SimpleAppsGridView;
import com.ziran.meiliao.ui.bean.GiftsReceivedBean;
import com.ziran.meiliao.ui.bean.MeSpaceBean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.util.NewMainHomeHeadViewUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.pupop.SimpleGivePopupWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

public class OtherUserHomeActivity extends BaseActivity {


    @Bind(R.id.recyclerView)
    IRecyclerView iRecyclerView;
    @Bind(R.id.arl_content)
    AutoRelativeLayout arl_content;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar1)
    Toolbar toolbar1;
    @Bind(R.id.nsv)
    NestedScrollView nsv;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.all_gift)
    AutoLinearLayout allGift;

    private NewMainHomeHeadViewUtil newMainHomeHeadViewUtil;
    private UserBean.DataBean dataBean;
    private String userId;
    private UserSpaceAdapter meMainAdapter;
    private List<GiftsReceivedBean.DataBean.RecordsBean> giftDatas;
    private SimpleAppsGridView gridView;
    private String gold;
    private SimpleGivePopupWindow simplePayPopupWindow;
    private ACache mCache;
    private Gson g;
    private List<String> data;
    private boolean rxFlag;

    public static void startAction(String userId) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, OtherUserHomeActivity.class);
        intent.putExtra("userId", userId);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_other_home;
    }

    @Override
    public void initPresenter() {
    }


    @Override
    public void initView() {
        if (getIntent() != null) {
            userId = getIntent().getStringExtra("userId");
        }
        if(MyAPP.getmUserBean()!=null){
            if(MyAPP.getmUserBean().getTeenagersIsOpen()!=null&&MyAPP.getmUserBean().getTeenagersIsOpen().equals("0")){
                //青少年模式
                allGift.setVisibility(View.GONE);
            }
        }
        g = new Gson();
        mCache = ACache.get(this);
        if(mCache.getAsString(""+userId)!=null){
            String userString = mCache.getAsString(""+userId);
            dataBean = g.fromJson(userString,  UserBean.DataBean.class);
            initData();
        }
        iRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        iRecyclerView.setFocusableInTouchMode(false);
        iRecyclerView.setFocusable(false);
        iRecyclerView.setHasFixedSize(true);
        iRecyclerView.setNestedScrollingEnabled(false);
        meMainAdapter = new UserSpaceAdapter(mContext, new ArrayList<>(), this.dataBean, new ArrayList<>(), false);
        iRecyclerView.setAdapter(meMainAdapter);
        newMainHomeHeadViewUtil = new NewMainHomeHeadViewUtil(iRecyclerView, dataBean, false);
//        getGiftList();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setAvatorChange();
        simplePayPopupWindow = new SimpleGivePopupWindow(this);
        UserBean.DataBean dataBean = MyAPP.getmUserBean();
        if (dataBean.getUserAccount() != null) {
            UserAccountBean.DataBean data = dataBean.getUserAccount().getData();
            if(data!=null){
                String gold = (int) (data.getRecharge() + data.getCurrency()) + "";
                simplePayPopupWindow.setTvMoney(gold,userId,dataBean.getNickname());
            }
        }
        mRxManager = new RxManager();
        mRxManager.on(AppConstant.RXTag.UPDATE_OTHERUSER, new Action1<String>() {
            @Override
            public void call(String balance) {
                getGiftList();
                rxFlag=true;
            }
        });
    }



    /**
     * 渐变toolbar背景
     */
    private void setAvatorChange() {
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float percent = scrollY * 1.0f / (arl_content.getY() - toolbar1.getHeight());
                Rect scrollRect = new Rect();
                nsv.getHitRect(scrollRect);
                //子控件在可视范围内（至少有一个像素在可视范围内）
                if (toolbar.getLocalVisibleRect(scrollRect)) {
                    toolbar1.setVisibility(View.GONE);
                } else {
                    toolbar1.setVisibility(View.VISIBLE);
                }
                if (percent > 1) {
                    percent = 1;
                }
                int i = changeAlpha(Color.parseColor("#FAFAFA"), percent);
                toolbar1.setBackgroundColor(i);
            }
        });
    }

    public void getSpaceData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId", userId);
        OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_SPACE_IMGPAGE, defMap, new
                NewRequestCallBack<MeSpaceBean>(MeSpaceBean.class) {
                    @Override
                    public void onSuccess(MeSpaceBean result) {
                         data = result.getData();
                        meMainAdapter.update(data, dataBean, giftDatas);

                    }

                    @Override
                    public void onError(String msg, int code) {

                    }
                });

    }

    private void getData() {
        OkHttpClientManager.getAsyncHead(ApiKey.ADMIN_USER_COMPLETEOTHERSUSERINFO, userId, MyAPP.getAccessToken(), new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                        dataBean = result.getData();
                        String json = g.toJson(dataBean);
                        mCache.put(""+userId, json, 2 * ACache.TIME_DAY);//保存两天，如果超过两天去获取这个key，将为null
                        newMainHomeHeadViewUtil = new NewMainHomeHeadViewUtil(iRecyclerView, dataBean, false);
                        initData();
                        getSpaceData();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getGiftList();
    }

    public void getGiftList() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("receiveUserId", userId);
        OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_GIFTRECORD_PAGE, defMap, new
                NewRequestCallBack<GiftsReceivedBean>(GiftsReceivedBean.class) {
                    @Override
                    public void onSuccess(GiftsReceivedBean result) {
                        giftDatas = result.getData().getRecords();
                        if(dataBean==null){
                            getData();
                        }else {
                            getSpaceData();
                        }
                    }

                    @Override
                    public void onError(String msg, int code) {

                    }
                });
    }

    private void initData() {
        if(dataBean!=null){
            Glide.with(mContext).load(dataBean.getHomepageImages()).error(R.drawable.jmui_head_icon).into(ivBg);
            Glide.with(mContext).load(dataBean.getAvatar()).error(R.drawable.jmui_head_icon).into(ivHead);
        }
    }


    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    //点击监听
    @OnClick({R.id.all_gift, R.id.all_chat})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        switch (view.getId()) {
            case R.id.all_gift:
                simplePayPopupWindow.show();
                break;
            case R.id.all_chat:
                if (Utils.isFastDoubleClick()) {
                    return;
                } else {
                    gotoChatActivity(mContext);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
    }

    private void gotoChatActivity(Context mContext) {
        Intent intent = new Intent();
        intent.putExtra(JGApplication.CONV_TITLE, dataBean.getNickname());
        intent.putExtra("targetId", userId);
        intent.putExtra("targetAppKey", MeiliaoConfig.IM_APPKEY);
        intent.putExtra("draft", "");
        intent.setClass(mContext, ChatActivity.class);
        mContext.startActivity(intent);
    }


}