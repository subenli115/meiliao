package com.ziran.meiliao.ui.bean;

/**
 * Created by Administrator on 2017/2/24.
 */

public  class AlbumListBean {
    /**
     * picture : 1.png
     * id : 1
     * author : {"name":"陈先生"}
     * detail : 这是音频的简介
     * title : 10天深度睡眠
     * listenCount : 8888
     */
    private boolean isHead;
    private String picture;
    private int id;
    private String vip;
    private AuthorBean author;
    private String title;
    private String detail;
    private int listenCount;
    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
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

    public int getListenCount() {
        return listenCount;
    }

    public void setListenCount(int listenCount) {
        this.listenCount = listenCount;
    }


    @Override
    public String toString() {
        return "AlbumListBean{" +
                "isHead=" + isHead +
                ", picture='" + picture + '\'' +
                ", id=" + id +
                ", author=" + author +
                ", detail='" + detail + '\'' +
                ", title='" + title + '\'' +
                ", listenCount=" + listenCount +
                '}';
    }

    public static AlbumListBean addHead(String title){
        AlbumListBean albumBean = new AlbumListBean();
        albumBean.setHead(true);
        albumBean.setTitle(title);
        return albumBean;
    }
}