package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class UserAccountBean extends Result {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        /**
         * id : d890d1730402a5fa4378f7de44814c2f
         * userId : cb9143bfbd79c420a1859ceb039e982e
         * money : 3.0
         * currency : 100.0
         * recharge : 1.0E7
         * nickname : null
         */

        private String id;
        private String userId;
        private double money;
        private double currency;
        private double recharge;
        private Object nickname;

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

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public double getCurrency() {
            return currency;
        }

        public void setCurrency(double currency) {
            this.currency = currency;
        }

        public double getRecharge() {
            return recharge;
        }

        public void setRecharge(double recharge) {
            this.recharge = recharge;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }
    }



}
