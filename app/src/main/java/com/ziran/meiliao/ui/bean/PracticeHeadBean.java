package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2018/9/18.
 */

public class PracticeHeadBean extends Result{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {


        /**
         * id : 1
         * headImg : http://www.dgli.net/resource/images/practiceActivity/defaultU.png
         * status : 1
         * name : 卖骚练习
         * shareTitle :
         * shareUrl :
         * sharePic :
         * userName : 用户4584643
         * guideMusicId : 1
         * shareDescribe :
         * guideMusic : 默认
         */

        private int id;
        private String headImg;
        private int status;
        private String name;
        private String shareTitle;
        private String shareUrl;
        private String sharePic;
        private String userName;
        private int guideMusicId;
        private String shareDescribe;
        private String guideMusic;
        private int firstStatus;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        private String picture;
        private String startTime;
        private int days;

        public int getDays() {
            return days;
        }
        public void setDays(int days) {
            this.days = days;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }
        public int getFirstStatus() {
            return firstStatus;
        }

        public void setFirstStatus(int firstStatus) {
            this.firstStatus = firstStatus;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getSharePic() {
            return sharePic;
        }

        public void setSharePic(String sharePic) {
            this.sharePic = sharePic;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getGuideMusicId() {
            return guideMusicId;
        }

        public void setGuideMusicId(int guideMusicId) {
            this.guideMusicId = guideMusicId;
        }

        public String getShareDescribe() {
            return shareDescribe;
        }

        public void setShareDescribe(String shareDescribe) {
            this.shareDescribe = shareDescribe;
        }

        public String getGuideMusic() {
            return guideMusic;
        }

        public void setGuideMusic(String guideMusic) {
            this.guideMusic = guideMusic;
        }
    }
}
