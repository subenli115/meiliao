package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.dao.MusicEntryDao;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.utils.StringUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 音乐的bean
 * Created by Administrator on 2017/2/28.
 */


@Entity
public class MusicEntry implements Parcelable {


    private String albumId;
    private String path;
    private String duration;
    private boolean isCollectMusic;
    @Id
    private Long id;
    private int musicId;
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    private int type;
    private String url;
    private float fileSize;

    //    @Transient
    private String size;
    private String shareUrl;
    private String shareTitle;
    private String shareDescript;
    private String sharePic;
    //    @Transient
    private String catalogName;
    //    @Transient
    private boolean isHead;
    //    @Transient
    private boolean audition;
    @Transient
    private boolean st;


    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDescript() {
        return shareDescript;
    }

    public void setShareDescript(String shareDescript) {
        this.shareDescript = shareDescript;
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public float getFileSize() {
        return this.fileSize;
    }

    public MusicEntry() {

    }


    public boolean isSt() {
        return st;
    }

    public void setSt(boolean st) {
        this.st = st;
    }

    public void setFileSize(float fileSize) {
        this.fileSize = fileSize;
    }

    public String getAESUrl() {
        if (EmptyUtils.isNotEmpty(url)) {
            return AES.get().decrypt(this.url);
        } else {
            return "";
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMusicId() {
        return this.musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public Long getId() {
        if (EmptyUtils.isEmpty(id)) {
            id = (long) getMusicId();
        }
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static void insert(String albumId, String url, String name, String id, String path, float fileSize) {
        MusicEntry musicEntry = new MusicEntry();
        musicEntry.setUrl(url);
        musicEntry.setName(name);
        musicEntry.setId(Long.parseLong(id));
        musicEntry.setMusicId(Integer.parseInt(id));
        musicEntry.setFileSize(fileSize);
        musicEntry.setPath(path);
        musicEntry.setAlbumId(albumId);
        DbCore.getDaoSession().getMusicEntryDao().insertOrReplace(musicEntry);
    }

    public static void insert(String albumId, String url, String name, String id, String path, float fileSize, String duration) {
        MusicEntry musicEntry = new MusicEntry();
        musicEntry.setUrl(url);
        musicEntry.setName(name);
        musicEntry.setId(Long.parseLong(id));
        musicEntry.setMusicId(Integer.parseInt(id));
        musicEntry.setFileSize(fileSize);
        musicEntry.setPath(path);
        if (EmptyUtils.isNotEmpty(duration)) {
            musicEntry.setDuration(duration);
        }
        musicEntry.setAlbumId(albumId);
        DbCore.getDaoSession().getMusicEntryDao().insertOrReplace(musicEntry);
    }

    public static void insert(String albumId, MusicEntry entry) {
        entry.setAlbumId(albumId);
        entry.setId((long) entry.getMusicId());
//        LogUtils.logd("insert MusicEntry" + entry);
        DbCore.getDaoSession().getMusicEntryDao().insertOrReplace(entry);
    }


    public String getPath() {
        String filePath = getFilePath();
        if (FileUtil.fileIsExists(filePath)) {
            return filePath;
        }
        return getAESUrl();
    }

    public String getFilePath(String fileName) {
        return FileUtil.getDownMusicFolder() + fileName + ".mp3";
    }
    public String getFilePathZl(String fileName) {
        return FileUtil.getDownZlFolder()+ fileName + ".mp3";
    }
    public String getFilePathZlVideo(String fileName) {
        return FileUtil.getDownZlFolder()+ fileName + ".mp4";
    }
    public static MusicEntry getCatalog(String catalogName) {
        MusicEntry musicEntry = new MusicEntry();
        musicEntry.setHead(true);
        musicEntry.setCatalogName(catalogName);
        return musicEntry;
    }

    public String getFilePath() {
        return getFilePath(getName());
    }
    public String getFilePathZl() {
        return getFilePathZl(getName());
    }
    public String getFilePathVideo() {
        return getFilePathZlVideo(getName());
    }
    public void setPath(String path) {
        this.path = path;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public boolean getIsCollectMusic() {
        return this.isCollectMusic;
    }

    public void setIsCollectMusic(boolean isCollectMusic) {
        this.isCollectMusic = isCollectMusic;
    }

    @Generated(hash = 1976628705)
    public MusicEntry(String albumId, String path, String duration, boolean isCollectMusic, Long id, int musicId, String name, int type,
            String url, float fileSize, String size, String shareUrl, String shareTitle, String shareDescript, String sharePic,
            String catalogName, boolean isHead, boolean audition) {
        this.albumId = albumId;
        this.path = path;
        this.duration = duration;
        this.isCollectMusic = isCollectMusic;
        this.id = id;
        this.musicId = musicId;
        this.name = name;
        this.type = type;
        this.url = url;
        this.fileSize = fileSize;
        this.size = size;
        this.shareUrl = shareUrl;
        this.shareTitle = shareTitle;
        this.shareDescript = shareDescript;
        this.sharePic = sharePic;
        this.catalogName = catalogName;
        this.isHead = isHead;
        this.audition = audition;
    }

    @Override
    public String toString() {
        return "MusicEntry{" + "albumId='" + albumId + '\'' + ", path='" + path + '\'' + ", duration='" + duration + '\'' + ", " +
                "isCollectMusic=" + isCollectMusic + ", id=" + id + ", musicId=" + musicId + ", name='" + name + '\'' + ", url='" + url +
                '\'' + ", fileSize=" + fileSize + ", size='" + size + '\'' + ", shareUrl='" + shareUrl + '\'' + ", shareTitle='" +
                shareTitle + '\'' + ", shareDescript='" + shareDescript + '\'' + ", sharePic='" + sharePic + '\'' + ", catalogName='" +
                catalogName + '\'' + ", isHead=" + isHead + '}';
    }

    public static void delete(String url) {

    }

    public static boolean hasDataFormAlbumId(String albumId) {
        List<MusicEntry> musicEntries = DbCore.getDaoSession().getMusicEntryDao().queryRaw("where ALBUM_ID = ?", StringUtils
                .getStringArray(albumId));
        return EmptyUtils.isEmpty(musicEntries);
    }

    public static void doCollect(MusicEntry currMusicEntry) {
        if (currMusicEntry == null) return;
        MusicEntryDao musicEntryDao = DbCore.getDaoSession().getMusicEntryDao();
        MusicEntry musicEntry = musicEntryDao.load(currMusicEntry.getId());
        if (musicEntry != null) {
            musicEntryDao.update(currMusicEntry);
            LogUtils.logd("doCollect musicEntry" + musicEntry.isCollectMusic);
        }
    }

    public void setAudition(boolean audition) {
        this.audition = audition;
    }

    public boolean isAudition() {
        return audition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.albumId);
        dest.writeString(this.path);
        dest.writeString(this.duration);
        dest.writeByte(this.isCollectMusic ? (byte) 1 : (byte) 0);
        dest.writeValue(this.id);
        dest.writeInt(this.musicId);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeFloat(this.fileSize);
        dest.writeString(this.size);
        dest.writeString(this.shareUrl);
        dest.writeString(this.shareTitle);
        dest.writeString(this.shareDescript);
        dest.writeString(this.sharePic);
        dest.writeString(this.catalogName);
        dest.writeByte(this.isHead ? (byte) 1 : (byte) 0);
        dest.writeByte(this.audition ? (byte) 1 : (byte) 0);
    }

    public boolean getIsHead() {
        return this.isHead;
    }

    public void setIsHead(boolean isHead) {
        this.isHead = isHead;
    }

    public boolean getAudition() {
        return this.audition;
    }



    protected MusicEntry(Parcel in) {
        this.albumId = in.readString();
        this.path = in.readString();
        this.duration = in.readString();
        this.isCollectMusic = in.readByte() != 0;
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.musicId = in.readInt();
        this.name = in.readString();
        this.url = in.readString();
        this.fileSize = in.readFloat();
        this.size = in.readString();
        this.shareUrl = in.readString();
        this.shareTitle = in.readString();
        this.shareDescript = in.readString();
        this.sharePic = in.readString();
        this.catalogName = in.readString();
        this.isHead = in.readByte() != 0;
        this.audition = in.readByte() != 0;
    }

    public static final Creator<MusicEntry> CREATOR = new Creator<MusicEntry>() {
        @Override
        public MusicEntry createFromParcel(Parcel source) {
            return new MusicEntry(source);
        }

        @Override
        public MusicEntry[] newArray(int size) {
            return new MusicEntry[size];
        }
    };
}
