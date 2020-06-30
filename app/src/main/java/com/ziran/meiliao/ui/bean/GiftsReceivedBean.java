package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class GiftsReceivedBean extends Result {


    /**
     * data : {"records":[{"id":"309a002cf0ac1b5ec4c861a53f332172","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.6,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-30 13:04:17"},{"id":"5936a8439040b1ad4f20c57c651f1957","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":11,"giftName":"超级大火箭","price":999,"giftImages":"http://meiliao.ziran518.com/c6c0938a03834316a26c97c95d5e5173.jpg","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-30 12:59:42"},{"id":"d288f656050584a574d0f411a029592e","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:25:50"},{"id":"8645fc79d47e379fa1b04673071c0f31","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:16:46"},{"id":"3e993c59ec2334bf56636d6e690493bf","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:09:27"},{"id":"b9232cdd5653d6f97be440439ed42d8b","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:09:26"},{"id":"4342892f6afa901cc2a8a21b56c00c73","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:09:25"},{"id":"6b5aa2b0ad5c089fed3de67bfb5c0a75","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:09:25"}],"total":17,"size":8,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":3}
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
         * records : [{"id":"309a002cf0ac1b5ec4c861a53f332172","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.6,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-30 13:04:17"},{"id":"5936a8439040b1ad4f20c57c651f1957","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":11,"giftName":"超级大火箭","price":999,"giftImages":"http://meiliao.ziran518.com/c6c0938a03834316a26c97c95d5e5173.jpg","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-30 12:59:42"},{"id":"d288f656050584a574d0f411a029592e","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:25:50"},{"id":"8645fc79d47e379fa1b04673071c0f31","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:16:46"},{"id":"3e993c59ec2334bf56636d6e690493bf","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:09:27"},{"id":"b9232cdd5653d6f97be440439ed42d8b","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:09:26"},{"id":"4342892f6afa901cc2a8a21b56c00c73","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:09:25"},{"id":"6b5aa2b0ad5c089fed3de67bfb5c0a75","giveUserId":"cb9143bfbd79c420a1859ceb039e9822","giveUserName":"秦大炮","giftId":1,"giftName":"棒棒糖","price":0.1,"giftImages":"xxx","receiveUserId":"cb9143bfbd79c420a1859ceb039e982e","receivUserName":"android","createTime":"2020-04-15 10:09:25"}]
         * total : 17
         * size : 8
         * current : 1
         * orders : []
         * hitCount : false
         * searchCount : true
         * pages : 3
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
            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            /**
             * id : 309a002cf0ac1b5ec4c861a53f332172
             * giveUserId : cb9143bfbd79c420a1859ceb039e9822
             * giveUserName : 秦大炮
             * giftId : 1
             * giftName : 棒棒糖
             * price : 0.6
             * giftImages : xxx
             * receiveUserId : cb9143bfbd79c420a1859ceb039e982e
             * receivUserName : android
             * createTime : 2020-04-30 13:04:17
             */
            private String id;
            private String number;
            private String giveUserId;
            private String giveUserName;
            private int giftId;
            private String giftName;
            private double price;
            private String giftImages;
            private String receiveUserId;
            private String receivUserName;
            private String createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getReceivUserName() {
                return receivUserName;
            }

            public void setReceivUserName(String receivUserName) {
                this.receivUserName = receivUserName;
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
