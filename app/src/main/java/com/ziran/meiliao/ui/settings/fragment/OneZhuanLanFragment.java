package com.ziran.meiliao.ui.settings.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.MyFollowBean;
import com.ziran.meiliao.ui.settings.adapter.ZhuanLanTwoAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.List;
import java.util.Map;

import rx.functions.Action1;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_USERFOLLOW_PAGEBYUSERID;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_USERFOLLOW_PAGEUSERTYPE;

/**
 * Created by Administrator on 2019/1/31.
 */
public class OneZhuanLanFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract

        .View<MyFollowBean> {
    private String userId;
    private ZhuanLanTwoAdapter zhuanLanTwoAdapter;
    private String position;

    @Override
    public void returnData(MyFollowBean result) {
        updateData(result.getData().getRecords());
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        iRecyclerView.setLoadMoreEnabled(true);
        iRecyclerView.setRefreshEnabled(true);
         zhuanLanTwoAdapter = new ZhuanLanTwoAdapter(getContext(), null,new ZhuanLanTwoAdapter.ActivityMultiItemType() {
        },position,getBg());
        return zhuanLanTwoAdapter;
    }




    @Override
    protected void initView() {
        position = getArguments().getString("position");
        super.initView();

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
            if(position.equals("0")){
                map.put("userId", MyAPP.getUserId());
                map.put("current",page+"");
                mPresenter.getData(ADMIN_USERFOLLOW_PAGEUSERTYPE,map,MyFollowBean.class);
            }else if(position.equals("1")){
                map.put("followUserId", MyAPP.getUserId());
                map.put("userId", MyAPP.getUserId());
                map.put("current",page+"");
                mPresenter.getData(ADMIN_USERFOLLOW_PAGEBYUSERID,map,MyFollowBean.class);
            }else {
                map.put("followUserId", MyAPP.getUserId());
                map.put("current",page+"");
                mPresenter.getData(ADMIN_USERFOLLOW_PAGEUSERTYPE,map,MyFollowBean.class);
            }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
