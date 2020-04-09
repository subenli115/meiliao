package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.CachePageEntry;
import com.ziran.meiliao.envet.MyOnScrollListener;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CategoryBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.decompressionmuseum.adapter.CategoryDetailAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;

/**
 * 导聆-分类-内容Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CategoryDetailFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract
        .View<CategoryBean> {

    //栏目ID
    private String columnId;
    //入口类型
    private int fromType;

    public static CategoryDetailFragment newInstance(String tag, String columnId) {
        CategoryDetailFragment fragment = new CategoryDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("fragmentTag", tag);
        bundle.putString(AppConstant.ExtraKey.COLUMN_ID, columnId);
        fragment.setArguments(bundle);
        return fragment;
    }

    //设置为网格布局管理器
    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            columnId = bundle.getString(AppConstant.ExtraKey.COLUMN_ID);
            fromType = getActivity().getIntent().getIntExtra(AppConstant.SPKey.ALBUM_FLAG, 0);
        } else {
            columnId = "1";
        }
        loadedTip.setEmptyMsg(getString(R.string.not_body_plese), R.mipmap.ic_empty_category);
        int offset = getResources().getDimensionPixelOffset(R.dimen.contorller_child_height);
        iRecyclerView.setPadding(offset, 0, 0, 0);
        iRecyclerView.addOnScrollListener(new MyOnScrollListener(AppConstant.RXTag.HOME_MUSIC_PLANE_SHOW_OR_HIDE));
        return new CategoryDetailAdapter(getContext(), R.layout.item_main_jyg_category_detail);
    }

    /**
     * 请求数据
     */
    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.GET_LIST_BY_COLUMN, MapUtils.getCategoryData(columnId, page), CategoryBean.class);
    }

    //    点击item监听
    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(), getView())) return;
        CategoryBean.DataBean dataBean = (CategoryBean.DataBean) o;
        if (dataBean != null) {
            AlbumDetailActivity.startAction(getActivity(), String.valueOf(dataBean.getAlbumId()), fromType);
        }
    }

    //没有网络状态处理
    @Override
    protected boolean noWifiState() {
        final CategoryBean result = CachePageEntry.loadData(AppConstant.CACHE_PAGE_MAIN_JYG_CATEGORY_DETAIL + Long.parseLong(columnId),
                CategoryBean.class);
        if (EmptyUtils.isNotEmpty(result)) {
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    updateData(result.getData());
                }
            }, 80);
            return true;
        }
        return super.noWifiState();
    }

    //显示数据
    @Override
    public void returnData(CategoryBean result) {
        CachePageEntry.insertData(AppConstant.CACHE_PAGE_MAIN_JYG_CATEGORY_DETAIL + Long.parseLong(columnId), result, page);
        updateData(result.getData());
    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible  ) {
            MusicPanelFloatManager.getInstance().setIsShowingAnimation(true);
            MusicPanelFloatManager.getInstance().updatePlayState();
        }
    }
}
