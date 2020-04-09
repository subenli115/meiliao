package com.ziran.meiliao.ui.decompressionmuseum.contract;

import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.base.BaseView;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.NoteResultBean;
import com.ziran.meiliao.ui.bean.NotesQuesBean;

import java.util.List;
import java.util.Map;

/**
 * 做笔记和更新笔记契约类
 */
public interface TakeNotesContract {

    interface View   extends BaseView {
        void practiceResult(NoteResultBean.DataBean result);
        void practiceUploadResult(Result result);
        void showNoteQues(NotesQuesBean result);
        void showError(String result);
    }

    abstract  class Presenter extends BasePresenter<View, CommonModel> {
        public abstract void postPractice(String url, Map<String,String> map );
        public abstract void postPracticeImg(String url, Map<String,String> map , List<String> imgs);
        public abstract void getNoteQues(String url,Map<String,String> map);
    }
}
