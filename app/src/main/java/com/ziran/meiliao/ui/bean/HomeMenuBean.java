package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class HomeMenuBean extends Result {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * menuName : 颜值配对
         * menuUrl : https://zrwlmeiliao.oss-cn-shanghai.aliyuncs.com/icon/yanzhi.png
         * type : 1
         * status : 0
         * sort : 2
         * createTime : 2020-07-24 16:26:04
         * relationship : null
         */

        private int id;
        private String menuName;
        private String menuUrl;
        private String type;
        @SerializedName("status")
        private String statusX;
        private int sort;
        private String createTime;
        private String updateTime;


        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
        private String relationship;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        private int number;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public String getMenuUrl() {
            return menuUrl;
        }

        public void setMenuUrl(String menuUrl) {
            this.menuUrl = menuUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatusX() {
            return statusX;
        }

        public void setStatusX(String statusX) {
            this.statusX = statusX;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getRelationship() {
            return relationship;
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }
    }
}
