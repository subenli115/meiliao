package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class FileListBean extends Result {

/**
 *   {"code":0,"msg":"","data":{"bucketName":"yueseseu","fileName":"690d3307eaed45a8b1dcaccd3a3ae3a3.png","url":"http://yuese.ziran518.com/690d3307eaed45a8b1dcaccd3a3ae3a3.png"}}
 */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {


        /**
         * bucketName : yueseseu
         * fileName : 690d3307eaed45a8b1dcaccd3a3ae3a3.png
         * url : http://yuese.ziran518.com/690d3307eaed45a8b1dcaccd3a3ae3a3.png
         */

        private String bucketName;
        private String fileName;
        private String url;

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
