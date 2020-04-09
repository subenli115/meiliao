package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.io.Serializable;
import java.util.List;

public class FitnessSavePracticeBean extends Result {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {


        /**
         * isFirstFinished : false
         */

        private boolean isFirstFinished;
        private List<MusicsBean> musics;

        public boolean isIsFirstFinished() {
            return isFirstFinished;
        }

        public void setIsFirstFinished(boolean isFirstFinished) {
            this.isFirstFinished = isFirstFinished;
        }

        public List<MusicsBean> getMusics() {
            return musics;
        }

        public void setMusics(List<MusicsBean> musics) {
            this.musics = musics;
        }

        public static class MusicsBean implements Serializable{
            /**
             * duration : 00:34:13
             * loadUrl : http://ojlzx3sl8.bkt.clouddn.com/296osgxwg.mp3
             * name : 光的靜心--早安曲
             * image : http://ojlzx3sl8.bkt.clouddn.com/js_rec_music_3.png
             */

            private String duration;
            private String loadUrl;
            private String name;
            private String image;

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getLoadUrl() {
                return loadUrl;
            }

            public void setLoadUrl(String loadUrl) {
                this.loadUrl = loadUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }




}
