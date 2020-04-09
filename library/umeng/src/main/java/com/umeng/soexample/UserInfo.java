package com.umeng.soexample;

import java.util.Map;

public class UserInfo {
    private String platName;
    private String unionid;
    private String userName;
    private String userIcon;
    private String userGender;
    private String city;
    private String country;
    private String province;


    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String userId) {
        this.unionid = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
//        if (!TextUtils.isEmpty(userGender)) {
//            if ("m".equals(userGender)) {
//            } else {
//                this.userGender = "女";
//            }
//        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public static UserInfo parse(Map<String, String> map,String platformName) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(map.get("name"));
        userInfo.setUserGender(map.get("gender"));
        userInfo.setUserIcon(map.get("iconurl"));
        userInfo.setUnionid(map.get("uid"));
        userInfo.setPlatName(platformName);
        if (map.containsKey("city")){
            userInfo.setCity(map.get("city"));
        }
        if (map.containsKey("province")){
            userInfo.setProvince(map.get("province"));
        }
        if (map.containsKey("country")){
            userInfo.setCountry(map.get("country"));
        }
        return userInfo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "platName='" + platName + '\'' +
                ", unionid='" + unionid + '\'' +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userGender='" + userGender + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
