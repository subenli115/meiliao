package com.ziran.meiliao.ui.workshops.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.SimpleTextWatcher;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CrowdFundingModuleBean;
import com.ziran.meiliao.ui.workshops.adapter.CrowdFundingUsedTemplateAdapter;
import com.ziran.meiliao.ui.bean.CrowdFundingPreviewBean;
import com.ziran.meiliao.ui.workshops.util.QJGDataUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CrowdFundingUsedTemplateFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<CrowdFundingModuleBean>, AppConstant.SearchId {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    private String courseId = "1";
    private String teacherId = "10";


    @Override
    protected void initBundle(Bundle extras) {
        courseId = extras.getString("courseId");
        teacherId = extras.getString("teacherId");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_crowd_funding_used_template;
    }


    @Override
    protected void initOther() {
        ntb.setLeftImagSrc(R.mipmap.ic_close_black);
        ntb.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QJGDataUtil.clearTemplateData();
                onRefreshBy(false);
                ntb.setRightTitleVisibility(false);
                contentChange = false;
            }
        });
        ntb.setRightTitleVisibility(false);
        setRecyclerEnabled(false);
    }

    private CrowdFundingUsedTemplateAdapter templateAdapter;

    @Override
    protected void initView() {
        super.initView();
        mRxManager.on("image", new Action1<Message>() {
            @Override
            public void call(Message msg) {
                if (msg.what == WHAT_FROM_TYPE_CROWD_FUNDING_USED_INPUT_MSG && msg.obj != null) {
                    addItem(CrowdFundingUsedTemplateAdapter.TYPE_IMAGE, ((List) msg.obj).get(0).toString());
                }
            }
        });
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        templateAdapter = new CrowdFundingUsedTemplateAdapter(getContext(), false);
        templateAdapter.setSimpleTextWatcher(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!contentChange) {
                    ntb.setRightTitleVisibility(true);
                    contentChange = true;
                }
            }
        });
        return templateAdapter;
    }

    private boolean contentChange;


    @Override
    protected void loadData() {
        if (EmptyUtils.isNotEmpty(QJGDataUtil.getTemplateData())) {
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    updateData(QJGDataUtil.getTemplateData());
                }
            }, 200);
        } else {
            Map<String, String> defMap = MapUtils.getDefMap(true);
            defMap.put("courseId", courseId);
            defMap.put("teacherId", teacherId);
            mPresenter.postData(ApiKey.CROWN_FUND_GET_CROWD_FUND_MODULE, defMap, CrowdFundingModuleBean.class);
        }
    }

    @Override
    public void returnData(CrowdFundingModuleBean result) {
        List<CrowdFundingPreviewBean> data = result.getData();
        Collections.sort(data, new Comparator<CrowdFundingPreviewBean>() {
            @Override
            public int compare(CrowdFundingPreviewBean o1, CrowdFundingPreviewBean o2) {
                return o1.getIndex() - o2.getIndex();
            }
        });
        updateData(data);
    }


    @OnClick({R.id.tv_item_crowd_funding_used_template_add_text, R.id.tv_item_crowd_funding_used_template_add_pic, R.id
            .tv_item_crowd_funding_used_template_used})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_item_crowd_funding_used_template_add_text:
                addItem(CrowdFundingUsedTemplateAdapter.TYPE_THREE, "");
                break;
            case R.id.tv_item_crowd_funding_used_template_add_pic:
                ImgSelActivity.startActivity(this, ImgSelConfig.DEFAULT_SIGN, ImgSelConfig.RequestCode,
                        WHAT_FROM_TYPE_CROWD_FUNDING_USED_INPUT_MSG);
                break;
            case R.id.tv_item_crowd_funding_used_template_used:
                RxManagerUtil.post(AppConstant.RXTag.CROWD_FUNDING_CHOOSE_DATA, HandlerUtil.obj(666, true));
                KeyBordUtil.closeKeyboard(getActivity());
                QJGDataUtil.setTemplateData(templateAdapter.getAll());
                finish();
                break;
        }
    }

    private void addItem(int type, String img) {
        int lastIndex = templateAdapter.get(templateAdapter.getSize() - 1).getIndex();
        CrowdFundingPreviewBean crowdFundingPreviewBean = new CrowdFundingPreviewBean(type, lastIndex + 1, "默认标题", "默认内容");
        crowdFundingPreviewBean.setPicture(img);
        templateAdapter.add(crowdFundingPreviewBean);
        iRecyclerView.setAdapter(templateAdapter);
        iRecyclerView.scrollToPosition(templateAdapter.getLastPosition());
    }
}
