package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/7/17 16:24
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/7/17$
 * @updateDes ${TODO}
 */

public class GainSpreadBean extends Result {

    /**
     * data : {"total":1,"albumId":1,"qrcode":"http://www.psytap.com:8888/page/content/download.html?t=1500280005892","name":"彭凯茵-",
     * "pic":"http://www.psytap.com:8888/resource/images/album/shareAlbum/1.png","isShow":true}
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
         * total : 1
         * albumId : 1
         * qrcode : http://www.psytap.com:8888/page/content/download.html?t=1500280005892
         * name : 彭凯茵-
         * pic : http://www.psytap.com:8888/resource/images/album/shareAlbum/1.png
         * isShow : true
         */

        private String  total;
        private String albumId;
        private String qrcode;
        private String name;
        private String pic;
        private boolean isShow;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String  getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public boolean isIsShow() {
            return isShow;
        }

        public void setIsShow(boolean isShow) {
            this.isShow = isShow;
        }
    }
}
