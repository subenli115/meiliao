package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 *练习册主页 on 2018/9/11.
 */

public class PracticeHomeBean extends Result{



    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {

        public int getBooksStatus() {
            return booksStatus;
        }

        public void setBooksStatus(int booksStatus) {
            this.booksStatus = booksStatus;
        }

        /**
         * practiceBooks : [{"id":1,"status":1,"name":"卖骚练习","notice":"记得每日去按时完成练习任务哦"},{"id":2,"status":1,"name":"犯贱练习","notice":"记得每日去按时完成练习任务哦"},{"id":3,"status":1,"name":"卖萌练习","notice":"记得每日去按时完成练习任务哦"}]
         * practiceData : {"minutes":2345,"days":1,"times":23456,"serialDays":1111}
         */
        private int booksStatus;
        private PracticeDataBean practiceData;
        private List<PracticeBooksBean> practiceBooks;

        public PracticeDataBean getPracticeData() {
            return practiceData;
        }

        public void setPracticeData(PracticeDataBean practiceData) {
            this.practiceData = practiceData;
        }

        public List<PracticeBooksBean> getPracticeBooks() {
            return practiceBooks;
        }

        public void setPracticeBooks(List<PracticeBooksBean> practiceBooks) {
            this.practiceBooks = practiceBooks;
        }

        public static class PracticeDataBean {
            /**
             * minutes : 2345
             * days : 1
             * times : 23456
             * serialDays : 1111
             */

            private int minutes;
            private int times;
            private int serialDays;
            private int hours;
            private String picture;

            public int getHours() {
                return hours;
            }
            public void setHours(int hours) {
                this.hours = hours;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }


            public int getTimes() {
                return times;
            }

            public void setTimes(int times) {
                this.times = times;
            }

            public int getSerialDays() {
                return serialDays;
            }

            public void setSerialDays(int serialDays) {
                this.serialDays = serialDays;
            }
        }

        public static class PracticeBooksBean {
            /**
             * id : 1
             * status : 1
             * name : 卖骚练习
             * notice : 记得每日去按时完成练习任务哦
             */

            private int id;
            private int status;
            private String name;
            private String notice;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
