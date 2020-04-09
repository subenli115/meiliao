package com.ziran.meiliao.utils;

import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.ExercisePageEntry;
import com.ziran.meiliao.envet.StringResultCallBack;
import com.ziran.meiliao.ui.bean.PracticePageBean;

import java.io.File;
import java.util.List;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     练习首页工具类,用于下载练习页面的背景图和背景音乐
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class PracticeDataUtil {
    //练习界面的数据对象
    private static PracticePageBean sPracticePageBean;
    //是否初始化
    private static boolean isInit = false;


    /**
     * 初始化数据
     * 首先从数据库查找,如果有内容则返回数据,没有则进行网络请求获取数据
     */
    public static void init() {
        if (isInit) return;
        List<ExercisePageEntry> entries = DbCore.getDaoSession().getExercisePageEntryDao().loadAll();
        if (checkNeedResetDownload(entries)) {
            //检查是否是已经初始化练习界面音频数据
            OkHttpClientManager.postAsync(ApiKey.PRACTICE_DOWNLOAD_VEDIO, MapUtils.getDefMap(false), new StringResultCallBack() {
                @Override
                public void onSuccess(String result) {
                    ExercisePageEntry exercisePageEntry = new ExercisePageEntry();
                    exercisePageEntry.setId(1L);
                    exercisePageEntry.setData(result);
                    DbCore.getDaoSession().getExercisePageEntryDao().insertOrReplace(exercisePageEntry);
                    PracticePageBean practicePageBean = exercisePageEntry.getExercisePageBean();
                    SPUtils.setInt(AppConstant.SPKey.EXERCISE_NUMBER, practicePageBean.getData().getNumber());
                    downloadFile(practicePageBean);
                }
            });
        } else {
            downloadFile(entries.get(0).getExercisePageBean());
        }
    }

    private static boolean checkNeedResetDownload(List<ExercisePageEntry> entries) {
        if (EmptyUtils.isEmpty(entries)) return true;
        try {
            PracticePageBean.DataBean.ListBean listBean = entries.get(0).getExercisePageBean().getData().getList().get(0);
            return EmptyUtils.isEmpty(listBean.getVedioPathUrl());
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }


    /**
     * 下载文件(背景和叮一声音乐)
     *
     * @param practicePageBean 练习数据对象
     */
    public static void downloadFile(PracticePageBean practicePageBean) {
        if (practicePageBean != null && practicePageBean.getData() != null) {
            PracticeDataUtil.sPracticePageBean = practicePageBean;
            isInit = true;
            List<PracticePageBean.DataBean.ListBean> listBeanList = practicePageBean.getData().getList();
            for (int i = 0; i < listBeanList.size(); i++) {
                PracticePageBean.DataBean.ListBean listBean = listBeanList.get(i);

                if (listBean != null && EmptyUtils.isNotEmpty(listBean.getVedioPathUrl()) && !new File(FileUtil.getExerciseBj2Mp4FileName(i))
                        .exists()) {
                    downFile(listBean.getVedioPathUrl(), FileUtil.getExerciseBj2Mp4FileName(i));
                }
                if (!new File(FileUtil.getExerciseDingMp3FileName()).exists()){
                    downFile(practicePageBean.getData().getStartMusicUrl(), FileUtil.getExerciseDingMp3FileName());
                }
            }
        }
    }

    //下载文件(mp3)
    public static void downFile(final String downloadUrl, final String fileName) {
        final File file = new File(fileName);

        if (!file.exists()) {
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    LogUtils.logd("downloadUrl:"+downloadUrl);
                    DownloadUtil.getInstance().downloadFile(downloadUrl, file.getParent() + "/", file.getName());
                }
            });

        }
    }

    public static void onDestroy() {
        isInit = false;
        sPracticePageBean = null;
    }

    public static PracticePageBean getPracticePageBean() {
        return sPracticePageBean;
    }

    public static PracticePageBean.DataBean.ListBean get(int index) {
        if(sPracticePageBean==null){
            return null;
        }else{

            return sPracticePageBean.getData().getList().get(index);
        }
    }


}
