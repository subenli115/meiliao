package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class PayWXBean extends Result {


    /**
     * data : {"body":"<form name=\"punchout_form\" method=\"post\" action=\"https://openapi.alipaydev.com/gateway.do?app_cert_sn=da4e07bf482086a13b4d502815f2bd1b&charset=utf-8&alipay_root_cert_sn=6bc29aa3b4d406c43483ffea81e08d22&method=alipay.trade.wap.pay&sign=iagawHTCvetjv96U6SqywhobOblmh%2FL5zZ9KozOtVMKrcogw1k5GlKpNi7coUf3WXfE3cGxABc%2B4UPMBcaJ0gDYWHdyflCIgTWOtW%2BxjV2rbSS3BGkRvrohSAGlzLfqfvNl8MCiadPrg2m2oIYG1J9jhkrmMKp6hwoZgCNJ%2BySZhsXicuOvKvoY7SYfqv4%2F152cJQO7qvlspOzWmX%2Fq1f5riaLiPkzN5EgyGQ72PTSPxr1rHWwDs%2FmjjbYOXfUE0wGDr8qOtWARxdkY7u47RRcK6oIl87kX6LYi3wteMUhWU1bKOuvS7iCJszMoVUhCb3ouFNhf4ddxEPTNcswq4DA%3D%3D&return_url=http%3A%2F%2F192.168.1.4%3A8080%2F%23%2Flogin&notify_url=http%3A%2F%2F192.168.1.4%3A9999%2Fzfb%2FreturnUrl&version=1.0&app_id=2016102200736397&sign_type=RSA2&timestamp=2020-05-21+17%3A17%3A28&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json\">\n<input type=\"hidden\" name=\"biz_content\" value=\"{&quot;out_trade_no&quot;:&quot;db36ac30-d6e9-4a74-a8b2-2833b237b1b6&quot;,&quot;product_code&quot;:&quot;QUICK_WAP_WAY&quot;,&quot;subject&quot;:&quot;比心&quot;,&quot;total_amount&quot;:&quot;1000&quot;}\">\n<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n<\/form>\n<script>document.forms[0].submit();<\/script>"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String body;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        /**
         * nonce_str : azeOBc4ftmmnz2k9
         * appid : wx9f81f7c83ead9d5d
         * sign : 33892E558E4AC235DD2071521B344667
         * trade_type : APP
         * return_msg : OK
         * result_code : SUCCESS
         * mch_id : 1543284141
         * return_code : SUCCESS
         * orderId
         * prepay_id : wx17150843260609823b3d44851945812700
         */

        private String orderId;
        private String nonce_str;
        private String appid;
        private String sign;
        private String trade_type;
        private String return_msg;
        private String result_code;
        private String mch_id;
        private String return_code;
        private String prepay_id;

        public String getTimeStamp() {
            return timestamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timestamp = timeStamp;
        }

        private String timestamp;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }
    }
}
