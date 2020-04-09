package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/26 14:42
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/26$
 * @updateDes ${TODO}
 */

public class GiveGiftResultBean extends Result {

    /**
     * data : {"yue":16500}
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
         * yue : 16500
         */

        private int yue;

        public int getYue() {
            return yue;
        }

        public void setYue(int yue) {
            this.yue = yue;
        }
    }
}
