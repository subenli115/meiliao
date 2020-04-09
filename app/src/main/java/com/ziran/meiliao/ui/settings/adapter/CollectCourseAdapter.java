package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.text.Html;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.R;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.CourseEntry;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;

import java.util.HashSet;

/**
 * Created by Administrator on 2017/2/9.
 */

public class CollectCourseAdapter extends OneSlideAdapter<CourseEntry> {

    public CollectCourseAdapter(Context context, int layoutId) {
        super(context, layoutId);
        from_type = FROM_COURSE;
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(final OneSlideViewHolder holder, final CourseEntry itemData, final int position) {
        holder.setImageUrlTarget(R.id.iv_item_collect_course_img,
                itemData.getPicture(), R.mipmap.ic_loading_square);
        holder.setText(R.id.tv_item_collect_course_title, itemData.getTitle());
        holder.setText(R.id.tv_item_collect_course_play_count, StringUtils.format(R.string.play_count, itemData.getWatchCount()));
        if (EmptyUtils.isNotEmpty(itemData.getDetail())){
            holder.setText(R.id.tv_item_collect_course_profile, Html.fromHtml( itemData.getDetail()));
        }
        holder.setText(R.id.tv_item_collect_course_anchor, itemData.peakAuthor().getName());
    }

    @Override
    protected void deleteSlef(HashSet<CourseEntry> valueSet) {
        if (EmptyUtils.isEmpty(valueSet)) return;
        StringBuilder sb = new StringBuilder();
        for (CourseEntry dataBean : valueSet) {
            sb.append(",").append(dataBean.getId());
        }
        sb.deleteCharAt(0);
        OkHttpClientManager.postAsync(ApiKey.COLLECT_COURSE, MapUtils.getCollect(true, "courseIds", sb.toString()), new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }
}

