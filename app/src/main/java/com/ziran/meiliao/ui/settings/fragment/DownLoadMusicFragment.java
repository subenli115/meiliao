package com.ziran.meiliao.ui.settings.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.service.ServiceManager;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.settings.activity.DownloadActivity;
import com.ziran.meiliao.ui.settings.activity.DownloadAlbumActivity;
import com.ziran.meiliao.ui.settings.adapter.DownloadMusicAdapter;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MusicPanelFloatManager;
import com.ziran.meiliao.utils.MyValueTempCache;

import org.jaudiotagger.audio.mp3.MP3File;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的下载 音乐Fragment
 * Created by Administrator on 2017/1/7.
 */

public class DownLoadMusicFragment extends DeleteRefreshFragment {
    private int fromType = OneSlideAdapter.FROM_MUSIC;
    private String albumId;
    private List<MusicEntry> musicEntries;

    @Override
    protected void loadData() {
         albumId = getIntentExtra(AppConstant.SPKey.ALBUM_ID);
        List<MusicEntry> newList = new ArrayList<>();
        if (EmptyUtils.isNotEmpty(albumId)) {
            musicEntries = DbCore.getDaoSession().getMusicEntryDao().queryRaw("where ALBUM_ID = ?",
                    new String[]{albumId});
            getExists(musicEntries, newList,false);
            updateData(changeData(newList));
        } else {
             musicEntries = DbCore.getDaoSession().getMusicEntryDao().loadAll();
            getExists(musicEntries, newList,true);
            updateData(changeData(newList));
        }
    }

    private void getExists(List<MusicEntry> musicEntries, List<MusicEntry> newList,boolean iszl) {
        for(int i=0;i<musicEntries.size();i++){
            if(musicEntries.get(i).getType()==1&&iszl){
                newList.add(musicEntries.get(i));
            }else if(musicEntries.get(i).getType()==2&&!iszl){
                newList.add(musicEntries.get(i));
            }
        }
    }

    private List<MusicEntry> changeData(List<MusicEntry> musicEntries) {
        if (EmptyUtils.isEmpty(musicEntries)) {
//            FileUtil.delete(FileUtil.getDownMusicFolder());
            return null;
        }
        return musicEntries;
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        if (canNotStartAct) return;
        MusicEntry audioDetailListBean = (MusicEntry) o;
        if(FileUtil.fileIsExists(audioDetailListBean.getFilePathVideo())){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String path =audioDetailListBean.getFilePathVideo();//该路径可以自定义
            File file = new File(path);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "video/*");
            startActivity(intent);
        }else if(FileUtil.fileIsExists(audioDetailListBean.getFilePath())||FileUtil.fileIsExists(audioDetailListBean.getFilePathZl())){
            MyValueTempCache.setCurrentPlayMusicEntry(audioDetailListBean);
            MyAPP.mServiceManager.setAlbumPicture(audioDetailListBean.getSharePic());
            MyAPP.mServiceManager.setAlbumName(audioDetailListBean.getName());
            MyAPP.mServiceManager.setClickFrom(ServiceManager.CLICK_FROM_DOWNLOAD);
            MyAPP.mServiceManager.setAlbumId(audioDetailListBean.getAlbumId());
            MusicPanelFloatManager.getInstance().setData(audioDetailListBean.getName(),audioDetailListBean.getDuration(),audioDetailListBean.getSharePic(),audioDetailListBean.getFilePathZl(),audioDetailListBean.getMusicId()+"",false);
            if (EmptyUtils.isNotEmpty(albumId)) {
                MyAPP.mServiceManager.playZlUrl(audioDetailListBean.getFilePath());
                DownloadAlbumActivity activity = (DownloadAlbumActivity) getActivity();
                activity.setVisit(audioDetailListBean.getName(),audioDetailListBean.getDuration(),audioDetailListBean.getSharePic(),audioDetailListBean);
            }else{
                MyAPP.mServiceManager.playZlUrl(audioDetailListBean.getFilePathZl());
                DownloadActivity activity = (DownloadActivity) getActivity();
                MP3File f = null;
//                try {
//                    f = (MP3File) AudioFileIO.read(new File(audioDetailListBean.getFilePathZl()));
//                } catch (CannotReadException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (TagException e) {
//                    e.printStackTrace();
//                } catch (ReadOnlyFileException e) {
//                    e.printStackTrace();
//                } catch (InvalidAudioFrameException e) {
//                    e.printStackTrace();
//                }
//                MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
//                System.out.println(getTimeStrBySecond(audioHeader.getTrackLength()));
                activity.setVisit(audioDetailListBean.getName(),audioDetailListBean.getDuration(),audioDetailListBean.getSharePic(),audioDetailListBean);
            }
        }else{
            ToastUitl.showShort("下载中或下载失败请重新下载");
            return;
        }

        try {
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 一小时的秒数
     */
    private static final int HOUR_SECOND = 60 * 60;

    /**
     * 一分钟的秒数
     */
    private static final int MINUTE_SECOND = 60;

    /**
     * 根据秒数获取时间串
     * @param second (eg: 100s)
     * @return (eg: 00:01:40)
     */
    public static String getTimeStrBySecond(int second) {
        if (second <= 0) {

            return "00:00:00";
        }

        StringBuilder sb = new StringBuilder();
        int hours = second / HOUR_SECOND;
        if (hours > 0) {

            second -= hours * HOUR_SECOND;
        }

        int minutes = second / MINUTE_SECOND;
        if (minutes > 0) {

            second -= minutes * MINUTE_SECOND;
        }

        return (hours >= 10 ? (hours + "")
                : ("0" + hours) + ":" + (minutes >= 10 ? (minutes + "") : ("0" + minutes)) + ":"
                + (second >= 10 ? (second + "") : ("0" + second)));
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false, true);
        needCheckNet = false;
        Bundle arguments = getArguments();
        if (arguments != null) {
            fromType = arguments.getInt(AppConstant.ExtraKey.FROM_TYPE);
        }
        loadedTip.setEmptyMsg(getString(R.string.emtry_download_music), R.mipmap.ic_empty_download);
        if (fromType ==  OneSlideAdapter.FROM_MUSIC){
            mRxManager.on(AppConstant.RXTag.DELETE_UPDATE, new rx.functions.Action1<Integer>() {
                @Override
                public void call(Integer fromType) {
                    if (fromType == OneSlideAdapter.FROM_UPDATE_MUSIC){
                        onRefresh();
                    }
                }
            });
        }
        return new DownloadMusicAdapter(getContext(), R.layout.item_me_music_collect_or_download, fromType);
    }

    @Override
    protected void deleteItem(Integer fromType) {
        boolean empty = mAdapter.getItemCount() == 0;
        switch (fromType) {
            case OneSlideAdapter.FROM_MUSIC:
                if (empty) {
                    showEmtry();
                }
                break;
            case OneSlideAdapter.FROM_ALBUM_MUSIC:
                if (empty) {
                    HandlerUtil.runMain(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },300);
                }
                RxManagerUtil.post(AppConstant.RXTag.DELETE_UPDATE, OneSlideAdapter.FROM_UPDATE_MUSIC);
                break;
            case OneSlideAdapter.FROM_ALBUM:
                onRefresh();
                break;
        }
    }
}
