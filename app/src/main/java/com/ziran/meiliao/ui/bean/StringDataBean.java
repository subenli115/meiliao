package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;

/**
 * Created by Administrator on 2017/2/13.
 */

public class StringDataBean extends Result {


    private String code;
    private String data;

    //返回AES界面的字符串
    public String getData() {
        return AES.get().decrypt(data);
    }

    public String getNornemData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIntData() {
        try {
            return Integer.parseInt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String toString() {
        return "StringDataBean{" + "data='" + getData() + '\'' + "code = " + getResultCode() + '}';
    }
}
