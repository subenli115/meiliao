package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 */

public class GainCouponBean extends Result {

    /**
     * data : {"pics":["https://www.psytap.com/wpyx_longjg/static/home/images/shareCoupon/pics1.png"
     * ,"https://www.psytap.com/wpyx_longjg/static/home/images/shareCoupon/pics2.png","https://www.psytap.com/wpyx_longjg/static/home/images/shareCoupon/pics3.png"],"times":"15","pg":"https://www.psytap.com/wpyx_longjg/static/home/images/shareCoupon/bg.png","number":1,"faceValue":"200","exist":false}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends ShareBean {
        /**
         * pics : ["https://www.psytap.com/wpyx_longjg/static/home/images/shareCoupon/pics1.png",
         * "https://www.psytap.com/wpyx_longjg/static/home/images/shareCoupon/pics2.png","https://www.psytap.com/wpyx_longjg/static/home/images/shareCoupon/pics3.png"]
         * times : 15
         * pg : https://www.psytap.com/wpyx_longjg/static/home/images/shareCoupon/bg.png
         * number : 1
         * faceValue : 200
         * exist : false
         */

        private String times;
        private String bg;
        private String qrcode;
        private int number;
        private String faceValue;
        private boolean exist;
        private List<String> pics;
        private int frowCount;
        private String msg;


        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getFrowCount() {
            return frowCount == 0 ? 15 : frowCount;
        }

        public void setFrowCount(int frowCount) {
            this.frowCount = frowCount;
        }

        public String getTimes() {
            return times;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getBg() {
            return bg;
        }

        public void setBg(String bg) {
            this.bg = bg;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getFaceValue() {
            return faceValue;
        }

        public void setFaceValue(String faceValue) {
            this.faceValue = faceValue;
        }

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }
    }
}
