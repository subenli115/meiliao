package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.LiveRoomBean;

/**
 *  直播间后台-开播历史适配器
 */

public class OpenHistoryAdapter extends CommonRecycleViewAdapter<LiveRoomBean.DataBean.HeraldListBean> {

    public OpenHistoryAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper,LiveRoomBean.DataBean.HeraldListBean bean, int position) {
        ImageLoaderUtils.display(helper.getImageView(R.id.iv_item_to_be_open_img),bean.getPicture());
        helper.setText(R.id.tv_item_to_be_open_title,bean.getTitle());
        helper.setText(R.id.tv_item_to_be_open_date_and_count, TimeUtil.getStringByFormat(bean.getDateTime()));
    }

}
