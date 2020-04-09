package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.commonutils.LogUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/9 16:42
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/9$
 * @updateDes ${TODO}
 */

public class CrowdFundingPreviewDetailBean {
    private String picture;
    private String title;
    private long startTime;
    private long endTime;
    private String address;
    private int supportMembers;
    private int leftTime;
    private int targetMembers;

    private String name;
    private String avatarUrl;
    private String profile; //老师简介
    private String demo;    //备注


    public void setHead(String picture, String title, String address, int
            supportMembers, int leftTime, int targetMembers) {
        this.picture = picture;
        this.title = title;
        this.address = address;
        this.supportMembers = supportMembers;
        this.leftTime = leftTime;
        this.targetMembers = targetMembers;
    }

    public void setFooter(String name, String avatarUrl, String profile, String demo) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.profile = profile;
        this.demo = demo;

        LogUtils.logd(""+toString());
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSupportMembers() {
        return supportMembers;
    }

    public void setSupportMembers(int supportMembers) {
        this.supportMembers = supportMembers;
    }

    public int getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    public int getTargetMembers() {
        return targetMembers;
    }

    public void setTargetMembers(int targetMembers) {
        this.targetMembers = targetMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "CrowdFundingPreviewDetailBean{" + "picture='" + picture + '\'' + ", title='" + title + '\'' + ", startTime='" + startTime
                + '\'' + ", endTime='" + endTime + '\'' + ", address='" + address + '\'' + ", supportMembers=" + supportMembers + ", " +
                "leftTime=" + leftTime + ", targetMembers=" + targetMembers + ", name='" + name + '\'' + ", avatarUrl='" + avatarUrl + '\''
                + ", profile='" + profile + '\'' + ", demo='" + demo + '\'' + '}';
    }
}
