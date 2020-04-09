package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2017/3/20.
 */

public class VersionBean extends Result {


    /**
     * data : {"content":"新版本是这样的<br/>sads das d","isNew":false,"lastestVersion":"0.0","url":"https://www.psytap.com/wpyx_longjgnull"}
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
         * content : 新版本是这样的<br/>sads das d
         * isNew : false
         * lastestVersion : 0.0
         * url : https://www.psytap.com/wpyx_longjgnull
         */

        private String content;
        private String servicePhone;
        private int payTag;
        private boolean isNew;
        private String lastestVersion;
        private String url;
        private boolean isForce;


        public String getServicePhone() {
            return servicePhone;
        }

        public void setServicePhone(String servicePhone) {
            this.servicePhone = servicePhone;
        }

        public int getPayTag() {
            return payTag;
        }

        public void setPayTag(int payTag) {
            this.payTag = payTag;
        }

        public boolean isForce() {
            return isForce;
        }

        public void setForce(boolean force) {
            isForce = force;
        }

        public boolean isNew() {
            return isNew;
        }

        public void setNew(boolean aNew) {
            isNew = aNew;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isIsNew() {
            return isNew;
        }

        public void setIsNew(boolean isNew) {
            this.isNew = isNew;
        }

        public String getLastestVersion() {
            return lastestVersion;
        }

        public void setLastestVersion(String lastestVersion) {
            this.lastestVersion = lastestVersion;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "content='" + content + '\'' +
                    ", isNew=" + isNew +
                    ", lastestVersion='" + lastestVersion + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VersionBean{" +
                "data=" + data +
                '}';
    }
}
