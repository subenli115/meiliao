package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeAlbumBean implements Parcelable {
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getMusicName() {
        return MusicName;
    }

    public void setMusicName(String musicName) {
        MusicName = musicName;
    }

    String    pic;
    String albumId;
    String musicId;
    String albumName;
    String MusicName;

    public static final Creator<HomeAlbumBean> CREATOR = new Creator<HomeAlbumBean>() {
        @Override
        public HomeAlbumBean createFromParcel(Parcel in) {
            return new HomeAlbumBean(in);
        }

        @Override
        public HomeAlbumBean[] newArray(int size) {
            return new HomeAlbumBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.pic);
        dest.writeString(this.albumId);
        dest.writeString(this.musicId);
        dest.writeString(this.albumName);
        dest.writeString(this.MusicName);
    }

    protected HomeAlbumBean(Parcel in) {
        this.pic = in.readString();
        this.albumId = in.readString();
        this.musicId = in.readString();
        this.albumName = in.readString();
        this.MusicName = in.readString();
    }

    public HomeAlbumBean() {

    }
}
