package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2018/7/23.
 */

public class CollectZLItemBean extends Result{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
    public static class DataBean {


        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        /**
         * picture : https://www.dgli.net/resource/images/subscription/course_banner_hu5.png
         * subscriptionTrList : [{"progress":"0","courseTitle":"开篇介绍","targetId":80,"type":1},{"progress":"0","courseTitle":"日常生活练习系列之饮食静观一","targetId":81,"type":1},{"progress":"0","courseTitle":"正念瑜珈伸展系列之立式正念瑜珈一","targetId":83,"type":1}]
         * title : 正念减压自我练习系列视频课
         * descript :         正念...
         * subscriptionId : 3
         */
        private String duration;
        private String picture;
        private String title;
        private String descript;
        private int subscriptionId;
        private List<SubscriptionTrListBean> subscriptionTrList;

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

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public int getSubscriptionId() {
            return subscriptionId;
        }

        public void setSubscriptionId(int subscriptionId) {
            this.subscriptionId = subscriptionId;
        }

        public List<SubscriptionTrListBean> getSubscriptionTrList() {
            return subscriptionTrList;
        }

        public void setSubscriptionTrList(List<SubscriptionTrListBean> subscriptionTrList) {
            this.subscriptionTrList = subscriptionTrList;
        }

        public static class SubscriptionTrListBean {
            /**
             * progress : 0
             * courseTitle : 开篇介绍
             * targetId : 80
             * type : 1
             */

            private String progress;
            private String courseTitle;
            private int targetId;
            private int type;

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            private String duration;

            public String getProgress() {
                return progress;
            }

            public void setProgress(String progress) {
                this.progress = progress;
            }

            public String getCourseTitle() {
                return courseTitle;
            }

            public void setCourseTitle(String courseTitle) {
                this.courseTitle = courseTitle;
            }

            public int getTargetId() {
                return targetId;
            }

            public void setTargetId(int targetId) {
                this.targetId = targetId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
