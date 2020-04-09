package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * 专辑背景音 2018/12/25.
 */

public class AlbumBgBean extends Result{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * picture : https://www.dgli.net/resource/images/album/1.png
         * id : 1
         * name : 背景音1
         * url : http://ojlzx3sl8.bkt.clouddn.com/zhaoling1.mp3
         */

        private String picture;
        private int id;
        private String name;
        private String url;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
