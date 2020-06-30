package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */

public class PracticePageBean extends Result{

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
         * number : 1
         */

        private String startMusicUrl;
        private int number;
        private List<ListBean> list;

        public String getStartMusicUrl() {
            return startMusicUrl;
        }

        public void setStartMusicUrl(String startMusicUrl) {
            this.startMusicUrl = startMusicUrl;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * musicUrl :
             */

            private String bg;
            private String musicUrl;
            private String vedioPathUrl;

            public String getVedioPathUrl() {
                return vedioPathUrl;
            }

            public void setVedioPathUrl(String vedioPathUrl) {
                this.vedioPathUrl = vedioPathUrl;
            }

            public String getBg() {
                return bg;
            }

            public void setBg(String bg) {
                this.bg = bg;
            }

            public String getMusicUrl() {
                return musicUrl;
            }

            public void setMusicUrl(String musicUrl) {
                this.musicUrl = musicUrl;
            }

            @Override
            public String toString() {
                return "ListBean{" + "bg='" + bg + '\'' + ", musicUrl='" + musicUrl + '\'' + ", vedioPathUrl='" + vedioPathUrl + '\'' + '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" + "startMusicUrl='" + startMusicUrl + '\'' + ", number=" + number + ", list=" + list + '}';
        }
    }

    @Override
    public String toString() {
        return "PracticePageBean{" + "data=" + data + '}';
    }
}
