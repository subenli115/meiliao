package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/10 16:11
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/10$
 * @updateDes ${TODO}
 */

public class CrownFundCreateOrderListBean {
        /**
         * picture : https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png
         * createTime : 1512815020000
         * title : 报名 | 医学及心理学中的正念3
         * price : 2000.0
         * status : {"statusType":0,"statusMsg":"审核中"}
         * courserId : 1
         * crowdFundId : 24
         * orderId : cfc57211512815020120
         */

        private String picture;
        private long createTime;
        private String title;
        private double price;
        private int statusType;
        private String statusMsg;

        private int courserId;
        private int crowdFundId;
        private String orderId;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getCourserId() {
            return courserId;
        }

        public void setCourserId(int courserId) {
            this.courserId = courserId;
        }

        public int getCrowdFundId() {
            return crowdFundId;
        }

        public void setCrowdFundId(int crowdFundId) {
            this.crowdFundId = crowdFundId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getStatusType() {
            return statusType;
        }

        public void setStatusType(int statusType) {
            this.statusType = statusType;
        }

        public String getStatusMsg() {
            return statusMsg;
        }

        public void setStatusMsg(String statusMsg) {
            this.statusMsg = statusMsg;
        }

    @Override
    public String toString() {
        return "CrownFundCreateOrderListBean{" + "picture='" + picture + '\'' + ", createTime=" + createTime + ", title='" + title + '\''
                + ", price=" + price + ", statusType=" + statusType + ", statusMsg='" + statusMsg + '\'' + ", courserId=" + courserId +
                ", crowdFundId=" + crowdFundId + ", orderId='" + orderId + '\'' + '}';
    }
}
