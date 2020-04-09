package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.LiveIncomeBean;
import com.ziran.meiliao.ui.priavteclasses.activity.ProfitDetailActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.ProfitAdapter;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/7 14:00
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/7$
 * @updateDes ${TODO}
 */

public class ProfitFragment  extends CommonRefreshFragment {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        rootView.setBackgroundColor(Color.TRANSPARENT);
        setRecyclerEnabled(false,false);
        return new ProfitAdapter(getContext(),R.layout.item_sjk_profit);
    }

    @Override
    protected void loadData() {
        if (getArguments()!=null){
            LiveIncomeBean.DataBean  live  =    getArguments().getParcelable("liveIncome");
            if (live!=null)
            updateData(live.getIncomeList());
        }
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        LiveIncomeBean.DataBean.IncomeListBean bean = EmptyUtils.parseObject(o);
       startActivity(ProfitDetailActivity.class,getBundle(String.valueOf(bean.getIncomeId())));
    }
}
