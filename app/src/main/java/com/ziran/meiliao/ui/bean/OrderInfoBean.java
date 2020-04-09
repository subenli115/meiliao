package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/8 14:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/8$
 * @updateDes ${TODO}
 */

public class OrderInfoBean extends Result {

    /**
     * data : {"createtime":1512712984000,"startTime":"2017-12-11","picture":"https://www.dgli.net/resource/images/qcourselibrary/default
     * .png","createTime":1512712984000,"phone":"1879376597","teacherName":"李燕蕙","title":"睡眠","targetMembers":21,"remarks":" ",
     * "orderId":"mbc89621512712984958"}
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
         * createtime : 1512712984000
         * startTime : 2017-12-11
         * picture : https://www.dgli.net/resource/images/qcourselibrary/default.png
         * createTime : 1512712984000
         * phone : 1879376597
         * teacherName : 李燕蕙
         * title : 睡眠
         * targetMembers : 21
         * remarks :
         * orderId : mbc89621512712984958
         */

        private String status;  //退款状态
        private String reason;  //退款原因
        private String statusName;      //退款状态名
        private long orderCreateTime; //发起退款的时间
        private String refundId;        //退款id
        private long refundCreateTime;
        private long refundSucceedTime;
        private long bankDealTime;
        private String teacherName;
        private String remarks;
        private long createTime;
        private int targetMembers;
        private String phone;
        private int statusType;
        private int supportMembers;
        private int leftTime;
        private String statusMsg;
        private String crowdFundId;
        private Object endTime;
        private String picture;
        private Object startTime;
        private String title;
        private double price;
        private String address;
        private String name;
        private String orderId;
        private String intro;
        private String totalTime;
        private int refundDays;
        private String refundDescript;

        public String getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(String totalTime) {
            this.totalTime = totalTime;
        }

        public long getRefundCreateTime() {
            return refundCreateTime;
        }

        public void setRefundCreateTime(long refundCreateTime) {
            this.refundCreateTime = refundCreateTime;
        }

        public long getRefundSucceedTime() {
            return refundSucceedTime;
        }

        public void setRefundSucceedTime(long refundSucceedTime) {
            this.refundSucceedTime = refundSucceedTime;
        }

        public long getBankDealTime() {
            return bankDealTime;
        }

        public void setBankDealTime(long bankDealTime) {
            this.bankDealTime = bankDealTime;
        }

        public String getRefundDescript() {
            return refundDescript;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public long getOrderCreateTime() {
            return orderCreateTime;
        }

        public void setOrderCreateTime(long orderCreateTime) {
            this.orderCreateTime = orderCreateTime;
        }

        public String getRefundId() {
            return refundId;
        }

        public void setRefundId(String refundId) {
            this.refundId = refundId;
        }

        public void setRefundDescript(String refundDescript) {
            this.refundDescript = refundDescript;
        }

        public int getRefundDays() {
            return refundDays;
        }

        public void setRefundDays(int refundDays) {
            this.refundDays = refundDays;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getPrice() {
            return (int) price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public int getStatus() {
            return statusType;
        }

        public void setStatus(int status) {
            this.statusType = status;
        }

        public String getStatusMsg() {
            return statusMsg;
        }

        public void setStatusMsg(String statusMsg) {
            this.statusMsg = statusMsg;
        }

        public Object getStartTime() {
            return startTime;
        }


        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTargetMembers() {
            return targetMembers;
        }

        public void setTargetMembers(int targetMembers) {
            this.targetMembers = targetMembers;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

        public int getSupportMembers() {
            return supportMembers;
        }

        public void setSupportMembers(int supportMembers) {
            this.supportMembers = supportMembers;
        }

        public int getLeftTime() {
            return leftTime;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        public String getCrowdFundId() {
            return crowdFundId;
        }

        public void setCrowdFundId(String crowdFundId) {
            this.crowdFundId = crowdFundId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
