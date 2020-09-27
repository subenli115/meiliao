package com.ziran.meiliao.ui.bean;

import com.atech.staggedrv.model.StaggedModel;
import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class SpaceRecommendBean extends Result {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements StaggedModel {
        public float getScale() {
            return scale;
        }

        public void setScale(float scale) {
            this.scale = scale;
        }

        /**
         * id : c2f515371ed4fbaa2849220a17ed3850
         * userId : c4beb6acb3635a3210536ae0a292638b
         * userName : 人体xx
         * avatar : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/4e2718fbf4da489ea5d971ba216c591a.jpg
         * nickname : 人体xx
         * title : null
         * content : 464646
         * images : eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6Im91dGluLTMyYmJlOTMxYzY0NTExZWE4NzBhMDAxNjNlMWE2MjVlIiwiRmlsZU5hbWUiOiJzdi9iOGNmMTgwLTE3MzZmNTZkMTQ0L2I4Y2YxODAtMTczNmY1NmQxNDQubXA0In0=
         * address : null
         * videoId : b8cb9c4bf6034cffaa3e1bfd1a5f062d
         * status : 1
         * enclosureType : 1
         * latitude : 4.9E-324
         * longitude : 4.9E-324
         * isOwn : null
         * commentNum : 0
         * browseNum : 0
         * clickNum : 0
         * delFlag : 0
         * createTime : 2020-07-21 11:07:13
         */
        private float scale;
        private String id;
        private String userId;
        private String userName;
        private String avatar;
        private String nickname;
        private Object title;
        private String content;
        private String images;
        private Object address;
        private String videoId;
        @SerializedName("status")
        private int statusX;
        private int enclosureType;
        private String latitude;
        private String longitude;
        private Object isOwn;
        private int commentNum;
        private int browseNum;
        private int clickNum;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        private int width;


        public void setHeight(int height) {
            this.height = height;
        }
        private String delFlag;
        private String createTime;

        private String duration;

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }


        public int getHeight() {
            return height;
        }

        public Object getTitle() {
            return title;
        }

        @Override
        public String getThumb() {
            return null;
        }

        @Override
        public int localResorce() {
            return 0;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public int getStatusX() {
            return statusX;
        }

        public void setStatusX(int statusX) {
            this.statusX = statusX;
        }

        public int getEnclosureType() {
            return enclosureType;
        }

        public void setEnclosureType(int enclosureType) {
            this.enclosureType = enclosureType;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Object getIsOwn() {
            return isOwn;
        }

        public void setIsOwn(Object isOwn) {
            this.isOwn = isOwn;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public int getClickNum() {
            return clickNum;
        }

        public void setClickNum(int clickNum) {
            this.clickNum = clickNum;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
