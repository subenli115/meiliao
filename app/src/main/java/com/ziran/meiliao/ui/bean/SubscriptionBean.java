package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/18 10:14
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/18$
 * @updateDes ${TODO}
 */

public class SubscriptionBean {
    private String title;
    private float price;
    private boolean isBuy;
    private int userCoin;
    private int needCoin;
    private int subscriptionId;
    private int memberPrice;
    private String levelDetail;
    private String pic;

    public int getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(int memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getLevelDetail() {
        return levelDetail;
    }

    public void setLevelDetail(String levelDetail) {
        this.levelDetail = levelDetail;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public int getUserCoin() {
        return userCoin;
    }

    public void setUserCoin(int userCoin) {
        this.userCoin = userCoin;
    }

    public int getNeedCoin() {
        return needCoin;
    }

    public void setNeedCoin(int needCoin) {
        this.needCoin = needCoin;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "SubscriptionBean{" + "title='" + title + '\'' + ", price=" + price + ", isBuy=" + isBuy + ", userCoin=" + userCoin + ", " +
                "needCoin=" + needCoin + ", subscriptionId=" + subscriptionId + ", pic='" + pic + '\'' + '}';
    }
}
