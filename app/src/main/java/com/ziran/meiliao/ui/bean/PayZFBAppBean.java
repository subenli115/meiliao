package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class PayZFBAppBean extends Result {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * orderId : 1-00002098426615
         * body : alipay_root_cert_sn=687b59193f3f462dd5336e5abf83c5d8_02941eef3187dddf3d3b83462e1dfcf6&alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_cert_sn=fd2e7a6d152ff9d83d60b5f885cb1923&app_id=2021001163634137&biz_content=%7B%22out_trade_no%22%3A%221-00002098426615%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%2218000M%E5%B8%81%22%2C%22total_amount%22%3A%2218.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F192.168.1.4%3A9999%2Fzfb%2FreturnUrl&return_url=http%3A%2F%2F192.168.1.4%3A8080%2F%23%2Flogin&sign=XYDO6sQyLpZTGSoNJrJtJjR6iK1tzhThRYz31%2FM0hkoCWXUVVk%2FWTTMJeEBZPxB%2BU1btU6YzGhosl%2FOE41VoDhh7dAmiHGMKvZjsCB%2BEBgrZR4vxglyGHxqRHSqFc5GQUzQboWFYJIRia5UCb6rS%2FE98wua03nXcXhZHo6m5W023zx2o7MEi%2BvNcgv1RRz3vmFC7S15FBpMyAtUoOI8NrJbFk42y1vniGUxforPEGj3ImUscpF5DVtna%2F8GLe5k7rJv3PlsJHJjh6GmFkjp3FhkYAkxdX8pBRJv69gBrjR5nxeEOI%2Buc43Y6SGaOqberxKLH7qZ1wwTmwTGHGwJSag%3D%3D&sign_type=RSA2&timestamp=2020-06-18+11%3A16%3A04&version=1.0
         */

        private String orderId;
        private String body;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    }
