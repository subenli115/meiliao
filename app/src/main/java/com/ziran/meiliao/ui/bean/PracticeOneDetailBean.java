package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2018/9/18.
 */

public class PracticeOneDetailBean extends Result{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        public String getRoundPic() {
            return roundPic;
        }

        public void setRoundPic(String roundPic) {
            this.roundPic = roundPic;
        }

        /**
     * picture : http://www.dgli.net/resource/images/practiceActivity/default.png
     * id : 1
     * duration : 00:20:10
     * practiceStatus : 0
     * itemId : 1
     * itemTitle : 身体扫描
     * url : http://ojlzx3sl8.bkt.clouddn.com/hjmyyindao1.mp3
     */
    private String roundPic;
    private String picture;
    private int id;
    private String duration;
    private int practiceStatus;
    private int itemId;
    private String itemTitle;
    private String bgPic;
    private String url;

        public String getBgPic() {
            return bgPic;
        }

        public void setBgPic(String bgPic) {
            this.bgPic = bgPic;
        }



    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPracticeStatus() {
        return practiceStatus;
    }

    public void setPracticeStatus(int practiceStatus) {
        this.practiceStatus = practiceStatus;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    }

}
