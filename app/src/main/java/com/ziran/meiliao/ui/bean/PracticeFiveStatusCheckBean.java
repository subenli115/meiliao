package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 *
 * 第五类练习数据查询 on 2018/9/14.
 */

public class PracticeFiveStatusCheckBean extends Result{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {


        /**
         * picture : default.png
         * id : 1
         * senseList : []
         * itemId : 1
         * itemTitle : 身体扫描
         */

        private String picture;
        private int id;
        private int itemId;
        private String itemTitle;
        private List<?> senseList;

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

        public List<?> getSenseList() {
            return senseList;
        }

        public void setSenseList(List<?> senseList) {
            this.senseList = senseList;
        }
    }


}
