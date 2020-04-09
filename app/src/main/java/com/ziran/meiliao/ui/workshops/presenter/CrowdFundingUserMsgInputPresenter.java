package com.ziran.meiliao.ui.workshops.presenter;

import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.workshops.contract.CrowdFundingUserMsgInputContract;

import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class CrowdFundingUserMsgInputPresenter extends CrowdFundingUserMsgInputContract.Presenter {


    @Override
    public void postData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<Result>(Result.class) {
            @Override
            protected void onSuccess(Result result) {
                mView.showData(result);
            }
        });
    }

    @Override
    public void postImg(String url, Map<String, String> params, List<File> files) {
        OkHttpClientManager._postContentAndFiles(url, params, files, new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
                mView.showPostImgResult(result);
            }
            @Override
            public void onError(String msg, int code) {
                if (code == -1) {
                    msg = "服务器繁忙,请稍后再上传";
                }
                LoadingDialog.cancelDialogForLoading();
                mView.showErrorTip(msg);
            }
        });
    }
}
