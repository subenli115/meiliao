package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.ui.priavteclasses.contract.SJKZhuanLanBigInContract;

import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/11 10:46
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/11$
 * @updateDes ${TODO}
 */

public class SJKZhuanLanBinInPresenter extends SJKZhuanLanBigInContract.Presenter {

    @Override
    public void getData(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<ZhuanLanBigInBean>(ZhuanLanBigInBean.class,mView) {
            @Override
            public void onSuccess(ZhuanLanBigInBean result) {
                mView.showData(result.getData());
            }
        });
    }

    @Override
    public void postGz(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showGzResult(result);
            }
        });
    }

    @Override
    public void postBuy(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showBuyResult(result);
            }
        });
    }

    @Override
    public void postListenUp(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {

            }
        });
    }
}
