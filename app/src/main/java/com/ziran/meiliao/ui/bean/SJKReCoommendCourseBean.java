package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */

public class SJKReCoommendCourseBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * picture : 1.png
         * id : 1
         * duration : 10:30
         * title : 我是标题
         * price : 111.0
         * watchCount : 20523
         */

        private String picture;
        private int id;
        private String duration;
        private String title;
        private String price;
        private int watchCount;

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

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getWatchCount() {
            return watchCount;
        }

        public void setWatchCount(int watchCount) {
            this.watchCount = watchCount;
        }
    }
}
