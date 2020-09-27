package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class LabelHobbyBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 10
         * labelContent : 流行
         * labelColour :
         * labelImages :
         * level : 2
         * tableId : 4
         * createTime : 2020-07-02 11:57:45
         */

        private int id;
        private String labelContent;
        private String labelColour;
        private String labelImages;
        private int level;
        private int tableId;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabelContent() {
            return labelContent;
        }

        public void setLabelContent(String labelContent) {
            this.labelContent = labelContent;
        }

        public String getLabelColour() {
            return labelColour;
        }

        public void setLabelColour(String labelColour) {
            this.labelColour = labelColour;
        }

        public String getLabelImages() {
            return labelImages;
        }

        public void setLabelImages(String labelImages) {
            this.labelImages = labelImages;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getTableId() {
            return tableId;
        }

        public void setTableId(int tableId) {
            this.tableId = tableId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
