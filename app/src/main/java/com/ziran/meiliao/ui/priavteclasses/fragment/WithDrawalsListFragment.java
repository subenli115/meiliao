package com.ziran.meiliao.ui.priavteclasses.fragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/7/31 15:18
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/7/31$
 * @updateDes ${TODO}
 */

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.WithDrawalsDetailBean;
import com.ziran.meiliao.ui.priavteclasses.activity.WithDrawalsItemDetailActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.WithDrawalsDetailAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 查看提现明细界面
 * Created by Administrator on 2017/1/7.
 */

public class WithDrawalsListFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<WithDrawalsDetailBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        rootView.setBackgroundColor(getColor(R.color.gray));
        times = new ArrayList<>();
        return new WithDrawalsDetailAdapter(getContext(), R.layout.item_with_drawals_detail);
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        WithDrawalsDetailBean.DataBean dataBean  = EmptyUtils.parseObject(o);
        startActivity(WithDrawalsItemDetailActivity.class,getBundle(dataBean.getOrderNo()));
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.LIVE_GET_MONEY_SUPPLY_INTRO, MapUtils.getOnlyPage(page), WithDrawalsDetailBean.class);
    }


    @Override
    public void returnData(WithDrawalsDetailBean result) {
        updateData(cahnge(result.getData()));
    }

    @Override
    public void onRefresh() {
        times.clear();
        super.onRefresh();
    }

    List<String> times;

    private List cahnge(List<WithDrawalsDetailBean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            WithDrawalsDetailBean.DataBean dataBean = data.get(i);
            dataBean.setExpectTime(TimeUtil.getStringByFormat(dataBean.getDateTime()));
            String charSequence = dataBean.getExpectTime().substring(0, 7);
            if (times.contains(charSequence)) {
                dataBean.setShowTop(false);
            } else {
                dataBean.setShowTop(true);
                times.add(charSequence);

            }
        }
        return data;
    }
}
