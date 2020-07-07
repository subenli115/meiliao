package com.ziran.meiliao.ui.main.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.me.activity.EditUserInfoActivity;
import com.ziran.meiliao.ui.me.activity.SetNoteActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 头部工具类 on 2018/12/20.
 */

public class NewMainHomeHeadViewUtil {
    private final Context mContext;
    private final View headView;
    private final IRecyclerView mRecyclerView;
    private final AutoRelativeLayout arlSign;
    private final TextView tvSex;
    private final TextView tvPeople;
    private final TextView tvSign;
    private final TextView tvLikes;
    private final TextView tvAge;
    private final TextView tvUsername;
    private final TextView tvRegion;
    private final ImageView ivReal;
    public final int REQUEST_CODE_B = 1;
    private final AutoLinearLayout allDay;
    private final AutoLinearLayout all;
    private final AutoLinearLayout allLike;
    private final boolean isSelf;
    private final TextView tvDay;
    private final ImageView ivMore;
//    private final ImageView ivHead;

    public NewMainHomeHeadViewUtil(IRecyclerView iRecyclerView, UserBean.DataBean dataBean, boolean isSelf) {
        this.isSelf=isSelf;
        mContext = iRecyclerView.getContext();
        mRecyclerView = iRecyclerView;
        headView = LayoutInflater.from(mContext).inflate(R.layout.headerview_main_home_new, null);
        tvSex = headView.findViewById(R.id.tv_sex);
        ivMore = headView.findViewById(R.id.iv_more);
        allDay = headView.findViewById(R.id.all_day);
        all = headView.findViewById(R.id.all);
        tvDay = headView.findViewById(R.id.tv_day_visit);
        allLike = headView.findViewById(R.id.all_like);
        tvPeople = headView.findViewById(R.id.tv_people);
        tvLikes = headView.findViewById(R.id.tv_likes);
        tvSign = headView.findViewById(R.id.tv_sign);
        ivReal = headView.findViewById(R.id.iv_real_name);
        tvAge = headView.findViewById(R.id.tv_age);
        tvRegion = headView.findViewById(R.id.tv_region);
        arlSign = headView.findViewById(R.id.arl_sign);
        tvUsername = headView.findViewById(R.id.tv_username);
        if(isSelf){
            ivMore.setVisibility(View.VISIBLE);
            arlSign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.isFastDoubleClick()) {
                        return;
                    }else{
                        //弹出Toast或者Dialog
                        EditUserInfoActivity.startAction();
                    }
                }
            });
        }
        if(iRecyclerView.isHasHeaderView()){
            iRecyclerView.getHeaderContainer().removeAllViews();
            iRecyclerView.setHasHeaderView(false);
            iRecyclerView.setHeadView(headView);
        }else {
            iRecyclerView.setHeadView(headView);
        }
        initData(dataBean);
    }

    public void startActivity(Class clz) {
//        if (CheckUtil.check(mContext)) {
        Intent intent = new Intent(mContext, clz);
        mContext.startActivity(intent);
//        }
    }

    public void initData( UserBean.DataBean dataBean) {
        if (dataBean != null) {
            if (dataBean.getSex() == 1) {
                tvSex.setText("男");
            } else {
                tvSex.setText("女");
            }
            tvAge.setText(dataBean.getAge() + "岁");
            tvRegion.setText(dataBean.getRegion() + "");
            if (dataBean.getIntroduce() != null && !dataBean.getIntroduce().equals("")) {
                tvSign.setText(dataBean.getIntroduce() + "");
                tvSign.setTextColor(Color.parseColor("#000000"));
            }else{
                if(!isSelf){
                    tvSign.setText("期待认识有趣的人和事");
                }

            }
            tvUsername.setText(dataBean.getNickname());
            if (dataBean.getIdCard() == null || dataBean.getIdCard().equals("")) {
                ivReal.setVisibility(View.GONE);
            }
            tvLikes.setText(dataBean.getLikeNum() + "");
            tvPeople.setText(dataBean.getVisitorNum() + "");
            if(!isSelf){
                all.setVisibility(View.GONE);
                allLike.setVisibility(View.GONE);
                allDay.setVisibility(View.VISIBLE);
                setDay(dataBean);
            }
        }

    }

    private void setDay(UserBean.DataBean dataBean) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date date = format.parse(dataBean.getUpdateTime());
            int day = differentDaysByMillisecond(date, new Date());
            if(day==0){
                tvDay.setText("刚刚来过");
            }else if(day<30){
                tvDay.setText(day+"天前来过");
            }else {
                tvDay.setText("很久没来过");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public static int differentDaysByMillisecond(Date date1, Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
}
