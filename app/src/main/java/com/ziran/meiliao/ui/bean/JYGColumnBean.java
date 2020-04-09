package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */

public class JYGColumnBean extends Result {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 冥想
         * columnId : 1
         */

        private String name;
        private String  columnId;

        public DataBean(String name, String columnId) {
            this.name = name;
            this.columnId = columnId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColumnId() {
            return columnId;
        }

        public void setColumnId(String columnId) {
            this.columnId = columnId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "name='" + name + '\'' +
                    ", columnId=" + columnId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "JYGColumnBean{" +
                "data=" + data +
                '}';
    }
}
