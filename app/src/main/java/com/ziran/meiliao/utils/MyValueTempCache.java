package com.ziran.meiliao.utils;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.entry.MusicEntry;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     缓存对象
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */
public class MyValueTempCache {
    public static Map<String, Object> caches;
    public static MyValueTempCache instance;
    public static int selectPosition = 0;

    private MyValueTempCache() {
        caches = new HashMap<>();
    }

    public static MyValueTempCache get() {
        if (instance == null) {
            synchronized (MyValueTempCache.class) {
                if (instance == null) {
                    instance = new MyValueTempCache();
                }
            }
        }
        return instance;
    }

    private static int totalSize = -1;

    public <T> void put(String key, T value) {
        if (value instanceof Collection) {
            totalSize = ((Collection) value).size();
        }
        caches.remove(key);
        caches.put(key, value);
    }


    public <T> T get(String key) {
        if (caches.containsKey(key)) {
            return (T) caches.get(key);
        }
        return null;
    }

    public <T> T get(String key, int position) {
        if (caches.containsKey(key)) {
            List<T> list = (List<T>) caches.get(key);
            if (EmptyUtils.isNotEmpty(list)) {
                return list.get(position);
            }
        }
        return null;
    }

    public <T> T reomve(String key) {
        return (T) caches.remove(key);
    }


    public static int getSelectPosition(List<MusicEntry> data) {
        if (mCurrentPlayMusicEntry == null || EmptyUtils.isEmpty(data)) return 0;
        for (int i = 0; i < data.size(); i++) {
            if (mCurrentPlayMusicEntry.getMusicId() == data.get(i).getMusicId()) {
                return i;
            }
        }
        return selectPosition;
    }


    public static MusicEntry getCurrentData() {
        return mCurrentPlayMusicEntry;
    }

    private static MusicEntry mCurrentPlayMusicEntry;

    public static int getTagId() {
        return tagId;
    }

    public static void setTagId(int tagId) {
        MyValueTempCache.tagId = tagId;
    }

    public static int tagId;
    public static void setCurrentPlayMusicEntry(MusicEntry musicEntry) {
        MyValueTempCache.mCurrentPlayMusicEntry = musicEntry;
    }


    public void clear() {
        caches.clear();
        selectPosition = -1;
    }

}
