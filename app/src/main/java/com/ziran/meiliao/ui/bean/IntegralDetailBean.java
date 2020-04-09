package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class IntegralDetailBean extends Result {




    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * customerScore : 20
         * scoreList : [{"title":"积分购买商品","time":1560853772000,"score":-20}]
         */

        private int customerScore;
        private List<ScoreListBean> scoreList;

        public int getCustomerScore() {
            return customerScore;
        }

        public void setCustomerScore(int customerScore) {
            this.customerScore = customerScore;
        }

        public List<ScoreListBean> getScoreList() {
            return scoreList;
        }

        public void setScoreList(List<ScoreListBean> scoreList) {
            this.scoreList = scoreList;
        }

        public static class ScoreListBean {
            /**
             * title : 积分购买商品
             * time : 1560853772000
             * score : -20
             */

            private String title;
            private long time;
            private int score;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }
    }







}
