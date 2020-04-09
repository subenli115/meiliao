package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.TrailerDetailBean;
import com.ziran.meiliao.ui.priavteclasses.contract.TrailerContract;

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

public class TrailerPresenter extends TrailerContract.Presenter {


    @Override
    public void getTrailerNative(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<TrailerDetailBean>(TrailerDetailBean.class) {
            @Override
            public void onSuccess(TrailerDetailBean trailerDetailBean) {
                mView.updateHead(trailerDetailBean.getData());
                mView.showList(trailerDetailBean.getData());
            }
        });
    }
}
