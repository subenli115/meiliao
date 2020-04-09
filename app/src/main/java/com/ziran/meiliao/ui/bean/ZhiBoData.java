package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/8/20 13:06
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/8/20
 * @updateDes ${TODO}
 */

public  class ZhiBoData {
    /**
     * id : 49
     * title : 第二堂课 沟通，不保证和谐；但不沟通，一定不和谐
     * status : 0
     * countDown : 1503137580000
     */

    private int id;
    private String title;
    @SerializedName("status")
    private int statusX;
    private long countDown;
    private int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatusX() {
        return statusX;
    }

    public void setStatusX(int statusX) {
        this.statusX = statusX;
    }

    public long getCountDown() {
        return countDown;
    }

    public void setCountDown(long countDown) {
        this.countDown = countDown;
    }

    @Override
    public String toString() {
        return "ZhiBoData{" + "id=" + id + ", title='" + title + '\'' + ", statusX=" + statusX + ", countDown=" + countDown + '}';
    }
}