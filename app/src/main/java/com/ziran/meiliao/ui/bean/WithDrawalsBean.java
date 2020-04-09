package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/7 10:55
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/7$
 * @updateDes ${TODO}
 */

public class WithDrawalsBean extends Result {


    /**
     * data : {"ableMoney":"1442","orderNo":"lcs60961527489359926","dateTime":1527489359926}
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
         * ableMoney : 1442
         * orderNo : lcs60961527489359926
         * dateTime : 1527489359926
         */

        private String ableMoney;
        private String orderNo;
        private long dateTime;

        public String getAbleMoney() {
            return ableMoney;
        }

        public void setAbleMoney(String ableMoney) {
            this.ableMoney = ableMoney;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public long getDateTime() {
            return dateTime;
        }

        public void setDateTime(long dateTime) {
            this.dateTime = dateTime;
        }
    }
}
