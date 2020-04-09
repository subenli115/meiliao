package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2019/2/1.
 */

public class PunchHisBean extends Result{
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private List<PunchDataBean> punchData;

        public List<PunchDataBean> getPunchData() {
            return punchData;
        }

        public void setPunchData(List<PunchDataBean> punchData) {
            this.punchData = punchData;
        }

        public static class PunchDataBean {
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
