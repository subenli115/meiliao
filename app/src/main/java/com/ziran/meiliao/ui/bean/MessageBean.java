package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */

public class MessageBean extends Result {

    private String imgUrl;
    private String title;
    private String time;
    private List<DataBean> data;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * picture : null
         * id : 1
         * createTime : 2017-02-06
         * title : 消息1
         * url : null
         */

        private String  picture;
        private int id;
        private String createTime;
        @SerializedName("title")
        private String titleX;
        private String url;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return titleX;
        }

        public void setTitle(String titleX) {
            this.titleX = titleX;
        }

        public String  getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "picture='" + picture + '\'' +
                    ", id=" + id +
                    ", createTime='" + createTime + '\'' +
                    ", titleX='" + titleX + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", data=" + data +
                '}';
    }
}
