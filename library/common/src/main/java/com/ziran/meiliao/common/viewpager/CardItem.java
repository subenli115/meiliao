package com.ziran.meiliao.common.viewpager;


public class CardItem {

    private String title;
    private String athor;
    private String pro;
    private String imgUrl;

    public CardItem() {
    }

    public CardItem(String title, String athor, String pro, String imgUrl) {
        this.title = title;
        this.athor = athor;
        this.pro = pro;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "title='" + title + '\'' +
                ", athor='" + athor + '\'' +
                ", pro='" + pro + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAthor() {
        return athor;
    }

    public void setAthor(String athor) {
        this.athor = athor;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}
