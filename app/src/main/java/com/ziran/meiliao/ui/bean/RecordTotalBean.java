package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/11 10:06
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/11$
 * @updateDes ${TODO}
 */

public class RecordTotalBean extends Result {

    /**
     * data : {"amounts":205,"days":1,"times":6034}
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
         * amounts : 205
         * days : 1
         * times : 6034
         */

        private int amounts;
        private int days;
        private String times;

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

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }
    }
}
