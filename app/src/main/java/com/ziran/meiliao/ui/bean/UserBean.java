package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;



public class UserBean extends Result {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        /**
         * id : cb9143bfbd79c420a1859ceb039e982e
         * userId : 13
         * username : 13883151092
         * password : $2a$10$iwplnWDr4GBhTl3c4h9ZfeRzap6Hy04/vH2cRkFn69nLrcO7dT7IW
         * nickname : 好好好
         * salt : null
         * wxOpenid : null
         * qqOpenid : null
         * createTime : 2020-04-07 14:38:07
         * updateTime : 2020-05-12 11:00:50
         * delFlag : null
         * lockFlag : 0
         * phone : 13883151092
         * avatar : http://yuese.ziran518.com/186dcc99bdd94db598aebee575fe119c.png
         * sex : 1
         * introduce : 用与聚集呵呵红红火火斤斤计较
         * realName : null
         * idCard : null
         * age : 45
         * preference : 1
         * region : 重庆市-重庆市
         * gradeValue : 0
         * label : null
         * userAccount : {"id":"d890d1730402a5fa4378f7de44814c2f","userId":"cb9143bfbd79c420a1859ceb039e982e","money":3,"currency":0.55,"recharge":0}
         * homepageImages : http://yuese.ziran518.com/186dcc99bdd94db598aebee575fe119c.png
         * recommendImages : http://yuese.ziran518.com/186dcc99bdd94db598aebee575fe119c.png
         * latitude : 29.59848055289064
         * longitude : 106.52426310173034
         * status : 0
         * abnormal : null
         * visitorNum : 1
         * likeNum : 0
         * distance : null
         * teenagersIsOpen : 0
         * invitationCode : null
         */

        private String id;
        private int userId;
        private String username;
        private String password;
        private String nickname;
        private Object salt;
        private Object wxOpenid;
        private Object qqOpenid;
        private String createTime;
        private String updateTime;
        private Object delFlag;
        private String lockFlag;
        private String phone;
        private String avatar;
        private int sex;
        private String introduce;
        private Object realName;
        private Object idCard;
        private int age;
        private String preference;
        private String region;
        private int gradeValue;
        private Object label;
        private UserAccountBean userAccount;
        private String homepageImages;
        private String recommendImages;
        private String latitude;
        private String longitude;
        private String status;
        private Object abnormal;
        private int visitorNum;
        private String likeNum;
        private Object distance;
        private String teenagersIsOpen;
        private Object invitationCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getSalt() {
            return salt;
        }

        public void setSalt(Object salt) {
            this.salt = salt;
        }

        public Object getWxOpenid() {
            return wxOpenid;
        }

        public void setWxOpenid(Object wxOpenid) {
            this.wxOpenid = wxOpenid;
        }

        public Object getQqOpenid() {
            return qqOpenid;
        }

        public void setQqOpenid(Object qqOpenid) {
            this.qqOpenid = qqOpenid;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag = delFlag;
        }

        public String getLockFlag() {
            return lockFlag;
        }

        public void setLockFlag(String lockFlag) {
            this.lockFlag = lockFlag;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public Object getRealName() {
            return realName;
        }

        public void setRealName(Object realName) {
            this.realName = realName;
        }

        public Object getIdCard() {
            return idCard;
        }

        public void setIdCard(Object idCard) {
            this.idCard = idCard;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPreference() {
            return preference;
        }

        public void setPreference(String preference) {
            this.preference = preference;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public int getGradeValue() {
            return gradeValue;
        }

        public void setGradeValue(int gradeValue) {
            this.gradeValue = gradeValue;
        }

        public Object getLabel() {
            return label;
        }

        public void setLabel(Object label) {
            this.label = label;
        }

        public UserAccountBean getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(UserAccountBean userAccount) {
            this.userAccount = userAccount;
        }

        public String getHomepageImages() {
            return homepageImages;
        }

        public void setHomepageImages(String homepageImages) {
            this.homepageImages = homepageImages;
        }

        public String getRecommendImages() {
            return recommendImages;
        }

        public void setRecommendImages(String recommendImages) {
            this.recommendImages = recommendImages;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getAbnormal() {
            return abnormal;
        }

        public void setAbnormal(Object abnormal) {
            this.abnormal = abnormal;
        }

        public int getVisitorNum() {
            return visitorNum;
        }

        public void setVisitorNum(int visitorNum) {
            this.visitorNum = visitorNum;
        }

        public String getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(String likeNum) {
            this.likeNum = likeNum;
        }

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
            this.distance = distance;
        }

        public String getTeenagersIsOpen() {
            return teenagersIsOpen;
        }

        public void setTeenagersIsOpen(String teenagersIsOpen) {
            this.teenagersIsOpen = teenagersIsOpen;
        }

        public Object getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(Object invitationCode) {
            this.invitationCode = invitationCode;
        }

    }

}
