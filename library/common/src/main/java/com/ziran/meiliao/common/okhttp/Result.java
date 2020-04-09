package com.ziran.meiliao.common.okhttp;

/**
 *
 * Created by Administrator on 2017/1/5.
 */

/**
 * des:接口返回数据基类
 * Created by xsf
 * on 2016.06.13:05
 */
public class Result   {
    protected int resultCode;
    protected String resultMsg;
    protected int status;
    protected int code;
    protected String msg;

    public Result() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int code) {
        this.code = code;
    }

    public String getResultMsg() {
        return msg;
    }

    public void setResultMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                ", resultCode=" + code +
                ", resultMsg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
