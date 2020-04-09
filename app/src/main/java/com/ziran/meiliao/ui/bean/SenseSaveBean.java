package com.ziran.meiliao.ui.bean;

/**
 * Created by Administrator on 2018/9/28.
 */

public class SenseSaveBean {


    /**
     * senseId :
     * senseStatus :
     */

    private int senseId;
    private int senseStatus;
    private int practiceStatus;
    private String senseDetail;

    public String getSenseDetail() {
        return senseDetail;
    }

    public void setSenseDetail(String senseDetail) {
        this.senseDetail = senseDetail;
    }

    public int getPracticeStatus() {
        return practiceStatus;
    }

    public void setPracticeStatus(int practiceStatus) {
        this.practiceStatus = practiceStatus;
    }

    public int getSenseId() {
        return senseId;
    }

    public void setSenseId(int senseId) {
        this.senseId = senseId;
    }

    public int getSenseStatus() {
        return senseStatus;
    }

    public void setSenseStatus(int senseStatus) {
        this.senseStatus = senseStatus;
    }
}
