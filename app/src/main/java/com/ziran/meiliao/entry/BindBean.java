package com.ziran.meiliao.entry;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2018/8/4.
 */

public class BindBean extends Result{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "MeCourseBean{" +
                "data=" + data + " resultCode = " + resultCode +
                '}';
    }

    public static class DataBean {

        /**
         * accessToken : 6884446d779a6d4410a6d5e3bc68c393292dca8
         */

        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
