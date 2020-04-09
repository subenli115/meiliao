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
        /**
         * courseDesc : 正念，一种专注状态，辅以稳定力，解除惯性反应并容许内在智慧浮现。\n止语，用最温柔的力量来爱自己，爱这个世界。\n静修营，不是避开车马喧嚣，而是在心中修篱种菊。\n无论你是成长中的正念师资，抑或是对正念静观感兴趣的人群，我们都诚挚邀请你的加入，于国庆节长假期在广州郊区，开启这趟俘获宁静、滋养身心的智慧之旅。
         * earlyPrice : 4000
         * earlyTime : 1563163205000
         * courseTableImg : http://ojlzx3sl8.bkt.clouddn.com/courseTable20190726.png
         * courseImgWidth : 685
         * banner : http://ojlzx3sl8.bkt.clouddn.com/index_act_banner_gz190623.png
         * courseContent : 活在当下片刻，同时保持真诚友善，便是世界上蛮困难的事。对于所有想要在医疗、健康、心理、教育、科研、政府、商业及任何其他领域教授正念静观的人，都需要深化并滋养你的止语静修练习，方可能为别人和自己的生命带来巨大而美丽的改变。\n正念止语静修，是以练习与自我探索为主的课程。将通过不间断的练习和每天晚上由导师分享的 “心灵法语” 来探索正念静观的“普世性” 特质和在当代主流社会、非宗教化场境中的表达与呈现。\n同时，在本次静修课练习中还将包含呼吸觉察、行走静观、正念伸展等练习。在每天的课程中，老师会循序渐进与系统化地引导大家以好奇和对自己友善的态度修习正念静观。同时还会安排学员直接与导师以小团体和个人方式会面交流。\n通过这些环节来进行一次浸入式的静观觉醒力培育，建立起身体、心息与心灵的联系与亲密感，以及对我们与生俱来的勇气、明晰、慈心和洞察力的认知，回归更深的醒觉与自由。
         * towardPeople : • 成长中的正念减压、正念认知师资，以及从事生命转化工作的助人工作者。\n • 对正念静观感兴趣的人群。\n• 对获得平静的生活、给自己慈爱关怀、对内在成长有兴趣的人群。
         * coursePlan : 时间：2019年10月1日-7日（国庆节假期）\n 地点：广州 福地（从化区康村晨立葡萄园）\n人数：小班教学，限额25人，保证课程质量
         * courseImgHeight : 648
         * teacherDesc : 华梵大学东方人文思想研究所硕士\n台湾庆安正念推广中心正念导师\n台湾正念发展协会正念止语静修营正念导师\n浙江师范大学正念研究实验室正念学顾问与正念督导师
         * price : 4500
         * stuReport : 老师讲的真好，下次还来
         * beginTime : 1569902428000
         * endTime : 1570420885000
         * baomingFee : 原价：4500元/人 \n早鸟价：4000元/人\n\n（早鸟价截止时间：7月15日）\n费用包含培训费、资料教材、蒲团道具等。\n分享赢学费\n分享本文至朋友圈，无分组停留48小时后，截图发送给课程小助手（微信：wpyx2018），即可享受本课程减免200元优惠。\n帐户名：广州钝感力网络科技有限公司 \n开户行：农业银行广州五羊新城支行 \n账   号：440304 010400 03430\n支付宝：long999@aliyun.com\n\n报名确认及咨询请联系：\n金老师：188 1940 7188（同微信）\n方老师：136 6004 7170（同微信）
         * teamPrice : 4000
         * teacherImg : http://ojlzx3sl8.bkt.clouddn.com/teacherHJm20190726.jpg
         */

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
