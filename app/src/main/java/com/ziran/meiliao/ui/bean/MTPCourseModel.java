package com.ziran.meiliao.ui.bean;

public class MTPCourseModel {


    private String content;
    private String title ;
    private String teacherName ;
    private String teacherDegree ;
    private float courseImgWidth ;
    private String courseUrl;
    private String avatarUrl;
    private float courseImgHeight ;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherDegree() {
        return teacherDegree;
    }

    public void setTeacherDegree(String teacherDegree) {
        this.teacherDegree = teacherDegree;
    }


    public float getCourseImgWidth() {
        return courseImgWidth;
    }

    public void setCourseImgWidth(float courseImgWidth) {
        this.courseImgWidth = courseImgWidth;
    }


    public float getCourseImgHeight() {
        return courseImgHeight;
    }

    public void setCourseImgHeight(float courseImgHeight) {
        this.courseImgHeight = courseImgHeight;
    }
    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


}
