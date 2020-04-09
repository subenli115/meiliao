package com.ziran.meiliao.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/1/15.
 */

public class HotAlbumBean {

    public List<NewHomeDataBean.DataBean.HotAlbumBean> getHotAlbum() {
        return hotAlbum;
    }

    public void setHotAlbum(List<NewHomeDataBean.DataBean.HotAlbumBean> hotAlbum) {
        this.hotAlbum = hotAlbum;
    }

    List<NewHomeDataBean.DataBean.HotAlbumBean> hotAlbum;
}
