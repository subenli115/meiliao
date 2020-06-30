package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class RecMusicData extends Result {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<RecommendBean.DataBean.RecMusicBean> recMusic;

        public List<RecommendBean.DataBean.RecMusicBean> getRecMusic() {
            return recMusic;
        }

        public void setRecMusic(List<RecommendBean.DataBean.RecMusicBean> recMusic) {
            this.recMusic = recMusic;
        }

    }
}
