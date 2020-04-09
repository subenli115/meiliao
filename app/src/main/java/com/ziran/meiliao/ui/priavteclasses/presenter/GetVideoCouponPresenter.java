package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.GetTicketBean;
import com.ziran.meiliao.ui.priavteclasses.contract.GetVideoCouponContract;

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

public class GetVideoCouponPresenter extends GetVideoCouponContract.Presenter {


    @Override
    public void getVideoCouponData(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<GetTicketBean>(GetTicketBean.class) {
            @Override
            public void onSuccess(GetTicketBean result) {
                switch (result.getResultCode()) {
                    case 1:
                        mView.getVideoCouponData(result.getData());
                }
            }
        });
    }

}
