package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class AlbumShareDataBean extends Result {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * picture : https://www.dgli.net:8888/resource/images/album/sharePic/11share.png
         * duration : 9
         * albumName : 马克•威廉姆斯
         * headImg : https://www.dgli.net/upload/images/userHeadImg/bb3ab58bf723466dbbe98b0105d4953e.jpg
         * days : 0
         * shareDescript : 分享音频：马克•威廉姆斯--身心的放松
         * shareUrl : http://www.psytap.com/resource/zhuanji_fx/index.html?albumId=11
         * shareTitle : 身心的放松
         *
         * userName : 用户0039264
         * musicName : 身心的放松
         * sharePicture : https://www.dgli.net:8888/resource/images/album/sharePic/11share.png
         */

        private String picture;
        private String duration;
        private String albumName;
        private String headImg;
        private int days;
        private String shareDescript;
        private String shareUrl;
        private String shareTitle;
        private String userName;
        private String musicName;
        private String sharePicture;
        private boolean haveShare;


        public boolean isHaveShare() {
            return haveShare;
        }

        public void setHaveShare(boolean haveShare) {
            this.haveShare = haveShare;
        }
        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getAlbumName() {
            return albumName;
        }

        public void setAlbumName(String albumName) {
            this.albumName = albumName;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public String getShareDescript() {
            return shareDescript;
        }

        public void setShareDescript(String shareDescript) {
            this.shareDescript = shareDescript;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMusicName() {
            return musicName;
        }

        public void setMusicName(String musicName) {
            this.musicName = musicName;
        }

        public String getSharePicture() {
            return sharePicture;
        }

        public void setSharePicture(String sharePicture) {
            this.sharePicture = sharePicture;
        }
    }
}
