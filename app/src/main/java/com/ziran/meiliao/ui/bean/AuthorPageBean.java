package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.MusicEntry;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */

public class AuthorPageBean extends Result{


    /**
     * data : {"album":{"collectCount":1,"detail":"这个专辑很不错","title":"七天睡眠","headImg":null,"name":"玛吉薇蒳","isCollectAlbum":false,"shareUrl":""},"albumCatalog":[{"catalogName":"初学者的冥想书","albumMusic":[{"duration":"00:18:24","name":"冥想1：观呼吸","url":"/static/upload/music/albumMusic/1.mp3","isCollectMusic":false}]},{"catalogName":"能量清理冥想","albumMusic":[]},{"catalogName":"朵琳.芙秋冥想","albumMusic":[]},{"catalogName":"祈祷文","albumMusic":[]}]}
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
         * album : {"collectCount":1,"detail":"这个专辑很不错","title":"七天睡眠","headImg":null,"name":"玛吉薇蒳","isCollectAlbum":false,"shareUrl":""}
         * albumCatalog : [{"catalogName":"初学者的冥想书","albumMusic":[{"duration":"00:18:24","name":"冥想1：观呼吸","url":"/static/upload/music/albumMusic/1.mp3","isCollectMusic":false}]},{"catalogName":"能量清理冥想","albumMusic":[]},{"catalogName":"朵琳.芙秋冥想","albumMusic":[]},{"catalogName":"祈祷文","albumMusic":[]}]
         */

        private AlbumBean album;
        private List<AlbumCatalogBean> albumCatalog;

        public AlbumBean getAlbum() {
            return album;
        }

        public void setAlbum(AlbumBean album) {
            this.album = album;
        }

        public List<AlbumCatalogBean> getAlbumCatalog() {
            return albumCatalog;
        }

        public void setAlbumCatalog(List<AlbumCatalogBean> albumCatalog) {
            this.albumCatalog = albumCatalog;
        }


        public static class AlbumCatalogBean {
            /**
             * catalogName : 初学者的冥想书
             * albumMusic : [{"duration":"00:18:24","name":"冥想1：观呼吸","url":"/static/upload/music/albumMusic/1.mp3","isCollectMusic":false}]
             */

            private String catalogName;
            private List<MusicEntry> albumMusic;

            public String getCatalogName() {
                return catalogName;
            }

            public void setCatalogName(String catalogName) {
                this.catalogName = catalogName;
            }

            public List<MusicEntry> getAlbumMusic() {
                return albumMusic;
            }

            public void setAlbumMusic(List<MusicEntry> albumMusic) {
                this.albumMusic = albumMusic;
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "album=" + album +
                    ", albumCatalog=" + albumCatalog +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AuthorPageBean{" +
                "data=" + data +
                '}';
    }
}
