package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/7 10:10
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/7$
 * @updateDes ${TODO}
 */

public class LiveRoomBean extends Result {

    /**
     * data : {"historyList":[],"heraldList":[{"picture":"https://www.dgli.net/resource/images/course/course_teacher_zhangshijie.png","id":136,"title":"舒志凌要开直播了，各位看官请注意",
     * .net/resource/images/subscription/course_teacher_hu.png","teacherName":"舒志凌","teacherId":10,"fansNumbers":0}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * historyList : []
         * heraldList : [{"picture":"https://www.dgli.net/resource/images/course/course_teacher_zhangshijie.png","id":136,"title":"舒志凌要开直播了，各位看官请注意","status":1,
         * picture : https://www.dgli.net/resource/images/subscription/course_teacher_hu.png
         * teacherName : 舒志凌
         * teacherId : 10
         * fansNumbers : 0
         */

        private String picture;
        private String teacherName;
        private int teacherId;
        private int fansNumbers;
        private List<HeraldListBean> historyList;
        private List<HeraldListBean> heraldList;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public int getFansNumbers() {
            return fansNumbers;
        }

        public void setFansNumbers(int fansNumbers) {
            this.fansNumbers = fansNumbers;
        }

        public List<HeraldListBean> getHistoryList() {
            return historyList;
        }

        public void setHistoryList(List<HeraldListBean> historyList) {
            this.historyList = historyList;
        }

        public List<HeraldListBean> getHeraldList() {
            return heraldList;
        }

        public void setHeraldList(List<HeraldListBean> heraldList) {
            this.heraldList = heraldList;
        }

        public static class HeraldListBean implements Parcelable {

            /**
             * picture : https://www.dgli.net/resource/images/course/course_teacher_zhangshijie.png
             * id : 136
             * title : 舒志凌要开直播了，各位看官请注意
             * status : 1
             * dateTime : 1527148766000
             */

            private String picture;
            private int id;
            private String title;
            @SerializedName("status")
            private int statusX;
            private long dateTime;
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

            public long getDateTime() {
                return dateTime;
            }

            public void setDateTime(long dateTime) {
                this.dateTime = dateTime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.picture);
                dest.writeInt(this.id);
                dest.writeString(this.title);
                dest.writeInt(this.statusX);
                dest.writeLong(this.dateTime);
                dest.writeString(this.url);
            }

            public HeraldListBean() {
            }

            protected HeraldListBean(Parcel in) {
                this.picture = in.readString();
                this.id = in.readInt();
                this.title = in.readString();
                this.statusX = in.readInt();
                this.dateTime = in.readLong();
                this.url = in.readString();
            }

            public static final Parcelable.Creator<HeraldListBean> CREATOR = new Parcelable.Creator<HeraldListBean>() {
                @Override
                public HeraldListBean createFromParcel(Parcel source) {
                    return new HeraldListBean(source);
                }

                @Override
                public HeraldListBean[] newArray(int size) {
                    return new HeraldListBean[size];
                }
            };
        }
    }
}
