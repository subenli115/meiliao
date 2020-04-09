package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/20 11:18
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/20$
 * @updateDes ${TODO}
 */

public class CourseLibraryTeamBean {
    private int itemType = 8883;
    private String people;
    private String date;
    private int targetMembers;
    private String shareTitle;
    private String sharePic;
    private int authorMembers;
    private String id;
    private String title;
    private String shareUrl;
    private double officePrice;
    private String totalTime;


    private String url;


    private String picture;
    private long createTime;
    private int courseNumbers;
    private String accessToken;
    private int status;
    private String name;
    private int courseId;
    private String intro;
    private int authorId;


    public CourseLibraryTeamBean(int itemType, String picture, String title, String people, String date) {
        this.itemType = itemType;
        this.picture = picture;
        this.title = title;
        this.people = people;
        this.date = date;
    }

    public CourseLibraryTeamBean(String picture, String title, String people, String date) {
        this.picture = picture;
        this.title = title;
        this.people = people;
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseNumbers() {
        return courseNumbers;
    }

    public void setCourseNumbers(int courseNumbers) {
        this.courseNumbers = courseNumbers;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "CourseLibraryTeamBean{" + "itemType=" + itemType + ", people='" + people + '\'' + ", date='" + date + '\'' + ", " +
                "targetMembers=" + targetMembers + ", shareTitle='" + shareTitle + '\'' + ", sharePic='" + sharePic + '\'' + ", " +
                "authorMembers=" + authorMembers + ", id=" + id + ", title='" + title + '\'' + ", shareUrl='" + shareUrl + '\'' + ", " +
                "officePrice=" + officePrice + ", totalTime='" + totalTime + '\'' + ", picture='" + picture + '\'' + ", createTime=" +
                createTime + ", accessToken='" + accessToken + '\'' + ", status=" + status + ", name='" + name + '\'' + ", courseId=" +
                courseId + ", authorId=" + authorId + '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
        this.date = data;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getTargetMembers() {
        return targetMembers == 0 ? courseNumbers : targetMembers;
    }

    public void setTargetMembers(int targetMembers) {
        this.targetMembers = targetMembers;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getAuthorMembers() {
        return authorMembers;
    }

    public void setAuthorMembers(int authorMembers) {
        this.authorMembers = authorMembers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getOfficePrice() {
        return (int) officePrice;
    }

    public void setOfficePrice(double officePrice) {
        this.officePrice = officePrice;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

}
