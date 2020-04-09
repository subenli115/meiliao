package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/8 11:19
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/8$
 * @updateDes ${TODO}
 */

public class SJKLiveOverBean extends Result {

    /**
     * data : {"total":"10:00:06","picture":"35.png","likeCount":2,"watchCount":270}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 10:00:06
         * picture : 35.png
         * likeCount : 2
         * watchCount : 270
         */

        private String total;
        private String picture;
        private int likeCount;
        private int watchCount;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getWatchCount() {
            return watchCount;
        }

        public void setWatchCount(int watchCount) {
            this.watchCount = watchCount;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "total='" + total + '\'' +
                    ", picture='" + picture + '\'' +
                    ", likeCount=" + likeCount +
                    ", watchCount=" + watchCount +
                    '}';
        }
    }
}
