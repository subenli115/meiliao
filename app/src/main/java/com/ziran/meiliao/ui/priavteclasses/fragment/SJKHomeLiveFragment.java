package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.WrapperAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.CachePageEntry;
import com.ziran.meiliao.envet.MyOnScrollListener;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SJKLiveBean;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;
import com.ziran.meiliao.ui.bean.TrailerBean;
import com.ziran.meiliao.ui.priavteclasses.activity.NowLiveMoreActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.TrailerMoreActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.TrailerWebActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.MainSJKLiveAdapter;
import com.ziran.meiliao.ui.priavteclasses.util.CountDownUtil;
import com.ziran.meiliao.ui.priavteclasses.util.SJKLiveUtil;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 私家课-直播Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKHomeLiveFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<SJKLiveBean> {
    private MainSJKLiveAdapter sjkLiveAdapter;


    @Override
    public void onRefresh() {
        if (sjkLiveAdapter != null) sjkLiveAdapter.onRefash();
        super.onRefresh();
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false, true);
        loadedTip.setEmptyMsg("暂无数据", R.mipmap.ic_empty_main_sjk);
        iRecyclerView.addOnScrollListener(new MyOnScrollListener(AppConstant.RXTag.HOME_MUSIC_PLANE_SHOW_OR_HIDE));
        sjkLiveAdapter = new MainSJKLiveAdapter(getContext(), new MainSJKLiveAdapter.MainSJKLiveMultiItemType());
        iRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return sjkLiveAdapter;
    }


    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.GET_ZHIBO_PAGEDATA, MapUtils.getOnlyPage(page), SJKLiveBean.class);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (o instanceof SJKSingeLiveData) {

            SJKSingeLiveData bean = (SJKSingeLiveData) o;
            int itemViewType = mAdapter.getItemViewType(position);
            switch (itemViewType) {
                case WrapperAdapter.TYPE_NOW:
                    if (!CheckUtil.check(getContext(), getView())) return;
                    SJKLiveUtil.startActivity(getContext(), bean.getId(), bean.getTag(), bean.getStatus());
                    break;
                case WrapperAdapter.TYPE_Trailer:
                    if (EmptyUtils.isNotEmpty(bean.getUrl())) {
                        TrailerWebActivity.startAction(getContext(), TrailerBean.changeData(bean), bean.getUrl());
                    }
                    break;
                case WrapperAdapter.TITLE:
                    if ("精彩直播".equals(bean.getTitle())) {
                        NowLiveMoreActivity.startAction(getContext());
                    } else if ("精彩预告".equals(bean.getTitle())) {
                        TrailerMoreActivity.startAction(getContext());
                    }
                    break;
            }
        }
    }

    @Override
    protected boolean noWifiState() {
        final SJKLiveBean result = CachePageEntry.loadData(AppConstant.CACHE_PAGE_MAIN_SJK_ZHIBO, SJKLiveBean.class);
        if (EmptyUtils.isNotEmpty(result)) {
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    setResult(result);
                }
            }, 80);
            return true;
        }
        return super.noWifiState();
    }

    @Override
    public void returnData(SJKLiveBean result) {
        CachePageEntry.insertData(AppConstant.CACHE_PAGE_MAIN_SJK_ZHIBO, result, page);
        setResult(result);
    }

    private void setResult(SJKLiveBean result) {
        updateData(SJKLiveBean.getData(result));

    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (mAdapter != null) {
            if (isVisible) {
                ((MainSJKLiveAdapter) mAdapter).updateCountTime();
            } else {
                CountDownUtil.get().cancelAllTimers();
            }
        }
    }
}
