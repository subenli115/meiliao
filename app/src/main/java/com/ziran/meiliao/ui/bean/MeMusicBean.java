package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.MusicEntry;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class MeMusicBean extends Result {


    private List<MusicEntry> data;

    public List<MusicEntry> getData() {
        return data;
    }

    public void setData(List<MusicEntry> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MeMusicBean{" +
                "data=" + data +
                '}';
    }
}
