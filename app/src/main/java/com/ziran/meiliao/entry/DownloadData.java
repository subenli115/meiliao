package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *  下载时的数据bean
 */
@Entity
public class DownloadData implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    private String url;
    private String path;
    private String name;
    private int currentLength;
    private int totalLength;
    private float percentage;
    private int state = 0;
    private int childTaskCount;
    private long date;
    private String lastModify;

    public DownloadData() {

    }

    public DownloadData(String url, String path, String name) {
        this.url = url;
        this.path = path;
        this.name = name;
    }

    public DownloadData(String url, String path, String name, int childTaskCount) {
        this.url = url;
        this.path = path;
        this.name = name;
        this.childTaskCount = childTaskCount;
    }

    public DownloadData(String url, String path, int childTaskCount, String name, int currentLength, int totalLength, String lastModify, long date) {
        this.url = url;
        this.path = path;
        this.childTaskCount = childTaskCount;
        this.currentLength = currentLength;
        this.name = name;
        this.totalLength = totalLength;
        this.lastModify = lastModify;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getChildTaskCount() {
        return childTaskCount;
    }

    public void setChildTaskCount(int childTaskCount) {
        this.childTaskCount = childTaskCount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.path);
        dest.writeString(this.name);
        dest.writeInt(this.currentLength);
        dest.writeInt(this.totalLength);
        dest.writeFloat(this.percentage);
        dest.writeInt(this.state);
        dest.writeInt(this.childTaskCount);
        dest.writeLong(this.date);
        dest.writeString(this.lastModify);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    protected DownloadData(Parcel in) {
        this.url = in.readString();
        this.path = in.readString();
        this.name = in.readString();
        this.currentLength = in.readInt();
        this.totalLength = in.readInt();
        this.percentage = in.readFloat();
        this.state = in.readInt();
        this.childTaskCount = in.readInt();
        this.date = in.readLong();
        this.lastModify = in.readString();
    }

    @Generated(hash = 842418278)
    public DownloadData(Long id, String url, String path, String name, int currentLength, int totalLength, float percentage, int state,
            int childTaskCount, long date, String lastModify) {
        this.id = id;
        this.url = url;
        this.path = path;
        this.name = name;
        this.currentLength = currentLength;
        this.totalLength = totalLength;
        this.percentage = percentage;
        this.state = state;
        this.childTaskCount = childTaskCount;
        this.date = date;
        this.lastModify = lastModify;
    }

    public static final Creator<DownloadData> CREATOR = new Creator<DownloadData>() {
        @Override
        public DownloadData createFromParcel(Parcel source) {
            return new DownloadData(source);
        }

        @Override
        public DownloadData[] newArray(int size) {
            return new DownloadData[size];
        }
    };

    @Override
    public String toString() {
        return "DownloadData{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", currentLength=" + currentLength +
                ", totalLength=" + totalLength +
                ", percentage=" + percentage +
                ", state=" + state +
                ", childTaskCount=" + childTaskCount +
                ", date=" + date +
                ", lastModify='" + lastModify + '\'' +
                '}';
    }
}
