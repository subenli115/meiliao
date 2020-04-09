package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.LiveIncomeBean;
import com.ziran.meiliao.utils.StringUtils;

/**
 * 减压馆-消息
 * Created by Administrator on 2017/1/14.
 */

public class ProfitAdapter extends CommonRecycleViewAdapter<LiveIncomeBean.DataBean.IncomeListBean> {



    public ProfitAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, final LiveIncomeBean.DataBean.IncomeListBean bean, int position) {
        helper.setText(R.id.tv_item_profit_title,bean.getTitle());
        helper.setText(R.id.tv_item_profit_date, StringUtils.format("开始时间：%s", TimeUtil.getStringByFormat(bean.getTime())));

    }
}
