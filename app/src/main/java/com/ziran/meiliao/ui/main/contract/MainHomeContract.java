package com.ziran.meiliao.ui.main.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.HomeDataBean;
import com.ziran.meiliao.ui.bean.NewHomeDataBean;
import com.ziran.meiliao.ui.bean.PractiveChartBean;
import com.ziran.meiliao.ui.bean.StringDataBean;

import java.util.List;
import java.util.Map;

/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface MainHomeContract {


    interface View   extends BaseView {
        void showHomeData(HomeDataBean result);
        void showUnReadCount(StringDataBean result);
        void showAuthor(AlbumBean result);
        void showChartData(PractiveChartBean.DataBean result);
        void showNewHomeData(NewHomeDataBean result);
        void updateCC(NewHomeDataBean.DataBean.RecAlbumBean result);
        void showMusicList(List<MusicEntry> musicEntries);
        void showNewMusicList(List<MusicEntry> musicEntries);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void getUnReadCount(Map<String,String> map);
        public abstract void getHomeData(Map<String,String> map);
        public abstract void getAuthorData(String url,Map<String,String> map);
        public abstract void getChartData(String url, Map<String,String> map );
        public abstract void getNewHomeData(Map<String,String> map);

    }
}
