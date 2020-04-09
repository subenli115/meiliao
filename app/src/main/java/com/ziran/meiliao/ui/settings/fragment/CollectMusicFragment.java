package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.R;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.MeMusicBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.settings.adapter.CollectMusicAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;

/**
 * 收藏音频Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CollectMusicFragment extends DeleteRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<MeMusicBean> {
    @Override
    protected void loadData() {
        mPresenter.postData(ApiKey.COLLECT_MUSIC_LIST, MapUtils.getOnlyPage(page), MeMusicBean.class);
    }

    @Override
    public void returnData(MeMusicBean result) {
        updateData(result.getData());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        if (canNotStartAct) return;
        if (!CheckUtil.check(getContext(),getView())) return;
        MusicEntry dataBean = (MusicEntry) o;
        if (EmptyUtils.isNotEmpty(dataBean)) {
            AlbumDetailActivity.startAction(getContext(), dataBean.getAlbumId());
        }
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(StringUtils.getText(R.string.emtry_collect_course), R.mipmap.ic_empty_collect);
        return new CollectMusicAdapter(getContext(), R.layout.item_me_music_collect_or_download);
    }

    @Override
    protected void deleteItem(Integer fromType) {
        switch (fromType) {
            case 0:
                if (mAdapter.getItemCount() == 0) {
                    showEmtry();
                }
        }
    }

}
