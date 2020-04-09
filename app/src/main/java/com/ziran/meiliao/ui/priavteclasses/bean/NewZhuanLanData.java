package com.ziran.meiliao.ui.priavteclasses.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2019/1/31.
 */

public class NewZhuanLanData extends Result{
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {


        /**
         * subscriptionNum : 3
         * picture : https://www.dgli.net/resource/images/subscription/course_banner_li.png
         * detail : 这是第一段测试文字，本来我想跟你说很多很多话，但是一切尽在不言中
         * title : 测试专栏1
         * tagName : 热门
         * teacher : 测试作者1
         * tagId : 1
         * subscriptionId : 1
         */
        private boolean isBuy;



        private String htmlLink;
        private int subscriptionNum;
        private String picture;
        private String detail;
        private String title;
        private String tagName;
        private String teacher;
        private String tagId;
        private int subscriptionId;

        public String getHtmlLink() {
            return htmlLink;
        }

        public void setHtmlLink(String htmlLink) {
            this.htmlLink = htmlLink;
        }

        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
        }

        public int getSubscriptionNum() {
            return subscriptionNum;
        }

        public void setSubscriptionNum(int subscriptionNum) {
            this.subscriptionNum = subscriptionNum;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getTagId() {
            return tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }

        public int getSubscriptionId() {
            return subscriptionId;
        }

        public void setSubscriptionId(int subscriptionId) {
            this.subscriptionId = subscriptionId;
        }
    }
}
