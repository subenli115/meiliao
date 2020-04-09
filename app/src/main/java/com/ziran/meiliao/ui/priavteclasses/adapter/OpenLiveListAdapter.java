package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.LiveRoomBean;

/**
 * Created by Administrator on 2017/1/14.
 */

public class OpenLiveListAdapter extends CommonRecycleViewAdapter<LiveRoomBean.DataBean.HeraldListBean> {

    public OpenLiveListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, LiveRoomBean.DataBean.HeraldListBean bean, int position) {
        helper.setImageRoundUrl(R.id.iv_item_open_live_list_pic, bean.getPicture());
        helper.setText(R.id.tv_item_open_live_list_title, bean.getTitle());
        helper.setText(R.id.tv_item_open_live_list_date, TimeUtil.getStringByFormat( bean.getDateTime()));
    }
}
