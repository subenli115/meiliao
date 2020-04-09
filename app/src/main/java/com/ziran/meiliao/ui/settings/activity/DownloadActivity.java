package com.ziran.meiliao.ui.settings.activity;

import android.support.v4.app.Fragment;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.settings.fragment.DownLoadAlbumFragment;
import com.ziran.meiliao.ui.settings.fragment.DownLoadMusicFragment;
import com.ziran.meiliao.ui.settings.fragment.DownLoadVideoFragment;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.widget.CustomMusicPanelNewView;

import java.util.List;

import butterknife.Bind;

/**
 * 已下载界面
 * Created by Administrator on 2017/1/4.
 */

public class DownloadActivity extends CommonDeleteActivity {
    @Bind(R.id.musicPanelView)
    CustomMusicPanelNewView mMusicPanelView;

    @Override
    public void initView() {
        super.initView();
        ntb.setTitleText(getString(R.string.download_history));
        tips = "删除";
    }

    @Override
    protected void initFragments(List<Fragment> fragments) {
        fragments.add(new DownLoadMusicFragment());
        fragments.add(new DownLoadAlbumFragment());
        fragments.add(new DownLoadVideoFragment());
    }
    @Override
    protected void onResume() {
        super.onResume();
        MusicPanelFloatManager.getInstance().bindView(mMusicPanelView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPanelFloatManager.getInstance().unBindView(mMusicPanelView);
    }

    @Override
    public int getTabsArrayId() {
        return R.array.download_tabs;
    }
    public void setVisit(String name, String duration, String url, MusicEntry musicEntry) {
        mMusicPanelView.setTvMusicPanelTitle(name);
        mMusicPanelView.setTvMusicPanelDuration(duration);
        mMusicPanelView.setIvMusicPanelCover(url);
        mMusicPanelView.setVisibility(View.VISIBLE);
        mMusicPanelView.updatePlayState();
        MusicPanelFloatManager.getInstance().setMusicPrepare(musicEntry);
        MusicPanelFloatManager.getInstance().bindView(mMusicPanelView);
    }

}
