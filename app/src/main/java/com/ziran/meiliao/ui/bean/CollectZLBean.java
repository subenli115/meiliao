package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/15 16:07
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/15$
 * @updateDes ${TODO}
 */

public class CollectZLBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        /**
         * picture : https://www.dgli.net/resource/images/subscription/course_banner_hu.png
         * title : 正念减压自我练习系列
         * courseTitle : 日常生活练习系列 — 1. 饮食静观
         * targetId : 74
         * subscriptionId : 3
         */
        private String descript;
        private String picture;
        private String title;
        private String courseTitle;
        private String targetId;
        private String  subscriptionId;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCourseTitle() {
            return courseTitle;
        }

        public void setCourseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getSubscriptionId() {
            return subscriptionId;
        }

        public void setSubscriptionId(String subscriptionId) {
            this.subscriptionId = subscriptionId;
        }
    }
}
