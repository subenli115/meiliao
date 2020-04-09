package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/5/24 19:11
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/5/24$
 * @updateDes ${TODO}
 */

public class WithDrawalsItemDetailBean extends Result {

    /**
     * data : {"dealTime":null,"leftMoney":336.40000000000003,"dealItem":"正在受理","orderNo":"lcs70651521609382467","incomeBank":"中国农业银行尾号0010","dateTime":null,"successTime":null,
     * "supplyTime":1521609382000,"money":2,"successItem":"提现成功","supplyItem":"提现申请"}
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
         * dealTime : null
         * leftMoney : 336.40000000000003
         * dealItem : 正在受理
         * orderNo : lcs70651521609382467
         * incomeBank : 中国农业银行尾号0010
         * dateTime : null
         * successTime : null
         * supplyTime : 1521609382000
         * money : 2
         * successItem : 提现成功
         * supplyItem : 提现申请
         */

        private long dealTime;
        private double leftMoney;
        private String dealItem;
        private String orderNo;
        private String incomeBank;
        private long dateTime;
        private long successTime;
        private long supplyTime;
        private int money;
        private String successItem;
        private String supplyItem;


        public long getDealTime() {
            return dealTime;
        }

        public void setDealTime(long dealTime) {
            this.dealTime = dealTime;
        }

        public long getSuccessTime() {
            return successTime;
        }

        public void setSuccessTime(long successTime) {
            this.successTime = successTime;
        }

        public long getSupplyTime() {
            return supplyTime;
        }

        public double getLeftMoney() {
            return leftMoney;
        }

        public void setLeftMoney(double leftMoney) {
            this.leftMoney = leftMoney;
        }

        public String getDealItem() {
            return dealItem;
        }

        public void setDealItem(String dealItem) {
            this.dealItem = dealItem;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getIncomeBank() {
            return incomeBank;
        }

        public void setIncomeBank(String incomeBank) {
            this.incomeBank = incomeBank;
        }

        public long getDateTime() {
            return dateTime;
        }

        public void setDateTime(long dateTime) {
            this.dateTime = dateTime;
        }



        public void setSupplyTime(long supplyTime) {
            this.supplyTime = supplyTime;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getSuccessItem() {
            return successItem;
        }

        public void setSuccessItem(String successItem) {
            this.successItem = successItem;
        }

        public String getSupplyItem() {
            return supplyItem;
        }

        public void setSupplyItem(String supplyItem) {
            this.supplyItem = supplyItem;
        }
    }
}
