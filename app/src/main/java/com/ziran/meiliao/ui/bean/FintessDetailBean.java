package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.io.Serializable;
import java.util.List;

public class FintessDetailBean extends Result implements Serializable{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {

        /**
         * jsCoursesRecommend : [{"id":2,"name":"八段锦 ","difficult":"一般","activityAmount":"温和","duration":731,"homePic":"http://ojlzx3sl8.bkt.clouddn.com/js_2_home.png","detailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_2_home.png","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_2_thumb.png","description":"\u201c八段锦\u201d是我国古代优秀的健身方法之一，古人用瑰丽的锦缎比喻其精美，因有八节运动，故冠以\u201c八段锦\u201d之美称。八段锦是一套独立而完整的健身功法，融合了中医的阴阳五行、经络学说，具有锻炼平衡能力、防病治病、纠正形体等作用，同时又具有针对性强、适用面广等特色，是动静结合、身心互动、健患均益的健身方法。","price":199,"recPic":"http://ojlzx3sl8.bkt.clouddn.com/js_2_home.png","shareTitle":"\"\"","shareDesc":null,"sharePic":null,"able":1,"practiceCount":638,"shareUrl":"https://www.psytap.com/resource/fitness/index.html"},{"id":3,"name":"六字诀","difficult":"一般 ","activityAmount":"温和 ","duration":896,"homePic":"http://ojlzx3sl8.bkt.clouddn.com/js_3_home.png","detailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_3_home.png","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_3_thumb.png","description":"六字诀又称六字气诀，是我国古代流传下来的一种以呼吸吐纳为主要手段的传统养生健身功法。练习中自然吸气，呼气的同时，结合默念\u201c嘘、呵、呼、呬、吹、嘻\u201d六个字的读音进行锻炼的气功功法。六字诀分主五脏六腑，以呵字治心气，以呼字治脾气，以呬字治肺气，以嘘字治肝气，以吹字治肾气，以嘻字通三焦。六字诀气功即通过呼吸导引，祛除体内病气浊气，培养正气，进而通过辩证的练习，达到延年益寿，身心健康和谐的目的。","price":199,"recPic":"http://ojlzx3sl8.bkt.clouddn.com/js_3_home.png","shareTitle":"\"\"","shareDesc":null,"sharePic":null,"able":1,"practiceCount":638,"shareUrl":"\"\""},{"id":4,"name":"动观禅·站姿","difficult":"一般","activityAmount":"温和 ","duration":1574,"homePic":"http://ojlzx3sl8.bkt.clouddn.com/js_4_home.png","detailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_4_home.png","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_4_thumb.png","description":"日常生活着，我们的动作和行为往往是由心驱使的，但我们却经常心不在焉，以选择性的知觉，来面对眼前正在发生的一切。以致于我们经常忽略当下的讯息，从而给我们带来一些意想不到的伤害，例如：开车时，若我们忽略手正在转动方向盘的动作，心却只顾着讲电话，这极有可能给我们带来危险。又例如：若我们忽略身体给出的警示，长期投入高强度的工作，没有适时改善，这极有可能给我们身体造成毁灭性的伤害...。因此，学习当下随观，保持觉知，将能避免危险与伤害，并将练习者带往身心平静、和谐自由的精神境界。","price":199,"recPic":"http://ojlzx3sl8.bkt.clouddn.com/js_4_home.png","shareTitle":"\"\"","shareDesc":null,"sharePic":null,"able":1,"practiceCount":638,"shareUrl":"\"\""}]
         * isBuy : true
         * thumbnailPic : http://ojlzx3sl8.bkt.clouddn.com/js_1_thumb.png
         * details : [{"id":1,"courseId":1,"name":"预备式","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_1.mp4","duration":64,"free":1,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_1.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_1.jpg","shareUrl":"\"\"#/singleAction?fromApp=0"},{"id":2,"courseId":1,"name":"第1式：虎举","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_2.mp4","duration":72,"free":1,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_2.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_2.jpg","shareUrl":"\"\"#/singleAction2?fromApp=0"},{"id":3,"courseId":1,"name":"第2式：虎扑 ","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_3.mp4","duration":89,"free":0,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_3.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_3.jpg","shareUrl":"\"\"#/singleAction3?fromApp=0"},{"id":4,"courseId":1,"name":"第3式：鹿抵","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_4.mp4","duration":51,"free":0,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_4.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_4.jpg","shareUrl":"\"\"#/singleAction4?fromApp=0"},{"id":5,"courseId":1,"name":"第4式：鹿奔","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_5.mp4","duration":73,"free":0,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_5.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_5.jpg","shareUrl":"\"\"#/singleAction5?fromApp=0"},{"id":6,"courseId":1,"name":"第5式：熊运 ","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_6.mp4","duration":40,"free":0,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_6.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_6.jpg","shareUrl":"\"\"#/singleAction6?fromApp=0"},{"id":7,"courseId":1,"name":"第6式: 熊晃","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_7.mp4","duration":67,"free":0,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_7.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_7.jpg","shareUrl":"\"\"#/singleAction7?fromApp=0"},{"id":8,"courseId":1,"name":"第7式：猿提","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_8.mp4","duration":47,"free":0,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_8.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_8.jpg","shareUrl":"\"\"#/singleAction8?fromApp=0"},{"id":9,"courseId":1,"name":"第8式：猿摘 ","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_9.mp4","duration":77,"free":0,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_9.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_9.jpg","shareUrl":"\"\"#/singleAction9?fromApp=0"},{"id":10,"courseId":1,"name":"第9式：鸟伸 ","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_10.mp4","duration":51,"free":0,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_10.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_10.jpg","shareUrl":"\"\"#/singleAction10?fromApp=0"},{"id":11,"courseId":1,"name":"第10式：鸟飞","url":"http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_11.mp4","duration":108,"free":0,"var1":"0","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_11.jpg","coverPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_11.jpg","shareUrl":"\"\"#/singleAction11?fromApp=0"}]
         * showAllUrl : ""#/showAll?fromApp=0
         * shareUrl : ""
         * shareTitle : 你的好友邀请你一起练习五禽戏
         * sharePic : http://ojlzx3sl8.bkt.clouddn.com/js_1_thumb.png
         * userCoin : 85996
         * userPracticeCount : 1
         * shareDesc : 健康养生，延年益寿的首选功法
         * needCoin : 1990
         * isCollect : false
         * jsCourse : {"id":1,"name":"五禽戏","difficult":"一般","activityAmount":"较轻","duration":819,"homePic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_home.png","detailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_home.png","thumbnailPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_thumb.png","description":"五禽戏是一种中国导引术,是由模仿5种动物的动作而形成的强身功法。\u201c导引\u201d是一项以肢体运动为主,配合呼吸吐纳的养生方式,源于上古的舞蹈动作。春秋战国时期,导引术获得长足的发展,出现了 \u201c熊经\u201d\u201c鸟伸\u201d等术势。五禽戏模仿的5种动物分别为虎、鹿、熊、猿、鸟。所以五禽戏中任何一戏的演练，既能主治对应脏腑的疾患，又能兼治其他各脏的疾病，达到祛病强身，延年益寿的作用。","price":199,"recPic":"http://ojlzx3sl8.bkt.clouddn.com/js_1_home.png","shareTitle":"你的好友邀请你一起练习五禽戏","shareDesc":null,"sharePic":null,"able":1,"practiceCount":null,"shareUrl":"\"\""}
         * practiceCount : 638
         */

        private boolean isBuy;
        private String thumbnailPic;
        private String showAllUrl;
        private String shareUrl;
        private String shareTitle;
        private String sharePic;
        private int userCoin;
        private int userPracticeCount;
        private String shareDesc;
        private int needCoin;
        private boolean isCollect;
        private JsCourseBean jsCourse;
        private int practiceCount;
        private List<JsCoursesRecommendBean> jsCoursesRecommend;
        private List<DetailsBean> details;

        public boolean isIsBuy() {
            return isBuy;
        }

        public void setIsBuy(boolean isBuy) {
            this.isBuy = isBuy;
        }

        public String getThumbnailPic() {
            return thumbnailPic;
        }

        public void setThumbnailPic(String thumbnailPic) {
            this.thumbnailPic = thumbnailPic;
        }

        public String getShowAllUrl() {
            return showAllUrl;
        }

        public void setShowAllUrl(String showAllUrl) {
            this.showAllUrl = showAllUrl;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
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

        public int getUserCoin() {
            return userCoin;
        }

        public void setUserCoin(int userCoin) {
            this.userCoin = userCoin;
        }

        public int getUserPracticeCount() {
            return userPracticeCount;
        }

        public void setUserPracticeCount(int userPracticeCount) {
            this.userPracticeCount = userPracticeCount;
        }

        public String getShareDesc() {
            return shareDesc;
        }

        public void setShareDesc(String shareDesc) {
            this.shareDesc = shareDesc;
        }

        public int getNeedCoin() {
            return needCoin;
        }

        public void setNeedCoin(int needCoin) {
            this.needCoin = needCoin;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public JsCourseBean getJsCourse() {
            return jsCourse;
        }

        public void setJsCourse(JsCourseBean jsCourse) {
            this.jsCourse = jsCourse;
        }

        public int getPracticeCount() {
            return practiceCount;
        }

        public void setPracticeCount(int practiceCount) {
            this.practiceCount = practiceCount;
        }

        public List<JsCoursesRecommendBean> getJsCoursesRecommend() {
            return jsCoursesRecommend;
        }

        public void setJsCoursesRecommend(List<JsCoursesRecommendBean> jsCoursesRecommend) {
            this.jsCoursesRecommend = jsCoursesRecommend;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class JsCourseBean implements Serializable{
            /**
             * id : 1
             * name : 五禽戏
             * difficult : 一般
             * activityAmount : 较轻
             * duration : 819
             * homePic : http://ojlzx3sl8.bkt.clouddn.com/js_1_home.png
             * detailPic : http://ojlzx3sl8.bkt.clouddn.com/js_1_home.png
             * thumbnailPic : http://ojlzx3sl8.bkt.clouddn.com/js_1_thumb.png
             * description : 五禽戏是一种中国导引术,是由模仿5种动物的动作而形成的强身功法。“导引”是一项以肢体运动为主,配合呼吸吐纳的养生方式,源于上古的舞蹈动作。春秋战国时期,导引术获得长足的发展,出现了 “熊经”“鸟伸”等术势。五禽戏模仿的5种动物分别为虎、鹿、熊、猿、鸟。所以五禽戏中任何一戏的演练，既能主治对应脏腑的疾患，又能兼治其他各脏的疾病，达到祛病强身，延年益寿的作用。
             * price : 199
             * recPic : http://ojlzx3sl8.bkt.clouddn.com/js_1_home.png
             * shareTitle : 你的好友邀请你一起练习五禽戏
             * shareDesc : null
             * sharePic : null
             * able : 1
             * practiceCount : null
             * shareUrl : ""
             */

            private int id;
            private String name;
            private String difficult;
            private String activityAmount;
            private int duration;
            private String homePic;
            private String detailPic;
            private String thumbnailPic;
            private String description;
            private int price;
            private String recPic;
            private String shareTitle;
            private String shareDesc;
            private String sharePic;
            private int able;
            private Object practiceCount;
            private String shareUrl;
            private String practicePic;

            public String getPracticePic() {
                return practicePic;
            }

            public void setPracticePic(String practicePic) {
                this.practicePic = practicePic;
            }
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDifficult() {
                return difficult;
            }

            public void setDifficult(String difficult) {
                this.difficult = difficult;
            }

            public String getActivityAmount() {
                return activityAmount;
            }

            public void setActivityAmount(String activityAmount) {
                this.activityAmount = activityAmount;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getHomePic() {
                return homePic;
            }

            public void setHomePic(String homePic) {
                this.homePic = homePic;
            }

            public String getDetailPic() {
                return detailPic;
            }

            public void setDetailPic(String detailPic) {
                this.detailPic = detailPic;
            }

            public String getThumbnailPic() {
                return thumbnailPic;
            }

            public void setThumbnailPic(String thumbnailPic) {
                this.thumbnailPic = thumbnailPic;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getRecPic() {
                return recPic;
            }

            public void setRecPic(String recPic) {
                this.recPic = recPic;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareDesc() {
                return shareDesc;
            }

            public void setShareDesc(String shareDesc) {
                this.shareDesc = shareDesc;
            }

            public String getSharePic() {
                return sharePic;
            }

            public void setSharePic(String sharePic) {
                this.sharePic = sharePic;
            }

            public int getAble() {
                return able;
            }

            public void setAble(int able) {
                this.able = able;
            }

            public Object getPracticeCount() {
                return practiceCount;
            }

            public void setPracticeCount(Object practiceCount) {
                this.practiceCount = practiceCount;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }
        }

        public static class JsCoursesRecommendBean implements Serializable{
            /**
             * id : 2
             * name : 八段锦
             * difficult : 一般
             * activityAmount : 温和
             * duration : 731
             * homePic : http://ojlzx3sl8.bkt.clouddn.com/js_2_home.png
             * detailPic : http://ojlzx3sl8.bkt.clouddn.com/js_2_home.png
             * thumbnailPic : http://ojlzx3sl8.bkt.clouddn.com/js_2_thumb.png
             * description : “八段锦”是我国古代优秀的健身方法之一，古人用瑰丽的锦缎比喻其精美，因有八节运动，故冠以“八段锦”之美称。八段锦是一套独立而完整的健身功法，融合了中医的阴阳五行、经络学说，具有锻炼平衡能力、防病治病、纠正形体等作用，同时又具有针对性强、适用面广等特色，是动静结合、身心互动、健患均益的健身方法。
             * price : 199
             * recPic : http://ojlzx3sl8.bkt.clouddn.com/js_2_home.png
             * shareTitle : ""
             * shareDesc : null
             * sharePic : null
             * able : 1
             * practiceCount : 638
             * shareUrl : https://www.psytap.com/resource/fitness/index.html
             */

            private int id;
            private String name;
            private String difficult;
            private String activityAmount;
            private int duration;
            private String homePic;
            private String detailPic;
            private String thumbnailPic;
            private String description;
            private int price;
            private String recPic;
            private String shareTitle;
            private Object shareDesc;
            private Object sharePic;
            private int able;
            private int practiceCount;
            private String shareUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDifficult() {
                return difficult;
            }

            public void setDifficult(String difficult) {
                this.difficult = difficult;
            }

            public String getActivityAmount() {
                return activityAmount;
            }

            public void setActivityAmount(String activityAmount) {
                this.activityAmount = activityAmount;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getHomePic() {
                return homePic;
            }

            public void setHomePic(String homePic) {
                this.homePic = homePic;
            }

            public String getDetailPic() {
                return detailPic;
            }

            public void setDetailPic(String detailPic) {
                this.detailPic = detailPic;
            }

            public String getThumbnailPic() {
                return thumbnailPic;
            }

            public void setThumbnailPic(String thumbnailPic) {
                this.thumbnailPic = thumbnailPic;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getRecPic() {
                return recPic;
            }

            public void setRecPic(String recPic) {
                this.recPic = recPic;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public Object getShareDesc() {
                return shareDesc;
            }

            public void setShareDesc(Object shareDesc) {
                this.shareDesc = shareDesc;
            }

            public Object getSharePic() {
                return sharePic;
            }

            public void setSharePic(Object sharePic) {
                this.sharePic = sharePic;
            }

            public int getAble() {
                return able;
            }

            public void setAble(int able) {
                this.able = able;
            }

            public int getPracticeCount() {
                return practiceCount;
            }

            public void setPracticeCount(int practiceCount) {
                this.practiceCount = practiceCount;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }
        }

        public static class DetailsBean implements Serializable{
            /**
             * id : 1
             * courseId : 1
             * name : 预备式
             * url : http://ojlzx3sl8.bkt.clouddn.com/js_1_course_detail_1.mp4
             * duration : 64
             * free : 1
             * var1 : 0
             * thumbnailPic : http://ojlzx3sl8.bkt.clouddn.com/js_1_1.jpg
             * coverPic : http://ojlzx3sl8.bkt.clouddn.com/js_1_1.jpg
             * shareUrl : ""#/singleAction?fromApp=0
             */

            private int id;
            private int courseId;
            private String name;
            private String url;
            private int duration;
            private int free;
            private String var1;
            private String thumbnailPic;
            private String coverPic;
            private String shareUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getFree() {
                return free;
            }

            public void setFree(int free) {
                this.free = free;
            }

            public String getVar1() {
                return var1;
            }

            public void setVar1(String var1) {
                this.var1 = var1;
            }

            public String getThumbnailPic() {
                return thumbnailPic;
            }

            public void setThumbnailPic(String thumbnailPic) {
                this.thumbnailPic = thumbnailPic;
            }

            public String getCoverPic() {
                return coverPic;
            }

            public void setCoverPic(String coverPic) {
                this.coverPic = coverPic;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }
        }
    }


}
