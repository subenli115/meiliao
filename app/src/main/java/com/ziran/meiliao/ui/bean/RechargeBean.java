package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class RechargeBean extends Result {

    /**
     * data : {"records":[{"id":21,"name":"6000金币","price":6,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"1","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":22,"name":"18000金币","price":18,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"2","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":23,"name":"30000金币","price":30,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"3","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":24,"name":"88000金币","price":88,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"4","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":25,"name":"198000金币","price":198,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"5","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":26,"name":"488000金币","price":488,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"6","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":27,"name":"998000金币","price":998,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"7","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":28,"name":"4998000金币","price":4998,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"8","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":29,"name":"9998000金币","price":9998,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"9","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"}],"total":9,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"id":21,"name":"6000金币","price":6,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"1","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":22,"name":"18000金币","price":18,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"2","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":23,"name":"30000金币","price":30,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"3","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":24,"name":"88000金币","price":88,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"4","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":25,"name":"198000金币","price":198,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"5","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":26,"name":"488000金币","price":488,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"6","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":27,"name":"998000金币","price":998,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"7","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":28,"name":"4998000金币","price":4998,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"8","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"},{"id":29,"name":"9998000金币","price":9998,"images":"http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png","type":"2","status":"0","reserve1":"","reserve2":"9","reserve3":"0","reserve4":"","createTime":"2020-05-21 15:13:04","updateTime":"2020-05-21 15:13:04"}]
         * total : 9
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

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            /**
             * id : 21
             * name : 6000金币
             * price : 6.0
             * images : http://meiliao.ziran518.com/e012441c59f1448da77b7d2382625828.png
             * type : 2
             * status : 0
             * reserve1 :
             * reserve2 : 1
             * reserve3 : 0
             * reserve4 :
             * createTime : 2020-05-21 15:13:04
             * updateTime : 2020-05-21 15:13:04
             */
            private boolean isSelect;
            private int id;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            private String userId;
            private String name;
            private double price;
            private String images;
            private String type;
            @SerializedName("status")
            private String statusX;
            private String reserve1;
            private String reserve2;
            private String reserve3;
            private String reserve4;
            private String createTime;
            private String updateTime;

            public String getAnimationImages() {
                return animationImages;
            }

            public void setAnimationImages(String animationImages) {
                this.animationImages = animationImages;
            }

            private String animationImages;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
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

            public String getReserve1() {
                return reserve1;
            }

            public void setReserve1(String reserve1) {
                this.reserve1 = reserve1;
            }

            public String getReserve2() {
                return reserve2;
            }

            public void setReserve2(String reserve2) {
                this.reserve2 = reserve2;
            }

            public String getReserve3() {
                return reserve3;
            }

            public void setReserve3(String reserve3) {
                this.reserve3 = reserve3;
            }

            public String getReserve4() {
                return reserve4;
            }

            public void setReserve4(String reserve4) {
                this.reserve4 = reserve4;
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
        }
    }
}
