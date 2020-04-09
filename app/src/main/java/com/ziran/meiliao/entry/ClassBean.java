package com.ziran.meiliao.entry;


import com.ziran.meiliao.common.okhttp.Result;

import java.io.Serializable;
import java.util.List;

public class ClassBean extends Result {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean implements Serializable {


        /**
         * count : 3
         * list : [{"detail":"ZOOM网络平台:9月18日-11月20日 (每周三19:30-22:00)","phone":"13266580774","host":"胡君梅","NAME":"也一样","begin_time":1566274295000,"activity_id":72,"detail_banner":"course_detail_banner.jpg","channel_type":"在线支付","raw_price":3500,"picture":"https://dgli.net/resource/images/activity/morePic/41more.png","title":"报名 | 正念减压MBSR八周网络课程 ","price":3000,"degree":"老师","end_time":1574223111000,"courseStatus":"进行中","create_time":1565168737000,"user_id":9939,"orders":"android20190807170529054","members":1}]
         */

        private int count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * detail : ZOOM网络平台:9月18日-11月20日 (每周三19:30-22:00)
             * phone : 13266580774
             * host : 胡君梅
             * NAME : 也一样
             * begin_time : 1566274295000
             * activity_id : 72
             * detail_banner : course_detail_banner.jpg
             * channel_type : 在线支付
             * raw_price : 3500
             * picture : https://dgli.net/resource/images/activity/morePic/41more.png
             * title : 报名 | 正念减压MBSR八周网络课程
             * price : 3000
             * degree : 老师
             * end_time : 1574223111000
             * courseStatus : 进行中
             * create_time : 1565168737000
             * user_id : 9939
             * orders : android20190807170529054
             * members : 1
             */

            private String detail;
            private String phone;
            private String host;
            private String NAME;
            private long begin_time;
            private int activity_id;
            private String detail_banner;
            private String channel_type;
            private int raw_price;
            private String picture;
            private String title;
            private int price;
            private String degree;
            private long end_time;
            private String courseStatus;
            private long create_time;
            private long user_id;
            private String orders;
            private int members;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public long getBegin_time() {
                return begin_time;
            }

            public void setBegin_time(long begin_time) {
                this.begin_time = begin_time;
            }

            public int getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(int activity_id) {
                this.activity_id = activity_id;
            }

            public String getDetail_banner() {
                return detail_banner;
            }

            public void setDetail_banner(String detail_banner) {
                this.detail_banner = detail_banner;
            }

            public String getChannel_type() {
                return channel_type;
            }

            public void setChannel_type(String channel_type) {
                this.channel_type = channel_type;
            }

            public int getRaw_price() {
                return raw_price;
            }

            public void setRaw_price(int raw_price) {
                this.raw_price = raw_price;
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

            public String getDegree() {
                return degree;
            }

            public void setDegree(String degree) {
                this.degree = degree;
            }

            public long getEnd_time() {
                return end_time;
            }

            public void setEnd_time(long end_time) {
                this.end_time = end_time;
            }

            public String getCourseStatus() {
                return courseStatus;
            }

            public void setCourseStatus(String courseStatus) {
                this.courseStatus = courseStatus;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public long getUser_id() {
                return user_id;
            }

            public void setUser_id(long user_id) {
                this.user_id = user_id;
            }

            public String getOrders() {
                return orders;
            }

            public void setOrders(String orders) {
                this.orders = orders;
            }

            public int getMembers() {
                return members;
            }

            public void setMembers(int members) {
                this.members = members;
            }
        }
    }
}
