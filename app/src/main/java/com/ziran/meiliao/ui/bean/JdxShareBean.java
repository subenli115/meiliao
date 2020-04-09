package com.ziran.meiliao.ui.bean;


import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

/**
 *         mbsr分享.
 */

    public class JdxShareBean extends Result {

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean implements Parcelable {

            /**
             * duration : 23:00
             * title : 《MBSR八周练习》第3周——不悦体验日历
             * userPic : http://www.dgli.net/upload/images/userHeadImg/defaultU.png
             * words : 我不想跟大傻逼说话，就这样
             * hisId : 27
             * userName : 用户0194651
             */

            private String duration;
            private String title;
            private String userPic;
            private String words;
            private int hisId;
            private String picture;
            private String userName;
            public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel in) {
                    return new DataBean(in);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUserPic() {
                return userPic;
            }

            public void setUserPic(String userPic) {
                this.userPic = userPic;
            }

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            public int getHisId() {
                return hisId;
            }

            public void setHisId(int hisId) {
                this.hisId = hisId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.duration);
                dest.writeInt(this.hisId);
                dest.writeString(this.title);
                dest.writeString(this.userPic);
                dest.writeString(this.words);
                dest.writeString(this.userName);
                dest.writeString(this.picture);
            }

            public DataBean() {
            }

            protected DataBean(Parcel in) {
                this.duration = in.readString();
                this.hisId = in.readInt();
                this.title = in.readString();
                this.userPic = in.readString();
                this.words = in.readString();
                this.userName = in.readString();
                this.picture = in.readString();
            }

        }
    }
