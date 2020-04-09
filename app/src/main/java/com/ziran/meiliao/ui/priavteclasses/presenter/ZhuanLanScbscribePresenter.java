package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.RecordListBean;
import com.ziran.meiliao.ui.priavteclasses.contract.ZhuanLanSubscribeContract;

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

public class ZhuanLanScbscribePresenter extends ZhuanLanSubscribeContract.Presenter {


    @Override
    public void getRecordList(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<RecordListBean>(RecordListBean.class) {
            @Override
            public void onSuccess(RecordListBean result) {
                mView.showRecordList(result.getData());

            }
            @Override
            public void showEmpty(RecordListBean result) {
                mView.showRecordList(null);
            }
        });
    }

    @Override
    public void postBuySpecColumn(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showBuySpecColumnResult(result);
            }
        });
    }
}
