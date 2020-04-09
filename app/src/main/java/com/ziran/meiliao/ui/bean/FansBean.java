package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/7 10:50
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/7$
 * @updateDes ${TODO}
 */

public class FansBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String picture;

        private String name;

        private int fansId;

        public int getFansId() {
            return fansId;

        }
        public void setFansId(int fansId) {
            this.fansId = fansId;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
