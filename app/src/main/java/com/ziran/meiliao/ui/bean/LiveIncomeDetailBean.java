package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/5/24 18:57
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/5/24$
 * @updateDes ${TODO}
 */

public class LiveIncomeDetailBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * detail : 一生守护购买了课程慈心静观练习系列之无特定对象的慈心练习一
         * headImg : https://www.dgli.net/upload/images/userHeadImg/ce179308089440fd9b612cd288e51ea2.jpeg
         * detailPrice : 200.0
         * name : 一生守护
         * typeId : 1
         */

        private String detail;
        private String headImg;
        private double detailPrice;
        private String name;
        private int typeId;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public double getDetailPrice() {
            return detailPrice;
        }

        public void setDetailPrice(double detailPrice) {
            this.detailPrice = detailPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }
    }
}
