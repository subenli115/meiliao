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
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.settings.adapter.BuyAlbumAdapter;
import com.ziran.meiliao.ui.bean.MeAlbumBean;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

/**
 * 购买专辑Fragment
 * Created by Administrator on 2017/1/16.
 */

public class BuyAlbumFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<MeAlbumBean> {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(getString(R.string.emtry_album), R.mipmap.ic_empty_course);
        return new BuyAlbumAdapter(getContext(), R.layout.item_me_course);
    }

    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.BUY_ALBUM_LIST, MapUtils.getOnlyPage(page), MeAlbumBean.class);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(),getView())) return;
        AlbumBean dataBean = (AlbumBean) o;
        if (EmptyUtils.isNotEmpty(dataBean)) {
            AlbumDetailActivity.startAction(getContext(), String .valueOf(dataBean.getId()));
        }
    }

    @Override
    public void returnData(MeAlbumBean result) {
        updateData(result.getData());
    }
}
