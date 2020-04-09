package com.ziran.meiliao.entry;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2016/12/26.
 */

public class LoginBean extends Result {


    /**
     * data : {"accessToken":"5e72127135b764851b8e5575e56b77fd","userInfo":{"sex":"女","lastUpdateTime":"2017-01-12","headImgVersion":9,"descript":"","country":"","provience":"","city":"","identity":"","headImg":"/static/upload/images/userHeadImg/762383676c3f43e5898b06f83e8c94ba.jpeg","email":"1052243597@qq.com","nickName":"隔壁老王","age":"","identityImage":"","isValidate":false,"isRealName":false}}
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
         * accessToken : 5e72127135b764851b8e5575e56b77fd
         * userInfo : {"sex":"女","lastUpdateTime":"2017-01-12","headImgVersion":9,"descript":"","country":"","provience":"","city":"","identity":"","headImg":"/static/upload/images/userHeadImg/762383676c3f43e5898b06f83e8c94ba.jpeg","email":"1052243597@qq.com","nickName":"隔壁老王","age":"","identityImage":"","isValidate":false,"isRealName":false}
         */
        private int uniId;
        private String accessToken;
        private UserInfo userInfo;
        private boolean isUser;
        private boolean phoneRegister=false;

        public boolean isPhoneRegister() {
            return phoneRegister;
        }

        public void setPhoneRegister(boolean phoneRegister) {
            this.phoneRegister = phoneRegister;
        }

        public int getUniId() {
            return uniId;
        }

        public void setUniId(int uniId) {
            this.uniId = uniId;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public boolean isUser() {
            return isUser;
        }

        public void setUser(boolean user) {
            isUser = user;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "uniId=" + uniId +
                    ", accessToken='" + accessToken + '\'' +
                    ", userInfo=" + userInfo +
                    ", isUser=" + isUser +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "data=" + data +
                '}';
    }
}
