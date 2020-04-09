package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/14 10:05
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/14$
 * @updateDes ${TODO}
 */

public class DailyMindBean extends Result {

    /**
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
         */

        private int timePoint;

        public int getTimePoint() {
            return timePoint;
        }

        public void setTimePoint(int timePoint) {
            this.timePoint = timePoint;
        }

        private boolean able;

        public boolean isAble() {
            return able;
        }

        public void setAble(boolean able) {
            this.able = able;
        }



    }
}
