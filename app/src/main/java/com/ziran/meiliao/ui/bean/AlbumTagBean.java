package com.ziran.meiliao.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/2/13.
 */

public class AlbumTagBean {

    List<AlbumClassifyBean.DataBean.TagListBean.NextTagListBean> nextTagList;

    public List<AlbumClassifyBean.DataBean.TagListBean.NextTagListBean> getNextTagList() {
        return nextTagList;
    }

    public void setNextTagList(List<AlbumClassifyBean.DataBean.TagListBean.NextTagListBean> nextTagList) {
        this.nextTagList = nextTagList;
    }
}
