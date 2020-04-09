package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/8 16:17
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/8$
 * @updateDes ${TODO}
 */

public class TopBean implements Parcelable {

    private int targetMembers;
    private int staus;
    @SerializedName("status")
    private int statusX;
    private int supportMembers;
    private int leftTime;
    private long endTime;
    private String intro;
    private int id;
    private String picture;
    private long startTime;
    private String title;
    private double avgPrice;
    private String address;
    private String totalTime;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTargetMembers() {
        return targetMembers;
    }

    public void setTargetMembers(int targetMembers) {
        this.targetMembers = targetMembers;
    }

    public int getStaus() {
        return staus;
    }

    public void setStaus(int staus) {
        this.staus = staus;
    }

    public int getStatusX() {
        return statusX;
    }

    public void setStatusX(int statusX) {
        this.statusX = statusX;
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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.targetMembers);
        dest.writeInt(this.staus);
        dest.writeInt(this.statusX);
        dest.writeInt(this.supportMembers);
        dest.writeInt(this.leftTime);
        dest.writeLong(this.endTime);
        dest.writeString(this.intro);
        dest.writeInt(this.id);
        dest.writeString(this.picture);
        dest.writeLong(this.startTime);
        dest.writeString(this.title);
        dest.writeDouble(this.avgPrice);
        dest.writeString(this.address);
        dest.writeString(this.totalTime);
    }

    public TopBean() {
    }

    protected TopBean(Parcel in) {
        this.targetMembers = in.readInt();
        this.staus = in.readInt();
        this.statusX = in.readInt();
        this.supportMembers = in.readInt();
        this.leftTime = in.readInt();
        this.endTime = in.readLong();
        this.intro = in.readString();
        this.id = in.readInt();
        this.picture = in.readString();
        this.startTime = in.readLong();
        this.title = in.readString();
        this.avgPrice = in.readDouble();
        this.address = in.readString();
        this.totalTime = in.readString();
    }

    public static final Parcelable.Creator<TopBean> CREATOR = new Parcelable.Creator<TopBean>() {
        @Override
        public TopBean createFromParcel(Parcel source) {
            return new TopBean(source);
        }

        @Override
        public TopBean[] newArray(int size) {
            return new TopBean[size];
        }
    };
}
