package com.ziran.meiliao.entry;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2018/8/4.
 */

public class CourseBean extends Result{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "MeCourseBean{" +
                "data=" + data + " resultCode = " + resultCode +
                '}';
    }

    public static class DataBean {



        /**
         * picture : http://www.dgli.net/resource/images/subscription/course_banner_Jeffrey.png
         * title : 艾瑞克森催眠课程
         * descript : 五分钟治疗秘笈是由艾瑞克森基金...
         * subscriptionId : 18
         */
        private  boolean  isBuy;
        private  String  htmlLink;
        private String picture;
        private String title;
        private String descript;
        private int subscriptionId;

        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
        }
        public String getHtmlLink() {
            return htmlLink;
        }

        public void setHtmlLink(String htmlLink) {
            this.htmlLink = htmlLink;
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
    }
}
