package com.ziran.meiliao.ui.decompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.StringDataBean;

import java.util.List;
import java.util.Map;

/**
 *  正念记录契约类
 */
public interface AlbumDetailListContract {

    interface View   extends BaseView {

        void showCollectAlbum(StringDataBean result);
        void showCollectMusic(StringDataBean result);
        void showAuthor(AlbumBean result);
        void showMusicList(List<MusicEntry> musicEntries, boolean buy);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {

        public abstract void getAuthorData(String url,Map<String,String> map);
        public abstract void postCollectAlbum(String url,Map<String,String> map);
        public abstract void postCollectMusic(String url,Map<String,String> map);
    }
}
