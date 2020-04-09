package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.BankInfoBean;
import com.ziran.meiliao.ui.priavteclasses.contract.EditCardMessageContract;

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

public class EditCardMessagePresenter extends EditCardMessageContract.Presenter {


    @Override
    public void getCode(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.returnCode(result);
            }
        });
    }

    @Override
    public void bindCard(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<BankInfoBean>(BankInfoBean.class) {
            @Override
            public void onSuccess(BankInfoBean result) {
                mView.bindCard(result.getData());
            }
        });
    }
}
