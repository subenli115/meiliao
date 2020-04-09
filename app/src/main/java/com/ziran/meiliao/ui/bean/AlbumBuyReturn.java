package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2019/1/14.
 */

public class AlbumBuyReturn extends Result{
    private AlbumBuyReturn.DataBean data;

    public AlbumBuyReturn.DataBean getData() {
        return data;
    }

    public void setData(AlbumBuyReturn.DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }
}
