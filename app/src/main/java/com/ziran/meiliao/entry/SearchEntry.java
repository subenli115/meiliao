package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 页面缓存的数据,每个页面只缓存第一页
 * Created by Administrator on 2017/3/11.
 */

@Entity
public class SearchEntry implements Parcelable {
    @Id
    private Long id;    //自定义的ID
    private String content;   //缓存数据的字符串
    private int type;     //发起请求的URL

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.content);
        dest.writeInt(this.type);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SearchEntry() {
    }

    protected SearchEntry(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.content = in.readString();
        this.type = in.readInt();
    }

    @Generated(hash = 2038911752)
    public SearchEntry(Long id, String content, int type) {
        this.id = id;
        this.content = content;
        this.type = type;
    }

    public static final Parcelable.Creator<SearchEntry> CREATOR = new Parcelable.Creator<SearchEntry>() {
        @Override
        public SearchEntry createFromParcel(Parcel source) {
            return new SearchEntry(source);
        }

        @Override
        public SearchEntry[] newArray(int size) {
            return new SearchEntry[size];
        }
    };

    public SearchEntry(String content, int type) {
        this.content = content;
        this.type = type;
    }


}
