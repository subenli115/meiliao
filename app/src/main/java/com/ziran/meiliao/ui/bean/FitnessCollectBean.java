package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class FitnessCollectBean extends Result {

    /**
     * resultCode : 10
     * resultMsg : 您已经收藏该课程，无需重复收藏
     * data : {}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

    }
}
