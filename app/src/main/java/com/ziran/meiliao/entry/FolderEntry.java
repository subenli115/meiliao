/**
 * Copyright (c) www.longdw.com
 */
package com.ziran.meiliao.entry;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.db.DbCore;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 	文件夹bean
 */
@Entity
public class FolderEntry implements Parcelable {

	@Transient
	public static String KEY_FOLDER_NAME = "folder_name";
	@Transient
	public static String KEY_FOLDER_PATH = "folder_path";

	@Id
	private Long id;
	public String folder_name;	//文件夹名称
	public String folder_path;//文件夹路径

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Bundle bundle = new Bundle();
		bundle.putString(KEY_FOLDER_NAME, folder_name);
		bundle.putString(KEY_FOLDER_PATH, folder_path);
		dest.writeBundle(bundle);
	}

	public Long getId() {
					return this.id;
	}

	public void setId(Long id) {
					this.id = id;
	}

	public String getFolder_name() {
					return this.folder_name;
	}

	public void setFolder_name(String folder_name) {
					this.folder_name = folder_name;
	}

	public String getFolder_path() {
					return this.folder_path;
	}

	public void setFolder_path(String folder_path) {
					this.folder_path = folder_path;
	}
	
	// 用来创建自定义的Parcelable的对象
	public static Creator<FolderEntry> CREATOR = new Creator<FolderEntry>() {

		@Override
		public FolderEntry createFromParcel(Parcel source) {
			FolderEntry info = new FolderEntry();
			Bundle bundle = source.readBundle();
			info.folder_name = bundle.getString(KEY_FOLDER_NAME);
			info.folder_path = bundle.getString(KEY_FOLDER_PATH);
			return info;
		}

		@Override
		public FolderEntry[] newArray(int size) {
			return new FolderEntry[size];
		}
	};

	@Generated(hash = 129366500)
	public FolderEntry(Long id, String folder_name, String folder_path) {
					this.id = id;
					this.folder_name = folder_name;
					this.folder_path = folder_path;
	}

	@Generated(hash = 596331942)
	public FolderEntry() {
	}


	public static void saveFolderInfo(List<FolderEntry> list) {
		if (EmptyUtils.isNotEmpty(list)){
			DbCore.getDaoSession().getFolderEntryDao().insertInTx(list);
		}
	}

	public static List<FolderEntry> getFolderInfo() {
        List<FolderEntry> folderEntries = DbCore.getDaoSession().getFolderEntryDao().loadAll();
        
        return folderEntries;
	}

    /**
     * 数据库中是否有数据
     * @return
     */
    public static boolean hasData() {
        List<FolderEntry> folderEntries = DbCore.getDaoSession().getFolderEntryDao().loadAll();
        return EmptyUtils.isNotEmpty(folderEntries);
    }
}
