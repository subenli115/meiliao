package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2017/3/4.
 */

public class ExerciseUploadBean extends Result {

    /**
     * data : {"id":2,"amounts":3,"days":1,"times":1800,"shareUrl":""}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * amounts : 3
         * days : 1
         * times : 1800
         * shareUrl :
         */

        private int id;
        private int amounts;
        private int days;
        private String times;
        private String timesFormat;
        private String qrcode;
        private String hisId;
        private int isFirstPractice;

        public int getIsFirstPractice() {
            return isFirstPractice;
        }

        public void setIsFirstPractice(int isFirstPractice) {
            this.isFirstPractice = isFirstPractice;
        }

        public String getTimesFormat() {
            return timesFormat;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public void setTimesFormat(String timesFormat) {
            this.timesFormat = timesFormat;
        }

        public String getHisId() {
            return hisId;
        }

        public void setHisId(String hisId) {
            this.hisId = hisId;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAmounts() {
            return amounts;
        }

        public void setAmounts(int amounts) {
            this.amounts = amounts;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }


        @Override
        public String toString() {
            return "DataBean{" + "id=" + id + ", amounts=" + amounts + ", days=" + days + ", times=" + times + ", qrcode='" + qrcode +
                    '\'' + ", hisId='" + hisId + '\'' + '}';
        }
    }

    @Override
    public String toString() {
        return "ExerciseUploadBean{" + "data=" + data + '}';
    }
}
