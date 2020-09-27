package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;


public class UserBean extends Result {

    /**
     * data : {"id":"c4beb6acb3635a3210536ae0a292638b","userId":658,"username":"13883151092","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"人体xx","salt":null,"wxOpenid":"orsU40-NxvQhQa0YYv8pGlGS1LL4","qqOpenid":"DA8FAD0A6E1DED8A93CBCA9F7A3AA592","createTime":"2020-06-05 15:46:22","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"13883151092","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/4e2718fbf4da489ea5d971ba216c591a.jpg","sex":1,"introduce":"","realName":null,"idCard":null,"age":22,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":{"id":"615ca5dbfb96dfc0fbf1a7f91a3ec83e","userId":"c4beb6acb3635a3210536ae0a292638b","money":3,"currency":0,"recharge":0,"nickname":null,"todayProfit":null},"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/4e2718fbf4da489ea5d971ba216c591a.jpg","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/4e2718fbf4da489ea5d971ba216c591a.jpg","latitude":"29.598319","longitude":"106.524232","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":0,"likeNum":"0","distance":null,"teenagersIsOpen":"1","channelNo":"baidu","versionNo":"1.0.0","isFollow":null,"userTables":[],"isReal":"1","invitationCode":"fr1orr"}
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
         * id : c4beb6acb3635a3210536ae0a292638b
         * userId : 658
         * username : 13883151092
         * password : $2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze
         * nickname : 人体xx
         * salt : null
         * wxOpenid : orsU40-NxvQhQa0YYv8pGlGS1LL4
         * qqOpenid : DA8FAD0A6E1DED8A93CBCA9F7A3AA592
         * createTime : 2020-06-05 15:46:22
         * updateTime : 2020-07-15 15:09:15
         * delFlag : 0
         * lockFlag : 0
         * phone : 13883151092
         * avatar : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/4e2718fbf4da489ea5d971ba216c591a.jpg
         * sex : 1
         * introduce :
         * realName : null
         * idCard : null
         * age : 22
         * preference : 2
         * region : 重庆市-重庆市
         * gradeValue : 0
         * offline : 0
         * userAccount : {"id":"615ca5dbfb96dfc0fbf1a7f91a3ec83e","userId":"c4beb6acb3635a3210536ae0a292638b","money":3,"currency":0,"recharge":0,"nickname":null,"todayProfit":null}
         * homepageImages : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/4e2718fbf4da489ea5d971ba216c591a.jpg
         * recommendImages : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/4e2718fbf4da489ea5d971ba216c591a.jpg
         * latitude : 29.598319
         * longitude : 106.524232
         * personality : null
         * shape : null
         * height : null
         * objective : null
         * click : null
         * video : null
         * voice : null
         * job : null
         * annualSalary : null
         * industry : null
         * major : null
         * school : null
         * education : null
         * constellation : null
         * contactStatus : null
         * status : 0
         * abnormal : null
         * visitorNum : 0
         * likeNum : 0
         * distance : null
         * teenagersIsOpen : 1
         * channelNo : baidu
         * versionNo : 1.0.0
         * isFollow : null
         * userTables : []
         * isReal : 1
         * invitationCode : fr1orr
         */

        private String id;
        private int userId;
        private String username;
        private String password;
        private String nickname;
        private Object salt;
        private String wxOpenid;
        private String qqOpenid;
        private String createTime;
        private String updateTime;
        private String delFlag;
        private String lockFlag;
        private String phone;
        private String avatar;
        private int sex;
        private String introduce;
        private String realName;
        private String realImg;
        private Object idCard;

        private int age;
        private String preference;
        private String region;
        private int gradeValue;
        private int offline;
        private UserAccountBean.DataBean userAccount;
        private String homepageImages;
        private String recommendImages;
        private String latitude;
        private String longitude;
        private Object personality;
        private Object shape;
        private Object height;
        private Object objective;
        private String click;
        private String video;
        private String voice;
        private Object job;
        private Object annualSalary;
        private Object industry;
        private Object major;
        private Object school;
        private Object education;
        private Object constellation;
        private Object contactStatus;
        private String status;
        private Object abnormal;
        private int visitorNum;
        private String likeNum;
        private String distance;
        private String teenagersIsOpen;
        private String channelNo;
        private String versionNo;
        private Object isFollow;
        private String isReal;
        private String invitationCode;
        private List<?> userTables;
        private String relationship;
        private String isClick;
        private String more;
        private String voiceDuration;
        private String videoCover;

        public String getRealImg() {
            return realImg;
        }

        public void setRealImg(String realImg) {
            this.realImg = realImg;
        }

        public String getVideoCover() {
            return videoCover;
        }

        public void setVideoCover(String videoCover) {
            this.videoCover = videoCover;
        }


        public String getVoiceDuration() {
            return voiceDuration;
        }

        public void setVoiceDuration(String voiceDuration) {
            this.voiceDuration = voiceDuration;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public String getIsClick() {
            return isClick;
        }

        public void setIsClick(String isClick) {
            this.isClick = isClick;
        }

        public String getRelationship() {
            return relationship;
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }

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

        public String getWxOpenid() {
            return wxOpenid;
        }

        public void setWxOpenid(String wxOpenid) {
            this.wxOpenid = wxOpenid;
        }

        public String getQqOpenid() {
            return qqOpenid;
        }

        public void setQqOpenid(String qqOpenid) {
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

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
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

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
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
            if(region.length()>3){
                return region.substring(0,3);
            }else {
                return region;
            }
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

        public int getOffline() {
            return offline;
        }

        public void setOffline(int offline) {
            this.offline = offline;
        }

        public UserAccountBean.DataBean getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(UserAccountBean.DataBean userAccount) {
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

        public Object getPersonality() {
            return personality;
        }

        public void setPersonality(Object personality) {
            this.personality = personality;
        }

        public Object getShape() {
            return shape;
        }

        public void setShape(Object shape) {
            this.shape = shape;
        }

        public Object getHeight() {
            return height;
        }

        public void setHeight(Object height) {
            this.height = height;
        }

        public Object getObjective() {
            return objective;
        }

        public void setObjective(Object objective) {
            this.objective = objective;
        }

        public String getClick() {
            return click;
        }

        public void setClick(String click) {
            this.click = click;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getVoice() {
            return voice;
        }

        public void setVoice(String voice) {
            this.voice = voice;
        }

        public Object getJob() {
            return job;
        }

        public void setJob(Object job) {
            this.job = job;
        }

        public Object getAnnualSalary() {
            return annualSalary;
        }

        public void setAnnualSalary(Object annualSalary) {
            this.annualSalary = annualSalary;
        }

        public Object getIndustry() {
            return industry;
        }

        public void setIndustry(Object industry) {
            this.industry = industry;
        }

        public Object getMajor() {
            return major;
        }

        public void setMajor(Object major) {
            this.major = major;
        }

        public Object getSchool() {
            return school;
        }

        public void setSchool(Object school) {
            this.school = school;
        }

        public Object getEducation() {
            return education;
        }

        public void setEducation(Object education) {
            this.education = education;
        }

        public Object getConstellation() {
            return constellation;
        }

        public void setConstellation(Object constellation) {
            this.constellation = constellation;
        }

        public Object getContactStatus() {
            return contactStatus;
        }

        public void setContactStatus(Object contactStatus) {
            this.contactStatus = contactStatus;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String statusX) {
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getTeenagersIsOpen() {
            return teenagersIsOpen;
        }

        public void setTeenagersIsOpen(String teenagersIsOpen) {
            this.teenagersIsOpen = teenagersIsOpen;
        }

        public String getChannelNo() {
            return channelNo;
        }

        public void setChannelNo(String channelNo) {
            this.channelNo = channelNo;
        }

        public String getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(String versionNo) {
            this.versionNo = versionNo;
        }

        public Object getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(Object isFollow) {
            this.isFollow = isFollow;
        }

        public String getIsReal() {
            return isReal;
        }

        public void setIsReal(String isReal) {
            this.isReal = isReal;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }

        public List<?> getUserTables() {
            return userTables;
        }

        public void setUserTables(List<?> userTables) {
            this.userTables = userTables;
        }

    }
}
