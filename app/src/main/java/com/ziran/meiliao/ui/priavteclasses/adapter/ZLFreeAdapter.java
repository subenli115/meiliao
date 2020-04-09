package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.RecordListBean;

/**
 * 减压馆-消息
 * Created by Administrator on 2017/1/14.
 */

public class ZLFreeAdapter extends CommonRecycleViewAdapter<RecordListBean.DataBean> {


    public ZLFreeAdapter(Context context) {
        super(context, R.layout.item_zl_free);
    }

    @Override
    public void convert(final ViewHolderHelper helper, final RecordListBean.DataBean bean, int position) {
        helper.setImageUrl(R.id.iv_item_img, bean.getPic(),R.mipmap.ic_loading_square);
        helper.setText(R.id.tv_item_title,bean.getTitle());
    }
}
