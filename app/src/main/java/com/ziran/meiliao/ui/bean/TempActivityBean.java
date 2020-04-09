package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/16 18:44
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/16$
 * @updateDes ${TODO}
 */

public class TempActivityBean extends Result {

    /**
     * data : {"picture":"https://www.dgli.net/resource/images/conference/banner_conference.png ","title":"首届正念应用年会",
     * "time":"2017年12月15日-2017年12月17日","shareTitle":"首届正念应用年会","shareUrl":"","sharePic":"https://www.dgli
     * .net/resource/images/conference/banner_conference.png "}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends ShareBean implements Parcelable {
        /**
         * picture : https://www.dgli.net/resource/images/conference/banner_conference.png
         * title : 首届正念应用年会
         * time : 2017年12月15日-2017年12月17日
         * shareTitle : 首届正念应用年会
         * shareUrl :
         * sharePic : https://www.dgli.net/resource/images/conference/banner_conference.png
         */


        private String picture;
        private String title;
        private String time;
        private String url;
        private String zhiboUrl;
        private String icon;

        public String getIcon() {
            return icon;
        }
        public void setIcon(String icon) {
            this.icon = icon;
        }
        public String getZhiboUrl() {
            return zhiboUrl;
        }

        public void setZhiboUrl(String zhiboUrl) {
            this.zhiboUrl = zhiboUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.picture);
            dest.writeString(this.title);
            dest.writeString(this.time);
            dest.writeString(this.url);
            dest.writeString(this.zhiboUrl);
            dest.writeString(this.icon);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.picture = in.readString();
            this.title = in.readString();
            this.time = in.readString();
            this.url = in.readString();
            this.zhiboUrl = in.readString();
            this.icon = in.readString();
        }
    }
}
