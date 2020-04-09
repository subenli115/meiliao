package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2019/1/31.
 */

public class NewBootCampBean extends Result{
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * startTime : 2019-01-31
         * picture : https://www.dgli.net/resource/images/practiceActivity/1.png
         * id : 1
         * detail : https://dgli.net/page/static/practiceBooks/MBSR/index.html
         * status : 1
         * tagName : 热门
         * name : MBSR八周练习
         * tagId : 1
         * booksId : 1
         */

        private String startTime;
        private String picture;
        private int id;
        private String detail;
        private int status;
        private String tagName;
        private String name;
        private int tagId;
        private int booksId;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

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

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTagId() {
            return tagId;
        }

        public void setTagId(int tagId) {
            this.tagId = tagId;
        }

        public int getBooksId() {
            return booksId;
        }

        public void setBooksId(int booksId) {
            this.booksId = booksId;
        }
    }
}
