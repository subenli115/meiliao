package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */

public class SJKActivityBean extends Result {


    private List<ActisData> data;

    public List<ActisData> getData() {
        return data;
    }

    public void setData(List<ActisData> data) {
        this.data = data;
    }

}
