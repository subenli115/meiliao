package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.db.DbCore;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

/**
 * 专辑对象
 * Created by Administrator on 2017/2/28.
 */

@Entity
public class AlbumEntry implements Parcelable {

    private int collectCount;
    private String headImg;
    private boolean isCollectAlbum;
    private String shareUrl;
    private String detail;
    private String title;
    private String albumId;
    private String name;
    private boolean isBuy;
    @Id
    private Long id;
    private String picture;

    @Generated(hash = 2104132900)
    public AlbumEntry(int collectCount, String headImg, boolean isCollectAlbum, String shareUrl, String detail, String title, String albumId,
                      String name, boolean isBuy, Long id, String picture) {
        this.collectCount = collectCount;
        this.headImg = headImg;
        this.isCollectAlbum = isCollectAlbum;
        this.shareUrl = shareUrl;
        this.detail = detail;
        this.title = title;
        this.albumId = albumId;
        this.name = name;
        this.isBuy = isBuy;
        this.id = id;
        this.picture = picture;
    }

    @Generated(hash = 723073685)
    public AlbumEntry() {
    }

    public int getCollectCount() {
        return this.collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public String getHeadImg() {
        return this.headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public boolean getIsCollectAlbum() {
        return this.isCollectAlbum;
    }

    public void setIsCollectAlbum(boolean isCollectAlbum) {
        this.isCollectAlbum = isCollectAlbum;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsBuy() {
        return this.isBuy;
    }

    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public boolean isCollectAlbum() {
        return isCollectAlbum;
    }

    public void setCollectAlbum(boolean collectAlbum) {
        isCollectAlbum = collectAlbum;
    }

    public static void insert(String title, String detail, String name, String albumId, String headImg, boolean isBuy,String albumDetail) {
        List<AlbumEntry> albumEntries = DbCore.getDaoSession().getAlbumEntryDao().queryRaw("where ALBUM_ID = ?", new String[]{albumId});
        if (EmptyUtils.isEmpty(albumEntries)) {
            AlbumEntry musicEntry = new AlbumEntry();
            musicEntry.setAlbumId(albumId);
            musicEntry.setId(Long.valueOf(albumId));
            musicEntry.setName(name);
            musicEntry.setIsBuy(isBuy);
            if (EmptyUtils.isNotEmpty(headImg)){
                musicEntry.setPicture(headImg);
            }
            if (EmptyUtils.isNotEmpty(albumDetail)){
                musicEntry.setDetail(albumDetail);
            }
            musicEntry.setTitle(title);
            DbCore.getDaoSession().getAlbumEntryDao().insertOrReplace(musicEntry);
        } else {

        }
    }

    public static AlbumEntry getAlbum(String albumId) {
        List<AlbumEntry> albumEntries = DbCore.getDaoSession().getAlbumEntryDao().queryRaw("where ALBUM_ID = ?", new String[]{albumId});
        if (EmptyUtils.isNotEmpty(albumEntries)) {
            return albumEntries.get(0);
        }
        return null;
    }

    public static List<AlbumEntry> getAlbumList(String albumId) {
        return DbCore.getDaoSession().getAlbumEntryDao().queryRaw("where ALBUM_ID = ?", new String[]{albumId});
    }

    @Override
    public String toString() {
        return "AlbumEntry{" +
                "collectCount=" + collectCount +
                ", headImg='" + headImg + '\'' +
                ", isCollectAlbum=" + isCollectAlbum +
                ", shareUrl='" + shareUrl + '\'' +
                ", detail='" + detail + '\'' +
                ", title='" + title + '\'' +
                ", albumId='" + albumId + '\'' +
                ", name='" + name + '\'' +
                ", getIsBuy=" + isBuy +
                ", id='" + id + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.collectCount);
        dest.writeString(this.headImg);
        dest.writeByte(this.isCollectAlbum ? (byte) 1 : (byte) 0);
        dest.writeString(this.shareUrl);
        dest.writeString(this.detail);
        dest.writeString(this.title);
        dest.writeString(this.albumId);
        dest.writeString(this.name);
        dest.writeByte(this.isBuy ? (byte) 1 : (byte) 0);
        dest.writeValue(this.id);
        dest.writeString(this.picture);
    }

    protected AlbumEntry(Parcel in) {
        this.collectCount = in.readInt();
        this.headImg = in.readString();
        this.isCollectAlbum = in.readByte() != 0;
        this.shareUrl = in.readString();
        this.detail = in.readString();
        this.title = in.readString();
        this.albumId = in.readString();
        this.name = in.readString();
        this.isBuy = in.readByte() != 0;
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.picture = in.readString();
    }

    public static final Parcelable.Creator<AlbumEntry> CREATOR = new Parcelable.Creator<AlbumEntry>() {
        @Override
        public AlbumEntry createFromParcel(Parcel source) {
            return new AlbumEntry(source);
        }

        @Override
        public AlbumEntry[] newArray(int size) {
            return new AlbumEntry[size];
        }
    };
}
