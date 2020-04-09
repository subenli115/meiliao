package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.AlbumEntry;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.settings.activity.DownloadAlbumActivity;
import com.ziran.meiliao.ui.settings.adapter.DownloadAlbumAdapter;

import java.util.List;

/**
 * 我的下载 专辑Fragment
 * Created by Administrator on 2017/1/7.
 */

public class DownLoadAlbumFragment extends DeleteRefreshFragment {

    @Override
    protected void loadData() {
        List<AlbumEntry> albumEntries = DbCore.getDaoSession().getAlbumEntryDao().loadAll();
        updateData(albumEntries);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        if (canNotStartAct) return;
        if (o != null && o instanceof AlbumEntry) {
            AlbumEntry albumBean = (AlbumEntry) o;
            DownloadAlbumActivity.startAction(getContext(), albumBean.getTitle(), albumBean.getAlbumId());
        }
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false, true);
        needCheckNet = false;
        loadedTip.setEmptyMsg(getString(R.string.emtry_download_album), R.mipmap.ic_empty_download);
        return new DownloadAlbumAdapter(getContext(), R.layout.item_me_download_album);
    }

    @Override
    protected void deleteItem(Integer fromType) {
        switch (fromType) {
            case OneSlideAdapter.FROM_ALBUM:
                showEmptyState();
                break;
            case OneSlideAdapter.FROM_MUSIC:
            case OneSlideAdapter.FROM_ALBUM_MUSIC:
                onRefresh();
                break;
        }
    }
}
