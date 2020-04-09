package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/7 15:50
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/7$
 * @updateDes ${TODO}
 */

public class CheckCardBean extends Result {

    /**
     * data : {"name":"用户2953052","bankName":"招商银行","bankCardNo":"6225768744876879","bankCardType":"信用卡"}
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
         * name : 用户2953052
         * bankName : 招商银行
         * bankCardNo : 6225768744876879
         * bankCardType : 信用卡
         */

        private String name;
        private String bankName;
        private String bankCardNo;
        private String bankCardType;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getBankCardType() {
            return bankCardType;
        }

        public void setBankCardType(String bankCardType) {
            this.bankCardType = bankCardType;
        }
    }
}
