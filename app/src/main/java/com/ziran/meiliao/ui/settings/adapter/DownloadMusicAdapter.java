package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.AlbumEntry;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.ui.settings.widget.DonutProgress;
import com.ziran.meiliao.utils.DownloadUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2017/1/14.
 */

public class DownloadMusicAdapter extends OneSlideAdapter<MusicEntry> {


    public DownloadMusicAdapter(Context context, int layoutId) {
        this(context, layoutId, FROM_MUSIC);
    }
    public DownloadMusicAdapter(Context context, int layoutId, int type) {
        super(context, layoutId);
        from_type = type;
        deleteAlbumIds = new ArrayList<>();
    }

    @Override
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }

    @Override
    public void convertData(OneSlideViewHolder helper, MusicEntry bean, int position) {
        helper.setText(R.id.tv_item_jyg_audio_name, bean.getName());
        DonutProgress donutProgress = helper.getView(R.id.donutProgress);
        DownloadUtil.getInstance().addProgressView(bean.getAESUrl(),donutProgress);
    }

    private List<MusicEntry> deleteAlbumIds;

    @Override
    protected void deleteSlef(HashSet<MusicEntry> valueSet) {
        if(!WpyxConfig.isDowning){
            ToastUitl.showShort("正在下载中..");
            return;
        }
        if (EmptyUtils.isEmpty(valueSet) || EmptyUtils.isEmpty(deleteAlbumIds)) return;
        for (MusicEntry musicEntry : deleteAlbumIds) {
            if (MusicEntry.hasDataFormAlbumId(musicEntry.getAlbumId())) {
                AlbumEntry albumEntry = AlbumEntry.getAlbum(musicEntry.getAlbumId());
                if (EmptyUtils.isNotEmpty(albumEntry)){
                    DbCore.getDaoSession().getAlbumEntryDao().delete(albumEntry);
                }
            }
        }
        deleteAlbumIds.clear();
    }

    @Override
    protected void delete(MusicEntry musicEntry) {
        if(!WpyxConfig.isDowning){
            ToastUitl.showShort("正在下载中..");
            return;
        }
        if (EmptyUtils.isNotEmpty(musicEntry)) {

            DownloadUtil.getInstance().cancel(musicEntry.getAESUrl());
//            DownloadService.getBinder(MyAPP.getContext()).cancelDownload(musicEntry.getAESUrl());
            DbCore.getDaoSession().getMusicEntryDao().delete(musicEntry);
            if (!deleteAlbumIds.contains(musicEntry)) {
                deleteAlbumIds.add(musicEntry);
            }
            FileUtil.delete(musicEntry.getFilePath());
            FileUtil.delete(musicEntry.getFilePathVideo());
            FileUtil.delete(musicEntry.getFilePathZl());
        }
    }
}
