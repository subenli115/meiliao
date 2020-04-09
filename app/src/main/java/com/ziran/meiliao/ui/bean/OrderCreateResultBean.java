package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/8 13:37
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/8$
 * @updateDes ${TODO}
 */

public class OrderCreateResultBean extends Result {

    /**
     * data : {"accessToken":"184927895d14daa3cf8e0c54be02a46841504a","orderId":null}
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
         * accessToken : 184927895d14daa3cf8e0c54be02a46841504a
         * orderId : null
         */

        private String orderId;



        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderIdX) {
            this.orderId = orderIdX;
        }
    }
}
