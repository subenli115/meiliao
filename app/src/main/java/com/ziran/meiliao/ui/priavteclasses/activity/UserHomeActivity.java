package com.ziran.meiliao.ui.priavteclasses.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.fastjson.JSONArray;
import com.aliyun.player.AliPlayer;
import com.aliyun.player.AliPlayerFactory;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ACache;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.im.activity.ChatActivity;
import com.ziran.meiliao.im.adapter.UserSpaceAdapter;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.GiftsReceivedBean;
import com.ziran.meiliao.ui.bean.HobbySelectBean;
import com.ziran.meiliao.ui.bean.LabelHobbyBean;
import com.ziran.meiliao.ui.bean.MeSpaceBean;
import com.ziran.meiliao.ui.bean.SelectBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.util.MainHomeHeadViewV2Util;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.NewCacheUtil;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.pupop.SimpleGivePopupWindow;
import com.ziran.meiliao.widget.pupop.UserPopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_LABEL_GETPAGE;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_USERTABLE_GETPAGE;


public class UserHomeActivity extends  CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {
    @Bind(R.id.tv_name)
    public TextView tvName;
    @Bind(R.id.iv_real_name)
    public ImageView ivRealName;
    @Bind(R.id.iv_real_person)
    public ImageView ivRealPerson;
    @Bind(R.id.iv_give_gift)
    public ImageView ivGift;
    @Bind(R.id.iv_chat)
    public ImageView ivChat;
    @Bind(R.id.tv_other)
    public TextView tvOther;
    @Bind(R.id.tv_distance)
    public TextView tvDistance;
    @Bind(R.id.jmui_right_btn)
    public ImageView rightBtn;
    @Bind(R.id.iv_top_head)
    public ImageView ivHead;
    @Bind(R.id.recyclerView)
    public IRecyclerView iRecyclerView;
    @Bind(R.id.arl)
    public AutoRelativeLayout headView;


    private String userId;
    private UserBean.DataBean dataBean;
    private List<String> data;
    private MainHomeHeadViewV2Util newMainHomeHeadViewUtil;
    private UserSpaceAdapter meMainAdapter;
    private SimpleGivePopupWindow simplePayPopupWindow;
    private List<GiftsReceivedBean.DataBean.RecordsBean> giftDatas;
    private boolean isSelf;
    private ArrayList<String> infoLists;
    private ArrayList<SelectBean> objects;
    private UserPopupWindow pop;
    private AliPlayer aliyunVodPlayer;
    private List<HobbySelectBean.DataBean> selectlist;
    private List<SelectBean> selectObjects;
    private NewCacheUtil newCacheUtil;


    public static void startAction(String userId) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, UserHomeActivity.class);
        intent.putExtra("userId", userId);
        activity.startActivity(intent);
    }
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_other_home_new;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }


    @Override
    public void initView() {
        infoLists = new ArrayList<>();
        objects = new ArrayList<>();
        selectObjects = new ArrayList<>();
        if (getIntent() != null) {
            userId = getIntent().getStringExtra("userId");
            getGiftList();
        }
        if(userId.equals(MyAPP.getUserId())){
            isSelf=true;
        }else {
            ivChat.setVisibility(View.VISIBLE);
            ivGift.setVisibility(View.VISIBLE);
        }
        if(MyAPP.getmUserBean()!=null){
            if(MyAPP.getmUserBean().getTeenagersIsOpen()!=null&&MyAPP.getmUserBean().getTeenagersIsOpen().equals("0")){
                //青少年模式
                ivGift.setVisibility(View.GONE);
            }
        }
         newCacheUtil = new NewCacheUtil(mContext);
        Object bean = newCacheUtil.getDataBean(userId, UserBean.DataBean.class);
        if(bean!=null){
           dataBean= (UserBean.DataBean) bean;
           initData();
        }else {
            getData();
        }
        List spaces = newCacheUtil.getDataList("space" + userId, String.class);
        if(spaces!=null){
            data=  spaces;
        }else {
            getSpaceData();
        }

        List labels = newCacheUtil.getDataList("label" + userId, SelectBean.class);
        if(labels!=null){
            selectObjects= labels;
        }else {
            getTagList();
        }

        aliyunVodPlayer = AliPlayerFactory.createAliPlayer(this.getApplicationContext());
        iRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        iRecyclerView.setFocusableInTouchMode(false);
        iRecyclerView.setFocusable(false);
        iRecyclerView.setHasFixedSize(true);
        iRecyclerView.setNestedScrollingEnabled(false);
        meMainAdapter = new UserSpaceAdapter(mContext,data, this.dataBean,giftDatas,infoLists, isSelf, selectObjects);
        iRecyclerView.setAdapter(meMainAdapter);
        newMainHomeHeadViewUtil = new MainHomeHeadViewV2Util(iRecyclerView, dataBean, isSelf,this,aliyunVodPlayer);
        simplePayPopupWindow = new SimpleGivePopupWindow(this);
        UserBean.DataBean dataBean = MyAPP.getmUserBean();
        if (dataBean.getUserAccount() != null) {
            UserAccountBean.DataBean data = dataBean.getUserAccount();
            if(data!=null){
                String gold = (int) (data.getRecharge() + data.getCurrency()) + "";
                simplePayPopupWindow.setTvMoney(gold,userId,dataBean.getNickname());
            }
        }
        mRxManager.on(AppConstant.RXTag.UPDATE_USER, new Action1<String>() {
            @Override
            public void call(String balance) {
                    getData();
            }
        });
        mRxManager.on(AppConstant.RXTag.UPDATE_OTHERUSER, new Action1<String>() {
            @Override
            public void call(String balance) {
                getGiftList();
            }
        });
    }

    private void getGiftList() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("receiveUserId", userId);
        mPresenter.getData(ApiKey.ADMIN_GIFTRECORD_PAGE,defMap, GiftsReceivedBean.class);
    }

    private void initData() {
        if(dataBean!=null){
            initInfoList();
            tvName.setText(dataBean.getNickname());
            Glide.with(mContext).load(dataBean.getAvatar()).error(R.drawable.jmui_head_icon).into(ivHead);
            if(dataBean.getIsReal()!=null&&dataBean.getIsReal().equals("1")){
                ivRealPerson.setVisibility(View.VISIBLE);
            }
            if(isSelf){
                rightBtn.setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {

                    }
                });
                tvDistance.setVisibility(View.GONE);
                tvOther.setVisibility(View.GONE);
                rightBtn.setVisibility(View.GONE);
            }else {
                if(dataBean.getDistance()!=null){
                    if(dataBean.getDistance().equals("0.0")){
                        tvDistance.setText("距离 "+0+"m");
                    }else {
                        if(Double.parseDouble(dataBean.getDistance())>1){
                            tvDistance.setText("距离 "+dataBean.getDistance()+"km");
                        }else {
                            tvDistance.setText("距离 "+(int)(Double.parseDouble(dataBean.getDistance())*1000)+"m");
                        }
                    }
                }
                    if(dataBean.getIsFollow()!=null&&dataBean.getIsFollow().equals("1")){
                        tvOther.setVisibility(View.GONE);
                    }
            }
        }
    }

    private void initInfoList() {
        infoLists.clear();
            infoLists.add(dataBean.getAge()+"岁");
            if(dataBean.getSex()==1){
                infoLists.add("男");
            }else {
                infoLists.add("女");
            }
            if(dataBean.getRegion()!=null&&(dataBean.getRegion()+"").length()>0){
                infoLists.add(dataBean.getRegion());
            }
            if(dataBean.getConstellation()!=null&&(dataBean.getConstellation()+"").length()>0){
                infoLists.add(dataBean.getConstellation()+"");
            }
            if(dataBean.getHeight()!=null&&(dataBean.getHeight()+"").length()>0){
                infoLists.add(Math.round((Double) dataBean.getHeight())+"cm");
            }
            if(dataBean.getShape()!=null&&(dataBean.getShape()+"").length()>0){
                infoLists.add(dataBean.getShape()+"");
            }
            if(dataBean.getSchool()!=null&&(dataBean.getSchool()+"").length()>0){
                infoLists.add(dataBean.getSchool()+"");
            }
            if(dataBean.getPersonality()!=null&&(dataBean.getPersonality()+"").length()>0){
                String personality = dataBean.getPersonality()+"";
                String[] split = personality.split(",");
                if(split!=null){
                    infoLists.addAll(Arrays.asList(split));
                }else {
                    infoLists.add(personality);
                }
            }

            if(dataBean.getMajor()!=null&&(dataBean.getMajor()+"").length()>0){
                infoLists.add(dataBean.getMajor()+"");
            }
            if(dataBean.getJob()!=null&&(dataBean.getJob()+"").length()>0){
                infoLists.add(dataBean.getJob()+"");
            }
            if(dataBean.getAnnualSalary()!=null&&(dataBean.getAnnualSalary()+"").length()>0){
                infoLists.add(dataBean.getAnnualSalary()+"");
            }
            if(dataBean.getIndustry()!=null&&(dataBean.getIndustry()+"").length()>0){
                infoLists.add(dataBean.getIndustry()+"");
            }
            if(dataBean.getEducation()!=null&&(dataBean.getEducation()+"").length()>0){
                infoLists.add(dataBean.getEducation()+"");
            }
    }


    //点击监听
    @OnClick({R.id.jmui_return_btn,R.id.iv_chat,R.id.iv_give_gift,R.id.tv_other,R.id.jmui_right_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jmui_return_btn:
                finish();
                break;
            case R.id.iv_give_gift:
                simplePayPopupWindow.show();
                break;
            case R.id.iv_chat:
                if (Utils.isFastDoubleClick()) {
                    return;
                } else {
                    if(dataBean!=null){
                        gotoChatActivity(mContext);
                    }
                }
                break;
            case R.id.tv_other:
                follow();
                break;

            case  R.id.jmui_right_btn:
                pop = new UserPopupWindow(mContext,userId);
                pop.show();
                break;
        }
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

    @Override
    public void returnData(Result result) {
        if(result instanceof GiftsReceivedBean){
            giftDatas = ((GiftsReceivedBean) result).getData().getRecords();
            meMainAdapter.update(data, dataBean, giftDatas,infoLists,selectObjects);
        }else if(result instanceof UserBean){
            dataBean = ((UserBean) result).getData();
            newCacheUtil.saveUserBean(dataBean);
            newMainHomeHeadViewUtil = new MainHomeHeadViewV2Util(iRecyclerView, dataBean, isSelf, this, aliyunVodPlayer);
            initData();
        }else if(result instanceof MeSpaceBean){
            data = ((MeSpaceBean) result).getData();
            newCacheUtil.saveSpaceBean(data,userId);
            meMainAdapter.update(data, dataBean, giftDatas,infoLists,selectObjects);
        }else if(result instanceof LabelHobbyBean){
            objects.clear();
            selectObjects.clear();
            List<LabelHobbyBean.DataBean>    listData = ((LabelHobbyBean) result).getData();
            for( int i=0; i<listData.size();i++){
                SelectBean selectBean = new SelectBean();
                selectBean.setText("");
                selectBean.setName(listData.get(i).getLabelContent());
                selectBean.setType(listData.get(i).getId()+"");
                selectBean.setImg(listData.get(i).getLabelImages());
                objects.add(selectBean);
            }
            getSelectList();
        }else if(result instanceof HobbySelectBean){
            selectlist = ((HobbySelectBean) result).getData();
            for(int i=0;i<selectlist.size();i++){
                for(int k=0;k<objects.size();k++){
                    if(selectlist.get(i).getType().equals(objects.get(k).getType())){
                        if(!objects.get(k).getText().equals("")){
                            objects.get(k).setText(objects.get(k).getText()+","+selectlist.get(i).getTableName());
                        }else {
                            objects.get(k).setText(selectlist.get(i).getTableName());
                        }
                    }
                }
            }
            for(int i=0;i<objects.size();i++){
                if(objects.get(i).getText().length()>0){
                    selectObjects.add(objects.get(i));
                }
            }
            newCacheUtil.saveLabelBean(selectObjects,userId);
            meMainAdapter.update(data, dataBean, giftDatas,infoLists,selectObjects);
        }else if(result instanceof StringDataV2Bean){
            tvOther.setVisibility(View.GONE);
            dataBean.setIsFollow("1");
            newCacheUtil.saveUserBean(dataBean);
        }
    }


    public void saveHomeBean(List<String> list) {
        newCacheUtil.saveHomeBean(list,userId);
    }
    public List<String> getHomeBean(){
        List dataList = newCacheUtil.getDataList("homeData" + userId, String.class);
        return dataList;

    }

    private void getSelectList() {
        Map<String, String> defMap1 = MapUtils.getDefMap(true);
        defMap1.put("userId", userId);
        mPresenter.getData(ADMIN_USERTABLE_GETPAGE,defMap1, HobbySelectBean.class);
    }

    private void  getSpaceData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId", userId);
        mPresenter.getData(ApiKey.ADMIN_SPACE_IMGPAGE,defMap, MeSpaceBean.class);
    }
    private void getTagList() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("level","1");
        mPresenter.getData(ADMIN_LABEL_GETPAGE,defMap, LabelHobbyBean.class);
    }


    public void getData() {
        if(isSelf){
            mPresenter.getDataOneHead(ApiKey.ADMIN_USER_COMPLETEUSERINFO,userId,MyAPP.getAccessToken(),UserBean.class);
        }else {
            mPresenter.getDataOneHead(ApiKey.ADMIN_USER_COMPLETEOTHERSUSERINFO,userId,MyAPP.getAccessToken(),UserBean.class);
        }
    }

    private void follow(){
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("followUserId", userId);
        defMap.put("userId", MyAPP.getUserId());
        mPresenter.postData(ApiKey.ADMIN_USERFOLLOW_ADD,defMap, StringDataV2Bean.class);
    }


    @Override
    public void returnAction(Result result) {

    }

    @Override
    public void onDestroy() {
        if(aliyunVodPlayer!=null) {
            aliyunVodPlayer.release();
            aliyunVodPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        if(aliyunVodPlayer!=null){
            newMainHomeHeadViewUtil.onPause();
        }
        super.onPause();
    }

}