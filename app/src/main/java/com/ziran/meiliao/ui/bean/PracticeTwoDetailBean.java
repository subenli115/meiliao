package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 *  第二类练习数据 on 2018/9/14.
 */

public class PracticeTwoDetailBean extends Result{


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {


        /**
         * id : 1
         * bgPic : http://www.dgli.net/resource/images/practiceActivity/1bg.png
         * practiceStatus : 0
         * itemId : 1
         * roundPic : http://www.dgli.net/resource/images/practiceActivity/roundPic.png
         * itemTitle : 身体扫描
         */

        private int id;
        private String bgPic;
        private int practiceStatus;
        private int itemId;
        private String roundPic;
        private String itemTitle;
        private String picture;

        public String getIsNinthPra() {
            return isNinthPra;
        }

        public void setIsNinthPra(String isNinthPra) {
            this.isNinthPra = isNinthPra;
        }

        private String isNinthPra;

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        private String notice;


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

        public String getBgPic() {
            return bgPic;
        }

        public void setBgPic(String bgPic) {
            this.bgPic = bgPic;
        }

        public int getPracticeStatus() {
            return practiceStatus;
        }

        public void setPracticeStatus(int practiceStatus) {
            this.practiceStatus = practiceStatus;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public String getRoundPic() {
            return roundPic;
        }

        public void setRoundPic(String roundPic) {
            this.roundPic = roundPic;
        }

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }
    }
}
