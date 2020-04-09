package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * 第五类数据 on 2018/9/14.
 */

public class PracticeFiveDetailBean extends Result{

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
         * senseChooseList : [{"senseDetail":"不想回答,并且丢一个草泥马给你","senseId":1},{"senseDetail":"不想回答,并且丢一个草泥马给你","senseId":2},{"senseDetail":"不想回答,并且丢一个草泥马给你","senseId":3}]
         * itemId : 1
         * itemTitle : 身体扫描
         */

        private String picture;
        private int id;
        private int itemId;
        private String itemTitle;
        private List<SenseChooseListBean> senseList;
        private List<SenseChooseListBean> senseChooseList;
        private String bgPic;
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

        public List<SenseChooseListBean> getSenseList() {
            return senseList;
        }

        public void setSenseList(List<SenseChooseListBean> senseList) {
            this.senseList = senseList;
        }

        public List<SenseChooseListBean> getSenseChooseList() {
            return senseChooseList;
        }

        public void setSenseChooseList(List<SenseChooseListBean> senseChooseList) {
            this.senseChooseList = senseChooseList;
        }

        public static class SenseChooseListBean {
            /**
             * senseDetail : 不想回答,并且丢一个草泥马给你
             * senseId : 1
             */

            private String senseDetail;
            private int senseId;
            private int senseStatus;
            private int practiceStatus;


            public int getSenseStatus() {
                return senseStatus;
            }

            public void setSenseStatus(int senseStatus) {
                this.senseStatus = senseStatus;
            }


            public int getPracticeStatus() {
                return practiceStatus;
            }

            public void setPracticeStatus(int practiceStatus) {
                this.practiceStatus = practiceStatus;
            }

            public String getSenseDetail() {
                return senseDetail;
            }

            public void setSenseDetail(String senseDetail) {
                this.senseDetail = senseDetail;
            }

            public int getSenseId() {
                return senseId;
            }

            public void setSenseId(int senseId) {
                this.senseId = senseId;
            }
        }
    }

}
