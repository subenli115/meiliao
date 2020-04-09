package com.ziran.meiliao.entry;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 用户资料的bean
 * Created by Administrator on 2017/1/10.
 */

@Entity
public class UserInfo implements Parcelable {

    @Id
    private Long id;

    private String sex;
    private String lastUpdateTime;
    private int headImgVersion;
    private int uniId;
    private String descript;
    private String country;
    private String provience;
    private String city;
    private String identity;
    private String headImg;
    private String email;
    private String nickName;
    private String age;


    private String  realName;
    private String identityImage;
    private boolean isValidate;
//    @Transient
    private boolean isTeacher  ;
//    @Transient
    private String phone;

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    private String career;
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public int getUniId() {
        return uniId;
    }

    public void setUniId(int uniId) {
        this.uniId = uniId;
    }

    public boolean getIsValidate() {
        return this.isValidate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsValidate(boolean isValidate) {
        this.isValidate = isValidate;
    }

    public String getIdentityImage() {
        return this.identityImage;
    }

    public void setIdentityImage(String identityImage) {
        this.identityImage = identityImage;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImg() {
        return this.headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getIdentity() {
        return this.identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvience() {
        return this.provience;
    }

    public void setProvience(String provience) {
        this.provience = provience;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescript() {
        return this.descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getHeadImgVersion() {
        return this.headImgVersion;
    }

    public void setHeadImgVersion(int headImgVersion) {
        this.headImgVersion = headImgVersion;
    }

    public String getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", sex='" + sex + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", headImgVersion=" + headImgVersion +
                ", uniId=" + uniId +
                ", descript='" + descript + '\'' +
                ", country='" + country + '\'' +
                ", provience='" + provience + '\'' +
                ", city='" + city + '\'' +
                ", identity='" + identity + '\'' +
                ", headImg='" + headImg + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age='" + age + '\'' +
                ", identityImage='" + identityImage + '\'' +
                ", isValidate=" + isValidate +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static UserInfo GUSET() {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName("游客");
        return userInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.sex);
        dest.writeString(this.lastUpdateTime);
        dest.writeInt(this.headImgVersion);
        dest.writeString(this.descript);
        dest.writeString(this.country);
        dest.writeString(this.provience);
        dest.writeString(this.city);
        dest.writeString(this.identity);
        dest.writeString(this.headImg);
        dest.writeString(this.email);
        dest.writeString(this.nickName);
        dest.writeString(this.age);
        dest.writeString(this.realName);
        dest.writeString(this.identityImage);
        dest.writeByte(this.isValidate ? (byte) 1 : (byte) 0);
    }

    public boolean getIsTeacher() {
        return this.isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }


    protected UserInfo(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.sex = in.readString();
        this.lastUpdateTime = in.readString();
        this.headImgVersion = in.readInt();
        this.descript = in.readString();
        this.country = in.readString();
        this.provience = in.readString();
        this.city = in.readString();
        this.identity = in.readString();
        this.headImg = in.readString();
        this.email = in.readString();
        this.realName = in.readString();
        this.nickName = in.readString();
        this.age = in.readString();
        this.identityImage = in.readString();
        this.isValidate = in.readByte() != 0;
    }

    @Generated(hash = 497953858)
    public UserInfo(Long id, String sex, String lastUpdateTime, int headImgVersion, int uniId,
            String descript, String country, String provience, String city, String identity,
            String headImg, String email, String nickName, String age, String realName,
            String identityImage, boolean isValidate, boolean isTeacher, String phone, String career) {
        this.id = id;
        this.sex = sex;
        this.lastUpdateTime = lastUpdateTime;
        this.headImgVersion = headImgVersion;
        this.uniId = uniId;
        this.descript = descript;
        this.country = country;
        this.provience = provience;
        this.city = city;
        this.identity = identity;
        this.headImg = headImg;
        this.email = email;
        this.nickName = nickName;
        this.age = age;
        this.realName = realName;
        this.identityImage = identityImage;
        this.isValidate = isValidate;
        this.isTeacher = isTeacher;
        this.phone = phone;
        this.career = career;
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
