package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.dao.AuthorBeanDao;
import com.ziran.meiliao.dao.CourseEntryDao;
import com.ziran.meiliao.dao.DaoSession;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.ui.bean.AuthorBean;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import com.ziran.meiliao.dao.DaoSession;
import com.ziran.meiliao.dao.AuthorBeanDao;
import com.ziran.meiliao.dao.CourseEntryDao;
import com.ziran.meiliao.dao.DaoSession;
import com.ziran.meiliao.dao.AuthorBeanDao;
import com.ziran.meiliao.dao.CourseEntryDao;

/**
 * 课程数据的bean
 * Created by Administrator on 2017/2/28.
 */

@Entity
public class CourseEntry implements Parcelable {

    private String name;
    private String courseId;
    private String url;
    private String picture;
    @Id
    private Long id;
    private String authorName;
    private String detail;
    private String title;
    private String path;
    private long fileSize;
//    @Transient
    private int watchCount;
//    @Transient
    private boolean isCheckIsBuy;

//    @Transient
    private String price;
//    @Transient
    private boolean isBuy;
//    @Transient
    private int  status;
//        @Transient
    private int  tag;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getPrice() {
        if (EmptyUtils.isEmpty(price)) {
            price = "30";
        }
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean isCheckIsBuy() {
        return isCheckIsBuy;
    }

    public void setCheckIsBuy(boolean checkIsBuy) {
        isCheckIsBuy = checkIsBuy;
    }

    public String getPath() {
        return path;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @ToOne
    private AuthorBean author;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1735917991)
    private transient CourseEntryDao myDao;

    @Generated(hash = 475250969)
    public CourseEntry(String name, String courseId, String url, String picture, Long id, String authorName, String detail, String title, String path, long fileSize,
            int watchCount, boolean isCheckIsBuy, String price, boolean isBuy, int status, int tag) {
        this.name = name;
        this.courseId = courseId;
        this.url = url;
        this.picture = picture;
        this.id = id;
        this.authorName = authorName;
        this.detail = detail;
        this.title = title;
        this.path = path;
        this.fileSize = fileSize;
        this.watchCount = watchCount;
        this.isCheckIsBuy = isCheckIsBuy;
        this.price = price;
        this.isBuy = isBuy;
        this.status = status;
        this.tag = tag;
    }

    @Generated(hash = 2046257289)
    public CourseEntry() {
    }

    @Generated(hash = 1826121774)
    private transient boolean author__refreshed;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }

    public static void insert(String title, String url, String name, String courseId, Long id, String path, String picture, long fileSize) {
        CourseEntry musicEntry = new CourseEntry();
        musicEntry.setUrl(url);
        musicEntry.setName(name);
        musicEntry.setId(id);
        musicEntry.setCourseId(courseId);
        musicEntry.setPath(path);
        musicEntry.setPicture(picture);
        musicEntry.setTitle(title);
        musicEntry.setFileSize(fileSize);
        DbCore.getDaoSession().getCourseEntryDao().insertOrReplace(musicEntry);
    }

    public static void insert(String title, String url, String authorName, String detail, String name,
                              String courseId, Long id, String path, String picture, long fileSize) {
        CourseEntry load = DbCore.getDaoSession().getCourseEntryDao().load(id);
        if (load != null) return;
        CourseEntry musicEntry = new CourseEntry();
        musicEntry.setUrl(url);
        musicEntry.setName(name);
        musicEntry.setId(Long.parseLong(courseId));
        musicEntry.setAuthorName(authorName);
        musicEntry.setDetail(detail);
        musicEntry.setCourseId(courseId);
        musicEntry.setPath(path);
        musicEntry.setPicture(picture);
        musicEntry.setTitle(title);
        musicEntry.setFileSize(fileSize);
        DbCore.getDaoSession().getCourseEntryDao().insertOrReplace(musicEntry);
    }

    @Override
    public String toString() {
        return "CourseEntry{" +
                "name='" + name + '\'' +
                ", courseId='" + courseId + '\'' +
                ", url='" + url + '\'' +
                ", picture='" + picture + '\'' +
                ", id=" + id +
                ", authorName='" + authorName + '\'' +
                ", detail='" + detail + '\'' +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", fileSize=" + fileSize +
                ", watchCount=" + watchCount +
                ", isCheckIsBuy=" + isCheckIsBuy +
                ", price='" + price + '\'' +
                ", getIsBuy=" + isBuy +
                ", author=" + author +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                ", author__refreshed=" + author__refreshed +
                '}';
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1686156312)
    public AuthorBean getAuthor() {
        if (author != null || !author__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AuthorBeanDao targetDao = daoSession.getAuthorBeanDao();
            targetDao.refresh(author);
            author__refreshed = true;
        }
        return author;
    }

    /**
     * To-one relationship, returned entity is not refreshed and may carry only the PK property.
     */
    @Generated(hash = 1091873949)
    public AuthorBean peakAuthor() {
        return author;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1168444064)
    public void setAuthor(AuthorBean author) {
        synchronized (this) {
            this.author = author;
            author__refreshed = true;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.courseId);
        dest.writeString(this.url);
        dest.writeString(this.picture);
        dest.writeValue(this.id);
        dest.writeString(this.authorName);
        dest.writeString(this.detail);
        dest.writeString(this.title);
        dest.writeString(this.path);
        dest.writeLong(this.fileSize);
        dest.writeInt(this.watchCount);
        dest.writeByte(this.isCheckIsBuy ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isBuy ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.author, flags);
    }

    public boolean getIsCheckIsBuy() {
        return this.isCheckIsBuy;
    }

    public void setIsCheckIsBuy(boolean isCheckIsBuy) {
        this.isCheckIsBuy = isCheckIsBuy;
    }

    public boolean getIsBuy() {
        return this.isBuy;
    }

    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1911087774)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCourseEntryDao() : null;
    }

    protected CourseEntry(Parcel in) {
        this.name = in.readString();
        this.courseId = in.readString();
        this.url = in.readString();
        this.picture = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.authorName = in.readString();
        this.detail = in.readString();
        this.title = in.readString();
        this.path = in.readString();
        this.fileSize = in.readLong();
        this.watchCount = in.readInt();
        this.isCheckIsBuy = in.readByte() != 0;
        this.isBuy = in.readByte() != 0;
        this.author = in.readParcelable(AuthorBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CourseEntry> CREATOR = new Parcelable.Creator<CourseEntry>() {
        @Override
        public CourseEntry createFromParcel(Parcel source) {
            return new CourseEntry(source);
        }

        @Override
        public CourseEntry[] newArray(int size) {
            return new CourseEntry[size];
        }
    };
}
