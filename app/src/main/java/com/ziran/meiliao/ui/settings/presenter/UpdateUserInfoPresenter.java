package com.ziran.meiliao.ui.settings.presenter;

import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.UpdateUserHeadBean;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.settings.contract.UpdateUserInfoContract;

import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class UpdateUserInfoPresenter extends UpdateUserInfoContract.Presenter {

    @Override
    public void updateUserHead(List<File> files, Map<String, String> maps) {

        mModel.updateUserHead(files, maps, new NewRequestCallBack<UpdateUserHeadBean>(UpdateUserHeadBean.class) {
            @Override
            public void onSuccess(UpdateUserHeadBean result) {
                LoadingDialog.cancelDialogForLoading();
               mView.returnUserHead(result);
            }

            @Override
            public void onError(String msg, int code) {
                super.onError(msg, code);
                LoadingDialog.cancelDialogForLoading();
            }
        });

    }

    @Override
    public void updateUserInfo(Map<String, String> map) {
        mModel.updateUserInfo(map, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                ToastUitl.showShort(mContext.getString(R.string.update_ok));
                mView.returnUserInfo(result);
            }
        });
    }
}
