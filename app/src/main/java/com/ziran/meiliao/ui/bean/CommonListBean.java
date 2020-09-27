package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class CommonListBean extends Result {


    /**
     * data : {"records":[{"id":2,"userId":"cb9143bfbd79c420a1859ceb039e982e","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":null,"avatar":null,"realName":null,"isReal":null,"createTime":"2020-07-28 10:45:10"},{"id":9,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":10,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":11,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":12,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":13,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":14,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":15,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":16,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":17,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"}],"total":49,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":5}
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
         * records : [{"id":2,"userId":"cb9143bfbd79c420a1859ceb039e982e","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":null,"avatar":null,"realName":null,"isReal":null,"createTime":"2020-07-28 10:45:10"},{"id":9,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":10,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":11,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":12,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":13,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":14,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":15,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":16,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"},{"id":17,"userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","visitorId":"c4beb6acb3635a3210536ae0a292638b","nickname":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-07-28 10:45:10"}]
         * total : 49
         * size : 10
         * current : 1
         * orders : []
         * hitCount : false
         * searchCount : true
         * pages : 5
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
             * id : 2
             * userId : cb9143bfbd79c420a1859ceb039e982e
             * visitorId : c4beb6acb3635a3210536ae0a292638b
             * nickname : null
             * avatar : null
             * realName : null
             * isReal : null
             * createTime : 2020-07-28 10:45:10
             */

            private int id;
            private String userId;
            private String visitorId;
            private Object nickname;
            private Object avatar;
            private Object realName;
            private Object isReal;
            private String createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getVisitorId() {
                return visitorId;
            }

            public void setVisitorId(String visitorId) {
                this.visitorId = visitorId;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
                this.avatar = avatar;
            }

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
                this.realName = realName;
            }

            public Object getIsReal() {
                return isReal;
            }

            public void setIsReal(Object isReal) {
                this.isReal = isReal;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
