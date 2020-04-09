package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.PracticeListBean;
import com.ziran.meiliao.ui.bean.RecordTotalBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.decompressionmuseum.adapter.PracticeListAdapter;
import com.ziran.meiliao.ui.decompressionmuseum.contract.RecordCourseContract;
import com.ziran.meiliao.ui.decompressionmuseum.presenter.RecordCoursePresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.CustomNumbersView;

import java.util.List;
import java.util.Map;

/**
 * 练习记录页面Fragment
 */

public class RecordCourseFragment extends CommonRefreshFragment<RecordCoursePresenter, CommonModel> implements RecordCourseContract.View {


    //    @Bind(R.id.customNumbersView)
    CustomNumbersView mCustomNumbersView;

    private Map<String, String> tagByMonthMap;


    @Override
    protected void initOther() {
        super.initOther();
        setBackColor(Color.TRANSPARENT);
        mCustomNumbersView = new CustomNumbersView(getContext());
        mCustomNumbersView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .WRAP_CONTENT));
        tagByMonthMap = MapUtils.getDefMap(true);
        mCustomNumbersView.setPadding(0,0,0,100);
        mPresenter.getTotalTime(ApiKey.PRACTICE_GET_TOTAL, defMap);
    }

    Map<String, String> defMap;

    //创建具体莫一天做笔记的适配器
    @Override
    public CommonRecycleViewAdapter getAdapter() {
        defMap = MapUtils.getDefMap(true);
        return new PracticeListAdapter(getContext());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        PracticeListBean.DataBean data = EmptyUtils.parseObject(o);
        if (EmptyUtils.isNotEmpty(data.getAlbumId())){
            AlbumDetailActivity.startAction(getContext(),data.getAlbumId());
        }
    }

    @Override
    protected void loadData() {

        defMap.put("page", String.valueOf(page));
        mPresenter.getCourseList(ApiKey.PRACTICE_LIST_PRA, defMap);
    }


    @Override
    public void showTotalTime(RecordTotalBean.DataBean result) {
        mCustomNumbersView.setDatas(result.getDays(), result.getTimes(), result.getAmounts(), true);
        iRecyclerView.setHeadView(mCustomNumbersView);
    }

    @Override
    public void showCourseList(List<PracticeListBean.DataBean> result) {

        updateData(result);
    }
}
