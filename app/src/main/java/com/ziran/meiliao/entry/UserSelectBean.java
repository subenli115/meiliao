package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 媒体实体类
 */
public class UserSelectBean implements Parcelable {


    private String age;
    private String avatar;
    private String sex;
    private String nickname;
    private String region;
    private String objective;
    private String constellation;
    private String figure;
    private String online="";

    public String getLeftHeight() {
        return leftHeight;
    }

    public void setLeftHeight(String leftHeight) {
        this.leftHeight = leftHeight;
    }

    public String getRightHeight() {
        return rightHeight;
    }

    public void setRightHeight(String rightHeight) {
        this.rightHeight = rightHeight;
    }

    public String getLeftAge() {
        return leftAge;
    }

    public void setLeftAge(String leftAge) {
        this.leftAge = leftAge;
    }

    public String getRightAge() {
        return rightAge;
    }

    public void setRightAge(String rightAge) {
        this.rightAge = rightAge;
    }

    private String leftHeight;
    private String rightHeight;
    private String leftAge;
    private String rightAge;


    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }



    public UserSelectBean() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(age);
        dest.writeString(avatar);
        dest.writeString(sex);
        dest.writeString(nickname);
        dest.writeString(region);
        dest.writeString(objective);
        dest.writeString(constellation);
        dest.writeString(figure);
        dest.writeString(online);
        dest.writeString(leftAge);
        dest.writeString(leftHeight);
        dest.writeString(rightHeight);
        dest.writeString(rightAge);
    }
    public static final Creator<UserSelectBean> CREATOR = new Creator<UserSelectBean>() {
        @Override
        public UserSelectBean[] newArray(int size) {
            return new UserSelectBean[size];
        }

        @Override
        public UserSelectBean createFromParcel(Parcel in) {
            return new UserSelectBean(in);
        }
    };
    public UserSelectBean(Parcel in) {
        age = in.readString();
        avatar = in.readString();
        sex = in.readString();
        nickname = in.readString();
        region = in.readString();
        objective = in.readString();
        constellation = in.readString();
        figure = in.readString();
        online = in.readString();
        leftAge = in.readString();
        leftHeight = in.readString();
        rightHeight = in.readString();
        rightAge = in.readString();

    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}