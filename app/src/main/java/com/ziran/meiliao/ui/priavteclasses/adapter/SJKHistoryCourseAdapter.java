package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.SJKHistoryBean;

/**
 * Created by Administrator on 2017/1/14.
 */

public class SJKHistoryCourseAdapter extends CommonRecycleViewAdapter<SJKHistoryBean.DataBean> {

    public SJKHistoryCourseAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, SJKHistoryBean.DataBean bean, int position) {
        ImageView iv_avatar = helper.getView(R.id.iv_item_sjk_history_course_img);
        helper.setText(R.id.tv_item_sjk_history_course_name, bean.getTitle());
        ImageLoaderUtils.display(mContext, iv_avatar, bean.getPicture(),R.mipmap.ic_loading_square_big);
        helper.setText(R.id.tv_item_sjk_history_course_listerCount, String.format(mContext.getString(R.string.watch_count), bean.getWatchCount()));
        helper.setText(R.id.tv_item_sjk_history_course_lable, bean.getVip());
    }
}
