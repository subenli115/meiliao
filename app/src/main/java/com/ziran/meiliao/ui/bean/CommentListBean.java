package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class CommentListBean extends Result {


    /**
     * data : {"records":[{"id":"222","spaceId":"0156e77f0b0252e52838520c10907c14","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8b7a7e1b5b28407b837a560397d403d5.png","nickname":"Gooocar","commentUserId":"b2f7918eda4f8d5320b0ca6fe5ad2a89","receiveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","content":"1111111","clickNum":0,"delFlag":"0","type":"0","createTime":"2020-08-18 17:13:48"},{"id":"111","spaceId":"0156e77f0b0252e52838520c10907c14","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/9b1b8cd37f774a5fbe831ba5df30d18c.png","nickname":"小仙女","commentUserId":"b2f7918eda4f8d5320b0ca6fe5ad2a83","receiveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","content":"好烦啊好烦啊","clickNum":0,"delFlag":"0","type":"0","createTime":"2020-08-18 17:13:48"}],"total":2,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"id":"222","spaceId":"0156e77f0b0252e52838520c10907c14","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8b7a7e1b5b28407b837a560397d403d5.png","nickname":"Gooocar","commentUserId":"b2f7918eda4f8d5320b0ca6fe5ad2a89","receiveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","content":"1111111","clickNum":0,"delFlag":"0","type":"0","createTime":"2020-08-18 17:13:48"},{"id":"111","spaceId":"0156e77f0b0252e52838520c10907c14","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/9b1b8cd37f774a5fbe831ba5df30d18c.png","nickname":"小仙女","commentUserId":"b2f7918eda4f8d5320b0ca6fe5ad2a83","receiveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","content":"好烦啊好烦啊","clickNum":0,"delFlag":"0","type":"0","createTime":"2020-08-18 17:13:48"}]
         * total : 2
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
             * id : 222
             * spaceId : 0156e77f0b0252e52838520c10907c14
             * avatar : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8b7a7e1b5b28407b837a560397d403d5.png
             * nickname : Gooocar
             * commentUserId : b2f7918eda4f8d5320b0ca6fe5ad2a89
             * receiveUserId : aa66cd8fd4fe10d9021ba0869b1c0c66
             * content : 1111111
             * clickNum : 0
             * delFlag : 0
             * type : 0
             * createTime : 2020-08-18 17:13:48
             */

            private String id;
            private String spaceId;
            private String avatar;
            private String nickname;
            private String commentUserId;
            private String receiveUserId;
            private String content;
            private int clickNum;
            private String delFlag;
            private String type;
            private String createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSpaceId() {
                return spaceId;
            }

            public void setSpaceId(String spaceId) {
                this.spaceId = spaceId;
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

            public String getCommentUserId() {
                return commentUserId;
            }

            public void setCommentUserId(String commentUserId) {
                this.commentUserId = commentUserId;
            }

            public String getReceiveUserId() {
                return receiveUserId;
            }

            public void setReceiveUserId(String receiveUserId) {
                this.receiveUserId = receiveUserId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getClickNum() {
                return clickNum;
            }

            public void setClickNum(int clickNum) {
                this.clickNum = clickNum;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
