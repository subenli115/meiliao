package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.os.Bundle;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.priavteclasses.adapter.ZhuanLanTwoAdapter;
import com.ziran.meiliao.ui.priavteclasses.bean.NewZhuanLanData;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import rx.functions.Action1;

import static com.ziran.meiliao.constant.ApiKey.SUBSCRIPTION_SUBSCRIPTIONLIST;

/**
 * Created by Administrator on 2019/1/31.
 */
public class OneZhuanLanFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract

        .View<NewZhuanLanData> {
    private String tagId;
    private ZhuanLanTwoAdapter zhuanLanTwoAdapter;
    private NewZhuanLanData mResult;


    @Override
    public void returnData(NewZhuanLanData result) {
        mResult=result;
        updateData(result.getData());
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        iRecyclerView.setLoadMoreEnabled(false);
        iRecyclerView.setRefreshEnabled(false);
         zhuanLanTwoAdapter = new ZhuanLanTwoAdapter(getContext(), null,new ZhuanLanTwoAdapter.ActivityMultiItemType() {
        });

        return zhuanLanTwoAdapter;
    }
    @Override
    protected void initView() {
        super.initView();
        tagId = getArguments().getString("tagId");
        mRxManager.on(AppConstant.RXTag.ZL_BUY_SUCESS, new Action1<Boolean>() {
            @Override
            public void call(Boolean scroll) {
                if(scroll){
                 onRefresh();
                }
            }
        });

    }

    @Override
    protected void initBundle(Bundle extras) {
        try {
            int anInt = extras.getInt(AppConstant.ExtraKey.FROM_TYPE);
            if(anInt==10){
                loadData();
            }

        } catch (Exception e) {
        }
    }



    @Override
    protected void loadData() {
            Map<String, String> map = MapUtils.getDefMap(true);
            map.put("tagId",tagId);
            map.put("page",page+"");
            mPresenter.postData(SUBSCRIPTION_SUBSCRIPTIONLIST,map,NewZhuanLanData.class);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
