package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.entry.CourseBean;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;

import java.util.Random;

/**
 * Created by Administrator on 2017/2/9.
 */

public class BuyCourseAdapter extends OneSlideAdapter<CourseBean.DataBean> {

    Random random;
    public BuyCourseAdapter(Context context, int layoutId) {
        super(context, layoutId);
        random = new Random();
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(OneSlideViewHolder holder, CourseBean.DataBean itemData,final int position) {
        holder.setImageUrlTarget(R.id.iv_item_buy_course_img, itemData.getPicture());
        holder.setText(R.id.tv_item_buy_course_title, itemData.getTitle());
    }


}

