package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class CourseDeatilBean extends Result {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
    public static class DataBean {

        private String courseDesc;
        private double earlyPrice;
        private long earlyTime;
        private String courseTableImg;
        private int courseImgWidth;
        private String banner;
        private String courseContent;
        private String towardPeople;
        private String coursePlan;
        private int courseImgHeight;
//        private String teacherDesc;
        private double price;
        private String stuReport;
        private long beginTime;
        private long endTime;
        private String baomingFee;
        private double teamPrice;

        private boolean isBuy;
        private String title;
        private int remain;
        /**
         * shareTitle : 【报名中】七日正念止语静修营 | 广州
         * teamPrice : 4000.0
         * sharePic : http://ojlzx3sl8.bkt.clouddn.com/teacherHJm20190726.jpg
         * shareDesc : 正念，一种专注状态，辅以稳定力，解除惯性反应并容许内在智慧浮现。\n止语，用最温柔的力量来爱自己，爱这个世界。\n静修营，不是避开车马喧嚣，而是在心中修篱种菊。\n无论你是成长中的正念师资，抑或是对正念静观感兴趣的人群，我们都诚挚邀请你的加入，于国庆节长假期在广州郊区，开启这趟俘获宁静、滋养身心的智慧之旅。
         * earlyPrice : 4000.0
         * price : 4500.0
         * shareUrl : www.baidu.com?courseId=64
         */

        private String shareTitle;
        private String sharePic;
        private String shareDesc;
        private String shareUrl;
        /**
         * teamPrice : 4000.0
         * earlyPrice : 4000.0
         * teachers : [{"degree":"硕士","teacherImg":"teacherHJm20190726.jpg","teacherName":"http://ojlzx3sl8.bkt.clouddn.com/何孟玲","teacherDesc":"\u2022华梵大学东方人文思想研究所硕士\\n\u2022台湾庆安正念推广中心正念导师\\n\u2022台湾正念发展协会正念止语静修营正念导师\\n\u2022浙江师范大学正念研究实验室正念学顾问与正念督导师"}]
         * price : 4500.0
         */

        private List<TeachersBean> teachers;
        /**
         * teamPrice : 4000.0
         * earlyPrice : 4000.0
         * teachers : [{"degree":"硕士","teacherImg":"http://ojlzx3sl8.bkt.clouddn.com/teacherHJm20190726.jpg","teacherName":"何孟玲","teacherDesc":"\u2022华梵大学东方人文思想研究所硕士\\n\u2022台湾庆安正念推广中心正念导师\\n\u2022台湾正念发展协会正念止语静修营正念导师\\n\u2022浙江师范大学正念研究实验室正念学顾问与正念督导师"}]
         * price : 4500.0
         */
        private List<TeachersBean> teachersX;


        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
        }

        public String getCourseDesc() {
            return courseDesc;
        }

        public void setCourseDesc(String courseDesc) {
            this.courseDesc = courseDesc;
        }

        public double getEarlyPrice() {
            return earlyPrice;
        }

        public void setEarlyPrice(double earlyPrice) {
            this.earlyPrice = earlyPrice;
        }

        public long getEarlyTime() {
            return earlyTime;
        }

        public void setEarlyTime(long earlyTime) {
            this.earlyTime = earlyTime;
        }

        public String getCourseTableImg() {
            return courseTableImg;
        }

        public void setCourseTableImg(String courseTableImg) {
            this.courseTableImg = courseTableImg;
        }

        public int getCourseImgWidth() {
            return courseImgWidth;
        }

        public void setCourseImgWidth(int courseImgWidth) {
            this.courseImgWidth = courseImgWidth;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getCourseContent() {
            return courseContent;
        }

        public void setCourseContent(String courseContent) {
            this.courseContent = courseContent;
        }

        public String getTowardPeople() {
            return towardPeople;
        }

        public void setTowardPeople(String towardPeople) {
            this.towardPeople = towardPeople;
        }

        public String getCoursePlan() {
            return coursePlan;
        }

        public void setCoursePlan(String coursePlan) {
            this.coursePlan = coursePlan;
        }

        public int getCourseImgHeight() {
            return courseImgHeight;
        }

        public void setCourseImgHeight(int courseImgHeight) {
            this.courseImgHeight = courseImgHeight;
        }


        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStuReport() {
            return stuReport;
        }

        public void setStuReport(String stuReport) {
            this.stuReport = stuReport;
        }

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getBaomingFee() {
            return baomingFee;
        }

        public void setBaomingFee(String baomingFee) {
            this.baomingFee = baomingFee;
        }

        public double getTeamPrice() {
            return teamPrice;
        }

        public void setTeamPrice(double teamPrice) {
            this.teamPrice = teamPrice;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public int getRemain() {
            return remain;
        }

        public void setRemain(int remain) {
            this.remain = remain;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public String getSharePic() {
            return sharePic;
        }

        public void setSharePic(String sharePic) {
            this.sharePic = sharePic;
        }

        public String getShareDesc() {
            return shareDesc;
        }

        public void setShareDesc(String shareDesc) {
            this.shareDesc = shareDesc;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }



        public List<TeachersBean> getTeachers() {
            return teachers;
        }

        public void setTeachers(List<TeachersBean> teachers) {
            this.teachers = teachers;
        }

        public static class TeachersBean {
            /**
             * degree : 硕士
             * teacherImg : http://ojlzx3sl8.bkt.clouddn.com/teacherHJm20190726.jpg
             * teacherName : 何孟玲
             * teacherDesc : •华梵大学东方人文思想研究所硕士\n•台湾庆安正念推广中心正念导师\n•台湾正念发展协会正念止语静修营正念导师\n•浙江师范大学正念研究实验室正念学顾问与正念督导师
             */

            private String degree;
            private String teacherImg;
            private String teacherName;
            private String teacherDesc;

            public String getDegree() {
                return degree;
            }

            public void setDegree(String degree) {
                this.degree = degree;
            }

            public String getTeacherImg() {
                return teacherImg;
            }

            public void setTeacherImg(String teacherImg) {
                this.teacherImg = teacherImg;
            }

            public String getTeacherName() {
                return teacherName;
            }

            public void setTeacherName(String teacherName) {
                this.teacherName = teacherName;
            }

            public String getTeacherDesc() {
                return teacherDesc;
            }

            public void setTeacherDesc(String teacherDesc) {
                this.teacherDesc = teacherDesc;
            }
        }


    }
}
