package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/4 17:57
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/4$
 * @updateDes ${TODO}
 */

public class TeacherPageBean extends Result {

    /**
     * data : {"course":{"id":36,"picture":"http://www.psytap.com/wpyx_longjg/resource/images/course/35.png","author":{"name":"陈",
     * "descript":"xiaochen"},"title":"测试","time":"05月03日10:38-10:39","shareDescript":"","shareTitle":"测试","shareUrl":"http://www.psytap
     * .com/wpyx_longjg/page/content/shareCourse.html?courseId=36","sharePic":"","intro":"测试----"},"flag":true}
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
         * course : {"id":36,"picture":"http://www.psytap.com/wpyx_longjg/resource/images/course/35.png","author":{"name":"陈",
         * "descript":"xiaochen"},"title":"测试","time":"05月03日10:38-10:39","shareDescript":"","shareTitle":"测试","shareUrl":"http://www
         * .psytap.com/wpyx_longjg/page/content/shareCourse.html?courseId=36","sharePic":"","intro":"测试----"}
         * flag : true
         */

        private CourseBean course;
        private boolean flag;

        public CourseBean getCourse() {
            return course;
        }

        public void setCourse(CourseBean course) {
            this.course = course;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public static class CourseBean {
            /**
             * id : 36
             * picture : http://www.psytap.com/wpyx_longjg/resource/images/course/35.png
             * author : {"name":"陈","descript":"xiaochen"}
             * title : 测试
             * time : 05月03日10:38-10:39
             * shareDescript :
             * shareTitle : 测试
             * shareUrl : http://www.psytap.com/wpyx_longjg/page/content/shareCourse.html?courseId=36
             * sharePic :
             * intro : 测试----
             */

            private int id;
            private String picture;
            private AuthorBean author;
            private String title;
            private String time;
            private String shareDescript;
            private String shareTitle;
            private String shareUrl;
            private String sharePic;
            private String intro;

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

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getShareDescript() {
                return shareDescript;
            }

            public void setShareDescript(String shareDescript) {
                this.shareDescript = shareDescript;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getSharePic() {
                return sharePic;
            }

            public void setSharePic(String sharePic) {
                this.sharePic = sharePic;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }


            @Override
            public String toString() {
                return "CourseBean{" +
                        "id=" + id +
                        ", picture='" + picture + '\'' +
                        ", author=" + author +
                        ", title='" + title + '\'' +
                        ", time='" + time + '\'' +
                        ", shareDescript='" + shareDescript + '\'' +
                        ", shareTitle='" + shareTitle + '\'' +
                        ", shareUrl='" + shareUrl + '\'' +
                        ", sharePic='" + sharePic + '\'' +
                        ", intro='" + intro + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "course=" + course +
                    ", flag=" + flag +
                    '}';
        }

    }
}
