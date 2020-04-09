package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/8/20 13:03
 * @des ${工作坊对象}
 * @updateAuthor #author
 * @updateDate 2017/8/20
 * @updateDes ${TODO}
 */
public  class ActisData  extends ShareBean implements Parcelable {

    private String host;
    private long countDown;
    private int status;
    private int tag;
    private String beginTime;
    private boolean isCollect;
    private String endTime;
    private String url;
    private int id;
    private String picture;
    private String title;
    private String price;
    private String address;
    private String signup;
    private String cellPhone;
    private String order;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    private String activityId;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getCellPhone() {
        return cellPhone;
    }
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
    private int mPosition;



    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public long getCountDown() {
        return countDown;
    }

    public void setCountDown(long countDown) {
        this.countDown = countDown;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }


    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public boolean isIsCollect() {
        return isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSignup() {
        return signup;
    }

    public void setSignup(String signup) {
        this.signup = signup;
    }

    @Override
    public String toString() {
        return "ActisData{" + "host='" + host + '\'' + ", countDown=" + countDown + ", status=" + status + ", tag=" + tag + ", " +
                "beginTime='" + beginTime + '\'' + ", isCollect=" + isCollect + ", endTime='" + endTime + '\'' + ", url='" + url + '\'' +
                ", id=" + id + ", picture='" + picture + '\'' + ", title='" + title + '\'' + ", price='" + price + '\'' + ", address='" +
                address + '\'' + ", signup='" + signup + '\'' + ", mPosition=" + mPosition + '}';
    }

    public ActisData() {
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public int getPosition() {
        return mPosition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.host);
        dest.writeLong(this.countDown);
        dest.writeInt(this.status);
        dest.writeInt(this.tag);
        dest.writeString(this.beginTime);
        dest.writeByte(this.isCollect ? (byte) 1 : (byte) 0);
        dest.writeString(this.endTime);
        dest.writeString(this.url);
        dest.writeInt(this.id);
        dest.writeString(this.picture);
        dest.writeString(this.title);
        dest.writeString(this.price);
        dest.writeString(this.address);
        dest.writeString(this.signup);
        dest.writeInt(this.mPosition);
        dest.writeString(this.order);
        dest.writeString(this.cellPhone);
        dest.writeString(this.name);
        dest.writeString(this.time);
    }

    protected ActisData(Parcel in) {
        super(in);
        this.host = in.readString();
        this.countDown = in.readLong();
        this.status = in.readInt();
        this.tag = in.readInt();
        this.beginTime = in.readString();
        this.isCollect = in.readByte() != 0;
        this.endTime = in.readString();
        this.url = in.readString();
        this.id = in.readInt();
        this.picture = in.readString();
        this.title = in.readString();
        this.price = in.readString();
        this.address = in.readString();
        this.signup = in.readString();
        this.mPosition = in.readInt();
        this.order=in.readString();
        this.name=in.readString();
        this.cellPhone=in.readString();
        this.time=in.readString();
    }

    public static final Creator<ActisData> CREATOR = new Creator<ActisData>() {
        @Override
        public ActisData createFromParcel(Parcel source) {
            return new ActisData(source);
        }

        @Override
        public ActisData[] newArray(int size) {
            return new ActisData[size];
        }
    };


    public static ActisData change(SearchItemBean bean){
        ActisData dataBean = new ActisData();
        dataBean.setId(Integer.parseInt(bean.getId()));
        dataBean.setPicture(bean.getPicture());
        dataBean.setUrl(bean.getUrl());
        dataBean.setTitle(bean.getTitle());
        dataBean.setAddress(bean.getAddress());
        dataBean.setBeginTime(bean.getBeginTime());
        dataBean.setEndTime(bean.getEndTime());
        dataBean.setStatus(Integer.parseInt(bean.getStatus()));
        return dataBean;
    }
}