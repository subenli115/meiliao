package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class CourseShareBean extends Result {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * shareDescript : 和锻炼身体一个道理，花时间平和心境也会产生诸多益处：消除紧张压力、改善分心强化专注力、缓解慢性疼痛、培养耐心与慈悲心……在这个移动互联网的世界，我们试一试关机，开放、悲悯地专注于当下。
         * shareTitle : 报名 | 医学及心理学中的正念——正念减压四日工作坊
         * shareUrl : https://www.psytap.com/resource/activity_fx/index.html?activityId=1
         * sharePic : https://dgli.net:8888/resource/images/activity/homePic/default.png
         */

        private String shareDescript;
        private String shareTitle;
        private String shareUrl;
        private String sharePic;

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

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getSharePic() {
            return sharePic;
        }

        public void setSharePic(String sharePic) {
            this.sharePic = sharePic;
        }
    }
}
