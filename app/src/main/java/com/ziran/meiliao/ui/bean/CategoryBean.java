package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/19.
 */

public class CategoryBean extends Result {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * vip : 限时
         * title : 正念创造力
         * albumId : 1
         * name : 彭凯茵@正念
         * descript : 正念减压课程导师
         * listenCount : 151
         */

        private String picture;
        private String vip;
        private String title;
        private int albumId;
        private String name;
        private String descript;
        private int listenCount;
        private String zw;

        private String fbt;

        public String getZw() {
            return zw;
        }

        public void setZw(String zw) {
            this.zw = zw;
        }

        public String getFbt() {
            return fbt;
        }

        public void setFbt(String fbt) {
            this.fbt = fbt;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getAlbumId() {
            return albumId;
        }

        public void setAlbumId(int albumId) {
            this.albumId = albumId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public int getListenCount() {
            return listenCount;
        }

        public void setListenCount(int listenCount) {
            this.listenCount = listenCount;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "picture='" + picture + '\'' +
                    ", vip='" + vip + '\'' +
                    ", title='" + title + '\'' +
                    ", albumId=" + albumId +
                    ", name='" + name + '\'' +
                    ", descript='" + descript + '\'' +
                    ", listenCount=" + listenCount +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                "data=" + data +
                '}';
    }
}
