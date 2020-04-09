package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/26 13:44
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/26$
 * @updateDes ${TODO}
 */

public class GiftBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 气球
         * path : http://www.psytap.com:8888/resource/images/course/gift/balloon
         * giftId : 1
         * needCoin : 30
         */

        private String name;
        private String path;
        private int giftId;
        private int needCoin;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getGiftId() {
            return giftId;
        }

        public void setGiftId(int giftId) {
            this.giftId = giftId;
        }

        public int getNeedCoin() {
            return needCoin;
        }

        public void setNeedCoin(int needCoin) {
            this.needCoin = needCoin;
        }
    }
}
