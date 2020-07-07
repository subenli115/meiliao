package com.ziran.meiliao.ui.bean;


import androidx.annotation.NonNull;

import com.ziran.meiliao.constant.AppConstant;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/12/4 22:15
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/12/4
 * @updateDes ${TODO}
 */

public class OrderDetailBean implements Comparable<OrderDetailBean> {
    private String orderId;
    private String title;
    private AuthorBean author;
    private String address;
    private String time;
    private String tag;
    private int statusType = 0;
    private String name;
    private String phone;
    private String email;
    private String efftime;
    private int totalPeople;
    private int currentPeople;
    private int lastDay;
    private String profile;
    private String servicePhone;
    private int orderTypeId;
    private String orderTypeName;
    private String picture;
    private long createTime;
    private double price;
    private String statusMsg;
    private String courserId;
    private int crowdFundId;


    public int getOrderTypeId() {
        return orderTypeId;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public void setOrderTypeId(int orderTypeId) {
        this.orderTypeId = orderTypeId;
    }


    public OrderDetailBean() {
    }

    public OrderDetailBean(int type, int index) {
        setOrderId(index + 1);
        setTime("2017-10-24");
        setTitle("10天深度睡眠");
        setPicture(AppConstant.URL);
        setPrice(50 + index);
        setOrderNumber("99999" + index);
        switch (type) {
            case 1:
                setTag("团建课程");
                setStatus(index % 3);
                setAuthor(new AuthorBean());
                setEfftime("2017-07-13");
                setStatus(index % 3);
                break;
            case 2:
                setTag(index % 2 == 0 ? "参与众筹" : "发起众筹");
                setName("Ariel" + index);
                setPhone("15999999999");
                setEmail("15999999999@139.com");
                setProfile("简介简介简介简介简介简介简介简介简介简");
                setServicePhone("311111111");
                setTotalPeople(88);
                setCurrentPeople(60);
                setLastDay(30 - index);
                setStatus(index % 3);

                break;
            case 3:
                setTag(index % 3 == 0 ? "自由报名" : index % 3 == 1 ? "发起拼团" : "参与拼团");
                setStatus(index % 5);
                setName("Ariel" + index);
                setPhone("15999999999");
                setEmail("15999999999@139.com");
                break;
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = String.valueOf(orderId);
    }


    public int getStatusType() {
        return statusType;
    }

    public void setStatusType(int statusType) {
        this.statusType = statusType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getCourserId() {
        return courserId;
    }

    public void setCourserId(String courserId) {
        this.courserId = courserId;
    }

    public int getCrowdFundId() {
        return crowdFundId;
    }

    public void setCrowdFundId(int crowdFundId) {
        this.crowdFundId = crowdFundId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getOrderNumber() {
        return orderId;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderId = orderNumber;
    }

    public int getStatus() {
        return statusType;
    }

    public void setStatus(int status) {
        this.statusType = status;
    }

    public int getPrice() {
        return (int) price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEfftime() {
        return efftime;
    }

    public void setEfftime(String efftime) {
        this.efftime = efftime;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        this.totalPeople = totalPeople;
    }

    public int getCurrentPeople() {
        return currentPeople;
    }

    public void setCurrentPeople(int currentPeople) {
        this.currentPeople = currentPeople;
    }

    public int getLastDay() {
        return lastDay;
    }

    public void setLastDay(int lastDay) {
        this.lastDay = lastDay;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    @Override
    public String toString() {
        return "OrderDetailBean{" + "orderId='" + orderId + '\'' + ", title='" + title + '\'' + ", author=" + author + '\'' + ", " +
                "address='" + address + '\'' + ", time='" + time + '\'' + ", tag='" + tag + '\'' + ", orderNumber='" + orderId + '\'' +
                ", status=" + statusType + ", name='" + name + '\'' + ", phone='" + phone + '\'' + ", email='" + email + '\'' + ", " +
                "efftime='" + efftime + '\'' + ", totalPeople=" + totalPeople + ", currentPeople=" + currentPeople + ", " + "lastDay=" +
                lastDay + ", profile='" + profile + '\'' + ", servicePhone='" + servicePhone + '\'' + ", picture='" + picture + '\'' + "," +
                " createTime=" + createTime + ", price=" + price + ", statusMsg='" + statusMsg + '\'' + ", courserId=" + courserId + ", " +
                "crowdFundId=" + crowdFundId + '}';
    }


    @Override
    public int compareTo(@NonNull OrderDetailBean o2) {
        return (int) (o2.getCreateTime()-getCreateTime()  );
    }
}
