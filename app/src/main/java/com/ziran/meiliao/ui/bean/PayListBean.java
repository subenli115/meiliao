package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class PayListBean extends Result {


    /**
     * records : [{"id":1,"ios":0,"payName":"支付宝H5支付","status":0,"payType":"1","createTime":"2020-06-05 12:28:34","updateTime":"2020-06-05 12:28:33","android":0},{"id":2,"ios":0,"payName":"微信H5支付","status":0,"payType":"2","createTime":"2020-06-05 12:28:26","updateTime":"2020-06-05 12:28:27","android":0}]
     * total : 2
     * size : 10
     * current : 1
     * orders : []
     * hitCount : false
     * searchCount : true
     * pages : 1
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
         * records : [{"id":1,"ios":0,"payName":"支付宝H5支付","status":0,"payType":"1","createTime":"2020-06-05 12:28:34","updateTime":"2020-06-05 12:28:33","android":0},{"id":2,"ios":0,"payName":"微信H5支付","status":0,"payType":"2","createTime":"2020-06-05 12:28:26","updateTime":"2020-06-05 12:28:27","android":0}]
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
             * id : 1
             * ios : 0
             * payName : 支付宝H5支付
             * status : 0
             * payType : 1
             * createTime : 2020-06-05 12:28:34
             * updateTime : 2020-06-05 12:28:33
             * android : 0
             */

            private int id;
            private int ios;
            private String payName;
            private int status;
            private String payType;
            private String createTime;
            private String updateTime;
            private int android;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIos() {
                return ios;
            }

            public void setIos(int ios) {
                this.ios = ios;
            }

            public String getPayName() {
                return payName;
            }

            public void setPayName(String payName) {
                this.payName = payName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getPayType() {
                return payType;
            }

            public void setPayType(String payType) {
                this.payType = payType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getAndroid() {
                return android;
            }

            public void setAndroid(int android) {
                this.android = android;
            }
        }
    }
}
