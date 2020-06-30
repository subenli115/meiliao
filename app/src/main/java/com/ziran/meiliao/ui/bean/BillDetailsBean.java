package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class BillDetailsBean extends Result {

    /**
     * code : 0
     * msg :
     * data : {"records":[{"id":"ca66699ad149d1b351399a96d593a465","userId":"e6ecd50d5dca492a6154d6dc7f855359","nickname":"透明","money":50,"moneyType":"2","type":1,"remarks":"广告收益","createTime":"2020-06-23 14:53:19"},{"id":"9785c8a33ce4fca6f433ace3483fcbe8","userId":"e6ecd50d5dca492a6154d6dc7f855359","nickname":"透明","money":10,"moneyType":"2","type":2,"remarks":"聊天扣除10","createTime":"2020-06-23 14:44:47"},{"id":"b42dc72ac112e7acf8f55a5118418cba","userId":"e7b683451ac8496af31ba7775f450d10","nickname":"原兔SAMT","money":5,"moneyType":"2","type":1,"remarks":"收到与透明的聊天收益","createTime":"2020-06-23 14:44:47"},{"id":"1773b41a5721c8666afaa2569c56c1e3","userId":"e7b683451ac8496af31ba7775f450d10","nickname":"原兔SAMT","money":5,"moneyType":"2","type":1,"remarks":"收到与透明的聊天收益","createTime":"2020-06-23 14:43:34"},{"id":"a1a455d2ffa98740c54fd9f17dc57599","userId":"e6ecd50d5dca492a6154d6dc7f855359","nickname":"透明","money":10,"moneyType":"2","type":2,"remarks":"聊天扣除10","createTime":"2020-06-23 14:43:34"},{"id":"d9077c65a979551568728ce183c6f5ec","userId":"e6ecd50d5dca492a6154d6dc7f855359","nickname":"透明","money":10,"moneyType":"2","type":2,"remarks":"聊天扣除10","createTime":"2020-06-23 14:41:06"},{"id":"22d47447102c42104055087baf69d10c","userId":"b2f7918eda4f8d5320b0ca6fe5ad2a75","nickname":"被遗忘的小猪🐷","money":5,"moneyType":"2","type":1,"remarks":"收到与透明的聊天收益","createTime":"2020-06-23 14:41:06"},{"id":"ff8c8a3c25171a41682a02d22cf0fe7f","userId":"86f5cd2c42549606e64815f937385b55","nickname":"甜甜","money":1000,"moneyType":"2","type":2,"remarks":"送出抱抱礼物","createTime":"2020-06-23 13:57:49"},{"id":"fa588d84a19c20f64f10ecabf1bb7f89","userId":"b2f7918eda4f8d5320b0ca6fe5ad2a99","nickname":"迟日幕林","money":1000,"moneyType":"2","type":1,"remarks":"收到甜甜赠送的【抱抱】礼物","createTime":"2020-06-23 13:57:49"},{"id":"8f3f953452ce4e49de7d66ea14580b2e","userId":"e7b683451ac8496af31ba7775f450df1","nickname":"遇见","money":0.3,"moneyType":"1","type":2,"remarks":"账户提现","createTime":"2020-06-23 11:43:25"}],"total":402,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":41}
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * records : [{"id":"ca66699ad149d1b351399a96d593a465","userId":"e6ecd50d5dca492a6154d6dc7f855359","nickname":"透明","money":50,"moneyType":"2","type":1,"remarks":"广告收益","createTime":"2020-06-23 14:53:19"},{"id":"9785c8a33ce4fca6f433ace3483fcbe8","userId":"e6ecd50d5dca492a6154d6dc7f855359","nickname":"透明","money":10,"moneyType":"2","type":2,"remarks":"聊天扣除10","createTime":"2020-06-23 14:44:47"},{"id":"b42dc72ac112e7acf8f55a5118418cba","userId":"e7b683451ac8496af31ba7775f450d10","nickname":"原兔SAMT","money":5,"moneyType":"2","type":1,"remarks":"收到与透明的聊天收益","createTime":"2020-06-23 14:44:47"},{"id":"1773b41a5721c8666afaa2569c56c1e3","userId":"e7b683451ac8496af31ba7775f450d10","nickname":"原兔SAMT","money":5,"moneyType":"2","type":1,"remarks":"收到与透明的聊天收益","createTime":"2020-06-23 14:43:34"},{"id":"a1a455d2ffa98740c54fd9f17dc57599","userId":"e6ecd50d5dca492a6154d6dc7f855359","nickname":"透明","money":10,"moneyType":"2","type":2,"remarks":"聊天扣除10","createTime":"2020-06-23 14:43:34"},{"id":"d9077c65a979551568728ce183c6f5ec","userId":"e6ecd50d5dca492a6154d6dc7f855359","nickname":"透明","money":10,"moneyType":"2","type":2,"remarks":"聊天扣除10","createTime":"2020-06-23 14:41:06"},{"id":"22d47447102c42104055087baf69d10c","userId":"b2f7918eda4f8d5320b0ca6fe5ad2a75","nickname":"被遗忘的小猪🐷","money":5,"moneyType":"2","type":1,"remarks":"收到与透明的聊天收益","createTime":"2020-06-23 14:41:06"},{"id":"ff8c8a3c25171a41682a02d22cf0fe7f","userId":"86f5cd2c42549606e64815f937385b55","nickname":"甜甜","money":1000,"moneyType":"2","type":2,"remarks":"送出抱抱礼物","createTime":"2020-06-23 13:57:49"},{"id":"fa588d84a19c20f64f10ecabf1bb7f89","userId":"b2f7918eda4f8d5320b0ca6fe5ad2a99","nickname":"迟日幕林","money":1000,"moneyType":"2","type":1,"remarks":"收到甜甜赠送的【抱抱】礼物","createTime":"2020-06-23 13:57:49"},{"id":"8f3f953452ce4e49de7d66ea14580b2e","userId":"e7b683451ac8496af31ba7775f450df1","nickname":"遇见","money":0.3,"moneyType":"1","type":2,"remarks":"账户提现","createTime":"2020-06-23 11:43:25"}]
         * total : 402
         * size : 10
         * current : 1
         * orders : []
         * hitCount : false
         * searchCount : true
         * pages : 41
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
             * id : ca66699ad149d1b351399a96d593a465
             * userId : e6ecd50d5dca492a6154d6dc7f855359
             * nickname : 透明
             * money : 50.0
             * moneyType : 2
             * type : 1
             * remarks : 广告收益
             * createTime : 2020-06-23 14:53:19
             */

            private String id;
            private String userId;
            private String nickname;
            private double money;
            private String moneyType;
            private int type;
            private String remarks;
            private String createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getMoneyType() {
                return moneyType;
            }

            public void setMoneyType(String moneyType) {
                this.moneyType = moneyType;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
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
