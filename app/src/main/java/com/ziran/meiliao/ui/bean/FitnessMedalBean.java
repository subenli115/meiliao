package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;


public class FitnessMedalBean extends Result {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private List<MedalBean> medal;

        public List<MedalBean> getMedal() {
            return medal;
        }

        public void setMedal(List<MedalBean> medal) {
            this.medal = medal;
        }

        public static class MedalBean {
            /**
             * picture : http://ojlzx3sl8.bkt.clouddn.com/xiongzhang_01.png
             * id : 1
             * creatTime : 1569494907000
             * type : 1
             */

            private String picture;
            private int id;
            private long creatTime;
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

            public long getCreatTime() {
                return creatTime;
            }

            public void setCreatTime(long creatTime) {
                this.creatTime = creatTime;
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
