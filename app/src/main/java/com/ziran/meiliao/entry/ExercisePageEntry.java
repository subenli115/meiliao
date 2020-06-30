package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.bean.PracticePageBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**练习页面数据,包含背景,及背景音乐和开始的叮声音
 * Created by Administrator on 2017/3/4.
 */

@Entity
public class ExercisePageEntry implements Parcelable {
    @Id
  private   Long id;
    private  String data;

    @Generated(hash = 2098595735)
    public ExercisePageEntry(Long id, String data) {
        this.id = id;
        this.data = data;
    }

    @Generated(hash = 667598100)
    public ExercisePageEntry() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public PracticePageBean getExercisePageBean() {
        if (EmptyUtils.isEmpty(data)) return null;
        return JsonUtils.fromJsonToType(data, PracticePageBean.class);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.data);
    }

    protected ExercisePageEntry(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.data = in.readString();
    }

    public static final Parcelable.Creator<ExercisePageEntry> CREATOR = new Parcelable.Creator<ExercisePageEntry>() {
        @Override
        public ExercisePageEntry createFromParcel(Parcel source) {
            return new ExercisePageEntry(source);
        }

        @Override
        public ExercisePageEntry[] newArray(int size) {
            return new ExercisePageEntry[size];
        }
    };

    @Override
    public String toString() {
        return "ExercisePageEntry{" + "id=" + id + ", data='" + data + '\'' + '}';
    }
}
