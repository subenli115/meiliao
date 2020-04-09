package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * author admin
 * create  2017/3/27 16
 * des     ${TODO}
 * <p>
 * updateAuthor     $admin
 * updateDate   2017/3/27 16
 * updateDes    ${TODO}
 */

public class ResBean extends Result {


    /**
     * data : {"extension":"http://www.psytap.com/page/static/rec/r1.html","protocol":"http://www.psytap.com/page/content/protocol.html",
     * "e_shareDescript":"5P医学 - 推广大使","shareDescript":"5P医学","e_sharePicture":"http://www.psytap.com/page/static/rec/r1.html",
     * "ios":false,"e_shareUrl":"http://www.psytap.com/page/static/rec/r1.html","e_shareTitle":"推广大使","shareUrl":"http://www.psytap
     * .com/page/content/protocol.html","shareTitle":"分享给好友","sharePicture":"http://www.psytap.com/page/static/rec/r1.html"}
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
         * extension : http://www.psytap.com/page/static/rec/r1.html
         * protocol : http://www.psytap.com/page/content/protocol.html
         * e_shareDescript : 5P医学 - 推广大使
         * shareDescript : 5P医学
         * e_sharePicture : http://www.psytap.com/page/static/rec/r1.html
         * ios : false
         * e_shareUrl : http://www.psytap.com/page/static/rec/r1.html
         * e_shareTitle : 推广大使
         * shareUrl : http://www.psytap.com/page/content/protocol.html
         * shareTitle : 分享给好友
         * sharePicture : http://www.psytap.com/page/static/rec/r1.html
         */

        private String extension;
        private String protocol;
        private String e_shareDescript;
        private String shareDescript;
        private String e_sharePicture;
        private boolean ios;
        private String e_shareUrl;
        private String e_shareTitle;
        private String shareUrl;
        private String shareTitle;
        private String sharePicture;

        public String getShareDescribe() {
            return shareDescribe;
        }

        public void setShareDescribe(String shareDescribe) {
            this.shareDescribe = shareDescribe;
        }

        private String shareDescribe;

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getE_shareDescript() {
            return e_shareDescript;
        }

        public void setE_shareDescript(String e_shareDescript) {
            this.e_shareDescript = e_shareDescript;
        }

        public String getShareDescript() {
            return shareDescript;
        }

        public void setShareDescript(String shareDescript) {
            this.shareDescript = shareDescript;
        }

        public String getE_sharePicture() {
            return e_sharePicture;
        }

        public void setE_sharePicture(String e_sharePicture) {
            this.e_sharePicture = e_sharePicture;
        }

        public boolean isIos() {
            return ios;
        }

        public void setIos(boolean ios) {
            this.ios = ios;
        }

        public String getE_shareUrl() {
            return e_shareUrl;
        }

        public void setE_shareUrl(String e_shareUrl) {
            this.e_shareUrl = e_shareUrl;
        }

        public String getE_shareTitle() {
            return e_shareTitle;
        }

        public void setE_shareTitle(String e_shareTitle) {
            this.e_shareTitle = e_shareTitle;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public String getSharePicture() {
            return sharePicture;
        }

        public void setSharePicture(String sharePicture) {
            this.sharePicture = sharePicture;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "extension='" + extension + '\'' +
                    ", protocol='" + protocol + '\'' +
                    ", e_shareDescript='" + e_shareDescript + '\'' +
                    ", shareDescript='" + shareDescript + '\'' +
                    ", e_sharePicture='" + e_sharePicture + '\'' +
                    ", ios=" + ios +
                    ", e_shareUrl='" + e_shareUrl + '\'' +
                    ", e_shareTitle='" + e_shareTitle + '\'' +
                    ", shareUrl='" + shareUrl + '\'' +
                    ", shareTitle='" + shareTitle + '\'' +
                    ", sharePicture='" + sharePicture + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.extension);
            dest.writeString(this.protocol);
            dest.writeString(this.e_shareDescript);
            dest.writeString(this.shareDescript);
            dest.writeString(this.e_sharePicture);
            dest.writeByte(this.ios ? (byte) 1 : (byte) 0);
            dest.writeString(this.e_shareUrl);
            dest.writeString(this.e_shareTitle);
            dest.writeString(this.shareUrl);
            dest.writeString(this.shareTitle);
            dest.writeString(this.sharePicture);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.extension = in.readString();
            this.protocol = in.readString();
            this.e_shareDescript = in.readString();
            this.shareDescript = in.readString();
            this.e_sharePicture = in.readString();
            this.ios = in.readByte() != 0;
            this.e_shareUrl = in.readString();
            this.e_shareTitle = in.readString();
            this.shareUrl = in.readString();
            this.shareTitle = in.readString();
            this.sharePicture = in.readString();
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

    @Override
    public String toString() {
        return "ResBean{" +
                "data=" + data +
                '}';
    }
}
