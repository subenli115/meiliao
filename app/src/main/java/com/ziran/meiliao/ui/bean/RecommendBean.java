package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;

import java.util.List;

/**
 * Created by Administrator on 2017/1/14.
 */

public class RecommendBean extends Result {


    /**
     * data : {"column":[{"name":"正念","albumList":[{"picture":"1.png","id":1,"author":{"name":"陈先生"},"detail":"这是音频的简介","title":"10天深度睡眠","listenCount":8888}]},{"name":"正念","albumList":[{"picture":"1.png","id":1,"author":{"name":"陈先生"},"detail":"这是音频的简介","title":"10天深度睡眠","listenCount":8888}]},{"name":"正念","albumList":[{"picture":"1.png","id":1,"author":{"name":"陈先生"},"detail":"这是音频的简介","title":"10天深度睡眠","listenCount":8888}]}],"pic":"1.png","recMusic":[{"picture":"1.png","descript":"这是一首很好的音乐","musicId":1},{"picture":"1.png","descript":"这是一首很好的音乐","musicId":1},{"picture":"1.png","descript":"这是一首很好的音乐","musicId":1},{"picture":"1.png","descript":"这是一首很好的音乐","musicId":1}]}
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
         * column : [{"name":"正念","albumList":[{"picture":"1.png","id":1,"author":{"name":"陈先生"},"detail":"这是音频的简介","title":"10天深度睡眠","listenCount":8888}]},{"name":"正念","albumList":[{"picture":"1.png","id":1,"author":{"name":"陈先生"},"detail":"这是音频的简介","title":"10天深度睡眠","listenCount":8888}]},{"name":"正念","albumList":[{"picture":"1.png","id":1,"author":{"name":"陈先生"},"detail":"这是音频的简介","title":"10天深度睡眠","listenCount":8888}]}]
         * pic : 1.png
         * recMusic : [{"picture":"1.png","descript":"这是一首很好的音乐","musicId":1},{"picture":"1.png","descript":"这是一首很好的音乐","musicId":1},{"picture":"1.png","descript":"这是一首很好的音乐","musicId":1},{"picture":"1.png","descript":"这是一首很好的音乐","musicId":1}]
         */

        private String pic;
        private List<PicsBean> pics;
        private List<ColumnBean> column;
        private List<RecMusicBean> recMusic;

        public List<PicsBean> getPics() {
            return pics;
        }

        public void setPics(List<PicsBean> pics) {
            this.pics = pics;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public List<ColumnBean> getColumn() {
            return column;
        }

        public void setColumn(List<ColumnBean> column) {
            this.column = column;
        }

        public List<RecMusicBean> getRecMusic() {
            return recMusic;
        }

        public void setRecMusic(List<RecMusicBean> recMusic) {
            this.recMusic = recMusic;
        }

        public static class ColumnBean {
            /**
             * name : 正念
             * albumList : [{"picture":"1.png","id":1,"author":{"name":"陈先生"},"detail":"这是音频的简介","title":"10天深度睡眠","listenCount":8888}]
             */

            private String name;
            private List<AlbumListBean> albumList;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<AlbumListBean> getAlbumList() {
                return albumList;
            }

            public void setAlbumList(List<AlbumListBean> albumList) {
                this.albumList = albumList;
            }


            @Override
            public String toString() {
                return "ColumnBean{" + "name='" + name + '\'' + ", albumList=" + albumList + '}';
            }
        }

        public static class RecMusicBean {
            /**
             * picture : 1.png
             * descript : 这是一首很好的音乐
             * musicId : 1
             */
            private String picture;
            private String descript;
            private int musicId;
            private String url;
            private String title;
            private String name;
            public String getTitle() {
                return title;
            }


            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return AES.get().decrypt(url);
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getDescript() {
                return descript;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setDescript(String descript) {
                this.descript = descript;
            }

            public int getMusicId() {
                return musicId;
            }

            public void setMusicId(int musicId) {
                this.musicId = musicId;
            }

            public boolean isStartPlay;

            public boolean isStartPlay() {
                return isStartPlay;
            }

            public void setStartPlay(boolean startPlay) {
                isStartPlay = startPlay;
            }


            @Override
            public String toString() {
                return "RecMusicData{" +
                        ", picture='" + picture + '\'' +
                        ", descript='" + descript + '\'' +
                        ", musicId=" + musicId +
                        ", url='" + url + '\'' +
                        ", isStartPlay=" + isStartPlay +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "pic='" + pic + '\'' +
                    ", column=" + column +
                    ", recMusic=" + recMusic +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RecommendBean{" +
                "data=" + data +
                '}';
    }
}
