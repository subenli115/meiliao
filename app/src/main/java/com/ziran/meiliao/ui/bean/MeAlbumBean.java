package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class MeAlbumBean extends Result {

    private List<AlbumBean> data;

    public List<AlbumBean> getData() {
        return data;
    }

    public void setData(List<AlbumBean> data) {
        this.data = data;
    }

}
