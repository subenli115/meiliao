package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;
import com.ziran.meiliao.utils.StringUtils;

/**
 * Created by Administrator on 2017/1/14.
 */

public class CourseListAdapter extends CommonRecycleViewAdapter<SJKSingeLiveData> {

    public CourseListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, SJKSingeLiveData bean, int position) {
        helper.setImageRoundUrl(R.id.iv_item_open_live_list_pic,bean.getPicture());
        helper.setText(R.id.tv_item_open_live_list_title, bean.getTitle());
        helper.setText(R.id.tv_item_open_live_list_date_and_watch_count, StringUtils.format("%s  已播放%s次","2017-06-23","999"));
        TextView slideView = helper.getView(R.id.slide_id);
        setSildeStyle(slideView, SLIDE_STYLE_STOP);
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
