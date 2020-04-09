package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/30 18:16
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/30$
 * @updateDes ${TODO}
 */

public class RecordListBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {

        /**
         * id : 3
         * createTime : 2017-08-30
         * title : 如何正确的装逼?
         * buyTimes : 0
         * seeTimes : 233
         * descript : 群主说过,人生的成功来自百分之九十九的汗水和百分之一的装逼;
         * pic : http://www.dgli.net:8888/resource/images/audio/35.png
         * type : 2
         */

        private String duration;
        private int targetId;
        private String createTime;
        private String title;
        private int buyTimes;
        private int seeTimes;
        private String descript;
        private String pic;
        private int type;
        private String url;
        private String detail;
        private int seeCount;
        private int buyCount;

        public String getDuration() {
            return duration;
        }
        public void setDuration(String duration) {
            this.duration = duration;
        }
        public int getSeeCount() {
            return seeCount;
        }

        public void setSeeCount(int seeCount) {
            this.seeCount = seeCount;
        }

        public int getBuyCount() {
            return buyCount;
        }

        public void setBuyCount(int buyCount) {
            this.buyCount = buyCount;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return targetId;
        }

        public void setId(int id) {
            this.targetId = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return title;
        }
        public String getAESUrl(){
            return AES.get().decrypt(url);
        }
        public void setTitle(String title) {
            this.title = title;
        }

        public int getBuyTimes() {
            return buyTimes;
        }

        public void setBuyTimes(int buyTimes) {
            this.buyTimes = buyTimes;
        }

        public int getSeeTimes() {
            return seeTimes;
        }

        public void setSeeTimes(int seeTimes) {
            this.seeTimes = seeTimes;
        }

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.targetId);
            dest.writeString(this.createTime);
            dest.writeString(this.title);
            dest.writeInt(this.buyTimes);
            dest.writeInt(this.seeTimes);
            dest.writeString(this.descript);
            dest.writeString(this.pic);
            dest.writeInt(this.type);
            dest.writeString(this.url);
            dest.writeString(this.detail);
            dest.writeInt(this.seeCount);
            dest.writeInt(this.buyCount);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.targetId = in.readInt();
            this.createTime = in.readString();
            this.title = in.readString();
            this.buyTimes = in.readInt();
            this.seeTimes = in.readInt();
            this.descript = in.readString();
            this.pic = in.readString();
            this.type = in.readInt();
            this.url = in.readString();
            this.detail = in.readString();
            this.seeCount = in.readInt();
            this.buyCount = in.readInt();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
