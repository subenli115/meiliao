package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class CourseJoinDeatilBean extends Result {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
    public static class DataBean {


        /**
         * score : 900
         * teamCoupon : 200
         * detailList : [{"id":1,"activity_id":64,"name":"时间：2019年10月1日-7日（国庆节假期）"}]
         */

        private int score;
        private String teamCoupon;
        private List<DetailListBean> detailList;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getTeamCoupon() {
            return teamCoupon;
        }

        public void setTeamCoupon(String teamCoupon) {
            this.teamCoupon = teamCoupon;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * id : 1
             * activity_id : 64
             * name : 时间：2019年10月1日-7日（国庆节假期）
             */

            private int id;
            private int activity_id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(int activity_id) {
                this.activity_id = activity_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }


}
