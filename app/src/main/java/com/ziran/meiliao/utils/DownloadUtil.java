package com.ziran.meiliao.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.annotation.DownloadStatus;
import com.okhttplib.bean.DownloadFileInfo;
import com.okhttplib.callback.ProgressCallback;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.settings.widget.DonutProgress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.functions.Action1;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/7/12 18:30
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/7/12$
 * @updateDes ${TODO}
 */

public class DownloadUtil {
    private static DownloadUtil INSTANCE;
    private static final int EXQ_WORK = 1;

    private  boolean isExqWork = false;
    public static final String DOWNLOAD_FINISH = "DOWNLOAD_FINISH";
    public static boolean isPlayerActivity;
    private WpyxDownloadUtil.onDownloadListener mdownloadListener;

    public static DownloadUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (DownloadUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DownloadUtil();
                }
            }
        }
        return INSTANCE;
    }

    private Map<String, DownloadFileInfo> mDownloadDatas;
    private Map<String, List<DonutProgress>> mProgressViews;
    private LinkedList<String> urls;

    public DownloadUtil() {
        mDownloadDatas = new ConcurrentHashMap<>();
        mProgressViews = new ConcurrentHashMap<>();
        urls = new LinkedList<>();
        RxManagerUtil.on(DOWNLOAD_FINISH, new Action1<DownloadFileInfo>() {
            @Override
            public void call(DownloadFileInfo downloadFileInfo) {
                if (urls.size() == 0) {
                    isExqWork = false;
                } else {
                    workHandler.sendEmptyMessage(EXQ_WORK);
                }
            }
        });
    }

    public  boolean isIsExqWork() {
        return isExqWork;
    }


    private Handler workHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case EXQ_WORK:
                    isExqWork = true;
//                    WpyxConfig.isDowning=false;
                    if (EmptyUtils.isNotEmpty(urls)) {
                        final String currentUrl = urls.getFirst();
                        final DownloadFileInfo fileInfo = mDownloadDatas.get(currentUrl);
                        fileInfo.setProgressCallback(new ProgressCallback() {
                            @Override
                            public void onProgressMain(int percent, long bytesWritten, long contentLength, boolean done) {
//                                if(mdownloadListener!=null){
//                                    mdownloadListener.onDownStart(currentUrl);
//                                }
                                setProgress(percent, currentUrl, false);
                                WpyxConfig.setDownUrl(currentUrl);
                                LogUtils.logd("下载"+percent);
                            }

                            @Override
                            public void onResponseMain(String filePath, HttpInfo info) {
                                if (info.isSuccessful()) {
                                    setProgress(100, currentUrl, true);
                                    urls.remove(currentUrl);
                                    mDownloadDatas.remove(currentUrl);
                                    WpyxConfig.isDowning=true;
                                    RxManagerUtil.post(DOWNLOAD_FINISH, fileInfo);
                                    LogUtils.logd(info.getRetDetail() + "\n下载状态：" + fileInfo.getDownloadStatus());
                                        ToastUitl.showShort("下载完成");
                                    isExqWork=false;
                                }else {
                                        Log.e("onResponseMain",""+info.getRetCode());
                                }
                            }
                        });

                        HttpInfo info = HttpInfo.Builder().addDownloadFile(fileInfo).build();
                        OkHttpUtil.Builder().setReadTimeout(120).build(currentUrl).doDownloadFileAsync(info);
                    } else {
                        isExqWork = false;
                    }
                    break;
            }
            return false;
        }
    });

    private void setProgress(int percent, String currentUrl, boolean isSuccess) {
        List<DonutProgress> progressList = mProgressViews.get(currentUrl);
        if (EmptyUtils.isNotEmpty(progressList)) {
            for (int i = 0; i < progressList.size(); i++) {
                DonutProgress donutProgress = progressList.get(i);
                donutProgress.setProgress(percent);
                if (percent == 100) {
                    progressList.get(i).setVisibility(View.GONE);
                } else {
                    if (!donutProgress.isShown()&&!isPlayerActivity) {
                        donutProgress.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (isSuccess) {
                progressList.clear();
                mProgressViews.remove(currentUrl);
            }
        }
    }

    public void addProgressViews(String url, DonutProgress... progresses) {
        if (EmptyUtils.isEmpty(url) || EmptyUtils.isEmpty(progresses) || EmptyUtils.isEmpty(urls)) return;
        if (mProgressViews.containsKey(url)) {
            mProgressViews.get(url).addAll(Arrays.asList(progresses));
        } else {
            List<DonutProgress> progressList = new ArrayList<>();
            progressList.addAll(Arrays.asList(progresses));
            mProgressViews.put(url, progressList);
        }
    }

    public void addProgressView(String url, DonutProgress progresses) {
        if (EmptyUtils.isEmpty(url) || EmptyUtils.isEmpty(progresses) || EmptyUtils.isEmpty(urls)) return;
        if (mProgressViews.containsKey(url)) {
            List<DonutProgress> progressList = mProgressViews.get(url);
            if (!progressList.contains(progresses)) {
                progressList.add(progresses);
            }
        } else {
            List<DonutProgress> progressList = new ArrayList<>();
            progressList.add(progresses);
            mProgressViews.put(url, progressList);
        }
    }

    public void singleDownloadFile(String url, String fileDir, String fileName, WpyxDownloadUtil.onDownloadListener downloadListener) {
         mdownloadListener=downloadListener;
        if (EmptyUtils.isNotEmpty(url) && EmptyUtils.isNotEmpty(fileDir) && !urls.contains(url)) {
            urls.add(url);
            DownloadFileInfo downloadFileInfo = new DownloadFileInfo(url, fileDir, fileName);
            mDownloadDatas.put(url, downloadFileInfo);
        }
        if (!isExqWork) {
            workHandler.sendEmptyMessage(EXQ_WORK);
        }
    }


    public void downloadFile(String url, String fileDir,  String fileName) {
        DownloadFileInfo downloadFileInfo = new DownloadFileInfo(url, fileDir,fileName);
        HttpInfo info = HttpInfo.Builder().addDownloadFile(downloadFileInfo).build();
        OkHttpUtil.Builder().setReadTimeout(120).build(url).doDownloadFileAsync(info);
    }


    public void pause(String url) {
        if (urls.contains(url)) {
            mDownloadDatas.get(url).setDownloadStatus(DownloadStatus.PAUSE);
            isExqWork = false;
        }
    }

    public void cancel(String url) {
        if (urls.contains(url)) {
            OkHttpUtil.Builder().build(url).cancelRequest(url);
            urls.remove(url);
            mDownloadDatas.remove(url);
            mProgressViews.remove(url).clear();
            workHandler.sendEmptyMessage(EXQ_WORK);
        }
    }

    public void cancelAll() {
        for (String s : urls) {
            OkHttpUtil.Builder().build(s).cancelRequest(s);
        }
        urls.clear();
        mDownloadDatas.clear();
        isExqWork = false;
    }

    public List<String> getDownloadUrl(){
        return urls;
    }
}
