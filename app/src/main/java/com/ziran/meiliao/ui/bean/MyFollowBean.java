package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class MyFollowBean extends Result {


    /**
     * data : {"records":[{"id":1,"userId":"86f5cd2c42549606e64815f937385b55","followUserId":"e7b683451ac8496af31ba7775f450d16","nickname":"小兔纸","followNickname":null,"avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b5fef00585f44283ad3799bd400d88a1.png","introduce":"","createTime":"2020-07-03 11:30:07"}],"total":1,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"id":1,"userId":"86f5cd2c42549606e64815f937385b55","followUserId":"e7b683451ac8496af31ba7775f450d16","nickname":"小兔纸","followNickname":null,"avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b5fef00585f44283ad3799bd400d88a1.png","introduce":"","createTime":"2020-07-03 11:30:07"}]
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
             * id : 1
             * userId : 86f5cd2c42549606e64815f937385b55
             * followUserId : e7b683451ac8496af31ba7775f450d16
             * nickname : 小兔纸
             * followNickname : null
             * avatar : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b5fef00585f44283ad3799bd400d88a1.png
             * introduce :
             * createTime : 2020-07-03 11:30:07
             */

            private int id;
            private String userId;
            private String followUserId;
            private String nickname;
            private Object followNickname;
            private String avatar;
            private String introduce;
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

            public String getFollowUserId() {
                return followUserId;
            }

            public void setFollowUserId(String followUserId) {
                this.followUserId = followUserId;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public Object getFollowNickname() {
                return followNickname;
            }

            public void setFollowNickname(Object followNickname) {
                this.followNickname = followNickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
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
