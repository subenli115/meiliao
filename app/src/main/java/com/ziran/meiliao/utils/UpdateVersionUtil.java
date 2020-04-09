package com.ziran.meiliao.utils;

import android.os.Build;

import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.ExercisePageEntry;
import com.ziran.meiliao.envet.StringResultCallBack;
import com.ziran.meiliao.ui.bean.PracticePageBean;

import java.util.List;

/**
 * author 吴祖清
 * create  2017/4/8 13
 * des     版本更新检查是否需要重新下载练习资源的工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class UpdateVersionUtil {

    public static boolean IS(int version) {
        return Build.VERSION.SDK_INT >= version;
    }

    private static boolean NEED_UPDATE_PRACTICE= false;

    public static void updateVersion() {
        OkHttpClientManager.postAsync(ApiKey.PRACTICE_DOWNLOAD_VEDIO, MapUtils.getDefMap(false), new StringResultCallBack() {
            @Override
            public void onSuccess(final String result) {
                HandlerUtil.runTask(new Runnable() {
                    @Override
                    public void run() {
                        final ExercisePageEntry exercisePageEntry = new ExercisePageEntry();
                        exercisePageEntry.setId(1L);
                        exercisePageEntry.setData(result);
                        final PracticePageBean dataBean = exercisePageEntry.getExercisePageBean();
                        if (dataBean == null) return;
                        int number = SPUtils.getInt(AppConstant.SPKey.EXERCISE_NUMBER);
                        int serviceNumber = NEED_UPDATE_PRACTICE?5000:dataBean.getData().getNumber();

                        if (true) {
                            List<PracticePageBean.DataBean.ListBean> list = dataBean.getData().getList();
                            for (int i = 0; i < list.size(); i++) {
                                FileUtil.delete(FileUtil.getExerciseBGFileName(i));
                                FileUtil.delete(FileUtil.getExerciseBj2Mp3FileName(i));
                                FileUtil.delete(FileUtil.getExerciseBj2Mp4FileName(i));
                            }
                            DbCore.getDaoSession().getExercisePageEntryDao().insertOrReplace(exercisePageEntry);
                            SPUtils.setInt(AppConstant.SPKey.EXERCISE_NUMBER, dataBean.getData().getNumber());
                            PracticeDataUtil.downloadFile(dataBean);
                        }
                    }
                });
            }
        });
    }
}
