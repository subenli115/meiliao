package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * 更新用户头像返回的bean
 * Created by Administrator on 2017/1/11.
 */

public class UpdateUserHeadBean extends Result {

    /**
     * data : {"headImgVersion":8,"path":"/static/upload/images/userHeadImg/762383676c3f43e5898b06f83e8c94ba.jpeg"}
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
         * headImgVersion : 8
         * path : /static/upload/images/userHeadImg/762383676c3f43e5898b06f83e8c94ba.jpeg
         */

        private int headImgVersion;
        private String path;

        public int getHeadImgVersion() {
            return headImgVersion;
        }

        public void setHeadImgVersion(int headImgVersion) {
            this.headImgVersion = headImgVersion;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "headImgVersion=" + headImgVersion +
                    ", path='" + path + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UpdateUserHeadBean{" +
                "data=" + data +
                '}';
    }
}
