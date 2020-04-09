package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.WithDrawalsDetailBean;
import com.ziran.meiliao.utils.StringUtils;

/**
 * Created by Administrator on 2017/1/14.
 */

public class WithDrawalsDetailAdapter extends CommonRecycleViewAdapter<WithDrawalsDetailBean.DataBean> {

    public WithDrawalsDetailAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, WithDrawalsDetailBean.DataBean bean, int position) {
        helper.setVisible(R.id.tv_item_with_drawals_detail_top, bean.isShowTop());

        if (bean.isShowTop()) helper.setText(R.id.tv_item_with_drawals_detail_top, bean.getExpectTime().substring(0, 7));
        helper.setText(R.id.tv_item_with_drawals_detail_amount,bean.getIntro());
        helper.setText(R.id.tv_item_with_drawals_detail_time, bean.getExpectTime());
        helper.setText(R.id.tv_item_with_drawals_detail_balance, StringUtils.format("-%d", bean.getMoney()));
    }

}
