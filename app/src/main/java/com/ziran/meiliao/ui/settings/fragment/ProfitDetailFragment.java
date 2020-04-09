package com.ziran.meiliao.ui.settings.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.LiveIncomeDetailBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.ProfitDetailAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

/**
 * 老师收益明细Fragment
 * Created by Administrator on 2017/1/16.
 */

public class ProfitDetailFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<LiveIncomeDetailBean> {
    private static final String KEY_ID  = "incomeId";
    private static final String KEY_TYPE  = "type";

    private String mIncomeId ="";
    private String type="";

    public static ProfitDetailFragment newInstance(String incomeId,String type){
        ProfitDetailFragment  fragment   =new ProfitDetailFragment();
        Bundle bundle  =   new Bundle();
        bundle.putString(KEY_ID,incomeId);
        bundle.putString(KEY_TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(getString(R.string.empty_profit_detail), R.mipmap.ic_empty_course);
        return new ProfitDetailAdapter(getContext(), R.layout.item_sjk_profit_detail);
    }

    @Override
    protected void initOther() {
        mStringMap   = MapUtils.getOnlyPage(page);
        if (getArguments()!=null){
            mIncomeId = getArguments().getString(KEY_ID);
            type = getArguments().getString(KEY_TYPE);
            mStringMap.put("incomeId",mIncomeId);
            mStringMap.put("typeId",type);
        }

    }

    Map<String, String> mStringMap;

    @Override
    protected void loadData() {

        mStringMap.put("page",String.valueOf(page));
        mPresenter.postData(ApiKey.LIVE_LIVE_INCOME_DETAIL,mStringMap, LiveIncomeDetailBean.class);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
//        if (!CheckUtil.check(getContext(),getView())) return;
//        AlbumBean dataBean = (AlbumBean) o;
//        if (EmptyUtils.isNotEmpty(dataBean)) {
//            AlbumDetailActivity.startAction(getContext(), String .valueOf(dataBean.getId()));
//        }
    }

    @Override
    public void returnData(LiveIncomeDetailBean result) {
        updateData(result.getData());
    }
}
