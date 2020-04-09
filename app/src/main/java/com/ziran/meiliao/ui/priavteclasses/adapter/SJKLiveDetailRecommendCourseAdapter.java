package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.SJKReCoommendCourseBean;
import com.ziran.meiliao.utils.StringUtils;

/**
 * Created by Administrator on 2017/1/14.
 */

public class SJKLiveDetailRecommendCourseAdapter extends CommonRecycleViewAdapter<SJKReCoommendCourseBean.DataBean> {

    public SJKLiveDetailRecommendCourseAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, SJKReCoommendCourseBean.DataBean bean, int position) {
        ImageView iv_img = helper.getView(R.id.iv_item_sjk_live_course_img);
        helper.setText(R.id.tv_item_sjk_live_course_time, bean.getDuration());
        helper.setText(R.id.tv_item_sjk_live_course_price, StringUtils.format(R.string.price,bean.getPrice()));
        helper.setText(R.id.tv_item_sjk_live_course_listerCount, String .valueOf(bean.getWatchCount()));
        helper.setText(R.id.tv_item_sjk_live_course_name, bean.getTitle());
        ImageLoaderUtils.display(mContext,iv_img,bean.getPicture(),R.mipmap.ic_loading_square_big);
    }
}
