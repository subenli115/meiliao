package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class VisitorBean extends Result {

    /**
     * data : {"love":0,"visitor":0}
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
         * love : 0
         * visitor : 0
         */

        private int love;
        private int visitor;

        public int getLove() {
            return love;
        }

        public void setLove(int love) {
            this.love = love;
        }

        public int getVisitor() {
            return visitor;
        }

        public void setVisitor(int visitor) {
            this.visitor = visitor;
        }
    }
}
