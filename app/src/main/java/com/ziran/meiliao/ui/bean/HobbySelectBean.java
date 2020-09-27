package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class HobbySelectBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * userId : c4beb6acb3635a3210536ae0a292638b
         * tableName : 流行
         * type : 4
         * createTime : 2020-07-29 17:05:36
         */

        private int id;
        private String userId;
        private String tableName;
        private String type;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
