package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 *  老师的对象
 * Created by Administrator on 2017/2/17.
 */

@Entity
public class AuthorBean implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    /**
     * name : 测试
     */

    private String name;

//    @Transient
    private String descript;
//    @Transient
    private String headImg;

//    @Transient
    private String teaIntro;
    @Transient
    private String availableCash;  //可提现金额
    @Transient
    private String income;  //总收益
    @Transient
    private String fans;  //粉丝数量
    @Transient
    private String intro;  //粉丝数量


    public String getTeaIntro() {
        return teaIntro;
    }

    public void setTeaIntro(String teaIntro) {
        this.teaIntro = teaIntro;
    }

    @Generated(hash = 1838678020)
    public AuthorBean(Long id, String name, String descript, String headImg, String teaIntro) {
        this.id = id;
        this.name = name;
        this.descript = descript;
        this.headImg = headImg;
        this.teaIntro = teaIntro;
    }

    @Generated(hash = 1694633584)
    public AuthorBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }


    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(String availableCash) {
        this.availableCash = availableCash;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    @Override
    public String toString() {
        return "AuthorBean{" + "id=" + id + ", name='" + name + '\'' + ", descript='" + descript + '\'' + ", headImg='" + headImg + '\''
                + ", teaIntro='" + teaIntro + '\'' + ", availableCash='" + availableCash + '\'' + ", income='" + income + '\'' + ", " +
                "fans='" + fans + '\'' + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.descript);
        dest.writeString(this.headImg);
        dest.writeString(this.teaIntro);
        dest.writeString(this.availableCash);
        dest.writeString(this.income);
        dest.writeString(this.fans);
    }

    protected AuthorBean(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.descript = in.readString();
        this.headImg = in.readString();
        this.teaIntro = in.readString();
        this.availableCash = in.readString();
        this.income = in.readString();
        this.fans = in.readString();
    }

    public static final Creator<AuthorBean> CREATOR = new Creator<AuthorBean>() {
        @Override
        public AuthorBean createFromParcel(Parcel source) {
            return new AuthorBean(source);
        }

        @Override
        public AuthorBean[] newArray(int size) {
            return new AuthorBean[size];
        }
    };
}
