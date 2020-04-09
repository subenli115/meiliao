package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/30 18:19
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/30$
 * @updateDes ${TODO}
 */

public class SJKSingeLiveData  implements Cloneable{

    /**
     * picture : null
     * id : 1
     * author : {"name":"测试"}
     * title : 七天睡眠治疗
     * price : 0.01
     */
    private int itemType;
    private boolean hasMore;
    private String picture;
    private String id;
    private AuthorBean author;
    private String title;
    private String price;
    private String watchCount;
    private String vip;
    private String url;
    private int tag;
    private String duration;
    private String intro;
    private int status;
    private long countDown;
    private boolean isCollect;
    private boolean mShowDivler;
    private Object historyData;

    public Object getHistoryData() {
        return historyData;
    }

    public void setHistoryData(Object historyData) {
        this.historyData = historyData;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(String watchCount) {
        this.watchCount = watchCount;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCountDown() {
        return countDown;
    }

    public void setCountDown(long countDown) {
        this.countDown = countDown;
    }

    @Override
    public String toString() {
        return "SJKSingeLiveData{" + "itemType=" + itemType + ", hasMore=" + hasMore + ", picture='" + picture + '\'' + ", id='" + id +
                '\'' + ", author=" + author + ", title='" + title + '\'' + ", price='" + price + '\'' + ", watchCount='" + watchCount +
                '\'' + ", vip='" + vip + '\'' + ", url='" + url + '\'' + ", tag=" + tag + ", duration='" + duration + '\'' + ", intro='"
                + intro + '\'' + ", status=" + status + ", countDown=" + countDown + '}';
    }

    @Override
    public SJKSingeLiveData clone() {
        SJKSingeLiveData employee = null;
        try {
            employee = (SJKSingeLiveData) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public boolean isShowDivler() {
        return mShowDivler;
    }
}
