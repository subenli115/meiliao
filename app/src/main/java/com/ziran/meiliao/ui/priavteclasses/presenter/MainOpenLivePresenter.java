package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.TeacherGoinBean;
import com.ziran.meiliao.ui.bean.TeacherPageBean;
import com.ziran.meiliao.ui.priavteclasses.contract.MainOpenLiveContract;

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

public class MainOpenLivePresenter extends MainOpenLiveContract.Presenter {
    @Override
    public void getTeacherIndex(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<TeacherPageBean>(TeacherPageBean.class,mView) {
            @Override
            public void onSuccess(TeacherPageBean result) {
                mView.stopLoading();
                mView.showData(result.getData());
            }
        });
    }

    @Override
    public void checkOpenLive(String url, Map<String, String> params) {
        mModel.getData(url, params, new NewRequestCallBack<TeacherGoinBean >(TeacherGoinBean.class) {
            @Override
            public void onSuccess(TeacherGoinBean result) {
                mView.openLive(result.getData());
            }
        });
    }
}
