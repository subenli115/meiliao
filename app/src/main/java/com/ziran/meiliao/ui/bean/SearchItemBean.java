package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/5 15:22
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/5$
 * @updateDes ${TODO}
 */

public class SearchItemBean {

    private String name;
    private String descript;
    private int watchCount;
    private int listenCount;
    private String intro;
    private String picture;
    private String id;
    private String tag;
    private String title;
    private String address;
    private String beginTime;
    private String endTime;
    private AuthorBean author;
    private String type;
    private String status = "1";
    private boolean isHead;
    private String url;
    private String link;



    private int targetMembers;
    private int officePrice;
    private String totalTime;

    private long startTime;
    private String headImg;
    private int avgPrice;
    private int supportMembers;
    private int leftTime;
    private int itemType;

    private long createTime;

    private int courseMembers;
    private int courseNumbers;
    private long countDown;
    private int mPosition;
    private int memberPrice;
    public int getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(int memberPrice) {
        this.memberPrice = memberPrice;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    private List<CourserListBean> courserList;

    public long getCountDown() {
        return countDown;
    }

    public void setCountDown(long countDown) {
        this.countDown = countDown;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCourseMembers() {
        return courseMembers;
    }

    public void setCourseMembers(int courseMembers) {
        this.courseMembers = courseMembers;
    }

    public List<CourserListBean> getCourserList() {
        return courserList;
    }

    public void setCourserList(List<CourserListBean> courserList) {
        this.courserList = courserList;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getCourseNumbers() {
        return courseNumbers;
    }

    public void setCourseNumbers(int courseNumbers) {
        this.courseNumbers = courseNumbers;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

    public int getSupportMembers() {
        return supportMembers;
    }

    public void setSupportMembers(int supportMembers) {
        this.supportMembers = supportMembers;
    }

    public int getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTargetMembers() {
        return targetMembers;
    }

    public void setTargetMembers(int targetMembers) {
        this.targetMembers = targetMembers;
    }

    public int getOfficePrice() {
        return officePrice;
    }

    public void setOfficePrice(int officePrice) {
        this.officePrice = officePrice;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getLink() {
        return EmptyUtils.isNotEmpty(link) ? link : id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }

    public int getListenCount() {
        return listenCount;
    }

    public void setListenCount(int listenCount) {
        this.listenCount = listenCount;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "SearchItemBean{" + "name='" + name + '\'' + ", descript='" + descript + '\'' + ", watchCount=" + watchCount + ", " +
                "listenCount=" + listenCount + ", intro='" + intro + '\'' + ", picture='" + picture + '\'' + ", id='" + id + '\'' + ", " +
                "tag='" + tag + '\'' + ", title='" + title + '\'' + ", address='" + address + '\'' + ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' + ", author=" + author + ", type='" + type + '\'' + ", status='" + status + '\'' + ", " +
                "isHead=" + isHead + ", url='" + url + '\'' + ", link='" + link + '\'' + ", courseNumbers=" + courseNumbers + ", " +
                "targetMembers=" + targetMembers + ", officePrice=" + officePrice + ", totalTime='" + totalTime + '\'' + ", startTime=" +
                startTime + ", headImg='" + headImg + '\'' + ", avgPrice=" + avgPrice + ", supportMembers=" + supportMembers + ", " +
                "leftTime=" + leftTime + '}';
    }

    public String getUrl() {
        return url;
    }


    public static SearchItemBean empty(){
        SearchItemBean searchItemBean = new SearchItemBean();
        searchItemBean.setType("empty");
        return  searchItemBean;
    }
    public static SearchItemBean xiangGuan(){
        SearchItemBean searchItemBean = new SearchItemBean();
        searchItemBean.setType("xiangGuan");
        searchItemBean.setTitle("为您推荐以下相关课程");
        return  searchItemBean;
    }

    public static class CourserListBean {
        /**
         * courseTitle : 报名 | 医学及心理学中的正念3
         * courseId : 1
         */

        private String courseTitle;
        private int courseId;

        public String getCourseTitle() {
            return courseTitle;
        }

        public void setCourseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }
    }
}
