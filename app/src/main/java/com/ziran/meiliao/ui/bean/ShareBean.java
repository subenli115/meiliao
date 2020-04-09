package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/20 19:28
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/20$
 * @updateDes ${TODO}
 */

public class ShareBean implements Parcelable {

    private String shareDescribe;
    private String shareImgPath;
    private String shareDescript;
    private String shareTitle;
    private String sharePic;
    private String sharePagePath;
    private String shareUrl;
    private int isShare;
    public String getShareDescribe() {
        return shareDescribe;
    }

    public void setShareDescribe(String shareDescribe) {
        this.shareDescribe = shareDescribe;
    }
    public int getIsShare() {
        return isShare;
    }

    public void setIsShare(int isShare) {
        this.isShare = isShare;
    }

    public String getShareImgPath() {
        return shareImgPath;
    }

    public void setShareImgPath(String shareImgPath) {
        this.shareImgPath = shareImgPath;
    }

    public String getShareDescript() {
        return shareDescript;
    }

    public void setShareDescript(String shareDescript) {
        this.shareDescript = shareDescript;
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

    public String getSharePagePath() {
        return sharePagePath;
    }

    public void setSharePagePath(String sharePagePath) {
        this.sharePagePath = sharePagePath;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    @Override
    public String toString() {
        return "ShareBean{" + "shareImgPath='" + shareImgPath + '\'' + ", shareDescript='" + shareDescript + '\'' + ", shareTitle='" +
                shareTitle + '\'' + ", sharePic='" + sharePic + '\'' + ", sharePagePath='" + sharePagePath + '\'' + ", shareUrl='" +
                shareUrl + '\'' + '}';
    }

    public ShareBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.shareImgPath);
        dest.writeString(this.shareDescript);
        dest.writeString(this.shareTitle);
        dest.writeString(this.sharePic);
        dest.writeString(this.sharePagePath);
        dest.writeString(this.shareUrl);
    }

    protected ShareBean(Parcel in) {
        this.shareImgPath = in.readString();
        this.shareDescript = in.readString();
        this.shareTitle = in.readString();
        this.sharePic = in.readString();
        this.sharePagePath = in.readString();
        this.shareUrl = in.readString();
    }

    public static final Creator<ShareBean> CREATOR = new Creator<ShareBean>() {
        @Override
        public ShareBean createFromParcel(Parcel source) {
            return new ShareBean(source);
        }

        @Override
        public ShareBean[] newArray(int size) {
            return new ShareBean[size];
        }
    };
}
