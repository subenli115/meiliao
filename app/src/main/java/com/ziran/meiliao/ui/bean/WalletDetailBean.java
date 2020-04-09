package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/6 15:16
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/6$
 * @updateDes ${TODO}
 */

public class WalletDetailBean extends Result {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        public DataBean() {
        }

        public DataBean(boolean isHead, String title, long createData, float balance, float amount) {
            this.isHead = isHead;
            this.title = title;
            this.createData = createData;
            this.balance = balance;
            this.amount = amount;
        }

        private boolean isHead;
        private String title;
        private long createData;
        private float balance;
        private float amount;

        public boolean isHead() {
            return isHead;
        }

        public void setHead(boolean head) {
            isHead = head;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getCreateData() {
            return createData;
        }

        public void setCreateData(long createData) {
            this.createData = createData;
        }

        public float getBalance() {
            return balance;
        }

        public void setBalance(float balance) {
            this.balance = balance;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "DataBean{" + "isHead=" + isHead + ", title='" + title + '\'' + ", createData=" + createData + ", balance=" + balance
                    + ", amount=" + amount + '}';
        }
    }

    @Override
    public String toString() {
        return "WalletDetailBean{" + "data=" + data + '}';
    }



}
