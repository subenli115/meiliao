package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ziran.meiliao.R;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.SettingUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.receiver.NetUtil;
import com.ziran.meiliao.entry.CachePageEntry;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.JYGColumnBean;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CategoryFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<JYGColumnBean> {

    @Bind(R.id.tab_layout)
    public TabLayout tabLayout;
    @Bind(R.id.viewpager)
    public ViewPager viewPager;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_jyg_category;
    }

    @Override
    protected void initView() {
        super.initView();
        //网络状态监听
        mRxManager.on(AppConstant.RXTag.NETWORK_CHANGE_STATE, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                switch (integer) {
                    case NetUtil.NETWORK_NONE:
                        if (!isLoadingData) {
                            loadedTip.setLoadingTip(LoadingTip.LoadStatus.noWifi);
                        }
                        break;
                    case NetUtil.NETWORK_MOBILE:
                    case NetUtil.NETWORK_WIFI:
                        if (!isLoadingData) {
                            loadedTip.setOperateShown();
                            doLoading();
                        }
                        break;
                }
            }
        });
    }

    //没有网络时显示缓存数据
    @Override
    protected boolean noWifiState() {
        final JYGColumnBean result = CachePageEntry.loadData(AppConstant.CACHE_PAGE_MAIN_JYG_CATEGORY, JYGColumnBean.class);
        if (EmptyUtils.isNotEmpty(result)) {
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    setResult(result);
                }
            },80);
            return true;
        }
        return super.noWifiState();
    }
    //重新加载
    protected void doLoading() {
        if (isLoadingData) return;
        mPresenter.getData(ApiKey.GET_ALBUM_COLUMN, MapUtils.getDefMap(false), JYGColumnBean.class);
    }

    @Override
    public void returnData(JYGColumnBean result) {
        CachePageEntry.insertData(AppConstant.CACHE_PAGE_MAIN_JYG_CATEGORY, result);
        setResult(result);
    }
    //显示数据
    private void setResult(JYGColumnBean result) {
        List<Fragment> fragments = new ArrayList<>();
        List<JYGColumnBean.DataBean> resultData = result.getData();
        if (EmptyUtils.isNotEmpty(resultData)) {
            String[] tabs = new String[resultData.size()];
            for (int i = 0; i < resultData.size(); i++) {
                tabs[i] = resultData.get(i).getName();
                fragments.add(CategoryDetailFragment.newInstance(resultData.get(i).getName(), resultData.get(i).getColumnId()));
            }
            viewPager.setAdapter(new BaseFragmentAdapter(getChildFragmentManager(), fragments));
            tabLayout.setupWithViewPager(viewPager);
            ViewUtil.changeTabText(tabLayout, tabs);
            isLoadingData = true;
        }
    }

    @Override
    public void reload() {
        SettingUtil.startSettings(getContext());
    }
}
