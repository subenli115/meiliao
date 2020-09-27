package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class NoticeNumsBean extends Result {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : null
         * triggerUserId : null
         * noticeUserId : null
         * noticeContent : null
         * type : 4
         * status : null
         * createTime : 2020-08-05 11:50:54
         * number : 1
         * realName : null
         * avatar : null
         * nickname : null
         * offline : null
         * isReal : null
         */

        private Object id;
        private Object triggerUserId;
        private Object noticeUserId;
        private Object noticeContent;
        private String type;
        @SerializedName("status")
        private Object statusX;
        private String createTime;
        private int number;
        private Object realName;
        private Object avatar;
        private Object nickname;
        private Object offline;
        private Object isReal;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getTriggerUserId() {
            return triggerUserId;
        }

        public void setTriggerUserId(Object triggerUserId) {
            this.triggerUserId = triggerUserId;
        }

        public Object getNoticeUserId() {
            return noticeUserId;
        }

        public void setNoticeUserId(Object noticeUserId) {
            this.noticeUserId = noticeUserId;
        }

        public Object getNoticeContent() {
            return noticeContent;
        }

        public void setNoticeContent(Object noticeContent) {
            this.noticeContent = noticeContent;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getStatusX() {
            return statusX;
        }

        public void setStatusX(Object statusX) {
            this.statusX = statusX;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public Object getRealName() {
            return realName;
        }

        public void setRealName(Object realName) {
            this.realName = realName;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getOffline() {
            return offline;
        }

        public void setOffline(Object offline) {
            this.offline = offline;
        }

        public Object getIsReal() {
            return isReal;
        }

        public void setIsReal(Object isReal) {
            this.isReal = isReal;
        }
    }
}
