package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.SearchAllResultBean;
import com.ziran.meiliao.ui.bean.SearchHotTagBean;
import com.ziran.meiliao.ui.priavteclasses.contract.SearchContract;

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

public class SearchPresenter extends SearchContract.Presenter {


    @Override
    public void getHotTag(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<SearchHotTagBean>(SearchHotTagBean.class) {
            @Override
            public void onSuccess(SearchHotTagBean result) {
                switch (result.getResultCode()) {
                    case 1:
                        mView.getHotTag(result);
                        break;
                }
            }
        });
    }

    @Override
    public void searchResult(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<SearchAllResultBean>(SearchAllResultBean.class,mView) {
            @Override
            public void onSuccess(SearchAllResultBean result) {
                mView.searchResult(result.getData());
            }
        });
    }
}
