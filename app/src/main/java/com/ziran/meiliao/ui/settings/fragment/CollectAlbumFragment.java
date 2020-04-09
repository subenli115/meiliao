package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.R;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.MeAlbumBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.settings.adapter.CollectAlbumAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 收藏专辑Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CollectAlbumFragment extends DeleteRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<MeAlbumBean> {


    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.COLLECT_ALBUM_LIST, MapUtils.getOnlyPage(page), MeAlbumBean.class);
    }

    @Override
    public void returnData(MeAlbumBean result) {
        updateData(result.getData());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        if (canNotStartAct) return;
        if (!CheckUtil.check(getContext(), getView())) return;
        AlbumBean dataBean = (AlbumBean) o;
        if (EmptyUtils.isNotEmpty(dataBean)) {
            AlbumDetailActivity.startAction(getContext(), dataBean.getRealAlbumId());
        }
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(getString(R.string.emtry_collect_course), R.mipmap.ic_empty_collect);
        return new CollectAlbumAdapter(getContext(), R.layout.item_me_collect_album);
    }

    @Override
    protected void deleteItem(Integer fromType) {
        switch (fromType) {
            case 1:
                if (mAdapter.getItemCount() == 0) {
                    showEmtry();
                }
        }
    }
}
