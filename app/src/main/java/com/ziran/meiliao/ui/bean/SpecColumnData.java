package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/8/20 12:56
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/8/20
 * @updateDes ${TODO}
 */

public class SpecColumnData {

    private int id;
    private String headImg;
    private String tag;
    private String name;
    private String descript;
    private String courseId;
    private int type = -1;
    private int targetId;
    private String pic;
    private String title;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SpecColumnData{" + "id=" + id + ", headImg='" + headImg + '\'' + ", tag='" + tag + '\'' + ", name='" + name + '\'' + ", descript='" + descript + '\'' + ", courseId='" + courseId + '\'' + ", type=" + type + '}';
    }
}
