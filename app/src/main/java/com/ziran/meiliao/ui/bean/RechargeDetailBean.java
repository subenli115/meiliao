package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/6/13 14:42
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/6/13
 * @updateDes ${TODO}
 */

public class RechargeDetailBean extends Result {

    /**
     * createTime :
     * newCoin : 100
     * title : 充值
     * value : 100
     * type : add
     * oldCoin : 0
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String createTime;
        private String newCoin;
        private String title;
        private String value;
        private String type;
        private String oldCoin;
        private boolean isHead;

        public boolean isHead() {
            return isHead;
        }

        public void setHead(boolean head) {
            isHead = head;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getNewCoin() {
            return newCoin;
        }

        public void setNewCoin(String newCoin) {
            this.newCoin = newCoin;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOldCoin() {
            return oldCoin;
        }

        public void setOldCoin(String oldCoin) {
            this.oldCoin = oldCoin;
        }
    }
}
