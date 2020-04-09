package com.ziran.meiliao.ui.decompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.AlbumBgMusicBean;
import com.ziran.meiliao.ui.bean.AlbumBuyReturn;
import com.ziran.meiliao.ui.bean.AlbumPracBean;
import com.ziran.meiliao.ui.bean.AlbumShareBean;

import java.util.Map;

/**
 *  正念记录契约类
 */
public interface AlbumPlayContract {

    interface View   extends BaseView {
        void showAlbumShare(AlbumShareBean.DataBean result);
        void showPurseBuyAlbum(AlbumBuyReturn.DataBean result);
        void showAlbumPrac(AlbumPracBean.DataBean result);
        void showBgMusic(AlbumBgMusicBean result);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {

        public abstract void getAlbumShare(String url,Map<String,String> map);
        public abstract void postPurseBuyAlbum(String url,Map<String,String> map);
        public abstract void postAlbumPrac(String url,Map<String,String> map);
        public abstract void getBgMusic(String url,Map<String,String> map);
    }
}
