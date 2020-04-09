package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class CoursePaySuccessResult extends Result {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean  {


        private List<CourseBean> course;
        private List<AlbumBean> album;

        public List<CourseBean> getCourse() {
            return course;
        }

        public void setCourse(List<CourseBean> course) {
            this.course = course;
        }

        public List<AlbumBean> getAlbum() {
            return album;
        }

        public void setAlbum(List<AlbumBean> album) {
            this.album = album;
        }

        public static class CourseBean {
            /**
             * picture : https://dgli.net/resource/images/activity/morePic/40more.png
             * id : 66
             * time : 2019-10-01
             * price : 4500
             * remain : 25
             * address : 广州
             * name : 报名 | 静观自我关怀五日集训课程
             * teacher : 何孟玲
             */

            private String picture;
            private int id;
            private String time;
            private int price;
            private int remain;
            private String address;
            private String name;
            private String teacher;

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

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getRemain() {
                return remain;
            }

            public void setRemain(int remain) {
                this.remain = remain;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTeacher() {
                return teacher;
            }

            public void setTeacher(String teacher) {
                this.teacher = teacher;
            }
        }

        public static class AlbumBean {
            /**
             * id : 87
             * picture : https://dgli.net/resource/images/album/recPic/87rec.png
             * title : 正念冥想练习导聆
             * price : 20
             * description : 正念学者
             * name : 赵经纬
             */

            private int id;
            private String picture;
            private String title;
            private int price;
            private String description;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
