package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class PointsListBean extends Result {



    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        /**
         * coin : 98510
         * score : 8426
         * scoreList : [{"goodsId":1,"coin":300,"score":30,"type":4},{"goodsId":2,"coin":600,"score":60,"type":4},{"goodsId":3,"coin":900,"score":90,"type":4},{"goodsId":4,"coin":1200,"score":120,"type":4},{"goodsId":5,"coin":1500,"score":150,"type":4},{"goodsId":6,"coin":1800,"score":180,"type":4}]
         */

        private String coin;
        private int score;
        private List<ScoreListBean> scoreList;

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<ScoreListBean> getScoreList() {
            return scoreList;
        }

        public void setScoreList(List<ScoreListBean> scoreList) {
            this.scoreList = scoreList;
        }

        public static class ScoreListBean {
            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            /**
             * goodsId : 1
             * coin : 300
             * score : 30
             * type : 4
             */
            private boolean isSelect;
            private int goodsId;
            private int coin;
            private int score;
            private int type;

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
