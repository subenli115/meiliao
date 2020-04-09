package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/12 14:42
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/12$
 * @updateDes ${TODO}
 */

public class NoteResultBean extends Result {

    /**
     * data : {"noteId":68}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * noteId : 68
         */

        private String noteId;

        public String getNoteId() {
            return noteId;
        }

        public void setNoteId(String noteId) {
            this.noteId = noteId;
        }
    }
}
