package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.graphics.Color;

import com.ziran.meiliao.R;
import com.ziran.meiliao.entry.ClassBean;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;

import java.util.Random;

/**
 * 已购课程适配器
 */

public class BuyClassAdapter extends OneSlideAdapter<ClassBean.DataBean.ListBean> {

    Random random;
    public BuyClassAdapter(Context context, int layoutId) {
        super(context, layoutId);
        random = new Random();
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.iv_item_sjk_act_img;
    }

    @Override
    public void convertData(OneSlideViewHolder holder, ClassBean.DataBean.ListBean itemData,final int position) {
        holder.setImageUrlTarget(R.id.iv_item_sjk_act_img, itemData.getPicture());
        holder.setText(R.id.tv_person, itemData.getMembers()+"人");
        holder.setText(R.id.tv_address, itemData.getDetail());
        holder.setText(R.id.tv_teacher_name, itemData.getHost());
        holder.setText(R.id.tv_title, itemData.getTitle());
        String courseStatus = itemData.getCourseStatus();
        holder.setText(R.id.iv_item_sjk_act_label, itemData.getCourseStatus());
        if(courseStatus.equals("进行中")){
            holder.setBackgroundColor(R.id.iv_item_sjk_act_label,R.color.red);
        }else if(courseStatus.equals("未开始")){
            holder.setBackgroundColor(R.id.iv_item_sjk_act_label, Color.parseColor("#F0AE5C"));
        }else{
            holder.setBackgroundColor(R.id.iv_item_sjk_act_label,R.color.gray);
        }
    }


}

