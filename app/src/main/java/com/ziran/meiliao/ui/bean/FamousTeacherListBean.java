package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/6 17:19
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/6$
 * @updateDes ${TODO}
 */

public class FamousTeacherListBean {
    /**
     * picture : https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png
     * id : 10
     * createTime : 1512098525000
     * accessToken : 184927895d14daa3cf8e0c54be02a46841504a
     * name : 李燕蕙
     * courseMembers : 3
     * intro : 简介简介简介
     */

    private long createTime;
    private String accessToken;

    private String picture;
    private String id;
    private String name;
    private int courseMembers;
    private String intro;



    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseMembers() {
        return courseMembers;
    }

    public void setCourseMembers(int courseMembers) {
        this.courseMembers = courseMembers;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

}
