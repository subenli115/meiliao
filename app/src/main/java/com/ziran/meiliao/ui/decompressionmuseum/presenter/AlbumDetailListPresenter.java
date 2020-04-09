package com.ziran.meiliao.ui.decompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.AuthorPageBean;
import com.ziran.meiliao.ui.bean.StringDataBean;
import com.ziran.meiliao.ui.decompressionmuseum.contract.AlbumDetailListContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/30 14:35
 * @des ${专辑详情契约类}
 * @updateAuthor $Author$
 * @updateDate 2017/8/30$
 * @updateDes ${TODO}
 */
public class AlbumDetailListPresenter extends AlbumDetailListContract.Presenter {
    private boolean isShowAuthor;
    @Override
    public void getAuthorData(String url, Map<String, String> map) {

        mModel.getData(url, map, new NewRequestCallBack<AuthorPageBean>(AuthorPageBean.class,mView) {
            @Override
            public void onSuccess(AuthorPageBean result) {
                if (!isShowAuthor){
                    isShowAuthor = true;
                   mView.showAuthor(result.getData().getAlbum());
                }
                List<AuthorPageBean.DataBean.AlbumCatalogBean> data = result.getData().getAlbumCatalog();
                //转换音频数据
                List<MusicEntry> resultMusics = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    AuthorPageBean.DataBean.AlbumCatalogBean albumBean = data.get(i);
                    if (EmptyUtils.isNotEmpty(albumBean.getCatalogName())) {
                        resultMusics.add(MusicEntry.getCatalog(albumBean.getCatalogName()));
                    }
                    List<MusicEntry> authorAlbumMusic = albumBean.getAlbumMusic();
                    if (EmptyUtils.isNotEmpty(authorAlbumMusic)) {
                        resultMusics.addAll(authorAlbumMusic);
                    }
                }
                mView.showMusicList(resultMusics,result.getData().getAlbum().isBuy());
            }
        });
    }

    @Override
    public void postCollectAlbum(String url, Map<String, String> map) {
        mModel.post(url, map, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            public void onSuccess(StringDataBean result) {
                mView.showCollectAlbum(result);
            }
        });
    }

    @Override
    public void postCollectMusic(String url, Map<String, String> map) {
        mModel.post(url, map, new NewRequestCallBack<StringDataBean>(StringDataBean.class) {
            @Override
            public void onSuccess(StringDataBean result) {
                mView.showCollectMusic(result);
            }
        });
    }
}
