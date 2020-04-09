package com.ziran.meiliao.ui.settings.presenter;

import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.BankInfoBean;
import com.ziran.meiliao.ui.bean.WithDrawalsBean;
import com.ziran.meiliao.ui.settings.contract.WithDrawalsContract;

import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class WithDrawalsPresenter extends WithDrawalsContract.Presenter {



    @Override
    public void getListBank(String url, Map<String, String> map) {
        mModel.post(url, map, new NewRequestCallBack<BankInfoBean>(BankInfoBean.class) {
            @Override
            public void onSuccess(BankInfoBean result) {
                mView.showListBank(result);
            }
        });
    }

    @Override
    public void postDrawCash(String url, Map<String, String> map) {
        mModel.post(url, map, new NewRequestCallBack<WithDrawalsBean>(WithDrawalsBean.class) {
            @Override
            public void onSuccess(WithDrawalsBean result) {
                mView.showDrawCash(result.getData());
            }
        });
    }
}
