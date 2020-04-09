package com.ziran.meiliao.ui.bean;


import com.ziran.meiliao.common.okhttp.Result;

public class ChangePhoneBean extends Result {

    private String data;

    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
