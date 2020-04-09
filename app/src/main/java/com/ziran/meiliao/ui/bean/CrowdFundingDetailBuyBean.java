package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/13 11:25
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/13$
 * @updateDes ${TODO}
 */

public class CrowdFundingDetailBuyBean extends Result {

    /**
     * data : {"detailList":[{"time":1513135472000,"name":"我之前","totalPrice":2200}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<DetailListBean> detailList;
        private List<DetailListBean> refundList;
        private int supportMembers;

        public List<DetailListBean> getRefundList() {
            return refundList;
        }

        public void setRefundList(List<DetailListBean> refundList) {
            this.refundList = refundList;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public int getSupportMembers() {
            return supportMembers;
        }

        public void setSupportMembers(int supportMembers) {
            this.supportMembers = supportMembers;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * time : 1513135472000
             * name : 我之前
             * totalPrice : 2200.0
             */

            private long time;
            private String name;
            private double totalPrice;
            private String headImg;
            private String picture;

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTotalPrice() {
                return (int)totalPrice;
            }

            public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
            }
        }
    }
}
