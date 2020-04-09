package com.ziran.meiliao.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/1/29.
 */

public class CommAlbumMoreBean {
    public List<AlbumMoreBean.DataBean.AlbumListBean.DetailListBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<AlbumMoreBean.DataBean.AlbumListBean.DetailListBean> detailList) {
        this.detailList = detailList;
    }

    List<AlbumMoreBean.DataBean.AlbumListBean.DetailListBean> detailList;

}
