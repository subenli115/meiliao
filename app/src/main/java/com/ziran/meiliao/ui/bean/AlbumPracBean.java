package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2019/1/14.
 */

public class AlbumPracBean extends Result {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        public int getIsGif() {
            return isGif;
        }

        public void setIsGif(int isGif) {
            this.isGif = isGif;
        }

        /**
         * punch : [{"picture":"","id":1,"days":3},{"picture":"https://www.dgli.net/resource/images/punch/gifs.png","id":2,"days":2},{"picture":"","id":3,"days":3},{"picture":"https://www.dgli.net/resource/images/punch/gifs.png","id":4,"days":4},{"picture":"https://www.dgli.net/resource/images/punch/gifs.png","id":5,"days":5,"isDraw":0},{"picture":"","id":6,"days":6},{"picture":"https://www.dgli.net/resource/images/punch/gifs.png","id":7,"days":7,"isDraw":0}]
         * serialDays : 2
         * nextPrizeDay : 1
         * notice : 这是个测试用语，本来我想了一千字，但是想想，这样子没什么意思计算了
         */
        private int isGif;
        private int serialDays;
        private int nextPrizeDay;
        private String notice;
        private List<PunchBean> punch;
        private String backgroud;

        public String getBackgroud() {
            return backgroud;
        }

        public void setBackgroud(String backgroud) {
            this.backgroud = backgroud;
        }

        public int getSerialDays() {
            return serialDays;
        }

        public void setSerialDays(int serialDays) {
            this.serialDays = serialDays;
        }

        public int getNextPrizeDay() {
            return nextPrizeDay;
        }

        public void setNextPrizeDay(int nextPrizeDay) {
            this.nextPrizeDay = nextPrizeDay;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public List<PunchBean> getPunch() {
            return punch;
        }

        public void setPunch(List<PunchBean> punch) {
            this.punch = punch;
        }

        public static class PunchBean {
            /**
             * picture :
             * id : 1
             * days : 3
             * isDraw : 0
             */

            private String picture;
            private int id;
            private int days;
            private int isDraw;


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

            public int getDays() {
                return days;
            }

            public void setDays(int days) {
                this.days = days;
            }

            public int getIsDraw() {
                return isDraw;
            }

            public void setIsDraw(int isDraw) {
                this.isDraw = isDraw;
            }
        }
    }
}
