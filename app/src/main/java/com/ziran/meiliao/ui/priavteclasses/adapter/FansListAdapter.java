package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.FansBean;

/**
 * Created by Administrator on 2017/1/14.
 */

public class FansListAdapter extends CommonRecycleViewAdapter<FansBean.DataBean> {

    public FansListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, FansBean.DataBean bean, int position) {
        helper.setImageCircle(R.id.iv_item_fans_list_avatar, bean.getPicture());
        helper.setText(R.id.tv_item_fans_list_name, bean.getName());
    }

}
