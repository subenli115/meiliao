package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;

/**
 *  专辑对象
 */

public class AlbumBean {
    /**
     * collectCount : 1
     * detail : 这个专辑很不错
     * title : 七天睡眠
     * headImg : null
     * name : 玛吉薇蒳
     * isCollectAlbum : false
     * shareUrl :
     */
    private int collectCount;
    private int listenCount;
    private String headImg;
    private boolean isCollectAlbum;
    private String shareUrl;
    private String detail;
    private String sharePic;
    private String shareDescript;
    private String shareTitle;
    private String title;
    private String albumId;
    private String name;
    private boolean isBuy;
    private String id;
    private String picture;
    private String type;
    private float price;
    private boolean ableUsedCoupon;
    private String usedCouponId;
    private float usedCouponPrice;
    private boolean isCheckIsBuy;
    private String vip;
    private float faceValue;
    private int shareTimes;
    private int finishTimes;
    private String bg;
    private String intro;
    private String zf;
    private String descript;
    private int userCoin;
    private int needCoin;
    private String roundPic;
    private float memberPrice;
    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public String getShareDescript() {
        return shareDescript;
    }

    public void setShareDescript(String shareDescript) {
        this.shareDescript = shareDescript;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }
    public float getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(float memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getLevelDetail() {
        return levelDetail;
    }

    public void setLevelDetail(String levelDetail) {
        this.levelDetail = levelDetail;
    }

    private String levelDetail;

    public int getFinishTimes() {
        return finishTimes;
    }

    public void setTimes(int times) {
        this.finishTimes = times;
    }

    public String getZf() {
        return zf;
    }

    public void setZf(String zf) {
        this.zf = zf;
    }

    public int getUserCoin() {
        return userCoin;
    }

    public void setUserCoin(int userCoin) {
        this.userCoin = userCoin;
    }

    public int getNeedCoin() {
        return needCoin;
    }

    public void setNeedCoin(int needCoin) {
        this.needCoin = needCoin;
    }

    public String getAlbumImage() {
        return zf;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public void setFaceValue(float faceValue) {
        this.faceValue = faceValue;
    }

    public void setAlbumImage(String albumImage) {
        this.zf = albumImage;
    }

    public String getIntro() {
        return EmptyUtils.isNotEmpty(intro) ? intro : "";
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public int getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(int shareTimes) {
        this.shareTimes = shareTimes;
    }

    public float getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public boolean isAbleUsedCoupon() {
        return ableUsedCoupon;
    }

    public void setAbleUsedCoupon(boolean ableUsedCoupon) {
        this.ableUsedCoupon = ableUsedCoupon;
    }

    public String getUsedCouponId() {
        return usedCouponId;
    }

    public void setUsedCouponId(String usedCouponId) {
        this.usedCouponId = usedCouponId;
    }

    public float getUsedCouponPrice() {
        return usedCouponPrice;
    }

    public void setUsedCouponPrice(float usedCouponPrice) {
        this.usedCouponPrice = usedCouponPrice;
    }

    public boolean isCheckIsBuy() {
        return isCheckIsBuy;
    }

    public void setCheckIsBuy(boolean checkIsBuy) {
        isCheckIsBuy = checkIsBuy;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public String getRoundPic() {
        return roundPic;
    }

    public void setRoundPic(String roundPic) {
        this.roundPic = roundPic;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getRealAlbumId() {
        return EmptyUtils.isNotEmpty(albumId) ? albumId : id;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public int getListenCount() {
        return listenCount;
    }

    public void setListenCount(int listenCount) {
        this.listenCount = listenCount;
    }

    @Override
    public String toString() {
        return "AlbumBean{" + "collectCount=" + collectCount + ", listenCount=" + listenCount + ", headImg='" + headImg + '\'' + ", isCollectAlbum=" + isCollectAlbum + ", " +
                "shareUrl='" + shareUrl + '\'' + ", detail='" + detail + '\'' + ", title='" + title + '\'' + ", albumId='" + albumId + '\'' + ", name='" + name + '\'' + ", " +
                "isBuy=" + isBuy + ", id='" + id + '\'' + ", picture='" + picture + '\'' + ", type='" + type + '\'' + ", price=" + price + ", ableUsedCoupon=" + ableUsedCoupon +
                ", usedCouponId='" + usedCouponId + '\'' + ", usedCouponPrice=" + usedCouponPrice + ", isCheckIsBuy=" + isCheckIsBuy + ", vip='" + vip + '\'' + ", faceValue=" +
                faceValue + ", shareTimes=" + shareTimes + ", finishTimes=" + finishTimes + ", bg='" + bg + '\'' + ", intro='" + intro + '\'' + ", zf='" + zf + '\'' + ", " +
                "descript='" + descript + '\'' + ", userCoin=" + userCoin + ", needCoin=" + needCoin + ", roundPic='" + roundPic + '\'' + '}';
    }

    public boolean isCollectAlbum() {
        return isCollectAlbum;
    }

    public void setCollectAlbum(boolean collectAlbum) {
        isCollectAlbum = collectAlbum;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsCollectAlbum() {
        return isCollectAlbum;
    }

    public void setIsCollectAlbum(boolean isCollectAlbum) {
        this.isCollectAlbum = isCollectAlbum;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

}