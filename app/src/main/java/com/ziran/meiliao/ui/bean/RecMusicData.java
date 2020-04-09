package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class RecMusicData extends Result {

    /**
     * data : {"recMusic":[{"picture":"https://www.psytap.com/wpyx_longjg/resource/images/albumPush/139.png","title":"中脉7轮冥想法","name":"郭怀慈","descript":"      领人走入一趟最纯净的精神之旅，那些被压抑的感情和感觉也将会得到纾解。","musicId":139,"url":"wHMNdIEZ+lEuXc+/b9mEMsHI56CpyyRnYxH17yntuPAG6GldKlhlqp2q9hBUQHj26L879N0TPC/ClostSystemDialogReceiver\niGeba2KlUuyqTR0qnSricXAMplWAr8Q="}]}
     */

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
