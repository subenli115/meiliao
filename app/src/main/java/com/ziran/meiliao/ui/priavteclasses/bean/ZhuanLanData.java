package com.ziran.meiliao.ui.priavteclasses.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.utils.StringUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/31 18:09
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/31$
 * @updateDes ${TODO}
 */

public class ZhuanLanData implements Parcelable {
    private  String levelDetail;
    private  int memberPrice;
    private int type;
    private int userCoin;
    private int needCoin;
    private String specColumnId;
    private String title;
    private String picture;
    private boolean isBuy;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private float price;
    private String intro;
    private String progress;
    private ZhuanLanBigInBean.DataBean.DirBean mDirBean;

    public String getLevelDetail() {
        return levelDetail;
    }

    public void setLevelDetail(String levelDetail) {
        this.levelDetail = levelDetail;
    }

    public int getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(int memberPrice) {
        this.memberPrice = memberPrice;
    }
    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }
    public ZhuanLanData(int type, int userCoin, int needCoin, String specColumnId, String title, String picture) {
        this.type = type;
        this.userCoin = userCoin;
        this.needCoin = needCoin;
        this.specColumnId = specColumnId;
        this.title = title;
        this.picture = picture;
        this.levelDetail=levelDetail;
        this.memberPrice=memberPrice;
    }
    public ZhuanLanData(int type, int userCoin, int needCoin, String specColumnId, String title, String picture,String levelDetail,int memberPrice) {
        this.type = type;
        this.userCoin = userCoin;
        this.needCoin = needCoin;
        this.specColumnId = specColumnId;
        this.title = title;
        this.picture = picture;
        this.levelDetail=levelDetail;
        this.memberPrice=memberPrice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
    public String getZhuanLanNeedCoin() {
        return  StringUtils.format("订阅   ¥%d",needCoin / 10);
    }

    public void setNeedCoin(int needCoin) {
        this.needCoin = needCoin;
    }

    public String getSpecColumnId() {
        return specColumnId;
    }

    public void setSpecColumnId(String specColumnId) {
        this.specColumnId = specColumnId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ZhuanLanData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeInt(this.userCoin);
        dest.writeInt(this.needCoin);
        dest.writeString(this.specColumnId);
        dest.writeString(this.title);
        dest.writeString(this.picture);
        dest.writeByte(this.isBuy ? (byte) 1 : (byte) 0);
    }

    protected ZhuanLanData(Parcel in) {
        this.type = in.readInt();
        this.userCoin = in.readInt();
        this.needCoin = in.readInt();
        this.specColumnId = in.readString();
        this.title = in.readString();
        this.picture = in.readString();
        this.isBuy = in.readByte() != 0;
    }

    public static final Creator<ZhuanLanData> CREATOR = new Creator<ZhuanLanData>() {
        @Override
        public ZhuanLanData createFromParcel(Parcel source) {
            return new ZhuanLanData(source);
        }

        @Override
        public ZhuanLanData[] newArray(int size) {
            return new ZhuanLanData[size];
        }
    };

    @Override
    public String toString() {
        return "ZhuanLanData{" + "type=" + type + ", userCoin=" + userCoin + ", needCoin=" + needCoin + ", specColumnId='" + specColumnId
                + '\'' + ", title='" + title + '\'' + ", picture='" + picture + '\'' + ", isBuy=" + isBuy + ", intro='" + intro + '\'' +
                ", progress='" + progress + '\'' + '}';
    }

    public void setDirBean(ZhuanLanBigInBean.DataBean.DirBean dirBean) {
        mDirBean = dirBean;
    }

    public ZhuanLanBigInBean.DataBean.DirBean getDirBean() {
        return mDirBean;
    }
}
