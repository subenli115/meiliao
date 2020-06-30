package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class PayWxH5Bean extends Result {


    /**
     * data : {"referer":"www.ziran168.cn","mweb_url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx22123348205352efb65a11bb1151619600&package=1349863007"}
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
         * referer : www.ziran168.cn
         * mweb_url : https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx22123348205352efb65a11bb1151619600&package=1349863007
         */

        private String referer;
        private String mweb_url;

        public String getReferer() {
            return referer;
        }

        public void setReferer(String referer) {
            this.referer = referer;
        }

        public String getMweb_url() {
            return mweb_url;
        }

        public void setMweb_url(String mweb_url) {
            this.mweb_url = mweb_url;
        }
    }
}
