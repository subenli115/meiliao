package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/6/13 15:17
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/6/13
 * @updateDes ${TODO}
 */

public class UserVipV1Bean extends Result {

    /**
     * title : 一年
     * price : 568
     * type : mem-1
     * vipId : 1
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private float price;
        private String type;
        private int vipId;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getVipId() {
            return vipId;
        }

        public void setVipId(int vipId) {
            this.vipId = vipId;
        }

        @Override
        public String toString() {
            return "DataBean{" + "title='" + title + '\'' + ", price=" + price + ", type='" + type + '\'' + ", vipId=" + vipId + ", " +
                    "isSelect=" + isSelect + '}';
        }

    }

    @Override
    public String toString() {
        return "UserVipV1Bean{" + "data=" + data + '}';
    }
}
