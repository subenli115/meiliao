package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */

public class SJKHistoryBean extends Result {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * picture : null
         * id : 1
         * title : 七天睡眠治疗
         * price : 0.01
         * watchCount : 25
         */

        private String  picture;
        private int id;
        private String title;
        private String  vip;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public int getWatchCount() {
            return watchCount;
        }

        public void setWatchCount(int watchCount) {
            this.watchCount = watchCount;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "picture='" + picture + '\'' +
                    ", id=" + id +
                    ", title='" + title + '\'' +
                    ", vip=" + vip +
                    ", watchCount=" + watchCount +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SJKHistoryBean{" +
                "data=" + data +
                '}';
    }
}
