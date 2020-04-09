package com.ziran.meiliao.ui.bean;


import com.ziran.meiliao.common.okhttp.Result;

public class BindPhoneBean extends Result {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean  {

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }


        private String accessToken;



    }
}
