package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class VersionNewBean extends Result {

    /**
     * code : 0
     * msg :
     * data : {"id":2,"appVersion":"1.0.0","type":2,"updateContent":"更新时间：2020/4/7 11:27\r安装包大小：58.9M\r更新内容：\r1.更新功能\r2.修复BUG\n3.更新了好多内容\n4.更新了好多内容\n5.更新了好多内容\n6.更新了好多内容\n7.更新了好多内容\n8.更新了好多内容","address":"xxxxx","isUpdate":0,"isNew":1,"createTime":"2020-05-15 09:14:10","updateTime":null}
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * appVersion : 1.0.0
         * type : 2
         * updateContent : 更新时间：2020/4/7 11:27安装包大小：58.9M更新内容：1.更新功能2.修复BUG
         3.更新了好多内容
         4.更新了好多内容
         5.更新了好多内容
         6.更新了好多内容
         7.更新了好多内容
         8.更新了好多内容
         * address : xxxxx
         * isUpdate : 0
         * isNew : 1
         * createTime : 2020-05-15 09:14:10
         * updateTime : null
         */

        private int id;
        private String appVersion;
        private int type;
        private String updateContent;
        private String address;
        private int isUpdate;
        private int isNew;
        private String createTime;
        private Object updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdateContent() {
            return updateContent;
        }

        public void setUpdateContent(String updateContent) {
            this.updateContent = updateContent;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(int isUpdate) {
            this.isUpdate = isUpdate;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }
    }
}
