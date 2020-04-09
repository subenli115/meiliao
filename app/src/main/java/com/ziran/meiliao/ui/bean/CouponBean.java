package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */

public class CouponBean extends Result {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 课程优惠券
         * beginTime : 2017-02-22
         * faceValue : 50
         * endTime : 2017-02-25
         */

        private int id;
        private String title;
        private String beginTime;
        private String faceValue;
        private String endTime;
        private boolean used;

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getFaceValue() {
            return faceValue;
        }

        public void setFaceValue(String faceValue) {
            this.faceValue = faceValue;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", beginTime='" + beginTime + '\'' +
                    ", faceValue='" + faceValue + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", used=" + used +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CouponBean{" +
                "data=" + data +
                '}';
    }
}
