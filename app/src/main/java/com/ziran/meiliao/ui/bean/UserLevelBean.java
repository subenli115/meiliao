package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2018/6/5.
 */

public class UserLevelBean extends Result {


    /**
     * data : {"id":0,"title":"一年会员资格","price":300,"type":"普通会员"}
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
         * nextLevelPercent : 0
         * level : 普通会员
         * pic : https://www.dgli.net/resource/images/level/regular.png
         */

        private int nextLevelPercent;
        private String level;
        private String pic;

        public int getNextLevelPercent() {
            return nextLevelPercent;
        }

        public void setNextLevelPercent(int nextLevelPercent) {
            this.nextLevelPercent = nextLevelPercent;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
