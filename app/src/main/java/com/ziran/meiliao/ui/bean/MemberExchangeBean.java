package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class MemberExchangeBean extends Result {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        /**
         * total : 20
         * goodsList : [{"picture":"https://dgli.net/resource/images/album/hotPic/1hot.png","id":1,"goodsId":1,"title":"正念创造力","price":20,"goods_id":1,"create_time":1559706539000,"score":20,"isCheckout":true,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/2hot.png","id":2,"goodsId":2,"title":"8周正念练习引导","price":0,"goods_id":2,"create_time":1559706539000,"score":20,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/3hot.png","id":5,"goodsId":3,"title":"引导式正念静观练习","price":20,"goods_id":3,"create_time":1560908488000,"score":20,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/4hot.png","id":6,"goodsId":4,"title":"穿越抑郁的正念之道","price":20,"goods_id":4,"create_time":1560908514000,"score":120,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/5hot.png","id":7,"goodsId":5,"title":"正念禅修引导","price":0,"goods_id":5,"create_time":1560908543000,"score":20,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/6hot.png","id":8,"goodsId":6,"title":"正念癌症康复","price":20,"goods_id":6,"create_time":1560908573000,"score":30,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/7hot.png","id":9,"goodsId":7,"title":"抗癌自愈力","price":20,"goods_id":7,"create_time":1560908592000,"score":20,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/8hot.png","id":10,"goodsId":8,"title":"静心冥想的练习","price":20,"goods_id":8,"create_time":1560908609000,"score":50,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/9hot.png","id":11,"goodsId":9,"title":"像蛙一样坐定","price":20,"goods_id":9,"create_time":1560908744000,"score":30,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/10hot.png","id":12,"goodsId":10,"title":"给与空间，保持亲近","price":20,"goods_id":10,"create_time":1560908845000,"score":30,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/11hot.png","id":13,"goodsId":11,"title":"正念禅修引导（英语）","price":20,"goods_id":11,"create_time":1560908845000,"score":30,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/12hot.png","id":14,"goodsId":12,"title":"基础冥想","price":20,"goods_id":12,"create_time":1560908845000,"score":30,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/13hot.png","id":15,"goodsId":13,"title":"初学者的冥想书","price":20,"goods_id":13,"create_time":1560908845000,"score":30,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/14hot.png","id":16,"goodsId":14,"title":"黄庭禅坐","price":20,"goods_id":14,"create_time":1560908845000,"score":30,"isCheckout":false,"type":1},{"picture":"https://dgli.net/resource/images/album/hotPic/15hot.png","id":17,"goodsId":15,"title":"黄庭禅坐","price":20,"goods_id":15,"create_time":1560908845000,"score":30,"isCheckout":false,"type":1}]
         */

        private int total;
        private List<GoodsListBean> goodsList;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
            /**
             * picture : https://dgli.net/resource/images/album/hotPic/1hot.png
             * id : 1
             * goodsId : 1
             * title : 正念创造力
             * price : 20
             * goods_id : 1
             * create_time : 1559706539000
             * score : 20
             * isCheckout : true
             * type : 1
             */

            private String picture;
            private int id;
            private int goodsId;
            private String title;
            private int price;
            private int goods_id;
            private long create_time;
            private int score;
            private boolean isCheckout;
            private int type;

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public boolean isIsCheckout() {
                return isCheckout;
            }

            public void setIsCheckout(boolean isCheckout) {
                this.isCheckout = isCheckout;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }

}