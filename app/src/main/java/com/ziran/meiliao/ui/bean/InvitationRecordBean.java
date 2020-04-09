package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class InvitationRecordBean extends Result {





    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * frdsList : [{"id":11,"userId":9940,"type":4,"createTime":1560643364000,"score":30,"userReadyPhone":"15066718321"},{"id":9,"userId":9940,"type":4,"createTime":1560610959000,"score":30,"userReadyPhone":"15083466771"},{"id":8,"userId":9940,"type":4,"createTime":1560609344000,"score":30,"userReadyPhone":"15083466771"},{"id":7,"userId":9940,"type":4,"createTime":1560609211000,"score":30,"userReadyPhone":"15083466771"}]
         * coin : 200
         * score : 30
         */

        private int coin;
        private int score;
        private List<FrdsListBean> frdsList;

        public int getCoin() {
            return coin;
        }

        public void setCoin(int coin) {
            this.coin = coin;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<FrdsListBean> getFrdsList() {
            return frdsList;
        }

        public void setFrdsList(List<FrdsListBean> frdsList) {
            this.frdsList = frdsList;
        }

        public static class FrdsListBean {
            /**
             * id : 11
             * userId : 9940
             * type : 4
             * createTime : 1560643364000
             * score : 30
             * userReadyPhone : 15066718321
             */

            private int id;
            private long userId;
            private int type;
            private long createTime;
            private int score;
            private String userReadyPhone;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getUserId() {
                return userId;
            }

            public void setUserId(long userId) {
                this.userId = userId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getUserReadyPhone() {
                return userReadyPhone;
            }

            public void setUserReadyPhone(String userReadyPhone) {
                this.userReadyPhone = userReadyPhone;
            }
        }
    }
}
