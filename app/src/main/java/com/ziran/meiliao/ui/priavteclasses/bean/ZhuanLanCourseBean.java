package com.ziran.meiliao.ui.priavteclasses.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/7 17:47
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/7$
 * @updateDes ${TODO}
 */

public class ZhuanLanCourseBean extends Result {

    /**
     * data : {"detail":null,"title":"第一堂课 知己知彼","buyTimes":1,"shareDescript":"","isLike":true,"status":0,"tag":0,"shareUrl":"http://www
     * .baidu.com","shareTitle":"第一堂课 知己知彼","seeTimes":938,"sharePic":"http://www.dgli.net:8888/resource/images/course/32.png",
     * "isCollect":true,"url":"fbJkPRoLHwckd2CimUfhbd4RM33BTAHzcJpMzkDQAYbbN5wZnw0enaRIMQswuKSB1rDmbuJGExZw\nbOvDdV3KpEFIr
     * /aZb4lSaEYvaRyBmzuNm4sCdDBCbRPR2JmFBkSV"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * detail : null
         * title : 第一堂课 知己知彼
         * buyTimes : 1
         * shareDescript :
         * isLike : true
         * status : 0
         * tag : 0
         * shareUrl : http://www.baidu.com
         * shareTitle : 第一堂课 知己知彼
         * seeTimes : 938
         * sharePic : http://www.dgli.net:8888/resource/images/course/32.png
         * isCollect : true
         * url : fbJkPRoLHwckd2CimUfhbd4RM33BTAHzcJpMzkDQAYbbN5wZnw0enaRIMQswuKSB1rDmbuJGExZw
         bOvDdV3KpEFIr/aZb4lSaEYvaRyBmzuNm4sCdDBCbRPR2JmFBkSV
         */

        private String detail;
        private String title;
        private int buyTimes;
        private String shareDescript;
        private boolean isLike;
        @SerializedName("status")
        private int statusX;
        private int tag;
        private String shareUrl;
        private String shareTitle;
        private int seeTimes;
        private String sharePic;
        private boolean isCollect;
        private String url;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTitle() {
            return title;
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

        public String getShareDescript() {
            return shareDescript;
        }

        public void setShareDescript(String shareDescript) {
            this.shareDescript = shareDescript;
        }

        public boolean isIsLike() {
            return isLike;
        }

        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
        }

        public int getStatusX() {
            return statusX;
        }

        public void setStatusX(int statusX) {
            this.statusX = statusX;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
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

        public int getSeeTimes() {
            return seeTimes;
        }

        public void setSeeTimes(int seeTimes) {
            this.seeTimes = seeTimes;
        }

        public String getSharePic() {
            return sharePic;
        }

        public void setSharePic(String sharePic) {
            this.sharePic = sharePic;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
