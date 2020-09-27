package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class NewsNoticeBean extends Result {


    /**
     * data : {"records":[{"id":40,"triggerUserId":"e7b683451ac8496af31ba7775f450df9","noticeUserId":"c4beb6acb3635a3210536ae0a292638b","noticeContent":"关注了你","type":"5","status":"0","createTime":"2020-08-05 10:53:33","number":null,"realName":null,"avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e59c2c460b6d4b5b909fd9fab0c2ced4.png","nickname":"Joanna","offline":0,"isReal":"1"}],"total":1,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * records : [{"id":40,"triggerUserId":"e7b683451ac8496af31ba7775f450df9","noticeUserId":"c4beb6acb3635a3210536ae0a292638b","noticeContent":"关注了你","type":"5","status":"0","createTime":"2020-08-05 10:53:33","number":null,"realName":null,"avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e59c2c460b6d4b5b909fd9fab0c2ced4.png","nickname":"Joanna","offline":0,"isReal":"1"}]
         * total : 1
         * size : 10
         * current : 1
         * orders : []
         * hitCount : false
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean hitCount;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;
        private List<?> orders;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isHitCount() {
            return hitCount;
        }

        public void setHitCount(boolean hitCount) {
            this.hitCount = hitCount;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class RecordsBean {
            /**
             * id : 40
             * triggerUserId : e7b683451ac8496af31ba7775f450df9
             * noticeUserId : c4beb6acb3635a3210536ae0a292638b
             * noticeContent : 关注了你
             * type : 5
             * status : 0
             * createTime : 2020-08-05 10:53:33
             * number : null
             * realName : null
             * avatar : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e59c2c460b6d4b5b909fd9fab0c2ced4.png
             * nickname : Joanna
             * offline : 0
             * isReal : 1
             */

            private int id;
            private String triggerUserId;
            private String noticeUserId;
            private String noticeContent;
            private String type;
            @SerializedName("status")
            private String statusX;
            private String createTime;
            private Object number;
            private Object realName;
            private String avatar;
            private String nickname;
            private int offline;
            private String isReal;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTriggerUserId() {
                return triggerUserId;
            }

            public void setTriggerUserId(String triggerUserId) {
                this.triggerUserId = triggerUserId;
            }

            public String getNoticeUserId() {
                return noticeUserId;
            }

            public void setNoticeUserId(String noticeUserId) {
                this.noticeUserId = noticeUserId;
            }

            public String getNoticeContent() {
                return noticeContent;
            }

            public void setNoticeContent(String noticeContent) {
                this.noticeContent = noticeContent;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getStatusX() {
                return statusX;
            }

            public void setStatusX(String statusX) {
                this.statusX = statusX;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getNumber() {
                return number;
            }

            public void setNumber(Object number) {
                this.number = number;
            }

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
                this.realName = realName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getOffline() {
                return offline;
            }

            public void setOffline(int offline) {
                this.offline = offline;
            }

            public String getIsReal() {
                return isReal;
            }

            public void setIsReal(String isReal) {
                this.isReal = isReal;
            }
        }
    }
}
