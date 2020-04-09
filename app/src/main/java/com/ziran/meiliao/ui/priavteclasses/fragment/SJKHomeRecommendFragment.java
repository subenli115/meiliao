package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.MyOnScrollListener;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.SJKHomeRecBean;
import com.ziran.meiliao.ui.bean.SpecColumnData;
import com.ziran.meiliao.ui.bean.ZhiBoData;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangMoreActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.NowLiveMoreActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.ZhuanLanMoreActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.SJKRecommendAdapter;
import com.ziran.meiliao.ui.priavteclasses.util.SJKLiveUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rx.functions.Action1;


/**
 * des:私家课Fragment
 * Created by xsf
 * on 2016.09.17:07
 */
public class SJKHomeRecommendFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<SJKHomeRecBean> {


    @Override
    protected void initView() {
        super.initView();
        //设置标题栏
        mRxManager.on(AppConstant.RXTag.CATEGORY_MORE_CLICK, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case HeadData.Type.ZHIBO:
                        NowLiveMoreActivity.startAction(getContext());
                        break;
                    case HeadData.Type.ZHUANLAN:
                        ZhuanLanMoreActivity.startAction(getContext());
                        break;
                    case HeadData.Type.GONGZUOFANG:
                        startActivity(GongZuoFangMoreActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false, true);
        iRecyclerView.addOnScrollListener(new MyOnScrollListener(AppConstant.RXTag.HOME_MUSIC_PLANE_SHOW_OR_HIDE));
        return new SJKRecommendAdapter(getContext());
    }

    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.SJK_GET_REC_DATA, MapUtils.getOnlyPage(page), SJKHomeRecBean.class);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        int itemViewType = mAdapter.getItemViewType(position);
        switch (itemViewType) {
            case HeadData.Type.ZHUANLAN:
                SpecColumnData scd = EmptyUtils.parseObject(o);
                if (scd.getType() == -1) {
//                    ZhuanLanDetailActivity.startAction(getContext(), o);
                } else if (scd.getType() == 1) {
//                    ZhuanLanBigInVideoActivity.startAction(getContext(), scd.getTargetId());
                } else if (scd.getType() == 2) {
//                    ZhuanLanBigInAudioActivity.startAction(getContext(), scd.getTargetId());
                }
                break;
            case HeadData.Type.ZHIBO:
                ZhiBoData zhiBoData = EmptyUtils.parseObject(o);
                SJKLiveUtil.startActivity(getContext(), String.valueOf(zhiBoData.getId()), zhiBoData.getTag(), zhiBoData.getStatusX());
                break;
            case SJKRecommendAdapter.TYPE_GZF_LEFT:
            case SJKRecommendAdapter.TYPE_GZF_TOP:
                ActisData dataBean = EmptyUtils.parseObject(o);
                if (dataBean != null) {
                    dataBean.setPosition(position);
                    GongZuoFangActivity.startAction(getContext(), dataBean);
                }
                break;
        }
    }

    @Override
    public void returnData(SJKHomeRecBean result) {
        SJKHomeRecBean.DataBean resultData = result.getData();
        List list = new ArrayList();
        if (EmptyUtils.isNotEmpty(resultData.getBigBrand())) {
            list.add(HeadData.create(HeadData.Type.DAPAI, "大牌进驻", true, false));
            list.addAll(addFlag(resultData.getBigBrand())); //大牌进驻
        }
        if (EmptyUtils.isNotEmpty(resultData.getZhibo())) {
            list.add(HeadData.create(HeadData.Type.ZHIBO, "精彩直播", true));
            list.addAll(resultData.getZhibo()); //精彩直播
        }
        if (EmptyUtils.isNotEmpty(resultData.getSubscription())) {
            list.add(HeadData.create(HeadData.Type.ZHUANLAN, "专栏订阅", true));
            list.addAll(resultData.getSubscription()); //专栏订阅
        }
        if (EmptyUtils.isNotEmpty(resultData.getActis())) {
            list.add(HeadData.create(HeadData.Type.GONGZUOFANG, "工作坊", true));
            list.addAll(resultData.getActis()); //工作坊
        }
        updateData(list);
    }

    private Collection addFlag(List<SpecColumnData> bigBrand) {
        return bigBrand;
    }

}
