package com.ziran.meiliao.entry;

import android.content.Context;

import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.utils.StringUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */

@Entity
public class VideoSectionEntry {
    @Id
    private Long id;
    @Transient
    private int courseId;
    private String rootCourseId;

    //    @Transient
    private boolean mFree;
    private int tag;

    private boolean isCur;
    @Transient
    private boolean isSelect;
    @Transient
    private boolean showMonth;
    private String duration;
    private String title;
    @Transient
    private long startTime;
    @Transient
    private String time;
    @Transient
    private String month;
    @Transient
    private String date;
    @Transient
    private  String progress;
    @Transient
    private int studyStatus;
    @Transient
    private int studyCount;
    @Transient
    private boolean isNewest;
    private boolean isHis;
    private String url;



    public String getTime() {
        return time;
    }

    public int getStudyCount() {
        return studyCount;
    }

    public void setStudyCount(int studyCount) {
        this.studyCount = studyCount;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public int getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(int studyStatus) {
        this.studyStatus = studyStatus;
    }

    public boolean isNewest() {
        return isNewest;
    }

    public void setNewest(boolean newest) {
        isNewest = newest;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getRootCourseId() {
        return rootCourseId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setRootCourseId(String rootCourseId) {
        this.rootCourseId = rootCourseId;
    }

    @Generated(hash = 1455172342)
    public VideoSectionEntry(Long id, String rootCourseId, boolean mFree, int tag, boolean isCur, String duration, String title, boolean
            isHis, String url) {
        this.id = id;
        this.rootCourseId = rootCourseId;
        this.mFree = mFree;
        this.tag = tag;
        this.isCur = isCur;
        this.duration = duration;
        this.title = title;
        this.isHis = isHis;
        this.url = url;
    }
    public static void insert( VideoSectionEntry entry) {
        entry.setId(entry.getId());
        LogUtils.logd("insert MusicEntry" + entry);
        DbCore.getDaoSession().getVideoSectionEntryDao().insertOrReplace(entry);
    }
    @Generated(hash = 1515080789)
    public VideoSectionEntry() {
    }

    public String getFilePath(Context context) {
        return FileUtil.getDownVideoFolder() + title + ".mp4";
    }

    public String getUrlAndPath() {
//        String path = FileUtil.getDownVideoFolder() + (title + ".mp4");
//        if (FileUtil.fileIsExists(path)) {
//            return path;
//        }
        if (EmptyUtils.isNotEmpty(url) && url.contains("http:")) {
            return url;
        }
        return AES.get().decrypt(url);
    }

    public Long getId() {
        return id;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isFree() {
        return mFree;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCur() {
        return isCur;
    }

    public void setCur(boolean cur) {
        isCur = cur;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public boolean isHis() {
        return isHis;
    }

    public void setHis(boolean his) {
        isHis = his;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getIsCur() {
        return this.isCur;
    }

    public void setIsCur(boolean isCur) {
        this.isCur = isCur;
    }

    public boolean getIsHis() {
        return this.isHis;
    }

    public void setIsHis(boolean isHis) {
        this.isHis = isHis;
    }


    public static List<VideoSectionEntry> loadWhereCourseId(String rootCourseId) {
        return DbCore.getDaoSession().getVideoSectionEntryDao().loadAll();
//        return DbCore.getDaoSession().getVideoSectionEntryDao().queryRaw("where  ROOT_COURSE_ID  = ?", new String[]{rootCourseId});
    }

    public static void deleteWhereCourseId(String rootCourseId,Context context) {
//        List<VideoSectionEntry> videoSectionEntries = loadWhereCourseId(rootCourseId);
//        if (EmptyUtils.isNotEmpty(videoSectionEntries)) {
//            for (int i = 0; i < videoSectionEntries.size(); i++) {
//                FileUtil.delete(videoSectionEntries.get(i).getFilePath(context));
//            }
//            DbCore.getDaoSession().getVideoSectionEntryDao().deleteInTx(videoSectionEntries);
//        }
    }

    @Override
    public String toString() {
        return "VideoSectionEntry{" + "id=" + id + ", courseId=" + courseId + ", rootCourseId='" + rootCourseId + '\'' + ", mFree=" +
                mFree + ", tag=" + tag + ", isCur=" + isCur + ", duration='" + duration + '\'' + ", title='" + title + '\'' + ", time='"
                + time + '\'' + ", isHis=" + isHis + ", url='" + url + '\'' + '}';
    }

    public void setFree(boolean free) {
        mFree = free;
    }

    public boolean getMFree() {
        return this.mFree;
    }

    public void setMFree(boolean mFree) {
        this.mFree = mFree;
    }

    public void setMonth(int month) {
        this.month = StringUtils.format("%d月",month);
    }

    public void setDate(int date) {
        this.date =String.valueOf(date);
    }

    public void setShowMonth(boolean showMonth) {
        this.showMonth = showMonth;
    }

    public boolean isShowMonth() {
        return showMonth;
    }

    public String getMonth() {
        return month;
    }

    public String getDate() {
        return date;
    }
    //    public static class StepBean {
//        /**
//         * stepName : 关注呼吸，回到现在，生活在当下，相信身体的康复力量
//         */
//
//        private String stepName;
//
//        public String getStepName() {
//            return stepName;
//        }
//
//        public void setStepName(String stepName) {
//            this.stepName = stepName;
//        }
//    }
}
