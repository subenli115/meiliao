package com.ziran.meiliao.utils;

import android.content.Context;

import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.Luban;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.AlbumEntry;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.settings.widget.DonutProgress;

import java.io.File;

/**
 * Created by Administrator on 2017/2/28.
 */

public class MlDownloadUtil {

    private static MlDownloadUtil INSTANCE;
    private File file;

    private MlDownloadUtil() {
        Luban luban;
    }

    public static MlDownloadUtil get() {
        if (INSTANCE == null) {
            synchronized (MlDownloadUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MlDownloadUtil();
                }
            }
        }
        return INSTANCE;
    }


    public void downMusic(Context mcontext, onDownloadListener downloadListener, String albumId, MusicEntry musicEntry, Boolean isVideo, Boolean isZl, DonutProgress... views) {
        if(!MeiliaoConfig.isDowning){
            ToastUitl.showShort("正在下载中..");
            return;
        }

        String url = musicEntry.getAESUrl();
                if(isZl){
                    musicEntry.setType(1);
                     file = new File(musicEntry.getFilePathZl());
                }else {
                    musicEntry.setType(2);
                     file = new File(musicEntry.getFilePath());
                }
        if(isVideo){
            musicEntry.setType(1);
             file = new File(musicEntry.getFilePathVideo());
        }

        if (file.exists()) {
                ToastUitl.showShort("已下载" + musicEntry.getName());
            return;
        }else {
            if(DownloadUtil.getInstance().isIsExqWork()){
                ToastUitl.showShort("有任务正在下载");
                return;
            }
            ToastUitl.showShort("下载开始");
        }
        if (downloadListener != null) {
            downloadListener.onDownStart(url);
        }
        DownloadUtil.getInstance().singleDownloadFile(url, file.getParent()+"/",file.getName(),downloadListener);
        //添加下载进度控件
        DownloadUtil.getInstance().addProgressViews(url, views);
        //执行保存专辑下载到数据库
        final AlbumBean albumBean = MyValueTempCache.get().get(AppConstant.DOWN_ALBUM);
        if (albumBean != null) {
            AlbumEntry.insert(albumBean.getTitle(), albumBean.getIntro(), albumBean.getName(), albumBean.getAlbumId(), albumBean
                    .getAlbumImage(), albumBean.isBuy(), albumBean.getDescript());
        }
        //执行保存音频下载到数据库'
        if(albumBean!=null&&albumBean.getAlbumId()!=null){
            musicEntry.setAlbumId(albumBean.getAlbumId());
        }
            musicEntry.setPath(file.getPath());
            MusicEntry.insert(albumId, musicEntry);

    }


    public interface onDownloadListener {
        void onFinish(String url, File file);

        void onDownStart(String url);
    }

    private String[] urls = {"http://flv2.bn.netease.com/tvmrepo/2017/3/T/T/ECE8715TT/SD/ECE8715TT-mobile.mp4", "http://flv2.bn.netease"
            + ".com/tvmrepo/2017/3/C/Q/ECE87HLCQ/SD/ECE87HLCQ-mobile.mp4", "http://flv2.bn.netease" + "" +
            ".com/videolib3/1703/10/NOHSv6665/SD/NOHSv6665-mobile.mp4", "http://flv2.bn.netease" + "" +
            ".com/videolib3/1702/17/XTzKs4207/SD/XTzKs4207-mobile.mp4", "http://flv2.bn.netease" + "" +
            ".com/videolib3/1702/17/lBkZD4054/SD/lBkZD4054-mobile.mp4", "http://flv2.bn.netease" + "" +
            ".com/videolib3/1702/17/PqkQl4630/SD/PqkQl4630-mobile.mp4", "http://flv2.bn.netease" + "" +
            ".com/tvmrepo/2017/2/7/C/ECCILI77C/SD/ECCILI77C-mobile.mp4", "http://flv2.bn.netease" + "" +
            ".com/tvmrepo/2017/2/K/L/ECCG22NKL/SD/ECCG22NKL-mobile.mp4"};

//    public void downVideo(final onDownloadListener downloadListener, final VideoSectionEntry videoSectionEntry, String url, final View...
//            views) {
//        if (EmptyUtils.isEmpty(url)) {
////            url = urls[currentPosition];
////            currentPosition++;
////            if (currentPosition == url.length()) {
////                currentPosition = 0;
////            }
//            ToastUitl.showShort("下载链接失效,暂时无法下载");
//            return;
//        }
//        File file = new File(videoSectionEntry.getFilePath());
//        if (file.exists()) {
//            ToastUitl.showShort(StringUtils.getText(R.string.is_download_video) + videoSectionEntry.getName());
//            if (downloadListener != null) {
//                downloadListener.onFinish(url, file);
//            }
//            return;
//        }
//        int videoId = IdUtil.getVideoId(videoSectionEntry.getCourseId());
//        DownloadViewHolder.get().addNotify(videoId);
//        Map<String, Object> stringMap = new HashMap<>();
//        stringMap.put(AppConstant.KEY_DOWN_TYPE, "2");
//        MyValueTempCache.get().put(url, videoSectionEntry);
//        DownloadService.getBinder(MyAPP.getContext()).startDownload(stringMap, FileUtil.getDownVideoFolder(), videoSectionEntry.getName
//                () + ".mp4", url, videoId, downloadListener);
//        if (EmptyUtils.isNotEmpty(views)) {
//            for (int i = 0; i < views.length; i++) {
//                DownloadViewHolder.get().put(videoId, views[i]);
//            }
//        }
//    }
}
