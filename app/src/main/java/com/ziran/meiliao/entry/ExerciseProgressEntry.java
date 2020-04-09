package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.db.DbCore;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 *  保存练习进度的bean
 * Created by Administrator on 2017/3/11.
 */

@Entity
public class ExerciseProgressEntry implements Parcelable {

    @Id
    private Long id;
    private int max;                //本次练习的时间(单位:秒)
    private int currentProgress;    //当前的进度    (单位:秒)
    private Date createData;        //  最新的保存时间

    @Generated(hash = 1747279854)
    public ExerciseProgressEntry(Long id, int max, int currentProgress,
                                 Date createData) {
        this.id = id;
        this.max = max;
        this.currentProgress = currentProgress;
        this.createData = createData;
    }

    @Generated(hash = 1082302240)
    public ExerciseProgressEntry() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCurrentProgress() {
        return this.currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public Date getCreateData() {
        return this.createData;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }

    @Override
    public String toString() {
        return "ExerciseProgressEntry{" +
                "id=" + id +
                ", max=" + max +
                ", currentProgress=" + currentProgress +
                ", createData=" + createData +
                '}';
    }


    public static void insert(int max, int currentProgress) {
        if (max ==0 && currentProgress ==0) return;
        ExerciseProgressEntry exerciseProgressEntry = new ExerciseProgressEntry();
        exerciseProgressEntry.setId(1L);
        exerciseProgressEntry.setMax(max);
        exerciseProgressEntry.setCurrentProgress(currentProgress);
        exerciseProgressEntry.setCreateData(new Date());
        LogUtils.logd("insert"+ exerciseProgressEntry);
        DbCore.getDaoSession().getExerciseProgressEntryDao().insertOrReplace(exerciseProgressEntry);
    }

    public static ExerciseProgressEntry load() {
        return DbCore.getDaoSession().getExerciseProgressEntryDao().load(1L);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeInt(this.max);
        dest.writeInt(this.currentProgress);
        dest.writeLong(this.createData != null ? this.createData.getTime() : -1);
    }

    protected ExerciseProgressEntry(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.max = in.readInt();
        this.currentProgress = in.readInt();
        long tmpCreateData = in.readLong();
        this.createData = tmpCreateData == -1 ? null : new Date(tmpCreateData);
    }

    public static final Parcelable.Creator<ExerciseProgressEntry> CREATOR = new Parcelable.Creator<ExerciseProgressEntry>() {
        @Override
        public ExerciseProgressEntry createFromParcel(Parcel source) {
            return new ExerciseProgressEntry(source);
        }

        @Override
        public ExerciseProgressEntry[] newArray(int size) {
            return new ExerciseProgressEntry[size];
        }
    };
}
