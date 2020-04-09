package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2018/9/21.
 */

public class PracticeThreeBean extends Result{


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * picture : http://www.dgli.net/resource/images/practiceActivity/default.png
         * id : 1
         * practiceStatus : 0
         * itemId : 1
         * itemTitle : 身体扫描
         */

        private String picture;
        private int id;
        private int practiceStatus;
        private int itemId;
        private String itemTitle;

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

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }
    }
}
