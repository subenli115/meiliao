package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/8 11:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/8$
 * @updateDes ${TODO}
 */

public class PracticeListBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * picture : https://www.dgli.net/resource/images/album/15.1.png
         * createTime : 2018-01-08 10:22
         * duration : 24
         * title : 黄庭禅坐
         * albumId : 15
         * hisId : 3183
         * musicUrl : http://ojlzx3sl8.bkt.clouddn.com/171vgurvy.mp3
         */

        private String picture;
        private String createTime;
        private int duration;
        private String title;
        private String albumId;
        private int hisId;
        private String musicUrl;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public int getHisId() {
            return hisId;
        }

        public void setHisId(int hisId) {
            this.hisId = hisId;
        }

        public String getMusicUrl() {
            return musicUrl;
        }

        public void setMusicUrl(String musicUrl) {
            this.musicUrl = musicUrl;
        }
    }
}
