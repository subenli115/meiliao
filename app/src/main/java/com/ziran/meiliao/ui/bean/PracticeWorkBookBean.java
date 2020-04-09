package com.ziran.meiliao.ui.bean;

/**
 * Created by Administrator on 2018/9/11.
 */

import com.ziran.meiliao.common.okhttp.Result;

/**
 * 练习册 详情 on 2018/9/11.
 */

public class PracticeWorkBookBean extends Result {



    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {

        /**
         * id : 1
         * status : 0
         * booksDetail : 卖骚练习+卖萌练习+犯贱练习
         */
        private int id;
        private int status;
        private String booksDetail;

        public String getGuideMusic() {
            return guideMusic;
        }

        public void setGuideMusic(String guideMusic) {
            this.guideMusic = guideMusic;
        }

        private String guideMusic;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        private String picture;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getBooksDetail() {
            return booksDetail;
        }

        public void setBooksDetail(String booksDetail) {
            this.booksDetail = booksDetail;
        }
    }
}
