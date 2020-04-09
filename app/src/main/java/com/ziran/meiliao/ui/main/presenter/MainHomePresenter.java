package com.ziran.meiliao.ui.main.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.HomeDataBean;
import com.ziran.meiliao.ui.bean.NewAuthorPageBean;
import com.ziran.meiliao.ui.bean.NewHomeDataBean;
import com.ziran.meiliao.ui.bean.PractiveChartBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.main.contract.MainHomeContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class MainHomePresenter extends MainHomeContract.Presenter {

    private boolean isShowAuthor;
    @Override
    public void getAuthorData(String url, Map<String, String> map) {

        mModel.getData(url, map, new NewRequestCallBack<NewAuthorPageBean>(NewAuthorPageBean.class,mView) {
            @Override
            public void onSuccess(NewAuthorPageBean result) {
                if (!isShowAuthor){
                    isShowAuthor = true;
                    mView.showAuthor(result.getData().getAlbum());
                }
                List<NewAuthorPageBean.DataBean.AlbumCatalogBean> data = result.getData().getAlbumCatalog();
                //转换音频数据
                List<MusicEntry> resultMusics = new ArrayList<>();
                List<MusicEntry> newResultMusics = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    NewAuthorPageBean.DataBean.AlbumCatalogBean albumBean = data.get(i);
                    if (EmptyUtils.isNotEmpty(albumBean.getCatalogName())) {
                        resultMusics.add(MusicEntry.getCatalog(albumBean.getCatalogName()));
                    }
                    List<MusicEntry> authorAlbumMusic = albumBean.getAlbumMusic();

                    if (EmptyUtils.isNotEmpty(authorAlbumMusic)) {
                        resultMusics.addAll(authorAlbumMusic);
                    }
                }
                for(int j=0;j<result.getData().getAlbumCatalog().size();j++){
                    for(int k=0;k<data.get(j).getAlbumMusic().size();k++){
                        newResultMusics.add(data.get(j).getAlbumMusic().get(k));
                    }
                }
                mView.showNewMusicList(newResultMusics);
                mView.showMusicList(resultMusics);
            }
        });
    }
    @Override
    public void getUnReadCount(Map<String, String> map) {
        mModel.getData(ApiKey.MESSAGE_GET_NO_READ_COUNT, map, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            protected void onSuccess(StringDataBean result) {
                mView.showUnReadCount(result);
            }
        });
    }

    @Override
    public void getHomeData(Map<String, String> map) {
        mModel.getData(ApiKey.HOME_GET_DATA, map, new NewRequestCallBack<HomeDataBean>(HomeDataBean.class) {
            @Override
            protected void onSuccess(HomeDataBean result) {
                mView.showHomeData(result);
            }
        });
    }

    @Override
    public void getNewHomeData(final Map<String, String> map) {
        mModel.getData(ApiKey.HOME_GDATAV2, map, new NewRequestCallBack<NewHomeDataBean>(NewHomeDataBean.class) {

            @Override

            protected void onSuccess(NewHomeDataBean result) {
                if(map.get("page").equals(1+"")){
                    mView.showNewHomeData(result);
                }else{
                    mView.updateCC(result.getData().getRecAlbum());
                }
            }
        });
    }
    @Override
    public void getChartData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<PractiveChartBean>(PractiveChartBean.class) {
            @Override
            public void onSuccess(PractiveChartBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showChartData(result.getData());
            }
        });
    }
}
