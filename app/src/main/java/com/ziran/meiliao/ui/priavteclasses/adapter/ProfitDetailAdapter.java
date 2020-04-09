package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.LiveIncomeDetailBean;
import com.ziran.meiliao.utils.StringUtils;

/**
 * Created by Administrator on 2017/1/14.
 */

public class ProfitDetailAdapter extends CommonRecycleViewAdapter<LiveIncomeDetailBean.DataBean> {

    public ProfitDetailAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, LiveIncomeDetailBean.DataBean bean, int position) {
        helper.setImageCircle(R.id.iv_item_sjk_profit_detail_avatar,bean.getHeadImg());
        helper.setText(R.id.tv_item_sjk_profit_detail_content, StringUtils.format("%s %s",bean.getName(),bean.getDetail()));
        helper.setText(R.id.tv_item_sjk_profit_detail_amount, StringUtils.format("%s",(int)bean.getDetailPrice()));
    }
}
