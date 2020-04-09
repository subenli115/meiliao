package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/5/29 10:44
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/5/29$
 * @updateDes ${TODO}
 */

public class TeacherInfoBean extends Result {

    /**
     * data : {"headImg":"https://www.dgli.net/resource/images/subscription/course_teacher_hu.png","teacherName":"舒志凌","intro":null,"teacherId":10}
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
         * headImg : https://www.dgli.net/resource/images/subscription/course_teacher_hu.png
         * teacherName : 舒志凌
         * intro : null
         * teacherId : 10
         */

        private String headImg;
        private String teacherName;
        private String intro;
        private int teacherId;

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }
    }
}
