package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.db.DbCore;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 页面缓存的数据,每个页面只缓存第一页
 * Created by Administrator on 2017/3/11.
 */

@Entity
public class CachePageEntry implements Parcelable {
    @Id
    private Long id;    //自定义的ID
    private String data;   //缓存数据的字符串
    private String url;     //发起请求的URL

    @Generated(hash = 1607461282)
    public CachePageEntry(Long id, String data, String url) {
        this.id = id;
        this.data = data;
        this.url = url;
    }

    @Generated(hash = 1244579346)
    public CachePageEntry() {
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getAESData() {
        return AES.get().decrypt(data);
    }

    public static void insertData(long id, Object data) {
        insertData(id, data, 1);
    }

    public static void insertData(long id, Object data, int page) {
        if (page != 1) return;
        String toJson = JsonUtils.toJson(data);
        if (EmptyUtils.isNotEmpty(toJson)) {
            CachePageEntry cachePageEntry = new CachePageEntry();
            cachePageEntry.setId(id);
            cachePageEntry.setData(AES.get().encrypt(toJson.getBytes()));
            DbCore.getDaoSession().getCachePageEntryDao().insertOrReplace(cachePageEntry);
        }
    }
    public static void insertData(long id, String  data, int page) {
        if (page != 1) return;
        if (EmptyUtils.isNotEmpty(data)) {
            CachePageEntry cachePageEntry = new CachePageEntry();
            cachePageEntry.setId(id);
            cachePageEntry.setData(AES.get().encrypt(data.getBytes()));
            DbCore.getDaoSession().getCachePageEntryDao().insertOrReplace(cachePageEntry);
        }
    }

    public static <T> T loadData(long id, Class<T> clz) {
        if (id > 0) {
            CachePageEntry cachePageEntry = DbCore.getDaoSession().getCachePageEntryDao().load(id);
            if (EmptyUtils.isNotEmpty(cachePageEntry)) {
                String aesData = cachePageEntry.getAESData();
                return JsonUtils.fromJsonToType(aesData, clz);
            }
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.data);
        dest.writeString(this.url);
    }

    protected CachePageEntry(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.data = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<CachePageEntry> CREATOR = new Parcelable.Creator<CachePageEntry>() {
        @Override
        public CachePageEntry createFromParcel(Parcel source) {
            return new CachePageEntry(source);
        }

        @Override
        public CachePageEntry[] newArray(int size) {
            return new CachePageEntry[size];
        }
    };
}
