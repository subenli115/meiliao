package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.MyOnScrollListener;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.SubTagBean;
import com.ziran.meiliao.ui.priavteclasses.activity.ZhuanLanMoreActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKHomeZhuanLanAdapter;
import com.ziran.meiliao.ui.priavteclasses.adapter.ZhuanLanTabadapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;


/**
 * des:私家课Fragment
 * Created by xsf
 * on 2016.09.17:07
 */
public class SJKHomeZhuanLanFragment extends CommonRefreshFragment<CommonPresenter,
    CommonModel> implements CommonContract.View<SubTagBean> {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;


    private static final int MAX_ZHUANLAN_COUNT = 8;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_zhuanlan;
    }


    @Override
    protected void initOther() {
        super.initOther();
        mRxManager.on(AppConstant.RXTag.SJKZHUANLAN_MORE_CLICK, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case HeadData.Type.ZHUANLAN:
                        ZhuanLanMoreActivity.startAction(getContext());
                        break;
                }
            }
        });
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false,true);
        iRecyclerView.addOnScrollListener(new MyOnScrollListener(AppConstant.RXTag.HOME_MUSIC_PLANE_SHOW_OR_HIDE));
        setEmptyMsg("暂无数据", R.mipmap.ic_empty_nocontent);
        return new SJKHomeZhuanLanAdapter(getContext());
    }

    @Override
    protected void loadData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("page",page+"");
        mPresenter.postData(ApiKey.SUBSCRIPTION_GETSUBSCRIPTIONLIST,defMap, SubTagBean.class);
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
//        ZhuanLanDetailActivity.startAction(getContext(),o);
    }

    @Override
    public void returnData(SubTagBean result) {
        List list = new ArrayList();
        List<SubTagBean.DataBean> data = result.getData();
        int size = result.getData().size();
        for(int i=0;i<size;i++){
            tabLayout.addTab(tabLayout.newTab().setText(data.get(i).getTagName()));
        }
        tabLayout.setupWithViewPager(viewPager);
        if(size>5){
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
//        if (EmptyUtils.isNotEmpty(resultData)){
//            list.add(HeadData.create(HeadData.Type.ZHUANLAN, "专栏订阅", true,true));
//            if (resultData.size()>MAX_ZHUANLAN_COUNT){
//                list.addAll(resultData.subList(0,MAX_ZHUANLAN_COUNT));
//            }else{
//                list.addAll(resultData);
//            }
//        }
        viewPager.setAdapter(new ZhuanLanTabadapter( getActivity().getSupportFragmentManager(),getContext(),data,getActivity()));


//        updateData(list);
    }
    @OnClick(R.id.tv_fm_sjk_zhuanlan_change)
    public void onViewClicked() {
        ZhuanLanMoreActivity.startAction(getContext());
    }
}
