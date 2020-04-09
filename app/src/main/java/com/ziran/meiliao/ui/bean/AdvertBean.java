package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/28 14:08
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/28$
 * @updateDes ${TODO}
 */

public class AdvertBean extends Result {


    /**
     * data : {"id":3,"picture":"https://www.dgli.net/resource/images/advert/banner_conference.png","detailUrl":"http://www.dgli
     * .net:8888/subscription/webIndex/tid/3/isShare/1?1=1","tag":5,"advertDetail":"5p医学广告语，小欧也想不出来"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 3
         * picture : https://www.dgli.net/resource/images/advert/banner_conference.png
         * detailUrl : http://www.dgli.net:8888/subscription/webIndex/tid/3/isShare/1?1=1
         * tag : 5
         * advertDetail : 5p医学广告语，小欧也想不出来
         */

        private String id;
        private String picture;
        private String detailUrl;
         //tag：1 众筹；tag：2 团建；tag：3 课程库（忽略） ；tag：4 专辑；tag：5 专栏；tag：6 工作坊。  getAdvert 启动页广告接口，跳去对应模块详情页
         private int tag;
        private String advertDetail;
        private String appDetail;

        public String getId() {
            return id;
        }
        public int getIntId(){
            return Integer.parseInt(id);
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public String getAppDetail() {
            return appDetail;
        }

        public void setAppDetail(String appDetail) {
            this.appDetail = appDetail;
        }

        public String getAdvertDetail() {
            return advertDetail;
        }

        public void setAdvertDetail(String advertDetail) {
            this.advertDetail = advertDetail;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.picture);
            dest.writeString(this.detailUrl);
            dest.writeInt(this.tag);
            dest.writeString(this.advertDetail);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.picture = in.readString();
            this.detailUrl = in.readString();
            this.tag = in.readInt();
            this.advertDetail = in.readString();
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
