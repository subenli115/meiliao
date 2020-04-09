package com.ziran.meiliao.ui.decompressionmuseum.presenter;

import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.NoteResultBean;
import com.ziran.meiliao.ui.bean.NotesQuesBean;
import com.ziran.meiliao.ui.decompressionmuseum.contract.TakeNotesContract;

import java.util.List;
import java.util.Map;


/**
 *  做笔记或更新笔记页面契约类
 */
public class TakeNotesPresenter extends TakeNotesContract.Presenter {


    @Override
    public void postPractice(String url, Map<String, String> map) {
        mModel.post(url, map, new NewRequestCallBack<NoteResultBean>(NoteResultBean.class) {

            @Override
            protected void onSuccess(NoteResultBean result) {
                mView.practiceResult(result.getData());
            }
        });
    }


    @Override
    public void postPracticeImg(String url, Map<String, String> map, List<String> imgs) {
        OkHttpClientManager._postContentAndFiles(url,map, FileUtil.str2File(imgs),new NewRequestCallBack<Result>(Result.class) {
            @Override
            public void onSuccess(Result result) {
            mView.practiceUploadResult(result);
            }

            @Override
            public void onError(String msg, int code) {

                if (code==-1){
                    msg = "服务器繁忙,请稍后再上传";
                }
                mView.showError(msg);
            }
        });
    }

    @Override
    public void getNoteQues(String url, Map<String, String> map) {
        mModel.getData(url, map, new NewRequestCallBack<NotesQuesBean>(NotesQuesBean.class) {
            @Override
            public void onSuccess(NotesQuesBean result) {
                mView.showNoteQues(result);
            }
        });
    }
}
