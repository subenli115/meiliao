package com.ziran.meiliao.ui.workshops.fragment;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CrowdFundingDetailBean;
import com.ziran.meiliao.ui.workshops.adapter.CrowdFundingUsedTemplateAdapter;
import com.ziran.meiliao.ui.bean.CrowdFundingPreviewBean;
import com.ziran.meiliao.ui.bean.CrowdFundingPreviewDetailBean;
import com.ziran.meiliao.ui.workshops.util.CrowFundingPreviewUtil;
import com.ziran.meiliao.ui.workshops.util.QJGDataUtil;

import java.util.List;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CrowdFundingPreviewFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<CrowdFundingDetailBean> {
    CrowFundingPreviewUtil mCrowFundingPreviewUtil;


    List<CrowdFundingPreviewBean> templateData;

    @Override
    protected void initOther() {
        mCrowFundingPreviewUtil = new CrowFundingPreviewUtil(getContext());
        mCrowFundingPreviewUtil.init();
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false);
        return new CrowdFundingUsedTemplateAdapter(getContext(), true);
    }

    @Override
    protected void loadData() {
        templateData = QJGDataUtil.getTemplateData();
        CrowdFundingPreviewDetailBean data = QJGDataUtil.setTemplateHeadAndFooter();
        mCrowFundingPreviewUtil.setHeadData(data.getPicture(), data.getTitle(), data.getStartTime(), data.getEndTime(), data.getAddress()
                , data.getSupportMembers(), data.getLeftTime(), data.getTargetMembers());
        mCrowFundingPreviewUtil.setFooterData(data.getAvatarUrl(), data.getName(), data.getProfile());
        mCrowFundingPreviewUtil.bindTarget(iRecyclerView);
        updateData(templateData);

    }

    @Override
    public void returnData(CrowdFundingDetailBean result) {

    }

}
