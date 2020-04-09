package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;


public class UserCenterShareBean extends Result {




    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * shareUrl : http://120.79.212.227:8081/page/static/userCenter/invite.html?accessToken=99399334bb2b6651df028217dde4e49e2fdaf92
         * shareTitle : 分享5p医学APP
         * sharePicture : http://120.79.212.227:8081/resource/images/icons/logo72.png
         * shareDescribe : 分享5p医学APP--告别被动医疗，推动精准健康
         */

        private String shareUrl;
        private String shareTitle;
        private String sharePicture;
        private String shareDescribe;

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

        public String getShareDescribe() {
            return shareDescribe;
        }

        public void setShareDescribe(String shareDescribe) {
            this.shareDescribe = shareDescribe;
        }
    }

}
