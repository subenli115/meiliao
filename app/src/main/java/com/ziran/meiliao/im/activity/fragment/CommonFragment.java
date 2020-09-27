package com.ziran.meiliao.im.activity.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.im.activity.ReleaseWechatActivity;
import com.ziran.meiliao.im.adapter.CommonAdapter;
import com.ziran.meiliao.im.adapter.WechatParentAdapter;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CommonListBean;
import com.ziran.meiliao.ui.bean.WechatListDataBean;
import com.ziran.meiliao.ui.bean.WechatParentBean;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_USERSIGN_PAGEBYID;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_VISITOR_PAGEBYID;


/**
 * Created by Administrator on 2019/1/31.
 */
public class CommonFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract

        .View<CommonListBean> {
    private static final int REQUEST_CODE_A = 2;
    private String tagId;
    private CommonAdapter adapter;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntb;
    private String userId;
    private int type;

    @Override
    public void returnData(CommonListBean result) {
        updateData(result.getData().getRecords());
    }



    @Override
    public CommonRecycleViewAdapter getAdapter() {
        adapter = new CommonAdapter(getContext(), mRxManager,null, new CommonAdapter.ActivityMultiItemType() {
        },type);
        return adapter;
    }

    @Override
    protected void initView() {
        userId = getIntentExtra(getActivity().getIntent(), "userId");
        type = getIntentExtra(getActivity().getIntent(), "type",-1);
        super.initView();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_list;
    }

    @Override
    protected void initBundle(Bundle extras) {
        try {

        } catch (Exception e) {
        }
    }


    @Override
    protected void loadData() {
        Map<String, String> map = MapUtils.getDefMap(true);
        map.put("userId", userId);
        map.put("current", page + "");
        if(type==1){
            mPresenter.getData(ADMIN_USERSIGN_PAGEBYID, map, CommonListBean.class);
        }else {
            mPresenter.getData(ADMIN_VISITOR_PAGEBYID, map, CommonListBean.class);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}