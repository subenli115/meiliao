package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 */

public class TrailerBean extends Result {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * picture :
         * id : 1
         * author : {"name":"陈先生"}
         * title : 课程标题
         * descript : 我是简介
         */

        private String picture;
        private String id;
        private AuthorBean author;
        private String title;
        private String descript;
        private String url;
        private boolean isCollect;

        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicture() {
            return picture;
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

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.picture);
            dest.writeString(this.id);
            dest.writeParcelable(this.author, flags);
            dest.writeString(this.title);
            dest.writeString(this.descript);
            dest.writeString(this.url);
            dest.writeByte(this.isCollect ? (byte) 1 : (byte) 0);
        }

        protected DataBean(Parcel in) {
            this.picture = in.readString();
            this.id = in.readString();
            this.author = in.readParcelable(AuthorBean.class.getClassLoader());
            this.title = in.readString();
            this.descript = in.readString();
            this.url = in.readString();
            this.isCollect = in.readByte() != 0;
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }


    public static TrailerBean.DataBean changeData(SJKSingeLiveData sjkLiveData) {
        TrailerBean.DataBean dataBean = new DataBean();
        dataBean.setCollect(true);
        dataBean.setDescript(String.valueOf(sjkLiveData.getTag()));
        dataBean.setTitle(sjkLiveData.getTitle());
        dataBean.setId(sjkLiveData.getId());
        dataBean.setAuthor(sjkLiveData.getAuthor());
        dataBean.setUrl(sjkLiveData.getUrl());
        dataBean.setPicture(sjkLiveData.getPicture());
        return dataBean;
    }
}
