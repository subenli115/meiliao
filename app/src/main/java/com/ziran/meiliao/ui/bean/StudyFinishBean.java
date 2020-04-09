package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/9 17:10
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/9$
 * @updateDes ${TODO}
 */

public class StudyFinishBean extends Result {

    /**
     * data : {"coursePrice":1000,"title":"第一堂课 自我關愛/正念相逢","headImg":"https://www.dgli
     * .net/upload/images/userHeadImg/95bc8f21af644133b1d9dd07094fc6f8.jpeg","shareDescript":"正念在成瘾群体中的应用","nickName":"一生守护1",
     * "shareUrl":"http://www.dgli.net:8888/subscription/singleCourseShareIndex/tid/1/targetId/1/isShare/1/accessToken
     * /4244083f1f248ae9bf8603f09dcbf9c19417e","shareTitle":"正念在成瘾群体中的应用","bg":"https://www.dgli
     * .net/resource/images/subscribeShareBg/share1.png","qrCode":"https://www.dgli.net/resource/images/subscribeShareBg/app.png",
     * "sharePicture":"https://www.dgli.net/resource/images/subscribeShareBg/share1.png","courseName":"正念在成瘾群体中的应用"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean  extends ShareBean{
        /**
         * coursePrice : 1000
         * title : 第一堂课 自我關愛/正念相逢
         * headImg : https://www.dgli.net/upload/images/userHeadImg/95bc8f21af644133b1d9dd07094fc6f8.jpeg
         * shareDescript : 正念在成瘾群体中的应用
         * nickName : 一生守护1
         * shareUrl : http://www.dgli.net:8888/subscription/singleCourseShareIndex/tid/1/targetId/1/isShare/1/accessToken
         * /4244083f1f248ae9bf8603f09dcbf9c19417e
         * shareTitle : 正念在成瘾群体中的应用
         * bg : https://www.dgli.net/resource/images/subscribeShareBg/share1.png
         * qrCode : https://www.dgli.net/resource/images/subscribeShareBg/app.png
         * sharePicture : https://www.dgli.net/resource/images/subscribeShareBg/share1.png
         * courseName : 正念在成瘾群体中的应用
         */

        private String coursePrice;
        private String title;
        private String headImg;
        private String nickName;
        private String bg;
        private String qrCode;
        private String sharePicture;
        private String courseName;

        public String getCoursePrice() {
            return coursePrice;
        }

        public void setCoursePrice(String coursePrice) {
            this.coursePrice = coursePrice;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHeadImg() {
            return headImg;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getBg() {
            return bg;
        }

        public void setBg(String bg) {
            this.bg = bg;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getSharePicture() {
            return sharePicture;
        }

        public void setSharePicture(String sharePicture) {
            this.sharePicture = sharePicture;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }
}
