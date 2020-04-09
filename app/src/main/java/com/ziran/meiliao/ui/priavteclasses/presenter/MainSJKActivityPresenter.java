package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.SJKActivityBean;
import com.ziran.meiliao.ui.priavteclasses.contract.MainSJKActivityContract;

import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/11 9:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/11$
 * @updateDes ${TODO}
 */

public class MainSJKActivityPresenter extends MainSJKActivityContract.Presenter {
    @Override
    public void getActivityList(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<SJKActivityBean>(SJKActivityBean.class,mView) {
            @Override
            public void onSuccess(SJKActivityBean result) {
                mView.stopLoading();
                mView.showList(result.getData());
            }
        });
    }

}
