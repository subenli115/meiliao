package com.ziran.meiliao.ui.decompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.AlbumBuyReturn;
import com.ziran.meiliao.ui.bean.AlbumPracBean;
import com.ziran.meiliao.ui.bean.AlbumShareBean;
import com.ziran.meiliao.ui.decompressionmuseum.contract.AlbumPlayContract;

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
public class AlbumPlayPresenter extends AlbumPlayContract.Presenter {


    @Override
    public void getAlbumShare(String url, Map<String, String> map) {
        mModel.post(url, map, new NewRequestCallBack<AlbumShareBean>(AlbumShareBean.class) {
            @Override
            public void onSuccess(AlbumShareBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showAlbumShare(result.getData());
            }
        });
    }

    @Override
    public void postPurseBuyAlbum(String url, Map<String, String> map) {
        mModel.post(url, map, new NewRequestCallBack<AlbumBuyReturn>(AlbumBuyReturn.class) {
            @Override
            public void onSuccess(AlbumBuyReturn result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showPurseBuyAlbum(result.getData());
            }
        });
    }

    @Override
    public void postAlbumPrac(String url, Map<String, String> map) {
        mModel.post(url, map, new NewRequestCallBack<AlbumPracBean>(AlbumPracBean.class) {
            @Override
            public void onSuccess(AlbumPracBean result) {
                if (EmptyUtils.isNotEmpty(result.getData()))
                    mView.showAlbumPrac(result.getData());
            }
        });
    }

    @Override
    public void getBgMusic(String url, Map<String, String> map) {
//        mModel.post(url, map, new NewRequestCallBack<AlbumBgMusicBean>(AlbumBgMusicBean.class) {
//            @Override
//            public void onSuccess(AlbumBgMusicBean result) {
//                if (EmptyUtils.isNotEmpty(result.getData()))
//                    mView.showBgMusic(result.getData());
//            }
//        });
    }
}
