package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * 检查是否是VIP会员的Bean
 * Created by Administrator on 2017/3/1.
 */

public class CheckVipLevelBean extends Result {


    /**
     * data : {"title":"一年会员资格","isMember":true,"endTime":"2017-03-12 ","type":"mem-1","tip":"加入5P医学"}
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
         * title : 一年会员资格
         * isMember : true
         * endTime : 2017-03-12
         * type : mem-1
         * tip : 加入5P医学
         */

        private String title;
        private boolean isMember;
        private String endTime;
        private String type;
        private String tip;
        private  String extension;

        public String getExtension() {
            return extension;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "title='" + title + '\'' +
                    ", isMember=" + isMember +
                    ", endTime='" + endTime + '\'' +
                    ", type='" + type + '\'' +
                    ", tip='" + tip + '\'' +
                    ", extension='" + extension + '\'' +
                    '}';
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isIsMember() {
            return isMember;
        }

        public void setIsMember(boolean isMember) {
            this.isMember = isMember;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

    }

    @Override
    public String toString() {
        return "CheckVipLevelBean{" +
                "data=" + data +
                '}';
    }
}
