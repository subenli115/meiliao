package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2017/2/23.
 */

public class UserVipBean extends Result {


    /**
     * data : {"id":0,"title":"一年会员资格","price":300,"type":"普通会员"}
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
         * id : 0
         * title : 一年会员资格
         * price : 300
         * type : 普通会员
         */

        private int vipId;
        private String title;
        private float price;
        private String type;

        public int getId() {
            return vipId;
        }
        public void setId(int id) {
            this.vipId = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "vipId=" + vipId +
                    ", title='" + title + '\'' +
                    ", price=" + price +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserVipBean{" +
                "data=" + data +
                '}';
    }
}
