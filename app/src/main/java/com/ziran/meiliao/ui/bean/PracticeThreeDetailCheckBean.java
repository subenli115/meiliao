package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * 第三类数据查询 on 2018/9/14.
 */

public class PracticeThreeDetailCheckBean extends Result {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {

        /**
         * picture : http://www.dgli.net/resource/images/practiceActivity/1.png
         * id : 1
         * amounts : 1
         * shareTitle :
         * shareUrl :
         * sharePic :
         * shareDescribe :
         * detailList : [{"detail":"我他妈的不想再折腾了，请你放过我","timeSlot":"中午","detailId":2}]
         * itemId : 1
         * itemTitle : 身体扫描
         */
        private String bgPic;
        private String picture;
        private int id;
        private int amounts;
        private String shareTitle;
        private String shareUrl;
        private String sharePic;
        private String shareDescribe;
        private int itemId;
        private String itemTitle;
        private List<DetailListBean> detailList;
        private String notice;

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }
        public String getBgPic() {
            return bgPic;
        }

        public void setBgPic(String bgPic) {
            this.bgPic = bgPic;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAmounts() {
            return amounts;
        }

        public void setAmounts(int amounts) {
            this.amounts = amounts;
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

        public String getShareDescribe() {
            return shareDescribe;
        }

        public void setShareDescribe(String shareDescribe) {
            this.shareDescribe = shareDescribe;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * detail : 我他妈的不想再折腾了，请你放过我
             * timeSlot : 中午
             * detailId : 2
             */

            private String detail;
            private String timeSlot;
            private int detailId;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getTimeSlot() {
                return timeSlot;
            }

            public void setTimeSlot(String timeSlot) {
                this.timeSlot = timeSlot;
            }

            public int getDetailId() {
                return detailId;
            }

            public void setDetailId(int detailId) {
                this.detailId = detailId;
            }
        }
    }
}
