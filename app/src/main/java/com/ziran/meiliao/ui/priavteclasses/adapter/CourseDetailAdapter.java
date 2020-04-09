package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;
import com.ziran.meiliao.utils.StringUtils;

/**
 * Created by Administrator on 2017/1/14.
 */

public class CourseDetailAdapter extends CommonRecycleViewAdapter<SJKSingeLiveData> {
    private int paddingRight;

    public CourseDetailAdapter(Context context, int layoutId) {
        super(context, layoutId);
        paddingRight = DisplayUtil.dip2px(6);
    }

    @Override
    public void convert(final ViewHolderHelper helper, SJKSingeLiveData bean, int position) {
        helper.setText(R.id.tv_item_course_detail_title, bean.getTitle());
        helper.setText(R.id.tv_item_course_detail_date, StringUtils.format("%s ／ %s ／","7月20日","00:40:13"));
        helper.setText(R.id.tv_item_course_detail_watch_count, StringUtils.format("%s人学习","999"));
        TextView tvIcon = helper.getView(R.id.tv_item_course_detail_icon);
        tvIcon.setText(String.valueOf(position + 1));
        switch (bean.getTag()) {
            case 0: //历史课程
                helper.setBackgroundRes(R.id.tv_item_course_detail_icon, R.mipmap.course_lessonbg3);
                tvIcon.setPadding(0, 0, paddingRight, 0);
                tvIcon.setTextColor(mContext.getResources().getColor(R.color.textColor_teshe));
                break;
            case 1: //直播中
                helper.setBackgroundRes(R.id.tv_item_course_detail_icon, R.mipmap.course_lessonbg2);

                tvIcon.setPadding(0, 0, paddingRight, 0);
                tvIcon.setTextColor(Color.WHITE);
                break;
            case 2: //预告
                helper.setBackgroundRes(R.id.tv_item_course_detail_icon, R.mipmap.course_lessonbg);
                tvIcon.setTextColor(Color.WHITE);
                tvIcon.setPadding(0, 0, 0, 6);
                break;
        }

        TextView slideView = helper.getView(R.id.slide_id);
        setSildeStyle(slideView, SLIDE_STYLE_DELETE);
        slideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private static final int SLIDE_STYLE_STOP = 0;
    private static final int SLIDE_STYLE_DELETE = 1;
    public void setSildeStyle(TextView textView,int style){
        switch (style){
            case SLIDE_STYLE_STOP:
                textView.setText("课程\n下架");
                textView.setBackgroundResource(R.drawable.selector_course_list_yellow);
                break;
            case SLIDE_STYLE_DELETE:
                textView.setText("删除");
                textView.setBackgroundResource(R.drawable.selector_course_list_red);
                break;
        }
    }
}
