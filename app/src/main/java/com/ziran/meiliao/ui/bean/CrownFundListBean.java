package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/6 17:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/6$
 * @updateDes ${TODO}
 */

public class CrownFundListBean {
    /**
     * createTime : 1511871192000
     * targetMembers : 50
     * supportMembers : 20
     * leftTime : 42
     * endTime : 1512023287000
     * url : https://www.dgli.net/crowdFundDetail?id=null&accessToken=1846935ac7a6bfe2115bd228628d9bd13468cd
     * intro : 报名 | 医学及心理学中的正念——正念减压
     * id : 1
     * picture : https://www.dgli.net/resource/images/qcourselibrary/https://www.dgli
     * .net/resource/images/qcourselibrary/index_act_banner1228.png
     * startTime : 1511936884000
     * title : 报名 | 医学及心理学中的正念3
     * avgPrice : 50.0
     * address : 广州星光映景
     * totalTime : 1天1晚
     */

    private long createTime;
    private int targetMembers;
    private int supportMembers;
    private int leftTime;
    private long endTime;
    private String url;
    private String intro;
    private int id;
    private String picture;
    private long startTime;
    private String title;
    private double avgPrice;
    private String address;
    private String totalTime;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getTargetMembers() {
        return targetMembers;
    }

    public void setTargetMembers(int targetMembers) {
        this.targetMembers = targetMembers;
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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getId() {
        return id;
    }
    public String getIdString() {
        return String.valueOf(id);
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAvgPrice() {
        return avgPrice;
    }
    public int getIntAvgPrice() {
        return (int) avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }


    @Override
    public String toString() {
        return "CrownFundListBean{" + "createTime=" + createTime + ", targetMembers=" + targetMembers + ", supportMembers=" +
                supportMembers + ", leftTime=" + leftTime + ", endTime=" + endTime + ", url='" + url + '\'' + ", intro='" + intro + '\''
                + ", id=" + id + ", picture='" + picture + '\'' + ", startTime=" + startTime + ", title='" + title + '\'' + ", avgPrice="
                + avgPrice + ", address='" + address + '\'' + ", totalTime='" + totalTime + '\'' + '}';
    }
}
