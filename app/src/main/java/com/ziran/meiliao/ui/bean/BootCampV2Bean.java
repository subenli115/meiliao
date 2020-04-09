package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.io.Serializable;
import java.util.List;

public class BootCampV2Bean extends Result {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean  {


        private List<JsCoursesBean> jsCourses;
        private List<PracticeBooksBean> practiceBooks;

        public List<JsCoursesBean> getJsCourses() {
            return jsCourses;
        }

        public void setJsCourses(List<JsCoursesBean> jsCourses) {
            this.jsCourses = jsCourses;
        }

        public List<PracticeBooksBean> getPracticeBooks() {
            return practiceBooks;
        }

        public void setPracticeBooks(List<PracticeBooksBean> practiceBooks) {
            this.practiceBooks = practiceBooks;
        }

        public static class JsCoursesBean implements Serializable {
            /**
             * id : 1
             * name : 五禽戏
             * difficult : 一般
             * activityAmount : 较轻
             * duration : 819
             * homePic : http://ojlzx3sl8.bkt.clouddn.com/js_1_home.png
             * detailPic : http://ojlzx3sl8.bkt.clouddn.com/js_1_home.png
             * thumbnailPic : http://ojlzx3sl8.bkt.clouddn.com/js_1_home.jpg
             * description : 五禽戏是一种中国导引术,是由模仿5种动物的动作而形成的强身功法。“导引”是一项以肢体运动为主,配合呼吸吐纳的养生方式,源于上古的舞蹈动作。春秋战国时期,导引术获得长足的发展,出现了 “熊经”“鸟伸”等术势。五禽戏模仿的5种动物分别为虎、鹿、熊、猿、鸟。所以五禽戏中任何一戏的演练，既能主治对应脏腑的疾患，又能兼治其他各脏的疾病，达到祛病强身，延年益寿的作用。
             * price : 199
             * recPic : http://ojlzx3sl8.bkt.clouddn.com/js_1_home.png
             * shareTitle : null
             * shareDesc : null
             * sharePic : null
             * able : 1
             * practiceCount : 502
             *  * practicePic : ""
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
            private Object shareTitle;
            private Object shareDesc;
            private Object sharePic;
            private int able;
            private int practiceCount;

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

            public Object getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(Object shareTitle) {
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
        }

        public static class PracticeBooksBean {
            /**
             * detail : https://dgli.net/page/static/practiceBooks/MBSR/index.html
             * status : 1
             * share_picture : sharePic.png
             * tagId : 45
             * booksId : 1
             * startTime : 2019-09-07
             * id : 1
             * picture : https://www.dgli.net/resource/images/practiceActivity/mbsr.png
             * name : MBSR八周练习
             * tagName : 胡君梅
             * bg_picture : headBg.png
             * sys_tag_id : 45
             */

            private String detail;
            private int status;
            private String share_picture;
            private int tagId;
            private int booksId;
            private String startTime;
            private int id;
            private String picture;
            private String name;
            private String tagName;
            private String bg_picture;
            private int sys_tag_id;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getShare_picture() {
                return share_picture;
            }

            public void setShare_picture(String share_picture) {
                this.share_picture = share_picture;
            }

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            public int getBooksId() {
                return booksId;
            }

            public void setBooksId(int booksId) {
                this.booksId = booksId;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }

            public String getBg_picture() {
                return bg_picture;
            }

            public void setBg_picture(String bg_picture) {
                this.bg_picture = bg_picture;
            }

            public int getSys_tag_id() {
                return sys_tag_id;
            }

            public void setSys_tag_id(int sys_tag_id) {
                this.sys_tag_id = sys_tag_id;
            }
        }
    }
}
