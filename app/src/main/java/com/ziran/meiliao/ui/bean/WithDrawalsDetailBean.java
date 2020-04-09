package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/21 10:45
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/21$
 * @updateDes ${TODO}
 */

public class WithDrawalsDetailBean extends Result {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * statusId : 0
         * orderNo : lcs70651521609382467
         * dateTime : 1519468087000
         * status : 0
         * money : 2
         * intro : 提现到中国农业银行(提现中)
         */

        private int statusId;
        private String orderNo;
        private long dateTime;
        @SerializedName("status")
        private int statusX;
        private int money;
        private String intro;
        private boolean isShowTop;
        private String expectTime;

        public String getExpectTime() {
            return expectTime;
        }

        public void setExpectTime(String expectTime) {
            this.expectTime = expectTime;
        }

        public boolean isShowTop() {
            return isShowTop;
        }

        public void setShowTop(boolean showTop) {
            isShowTop = showTop;
        }

        public int getStatusId() {
            return statusId;
        }

        public void setStatusId(int statusId) {
            this.statusId = statusId;
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

        public int getStatusX() {
            return statusX;
        }

        public void setStatusX(int statusX) {
            this.statusX = statusX;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        @Override
        public String toString() {
            return "DataBean{" + "statusId=" + statusId + ", orderNo='" + orderNo + '\'' + ", dateTime=" + dateTime + ", statusX=" + statusX + ", money=" + money + ", intro='" +
                    intro + '\'' + '}';
        }
    }
}
