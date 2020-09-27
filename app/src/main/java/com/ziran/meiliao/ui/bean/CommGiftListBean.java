package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class CommGiftListBean extends Result {


    /**
     * data : {"records":[{"id":"55d8cd5980503f0dfd83bf593a78d6e3","number":null,"giveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","giveUserName":"沙漠认识1","giftId":18,"giftName":"心动","price":5000,"giftImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b508223d27ac418287e324079a334970.png","receiveUserId":"c4beb6acb3635a3210536ae0a292638b","receivUserName":null,"type":null,"objectId":"","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-08-11 16:43:58"},{"id":"08a65f199f2f3cd8abacd627fda9a780","number":null,"giveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","giveUserName":"沙漠认识1","giftId":20,"giftName":"赞","price":1000,"giftImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/7ba1c0056048431f894a40c01cb8e08f.png","receiveUserId":"c4beb6acb3635a3210536ae0a292638b","receivUserName":null,"type":null,"objectId":"","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-08-11 16:43:55"},{"id":"667c0a37d0433fc70a71cfcccaf09c63","number":null,"giveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","giveUserName":"沙漠认识1","giftId":13,"giftName":"比心","price":1000,"giftImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b1b57883f38547309ba2d8d40d650f8e.png","receiveUserId":"c4beb6acb3635a3210536ae0a292638b","receivUserName":null,"type":null,"objectId":"","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-08-11 16:43:50"},{"id":"427b57ef62530575e184025d37348290","number":null,"giveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","giveUserName":"沙漠认识1","giftId":16,"giftName":"怦然心动","price":52000,"giftImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/212a1b0217dd4b03a24c5d5ef4813a64.png","receiveUserId":"c4beb6acb3635a3210536ae0a292638b","receivUserName":null,"type":null,"objectId":"c4beb6acb3635a3210536ae0a292638b","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-08-11 16:43:46"},{"id":"03a4028b237eca1e542f5580775e49bf","number":null,"giveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","giveUserName":"沙漠认识1","giftId":16,"giftName":"怦然心动","price":52000,"giftImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/212a1b0217dd4b03a24c5d5ef4813a64.png","receiveUserId":"c4beb6acb3635a3210536ae0a292638b","receivUserName":null,"type":null,"objectId":"c4beb6acb3635a3210536ae0a292638b","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-08-11 16:43:42"}],"total":5,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"id":"55d8cd5980503f0dfd83bf593a78d6e3","number":null,"giveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","giveUserName":"沙漠认识1","giftId":18,"giftName":"心动","price":5000,"giftImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b508223d27ac418287e324079a334970.png","receiveUserId":"c4beb6acb3635a3210536ae0a292638b","receivUserName":null,"type":null,"objectId":"","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-08-11 16:43:58"},{"id":"08a65f199f2f3cd8abacd627fda9a780","number":null,"giveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","giveUserName":"沙漠认识1","giftId":20,"giftName":"赞","price":1000,"giftImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/7ba1c0056048431f894a40c01cb8e08f.png","receiveUserId":"c4beb6acb3635a3210536ae0a292638b","receivUserName":null,"type":null,"objectId":"","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-08-11 16:43:55"},{"id":"667c0a37d0433fc70a71cfcccaf09c63","number":null,"giveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","giveUserName":"沙漠认识1","giftId":13,"giftName":"比心","price":1000,"giftImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b1b57883f38547309ba2d8d40d650f8e.png","receiveUserId":"c4beb6acb3635a3210536ae0a292638b","receivUserName":null,"type":null,"objectId":"","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-08-11 16:43:50"},{"id":"427b57ef62530575e184025d37348290","number":null,"giveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","giveUserName":"沙漠认识1","giftId":16,"giftName":"怦然心动","price":52000,"giftImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/212a1b0217dd4b03a24c5d5ef4813a64.png","receiveUserId":"c4beb6acb3635a3210536ae0a292638b","receivUserName":null,"type":null,"objectId":"c4beb6acb3635a3210536ae0a292638b","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-08-11 16:43:46"},{"id":"03a4028b237eca1e542f5580775e49bf","number":null,"giveUserId":"aa66cd8fd4fe10d9021ba0869b1c0c66","giveUserName":"沙漠认识1","giftId":16,"giftName":"怦然心动","price":52000,"giftImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/212a1b0217dd4b03a24c5d5ef4813a64.png","receiveUserId":"c4beb6acb3635a3210536ae0a292638b","receivUserName":null,"type":null,"objectId":"c4beb6acb3635a3210536ae0a292638b","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png","realName":"谭谭","isReal":"1","createTime":"2020-08-11 16:43:42"}]
         * total : 5
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
             * id : 55d8cd5980503f0dfd83bf593a78d6e3
             * number : null
             * giveUserId : aa66cd8fd4fe10d9021ba0869b1c0c66
             * giveUserName : 沙漠认识1
             * giftId : 18
             * giftName : 心动
             * price : 5000.0
             * giftImages : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b508223d27ac418287e324079a334970.png
             * receiveUserId : c4beb6acb3635a3210536ae0a292638b
             * receivUserName : null
             * type : null
             * objectId :
             * avatar : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/eeb5d60b33ab4a98b8720bdcb28bbb5d.png
             * realName : 谭谭
             * isReal : 1
             * createTime : 2020-08-11 16:43:58
             */

            private String id;
            private Object number;
            private String giveUserId;
            private String giveUserName;
            private int giftId;
            private String giftName;
            private double price;
            private String giftImages;
            private String receiveUserId;
            private Object receivUserName;
            private Object type;
            private String objectId;
            private String avatar;
            private String realName;
            private String isReal;
            private String createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getNumber() {
                return number;
            }

            public void setNumber(Object number) {
                this.number = number;
            }

            public String getGiveUserId() {
                return giveUserId;
            }

            public void setGiveUserId(String giveUserId) {
                this.giveUserId = giveUserId;
            }

            public String getGiveUserName() {
                return giveUserName;
            }

            public void setGiveUserName(String giveUserName) {
                this.giveUserName = giveUserName;
            }

            public int getGiftId() {
                return giftId;
            }

            public void setGiftId(int giftId) {
                this.giftId = giftId;
            }

            public String getGiftName() {
                return giftName;
            }

            public void setGiftName(String giftName) {
                this.giftName = giftName;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getGiftImages() {
                return giftImages;
            }

            public void setGiftImages(String giftImages) {
                this.giftImages = giftImages;
            }

            public String getReceiveUserId() {
                return receiveUserId;
            }

            public void setReceiveUserId(String receiveUserId) {
                this.receiveUserId = receiveUserId;
            }

            public Object getReceivUserName() {
                return receivUserName;
            }

            public void setReceivUserName(Object receivUserName) {
                this.receivUserName = receivUserName;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getIsReal() {
                return isReal;
            }

            public void setIsReal(String isReal) {
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
