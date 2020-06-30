package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 媒体实体类
 */
public class UserRegBean implements Parcelable {


    private String age;
    private String avatar;
    private String sex;
    private String nickname;
    private String region;

    public UserRegBean() {

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
    }
    public static final Creator<UserRegBean> CREATOR = new Creator<UserRegBean>() {
        @Override
        public UserRegBean[] newArray(int size) {
            return new UserRegBean[size];
        }

        @Override
        public UserRegBean createFromParcel(Parcel in) {
            return new UserRegBean(in);
        }
    };
    public UserRegBean(Parcel in) {
        age = in.readString();
        avatar = in.readString();
        sex = in.readString();
        nickname = in.readString();
        region = in.readString();
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