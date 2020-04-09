package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;


/**
 * Created by Administrator on 2017/1/14.
 */

public class SJKLiveHistoryAdapter extends CommonRecycleViewAdapter<SJKSingeLiveData> {


    public SJKLiveHistoryAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, SJKSingeLiveData bean, int position) {
        helper.setText(R.id.tv_item_sjk_live_course_listerCount, String.valueOf(bean.getWatchCount()));
        helper.setText(R.id.tv_item_sjk_live_course_name, bean.getTitle());
        helper.setImageUrlTarget(R.id.iv_item_sjk_live_course_img, bean.getPicture(),R.mipmap.ic_loading_square);
        helper.setText(R.id.tv_item_sjk_live_course_time, bean.getDuration());
        helper.setText(R.id.tv_item_sjk_live_course_lable, bean.getVip());
    }
}
